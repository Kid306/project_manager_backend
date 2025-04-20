package com.mdp.micro.client.resolver;

import javax.servlet.http.HttpServletRequest;

/**
 * 解析前端请求头中的Bearer值 Authorization: Bearer tokenValue
 */
public interface BearerTokenResolver {
    /**
     * 解析前端请求头中的Bearer值 Authorization: Bearer tokenValue
     * @return tokenValue
     */
    String resolve(HttpServletRequest var1);
}