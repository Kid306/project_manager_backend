package  com.mdp.menu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 menu  小模块 <br> 
 * 实体 MenuRole所有属性名: <br>
 *	"roleid","用户组编号","mid","菜单编号","half","是否半选状态";<br>
 * 当前主键(包括多主键):<br>
 *	roleid,mid;<br>
 */
 @Data
@TableName("menu_role")
@ApiModel(description="菜单角色分配")
public class MenuRole  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="用户组编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String roleid;
    @TableIds
    @ApiModelProperty(notes="菜单编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String mid;
	
	@ApiModelProperty(notes="是否半选状态",allowEmptyValue=true,example="",allowableValues="")
	String half;

	/**
	 *用户组编号,菜单编号
	 **/
	public MenuRole(String roleid,String mid) {
		this.roleid = roleid;
		this.mid = mid;
	}
    
    /**
     * 菜单角色分配
     **/
	public MenuRole() {
	}

}