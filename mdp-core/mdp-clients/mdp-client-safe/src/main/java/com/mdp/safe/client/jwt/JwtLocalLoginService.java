package com.mdp.safe.client.jwt;

import com.mdp.core.entity.Tips;
import com.mdp.safe.client.entity.CommonUserDetails;
import com.mdp.safe.client.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class JwtLocalLoginService {

    @Autowired
    JwtDecoder jwtDecoder;

    JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    Jwt2AuthenticationConverter jwtConverter;

    public Tips doLocalLoginUseJwtToken(String jwtTokenValue){
        Tips tips = new Tips("登录成功");
        LoginUtils.clearThreadLock();
        BearerTokenAuthenticationToken bearerTokenAuthenticationToken=new BearerTokenAuthenticationToken(jwtTokenValue);
        Authentication authentication=jwtAuthenticationProvider.authenticate(bearerTokenAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tips;
    }

    public CommonUserDetails getUserDetails(){
        return LoginUtils.converterTokenToUserDetails(SecurityContextHolder.getContext().getAuthentication());
    }

    @PostConstruct
    public void init(){
        if(jwtAuthenticationProvider==null){

                jwtAuthenticationProvider=new JwtAuthenticationProvider(jwtDecoder);
                jwtAuthenticationProvider.setJwtAuthenticationConverter(jwtConverter);

        }
    }
}
