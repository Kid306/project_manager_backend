package com.mdp.safe.client.config;

import com.mdp.core.utils.ObjectTools;
import com.mdp.micro.client.CallBizService;
import com.mdp.safe.client.jwt.JwtTool;
import com.mdp.safe.client.pwd.SafePasswordEncoder;
import com.mdp.safe.client.service.remote.*;
import com.nimbusds.jose.JWSAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

@Configuration
public class SafeClientConfig {

    @Value(value = "${mdp.jwt.secret:}")
    String jwtSecret=null;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri:}")
    String jwkSetUri="";

    @Value(value = "${mdp.jwt.connect-oauth2-server:true}")
    Boolean connectOauth2Server=true;

    @Bean
    @Autowired
    @ConditionalOnMissingBean(UserResourceRemoteService.class)
    UserResourceRemoteService userResourceRemoteService(CallBizService callBizService){
        DefaultUserResourceRemoteService userResourceRemoteService=new DefaultUserResourceRemoteService();
        userResourceRemoteService.setCallBizService(callBizService);
        return userResourceRemoteService;
    }

    @Bean
    @Autowired
    @ConditionalOnMissingBean(UserBaseInfoRemoteOperService.class)
    UserBaseInfoRemoteOperService userBaseInfoRemoteOperService(CallBizService callBizService){
        DefaultUserBaseInfoRemoteOperService userBaseInfoRemoteOperService=new DefaultUserBaseInfoRemoteOperService();
        userBaseInfoRemoteOperService.setCallBizService(callBizService);
        return userBaseInfoRemoteOperService;
    }

    @Bean
    @Autowired
    @ConditionalOnMissingBean(UserBaseInfoRemoteQueryService.class)
    UserBaseInfoRemoteQueryService userBaseInfoRemoteQueryService(CallBizService callBizService){
        DefaultUserBaseInfoRemoteQueryService userBaseInfoRemoteQueryService=new DefaultUserBaseInfoRemoteQueryService();
        userBaseInfoRemoteQueryService.setCallBizService(callBizService);
        return userBaseInfoRemoteQueryService;
    }


    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    PasswordEncoder passwordEncoder(){
        PasswordEncoder passwordEncoder=new SafePasswordEncoder();
        return passwordEncoder;
    }
    @Bean
    @ConditionalOnMissingBean(JwtDecoder.class)
    @Autowired
    JwtDecoder JwtDecoder(RestTemplate restTemplate){

        if(ObjectTools.isNotEmpty(jwkSetUri) && connectOauth2Server==true){
            return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).restOperations(restTemplate).build();
        }else {
            String jwtSecret=this.jwtSecret;
            if(ObjectTools.isEmpty(this.jwtSecret)){
                jwtSecret= JwtTool.defaultJwtSecret;
            }
            String jwtSecret64= DatatypeConverter.printBase64Binary(jwtSecret.getBytes(StandardCharsets.UTF_8));
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret64);
            SecretKey signingKey = new SecretKeySpec(apiKeySecretBytes, JWSAlgorithm.HS256.getName());
            return NimbusJwtDecoder.withSecretKey(signingKey).build();
        }
    }
}
