package com.mdp.core.config;
 
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
/**
 * 处理mybatisplus 转时间字段为LocalDateTime类型，而springboot2.3.4.RELEASE
 */
@Configuration
public class LocalDateTimeSerializerConfig {
 
    @Value("${spring.jackson.date-format}")
    private String pattern;
 
    @Bean
    public LocalDateTimeSerializer localDateTimeDeserializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }
 
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
    }
 
}
 