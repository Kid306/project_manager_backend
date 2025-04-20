package com.xm.core.service.push;

import com.mdp.msg.client.PushMsgService;
import com.xm.core.service.XmMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmMenuPushMsgService extends PushMsgService {
	
 
	
	@Autowired
	XmMenuService xmMenuService;
	
	
	
	/**
	 * 发消息给需求相关人员
	 * 产品经理/客户/任务执行人
	 * @param branchId
	 * @param menuId
	 * @param sendUserid
	 * @param sendUsername
	 * @param msg
	 */
	@Async
	public void pushMenuRelUsersMsg(String branchId,String menuId,String sendUserid,String sendUsername,String msg) {
		List<Map<String,Object>> users=xmMenuService.queryTaskUsersByMenuId(menuId);
		Map<String,Map<String,Object>> mapAll=new HashMap<>();
		for (Map<String, Object> map : users) {
			String userid=(String) map.get("userid");
			String username=(String) map.get("username");
			String executorUserid=(String) map.get("executorUserid");
			String executorUsername=(String) map.get("executorUsername");
			if(!StringUtils.isEmpty(userid) && !mapAll.containsKey(userid)) {
				Map<String,Object> u=new HashMap<>();
				u.put("userid", userid);
				u.put("username", username); 
				mapAll.put(userid, u);
			}
			if( !StringUtils.isEmpty(executorUserid) && !mapAll.containsKey(executorUserid)) {
				Map<String,Object> u=new HashMap<>();
				u.put("userid", executorUserid);
				u.put("username", executorUsername); 
				mapAll.put(executorUserid, u);
			} 
		}
		if(!mapAll.isEmpty()) {
			for (Map<String, Object> user : mapAll.values()) {
				String userid=(String) user.get("userid");
				String username=(String) user.get("username");
				this.pushPrichatMsgToIm(branchId, sendUserid,sendUsername,userid,username, msg);
			}
		}
	}
	 
}
