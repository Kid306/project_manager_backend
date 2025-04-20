package com.mdp.mq.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Primary
@Service
public class PopServiceImpl implements Pop {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public <T> T rightPop(Object key) {
        return (T) redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public <T> T rightPop(Object key, Long millseconds) {
        return (T) redisTemplate.opsForList().rightPop(key,millseconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public Long size(Object key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public <T> List<T> range(Object key, long start, long end) {
        return redisTemplate.opsForList().range(key,start,end);
    }
}
