package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserValidCode所有属性名: <br>
 *	"userid","内部用户编号","valiCode","验证码","codeSendTime","验证码发送时间","codeEmail","验证码接收邮箱编号","codeScene","验证场景0-重置密码，1-注册验证，2-更换常用邮箱第一步验证旧邮箱,，3-更换常用邮箱第二步验证新邮箱，4-更换常用邮箱第一步验证旧邮箱,，5-更换常用邮箱第二步验证新邮箱，","codeValidTime","验证码验证时间","id","主键";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_user_valid_code")
@ApiModel(description="会员表（前端商城")
public class UserValidCode  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="内部用户编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;
	
	@ApiModelProperty(notes="验证码",allowEmptyValue=true,example="",allowableValues="")
	String valiCode;
	
	@ApiModelProperty(notes="验证码发送时间",allowEmptyValue=true,example="",allowableValues="")
	Date codeSendTime;
	
	@ApiModelProperty(notes="验证码接收邮箱编号",allowEmptyValue=true,example="",allowableValues="")
	String codeEmail;
	
	@ApiModelProperty(notes="验证场景0-重置密码，1-注册验证，2-更换常用邮箱第一步验证旧邮箱,，3-更换常用邮箱第二步验证新邮箱，4-更换常用邮箱第一步验证旧邮箱,，5-更换常用邮箱第二步验证新邮箱，",allowEmptyValue=true,example="",allowableValues="")
	String codeScene;
	
	@ApiModelProperty(notes="验证码验证时间",allowEmptyValue=true,example="",allowableValues="")
	Date codeValidTime;

	/**
	 *主键
	 **/
	public UserValidCode(String id) {
		this.id = id;
	}
    
    /**
     * 会员表（前端商城
     **/
	public UserValidCode() {
	}

}