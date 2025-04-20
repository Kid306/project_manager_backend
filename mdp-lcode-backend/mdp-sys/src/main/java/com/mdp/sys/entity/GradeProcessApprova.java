package com.mdp.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 组织 com  顶级模块 mk 大模块 mem  小模块 <br> 
 * 实体 GradeProcessApprova所有属性名: <br>
 *	branchId,procInstId,agree,assignee,mainTitle,actId,taskName,commentMsg,id,eventName,bizKey,modelKey,flowLastTime,flowBranchId,flowState,startUserid,procDefId,modelName,flowEndTime,assigneeName,shopId,gradeId;<br>
 * 表 MK.mem_grade_process_approva 新增等级审核流程的所有字段名: <br>
 *	branch_id,proc_inst_id,agree,assignee,main_title,act_id,task_name,comment_msg,id,event_name,biz_key,model_key,flow_last_time,flow_branch_id,flow_state,start_userid,proc_def_id,model_name,flow_end_time,assignee_name,shop_id,grade_id;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@ApiModel(description="新增等级审核流程")
public class GradeProcessApprova  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
  	
	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String procInstId;
	
	@ApiModelProperty(notes="审批状态同意1不同意0",allowEmptyValue=true,example="",allowableValues="")
	String agree;
	
	@ApiModelProperty(notes="审批人",allowEmptyValue=true,example="",allowableValues="")
	String assignee;
	
	@ApiModelProperty(notes="流程标题",allowEmptyValue=true,example="",allowableValues="")
	String mainTitle;
	
	@ApiModelProperty(notes="审批节点编号",allowEmptyValue=true,example="",allowableValues="")
	String actId;
	
	@ApiModelProperty(notes="审批环节",allowEmptyValue=true,example="",allowableValues="")
	String taskName;
	
	@ApiModelProperty(notes="审批意见",allowEmptyValue=true,example="",allowableValues="")
	String commentMsg;
	
	@ApiModelProperty(notes="事件类型create/assignment/complete/delete/PROCESS_CREATED/PROCESS_COMPLETE/PROCESS_CANCELLED",allowEmptyValue=true,example="",allowableValues="")
	String eventName;
	
	@ApiModelProperty(notes="业务主键发起时上送，原样返回",allowEmptyValue=true,example="",allowableValues="")
	String bizKey;
	
	@ApiModelProperty(notes="流程key，可以根据该key找到对应的流程模型也代表审批事项，就是审什么内容",allowEmptyValue=true,example="",allowableValues="")
	String modelKey;
	
	@ApiModelProperty(notes="最后更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date flowLastTime;
	
	@ApiModelProperty(notes="流程审批机构号",allowEmptyValue=true,example="",allowableValues="")
	String flowBranchId;
	
	@ApiModelProperty(notes="0初始1审批中2审批通过3审批不通过4流程取消或者删除",allowEmptyValue=true,example="",allowableValues="")
	String flowState;
	
	@ApiModelProperty(notes="启动人",allowEmptyValue=true,example="",allowableValues="")
	String startUserid;
	
	@ApiModelProperty(notes="流程定义编号带版本的",allowEmptyValue=true,example="",allowableValues="")
	String procDefId;
	
	@ApiModelProperty(notes="模型名称，也代表审批事项，就是审什么内容",allowEmptyValue=true,example="",allowableValues="")
	String modelName;
	
	@ApiModelProperty(notes="结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date flowEndTime;
	
	@ApiModelProperty(notes="执行人",allowEmptyValue=true,example="",allowableValues="")
	String assigneeName;
	
	@ApiModelProperty(notes="商户编号",allowEmptyValue=true,example="",allowableValues="")
	String shopId;
	
	@ApiModelProperty(notes="等级编号",allowEmptyValue=true,example="",allowableValues="")
	String gradeId;

	/**主键**/
	public GradeProcessApprova(String id) {
		this.id = id;
	}
    
    /**新增等级审核流程**/
	public GradeProcessApprova() {
	}
	
	/**
	 * 机构编号
	 **/
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * 流程实例编号
	 **/
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	/**
	 * 审批状态同意1不同意0
	 **/
	public void setAgree(String agree) {
		this.agree = agree;
	}
	/**
	 * 审批人
	 **/
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	/**
	 * 流程标题
	 **/
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}
	/**
	 * 审批节点编号
	 **/
	public void setActId(String actId) {
		this.actId = actId;
	}
	/**
	 * 审批环节
	 **/
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/**
	 * 审批意见
	 **/
	public void setCommentMsg(String commentMsg) {
		this.commentMsg = commentMsg;
	}
	/**
	 * 主键
	 **/
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 事件类型create/assignment/complete/delete/PROCESS_CREATED/PROCESS_COMPLETE/PROCESS_CANCELLED
	 **/
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * 业务主键发起时上送，原样返回
	 **/
	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}
	/**
	 * 流程key，可以根据该key找到对应的流程模型也代表审批事项，就是审什么内容
	 **/
	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}
	/**
	 * 最后更新时间
	 **/
	public void setFlowLastTime(Date flowLastTime) {
		this.flowLastTime = flowLastTime;
	}
	/**
	 * 流程审批机构号
	 **/
	public void setFlowBranchId(String flowBranchId) {
		this.flowBranchId = flowBranchId;
	}
	/**
	 * 0初始1审批中2审批通过3审批不通过4流程取消或者删除
	 **/
	public void setFlowState(String flowState) {
		this.flowState = flowState;
	}
	/**
	 * 启动人
	 **/
	public void setStartUserid(String startUserid) {
		this.startUserid = startUserid;
	}
	/**
	 * 流程定义编号带版本的
	 **/
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	/**
	 * 模型名称，也代表审批事项，就是审什么内容
	 **/
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	/**
	 * 结束时间
	 **/
	public void setFlowEndTime(Date flowEndTime) {
		this.flowEndTime = flowEndTime;
	}
	/**
	 * 执行人
	 **/
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	/**
	 * 商户编号
	 **/
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	/**
	 * 等级编号
	 **/
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 机构编号
	 **/
	public String getBranchId() {
		return this.branchId;
	}
	/**
	 * 流程实例编号
	 **/
	public String getProcInstId() {
		return this.procInstId;
	}
	/**
	 * 审批状态同意1不同意0
	 **/
	public String getAgree() {
		return this.agree;
	}
	/**
	 * 审批人
	 **/
	public String getAssignee() {
		return this.assignee;
	}
	/**
	 * 流程标题
	 **/
	public String getMainTitle() {
		return this.mainTitle;
	}
	/**
	 * 审批节点编号
	 **/
	public String getActId() {
		return this.actId;
	}
	/**
	 * 审批环节
	 **/
	public String getTaskName() {
		return this.taskName;
	}
	/**
	 * 审批意见
	 **/
	public String getCommentMsg() {
		return this.commentMsg;
	}
	/**
	 * 主键
	 **/
	public String getId() {
		return this.id;
	}
	/**
	 * 事件类型create/assignment/complete/delete/PROCESS_CREATED/PROCESS_COMPLETE/PROCESS_CANCELLED
	 **/
	public String getEventName() {
		return this.eventName;
	}
	/**
	 * 业务主键发起时上送，原样返回
	 **/
	public String getBizKey() {
		return this.bizKey;
	}
	/**
	 * 流程key，可以根据该key找到对应的流程模型也代表审批事项，就是审什么内容
	 **/
	public String getModelKey() {
		return this.modelKey;
	}
	/**
	 * 最后更新时间
	 **/
	public Date getFlowLastTime() {
		return this.flowLastTime;
	}
	/**
	 * 流程审批机构号
	 **/
	public String getFlowBranchId() {
		return this.flowBranchId;
	}
	/**
	 * 0初始1审批中2审批通过3审批不通过4流程取消或者删除
	 **/
	public String getFlowState() {
		return this.flowState;
	}
	/**
	 * 启动人
	 **/
	public String getStartUserid() {
		return this.startUserid;
	}
	/**
	 * 流程定义编号带版本的
	 **/
	public String getProcDefId() {
		return this.procDefId;
	}
	/**
	 * 模型名称，也代表审批事项，就是审什么内容
	 **/
	public String getModelName() {
		return this.modelName;
	}
	/**
	 * 结束时间
	 **/
	public Date getFlowEndTime() {
		return this.flowEndTime;
	}
	/**
	 * 执行人
	 **/
	public String getAssigneeName() {
		return this.assigneeName;
	}
	/**
	 * 商户编号
	 **/
	public String getShopId() {
		return this.shopId;
	}
	/**
	 * 等级编号
	 **/
	public String getGradeId() {
		return this.gradeId;
	}

}