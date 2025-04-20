package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 RoleQx所有属性名: <br>
 *	"qxId","权限编号增删改查批量更新导出导入","roleid","角色编号";<br>
 * 当前主键(包括多主键):<br>
 *	qx_id,roleid;<br>
 */
 @Data
@TableName("sys_role_qx")
@ApiModel(description="角色权限关系表")
public class RoleQx  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="权限编号增删改查批量更新导出导入,主键",allowEmptyValue=true,example="",allowableValues="")
    String qxId;
    @TableIds
    @ApiModelProperty(notes="角色编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String roleid;

	/**
	 *权限编号增删改查批量更新导出导入,角色编号
	 **/
	public RoleQx(String qxId,String roleid) {
		this.qxId = qxId;
		this.roleid = roleid;
	}
    
    /**
     * 角色权限关系表
     **/
	public RoleQx() {
	}

}