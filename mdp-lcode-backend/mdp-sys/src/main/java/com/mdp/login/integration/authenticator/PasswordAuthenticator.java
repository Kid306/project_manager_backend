package com.mdp.login.integration.authenticator;

import org.springframework.stereotype.Component;

/**
 * 默认登录处理,密码方式
 * 格式 url: http://${domain}/api/m1/oauth2/oauth/token?grant_type=password&username=${username}&password=${前端密码MD5加密后的密码}&scope=all
 * @author chenyc
 * @date 2021-01-12
 **/
@Component
public class PasswordAuthenticator extends AuthenticatorAdapter{


}
