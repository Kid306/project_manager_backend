package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserRole所有属性名: <br>
 *	"roleid","用户组编号","userid","用户编号";<br>
 * 当前主键(包括多主键):<br>
 *	roleid,userid;<br>
 */
 @Data
@TableName("sys_user_role")
@ApiModel(description="用户角色表(作废)")
public class UserRole  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="用户组编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String roleid;
    @TableIds
    @ApiModelProperty(notes="用户编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String userid;

	/**
	 *用户组编号,用户编号
	 **/
	public UserRole(String roleid,String userid) {
		this.roleid = roleid;
		this.userid = userid;
	}
    
    /**
     * 用户角色表(作废)
     **/
	public UserRole() {
	}

}