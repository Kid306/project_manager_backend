package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_task_eval")
@ApiModel(description="xm_task_eval")
public class XmTaskEval  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="评价,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="评价类型1-雇主对服务商的评价，2-服务商对雇主的评价，3-组长对组员的评价",allowEmptyValue=true,example="",allowableValues="")
	String type;

	
	@ApiModelProperty(notes="工作速度0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer wspeed;

	
	@ApiModelProperty(notes="工作态度0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer wattit;

	
	@ApiModelProperty(notes="工作质量0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer wquality;

	
	@ApiModelProperty(notes="总体评价0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer totalStar;

	
	@ApiModelProperty(notes="评价内容",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="评价人编号",allowEmptyValue=true,example="",allowableValues="")
	String evalUserid;

	
	@ApiModelProperty(notes="评价人姓名",allowEmptyValue=true,example="",allowableValues="")
	String evalUsername;

	
	@ApiModelProperty(notes="被评价人编号",allowEmptyValue=true,example="",allowableValues="")
	String toUserid;

	
	@ApiModelProperty(notes="被评价人姓名",allowEmptyValue=true,example="",allowableValues="")
	String toUsername;

	
	@ApiModelProperty(notes="评价人归属机构",allowEmptyValue=true,example="",allowableValues="")
	String evalBranchId;

	
	@ApiModelProperty(notes="被评价人归属机构号",allowEmptyValue=true,example="",allowableValues="")
	String toBranchId;

	
	@ApiModelProperty(notes="任务编号",allowEmptyValue=true,example="",allowableValues="")
	String taskId;

	
	@ApiModelProperty(notes="评价时间",allowEmptyValue=true,example="",allowableValues="")
	Date evalTime;

	
	@ApiModelProperty(notes="付款及时度0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer paySpeed;

	
	@ApiModelProperty(notes="合作愉快度0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer coopHappy;

	/**
	 *评价
	 **/
	public XmTaskEval(String id) {
		this.id = id;
	}
    
    /**
     * xm_task_eval
     **/
	public XmTaskEval() {
	}

}