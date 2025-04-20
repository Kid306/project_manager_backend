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
@TableName("xm_task_sbill")
@ApiModel(description="任务结算表-一个结算单对应1个或者多个任务，结算对象只能有一个")
public class XmTaskSbill  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="结算单据编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="结算单标题",allowEmptyValue=true,example="",allowableValues="")
	String title;

	
	@ApiModelProperty(notes="金额=工时表中结算金额之和",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal amt;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="部门编号",allowEmptyValue=true,example="",allowableValues="")
	String deptid;

	
	@ApiModelProperty(notes="相对方编号(机构写机构号，个人写个人编号)",allowEmptyValue=true,example="",allowableValues="")
	String cpId;

	
	@ApiModelProperty(notes="相对方名称（机构写机构名称，个人写个人名称）",allowEmptyValue=true,example="",allowableValues="")
	String cpName;

	
	@ApiModelProperty(notes="结算工作量=工时表中工时之和",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal workload;

	
	@ApiModelProperty(notes="业务月份yyyy-MM",allowEmptyValue=true,example="",allowableValues="")
	String bizMonth;

	
	@ApiModelProperty(notes="业务日期yyyy-MM-dd",allowEmptyValue=true,example="",allowableValues="")
	String bizDate;

	
	@ApiModelProperty(notes="结算流程状态：0初始1审批中2审批通过3审批不通过4流程取消或者删除",allowEmptyValue=true,example="",allowableValues="")
	String bizFlowState;

	
	@ApiModelProperty(notes="结算流程实例",allowEmptyValue=true,example="",allowableValues="")
	String bizProcInstId;

	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;

	
	@ApiModelProperty(notes="0-待提交，1-已提交，2-审核已通过，3-待开票，4-已开票待付款，5-已付款",allowEmptyValue=true,example="",allowableValues="")
	String status;

	
	@ApiModelProperty(notes="最后审核意见",allowEmptyValue=true,example="",allowableValues="")
	String fmsg;

	
	@ApiModelProperty(notes="项目编号",allowEmptyValue=true,example="",allowableValues="")
	String projectId;

	
	@ApiModelProperty(notes="项目名称",allowEmptyValue=true,example="",allowableValues="")
	String projectName;

	
	@ApiModelProperty(notes="结算人数",allowEmptyValue=true,example="",allowableValues="")
	Integer userCnt;

	
	@ApiModelProperty(notes="相对方类型1-个人，2-企业",allowEmptyValue=true,example="",allowableValues="")
	String cpType;

	/**
	 *结算单据编号
	 **/
	public XmTaskSbill(String id) {
		this.id = id;
	}
    
    /**
     * 任务结算表-一个结算单对应1个或者多个任务，结算对象只能有一个
     **/
	public XmTaskSbill() {
	}

}