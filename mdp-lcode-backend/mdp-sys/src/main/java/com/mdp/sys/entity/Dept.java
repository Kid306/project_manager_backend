package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-23
 */
@Data
@TableName("sys_dept")
@ApiModel(description="sys_dept")
public class Dept  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="部门编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String deptid;

	
	@ApiModelProperty(notes="部门全称",allowEmptyValue=true,example="",allowableValues="")
	String deptName;

	
	@ApiModelProperty(notes="上级部门编号",allowEmptyValue=true,example="",allowableValues="")
	String pdeptid;

	
	@ApiModelProperty(notes="参考数据字典deptType",allowEmptyValue=true,example="",allowableValues="")
	String deptType;

	
	@ApiModelProperty(notes="状态A正常E无效",allowEmptyValue=true,example="",allowableValues="")
	String state;

	
	@ApiModelProperty(notes="负责人编号",allowEmptyValue=true,example="",allowableValues="")
	String manager;

	
	@ApiModelProperty(notes="上级领导编号",allowEmptyValue=true,example="",allowableValues="")
	String leader;

	
	@ApiModelProperty(notes="简称",allowEmptyValue=true,example="",allowableValues="")
	String shortName;

	
	@ApiModelProperty(notes="部门编码外部使用",allowEmptyValue=true,example="",allowableValues="")
	String displayDeptid;

	
	@ApiModelProperty(notes="参考数据字典orgType",allowEmptyValue=true,example="",allowableValues="")
	String orgType;

	
	@ApiModelProperty(notes="负责人名称",allowEmptyValue=true,example="",allowableValues="")
	String managerName;

	
	@ApiModelProperty(notes="上级领导名称",allowEmptyValue=true,example="",allowableValues="")
	String leaderName;

	
	@ApiModelProperty(notes="云用户机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="层级类型(科云：0国,1省,2市,3区县,4街道,5自然村)",allowEmptyValue=true,example="",allowableValues="")
	String levelType;

	
	@ApiModelProperty(notes="部门编号路径",allowEmptyValue=true,example="",allowableValues="")
	String idPath;

	
	@ApiModelProperty(notes="当前流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String bizProcInstId;

	
	@ApiModelProperty(notes="当前流程状态",allowEmptyValue=true,example="",allowableValues="")
	String bizFlowState;

	
	@ApiModelProperty(notes="最后更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;

	
	@ApiModelProperty(notes="是否为成本中心0否1是",allowEmptyValue=true,example="",allowableValues="")
	String isCbCenter;

	
	@ApiModelProperty(notes="协作类型",allowEmptyValue=true,example="",allowableValues="")
	String cpaType;

	
	@ApiModelProperty(notes="协作组织编号",allowEmptyValue=true,example="",allowableValues="")
	String cpaBranchId;

	
	@ApiModelProperty(notes="参考类型",allowEmptyValue=true,example="",allowableValues="")
	String relyType;

	
	@ApiModelProperty(notes="参考编号",allowEmptyValue=true,example="",allowableValues="")
	String relyId;

	
	@ApiModelProperty(notes="参考子类型",allowEmptyValue=true,example="",allowableValues="")
	String relyStype;

	
	@ApiModelProperty(notes="参考子编号",allowEmptyValue=true,example="",allowableValues="")
	String relySid;


	@ApiModelProperty(notes="图标",allowEmptyValue=true,example="",allowableValues="")
	String icon;

	/**
	 *部门编号
	 **/
	public Dept(String deptid) {
		this.deptid = deptid;
	}
    
    /**
     * sys_dept
     **/
	public Dept() {
	}

}