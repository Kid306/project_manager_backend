package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserDept所有属性名: <br>
 *	"userid","用户编号","deptid","部门编号","enabled","是否启用","seq","顺序号";<br>
 * 当前主键(包括多主键):<br>
 *	userid,deptid;<br>
 */
 @Data
@TableName("sys_user_dept")
@ApiModel(description="用户部门关系表")
public class UserDept  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="用户编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String userid;
    @TableIds
    @ApiModelProperty(notes="部门编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String deptid;
	
	@ApiModelProperty(notes="是否启用",allowEmptyValue=true,example="",allowableValues="")
	String enabled;
	
	@ApiModelProperty(notes="顺序号",allowEmptyValue=true,example="",allowableValues="")
	Integer seq;

	/**
	 *用户编号,部门编号
	 **/
	public UserDept(String userid,String deptid) {
		this.userid = userid;
		this.deptid = deptid;
	}
    
    /**
     * 用户部门关系表
     **/
	public UserDept() {
	}

}