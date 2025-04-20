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
 * @since 2023-9-22
 */
@Data
@TableName("sys_user_tpa_invite")
@ApiModel(description="第三方邀请加入流水表，send_branch_id+join_userid唯一索引")
public class UserTpaInvite  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="邀请编号，带到邀请链接中的state字段，通过该邀请码反查邀请信息,主键",allowEmptyValue=true,example="",allowableValues="")
	String inviteId;

	
	@ApiModelProperty(notes="被邀请的用户编号，对应sys_user.userid",allowEmptyValue=true,example="",allowableValues="")
	String joinUserid;

	
	@ApiModelProperty(notes="邀请加入的企业编号",allowEmptyValue=true,example="",allowableValues="")
	String sendBranchId;

	
	@ApiModelProperty(notes="发起邀请的用户编号",allowEmptyValue=true,example="",allowableValues="")
	String sendUserid;

	
	@ApiModelProperty(notes="邀请加入的企业编号",allowEmptyValue=true,example="",allowableValues="")
	String sendBranchName;

	
	@ApiModelProperty(notes="发起邀请的用户名",allowEmptyValue=true,example="",allowableValues="")
	String sendUsername;

	
	@ApiModelProperty(notes="邀请时间",allowEmptyValue=true,example="",allowableValues="")
	Date sendTime;

	
	@ApiModelProperty(notes="邀请状态，字典invite_state，0-发起，1-待客户扫码确认，2-已加入",allowEmptyValue=true,example="",allowableValues="")
	String inviteState;

	
	@ApiModelProperty(notes="邀请场景，1-裸邦，创建个人虚拟企业及账户",allowEmptyValue=true,example="",allowableValues="")
	String inviteScene;

	
	@ApiModelProperty(notes="邀请类型，1-微信-扫码，2-手机号码+短信验证码，3-邮件+验证码，4-小程序",allowEmptyValue=true,example="",allowableValues="")
	String inviteType;

	
	@ApiModelProperty(notes="邀请对象类型，1-指定用户编号，0-不指定用户编号",allowEmptyValue=true,example="",allowableValues="")
	String objType;

	
	@ApiModelProperty(notes="被邀请的用户名称",allowEmptyValue=true,example="",allowableValues="")
	String joinUsername;

	/**
	 *邀请编号，带到邀请链接中的state字段，通过该邀请码反查邀请信息
	 **/
	public UserTpaInvite(String inviteId) {
		this.inviteId = inviteId;
	}
    
    /**
     * 第三方邀请加入流水表，send_branch_id+join_userid唯一索引
     **/
	public UserTpaInvite() {
	}

}