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
@TableName("sys_user_tpa")
@ApiModel(description="第三方系统向我方开放的用户列表")
public class UserTpa  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="帐户加密后的编号，用于对第三方系统进行开放,主键",allowEmptyValue=true,example="",allowableValues="")
	String openid;

	
	@ApiModelProperty(notes="全局唯一编号",allowEmptyValue=true,example="",allowableValues="")
	String unionid;

	
	@ApiModelProperty(notes="是否被锁定",allowEmptyValue=true,example="",allowableValues="")
	String locked;

	
	@ApiModelProperty(notes="启用日期",allowEmptyValue=true,example="",allowableValues="")
	Date startdate;

	
	@ApiModelProperty(notes="昵称",allowEmptyValue=true,example="",allowableValues="")
	String nickname;

	
	@ApiModelProperty(notes="用户名称",allowEmptyValue=true,example="",allowableValues="")
	String username;

	
	@ApiModelProperty(notes="移动电话号码",allowEmptyValue=true,example="",allowableValues="")
	String phoneno;

	
	@ApiModelProperty(notes="国家",allowEmptyValue=true,example="",allowableValues="")
	String country;

	
	@ApiModelProperty(notes="城市",allowEmptyValue=true,example="",allowableValues="")
	String city;

	
	@ApiModelProperty(notes="头像地址",allowEmptyValue=true,example="",allowableValues="")
	String headimgurl;

	
	@ApiModelProperty(notes="省份",allowEmptyValue=true,example="",allowableValues="")
	String province;

	
	@ApiModelProperty(notes="前端应用系统编号",allowEmptyValue=true,example="",allowableValues="")
	String mdpAppid;

	
	@ApiModelProperty(notes="第三方登录应用系统编号",allowEmptyValue=true,example="",allowableValues="")
	String appid;

	
	@ApiModelProperty(notes="授权编号",allowEmptyValue=true,example="",allowableValues="")
	String authId;

	
	@ApiModelProperty(notes="biz_type",allowEmptyValue=true,example="",allowableValues="")
	String bizType;

	
	@ApiModelProperty(notes="性别",allowEmptyValue=true,example="",allowableValues="")
	String gender;

	
	@ApiModelProperty(notes="第三方应用类型，字典tpa_app_type,1-微信公众号，2-小程序，3-微信开放平台",allowEmptyValue=true,example="",allowableValues="")
	String appType;

	/**
	 *帐户加密后的编号，用于对第三方系统进行开放
	 **/
	public UserTpa(String openid) {
		this.openid = openid;
	}
    
    /**
     * 第三方系统向我方开放的用户列表
     **/
	public UserTpa() {
	}

}