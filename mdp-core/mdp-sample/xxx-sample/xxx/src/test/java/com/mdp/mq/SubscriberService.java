package com.mdp.mq;

import com.alibaba.fastjson.JSON;
import com.mdp.mq.sp.Subscriber;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService implements Subscriber {

    @Override
    public void receiveMessage(String channelName, Object message) {
        System.out.println("channelName----->"+channelName);

        System.out.println("message----->"+ JSON.toJSONString(message));
    }
}
