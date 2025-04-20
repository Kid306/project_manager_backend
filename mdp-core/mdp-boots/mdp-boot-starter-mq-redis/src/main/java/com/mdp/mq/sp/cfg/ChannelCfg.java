package com.mdp.mq.sp.cfg;

import com.mdp.mq.enums.SpTopic;
import com.mdp.mq.sp.ChannelConfig;
import com.mdp.mq.sp.SpMessageListeener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Service;

@Service
public class ChannelCfg implements ChannelConfig {

    @Autowired
    SpMessageListeener messageListeener;
    String defaultTopic= SpTopic.defTopic.name();

    @Override
    public void container(Object container) {
        RedisMessageListenerContainer c= (RedisMessageListenerContainer) container;
        c.addMessageListener(messageListeener,topic(defaultTopic));
    }
    Topic topic(String channelName){
        ChannelTopic topic=new ChannelTopic(channelName);
        return topic;
    }
}
