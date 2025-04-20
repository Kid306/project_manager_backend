package com.mdp.micro.client.resolver;

import javax.servlet.http.HttpServletRequest;

public interface BasicTokenResolver {
    /**
     * 解析前端请求头中的Basic值 Authorization: Basic tokenValue
     * @return tokenValue
     */
    String resolve(HttpServletRequest request);
}
