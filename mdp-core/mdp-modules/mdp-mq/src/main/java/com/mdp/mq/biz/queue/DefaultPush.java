package com.mdp.mq.biz.queue;

import com.mdp.mq.queue.Push;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultPush implements Push {
    public static Map<Object, List<Object>> cache=new ConcurrentHashMap<>(1000);

    @Override
    public Long leftPush(Object key, Object value) {
        List<Object> list=cache.get(key);
        if(list==null){
            list=new ArrayList<>();
            list.add(value);
            cache.put(key,list);
        }else{
            list.add(0,value);
            cache.put(key,list);
        }
        return (long) list.size();
    }
}
