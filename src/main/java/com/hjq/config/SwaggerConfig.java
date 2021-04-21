package com.hjq.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {


    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Contact contact = new Contact("hjq","www.miaosha.org","123@qq.com");
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("秒杀系统接口文档")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact(contact)
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("1.0")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.hjq.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}