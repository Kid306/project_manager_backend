package com.mdp.tpa.client.cache;

import com.mdp.tpa.client.entity.UserTpa;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserTpaCacheService extends TpaRedisCacheService<UserTpa> {

    Map<String,String> keyMap=new HashMap<>();

    public int maxSize=20000;


    @Override
    public String getCacheKey() {
        return "mdp_safe_client_user_tpa";
    }

    public void putUserTpa(UserTpa userTpa){
        this.put(userTpa.getOpenid(),userTpa);
        keyMap.put(userTpa.getAuthId()+"_"+userTpa.getUserid(),userTpa.getOpenid());
        if(keyMap.size()>maxSize){
            this.clearSomeKey();
        }
    }
    public UserTpa getUserTpaByUserid(String authId,String userid){
        String openid=keyMap.get(authId+"_"+userid);
        if(StringUtils.hasText(openid)){
            return this.get(openid);
        }else{
            return null;
        }

    }
    public UserTpa getUserTpa(String openid){
        return this.get(openid);
    }


    public void clearSomeKey(){
        int i=0;
        for (String key : keyMap.keySet()) {
            i++;
            keyMap.remove(key);
            if(i>maxSize/3){
                break;
            }
        }
    }
}
