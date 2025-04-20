package  com.xm.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组织 com  顶级模块 xm 大模块 core  小模块 <br> 
 * 实体 XmProjectTaskTypeState所有属性名: <br>
 *	projectId,projectName,taskType,planWorkload,planAmount,actWorkload,actAmount,branchId,bizDate,calcTime,planOuserAt,planIuserAt,actOutUserAt,actInnerUserAt,planOuserWorkload,planIuserWorkload,actOuserWorkload,actIuserWorkload,planNouserAt,actNouserAt,id;<br>
 * 表 xm_project_task_type_state 按任务类型汇总的所有字段名: <br>
 *	project_id,project_name,task_type,plan_workload,plan_amount,act_workload,act_amount,branch_id,biz_date,calc_time,plan_ouser_at,plan_iuser_at,act_ouser_at,act_iuser_at,plan_ouser_workload,plan_iuser_workload,act_ouser_workload,act_iuser_workload,plan_nouser_at,act_nouser_at,id;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@ApiModel(description="按任务类型汇总")
public class XmProjectTaskTypeState  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
  	
	
	@ApiModelProperty(notes="项目编号",allowEmptyValue=true,example="",allowableValues="")
	String projectId;
	
	@ApiModelProperty(notes="项目名称",allowEmptyValue=true,example="",allowableValues="")
	String projectName;
	
	@ApiModelProperty(notes="任务类型",allowEmptyValue=true,example="",allowableValues="")
	String taskType;
	
	@ApiModelProperty(notes="工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planWorkload;
	
	@ApiModelProperty(notes="预算金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planAmount;
	
	@ApiModelProperty(notes="实际完成工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actWorkload;
	
	@ApiModelProperty(notes="实际完成金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actAmount;
	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="业务日期yyyy-MM-dd型",allowEmptyValue=true,example="",allowableValues="")
	String bizDate;
	
	@ApiModelProperty(notes="计算日期",allowEmptyValue=true,example="",allowableValues="")
	Date calcTime;
	
	@ApiModelProperty(notes="外购资金预算",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planOuserAt;
	
	@ApiModelProperty(notes="内购资金预算",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planIuserAt;
	
	@ApiModelProperty(notes="实际外购成本",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actOutUserAt;
	
	@ApiModelProperty(notes="实际内购成本",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actInnerUserAt;
	
	@ApiModelProperty(notes="计划外购工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planOuserWorkload;
	
	@ApiModelProperty(notes="计划内购工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planIuserWorkload;
	
	@ApiModelProperty(notes="实际外购工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actOuserWorkload;
	
	@ApiModelProperty(notes="实际内购工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actIuserWorkload;
	
	@ApiModelProperty(notes="计划非人力成本",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planNouserAt;
	
	@ApiModelProperty(notes="实际非人力成本",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actNouserAt;

	/**主键**/
	public XmProjectTaskTypeState(String id) {
		this.id = id;
	}
    
    /**按任务类型汇总**/
	public XmProjectTaskTypeState() {
	}
	
	/**
	 * 项目编号
	 **/
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 项目名称
	 **/
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 任务类型
	 **/
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	/**
	 * 工作量
	 **/
	public void setPlanWorkload(BigDecimal planWorkload) {
		this.planWorkload = planWorkload;
	}
	/**
	 * 预算金额
	 **/
	public void setPlanAmount(BigDecimal planAmount) {
		this.planAmount = planAmount;
	}
	/**
	 * 实际完成工作量
	 **/
	public void setActWorkload(BigDecimal actWorkload) {
		this.actWorkload = actWorkload;
	}
	/**
	 * 实际完成金额
	 **/
	public void setActAmount(BigDecimal actAmount) {
		this.actAmount = actAmount;
	}
	/**
	 * 机构编号
	 **/
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * 业务日期yyyy-MM-dd型
	 **/
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
	/**
	 * 计算日期
	 **/
	public void setCalcTime(Date calcTime) {
		this.calcTime = calcTime;
	}
	/**
	 * 外购资金预算
	 **/
	public void setPlanOuserAt(BigDecimal planOuserAt) {
		this.planOuserAt = planOuserAt;
	}
	/**
	 * 内购资金预算
	 **/
	public void setPlanIuserAt(BigDecimal planIuserAt) {
		this.planIuserAt = planIuserAt;
	}
	/**
	 * 实际外购成本
	 **/
	public void setActOutUserAt(BigDecimal actOutUserAt) {
		this.actOutUserAt = actOutUserAt;
	}
	/**
	 * 实际内购成本
	 **/
	public void setActInnerUserAt(BigDecimal actInnerUserAt) {
		this.actInnerUserAt = actInnerUserAt;
	}
	/**
	 * 计划外购工作量
	 **/
	public void setPlanOuserWorkload(BigDecimal planOuserWorkload) {
		this.planOuserWorkload = planOuserWorkload;
	}
	/**
	 * 计划内购工作量
	 **/
	public void setPlanIuserWorkload(BigDecimal planIuserWorkload) {
		this.planIuserWorkload = planIuserWorkload;
	}
	/**
	 * 实际外购工作量
	 **/
	public void setActOuserWorkload(BigDecimal actOuserWorkload) {
		this.actOuserWorkload = actOuserWorkload;
	}
	/**
	 * 实际内购工作量
	 **/
	public void setActIuserWorkload(BigDecimal actIuserWorkload) {
		this.actIuserWorkload = actIuserWorkload;
	}
	/**
	 * 计划非人力成本
	 **/
	public void setPlanNouserAt(BigDecimal planNouserAt) {
		this.planNouserAt = planNouserAt;
	}
	/**
	 * 实际非人力成本
	 **/
	public void setActNouserAt(BigDecimal actNouserAt) {
		this.actNouserAt = actNouserAt;
	}
	/**
	 * 主键
	 **/
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 项目编号
	 **/
	public String getProjectId() {
		return this.projectId;
	}
	/**
	 * 项目名称
	 **/
	public String getProjectName() {
		return this.projectName;
	}
	/**
	 * 任务类型
	 **/
	public String getTaskType() {
		return this.taskType;
	}
	/**
	 * 工作量
	 **/
	public BigDecimal getPlanWorkload() {
		return this.planWorkload;
	}
	/**
	 * 预算金额
	 **/
	public BigDecimal getPlanAmount() {
		return this.planAmount;
	}
	/**
	 * 实际完成工作量
	 **/
	public BigDecimal getActWorkload() {
		return this.actWorkload;
	}
	/**
	 * 实际完成金额
	 **/
	public BigDecimal getActAmount() {
		return this.actAmount;
	}
	/**
	 * 机构编号
	 **/
	public String getBranchId() {
		return this.branchId;
	}
	/**
	 * 业务日期yyyy-MM-dd型
	 **/
	public String getBizDate() {
		return this.bizDate;
	}
	/**
	 * 计算日期
	 **/
	public Date getCalcTime() {
		return this.calcTime;
	}
	/**
	 * 外购资金预算
	 **/
	public BigDecimal getPlanOuserAt() {
		return this.planOuserAt;
	}
	/**
	 * 内购资金预算
	 **/
	public BigDecimal getPlanIuserAt() {
		return this.planIuserAt;
	}
	/**
	 * 实际外购成本
	 **/
	public BigDecimal getActOutUserAt() {
		return this.actOutUserAt;
	}
	/**
	 * 实际内购成本
	 **/
	public BigDecimal getActInnerUserAt() {
		return this.actInnerUserAt;
	}
	/**
	 * 计划外购工作量
	 **/
	public BigDecimal getPlanOuserWorkload() {
		return this.planOuserWorkload;
	}
	/**
	 * 计划内购工作量
	 **/
	public BigDecimal getPlanIuserWorkload() {
		return this.planIuserWorkload;
	}
	/**
	 * 实际外购工作量
	 **/
	public BigDecimal getActOuserWorkload() {
		return this.actOuserWorkload;
	}
	/**
	 * 实际内购工作量
	 **/
	public BigDecimal getActIuserWorkload() {
		return this.actIuserWorkload;
	}
	/**
	 * 计划非人力成本
	 **/
	public BigDecimal getPlanNouserAt() {
		return this.planNouserAt;
	}
	/**
	 * 实际非人力成本
	 **/
	public BigDecimal getActNouserAt() {
		return this.actNouserAt;
	}
	/**
	 * 主键
	 **/
	public String getId() {
		return this.id;
	}

}