package  com.mdp.sys.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-29
 */
@Data
@TableName("sys_notify_msg")
@ApiModel(description="个人消息通知")
public class NotifyMsg  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="消息编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="操作人id",allowEmptyValue=true,example="",allowableValues="")
	String sendUserid;

	
	@ApiModelProperty(notes="操作人名字",allowEmptyValue=true,example="",allowableValues="")
	String sendUsername;

	
	@ApiModelProperty(notes="操作时间",allowEmptyValue=true,example="",allowableValues="")
	Date operTime;

	
	@ApiModelProperty(notes="对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代-6/团队-7/关注用户-8/点赞文章-9/评论文章-10/收藏文章-11/12-用户注销/13-商务合作",allowEmptyValue=true,example="",allowableValues="")
	String objType;

	
	@ApiModelProperty(notes="备注-完整描述",allowEmptyValue=true,example="",allowableValues="")
	String msg;

	
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

	
	@ApiModelProperty(notes="接收用户编号",allowEmptyValue=true,example="",allowableValues="")
	String toUserid;

	
	@ApiModelProperty(notes="接收用户名称",allowEmptyValue=true,example="",allowableValues="")
	String toUsername;

	
	@ApiModelProperty(notes="是否已读",allowEmptyValue=true,example="",allowableValues="")
	String hadRead;

	
	@ApiModelProperty(notes="跳转地址",allowEmptyValue=true,example="",allowableValues="")
	String url;

	/**
	 *消息编号
	 **/
	public NotifyMsg(String id) {
		this.id = id;
	}
    
    /**
     * 个人消息通知
     **/
	public NotifyMsg() {
	}

}