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
@TableName("xm_branch_task_type_state")
@ApiModel(description="按机构编号任务类型汇总")
public class XmBranchTaskTypeState  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="任务类型",allowEmptyValue=true,example="",allowableValues="")
	String taskType;

	
	@ApiModelProperty(notes="工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planWorkload;

	
	@ApiModelProperty(notes="预算金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planAmount;

	
	@ApiModelProperty(notes="实际完成工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actWorkload;

	
	@ApiModelProperty(notes="实际完成金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actAmount;

	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="业务日期yyyy-MM-dd型",allowEmptyValue=true,example="",allowableValues="")
	String bizDate;

	
	@ApiModelProperty(notes="计算日期",allowEmptyValue=true,example="",allowableValues="")
	Date calcTime;

	
	@ApiModelProperty(notes="外购资金预算",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planOuserAt;

	
	@ApiModelProperty(notes="内购资金预算",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planIuserAt;

	
	@ApiModelProperty(notes="实际外购成本",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actOutUserAt;

	
	@ApiModelProperty(notes="实际内购成本",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actInnerUserAt;

	
	@ApiModelProperty(notes="计划外购工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planOuserWorkload;

	
	@ApiModelProperty(notes="计划内购工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planIuserWorkload;

	
	@ApiModelProperty(notes="实际外购工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actOuserWorkload;

	
	@ApiModelProperty(notes="实际内购工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actIuserWorkload;

	
	@ApiModelProperty(notes="计划非人力成本",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal planNouserAt;

	
	@ApiModelProperty(notes="实际非人力成本",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actNouserAt;

	
	@ApiModelProperty(notes="机构名称",allowEmptyValue=true,example="",allowableValues="")
	String branchName;

	/**
	 *主键
	 **/
	public XmBranchTaskTypeState(String id) {
		this.id = id;
	}
    
    /**
     * 按机构编号任务类型汇总
     **/
	public XmBranchTaskTypeState() {
	}

}