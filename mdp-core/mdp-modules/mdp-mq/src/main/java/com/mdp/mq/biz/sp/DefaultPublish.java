package com.mdp.mq.biz.sp;

import com.mdp.mq.sp.Publish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultPublish implements Publish {

    public static Map<String,List<Object>> cache=new ConcurrentHashMap<>(1000);

    @Override
    public void push(String subject, Object message) {
        List<Object> list=cache.get(subject);
        if(list==null){
            list=new ArrayList<>();
            list.add(message);
            cache.put(subject,list);
        }else{
            list.add(message);
        }
    }
}
