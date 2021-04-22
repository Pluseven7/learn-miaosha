package com.hjq.common.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis")
@PropertySource("classpath:application.properties")
@Data
public class RedisConfig {
    private Integer port;

    private String host;

    private String password;

    private Integer database;

}
