package com.mdp;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName: SpringDocConfig
 * Package: com.mcode.swaggertest.config
 * Description:
 *
 * @Author: robin
 * @Create: 2023/9/9 - 7:00 PM
 * @Version: v1.0
 */

@Configuration
public class SpringDocConfig {

//    @Bean
//    public GroupedOpenApi productApi() {
//        return GroupedOpenApi.builder()
//                .group("product-service")
//                .pathsToMatch("/product/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi orderApi() {
//        return GroupedOpenApi.builder()
//                .group("order-service")
//                .pathsToMatch("/order/**")
//                .build();
//    }

    @Bean
    public OpenAPI openAPI(){
       // 联系人信息(contact)，构建API的联系人信息，用于描述API开发者的联系信息，包括名称、URL、邮箱等
        Contact contact = new Contact()
                .name("唛盟开源")  // 作者名称
                .email("cyc58469@163.com") // 作者邮箱
                .url("https://www.maimengcloud.com/") // 介绍作者的URL地址
                .extensions(new HashMap<String,Object>());// 使用Map配置信息（如key为"name","email","url"）

        License license = new License()
                .name("MIT")                         // 授权名称 / 授权信息
                .identifier("MIT")                   // 标识授权许可
                .extensions(new HashMap<String, Object>());// 使用Map配置信息（如key为"name","url","identifier"）

        //创建Api帮助文档的描述信息、联系人信息(contact)、授权许可信息(license)
        Info info = new Info()
                .title("唛盟开源-研发管理、协同办公、开发平台等接口")      // Api接口文档标题（必填）
                .description("唛盟开源面向企业提供研发管理、开发平台、协同办公等子系统")     // Api接口文档描述
                .version("3.0.0")                                  // Api接口版本
                .termsOfService("https://www.maimengcloud.com/api/lcode/v3/api-docs\n")    // Api接口的服务条款地址
                .license(license)  //   授权名称                                
                .contact(contact); // 设置联系人信息

         List<Server>  servers = new ArrayList<>(); //多服务
         // 表示服务器地址或者URL模板列表，多个服务地址随时切换（只不过是有多台IP有当前的服务API）
         servers.add(new Server().url("https://www.maimengcloud.com/api/lcode/v3/api-docs").description("低代码平台"));
         servers.add(new Server().url("https://www.maimengcloud.com/api/xm/v3/api-docs").description("项目管理"));
        servers.add(new Server().url("https://www.maimengcloud.com/api/oa/v3/api-docs").description("系统办公"));
        servers.add(new Server().url("https://www.maimengcloud.com/api/mall/v3/api-docs").description("电商客户端"));
        servers.add(new Server().url("https://www.maimengcloud.com/api/mallm/v3/api-docs").description("电商管理端"));
        servers.add(new Server().url("https://www.maimengcloud.com/api/oauth2client/v3/api-docs").description("统一认证中心-登录前置"));
        servers.add(new Server().url("https://www.maimengcloud.com/api/oauth2server/v3/api-docs").description("统一认证中心-服务端"));
        servers.add(new Server().url("https://www.maimengcloud.com/api/tpa/v3/api-docs").description("第三方支付、登录等"));

        // // 设置 spring security apikey accessToken 认证的请求头 X-Token: xxx.xxx.xxx
        SecurityScheme securityScheme = new SecurityScheme()
                .name("x-token") 
                .type(SecurityScheme.Type.APIKEY)
                .description("APIKEY认证描述")
                .in(SecurityScheme.In.HEADER);

        // 设置 spring security jwt accessToken 认证的请求头 Authorization: Bearer xxx.xxx.xxx
        SecurityScheme securityScheme1 = new SecurityScheme()
                .name("JWT认证")
                .scheme("bearer") //如果是Http类型，Scheme是必填
                .type(SecurityScheme.Type.HTTP)
                .description("认证描述")
                .in(SecurityScheme.In.HEADER)
                .bearerFormat("JWT");

        List<SecurityRequirement> securityRequirements = new ArrayList<>();

        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("authScheme");

        securityRequirements.add(securityRequirement);

        // 返回信息
        return new OpenAPI()
                //.openapi("3.0.1")  // Open API 3.0.1(默认)
                .info(info)
                .servers(servers)
                .components(new Components().addSecuritySchemes("authScheme",securityScheme1)) //添加鉴权组件
                .security(securityRequirements) //全部添加鉴权小锁
                .externalDocs(new ExternalDocumentation()
                        .description("唛盟开源面向企业提供研发管理、开发平台、协同办公等子系统") //对外说明
                        .url("https://www.maimengcloud.com/api/"));       // 配置Swagger3.0描述信息
    }
}
