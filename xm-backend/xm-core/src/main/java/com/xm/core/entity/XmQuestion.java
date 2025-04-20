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
 * @since 2025-4-10
 */
@Data
@TableName("xm_question")
@Schema(description="缺陷列表")
public class XmQuestion  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="问题编号,主键")
	String id;

	
	@Schema(description="问题标题")
	String name;

	
	@Schema(description="项目编号")
	String projectId;

	
	@Schema(description="项目名称")
	String projectName;

	
	@Schema(description="测试案例编号")
	String caseId;

	
	@Schema(description="测试案例名称")
	String caseName;

	
	@Schema(description="到期时间")
	Date endTime;

	
	@Schema(description="提出人编号")
	String askUserid;

	
	@Schema(description="提出人")
	String askUsername;

	
	@Schema(description="处理人编号")
	String handlerUserid;

	
	@Schema(description="处理人")
	String handlerUsername;

	
	@Schema(description="优先级别1-非常紧急，2-紧急，3-一般紧急，4-低")
	String priority;

	
	@Schema(description="解决方案:")
	String solution;

	
	@Schema(description="问题描述")
	String description;

	
	@Schema(description="问题创建人编号")
	String createUserid;

	
	@Schema(description="问题创建人")
	String createUsername;

	
	@Schema(description="创建时间")
	Date createTime;

	
	@Schema(description="缺陷状态1|新提交2|处理中3|已修复4|已挂起5|已提测6|已拒绝7|已解决8|已关闭9|重新打开")
	String bugStatus;

	
	@Schema(description="当前流程实例编号")
	String bizProcInstId;

	
	@Schema(description="当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除")
	String bizFlowState;

	
	@Schema(description="故事编号")
	String menuId;

	
	@Schema(description="故事名称")
	String menuName;

	
	@Schema(description="预估工时单位人时")
	BigDecimal budgetWorkload;

	
	@Schema(description="预估成本金额")
	BigDecimal budgetAt;

	
	@Schema(description="实际工时（取报工实际工时汇总）")
	BigDecimal actWorkload;

	
	@Schema(description="实际总金额")
	BigDecimal actAt;

	
	@Schema(description="期望结果")
	String expectResult;

	
	@Schema(description="测试步骤[{id:,op:,eresult:,aresult:,tcode:测试结果代码}]")
	String opStep;

	
	@Schema(description="当前结果")
	String currResult;

	
	@Schema(description="相关需求")
	String refRequire;

	
	@Schema(description="严重程度1、2、3、4，分别对应：致命缺陷、严重缺陷、普通缺陷、轻微缺陷")
	String bugSeverity;

	
	@Schema(description="缺陷类型1、2、3、4，分别对应：代码错误、低级缺陷、设计缺陷、配置相关、安全相关、性能问题、其他")
	String bugType;

	
	@Schema(description="标签id列表逗号分隔")
	String tagIds;

	
	@Schema(description="标签名称列表逗号分隔")
	String tagNames;

	
	@Schema(description="链接地址列表逗号分隔")
	String urls;

	
	@Schema(description="最后更新时间")
	Date ltime;

	
	@Schema(description="问题类型2-风险、1-功能问题、3-普通咨询、（暂时不用这个字段了）")
	String qtype;

	
	@Schema(description="关联的案例执行编号，作废")
	String caseExecId;

	
	@Schema(description="最后更新说明")
	String remarks;

	
	@Schema(description="产品编号")
	String productId;

	
	@Schema(description="复现频率1-必现，2-大概率复现，3-小概率复现，4-仅出现一次")
	String repRate;

	
	@Schema(description="版本号")
	String verNum;

	
	@Schema(description="访问路径/斜杠分割")
	String vpath;

	
	@Schema(description="发布版本")
	String pverNum;

	
	@Schema(description="原因分析")
	String bugReason;

	
	@Schema(description="进度0-100")
	Integer rate;

	
	@Schema(description="原始预估工作量，budget_workload发生变化后，进行备份")
	BigDecimal initWorkload;

	
	@Schema(description="是否众包0否1是")
	String taskOut;

	
	@Schema(description="任务编号-可以在任务下直接创建bug-废弃，不用了")
	String taskId;

	
	@Schema(description="功能菜单编号")
	String funcId;

	
	@Schema(description="功能菜单名称")
	String funcName;

	
	@Schema(description="上级名称逗号分割")
	String funcPnames;

	
	@Schema(description="测试计划编号")
	String planId;

	
	@Schema(description="测试库编号")
	String casedbId;

	
	@Schema(description="产品或者项目归属企业编号")
	String pbranchId;

	
	@Schema(description="排序序号")
	String sortNo;

	
	@Schema(description="风险编号")
	String riskId;

	/**
	 *问题编号
	 **/
	public XmQuestion(String id) {
		this.id = id;
	}
    
    /**
     * 缺陷列表
     **/
	public XmQuestion() {
	}

}