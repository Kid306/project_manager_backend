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
@TableName("xm_cost_record")
@Schema(description="项目实际成本费用")
public class XmCostRecord  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="主键,主键")
	String id;

	
	@Schema(description="项目编号")
	String projectId;

	
	@Schema(description="用户编号-费用主责人")
	String userid;

	
	@Schema(description="创建时间")
	Date ctime;

	
	@Schema(description="费用发放时间")
	Date sendTime;

	
	@Schema(description="用户名称")
	String username;

	
	@Schema(description="项目名称")
	String projectName;

	
	@Schema(description="备注")
	String remark;

	
	@Schema(description="任务编号")
	String taskId;

	
	@Schema(description="任务名称")
	String taskName;

	
	@Schema(description="科目编号")
	String subjectId;

	
	@Schema(description="费用归属周期开始日期")
	Date bizSdate;

	
	@Schema(description="费用归属周期结束日期")
	Date bizEdate;

	
	@Schema(description="实际成本金额")
	BigDecimal actAt;

	
	@Schema(description="成本类型0非人力1内部人力2外购人力,此表都是非人力")
	String costType;

	
	@Schema(description="业务归属月份yyyy-MM")
	String bizMonth;

	
	@Schema(description="业务归属日期yyyy-MM-dd")
	String bizDate;

	
	@Schema(description="科目名称")
	String subjectName;

	
	@Schema(description="用户归属机构")
	String ubranchId;

	
	@Schema(description="项目归属机构")
	String branchId;

	
	@Schema(description="当前流程实例编号")
	String instId;

	
	@Schema(description="当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除")
	String bizFlowState;

	
	@Schema(description="结算单编号")
	String refSbillId;

	
	@Schema(description="结算单明细编号")
	String refSbillSubId;

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

	@Schema(description="更新时间")
	Date ltime;

	/**
	 *主键
	 **/
	public XmCostRecord(String id) {
		this.id = id;
	}
    
    /**
     * 项目实际成本费用
     **/
	public XmCostRecord() {
	}

}