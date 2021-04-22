package com.hjq.common.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfiguration {


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置如果未登录时需要跳转的url
        shiroFilterFactoryBean.setLoginUrl("");

        //设置登陆成功后跳转的界面或者url，如果是前后端分离，则可以不写该选项
        shiroFilterFactoryBean.setSuccessUrl("");

        //登录成功后，但是未授权，则调到未授权的url
        shiroFilterFactoryBean.setUnauthorizedUrl("");

        //将自定义的角色过滤器放到map里面
        Map<String, Filter> map = new LinkedHashMap<>();

        //这里配置好角色过滤器时，拦截路径时的前缀要写成key的值
        map.put("customRoles", new CustomRoleFilter());
        //自定义的过滤器解决跨域
        map.put("corsFilter", new CorsFilter());

        //将自定义的角色过滤器放到shiroFilterFactoryBean里面去
        shiroFilterFactoryBean.setFilters(map);

//       ！！！！这里有个坑：一定要使用LinkedHashMap而不是hashmap，因为hashmap是无序的，
//        对于路径的映射，一旦匹配到就会立即返回，我配置也是有顺序的，使用了hashmap->路径有时匹配成功，有时失败
        //拦截器路径，同一拦截，注意要使用linkedHashMap保证过滤器的顺序
        Map<String, String> filterMap = new LinkedHashMap<>();

        //key是需要拦截的路径，value采用哪种过滤器，或者那种角色或权限
        //跨域拦截
        filterMap.put("/**", "corsFilter");

        //登出过滤器
        filterMap.put("", "logout");

        //匿名访问，无需的登录即可访问
        filterMap.put("", "anon");

        //需要登录才能访问的
        filterMap.put("", "authc");

        //有相应角色才能访问的,例如管理员才能访问
        filterMap.put("", "customRoles[admin,root]"); //这里对应上面自定义AuthorizationFilter的key

        //有相应权限才能访问的，例如有
        filterMap.put("", "perms[*]");

        //全局拦截，避免遗漏哪些路径，放到最下面，这里也是要求必须使用linkedhashmap的理由
        filterMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //安全管理器
    @Bean
    public SecurityManager securityManager(RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //设置会话管理
        manager.setSessionManager(sessionManager());
        //设置realm
        manager.setRealm(shiroRealm());
        //设置缓存
        manager.setCacheManager(redisCacheManager);
        return manager;
    }

    //自定义realm
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm().setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm();
    }

    //自定义session管理器
    @Bean
    public SessionManager sessionManager() {
        SessionManager sessionManager = new SessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    //密码匹配器
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Autowired
    private RedisConfig redisConfig;

    //缓存管理
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setDatabase(redisConfig.getDatabase());
        redisManager.setHost(redisConfig.getHost());
        redisManager.setPort(redisConfig.getPort());
        redisManager.setPassword(redisConfig.getPassword());
        return redisManager;
    }

    //cache管理器
    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        //设置过期时间，单位是秒
        redisCacheManager.setExpire(60 * 30);
        return redisCacheManager;
    }

    //设置sessionDao
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
}
