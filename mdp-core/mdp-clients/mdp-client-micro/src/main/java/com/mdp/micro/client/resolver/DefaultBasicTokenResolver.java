//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mdp.micro.client.resolver;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public final class DefaultBasicTokenResolver implements BasicTokenResolver {

    private String basicTokenHeaderName = "Authorization";

    public DefaultBasicTokenResolver() {
    }

    public String resolve(HttpServletRequest request) {
        String authorizationHeaderToken = this.resolveFromAuthorizationHeader(request);
        return authorizationHeaderToken;
    }



    private String resolveFromAuthorizationHeader(HttpServletRequest request) {
        String authorization = request.getHeader(this.basicTokenHeaderName);
        if (!StringUtils.startsWithIgnoreCase(authorization, "Basic")) {
            return null;
        } else {
            return authorization.substring(6);
        }
    }

}
