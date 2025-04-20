package  com.mdp.sys.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-22
 */
@Data
@TableName("sys_user_tpa_invite_link")
@ApiModel(description="邀请对象响应流水表")
public class UserTpaInviteLink  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="邀请码-对应invite.invite_id,主键",allowEmptyValue=true,example="",allowableValues="")
    String inviteId;
    @TableIds
	
    @ApiModelProperty(notes="加入的用户,主键",allowEmptyValue=true,example="",allowableValues="")
    String joinUserid;
    @TableIds
	
    @ApiModelProperty(notes="对应sys_user_tpa.openid,主键",allowEmptyValue=true,example="",allowableValues="")
    String joinTpaId;

	
	@ApiModelProperty(notes="加入时间",allowEmptyValue=true,example="",allowableValues="")
	Date joinTime;

	
	@ApiModelProperty(notes="邀请类型，1-微信-扫码，2-手机号码+短信验证码，3-邮件+验证码，4-小程序",allowEmptyValue=true,example="",allowableValues="")
	String joinType;

	
	@ApiModelProperty(notes="加入的机构编号=invite.send_branch_id=join_userid的归属机构号",allowEmptyValue=true,example="",allowableValues="")
	String joinBranchId;

	/**
	 *邀请码-对应invite.invite_id,加入的用户,对应sys_user_tpa.openid
	 **/
	public UserTpaInviteLink(String inviteId,String joinUserid,String joinTpaId) {
		this.inviteId = inviteId;
		this.joinUserid = joinUserid;
		this.joinTpaId = joinTpaId;
	}
    
    /**
     * 邀请对象响应流水表
     **/
	public UserTpaInviteLink() {
	}

}