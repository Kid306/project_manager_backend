package com.mdp.mq.biz.sp;

import com.mdp.mq.sp.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber implements Subscriber {

    Logger logger= LoggerFactory.getLogger(DefaultSubscriber.class);
    @Override
    public void receiveMessage(String channelName, Object message) {
        logger.debug("单机版订阅者模型收到消息：主题：{}，消息内容：{}",channelName,message);
    }
}
