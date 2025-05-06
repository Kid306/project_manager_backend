package com.xm.agent.config;

import com.theokanning.openai.service.OpenAiService;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Data
@Configuration
@ConfigurationProperties(prefix = "openai")
public class OpenAIConfig {
    private String apiKey;
    private String model = "gpt-3.5-turbo";
    private Duration timeout = Duration.ofSeconds(60);

    @Bean
    public OpenAiService openAiService() {
        return OpenAiService.builder()
                .apiKey(apiKey)
                .timeout(timeout)
                .build();
    }
} 