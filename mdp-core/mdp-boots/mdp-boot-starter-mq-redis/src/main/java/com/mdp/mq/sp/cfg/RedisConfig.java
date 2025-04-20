package com.mdp.mq.sp.cfg;

import com.mdp.mq.sp.ChannelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.List;

@Configuration
public class RedisConfig {


    @Autowired(required=false)
    List<ChannelConfig> configs;
	
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        if(configs==null) {
            return container;
        }
        for (ChannelConfig channelConfig : configs) {
            channelConfig.container(container);
        }
        //这个container 可以添加多个 messageListener
        return container;
    } 
 
}