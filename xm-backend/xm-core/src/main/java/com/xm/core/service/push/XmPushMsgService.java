package com.xm.core.service.push;

import com.mdp.msg.client.PushMsgService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class XmPushMsgService extends PushMsgService {
	
	 
	
	/**
	 * 添加候选人时-发送消息给候选人，发消息给任务负责人，发消息给需求负责人
	 * 候选人变成执行人-发送消息给候选人，发消息给任务负责人，发消息给需求负责人
	 * 执行人提交测试，提交验收时发给 需求负责人，任务负责人，小组组长
	 * @param msg
	 */
	public void pushCssMsg(String branchId,String sendUserid,String sendUsername,String msg){
		String cssGroupId=branchId+"_css_"+sendUserid;
		super.pushGroupMsg(branchId, cssGroupId,sendUserid, sendUsername, msg);
 	}
	 
	public void pushCreateCssGroupMsg(String branchId,String cuserid,String cusername,String msg){ 
		String cssGroupId=branchId+"_css_"+cuserid;
		String cssGroupName=cusername+":"+msg;
		List<Map<String,Object>> users = new ArrayList<>();
		Map<String,Object> user= new HashMap<>();
		user.put("userid", cuserid); 
		user.put("username", cusername);
		users.add(user);
		super.pushChannelGroupCreateMsg(branchId, "css",cssGroupId,cssGroupId,cssGroupId, cssGroupName, cuserid, cusername, users,msg);
 	}
	 
}
