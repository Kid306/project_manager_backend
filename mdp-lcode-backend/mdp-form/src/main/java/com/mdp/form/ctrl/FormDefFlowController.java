package com.mdp.form.ctrl;

import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.form.service.FormDefFlowService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value="/*/form/data")
@Api(tags={"表单数据与流程关联表-操作接口"})
public class FormDefFlowController {
	
	static Logger logger =LoggerFactory.getLogger(FormDefFlowController.class);
	
	@Autowired
	private FormDefFlowService formDataFlowService;


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
	 *
	 * 其中 create/complete/assignment/delete事件是需要在模型中人工节点上配置了委托代理表达式 ${taskBizCallListener}才会推送过来。
	 * 在人工任务节点上配置 任务监听器  建议事件为 complete,其它assignment/create/complete/delete也可以，一般建议在complete,委托代理表达式 ${taskBizCallListener}
	 *
	 * @param flowVars {flowBranchId,agree,procInstId,assignee,actId,taskName,mainTitle,branchId,bizKey,commentMsg,eventName,modelKey} 等
	 * @return 如果tips.isOk==false，将影响流程提交
	 **/
	@RequestMapping(value="/processApprova",method=RequestMethod.POST)
	public Map<String,Object> processApprova( @RequestBody Map<String,Object> flowVars){
		try{
			this.formDataFlowService.processApprova(flowVars);
			logger.debug("procInstId====="+flowVars.get("procInstId"));
			return Result.ok();
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e.getMessage());
		}
	}
}
