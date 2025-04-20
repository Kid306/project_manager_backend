package com.mdp.tpa.client.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.micro.client.CallBizService;
import com.mdp.tpa.client.cache.UserTpaCacheService;
import com.mdp.tpa.client.entity.UserTpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 ***/
@Service("mdp.tpa.client.UserTpaService" )
public class UserTpaService  {

    @Value(value = "${mdp.tpa.user-tpa-query-uri:http://lcode/sys/userTpa/list}")
    String userTpaQueryUri;

    @Autowired
    UserTpaCacheService userTpaCacheService;

    @Autowired
    CallBizService callBizService;

    public UserTpa getUserTpaByOpenid(String openid){
        UserTpa userTpa = userTpaCacheService.getUserTpa(openid);
        if (userTpa != null) {
            return userTpa;
        }else{
            userTpa= getUerTpaByOpenidFromRemote(openid);
            if(userTpa!=null){
                userTpaCacheService.putUserTpa(userTpa);
            }
            return userTpa;
        }
    }
    public UserTpa getUserTpaByAuthIdAndUserid(String authId,String userid){
        UserTpa userTpa = userTpaCacheService.getUserTpaByUserid(authId,userid);
        if (userTpa != null) {
            return userTpa;
        }else{
            userTpa= getUserTpaByAuthIdAndUseridFromRemote(authId,userid);
            if(userTpa!=null){
                userTpaCacheService.putUserTpa(userTpa);
            }
            return userTpa;
        }
    }
    public UserTpa getUerTpaByOpenidFromRemote(String openid){
        String uri=userTpaQueryUri+"?openid={openid}";
        Map<String,Object> params=new HashMap<>();
        params.put("openid",openid);
        Map<String,Object> result=callBizService.getForMap(uri,params);
        List<Map<String,Object>> userTpas= (List<Map<String,Object>>) result.get("data");
        if(userTpas==null || userTpas.size()==0){
            return null;
        }else {
            UserTpa userTpa= BaseUtils.fromMap(userTpas.get(0),UserTpa.class);
            return userTpa;
        }

    }

    public UserTpa getUserTpaByAuthIdAndUseridFromRemote(String authId,String userid){
        String uri=userTpaQueryUri+"?authId={authId}&userid={userid}";
        Map<String,Object> params=new HashMap<>();
        params.put("authId",authId);
        params.put("userid",userid);
        Map<String,Object> result=callBizService.getForMap(uri,params);
        List<Map<String,Object>> userTpas= (List<Map<String,Object>>) result.get("data");
        if(userTpas==null || userTpas.size()==0){
            return null;
        }else {
            UserTpa userTpa= BaseUtils.fromMap(userTpas.get(0),UserTpa.class);
            return userTpa;
        }
    }

    public List<UserTpa> getUserTpasByUseridFromRemote(String userid){
        String uri=userTpaQueryUri+"?userid={userid}";
        Map<String,Object> params=new HashMap<>();
        params.put("userid",userid);
        Map<String,Object> result=callBizService.getForMap(uri,params);
        List<Map<String,Object>> userTpas= (List<Map<String,Object>>) result.get("data");
        if(userTpas==null || userTpas.size()==0){
            return null;
        }else {
            List<UserTpa> userTpaList=userTpas.stream().map(i->BaseUtils.fromMap(i,UserTpa.class)).collect(Collectors.toList());
             return userTpaList;
        }
    }
    public List<UserTpa> getUserTpasByUseridAndBizTypeFromRemote(String userid,String bizType){
        String uri=userTpaQueryUri+"?userid={userid}&bizType={bizType}";
        Map<String,Object> params=new HashMap<>();
        params.put("userid",userid);
        params.put("bizType",bizType);
        Map<String,Object> result=callBizService.getForMap(uri,params);
        List<Map<String,Object>> userTpas= (List<Map<String,Object>>) result.get("data");
        if(userTpas==null || userTpas.size()==0){
            return null;
        }else {
            List<UserTpa> userTpaList=userTpas.stream().map(i->BaseUtils.fromMap(i,UserTpa.class)).collect(Collectors.toList());
            return userTpaList;
        }
    }
}

