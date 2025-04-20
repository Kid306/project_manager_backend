package com.mdp.mq.sp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Primary
@Service
public class PublishServiceImpl implements Publish{

    @Autowired
    RedisTemplate redisTemplate;

    public void  push(String subject,Object  message){
        redisTemplate.convertAndSend(subject, message);
    }
}
