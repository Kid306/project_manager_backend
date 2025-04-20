package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 唛盟开源 code-gen
 * @since 2024-7-6
 */
@Data
@TableName("xm_budget_record")
@Schema(description="项目人力成本预算")
public class XmBudgetRecord  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="主键,主键")
	String id;

	
	@Schema(description="项目编号")
	String projectId;

	
	@Schema(description="项目成员编号")
	String userid;

	
	@Schema(description="预算金额/每月")
	BigDecimal budgetAt;

	
	@Schema(description="备注")
	String remark;

	
	@Schema(description="用户名")
	String username;

	
	@Schema(description="预算科目编号")
	String subjectId;

	
	@Schema(description="费用归属周期开始日期")
	Date bizSdate;

	
	@Schema(description="费用归属周期结束日期")
	Date bizEdate;

	
	@Schema(description="费用归属月份yyyy-mm")
	String bizMonth;

	
	@Schema(description="当前流程实例编号")
	String instId;

	
	@Schema(description="当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除")
	String bizFlowState;

	
	@Schema(description="成本类型0非人力1内部人力2外购人力")
	String costType;

	
	@Schema(description="科目名称")
	String subjectName;

	
	@Schema(description="项目归属机构编号")
	String branchId;

	
	@Schema(description="用户归属机构编号-也就是将来的结算对象")
	String ubranchId;

	
	@Schema(description="拆分时填原来的id")
	String pid;

	
	@Schema(description="任务编号")
	String taskId;


	@Schema(description="成本中心编号")
	String costCenterId;

	@Schema(description="创建人编号")
	String cuserid;

	@Schema(description="创建人姓名")
	String cusername;

	@Schema(description="更新人编号")
	String luserid;

	@Schema(description="更新人姓名")
	String lusername;

	@Schema(description="创建时间")
	Date ctime;

	@Schema(description="更新时间")
	Date ltime;

	/**
	 *主键
	 **/
	public XmBudgetRecord(String id) {
		this.id = id;
	}
    
    /**
     * 项目人力成本预算
     **/
	public XmBudgetRecord() {
	}

}