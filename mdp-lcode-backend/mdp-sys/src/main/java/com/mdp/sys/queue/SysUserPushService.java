package com.mdp.sys.queue;

import com.mdp.mq.queue.Push;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserPushService {

    @Autowired
    Push push;

    public void pushUserInfoAfterChange(Map<String,Object> userInfo){
        push.leftPush("adm-user-update",userInfo);
    }

    public void pushUserInfoAfterAdd(Map<String,Object> userInfo){
        push.leftPush("adm-user-add",userInfo);
    }

}
