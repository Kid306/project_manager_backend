package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.mdp.core.dao.annotation.TableIds;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * @author 唛盟开源 code-gen
 * @since 2025-4-9
 */
@Data
@TableName("xm_kpi")
@Schema(description="项目或任务关键指标考核")
public class XmKpi  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="主键,主键")
	String id;

	
	@Schema(description="项目编号")
	String projectId;

	
	@Schema(description="机构编码")
	String branchId;

	
	@Schema(description="指标编号")
	String kpiIndex;

	
	@Schema(description="指标名称")
	String kpiName;

	
	@Schema(description="得分0~100分")
	Integer score;

	
	@Schema(description="评分日期")
	Date scoreDate;

	
	@Schema(description="流程状态")
	String bizFlowState;

	
	@Schema(description="流程实例编号")
	String bizProcInstId;

	
	@Schema(description="kpi当前值")
	String kpiValue;

	
	@Schema(description="备注-要求，产物等描述")
	String remark;

	
	@Schema(description="考核方式1每月2每季度3每半年4每年9一次性")
	String calcType;

	
	@Schema(description="下次考核开始时间")
	Date nextCalcDate;

	
	@Schema(description="组织类型")
	String ktype;

	
	@Schema(description="成果附件地址、成果描述等")
	String kresult;

	
	@Schema(description="状态0-未达成，1-已达成")
	String kstatus;

	
	@Schema(description="是否附件")
	String kfile;

	
	@Schema(description="kpi指标分类，如预算类，成本类，进度类，利润类，字典kpk_class")
	String kclass;

	
	@Schema(description="审核人编号")
	String kadmUserid;

	
	@Schema(description="审核人名称")
	String kadmUsername;

	
	@Schema(description="负责人编号")
	String kselfUserid;

	
	@Schema(description="负责人名称")
	String kselfUsername;

	
	@Schema(description="自评时间")
	Date kselfTime;

	
	@Schema(description="审核时间")
	Date kadmTime;

	
	@Schema(description="目标值,支持一般运算表达式，如:1000>${kpIValue}>100")
	String targetValue;

	
	@Schema(description="数据来源字典xm_data_link,多选，逗号分隔")
	String dataLink;

	
	@Schema(description="上级指标编号")
	String parentId;

	
	@Schema(description="权重，相对上级的权重，同一个上级，所有权重不得大于100")
	Integer weight;

	
	@Schema(description="部门编号")
	String deptid;

	
	@Schema(description="儿子节点个数")
	Integer childrenCnt;

	
	@Schema(description="进度汇总方式1-自动，2-手工填写")
	String rateType;

	
	@Schema(description="父级id逗号分割，最后一个为本节点节点编号,以,号结尾")
	String pidPaths;

	
	@Schema(description="考核周期开始时间")
	Date startTime;

	
	@Schema(description="考核周期结束时间")
	Date endTime;

	
	@Schema(description="自评得分")
	Integer kselfScore;

	
	@Schema(description="前置指标编号，最多3个，逗号分隔")
	String preIds;

	
	@Schema(description="复评状态0-待提交，1-待复评通过，2-复评不通过,3.复评通过")
	String kadmStatus;

	
	@Schema(description="红色预警规则,支持一般运算表达式，如:1000>${kpIValue}>100")
	String redRule;

	
	@Schema(description="橙色预警规则,支持一般运算表达式，如:1000>${kpIValue}>100")
	String orangeRule;

	
	@Schema(description="黄色预警规则,支持一般运算表达式，如:1000>${kpIValue}>100")
	String yellowRule;

	
	@Schema(description="预警状态,0-初始，1-正常，2-黄色，3-橙色，4-红色")
	String kwarnStatus;

	/**
	 *主键
	 **/
	public XmKpi(String id) {
		this.id = id;
	}
    
    /**
     * 项目或任务关键指标考核
     **/
	public XmKpi() {
	}

}