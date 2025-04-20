package com.mdp.core.service;

import com.mdp.core.api.ContextEnvService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DefaultQueryEnvService implements ContextEnvService {
    String platformBranchId="platform-brancch-001";
    String demoUserid="demo-branch-01";
    private static ThreadLocal<Map<String,Object>> local=new ThreadLocal<>();

    private Map<String,Object> env=null;
    static Map<String,Object> demoUser=new HashMap<>();
    static {

        demoUser.put("userid","demoUserid");
        demoUser.put("username","演示机构用户账户");
        demoUser.put("deptid","demo-branch-01");
        demoUser.put("branchId","demo-branch-01");
    }


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
        return demoUser;
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
