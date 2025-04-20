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
 * @since 2023-11-6
 */
@Data
@TableName("xm_record_visit")
@ApiModel(description="重要页面访问记录")
public class XmRecordVisit  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="日志编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="操作人id",allowEmptyValue=true,example="",allowableValues="")
	String operUserid;

	
	@ApiModelProperty(notes="操作人名字",allowEmptyValue=true,example="",allowableValues="")
	String operUsername;

	
	@ApiModelProperty(notes="操作时间",allowEmptyValue=true,example="",allowableValues="")
	Date operTime;

	
	@ApiModelProperty(notes="对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6",allowEmptyValue=true,example="",allowableValues="")
	String objType;

	
	@ApiModelProperty(notes="操作的id",allowEmptyValue=true,example="",allowableValues="")
	String action;

	
	@ApiModelProperty(notes="备注-只描述新旧值之间的变化",allowEmptyValue=true,example="",allowableValues="")
	String remarks;

	
	@ApiModelProperty(notes="全局根踪号，用于跟踪日志",allowEmptyValue=true,example="",allowableValues="")
	String gloNo;

	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="ip地址",allowEmptyValue=true,example="",allowableValues="")
	String ip;

	
	@ApiModelProperty(notes="业务主键编号",allowEmptyValue=true,example="",allowableValues="")
	String bizId;

	
	@ApiModelProperty(notes="对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号",allowEmptyValue=true,example="",allowableValues="")
	String pbizId;

	
	@ApiModelProperty(notes="对象名称",allowEmptyValue=true,example="",allowableValues="")
	String bizName;

	/**
	 *日志编号
	 **/
	public XmRecordVisit(String id) {
		this.id = id;
	}
    
    /**
     * 重要页面访问记录
     **/
	public XmRecordVisit() {
	}

}