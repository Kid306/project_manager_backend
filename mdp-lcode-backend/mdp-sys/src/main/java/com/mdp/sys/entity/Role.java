package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 Role所有属性名: <br>
 *	"roleid","角色编号","rolename","角色名","remark","备注","roletype","角色类型0-机构私有,1-公共","rolebeg","开始时间","roleend","结束时间","crdate","创建日期","enabled","是否启用","deptid","机构编号","sortOrder","角色排序","branchId","云用户机构编号","dataLvl","数据访问等级";<br>
 * 当前主键(包括多主键):<br>
 *	roleid;<br>
 */
 @Data
@TableName("sys_role")
@ApiModel(description="角色管理")
public class Role  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="角色编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String roleid;
	
	@ApiModelProperty(notes="角色名",allowEmptyValue=true,example="",allowableValues="")
	String rolename;
	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="角色类型0-机构私有,1-公共",allowEmptyValue=true,example="",allowableValues="")
	String roletype;
	
	@ApiModelProperty(notes="开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date rolebeg;
	
	@ApiModelProperty(notes="结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date roleend;
	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date crdate;
	
	@ApiModelProperty(notes="是否启用",allowEmptyValue=true,example="",allowableValues="")
	String enabled;
	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String deptid;
	
	@ApiModelProperty(notes="角色排序",allowEmptyValue=true,example="",allowableValues="")
	Integer sortOrder;
	
	@ApiModelProperty(notes="云用户机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="数据访问等级",allowEmptyValue=true,example="",allowableValues="")
	Integer dataLvl;

	/**
	 *角色编号
	 **/
	public Role(String roleid) {
		this.roleid = roleid;
	}
    
    /**
     * 角色管理
     **/
	public Role() {
	}

}