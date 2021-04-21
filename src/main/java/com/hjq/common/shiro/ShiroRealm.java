package com.hjq.common.shiro;

import com.hjq.mybatis.entity.User;
import com.hjq.mybatis.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService service;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User userNew =(User) principalCollection.getPrimaryPrincipal();
        User user = service.findByLoginName(userNew.getLoginName());
        List<String> stringRole=new ArrayList<>();
        List<String> stringPermission=new ArrayList<>();
//        for(Role role:user.getRoleList()){
//            if(role!=null){
//                stringRole.add(role.getName());
//                for(Permission permission:role.getPermissionList()){
//                    if(permission!=null){
//                        stringPermission.add(permission.getName());
//                    }
//                }
//            }
//        }
//        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        info.addStringPermissions(stringPermission);
//        info.addRoles(stringRole);
        return null;

    }

    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        User user = service.findByLoginName(loginName);
//        if(user==null||user.getPassword()==null||"".equals(user.getPassword())){
//            return null;
//        }
//        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
//    }
    return null;
    }
}
