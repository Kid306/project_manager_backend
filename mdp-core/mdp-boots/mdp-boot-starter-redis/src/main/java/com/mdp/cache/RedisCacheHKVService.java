package com.mdp.cache;

import cn.hutool.core.lang.Assert;
import com.mdp.core.api.CacheHKVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@Primary
@Service
public class RedisCacheHKVService<V> implements CacheHKVService<V> {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void put(String spaceKey, String hkey,V value) {
        redisTemplate.opsForHash().put(spaceKey,hkey,value);
    }

    @Override
    public long delete(String spaceKey, Object ... hkeys) {
       return redisTemplate.opsForHash().delete(spaceKey,hkeys);
    }

    @Override
    public V get(String spaceKey, String hkey) {
        return (V) redisTemplate.opsForHash().get(spaceKey,hkey);
    }

    @Override
    public void remove(String spaceKey, String hkey) {
        redisTemplate.opsForHash().delete(spaceKey,hkey);
    }

    @Override
    public boolean containsKey(String spaceKey, String hkey) {
        return redisTemplate.opsForHash().hasKey(spaceKey,hkey);
    }

    @Override
    public void refresh(String h) {
        redisTemplate.opsForHash().entries(h).clear();
        //redisTemplate.opsForHash().put();
    }

    @Override
    public List<V> getValus(String spaceKey) {
        return redisTemplate.opsForHash().values(spaceKey);
    }

    @Override
    public Set<String> keys(String spaceKey) {
        return redisTemplate.opsForHash().keys(spaceKey);
    }

    @Override
    public boolean containsValue(String spaceKey,V value) {
        return redisTemplate.opsForHash().values(value)==null?false:true;
    }

    @Override
    public boolean expire(String spaceKey, long milliseconds) {
        return redisTemplate.expire(spaceKey,milliseconds,TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean expire(String spaceKey, long time, TimeUnit timeUnit) {
        return redisTemplate.expire(spaceKey,time,timeUnit);
    }

    @Override
    public void put(String hkey,V value) {
        redisTemplate.opsForValue().set(hkey,value);
    }

    @Override
    public V get(String hkey) {
        return (V) redisTemplate.opsForValue().get(hkey);
    }


    @Override
    public boolean containsKey(String hkey) {
        return redisTemplate.opsForValue().get(hkey)==null?false:true;
    }

    @Override
    public void setValue(String key,V value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void setValue(String key,V value, long time, TimeUnit timeUnit) {

        redisTemplate.opsForValue().set(key,value,time,timeUnit);
    }

    @Override
    public V getValue(String key) {
        return (V) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void remove(String hkey) {
        redisTemplate.opsForValue().set(hkey,null);
    }

    @Override
    public boolean containsValue(V value) {
        Assert.isFalse(true);
        return false;
    }
}
