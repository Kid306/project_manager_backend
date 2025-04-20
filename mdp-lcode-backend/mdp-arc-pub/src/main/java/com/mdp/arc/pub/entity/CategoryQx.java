package  com.mdp.arc.pub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author code-gen
 * @since 2023-9-15
 */
@Data
@TableName("arc_category_qx")
@ApiModel(description="表单权限")
public class CategoryQx  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="分类编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String cateId;

	
	@ApiModelProperty(notes="允许那些角色查询,号分割",allowEmptyValue=true,example="",allowableValues="")
	String qryRoleids;

	
	@ApiModelProperty(notes="允许那些部门查询,号分割",allowEmptyValue=true,example="",allowableValues="")
	String qryDeptids;

	
	@ApiModelProperty(notes="允许哪些人查询,号分割",allowEmptyValue=true,example="",allowableValues="")
	String qryUserids;

	
	@ApiModelProperty(notes="禁止哪些角色查询",allowEmptyValue=true,example="",allowableValues="")
	String nqRoleids;

	
	@ApiModelProperty(notes="禁止哪些部门查询",allowEmptyValue=true,example="",allowableValues="")
	String nqDeptids;

	
	@ApiModelProperty(notes="禁止哪些人查询",allowEmptyValue=true,example="",allowableValues="")
	String nqUserids;

	
	@ApiModelProperty(notes="是否允许其它人查询",allowEmptyValue=true,example="",allowableValues="")
	String othQuery;

	
	@ApiModelProperty(notes="是否允许其它人修改",allowEmptyValue=true,example="",allowableValues="")
	String othEdit;

	
	@ApiModelProperty(notes="是否允许其它人删除",allowEmptyValue=true,example="",allowableValues="")
	String othDel;

	
	@ApiModelProperty(notes="是否进行部门级别传递权限检查",allowEmptyValue=true,example="",allowableValues="")
	String lvlCheck;

	
	@ApiModelProperty(notes="最低级别查询权限",allowEmptyValue=true,example="",allowableValues="")
	String qryMinLvl;

	
	@ApiModelProperty(notes="允许那些角色更新,号分割",allowEmptyValue=true,example="",allowableValues="")
	String editRoleids;

	
	@ApiModelProperty(notes="允许那些部门更新,号分割",allowEmptyValue=true,example="",allowableValues="")
	String editDeptids;

	
	@ApiModelProperty(notes="允许哪些人更新号分割",allowEmptyValue=true,example="",allowableValues="")
	String editUserids;

	
	@ApiModelProperty(notes="禁止哪些角色更新",allowEmptyValue=true,example="",allowableValues="")
	String neRoleids;

	
	@ApiModelProperty(notes="禁止哪些部门更新",allowEmptyValue=true,example="",allowableValues="")
	String neDeptids;

	
	@ApiModelProperty(notes="禁止哪些人更新",allowEmptyValue=true,example="",allowableValues="")
	String neUserids;

	
	@ApiModelProperty(notes="允许那些角色删除,号分割",allowEmptyValue=true,example="",allowableValues="")
	String delRoleids;

	
	@ApiModelProperty(notes="允许那些部门删除,号分割",allowEmptyValue=true,example="",allowableValues="")
	String delDeptids;

	
	@ApiModelProperty(notes="允许哪些人删除,号分割",allowEmptyValue=true,example="",allowableValues="")
	String delUserids;

	
	@ApiModelProperty(notes="禁止哪些角色删除",allowEmptyValue=true,example="",allowableValues="")
	String ndRoleids;

	
	@ApiModelProperty(notes="禁止哪些部门删除",allowEmptyValue=true,example="",allowableValues="")
	String ndDeptids;

	
	@ApiModelProperty(notes="禁止哪些人查询",allowEmptyValue=true,example="",allowableValues="")
	String ndUserids;

	
	@ApiModelProperty(notes="最低级别更新权限",allowEmptyValue=true,example="",allowableValues="")
	String editMinLvl;

	
	@ApiModelProperty(notes="最低级别删除权限",allowEmptyValue=true,example="",allowableValues="")
	String delMinLvl;

	/**
	 *分类编号
	 **/
	public CategoryQx(String cateId) {
		this.cateId = cateId;
	}
    
    /**
     * 表单权限
     **/
	public CategoryQx() {
	}

}