package com.mdp.mq.biz.queue;

import com.mdp.mq.queue.Pop;

import java.util.List;

public class DefaultPop implements Pop {


    @Override
    public <T> T rightPop(Object key, Long millseconds) {
        List<T> dataList= (List<T>) DefaultPush.cache.get(key);
        if(dataList==null||dataList.size()==0){
            return (T) null;
        }else{
            return dataList.get(dataList.size()-1);
        }
    }

    @Override
    public Long size(Object key) {
        List<Object> dataList= DefaultPush.cache.get(key);
        if(dataList==null ||dataList.size()==0){
            return 0L;
        }else{
            return (long) dataList.size();
        }
    }

    @Override
    public <T> List<T> range(Object key, long start, long end) {
        List<T> dataList= (List<T>) DefaultPush.cache.get(key);
        if(dataList==null ||dataList.size()==0){
            return null;
        }else{
            return dataList.subList(Math.toIntExact(start), Math.toIntExact(end));
        }
    }
}
