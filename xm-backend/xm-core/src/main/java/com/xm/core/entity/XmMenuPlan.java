package  com.xm.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组织 com  顶级模块 xm 大模块 core  小模块 <br> 
 * 实体 XmMenuPlan所有属性名: <br>
 *	projectId,projectName,menuId,planStartTime,planEndTime,actStartTime,actEndTime,planWorkload,actWorkload,planCostAmount,actCostAmount,finishRate,demandRate,designRate,devRate,uatRate,sitRate,onlineStatus,onlineTime,planStatus,chargeUserid,chargeUsername,menuStatus,ctime,ltime,cuserid,cusername,calcTime,menuName,planWorkhours,planWorkerCnt,closedBugs,activeBugs,confirmedBugs,resolvedBugs,testCases,execCases,designCases,finishCases,iterationCnt,taskCnt,finishTaskCnt,productId,productName,bugCnt;<br>
 * 表 xm_menu_plan 功能计划表,无需前端维护，所有数据由汇总统计得出的所有字段名: <br>
 *	project_id,project_name,menu_id,plan_start_time,plan_end_time,act_start_time,act_end_time,plan_workload,act_workload,plan_cost_amount,act_cost_amount,finish_rate,demand_rate,design_rate,dev_rate,uat_rate,sit_rate,online_status,online_time,plan_status,charge_userid,charge_username,menu_status,ctime,ltime,cuserid,cusername,calc_time,menu_name,plan_workhours,plan_worker_cnt,closed_bugs,active_bugs,confirmed_bugs,resolved_bugs,test_cases,exec_cases,design_cases,finish_cases,iteration_cnt,task_cnt,finish_task_cnt,product_id,product_name,bug_cnt;<br>
 * 当前主键(包括多主键):<br>
 *	project_id,menu_id;<br>
 */
