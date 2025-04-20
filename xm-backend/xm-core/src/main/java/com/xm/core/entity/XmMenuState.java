package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.mdp.core.dao.annotation.TableIds;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 唛盟开源 code-gen
 * @since 2024-9-9
 */
@Data
@TableName("xm_menu_state")
@Schema(description="功能状态表")
public class XmMenuState  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="功能编号,主键")
	String menuId;

	
	@Schema(description="总体完成比例0-100之间,根据taskType进行汇总")
	BigDecimal finishRate;

	
	@Schema(description="状态0初始1设计中2开发中3测试中4uat测试2已上线3已下线4已删除")
	String menuStatus;

	
	@Schema(description="创建时间")
	Date ctime;

	
	@Schema(description="汇总时间")
	Date calcTime;

	
	@Schema(description="菜单名字")
	String menuName;

	
	@Schema(description="工时数")
	BigDecimal planWorkhours;

	
	@Schema(description="总人数")
	BigDecimal planWorkerCnt;

	
	@Schema(description="总关闭bugs")
	BigDecimal closedBugs;

	
	@Schema(description="激活bugs")
	BigDecimal activeBugs;

	
	@Schema(description="已确认bugs总数")
	BigDecimal confirmedBugs;

	
	@Schema(description="已解决bugs总数")
	BigDecimal resolvedBugs;

	
	@Schema(description="产品编号")
	String productId;

	
	@Schema(description="测试案例总数-指测试库中总用例数")
	Integer testCases;

	
	@Schema(description="测试中案例总数-指有测试计划的阻塞和失败的用例总数-去重")
	Integer execCases;

	
	@Schema(description="设计中案例总数-指有测试计划的未测状态的用例数-去重")
	Integer designCases;

	
	@Schema(description="完成案例总数-指有测试计划的已通过和忽略状态的用例数-去重")
	Integer finishCases;

	
	@Schema(description="业务日期yyyy-MM-dd字符串")
	String bizDate;

	
	@Schema(description="bug总数")
	Integer bugCnt;

	
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

	
	@Schema(description="项目总预算工作量-来自任务表任务预估工时合计")
	BigDecimal budgetWorkload;

	
	@Schema(description="外购人力总工作量-应该大于或等于阶段计划外购人力总成本")
	BigDecimal budgetOuserWorkload;

	
	@Schema(description="内部人力总工作量-应该大于或等于阶段计划内部人力总成本")
	BigDecimal budgetIuserWorkload;

	
	@Schema(description="已完成工作量-来自工时明细表")
	BigDecimal actWorkload;

	
	@Schema(description="实际外购总工作量，来自任务表")
	BigDecimal actOuserWorkload;

	
	@Schema(description="实际内部总工作量，来自任务表")
	BigDecimal actIuserWorkload;

	
	@Schema(description="预估工时")
	BigDecimal estimateWorkload;

	
	@Schema(description="预算金额")
	BigDecimal budgetAt;

	
	@Schema(description="实际金额")
	BigDecimal actAt;

	
	@Schema(description="最早开始日期")
	Date minStartTime;

	
	@Schema(description="最晚结束时间")
	Date maxEndTime;

	
	@Schema(description="关联产品数（主要是指子节点关联）")
	Integer productCnt;

	
	@Schema(description="关联迭代数（主要是指子节点关联）")
	Integer iterationCnt;

	
	@Schema(description="关联项目数（主要是指子节点关联）")
	Integer projectCnt;

	
	@Schema(description="儿子节点树-不包括孙子，不包括故事")
	Integer subEfCnt;

	
	@Schema(description="儿子故事树-不包括孙子，仅仅计算故事数量")
	Integer subStoryCnt;

	
	@Schema(description="计划预估总工时,手填或者上级分解")
	BigDecimal initWorkload;

	
	@Schema(description="计划进度0-100之间，=实际总工时/计划预估总工时*100")
	Integer initRate;

	
	@Schema(description="权重0-100之间，上级为100，儿子节点总和不超过100")
	Integer weight;

	
	@Schema(description="手工填写本级节点独立实际工时")
	BigDecimal mactWorkload;

	
	@Schema(description="实际工时汇总，=son_sum_mact_workload+mact_workload+act_workload")
	BigDecimal sumActWorkload;

	
	@Schema(description="下级手工工时汇总,不包括本级mact_wokload")
	BigDecimal sonSumMactWorkload;

	
	@Schema(description="下级预估工时汇总，不包括本级=所有儿子节点的init_workload之后")
	BigDecimal sonSumInitWorkload;

	/**
	 *功能编号
	 **/
	public XmMenuState(String menuId) {
		this.menuId = menuId;
	}
    
    /**
     * 功能状态表
     **/
	public XmMenuState() {
	}

}