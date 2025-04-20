package com.mdp.safe.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-23
 */
@Data
@TableName("sys_dept")
@Schema(description = "部门")
public class Dept  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="部门编号,主键")
	String deptid;

	
	@Schema(description="部门全称")
	String deptName;

	
	@Schema(description="上级部门编号")
	String pdeptid;

	
	@Schema(description="参考数据字典deptType")
	String deptType;

	
	@Schema(description="状态A正常E无效")
	String state;

	
	@Schema(description="负责人编号")
	String manager;

	
	@Schema(description="上级领导编号")
	String leader;

	
	@Schema(description="简称")
	String shortName;

	
	@Schema(description="部门编码外部使用")
	String displayDeptid;

	
	@Schema(description="参考数据字典orgType")
	String orgType;

	
	@Schema(description="负责人名称")
	String managerName;

	
	@Schema(description="上级领导名称")
	String leaderName;

	
	@Schema(description="云用户机构编号")
	String branchId;

	
	@Schema(description="层级类型(科云：0国,1省,2市,3区县,4街道,5自然村)")
	String levelType;

	
	@Schema(description="部门编号路径")
	String idPath;

	
	@Schema(description="当前流程实例编号")
	String bizProcInstId;

	
	@Schema(description="当前流程状态")
	String bizFlowState;

	
	@Schema(description="最后更新时间")
	Date ltime;

	
	@Schema(description="是否为成本中心0否1是")
	String isCbCenter;

	
	@Schema(description="协作类型")
	String cpaType;

	
	@Schema(description="协作组织编号")
	String cpaBranchId;

	
	@Schema(description="参考类型")
	String relyType;

	
	@Schema(description="参考编号")
	String relyId;

	
	@Schema(description="参考子类型")
	String relyStype;

	
	@Schema(description="参考子编号")
	String relySid;

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