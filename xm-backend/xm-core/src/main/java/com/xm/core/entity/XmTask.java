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
 * @since 2025-4-7
 */
@Data
@TableName("xm_task")
@Schema(description="项目任务表")
public class XmTask  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="任务编号,主键")
	String id;

	
	@Schema(description="任务名称")
	String name;

	
	@Schema(description="父任务编号")
	String parentTaskid;

	
	@Schema(description="父任务名称")
	String parentTaskname;

	
	@Schema(description="项目编号")
	String projectId;

	
	@Schema(description="项目名称")
	String projectName;

	
	@Schema(description="任务级别")
	String level;

	
	@Schema(description="序号")
	String sortLevel;

	
	@Schema(description="任务执行人编号")
	String executorUserid;

	
	@Schema(description="任务执行人")
	String executorUsername;

	
	@Schema(description="前置任务编号")
	String preTaskid;

	
	@Schema(description="前置任务名称")
	String preTaskname;

	
	@Schema(description="任务开始时间")
	Date startTime;

	
	@Schema(description="任务结束时间")
	Date endTime;

	
	@Schema(description="里程碑")
	String milestone;

	
	@Schema(description="任务描述")
	String description;

	
	@Schema(description="备注")
	String remarks;

	
	@Schema(description="任务创建人编号（谁创建谁负责）")
	String createUserid;

	
	@Schema(description="任务创建人（谁创建谁负责）")
	String createUsername;

	
	@Schema(description="创建时间")
	Date createTime;

	
	@Schema(description="任务进度0-100（=实际工时/(任务预估工时*100）")
	Integer rate;

	
	@Schema(description="当前任务预算金额（calc_type=2时预算工时*单价，calc_type=1时下级汇总）")
	BigDecimal budgetAt;

	
	@Schema(description="任务预估工时，自底向上逐层汇总")
	BigDecimal budgetWorkload;

	
	@Schema(description="当前任务实际费用金额（calc_type=2时，取实际工时*单价，calc_type=1时取下级汇总数据）待结算金额")
	BigDecimal actAt;

	
	@Schema(description="任务取工时表报工工时汇总，其余取下级汇总")
	BigDecimal actWorkload;

	
	@Schema(description="任务状态0待领取1已领取执行中2已完工3已验收4已结算9已关闭")
	String taskState;

	
	@Schema(description="0售前方案1投标2需求3设计4开发5测试6验收7部署8运维--来自基础数据表taskType")
	String taskType;

	
	@Schema(description="1需结算0不需结算")
	String taskClass;

	
	@Schema(description="是否发布到任务大厅0否1是,1时互联网可访问")
	String toTaskCenter;

	
	@Schema(description="实际开始时间-任务状态变成执行中的时间")
	Date actStartTime;

	
	@Schema(description="实际结束时间-任务状态变成完工状态时的时间")
	Date actEndTime;

	
	@Schema(description="当前流程实例编号")
	String bizProcInstId;

	
	@Schema(description="当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除")
	String bizFlowState;

	
	@Schema(description="项目阶段编号,执行项目类型定义下的项目阶段节点")
	String phaseId;

	
	@Schema(description="项目阶段名称(作废)")
	String phaseName;

	
	@Schema(description="技能列表,逗号分隔")
	String taskSkillNames;

	
	@Schema(description="执行人列表逗号分隔如陈x(审核人),王x(监控人)")
	String exeUsernames;

	
	@Schema(description="技能编号列表逗号分隔")
	String taskSkillIds;

	
	@Schema(description="执行人编号列表逗号分隔如u1(1),u2(2)")
	String exeUserids;

	
	@Schema(description="执行方式-0内研1外购")
	String taskOut;

	
	@Schema(description="计划类型w1-周,w2-2周,w3-3周,m1-1月,m2-2月,q1-季,q2-半年，y1-年")
	String planType;

	
	@Schema(description="任务结算方案-来自数字字典xmTaskSettleSchemel")
	String settleSchemel;

	
	@Schema(description="归属功能编号")
	String menuId;

	
	@Schema(description="归属功能名称")
	String menuName;

	
	@Schema(description="产品编号根据功能变化带进")
	String productId;

	
	@Schema(description="创建机构")
	String cbranchId;

	
	@Schema(description="创建部门")
	String cdeptid;

	
	@Schema(description="标签编号，逗号分割")
	String tagIds;

	
	@Schema(description="标签名称，逗号分割")
	String tagNames;

	
	@Schema(description="节点类型0-任务，1-计划。计划下可建立计划和任务，任务下不允许再扩展。也就是非叶子节点都是计划，叶子节点有可能是计划或者任务")
	String ntype;

	
	@Schema(description="儿子节点个数")
	Integer childrenCnt;

	
	@Schema(description="更新时间")
	Date ltime;

	
	@Schema(description="父级id逗号分割，最后一个为本节点节点编号,以,号结尾")
	String pidPaths;

	
	@Schema(description="层级0-顶级，1-一级，2-二级，3-三级，4-四级。总共5级")
	Integer lvl;

	
	@Schema(description="是否为模板")
	String isTpl;

	
	@Schema(description="是否为关键路径上的节点")
	String keyPath;

	
	@Schema(description="内部单位工时单价")
	BigDecimal uniInnerPrice;

	
	@Schema(description="外部单位工时单价")
	BigDecimal uniOutPrice;

	
	@Schema(description="实际完成工时统计")
	String calcType;

	
	@Schema(description="类型")
	String ptype;

	
	@Schema(description="报工方式1-强制每日报工，2-工期内报工，0-无需报工")
	String wtype;

	
	@Schema(description="报工限制0-不限制，1-不得超出预估工时")
	String bctrl;

	
	@Schema(description="计划预估工作量，自顶向下按权重逐层分解")
	BigDecimal initWorkload;

	
	@Schema(description="分享赚佣金")
	BigDecimal shareFee;

	
	@Schema(description="开启分享赚功能0-否1-待付款，2已付款")
	String oshare;

	
	@Schema(description="是否众包0否1是，众包属于外购的一种")
	String crowd;

	
	@Schema(description="浏览人数")
	Integer browseUsers;

	
	@Schema(description="投标人数")
	Integer execUsers;

	
	@Schema(description="城市编号")
	String cityId;

	
	@Schema(description="城市名称")
	String cityName;

	
	@Schema(description="地域限制方式0-不限制，1-同城，2-同省，3-同国，4-同洲")
	String regionType;

	
	@Schema(description="浏览次数")
	Integer browseTimes;

	
	@Schema(description="能力等级最小要求")
	String capaLvls;

	
	@Schema(description="交易模式1-招标，2-雇佣")
	String tranMode;

	
	@Schema(description="保障要求编号0-不限制，1铜牌，2银牌，3金牌")
	String supRequires;

	
	@Schema(description="是否为热搜0否1待付款2已开通3已过期,每次热搜3天，3天后自动取消热搜")
	String hot;

	
	@Schema(description="是否为置顶0否1待付款2已开通3已过期,每次置顶3天，3天后自动取消置顶")
	String top;

	
	@Schema(description="加急0否1待付款2已开通3已过期")
	String urgent;

	
	@Schema(description="客服包办0否1待付款2已开通，理顺需求、比稿选稿")
	String crmSup;

	
	@Schema(description="投标流程0-草稿，1-发布需求，2-用户投标，3雇主选标，4拓管赏金，5用户工作，6验收付款,7完结雇主正式发布后由0->2雇主选标后由2->4雇主托管资金后由4->5服务商提交任务成功，由5->6任务验收完毕后，雇主手动点击付款，由6->7")
	String bidStep;

	
	@Schema(description="会员等级最小要求")
	String interestLvls;

	
	@Schema(description="附件地址列表，逗号分割")
	String filePaths;

	
	@Schema(description="资金托管状况0-无须托管，1-待付款，2-已托管资金，3-已付款给服务商，4-已退款")
	String estate;

	
	@Schema(description="托管金额=quote_final_at")
	BigDecimal efunds;

	
	@Schema(description="托管资金付款给平台的时间")
	Date etoPlatTime;

	
	@Schema(description="托管资金支付给服务商的时间")
	Date etoDevTime;

	
	@Schema(description="托管资金退回甲方时间")
	Date ebackTime;

	
	@Schema(description="置顶开始时间")
	Date topStime;

	
	@Schema(description="置顶结束时间")
	Date topEtime;

	
	@Schema(description="热搜开始时间")
	Date hotStime;

	
	@Schema(description="热搜结束时间")
	Date hotEtime;

	
	@Schema(description="加急开始时间")
	Date urgentStime;

	
	@Schema(description="加急结束时间")
	Date urgentEtime;

	
	@Schema(description="众包最终确定价格")
	BigDecimal quoteFinalAt;

	
	@Schema(description="省编号")
	String provinceId;

	
	@Schema(description="省名称")
	String provinceName;

	
	@Schema(description="区县编号")
	String areaId;

	
	@Schema(description="区县名称")
	String areaName;

	
	@Schema(description="0-草稿，1-正式")
	String status;

	
	@Schema(description="供应商投标截止时间")
	Date bidEtime;

	
	@Schema(description="服务编号-对应服务商中我的服务里面的服务编号")
	String serviceId;

	
	@Schema(description="最低信用等级")
	String creditId;

	
	@Schema(description="项目归属企业编号")
	String pbranchId;

	
	@Schema(description="儿子任务树-不包括孙子")
	Integer subTaskCnt;

	
	@Schema(description="儿子计划树-不包括孙子")
	Integer subPlanCnt;

	
	@Schema(description="权重值0-100之间，相对于上级来说每个儿子节点占比，每次更新，需要同步更改所有其它兄弟的权重，保证总权重不超过100.超过100时，其它兄弟的权重值自动按比例缩小")
	Integer weight;

	
	@Schema(description="计划进度=实际工时汇总/计划预估值*100,0-100之间")
	Integer initRate;

	
	@Schema(description="手工填写本级节点独立实际工时")
	BigDecimal mactWorkload;

	
	@Schema(description="实际工时汇总，=son_sum_mact_workload+mact_workload+act_workload")
	BigDecimal sumActWorkload;

	
	@Schema(description="下级手工工时汇总,不包括本级mact_wokload")
	BigDecimal sonSumMactWorkload;

	
	@Schema(description="下级预估工时汇总，不包括本级=所有儿子节点的init_workload之后")
	BigDecimal sonSumInitWorkload;

	
	@Schema(description="公开程度")
	String openLvl;

	
	@Schema(description="是否开启验证0-否，1是")
	String openValid;

	
	@Schema(description="验证结果0-未完成，1已完成")
	String validStatus;

	
	@Schema(description="关键指标编号")
	String kpiId;

	/**
	 *任务编号
	 **/
	public XmTask(String id) {
		this.id = id;
	}
    
    /**
     * 项目任务表
     **/
	public XmTask() {
	}

}