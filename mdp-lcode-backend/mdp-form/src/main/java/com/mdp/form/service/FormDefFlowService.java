package com.mdp.form.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.form.FormUtil;
import com.mdp.form.entity.FormDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 form 小模块 <br>
 * 实体 FormDataProcessApprova 表 form_data_process_approva 当前主键(包括多主键): id; 
 ***/
@Service
public class FormDefFlowService {
	static Logger logger =LoggerFactory.getLogger(FormDefFlowService.class);


	@Autowired
	FormDefService formDefService;



	/**
	 * 流程审批过程中回调该接口，更新业务数据
	 * 如果发起流程时上送了restUrl，则无论流程中是否配置了监听器都会在流程发生以下事件时推送数据过来
	 * eventName: PROCESS_STARTED 流程启动完成 全局
	 *            PROCESS_COMPLETED 流程正常结束 全局
	 *            PROCESS_CANCELLED 流程删除 全局
	 *            create 人工任务启动
	 *            complete 人工任务完成
	 *            assignment 人工任务分配给了具体的人
	 *            delete 人工任务被删除
	 *            TASK_COMPLETED_FORM_DATA_UPDATE 人工任务提交完成后，低代码数据更新
	 *
	 * 其中 create/complete/assignment/delete事件是需要在模型中人工节点上配置了委托代理表达式 ${taskBizCallListener}才会推送过来。
	 * 在人工任务节点上配置 任务监听器  建议事件为 complete,其它assignment/create/complete/delete也可以，一般建议在complete,委托代理表达式 ${taskBizCallListener}
	 *
	 * @param flowVars {flowBranchId,agree,procInstId,startUserid,assignee,actId,taskName,mainTitle,branchId,bizKey,commentMsg,eventName,modelKey} 等
	 * @return 如果tips.isOk==false，将影响流程提交
	 **/
	public void processApprova(Map<String, Object> flowVars) {
		String procInstId=(String) flowVars.get("procInstId");
		String eventName=(String) flowVars.get("eventName");
		String formId=(String) flowVars.get("formId");
		String agree=(String) flowVars.get("agree");
		if(ObjectTools.isEmpty(formId)){
			throw new BizException("formId-0","表单编号不能为空");
		}
		FormDef formDef=formDefService.getFormCacheFirst(formId);
		if(formDef==null){
			throw new BizException("form-def-0","表单已不存在");
		}
		String assignee=(String) flowVars.get("assignee");
		if(FormUtil.TASK_COMPLETED_FORM_DATA_UPDATE.equals(eventName) || FormUtil.TASK_COMPLETED.equals(eventName)) {
			FormDef fd=new FormDef();
			fd.setId(formDef.getId());
 			fd.setFlowTime(new Date());
			 fd.setFlowUserid(assignee);
			formDefService.updateById(fd,true);
		}else {

			if("PROCESS_STARTED".equals(eventName)) {
				Object formDataObj=flowVars.get("formData");
				if(formDataObj==null){
					throw new BizException("form-data-0","表单数据不能为空,参数名为formData");
				}
				List<Map<String,Object>> formDataList=new ArrayList<>();
				if(formDataObj instanceof List){
					formDataList= (List<Map<String, Object>>) formDataObj;
				}else {
					formDataList.add((Map<String, Object>) formDataObj);
				}
 				if(formDataList!=null && formDataList.size()>0) {//实际只有一条数据
					for (Map<String, Object> formData : formDataList) {
						FormDef fd=new FormDef();
						fd.setId(formDef.getId());
						fd.setFlowStartUserid((String) flowVars.get("startUserid"));
						fd.setFlowTime(new Date());
						fd.setFlowStartTime(new Date());
						formDefService.updateById(fd,true);
					}

				}
			}else if("PROCESS_COMPLETED".equals(eventName)) {
				if("1".equals(agree)) {
					this.updateFlowStateByProcInst("2",assignee , procInstId);
				}else {
					this.updateFlowStateByProcInst("3",assignee , procInstId);
				}

			}else if("PROCESS_CANCELLED".equals(eventName)) {
				this.updateFlowStateByProcInst("4",assignee , procInstId);
			}
		}
	}

	public void updateFlowStateByProcInst(String flowState,String assignee, String procInstId){
		UpdateWrapper<FormDef> uw=QueryTools.initUpdateWrapper(FormDef.class);

		uw.set("flow_state",flowState)
		.set("flow_time",new Date())
		.eq("proc_inst_id",procInstId);
		if(!ObjectTools.isEmpty(assignee)){
			uw.set("flow_userid",assignee);
		}
		formDefService.update(uw);
	}
}

