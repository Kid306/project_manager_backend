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
@TableName("xm_task_comment")
@ApiModel(description="档案评论表")
public class XmTaskComment  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="评论人",allowEmptyValue=true,example="",allowableValues="")
	String userid;

	
	@ApiModelProperty(notes="评论人姓名",allowEmptyValue=true,example="",allowableValues="")
	String username;

	
	@ApiModelProperty(notes="星级",allowEmptyValue=true,example="",allowableValues="")
	String star;

	
	@ApiModelProperty(notes="时间",allowEmptyValue=true,example="",allowableValues="")
	Date cdate;

	
	@ApiModelProperty(notes="需求编号",allowEmptyValue=true,example="",allowableValues="")
	String taskId;

	
	@ApiModelProperty(notes="上级评论",allowEmptyValue=true,example="",allowableValues="")
	String pid;

	
	@ApiModelProperty(notes="点赞数量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal ups;

	
	@ApiModelProperty(notes="是否显示0否1是",allowEmptyValue=true,example="",allowableValues="")
	String isShow;

	
	@ApiModelProperty(notes="回复用户编号",allowEmptyValue=true,example="",allowableValues="")
	String toUserid;

	
	@ApiModelProperty(notes="回复用户名",allowEmptyValue=true,example="",allowableValues="")
	String toUsername;

	
	@ApiModelProperty(notes="层级0,1,2,3,4",allowEmptyValue=true,example="",allowableValues="")
	String lvl;

	
	@ApiModelProperty(notes="评论内容",allowEmptyValue=true,example="",allowableValues="")
	String context;

	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="ip地址",allowEmptyValue=true,example="",allowableValues="")
	String ip;

	
	@ApiModelProperty(notes="城市编号",allowEmptyValue=true,example="",allowableValues="")
	String cityId;

	
	@ApiModelProperty(notes="城市名称",allowEmptyValue=true,example="",allowableValues="")
	String cityName;

	
	@ApiModelProperty(notes="状态0未审核，1已审核，3审核不通过",allowEmptyValue=true,example="",allowableValues="")
	String status;

	
	@ApiModelProperty(notes="儿子节点数量",allowEmptyValue=true,example="",allowableValues="")
	Integer childNums;

	/**
	 *主键
	 **/
	public XmTaskComment(String id) {
		this.id = id;
	}
    
    /**
     * 档案评论表
     **/
	public XmTaskComment() {
	}

}