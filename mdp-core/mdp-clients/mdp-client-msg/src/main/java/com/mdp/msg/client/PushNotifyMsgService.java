package com.mdp.msg.client;

import com.mdp.core.service.SequenceService;
import com.mdp.core.utils.RequestUtils;
import com.mdp.mq.queue.Push;
import com.mdp.safe.client.entity.User;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("com.mdp.msg.client.PushNotifyMsgService")
public class PushNotifyMsgService {

    public static String queueName="mdp-notify-msg";

    SequenceService sequenceService=new SequenceService();

    String tplType="taskNotify";

    @Autowired
    Push push;

    /**
     * 发给个人的消息
     * @param sendBranchId 发送机构号
     * @param sendUserid 发送用户 编号
     * @param sendUsername 发送用户名称
     * @param toUserid 接收用户编号
     * @param toUsername 接收用户名称
     * @param msg 消息内容
     * @param url 跳转链接
     */
    public Map<String,Object> pushMsg(String sendBranchId,String sendUserid,String sendUsername,String toUserid,String toUsername,String msg,String url){
        Map<String,Object> msgMap=new HashMap<>();
        msgMap.put("id",sequenceService.getTablePK("notify_msg","id"));
        msgMap.put("branchId",sendBranchId);
        msgMap.put("sendUserid",sendUserid);
        msgMap.put("sendUsername",sendUsername);
        msgMap.put("toUserid",toUserid);
        msgMap.put("toUsername",toUsername);
        msgMap.put("msg",msg);
        msgMap.put("url",url);
        msgMap.put("ip", RequestUtils.getIpAddr(RequestUtils.getRequest()));
        msgMap.put("gloNo", MDC.get("gloNo"));
        msgMap.put("tplType",tplType);
        push.leftPush(queueName,msgMap);
        return msgMap;
    }

    /**
     * 发给个人的消息
     * @param sendUser 发送者信息
     * @param toUserid 接收人编号
     * @param toUsername 接收人名称
     * @param msg 消息
     * @param url 跳转链接
     */
    public Map<String,Object> pushMsg(User sendUser,String toUserid,String toUsername,String msg,String url){
        Map<String,Object> msgMap=new HashMap<>();
        msgMap.put("id",sequenceService.getTablePK("notify_msg","id"));
        msgMap.put("branchId",sendUser.getBranchId());
        msgMap.put("sendUserid",sendUser.getUserid());
        msgMap.put("sendUsername",sendUser.getUsername());
        msgMap.put("toUserid",toUserid);
        msgMap.put("toUsername",toUsername);
        msgMap.put("msg",msg);
        msgMap.put("url",url);
        msgMap.put("ip", RequestUtils.getIpAddr(RequestUtils.getRequest()));
        msgMap.put("gloNo", MDC.get("gloNo"));
        msgMap.put("tplType",tplType);
        push.leftPush(queueName,msgMap);
        return msgMap;
    }

    /**
     * 发给个人的消息
     * @param sendUserid 发送者编号
     * @param toUserid 接收人编号
     * @param msg 消息
     * @param url 跳转链接
     */
    public Map<String,Object> pushMsg(String sendUserid,String toUserid,String msg,String url){
        Map<String,Object> msgMap=new HashMap<>();
        msgMap.put("id",sequenceService.getTablePK("notify_msg","id"));
        msgMap.put("sendUserid",sendUserid);
        msgMap.put("toUserid",toUserid);
        msgMap.put("msg",msg);
        msgMap.put("url",url);
        msgMap.put("ip", RequestUtils.getIpAddr(RequestUtils.getRequest()));
        msgMap.put("gloNo", MDC.get("gloNo"));
        msgMap.put("tplType",tplType);
        push.leftPush(queueName,msgMap);
        return msgMap;
    }
    /**
     * 发给个人的消息
     * @param msg 消息
     */
    public Map<String,Object> pushMsg(Map<String,Object> msg){
        Map<String,Object> msgMap=msg;
        if(!msgMap.containsKey("id")){
            msgMap.put("id",sequenceService.getTablePK("notify_msg","id"));
        }
        if(!msgMap.containsKey("gloNo")){
            msgMap.put("gloNo",MDC.get("gloNo"));
        }
        if(!msgMap.containsKey("ip")){
            msgMap.put("ip", RequestUtils.getIpAddr(RequestUtils.getRequest()));
        }
        push.leftPush(queueName,msgMap);
        return msgMap;
    }
}
