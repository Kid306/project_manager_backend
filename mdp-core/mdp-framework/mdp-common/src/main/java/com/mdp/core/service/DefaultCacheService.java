package com.mdp.core.service;

import com.mdp.core.api.CacheService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class DefaultCacheService<T> implements CacheService<T> {

    protected Map<String,Map<String,T>> cache=new HashMap<>();
    Map<String,Timer> taskMap=new HashMap<>();
    protected Map<String,T> valueMap=new HashMap<>();
    Map<String,Timer> taskValueMap=new HashMap<>();


    @PostConstruct
    private void initSpace(){
        Map<String,T> space=cache.get(getSpaceKey());
        cache.put(getSpaceKey(),space);
    }

    @Override
    public void put( String hkey, T value) {
        Map<String,T> space=cache.get(getSpaceKey());
        space.put(hkey,value);
    }

    @Override
    public T get( String hkey) {
        Map<String,T> space=cache.get(getSpaceKey());
        return (T) space.get(hkey);
    }

    @Override
    public void remove( String hkey) {
        Map<String,T> space=cache.get(getSpaceKey());
        space.remove(hkey);
    }

    @Override
    public boolean containsKey( String hkey) {
        Map<String,T> space=cache.get(getSpaceKey());
        return space.containsKey(hkey);
    }

    @Override
    public void refresh() {
        Map<String,T> space=cache.get(getSpaceKey());
        space.clear();
    }

    @Override
    public boolean containsValue( T value) {
        Map<String,T> space=cache.get(getSpaceKey());
        return space.containsValue(value);
    }

    @Override
    public boolean expire( long milliseconds) {
        return expire(milliseconds,TimeUnit.MILLISECONDS);
    }


    @Override
    public boolean expire( long time, TimeUnit timeUnit) {
        if(taskMap.containsKey(getSpaceKey())){
            Timer timer=taskMap.get(getSpaceKey());
            timer.cancel();
        }
        TimerTask task = new TimerTask() { 
            @Override
            public void run() {
                Map<String,T> space=cache.get(getSpaceKey());
                space.clear();
            }
        };
        Timer timer = new Timer();
        // 第一个参数是 TimerTask，第二个参数是延迟多少毫秒开始执行，第三个参数是定时任务的执行间隔（以毫秒为单位）
        timer.scheduleAtFixedRate(task, 100, timeUnit.toMillis(time)); // 每秒执行一次
        return true;
    }

    @Override
    public void setValue(String key, T value) {
        valueMap.put(key, value);
    }

    @Override
    public void setValue(String key, T value, long time, TimeUnit timeUnit) {
        valueMap.put(key, value);
        if(taskValueMap.containsKey(getSpaceKey())){
            Timer timer=taskValueMap.get(getSpaceKey());
            timer.cancel();
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                valueMap.remove(key);
            }
        };
        Timer timer = new Timer();
        // 第一个参数是 TimerTask，第二个参数是延迟多少毫秒开始执行，第三个参数是定时任务的执行间隔（以毫秒为单位）
        timer.scheduleAtFixedRate(task, 1, timeUnit.toMillis(time)); // 每秒执行一次
     }

    @Override
    public boolean setIfAbsent(String key, T value, long time, TimeUnit timeUnit) {
        T  val= (T) valueMap.get(key);
        if(val!=null){
            return true;
        }else {
            this.setValue(key,value,time,timeUnit);
            return false;
        }
    }
}
