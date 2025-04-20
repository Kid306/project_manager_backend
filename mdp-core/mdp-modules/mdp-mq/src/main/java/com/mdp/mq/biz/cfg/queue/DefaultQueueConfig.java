package com.mdp.mq.biz.cfg.queue;


import com.mdp.mq.biz.queue.DefaultPop;
import com.mdp.mq.biz.queue.DefaultPush;
import com.mdp.mq.queue.Pop;
import com.mdp.mq.queue.Push;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultQueueConfig {

    @ConditionalOnMissingBean(Pop.class)
    @Bean
    Pop pop___(){
        return new DefaultPop();
    }
    @ConditionalOnMissingBean(Push.class)
    @Bean
    Push push___(){
        return new DefaultPush();
    }

}
