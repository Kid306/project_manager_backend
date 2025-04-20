package com.mdp.core.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mdp.core.utils.MdpDateDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JacksonConfig {
 
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.timeZone(java.util.TimeZone.getTimeZone("Asia/Shanghai"))
                .deserializerByType(Date.class,new  MdpDateDeserializer())
                .modules(new JavaTimeModule()).failOnUnknownProperties(false).failOnEmptyBeans(false);
    }
}