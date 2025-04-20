package com.mdp.mq.biz.cfg.sp;


import com.mdp.mq.biz.sp.DefaultMessageListener;
import com.mdp.mq.biz.sp.DefaultPublish;
import com.mdp.mq.biz.sp.DefaultSubscriber;
import com.mdp.mq.sp.ChannelConfig;
import com.mdp.mq.sp.Publish;
import com.mdp.mq.sp.Subscriber;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConditionalOnMissingBean(ChannelConfig.class)
@Configuration
public class DefaultChangeConfig {


    @ConditionalOnMissingBean(DefaultMessageListener.class)
    @Bean
    DefaultMessageListener defaultMessageListener___(List<Subscriber> subscribers){
        DefaultMessageListener defaultMessageListener=new DefaultMessageListener();
        defaultMessageListener.setSubscribers(subscribers);
        defaultMessageListener.watch();
        return defaultMessageListener;
    }

    @ConditionalOnMissingBean(Publish.class)
    @Bean
    Publish defaultPublish___(){
        return new DefaultPublish();
    }

    @ConditionalOnMissingBean(Subscriber.class)
    @Bean
    Subscriber DefaultSubscriber___(){
        return new DefaultSubscriber();
    }
}
