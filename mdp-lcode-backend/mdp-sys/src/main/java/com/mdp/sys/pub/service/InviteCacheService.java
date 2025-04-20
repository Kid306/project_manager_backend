package com.mdp.sys.pub.service;

import com.mdp.core.api.CacheHKVService;
import com.mdp.core.api.Sequence;
import com.mdp.core.service.SequenceService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.sys.entity.UserTpaInvite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用于传递给微信/微信回传的state的相关操作
 *
 */
@Service("mdp.sys.pub.InviteCacheService")
public class InviteCacheService{

    @Autowired
    CacheHKVService cacheHKVService;

    Sequence sequence=new SequenceService();

    public String createInviteId(String prefix){
       String seq= prefix+sequence.getCommonNo("{date62:yyyyMMddHHmmssS}{rands:8}");
       return seq;
    }
    public void setInvite(String inviteId,UserTpaInvite invite){

        cacheHKVService.put(getCacheKey(),inviteId,BaseUtils.toMap(invite));
    }

    public boolean valiInviteId(String inviteId){
        return cacheHKVService.containsKey(getCacheKey(),inviteId);
    }

    public void clearInviteId(String inviteId){
        cacheHKVService.remove(getCacheKey(),inviteId);
    }
    public UserTpaInvite getInvite(String inviteId){
        Map<String,Object> map= (Map<String, Object>) cacheHKVService.get(getCacheKey(),inviteId);
        if(map==null){
            return null;
        }else{
            return BaseUtils.fromMap(map,UserTpaInvite.class);
        }

    }

     public String getCacheKey() {
        return "mdp-invite";
    }
}
