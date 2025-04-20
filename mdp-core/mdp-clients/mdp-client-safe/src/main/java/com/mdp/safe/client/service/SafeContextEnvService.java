package com.mdp.safe.client.service;

import com.mdp.core.api.ContextEnvService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SafeContextEnvService implements ContextEnvService {
    @Value("${mdp.platformBranchId:platform-branch-001}")
    String platformBranchId;
    @Value("${mdp.demo.userid:demo-branch-01}")
    String demoUserid;
    private static ThreadLocal<User> local=new ThreadLocal<>();
    private Map<String,Object> env=null;

    @Override
    public Map<String, Object> getEnv() {

        if(env==null || env.isEmpty()){
            env=new HashMap<>();
            env.put("platformBranchId",platformBranchId);
            env.put("date",new Date());
            env.put("demoUserid",demoUserid);
        }
        return env;
    }

    @Override
    public Map<String, Object> getUser() {
        User user=local.get();
        if(user==null){
            user=LoginUtils.getCurrentUserInfo();
        }
        local.set(user);
        return user;

    }

    @Override
    public Map<String, Object> getAll() {
        Map<String,Object> vars=new HashMap<>();
        vars.put("env",this.getEnv());
        vars.put("user",this.getUser());
        return vars;
    }

    @Override
    public void clearThreadLock() {
        local.remove();
    }
}
