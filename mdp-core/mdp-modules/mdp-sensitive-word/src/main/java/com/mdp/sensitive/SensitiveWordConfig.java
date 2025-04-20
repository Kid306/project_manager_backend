package com.mdp.sensitive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Order(value = 2147483647)
public class SensitiveWordConfig {

    @Value("${mdp.sensitive-word.words:秒杀}")
    String sensitiveWords="";

    @Bean
    SensitiveWordService sensitiveWordInit(){
        Set<String> sensitiveWordSet=new HashSet<>();
        SensitiveWordService xmSensitiveWordService=new SensitiveWordService();
        if(StringUtils.hasText(sensitiveWords)){
            String[] words=sensitiveWords.split(",");
            for (String word : words) {
                sensitiveWordSet.add(word);
            }
            xmSensitiveWordService.init(sensitiveWordSet);
        }else{
            xmSensitiveWordService.init(sensitiveWordSet);
        }
        return xmSensitiveWordService;
    }

}
