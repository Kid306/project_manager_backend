package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
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
@TableName("xm_test_plan_case")
@ApiModel(description="测试计划与用例关系表")
public class XmTestPlanCase  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="测试用例编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String caseId;
    @TableIds
	
    @ApiModelProperty(notes="计划编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String planId;

	
	@ApiModelProperty(notes="bug数目",allowEmptyValue=true,example="",allowableValues="")
	Integer bugs;

	
	@ApiModelProperty(notes="执行人",allowEmptyValue=true,example="",allowableValues="")
	String execUserid;

	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="0-未测，1-通过，2-受阻，3-忽略，4-失败",allowEmptyValue=true,example="",allowableValues="")
	String execStatus;

	
	@ApiModelProperty(notes="执行人姓名",allowEmptyValue=true,example="",allowableValues="")
	String execUsername;

	
	@ApiModelProperty(notes="优先级",allowEmptyValue=true,example="",allowableValues="")
	String priority;

	
	@ApiModelProperty(notes="执行备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="测试步骤",allowEmptyValue=true,example="",allowableValues="")
	String testStep;

	
	@ApiModelProperty(notes="项目编号",allowEmptyValue=true,example="",allowableValues="")
	String projectId;

	
	@ApiModelProperty(notes="预算工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal budgetWorkload;

	
	@ApiModelProperty(notes="实际工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actWorkload;

	
	@ApiModelProperty(notes="原估工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal initWorkload;

	
	@ApiModelProperty(notes="执行日期，以执行状态变更日期为准yyyy-MM-dd型",allowEmptyValue=true,example="",allowableValues="")
	String execDate;

	
	@ApiModelProperty(notes="执行类型0-手工，1-自动化",allowEmptyValue=true,example="",allowableValues="")
	String execType;

	
	@ApiModelProperty(notes="归属产品",allowEmptyValue=true,example="",allowableValues="")
	String productId;

	@ApiModelProperty(notes="进度0-100",allowEmptyValue=true,example="",allowableValues="")
	Integer execRate;

	/**
	 *测试用例编号,计划编号
	 **/
	public XmTestPlanCase(String caseId,String planId) {
		this.caseId = caseId;
		this.planId = planId;
	}
    
    /**
     * 测试计划与用例关系表
     **/
	public XmTestPlanCase() {
	}

}