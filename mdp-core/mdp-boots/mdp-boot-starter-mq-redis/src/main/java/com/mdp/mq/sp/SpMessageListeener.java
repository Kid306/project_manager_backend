package com.mdp.mq.sp;

import com.mdp.mq.enums.SpTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SpMessageListeener implements MessageListener {

    RedisSerializer<String> stringSerializer=RedisSerializer.string();

    @Autowired(required=false)
    List<Subscriber> subscribers = Collections.emptyList();

    @Autowired
    StringRedisTemplate redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SpMessageListeener.class);

    /*定时心跳*/
    @Scheduled(cron = "0/30 * * * * *")
    public void timer() {
        redisTemplate.convertAndSend(SpTopic.defTopic.name(), "");
        redisTemplate.opsForValue().set("headbea", "1");
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String msg=message.toString();
            logger.debug("消息下行开始----》"+msg);
            //logger.debug(msg);
            //心跳包
            if("".equals(msg)||"\"\"".equals(msg)) {
                return;
            }
            String channelName=stringSerializer.deserialize(pattern);
            if(subscribers==null){
                return;
            }else{
                for (Subscriber subscriber : subscribers) {
                    subscriber.receiveMessage(channelName,message);
                }
            }
        } catch (Exception e) {
            logger.error("",e);
        }finally {
            logger.debug("消息下行结束!!!!!!!!");
        }

    }
}
