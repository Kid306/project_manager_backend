package com.mdp.micro.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration

@ConditionalOnMissingClass({"com.mdp.oauth2.client.WebClientConfig"})
public class ClientConfig {


    @Bean
    WebClient webClient(){
       return WebClient.create();
    }

}