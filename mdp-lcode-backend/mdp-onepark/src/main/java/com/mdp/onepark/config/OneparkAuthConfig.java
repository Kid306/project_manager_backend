package com.mdp.onepark.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * onenet配置
 *
 * @author 莞蓉科技
 */
@Component
@ConfigurationProperties(prefix = "onepark.auth")
@Data
public class OneparkAuthConfig {
    private String baseUrl;//平台地址
    private String clientId;
    private String clientSecretStr;
    private String apiUsersCurrent;//1.获取当前登录用户
    private String apiOauthToken;//2.获取token
    private String apiLogout; //3.退出登录

}
