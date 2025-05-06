package com.xm.agent.config;

import com.theokanning.openai.service.OpenAiService;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.theokanning.openai.client.OpenAiApi;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.Duration;

import static com.theokanning.openai.service.OpenAiService.defaultClient;
import static com.theokanning.openai.service.OpenAiService.defaultObjectMapper;

@Data
@Configuration
@ConfigurationProperties(prefix = "openai")
public class OpenAIConfig {
    private String apiKey;
    private String model;
    private String baseUrl;
    private Duration timeout = Duration.ofSeconds(60);

    @Bean
    public OpenAiService openAiService() {
        OkHttpClient client = defaultClient(apiKey, timeout)
                .newBuilder()
                // .addInterceptor(chain -> {
                //     okhttp3.Request originalRequest = chain.request();
                //     // okhttp3.Request requestWithHeaders = originalRequest.newBuilder()
                //     //         .header("HTTP-Referer", "https://yourwebsite.com")  // 替换为你的网站
                //     //         .header("X-Title", "XM Agent")  // 你的应用名称
                //     //         .build();
                //     return chain.proceed(requestWithHeaders);
                // })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create(defaultObjectMapper()))
                .build();

        OpenAiApi api = retrofit.create(OpenAiApi.class);
        return new OpenAiService(api);
    }
} 