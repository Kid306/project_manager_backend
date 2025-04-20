package com.mdp.oauth2.client.resource;

import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.jwt.JwtTool;
import com.nimbusds.jose.JWSAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtDecoderConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri:}")
    String jwkSetUri="";

    @Value(value = "${mdp.jwt.secret:}")
    String jwtSecret=null;

    @ConditionalOnMissingBean
    @Bean
    @Autowired
    JwtDecoder JwtDecoder(RestTemplate restTemplate){
        if(ObjectTools.isNotEmpty(jwkSetUri)){
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
