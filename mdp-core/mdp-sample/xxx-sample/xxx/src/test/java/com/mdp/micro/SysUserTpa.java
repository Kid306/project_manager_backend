package com.mdp.micro;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-22
 */
@Data
@TableName("sys_user_tpa")
@Schema(description="第三方系统向我方开放的用户列表")
public class SysUserTpa  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="帐户加密后的编号，用于对第三方系统进行开放,主键")
	String openid;

	
	@Schema(description="全局唯一编号")
	String unionid;

	
	@Schema(description="是否被锁定")
	String locked;

	
	@Schema(description="启用日期")
	Date startdate;

	
	@Schema(description="昵称")
	String nickname;

	
	@Schema(description="用户名称")
	String username;

	
	@Schema(description="移动电话号码")
	String phoneno;

	
	@Schema(description="国家")
	String country;

	
	@Schema(description="城市")
	String city;

	
	@Schema(description="头像地址")
	String headimgurl;

	
	@Schema(description="省份")
	String province;

	
	@Schema(description="前端应用系统编号")
	String mdpAppid;

	
	@Schema(description="第三方登录应用系统编号")
	String appid;

	
	@Schema(description="授权编号")
	String authId;

	
	@Schema(description="biz_type")
	String bizType;

	
	@Schema(description="性别")
	String gender;

	
	@Schema(description="第三方应用类型，字典tpa_app_type,1-微信公众号，2-小程序，3-微信开放平台")
	String appType;

	/**
	 *帐户加密后的编号，用于对第三方系统进行开放
	 **/
	public SysUserTpa(String openid) {
		this.openid = openid;
	}
    
    /**
     * 第三方系统向我方开放的用户列表
     **/
	public SysUserTpa() {
	}

}