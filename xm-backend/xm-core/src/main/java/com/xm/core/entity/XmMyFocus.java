package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_my_focus")
@ApiModel(description="我关注的项目或者任务")
public class XmMyFocus  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="用户编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String userid;
    @TableIds
	
    @ApiModelProperty(notes="关注的对象主键,主键",allowEmptyValue=true,example="",allowableValues="")
    String bizId;
    @TableIds
	
    @ApiModelProperty(notes="对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String pbizId;

	
	@ApiModelProperty(notes="用户名称",allowEmptyValue=true,example="",allowableValues="")
	String username;

	
	@ApiModelProperty(notes="对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6",allowEmptyValue=true,example="",allowableValues="")
	String focusType;

	
	@ApiModelProperty(notes="任务名称",allowEmptyValue=true,example="",allowableValues="")
	String bizName;

	
	@ApiModelProperty(notes="对象上级名称",allowEmptyValue=true,example="",allowableValues="")
	String pbizName;

	
	@ApiModelProperty(notes="关注时间",allowEmptyValue=true,example="",allowableValues="")
	Date ftime;

	
	@ApiModelProperty(notes="用户归属机构",allowEmptyValue=true,example="",allowableValues="")
	String ubranchId;

	/**
	 *用户编号,关注的对象主键,对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号
	 **/
	public XmMyFocus(String userid,String bizId,String pbizId) {
		this.userid = userid;
		this.bizId = bizId;
		this.pbizId = pbizId;
	}
    
    /**
     * 我关注的项目或者任务
     **/
	public XmMyFocus() {
	}

}