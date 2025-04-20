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
 * 实体 UserValidInfo所有属性名: <br>
 *	"userid","用户编号，如果是机构就是机构编号对应的用户编号","idFront","身份证正面","idBack","身份证反面","idHold","手持身份证","bizLicense","营业执照","oths","其它逗号分割多个","idNo","身份证号","idEtime","身份证到期日期","uscc","统一信用代码号税号营业执照号等","actBname","机构实名名称","actUname","实名用户名称或者法人名称","ctime","新增时间","ltime","修改时间","flowState","审核状态0-初始，1-审核中，2-通过，3-拒绝","isOrg","是否为机构，0-否，1-是，机构指企业认证，个人指实名认证","validRemarks","审核原因说明","branchId","归属机构编号";<br>
 * 当前主键(包括多主键):<br>
 *	userid;<br>
 */
 @Data
@TableName("sys_user_valid_info")
@ApiModel(description="用户实名验证资料库")
public class UserValidInfo  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="用户编号，如果是机构就是机构编号对应的用户编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String userid;
	
	@ApiModelProperty(notes="身份证正面",allowEmptyValue=true,example="",allowableValues="")
	String idFront;
	
	@ApiModelProperty(notes="身份证反面",allowEmptyValue=true,example="",allowableValues="")
	String idBack;
	
	@ApiModelProperty(notes="手持身份证",allowEmptyValue=true,example="",allowableValues="")
	String idHold;
	
	@ApiModelProperty(notes="营业执照",allowEmptyValue=true,example="",allowableValues="")
	String bizLicense;
	
	@ApiModelProperty(notes="其它逗号分割多个",allowEmptyValue=true,example="",allowableValues="")
	String oths;
	
	@ApiModelProperty(notes="身份证号",allowEmptyValue=true,example="",allowableValues="")
	String idNo;
	
	@ApiModelProperty(notes="身份证到期日期",allowEmptyValue=true,example="",allowableValues="")
	String idEtime;
	
	@ApiModelProperty(notes="统一信用代码号税号营业执照号等",allowEmptyValue=true,example="",allowableValues="")
	String uscc;
	
	@ApiModelProperty(notes="机构实名名称",allowEmptyValue=true,example="",allowableValues="")
	String actBname;
	
	@ApiModelProperty(notes="实名用户名称或者法人名称",allowEmptyValue=true,example="",allowableValues="")
	String actUname;
	
	@ApiModelProperty(notes="新增时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="修改时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;
	
	@ApiModelProperty(notes="审核状态0-初始，1-审核中，2-通过，3-拒绝",allowEmptyValue=true,example="",allowableValues="")
	String flowState;
	
	@ApiModelProperty(notes="是否为机构，0-否，1-是，机构指企业认证，个人指实名认证",allowEmptyValue=true,example="",allowableValues="")
	String isOrg;
	
	@ApiModelProperty(notes="审核原因说明",allowEmptyValue=true,example="",allowableValues="")
	String validRemarks;
	
	@ApiModelProperty(notes="归属机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	/**
	 *用户编号，如果是机构就是机构编号对应的用户编号
	 **/
	public UserValidInfo(String userid) {
		this.userid = userid;
	}
    
    /**
     * 用户实名验证资料库
     **/
	public UserValidInfo() {
	}

}