package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_task_sbill_detail")
@ApiModel(description="工时结算单明细-一个任务只允许结算一次，一次性结算完毕。任务必须已完工")
public class XmTaskSbillDetail  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="员工编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;

	
	@ApiModelProperty(notes="姓名",allowEmptyValue=true,example="",allowableValues="")
	String username;

	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="业务对象主键任务编号",allowEmptyValue=true,example="",allowableValues="")
	String taskId;

	
	@ApiModelProperty(notes="业务日期yyyy-MM-dd",allowEmptyValue=true,example="",allowableValues="")
	String bizDate;

	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="结算单据编号-来自task_sbill.id",allowEmptyValue=true,example="",allowableValues="")
	String sbillId;

	
	@ApiModelProperty(notes="结算提交时间",allowEmptyValue=true,example="",allowableValues="")
	Date stime;

	
	@ApiModelProperty(notes="结算状态0-无需结算，1-待结算2-已提交3-已通过4-已结算",allowEmptyValue=true,example="",allowableValues="")
	String sstatus;

	
	@ApiModelProperty(notes="工时对应金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal amt;

	
	@ApiModelProperty(notes="结算工时对应结算金额-根据结算方案计算结算金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal samt;

	
	@ApiModelProperty(notes="报工工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal workload;

	
	@ApiModelProperty(notes="归属项目",allowEmptyValue=true,example="",allowableValues="")
	String projectId;

	
	@ApiModelProperty(notes="结算工时，用于结算，默认=workload",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal sworkload;

	
	@ApiModelProperty(notes="月份yyyy-MM型",allowEmptyValue=true,example="",allowableValues="")
	String bizMonth;

	
	@ApiModelProperty(notes="任务预算金额-来自task表",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal budgetAt;

	
	@ApiModelProperty(notes="任务预算工时-来自task表",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal budgetWorkload;

	
	@ApiModelProperty(notes="任务初始工时-来自task表",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal initWorkload;

	
	@ApiModelProperty(notes="报价金额-来自task_execuser表",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal quoteAt;

	
	@ApiModelProperty(notes="报价工时-来自task_execuser表",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal quoteWorkload;

	
	@ApiModelProperty(notes="任务结算方案,来自task表、来自数字字典xmTaskSettleSchemel",allowEmptyValue=true,example="",allowableValues="")
	String sschemel;

	
	@ApiModelProperty(notes="工时单价，来自task表，根据task_out判断取内部还是外部单价",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal uniPrice;

	
	@ApiModelProperty(notes="报价结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date qendTime;

	
	@ApiModelProperty(notes="报价开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date qstartTime;

	
	@ApiModelProperty(notes="实际完工时间-来自task表",allowEmptyValue=true,example="",allowableValues="")
	Date actEndTime;

	
	@ApiModelProperty(notes="实际开始时间-来自task表",allowEmptyValue=true,example="",allowableValues="")
	Date actStartTime;

	
	@ApiModelProperty(notes="是否开启分享赚",allowEmptyValue=true,example="",allowableValues="")
	String oshare;

	
	@ApiModelProperty(notes="分享赚佣金",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal shareFee;

	
	@ApiModelProperty(notes="平台服务费",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal sfee;

	
	@ApiModelProperty(notes="服务费率",allowEmptyValue=true,example="",allowableValues="")
	Integer sfeeRate;

	
	@ApiModelProperty(notes="相对方编号(机构写机构号，个人写个人编号)",allowEmptyValue=true,example="",allowableValues="")
	String cpId;

	
	@ApiModelProperty(notes="相对方名称（机构写机构名称，个人写个人名称）",allowEmptyValue=true,example="",allowableValues="")
	String cpName;

	
	@ApiModelProperty(notes="相对方类型1-个人，2-企业",allowEmptyValue=true,example="",allowableValues="")
	String cpType;

	
	@ApiModelProperty(notes="推荐人编号",allowEmptyValue=true,example="",allowableValues="")
	String distUserid;

	
	@ApiModelProperty(notes="推荐人姓名",allowEmptyValue=true,example="",allowableValues="")
	String distUsername;

	
	@ApiModelProperty(notes="分享码",allowEmptyValue=true,example="",allowableValues="")
	String shareKey;

	
	@ApiModelProperty(notes="是否外购0否1是",allowEmptyValue=true,example="",allowableValues="")
	String taskOut;

	
	@ApiModelProperty(notes="是否众包",allowEmptyValue=true,example="",allowableValues="")
	String crowd;

	
	@ApiModelProperty(notes="其它费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal othFee;

	
	@ApiModelProperty(notes="费用说明",allowEmptyValue=true,example="",allowableValues="")
	String feeRemark;

	
	@ApiModelProperty(notes="该任务在本次结算前已结算的金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal tactAt;

	
	@ApiModelProperty(notes="任务名称",allowEmptyValue=true,example="",allowableValues="")
	String taskName;

	
	@ApiModelProperty(notes="费用科目编号",allowEmptyValue=true,example="",allowableValues="")
	String subjectId;

	
	@ApiModelProperty(notes="费用科目名称",allowEmptyValue=true,example="",allowableValues="")
	String subjectName;

	
	@ApiModelProperty(notes="项目归属机构号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	/**
	 *主键
	 **/
	public XmTaskSbillDetail(String id) {
		this.id = id;
	}
    
    /**
     * 工时结算单明细-一个任务只允许结算一次，一次性结算完毕。任务必须已完工
     **/
	public XmTaskSbillDetail() {
	}

}