@ApiModel(description="功能计划表,无需前端维护，所有数据由汇总统计得出")
public class XmMenuPlan  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes="项目编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String projectId;
	
	@ApiModelProperty(notes="功能编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String menuId;
  	
	
	@ApiModelProperty(notes="项目名称",allowEmptyValue=true,example="",allowableValues="")
	String projectName;
	
	@ApiModelProperty(notes="开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date planStartTime;
	
	@ApiModelProperty(notes="结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date planEndTime;
	
	@ApiModelProperty(notes="实际开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date actStartTime;
	
	@ApiModelProperty(notes="实际结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date actEndTime;
	
	@ApiModelProperty(notes="计划工作量，根据关联任务汇总",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planWorkload;
	
	@ApiModelProperty(notes="实际工作量，根据关联任务汇总",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actWorkload;
	
	@ApiModelProperty(notes="计划成本，根据关联任务汇总",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planCostAmount;
	
	@ApiModelProperty(notes="实际成本金额根据关联任务汇总",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actCostAmount;
	
	@ApiModelProperty(notes="总体完成比例0-100之间,根据taskType进行汇总",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal finishRate;
	
	@ApiModelProperty(notes="需求完成率0-100之间,根据taskType进行汇总",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal demandRate;
	
	@ApiModelProperty(notes="设计完成率0-100之间,根据taskType进行汇总",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal designRate;
	
	@ApiModelProperty(notes="开发完成率0-100之间,根据taskType进行汇总",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal devRate;
	
	@ApiModelProperty(notes="uat测试完成率0-100之间,根据taskType进行汇总",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal uatRate;
	
	@ApiModelProperty(notes="sit测试完成率0-100之间,根据taskType进行汇总",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal sitRate;
	
	@ApiModelProperty(notes="上线状态0未上线1上线成功",allowEmptyValue=true,example="",allowableValues="")
	String onlineStatus;
	
	@ApiModelProperty(notes="上线时间",allowEmptyValue=true,example="",allowableValues="")
	Date onlineTime;
	
	@ApiModelProperty(notes="计划状态0初始1正常2暂停3延误4结束5关闭",allowEmptyValue=true,example="",allowableValues="")
	String planStatus;
	
	@ApiModelProperty(notes="负责人编号",allowEmptyValue=true,example="",allowableValues="")
	String chargeUserid;
	
	@ApiModelProperty(notes="负责人姓名",allowEmptyValue=true,example="",allowableValues="")
	String chargeUsername;
	
	@ApiModelProperty(notes="状态0初始1设计中2开发中3测试中4uat测试2已上线3已下线4已删除",allowEmptyValue=true,example="",allowableValues="")
	String menuStatus;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;
	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;
	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;
	
	@ApiModelProperty(notes="汇总时间",allowEmptyValue=true,example="",allowableValues="")
	Date calcTime;
	
	@ApiModelProperty(notes="菜单名字",allowEmptyValue=true,example="",allowableValues="")
	String menuName;
	
	@ApiModelProperty(notes="工时数",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planWorkhours;
	
	@ApiModelProperty(notes="总人数",allowEmptyValue=true,example="",allowableValues="")
	Integer planWorkerCnt;
	
	@ApiModelProperty(notes="总关闭bugs",allowEmptyValue=true,example="",allowableValues="")
	Integer closedBugs;
	
	@ApiModelProperty(notes="激活bugs",allowEmptyValue=true,example="",allowableValues="")
	Integer activeBugs;
	
	@ApiModelProperty(notes="已确认bugs总数",allowEmptyValue=true,example="",allowableValues="")
	Integer confirmedBugs;
	
	@ApiModelProperty(notes="已解决bugs总数",allowEmptyValue=true,example="",allowableValues="")
	Integer resolvedBugs;
	
	@ApiModelProperty(notes="测试案例总数",allowEmptyValue=true,example="",allowableValues="")
	Integer testCases;
	
	@ApiModelProperty(notes="测试中案例总数",allowEmptyValue=true,example="",allowableValues="")
	Integer execCases;
	
	@ApiModelProperty(notes="设计中案例总数",allowEmptyValue=true,example="",allowableValues="")
	Integer designCases;
	
	@ApiModelProperty(notes="完成案例总数",allowEmptyValue=true,example="",allowableValues="")
	Integer finishCases;
	
	@ApiModelProperty(notes="关联迭代数",allowEmptyValue=true,example="",allowableValues="")
	Integer iterationCnt;
	
	@ApiModelProperty(notes="任务数",allowEmptyValue=true,example="",allowableValues="")
	Integer taskCnt;
	
	@ApiModelProperty(notes="完成的任务数",allowEmptyValue=true,example="",allowableValues="")
	Integer finishTaskCnt;
	
	@ApiModelProperty(notes="产品编号",allowEmptyValue=true,example="",allowableValues="")
	String productId;
	
	@ApiModelProperty(notes="产品名称",allowEmptyValue=true,example="",allowableValues="")
	String productName;
	
	@ApiModelProperty(notes="bug总数",allowEmptyValue=true,example="",allowableValues="")
	Integer bugCnt;

	/**项目编号,功能编号**/
	public XmMenuPlan(String projectId,String menuId) {
		this.projectId = projectId;
		this.menuId = menuId;
	}
    
    /**功能计划表,无需前端维护，所有数据由汇总统计得出**/
	public XmMenuPlan() {
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
	 * 功能编号
	 **/
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * 开始时间
	 **/
	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}
	/**
	 * 结束时间
	 **/
	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
	/**
	 * 实际开始时间
	 **/
	public void setActStartTime(Date actStartTime) {
		this.actStartTime = actStartTime;
	}
	/**
	 * 实际结束时间
	 **/
	public void setActEndTime(Date actEndTime) {
		this.actEndTime = actEndTime;
	}
	/**
	 * 计划工作量，根据关联任务汇总
	 **/
	public void setPlanWorkload(BigDecimal planWorkload) {
		this.planWorkload = planWorkload;
	}
	/**
	 * 实际工作量，根据关联任务汇总
	 **/
	public void setActWorkload(BigDecimal actWorkload) {
		this.actWorkload = actWorkload;
	}
	/**
	 * 计划成本，根据关联任务汇总
	 **/
	public void setPlanCostAmount(BigDecimal planCostAmount) {
		this.planCostAmount = planCostAmount;
	}
	/**
	 * 实际成本金额根据关联任务汇总
	 **/
	public void setActCostAmount(BigDecimal actCostAmount) {
		this.actCostAmount = actCostAmount;
	}
	/**
	 * 总体完成比例0-100之间,根据taskType进行汇总
	 **/
	public void setFinishRate(BigDecimal finishRate) {
		this.finishRate = finishRate;
	}
	/**
	 * 需求完成率0-100之间,根据taskType进行汇总
	 **/
	public void setDemandRate(BigDecimal demandRate) {
		this.demandRate = demandRate;
	}
	/**
	 * 设计完成率0-100之间,根据taskType进行汇总
	 **/
	public void setDesignRate(BigDecimal designRate) {
		this.designRate = designRate;
	}
	/**
	 * 开发完成率0-100之间,根据taskType进行汇总
	 **/
	public void setDevRate(BigDecimal devRate) {
		this.devRate = devRate;
	}
	/**
	 * uat测试完成率0-100之间,根据taskType进行汇总
	 **/
	public void setUatRate(BigDecimal uatRate) {
		this.uatRate = uatRate;
	}
	/**
	 * sit测试完成率0-100之间,根据taskType进行汇总
	 **/
	public void setSitRate(BigDecimal sitRate) {
		this.sitRate = sitRate;
	}
	/**
	 * 上线状态0未上线1上线成功
	 **/
	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	/**
	 * 上线时间
	 **/
	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}
	/**
	 * 计划状态0初始1正常2暂停3延误4结束5关闭
	 **/
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	/**
	 * 负责人编号
	 **/
	public void setChargeUserid(String chargeUserid) {
		this.chargeUserid = chargeUserid;
	}
	/**
	 * 负责人姓名
	 **/
	public void setChargeUsername(String chargeUsername) {
		this.chargeUsername = chargeUsername;
	}
	/**
	 * 状态0初始1设计中2开发中3测试中4uat测试2已上线3已下线4已删除
	 **/
	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}
	/**
	 * 创建时间
	 **/
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	/**
	 * 更新时间
	 **/
	public void setLtime(Date ltime) {
		this.ltime = ltime;
	}
	/**
	 * 创建人编号
	 **/
	public void setCuserid(String cuserid) {
		this.cuserid = cuserid;
	}
	/**
	 * 创建人姓名
	 **/
	public void setCusername(String cusername) {
		this.cusername = cusername;
	}
	/**
	 * 汇总时间
	 **/
	public void setCalcTime(Date calcTime) {
		this.calcTime = calcTime;
	}
	/**
	 * 菜单名字
	 **/
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * 工时数
	 **/
	public void setPlanWorkhours(BigDecimal planWorkhours) {
		this.planWorkhours = planWorkhours;
	}
	/**
	 * 总人数
	 **/
	public void setPlanWorkerCnt(Integer planWorkerCnt) {
		this.planWorkerCnt = planWorkerCnt;
	}
	/**
	 * 总关闭bugs
	 **/
	public void setClosedBugs(Integer closedBugs) {
		this.closedBugs = closedBugs;
	}
	/**
	 * 激活bugs
	 **/
	public void setActiveBugs(Integer activeBugs) {
		this.activeBugs = activeBugs;
	}
	/**
	 * 已确认bugs总数
	 **/
	public void setConfirmedBugs(Integer confirmedBugs) {
		this.confirmedBugs = confirmedBugs;
	}
	/**
	 * 已解决bugs总数
	 **/
	public void setResolvedBugs(Integer resolvedBugs) {
		this.resolvedBugs = resolvedBugs;
	}
	/**
	 * 测试案例总数
	 **/
	public void setTestCases(Integer testCases) {
		this.testCases = testCases;
	}
	/**
	 * 测试中案例总数
	 **/
	public void setExecCases(Integer execCases) {
		this.execCases = execCases;
	}
	/**
	 * 设计中案例总数
	 **/
	public void setDesignCases(Integer designCases) {
		this.designCases = designCases;
	}
	/**
	 * 完成案例总数
	 **/
	public void setFinishCases(Integer finishCases) {
		this.finishCases = finishCases;
	}
	/**
	 * 关联迭代数
	 **/
	public void setIterationCnt(Integer iterationCnt) {
		this.iterationCnt = iterationCnt;
	}
	/**
	 * 任务数
	 **/
	public void setTaskCnt(Integer taskCnt) {
		this.taskCnt = taskCnt;
	}
	/**
	 * 完成的任务数
	 **/
	public void setFinishTaskCnt(Integer finishTaskCnt) {
		this.finishTaskCnt = finishTaskCnt;
	}
	/**
	 * 产品编号
	 **/
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 产品名称
	 **/
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * bug总数
	 **/
	public void setBugCnt(Integer bugCnt) {
		this.bugCnt = bugCnt;
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
	 * 功能编号
	 **/
	public String getMenuId() {
		return this.menuId;
	}
	/**
	 * 开始时间
	 **/
	public Date getPlanStartTime() {
		return this.planStartTime;
	}
	/**
	 * 结束时间
	 **/
	public Date getPlanEndTime() {
		return this.planEndTime;
	}
	/**
	 * 实际开始时间
	 **/
	public Date getActStartTime() {
		return this.actStartTime;
	}
	/**
	 * 实际结束时间
	 **/
	public Date getActEndTime() {
		return this.actEndTime;
	}
	/**
	 * 计划工作量，根据关联任务汇总
	 **/
	public BigDecimal getPlanWorkload() {
		return this.planWorkload;
	}
	/**
	 * 实际工作量，根据关联任务汇总
	 **/
	public BigDecimal getActWorkload() {
		return this.actWorkload;
	}
	/**
	 * 计划成本，根据关联任务汇总
	 **/
	public BigDecimal getPlanCostAmount() {
		return this.planCostAmount;
	}
	/**
	 * 实际成本金额根据关联任务汇总
	 **/
	public BigDecimal getActCostAmount() {
		return this.actCostAmount;
	}
	/**
	 * 总体完成比例0-100之间,根据taskType进行汇总
	 **/
	public BigDecimal getFinishRate() {
		return this.finishRate;
	}
	/**
	 * 需求完成率0-100之间,根据taskType进行汇总
	 **/
	public BigDecimal getDemandRate() {
		return this.demandRate;
	}
	/**
	 * 设计完成率0-100之间,根据taskType进行汇总
	 **/
	public BigDecimal getDesignRate() {
		return this.designRate;
	}
	/**
	 * 开发完成率0-100之间,根据taskType进行汇总
	 **/
	public BigDecimal getDevRate() {
		return this.devRate;
	}
	/**
	 * uat测试完成率0-100之间,根据taskType进行汇总
	 **/
	public BigDecimal getUatRate() {
		return this.uatRate;
	}
	/**
	 * sit测试完成率0-100之间,根据taskType进行汇总
	 **/
	public BigDecimal getSitRate() {
		return this.sitRate;
	}
	/**
	 * 上线状态0未上线1上线成功
	 **/
	public String getOnlineStatus() {
		return this.onlineStatus;
	}
	/**
	 * 上线时间
	 **/
	public Date getOnlineTime() {
		return this.onlineTime;
	}
	/**
	 * 计划状态0初始1正常2暂停3延误4结束5关闭
	 **/
	public String getPlanStatus() {
		return this.planStatus;
	}
	/**
	 * 负责人编号
	 **/
	public String getChargeUserid() {
		return this.chargeUserid;
	}
	/**
	 * 负责人姓名
	 **/
	public String getChargeUsername() {
		return this.chargeUsername;
	}
	/**
	 * 状态0初始1设计中2开发中3测试中4uat测试2已上线3已下线4已删除
	 **/
	public String getMenuStatus() {
		return this.menuStatus;
	}
	/**
	 * 创建时间
	 **/
	public Date getCtime() {
		return this.ctime;
	}
	/**
	 * 更新时间
	 **/
	public Date getLtime() {
		return this.ltime;
	}
	/**
	 * 创建人编号
	 **/
	public String getCuserid() {
		return this.cuserid;
	}
	/**
	 * 创建人姓名
	 **/
	public String getCusername() {
		return this.cusername;
	}
	/**
	 * 汇总时间
	 **/
	public Date getCalcTime() {
		return this.calcTime;
	}
	/**
	 * 菜单名字
	 **/
	public String getMenuName() {
		return this.menuName;
	}
	/**
	 * 工时数
	 **/
	public BigDecimal getPlanWorkhours() {
		return this.planWorkhours;
	}
	/**
	 * 总人数
	 **/
	public Integer getPlanWorkerCnt() {
		return this.planWorkerCnt;
	}
	/**
	 * 总关闭bugs
	 **/
	public Integer getClosedBugs() {
		return this.closedBugs;
	}
	/**
	 * 激活bugs
	 **/
	public Integer getActiveBugs() {
		return this.activeBugs;
	}
	/**
	 * 已确认bugs总数
	 **/
	public Integer getConfirmedBugs() {
		return this.confirmedBugs;
	}
	/**
	 * 已解决bugs总数
	 **/
	public Integer getResolvedBugs() {
		return this.resolvedBugs;
	}
	/**
	 * 测试案例总数
	 **/
	public Integer getTestCases() {
		return this.testCases;
	}
	/**
	 * 测试中案例总数
	 **/
	public Integer getExecCases() {
		return this.execCases;
	}
	/**
	 * 设计中案例总数
	 **/
	public Integer getDesignCases() {
		return this.designCases;
	}
	/**
	 * 完成案例总数
	 **/
	public Integer getFinishCases() {
		return this.finishCases;
	}
	/**
	 * 关联迭代数
	 **/
	public Integer getIterationCnt() {
		return this.iterationCnt;
	}
	/**
	 * 任务数
	 **/
	public Integer getTaskCnt() {
		return this.taskCnt;
	}
	/**
	 * 完成的任务数
	 **/
	public Integer getFinishTaskCnt() {
		return this.finishTaskCnt;
	}
	/**
	 * 产品编号
	 **/
	public String getProductId() {
		return this.productId;
	}
	/**
	 * 产品名称
	 **/
	public String getProductName() {
		return this.productName;
	}
	/**
	 * bug总数
	 **/
	public Integer getBugCnt() {
		return this.bugCnt;
	}

}