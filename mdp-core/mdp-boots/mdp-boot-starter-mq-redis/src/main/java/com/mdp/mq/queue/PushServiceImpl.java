package com.mdp.mq.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Primary
@Service
public class PushServiceImpl implements Push {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Long leftPush(Object key, Object value) {
        return redisTemplate.opsForList().leftPush(key,value);
    }
}
