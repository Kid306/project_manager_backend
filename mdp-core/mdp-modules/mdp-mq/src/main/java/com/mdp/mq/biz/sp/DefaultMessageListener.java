package com.mdp.mq.biz.sp;

import com.mdp.mq.biz.queue.DefaultPush;
import com.mdp.mq.sp.Subscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class DefaultMessageListener {

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    List<Subscriber> subscribers;

    @Autowired
    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }


    /**
     * 模拟发布订阅模式，构建单机版的sp模型，适用于单机版的定阅模型
     */
    public void watch(){
        if(subscribers==null||subscribers.size()==0){
            return;
        }
        executorService.submit(()->{
            while (true){

                for (Map.Entry<Object, List<Object>> kv : DefaultPush.cache.entrySet()) {
                    if(kv.getValue().size()>0){
                        List<Object> values=kv.getValue();
                        for (Object value : values) {
                            for (Subscriber subscriber : subscribers) {
                                subscriber.receiveMessage(kv.getKey().toString(),value);
                            }
                        }
                        values.clear();
                    }

                }
                try {
                    Thread.sleep(100000);
                    log.debug("消息监听中。。。。。");
                } catch (InterruptedException e) {
                    log.debug("消息监听异常",e);
                }
            }
        });

    }

}
