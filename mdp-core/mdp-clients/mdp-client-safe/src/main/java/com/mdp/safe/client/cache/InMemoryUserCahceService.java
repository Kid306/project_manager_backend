package com.mdp.safe.client.cache;

import com.mdp.safe.client.entity.Qx;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 角色，权限相关数据，高频访问，为了减少对redis/对数据的压力，此类数据存储于内存。
 * 每2小时清除一次
 */
@Service
public class InMemoryUserCahceService {
    public  Map<String,Map<String, Qx>> userQxs =new ConcurrentHashMap<>();

    public  void putUserQxs(String userid, Map<String, Qx> qxs){
        userQxs.put(userid,qxs);
    }

    public Map<String, Qx> getUserQxs(String userid){
       return userQxs.get(userid);
    }

    @Scheduled(cron = "0 0 */2 * * ?")
    public void clear(){
        userQxs.clear();
    }
}