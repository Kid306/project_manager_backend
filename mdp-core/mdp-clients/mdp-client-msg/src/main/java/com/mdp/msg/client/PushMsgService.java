package com.mdp.msg.client;

import com.mdp.core.entity.Tips;
import com.mdp.micro.client.CallBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service("com.mdp.msg.client.PushMsgService")
public class PushMsgService {

	@Value("${mdp.im.client.open:true}")
	public boolean imClientOpen=true;


	@Value("${mdp.im.client.push-uri:/im/im/push/publishMessage}")
	public  String imClientPushUri="";
	
	@Autowired
    public CallBizService callBizServie;
	
	 public static ExecutorService pool;
	 
	 @PostConstruct
	public void initPool(){
		//maximumPoolSize设置为2 ，拒绝策略为AbortPolic策略，直接抛出异常
	        pool = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy() );
	        
	 }
	
	
	public void pushPrichatMsgToIm(String branchId,String sendUserid,String sendUsername,String toUserid,String toUsername,String msg) {
		if(this.imClientOpen==false){
			return;
		}
	 	Map<String,Object> map=new HashMap<>();
		map.put("toUserid", toUserid); 
		map.put("toUsername", toUsername); 
		map.put("sendUserid", sendUserid); 
		map.put("sendUsername", sendUsername);
		map.put("action", "newMessage");
		map.put("msgType", "prichat");
		map.put("branchId", branchId);
		map.put("sendContent", msg);
		map.put("store", "1");
		pool.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					Tips tips=callBizServie.postForTips(imClientPushUri, map);
				} catch (Exception e) {
				}
				
			}
		}); 
		
	}
	
	public void pushMsgToIm(Map<String,Object> message) {
		if(this.imClientOpen==false){
			return;
		}
		message.put("store", "1");
		//Tips tips=callBizServie.callApi("/im/im/push/publishMessage", message);

		pool.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					Tips tips=callBizServie.postForTips(imClientPushUri, message);
				} catch (Exception e) {
				}
				
			}
		}); 
		
	} 

	public void pushGroupMsg(String branchId,String groupId,String sendUserid,String sendUsername,String msg) {
		Map<String,Object> message=new HashMap<>();
 		message.put("groupId", groupId); 
		message.put("action", "newMessage"); 
		message.put("msgType", "group"); 
		message.put("branchId", branchId); 
		message.put("sendUserid", sendUserid);
 		message.put("sendUsername", sendUsername); 
 		message.put("sendContent", msg);  
 		message.put("store", "1"); 
 		this.pushMsgToIm(message);
	}
	public void pushLeaveChannelGroupMsg(String branchId,String groupId,List<Map<String,Object>> users) {
		Map<String,Object> map=new HashMap<>();
		map.put("groupId", groupId); 
		map.put("action", "leaveChannelGroup");   
		map.put("msgType", "group");  
		map.put("users",users); 
		map.put("branchId", branchId);  
		map.put("store", "1"); 
		this.pushMsgToIm(map);
	}
	public void pushJoinChannelGroupMsg(String branchId,String groupId,List<Map<String,Object>> users) {
		Map<String,Object> map=new HashMap<>();
		map.put("groupId", groupId); 
		map.put("action", "joinChannelGroup");   
		map.put("msgType", "group");  
		map.put("users",users);  
		map.put("store", "1"); 
		map.put("branchId", branchId); 
		this.pushMsgToIm(map);
	}
	public void pushChannelGroupRemoveMsg(String branchId,String groupId) {
		Map<String,Object> map=new HashMap<>();
		map.put("groupId", groupId); 
		map.put("action", "channelGroupRemove");  
		map.put("msgType", "group"); 
		map.put("branchId", branchId);  
		map.put("store", "1"); 
		this.pushMsgToIm(map);
	}
	public void pushChannelGroupCreateMsg(String branchId,String bizPid,String bizId,String groupId,String groupName,String cuserid,String cusername,List<Map<String,Object>> users,String msg) {
		Map<String,Object> map=new HashMap<>();
		map.put("groupId", groupId);
		map.put("groupName", groupName);  
		map.put("categoryId", "common"); 
		map.put("users", users); 
		map.put("cuserid", cuserid);
		map.put("cusername", cusername);  
		map.put("branchId", branchId); 
		map.put("bizPid", bizPid); 
		map.put("bizId", bizId); 
		map.put("action", "channelGroupCreate");  
		map.put("sendContent", msg); 
		map.put("msgType", "group");  
		map.put("store", "1"); 
		this.pushMsgToIm(map);
	}
	public void pushChannelGroupCreateMsg(String branchId,String categoryId,String bizPid,String bizId,String groupId,String groupName,String cuserid,String cusername,List<Map<String,Object>> users,String msg) {
		Map<String,Object> map=new HashMap<>();
		map.put("groupId", groupId);
		map.put("groupName", groupName); 
		map.put("categoryId", categoryId); 

		map.put("users", users); 
		map.put("cuserid", cuserid);
		map.put("cusername", cusername);  
		map.put("branchId", branchId); 
		map.put("bizPid", bizPid); 
		map.put("bizId", bizId); 

		map.put("sendContent", msg); 
		map.put("action", "channelGroupCreate");  
		map.put("msgType", "group");  
		map.put("store", "1"); 
		this.pushMsgToIm(map);
	}


}
