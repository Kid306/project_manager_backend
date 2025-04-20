//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mdp.micro.client.resolver;

import com.mdp.core.err.BizException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DefaultBearerTokenResolver  implements BearerTokenResolver {
    private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$", 2);
    private boolean allowFormEncodedBodyParameter = false;
    private boolean allowUriQueryParameter = false;
    private String bearerTokenHeaderName = "Authorization";

    public DefaultBearerTokenResolver() {
    }

    public String resolve(HttpServletRequest request) {
        String authorizationHeaderToken = this.resolveFromAuthorizationHeader(request);
        if (authorizationHeaderToken != null) {
            return authorizationHeaderToken;
        } else {
            String parameterToken = resolveFromRequestParameters(request);
            return parameterToken;
        }
    }

    public void setAllowFormEncodedBodyParameter(boolean allowFormEncodedBodyParameter) {
        this.allowFormEncodedBodyParameter = allowFormEncodedBodyParameter;
    }

    public void setAllowUriQueryParameter(boolean allowUriQueryParameter) {
        this.allowUriQueryParameter = allowUriQueryParameter;
    }

    public void setBearerTokenHeaderName(String bearerTokenHeaderName) {
        this.bearerTokenHeaderName = bearerTokenHeaderName;
    }

    private String resolveFromAuthorizationHeader(HttpServletRequest request) {
        String authorization = request.getHeader(this.bearerTokenHeaderName);
        if (!StringUtils.startsWithIgnoreCase(authorization, "bearer")) {
            return null;
        } else {
            Matcher matcher = authorizationPattern.matcher(authorization);
            if (!matcher.matches()) {
                throw new BizException("访问令牌存在非法字母");
            } else {
                return matcher.group("token");
            }
        }
    }

    private static String resolveFromRequestParameters(HttpServletRequest request) {
        String[] values = request.getParameterValues("access_token");
        if (values != null && values.length != 0) {
            if (values.length == 1) {
                return values[0];
            } else {
                throw new BizException("不允许多个访问令牌存在于请求参数中");
            }
        } else {
            return null;
        }
    }

    private boolean isParameterTokenSupportedForRequest(HttpServletRequest request) {
        return this.allowFormEncodedBodyParameter && "POST".equals(request.getMethod()) || this.allowUriQueryParameter && "GET".equals(request.getMethod());
    }
}
