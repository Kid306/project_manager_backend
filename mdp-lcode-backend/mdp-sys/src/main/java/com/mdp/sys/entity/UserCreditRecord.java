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
 * 实体 UserCreditRecord所有属性名: <br>
 *	"userid","用户编号","score","信用得分","remark","信用得分变化说明","bizId","引起变化的业务主键，比如任务编号等","bizType","引起编号的业务分类1-个人实名认证，2-企业认证，3-银行卡认证，4-手机号认证，5-邮箱认证，10-加入三保服务保障，11-完成平台任务，12-邀请用户实名注册，13-邀请用户完成任务。14-其它","direct","变化方向0-减，1-增加","ctime","变化时间","id","主键";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_user_credit_record")
@ApiModel(description="用户信用得分记录表")
public class UserCreditRecord  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="用户编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;
	
	@ApiModelProperty(notes="信用得分",allowEmptyValue=true,example="",allowableValues="")
	Integer score;
	
	@ApiModelProperty(notes="信用得分变化说明",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="引起变化的业务主键，比如任务编号等",allowEmptyValue=true,example="",allowableValues="")
	String bizId;
	
	@ApiModelProperty(notes="引起编号的业务分类1-个人实名认证，2-企业认证，3-银行卡认证，4-手机号认证，5-邮箱认证，10-加入三保服务保障，11-完成平台任务，12-邀请用户实名注册，13-邀请用户完成任务。14-其它",allowEmptyValue=true,example="",allowableValues="")
	String bizType;
	
	@ApiModelProperty(notes="变化方向0-减，1-增加",allowEmptyValue=true,example="",allowableValues="")
	String direct;
	
	@ApiModelProperty(notes="变化时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	/**
	 *主键
	 **/
	public UserCreditRecord(String id) {
		this.id = id;
	}
    
    /**
     * 用户信用得分记录表
     **/
	public UserCreditRecord() {
	}

}