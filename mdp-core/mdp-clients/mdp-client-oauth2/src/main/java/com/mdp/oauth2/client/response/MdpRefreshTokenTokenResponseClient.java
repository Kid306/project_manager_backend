//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mdp.oauth2.client.response;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2RefreshTokenGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2RefreshTokenGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.MapOAuth2AccessTokenResponseConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse.Builder;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;
/**
 * oauth2server 服务端返回的是下横线的字段名，需要MapOAuth2AccessTokenResponseConverter进行转换，否则结果不正确
 */
public final class MdpRefreshTokenTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2RefreshTokenGrantRequest> {
    private static final String INVALID_TOKEN_RESPONSE_ERROR_CODE = "invalid_token_response";
    private Converter<OAuth2RefreshTokenGrantRequest, RequestEntity<?>> requestEntityConverter = new OAuth2RefreshTokenGrantRequestEntityConverter();
    private RestOperations restOperations;
    private MapOAuth2AccessTokenResponseConverter mapOAuth2AccessTokenResponseConverter=new MapOAuth2AccessTokenResponseConverter();


    public MdpRefreshTokenTokenResponseClient() {
        RestTemplate restTemplate = new RestTemplate(Arrays.asList(new FormHttpMessageConverter(), new OAuth2AccessTokenResponseHttpMessageConverter()));
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        this.restOperations = restTemplate;
    }

    public OAuth2AccessTokenResponse getTokenResponse(OAuth2RefreshTokenGrantRequest refreshTokenGrantRequest) {
        Assert.notNull(refreshTokenGrantRequest, "refreshTokenGrantRequest cannot be null");
        RequestEntity<?> request = (RequestEntity)this.requestEntityConverter.convert(refreshTokenGrantRequest);
        OAuth2AccessTokenResponse tokenResponse = this.getResponse(request);;
        if (CollectionUtils.isEmpty(tokenResponse.getAccessToken().getScopes()) || tokenResponse.getRefreshToken() == null) {
            Builder tokenResponseBuilder = OAuth2AccessTokenResponse.withResponse(tokenResponse);
            if (CollectionUtils.isEmpty(tokenResponse.getAccessToken().getScopes())) {
                tokenResponseBuilder.scopes(refreshTokenGrantRequest.getAccessToken().getScopes());
            }

            if (tokenResponse.getRefreshToken() == null) {
                tokenResponseBuilder.refreshToken(refreshTokenGrantRequest.getRefreshToken().getTokenValue());
            }

            tokenResponse = tokenResponseBuilder.build();
        }

        return tokenResponse;
    }

    private OAuth2AccessTokenResponse getResponse(RequestEntity<?> request) {
        try {
            Map map = this.restOperations.exchange(request, Map.class).getBody();
            return mapOAuth2AccessTokenResponseConverter.convert(map);
        } catch (RestClientException var4) {
            OAuth2Error oauth2Error = new OAuth2Error("invalid_token_response", "An error occurred while attempting to retrieve the OAuth 2.0 Access Token Response: " + var4.getMessage(), (String)null);
            throw new OAuth2AuthorizationException(oauth2Error, var4);
        }
    }

    public void setRequestEntityConverter(Converter<OAuth2RefreshTokenGrantRequest, RequestEntity<?>> requestEntityConverter) {
        Assert.notNull(requestEntityConverter, "requestEntityConverter cannot be null");
        this.requestEntityConverter = requestEntityConverter;
    }

    public void setRestOperations(RestOperations restOperations) {
        Assert.notNull(restOperations, "restOperations cannot be null");
        this.restOperations = restOperations;
    }
}
