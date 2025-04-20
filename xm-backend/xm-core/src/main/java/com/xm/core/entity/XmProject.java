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
 * @since 2025-4-12
 */
@Data
@TableName("xm_project")
@Schema(description="项目表")
public class XmProject  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="项目编号,主键")
	String id;

	
	@Schema(description="项目代号")
	String code;

	
	@Schema(description="项目名称")
	String name;

	
	@Schema(description="项目类型")
	String xmType;

	
	@Schema(description="项目开始时间")
	Date startTime;

	
	@Schema(description="项目结束时间")
	Date endTime;

	
	@Schema(description="紧急程度")
	String urgent;

	
	@Schema(description="优先程度")
	String priority;

	
	@Schema(description="项目描述")
	String description;

	
	@Schema(description="项目创建人编号")
	String createUserid;

	
	@Schema(description="项目创建人")
	String createUsername;

	
	@Schema(description="创建时间")
	Date createTime;

	
	@Schema(description="项目考核")
	String assess;

	
	@Schema(description="考核备注")
	String assessRemarks;

	
	@Schema(description="0|初始1|售前2|立项中3|实施中4|暂停中5|结项中6|已结项7|售后8|已完成9|已关闭")
	String status;

	
	@Schema(description="机构编号")
	String branchId;

	
	@Schema(description="总预算")
	BigDecimal planTotalCost;

	
	@Schema(description="当前流程实例编号")
	String bizProcInstId;

	
	@Schema(description="当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除")
	String bizFlowState;

	
	@Schema(description="非人力成本总预算-应该大于或等于阶段计划非人力总成本")
	BigDecimal planNouserAt;

	
	@Schema(description="内部人力成本总预算-应该大于或等于阶段计划内部人力总成本")
	BigDecimal planIuserAt;

	
	@Schema(description="外购人力成本总预算-应该大于或等于阶段计划外购人力总成本")
	BigDecimal planOuserAt;

	
	@Schema(description="是否锁定整个项目不允许变化0否1是")
	String locked;

	
	@Schema(description="基线时间")
	Date baseTime;

	
	@Schema(description="基线备注")
	String baseRemark;

	
	@Schema(description="基线主键")
	String baselineId;

	
	@Schema(description="总预算工作量-应该大于或等于阶段计划总工作量")
	BigDecimal planWorkload;

	
	@Schema(description="总预计收款金额")
	BigDecimal totalReceivables;

	
	@Schema(description="预估毛利率")
	BigDecimal budgetMarginRate;

	
	@Schema(description="合同总金额")
	BigDecimal contractAmt;

	
	@Schema(description="内部人力成本单价元/人时")
	BigDecimal planIuserPrice;

	
	@Schema(description="外购人力成本单价元/人时")
	BigDecimal planOuserPrice;

	
	@Schema(description="外购人数")
	Integer planOuserCnt;

	
	@Schema(description="内部人数")
	Integer planIuserCnt;

	
	@Schema(description="预计工作小时数目")
	Integer planWorkingHours;

	
	@Schema(description="税率0-100之间")
	BigDecimal taxRate;

	
	@Schema(description="内部人力总工作量-应该大于或等于阶段计划内部人力总成本")
	BigDecimal planIuserWorkload;

	
	@Schema(description="外购人力总工作量-应该大于或等于阶段计划外购人力总成本")
	BigDecimal planOuserWorkload;

	
	@Schema(description="关联模板编号")
	String fromTplId;

	
	@Schema(description="是否进行预算控制，计划中一级计划总预算大于项目预算则拒绝添加计划,一般用于瀑布型项目")
	String budgetCtrl;

	
	@Schema(description="部门编号")
	String deptid;

	
	@Schema(description="是否对外公开0-完全不可见，1-仅本司人员可见，2-关联人员可见（众包-外包-招投标）,3-本部门上级及下属部门可见,4-仅本部及上级可见，5-仅本部及下级可见,6-仅本部人员可见")
	String showOut;

	
	@Schema(description="是否为模板")
	String isTpl;

	
	@Schema(description="项目经理编号")
	String pmUserid;

	
	@Schema(description="项目经理名称")
	String pmUsername;

	
	@Schema(description="助理、副经理编号")
	String assUserid;

	
	@Schema(description="助理、副经理姓名")
	String assUsername;

	
	@Schema(description="主管领导编号")
	String admUserid;

	
	@Schema(description="主管领导姓名")
	String admUsername;

	
	@Schema(description="是否进行计划预算预警，计划预算超出项目预算既定额度进行预警")
	String budgetEarly;

	
	@Schema(description="计划是否进行实际金额控制，实际金额不能大于预算金额（大于预算金额不得结算）")
	String phaseActCtrl;

	
	@Schema(description="是否已删除0否1是")
	String del;

	
	@Schema(description="最后更新时间")
	Date ltime;

	
	@Schema(description="原状态，暂停时记录原状态，暂停恢复后把原状态恢复")
	String ostatus;

	
	@Schema(description="工作方式1-scrum、2-kanban")
	String workType;

	
	@Schema(description="报工方式0-无须报工，1-每日报工，2-工期内报工")
	String wtype;

	
	@Schema(description="超出预算金额多少金额进行预警，正数代表超出的额度，负数代表距离预算的额度")
	BigDecimal earlyAmt;

	
	@Schema(description="单个任务最大金额")
	BigDecimal maxTaskAmt;

	
	@Schema(description="任务是否必须严格关联用户故事，0不限制，1必须关联，2-完全不关联")
	String menuLink;

	
	@Schema(description="任务是否必须关联计划，0-不限制，1必须关联，2完全不关联")
	String phaseLink;

	
	@Schema(description="模板类型1-全域公开，2-本机构公开")
	String tplType;

	
	@Schema(description="权限码0,1,2,3,4,5,67,8,9，逗号分割共10位,不定长，暂时只启用前6个位第0位代表团队建立及成员管理及crud权限：")
	String qxCode;

	
	@Schema(description="部门编号全路径编号")
	String deptidPath;

	
	@Schema(description="标签号码,逗号分隔多个")
	String tagIds;

	
	@Schema(description="当前项目所处管理节点，字典projectPhasePlansXXX")
	String currPhase;

	
	@Schema(description="是否开启kpi,0-否，1-是，开启后项目结项时做统计kpi的达成情况")
	String openKpi;

	
	@Schema(description="kpi考核是否通过，0-否，1是")
	String kpiStatus;

	
	@Schema(description="实际开始")
	Date actStartTime;

	
	@Schema(description="实际结束时间")
	Date actEndTime;

	
	@Schema(description="需要审核的变更字段编码,逗号分隔，驼峰命名，如f1,f2,f3")
	String flowFields;

	
	@Schema(description="技术来源，字典tsrc，1-自由技术，2-引进新技术再创新，3-院校合作研发，4-其它企业技术")
	String tsrc;

	
	@Schema(description="成果,字典gain,1-新产品，2-新技术，3-新工艺")
	String gain;

	
	@Schema(description="立项说明")
	String setupRemark;

	
	@Schema(description="研发目标说明")
	String target;

	/**
	 *项目编号
	 **/
	public XmProject(String id) {
		this.id = id;
	}
    
    /**
     * 项目表
     **/
	public XmProject() {
	}

}