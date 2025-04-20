package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserFans所有属性名: <br>
 *	"userid","我的账户","fuserid","我关注的用户，如果是企业则为企业的管理员账户","ftime","关注时间";<br>
 * 当前主键(包括多主键):<br>
 *	userid,fuserid;<br>
 */
 @Data
@TableName("sys_user_fans")
@ApiModel(description="sys_user_fans")
public class UserFans  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="我的账户,主键",allowEmptyValue=true,example="",allowableValues="")
    String userid;
    @TableIds
    @ApiModelProperty(notes="我关注的用户，如果是企业则为企业的管理员账户,主键",allowEmptyValue=true,example="",allowableValues="")
    String fuserid;
	
	@ApiModelProperty(notes="关注时间",allowEmptyValue=true,example="",allowableValues="")
	Date ftime;

	/**
	 *我的账户,我关注的用户，如果是企业则为企业的管理员账户
	 **/
	public UserFans(String userid,String fuserid) {
		this.userid = userid;
		this.fuserid = fuserid;
	}
    
    /**
     * sys_user_fans
     **/
	public UserFans() {
	}

}