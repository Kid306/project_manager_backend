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
@TableName("xm_question_handle")
@ApiModel(description="xm_question_handle")
public class XmQuestionHandle  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="处理人编号",allowEmptyValue=true,example="",allowableValues="")
	String handlerUserid;

	
	@ApiModelProperty(notes="处理人",allowEmptyValue=true,example="",allowableValues="")
	String handlerUsername;

	
	@ApiModelProperty(notes="解决方案:",allowEmptyValue=true,example="",allowableValues="")
	String handleSolution;

	
	@ApiModelProperty(notes="回执信息",allowEmptyValue=true,example="",allowableValues="")
	String receiptMessage;

	
	@ApiModelProperty(notes="回执时间",allowEmptyValue=true,example="",allowableValues="")
	Date receiptTime;

	
	@ApiModelProperty(notes="=bugStatus",allowEmptyValue=true,example="",allowableValues="")
	String handleStatus;

	
	@ApiModelProperty(notes="当前流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String bizProcInstId;

	
	@ApiModelProperty(notes="当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除",allowEmptyValue=true,example="",allowableValues="")
	String bizFlowState;

	
	@ApiModelProperty(notes="问题编号",allowEmptyValue=true,example="",allowableValues="")
	String questionId;

	
	@ApiModelProperty(notes="最后更新日期",allowEmptyValue=true,example="",allowableValues="")
	Date lastUpdateTime;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date createTime;

	
	@ApiModelProperty(notes="实际工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actWorkload;

	
	@ApiModelProperty(notes="实际金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actCostAmount;

	
	@ApiModelProperty(notes="链接地址列表逗号分隔",allowEmptyValue=true,example="",allowableValues="")
	String urls;

	
	@ApiModelProperty(notes="指派给谁",allowEmptyValue=true,example="",allowableValues="")
	String targetUserid;

	
	@ApiModelProperty(notes="指派给谁",allowEmptyValue=true,example="",allowableValues="")
	String targetUsername;


	@ApiModelProperty(notes="操作人编号",allowEmptyValue=true,example="",allowableValues="")
	String operUserid;


	@ApiModelProperty(notes="原状态",allowEmptyValue=true,example="",allowableValues="")
	String oldStatus;


	@ApiModelProperty(notes="操作人姓名",allowEmptyValue=true,example="",allowableValues="")
	String operUsername;

	/**
	 *主键
	 **/
	public XmQuestionHandle(String id) {
		this.id = id;
	}
    
    /**
     * xm_question_handle
     **/
	public XmQuestionHandle() {
	}

}