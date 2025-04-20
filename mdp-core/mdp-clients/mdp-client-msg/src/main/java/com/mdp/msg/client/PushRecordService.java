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

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 Map<String,Object> 表 XM.xm_record 当前主键(包括多主键): id; 
 ***/
@Service("com.mdp.msg.client.PushRecordService")
public class PushRecordService {
	
	/** 请在此类添加自定义函数 */
	@Autowired
	Push push;


	SequenceService sequenceService=new SequenceService();
	
	public static String queueName="mdp-record";

	/**
	 *
	 * @param opUserid
	 * @param opUsername
	 * @param objType 对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6
	 * @param pbizId 对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号
	 * @param bizId
	 * @param remarks
	 * @return
	 */
	public Map<String,Object> pushRecord(String branchId,String opUserid,String opUsername,String objType,String pbizId,String bizId,String remarks){
		return this.pushRecord(branchId,opUserid,opUsername,objType,pbizId,bizId,remarks,MDC.get("gloNo"),RequestUtils.getIpAddr(RequestUtils.getRequest()),"","");
	}
	/**
	 *
	 * @param opUser
	 * @param objType 对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6
	 * @param pbizId 对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号
	 * @param bizId
	 * @param remarks
	 * @return
	 */
	public Map<String,Object> pushRecord(User opUser, String objType, String pbizId, String bizId, String remarks){
		return this.pushRecord(opUser.getBranchId(),opUser.getUserid(),opUser.getUsername(),objType,pbizId,bizId,remarks,MDC.get("gloNo"),RequestUtils.getIpAddr(RequestUtils.getRequest()),"","");
	}
	/**
	 *
	 * @param opUser
	 * @param objType 对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6
	 * @param pbizId 对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号
	 * @param bizId
	 * @param remarks
	 * @param oldValue
	 * @param newValue
	 * @return
	 */
	public Map<String,Object> pushRecord(User opUser,String objType,String pbizId,String bizId,String remarks,String oldValue,String newValue){
		return this.pushRecord(opUser.getBranchId(),opUser.getUserid(),opUser.getUsername(),objType,pbizId,bizId,remarks,MDC.get("gloNo"),RequestUtils.getIpAddr(RequestUtils.getRequest()),oldValue,newValue);
	}
	/**
	 *
	 * @param opUserid
	 * @param opUsername
	 * @param objType 对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6
	 * @param pbizId 对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号
	 * @param bizId
	 * @param remarks
	 * @param glodNo
	 * @param ip
	 * @param oldValue
	 * @param newValue
	 * @return
	 */
	public Map<String,Object> pushRecord(String branchId,String opUserid,String opUsername,String objType,String pbizId,String bizId,String remarks,String glodNo,String ip,String oldValue,String newValue){
		Map<String,Object> record=new HashMap<>();
		record.put("branchId",branchId);
		record.put("id",sequenceService.getTablePK("sys_record","id"));
		record.put("opUserid",opUserid);
		record.put("opUsername",opUsername);
		record.put("objType",objType);
		record.put("pbizId",pbizId);
		record.put("bizId",bizId);
		record.put("remarks",remarks);
		record.put("gloNo",glodNo);
		record.put("ip",ip);
		record.put("newValue",newValue);
		record.put("oldValue",oldValue);
		this.push.leftPush(queueName,record);
		return record;
	}
}

