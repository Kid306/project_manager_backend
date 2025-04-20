package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.mdp.core.dao.annotation.TableIds;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @author 唛盟开源 code-gen
 * @since 2025-3-24
 */
@Data
@TableName("xm_collect_state")
@Schema(description="项目集内所有项目指标汇总")
public class XmCollectState  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="集合编号,主键")
	String collectId;

	
	@Schema(description="项目数")
	Integer projectCnt;

	
	@Schema(description="统计日期yyyy-mm-dd类型")
	String bizDate;

	
	@Schema(description="文件数据")
	Integer fileCnt;

	
	@Schema(description="统计执行日期")
	Date calcTime;

	
	@Schema(description="0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖")
	String calcStatus;

	
	@Schema(description="项目阶段计划数")
	Integer phaseCnt;

	
	@Schema(description="项目阶段计划已完成数")
	Integer phaseFinishCnt;

	
	@Schema(description="待付款总金额")
	BigDecimal needPayAt;

	
	@Schema(description="已付款总金额")
	BigDecimal finishPayAt;

	
	@Schema(description="待收款总金额")
	BigDecimal needColAt;

	
	@Schema(description="已收款总金额")
	BigDecimal finishColAt;

	
	@Schema(description="项目风险总数")
	Integer riskCnt;

	
	@Schema(description="已完成风险总数")
	Integer riskFinishCnt;

	
	@Schema(description="机构编号")
	String branchId;

	
	@Schema(description="项目总非人力预算-来自任务表")
	BigDecimal budgetNouserAt;

	
	@Schema(description="项目总外购人力预算-来自任务表")
	BigDecimal budgetOuserAt;

	
	@Schema(description="项目总内部人力预算-来自任务表")
	BigDecimal budgetIuserAt;

	
	@Schema(description="项目总人力成本")
	BigDecimal actUserAt;

	
	@Schema(description="项目总内部人力成本金额")
	BigDecimal actIuserAt;

	
	@Schema(description="项目总外购人力成本金额")
	BigDecimal actOuserAt;

	
	@Schema(description="项目总非人力成本")
	BigDecimal actNouserAt;

	
	@Schema(description="项目进度0~100之间，来自任务表")
	BigDecimal finishRate;

	
	@Schema(description="项目总预算工作量-来自任务表")
	BigDecimal budgetWorkload;

	
	@Schema(description="外购人力总工作量-应该大于或等于阶段计划外购人力总成本")
	BigDecimal budgetOuserWorkload;

	
	@Schema(description="内部人力总工作量-应该大于或等于阶段计划内部人力总成本")
	BigDecimal budgetIuserWorkload;

	
	@Schema(description="预估工时=计划结束时间在计算当日前完成的任务的预算工时总和")
	BigDecimal estimateWorkload;

	
	@Schema(description="已完成工作量-来自计划中实际完成工作量")
	BigDecimal actWorkload;

	
	@Schema(description="实际外购总工作量，来自任务表")
	BigDecimal actOuserWorkload;

	
	@Schema(description="实际内部总工作量，来自任务表")
	BigDecimal actIuserWorkload;

	
	@Schema(description="待付款笔数")
	BigDecimal needPayCnt;

	
	@Schema(description="完成付款总比数")
	BigDecimal finishPayCnt;

	
	@Schema(description="已付款总人数")
	BigDecimal finishPayUserCnt;

	
	@Schema(description="待付款总人数")
	BigDecimal needPayUserCnt;

	
	@Schema(description="测试案例总数")
	Integer testCases;

	
	@Schema(description="测试中案例总数")
	Integer execCases;

	
	@Schema(description="设计中案例总数")
	Integer designCases;

	
	@Schema(description="完成案例总数")
	Integer finishCases;

	
	@Schema(description="迭代数")
	Integer iterationCnt;

	
	@Schema(description="产品数")
	Integer productCnt;

	
	@Schema(description="最早开始日期")
	Date minStartTime;

	
	@Schema(description="最晚结束时间")
	Date maxEndTime;

	
	@Schema(description="故事数")
	Integer menuCnt;

	
	@Schema(description="已完成需求数，2状态需求")
	Integer menuFinishCnt;

	
	@Schema(description="执行中需求数，1状态的需求")
	Integer menuExecCnt;

	
	@Schema(description="未开始需求数，0状态数据")
	Integer menuUnstartCnt;

	
	@Schema(description="已关闭需求数，3状态数据")
	Integer menuCloseCnt;

	
	@Schema(description="任务总数")
	Integer taskCnt;

	
	@Schema(description="待开始任务")
	Integer taskUnstartCnt;

	
	@Schema(description="执行中任务")
	Integer taskExecCnt;

	
	@Schema(description="已完成任务总数-来自任务表")
	Integer taskFinishCnt;

	
	@Schema(description="已结算任务")
	Integer taskSetCnt;

	
	@Schema(description="外购任务数，来自任务表")
	BigDecimal taskOutCnt;

	
	@Schema(description="已关闭任务")
	Integer taskCloseCnt;

	
	@Schema(description="bug数目")
	Integer bugCnt;

	
	@Schema(description="已关闭bug总数")
	Integer closedBugs;

	
	@Schema(description="已解决bug总数")
	Integer resolvedBugs;

	
	@Schema(description="激活的bug总数")
	Integer activeBugs;

	
	@Schema(description="已解决bug总数")
	Integer confirmedBugs;

	
	@Schema(description="工期（小时）")
	BigDecimal planWorkhours;

	
	@Schema(description="总人数")
	Integer planWorkerCnt;

	
	@Schema(description="实际投入人员数")
	BigDecimal actWorkerCnt;

	
	@Schema(description="预算金额")
	BigDecimal budgetAt;

	
	@Schema(description="实际金额")
	BigDecimal actAt;

	
	@Schema(description="产品工时")
	BigDecimal productBudgetWorkload;

	
	@Schema(description="产品实际工时")
	BigDecimal productActWorkload;

	
	@Schema(description="计划预估总工时=一级计划中的init_workload汇总而来")
	BigDecimal initWorkload;

	
	@Schema(description="计划进度0-100之间，=实际总工时/计划预估总工时*100")
	Integer initRate;

	
	@Schema(description="非报工工时")
	BigDecimal mactWorkload;

	
	@Schema(description="完成工时合计=act_workload+pmact_workload")
	BigDecimal sumActWorkload;

	
	@Schema(description="集合名称")
	String collectName;

	/**
	 *集合编号
	 **/
	public XmCollectState(String collectId) {
		this.collectId = collectId;
	}
    
    /**
     * 项目集内所有项目指标汇总
     **/
	public XmCollectState() {
	}

}