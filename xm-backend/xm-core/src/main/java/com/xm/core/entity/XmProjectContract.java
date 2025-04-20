package com.xm.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 组织 com.qqkj  顶级模块 oa 大模块 xm  小模块 <br> 
 * 实体 XmProjectContract所有属性名: <br>
 *	htId,projectId;<br>
 * 表 XM.xm_project_contract xm_project_contract的所有字段名: <br>
 *	ht_id,project_id;<br>
 * 当前主键(包括多主键):<br>
 *	ht_id;<br>
 */
@ApiModel(description="xm_project_contract")
public class XmProjectContract  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes="合同编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String htId;
  	
	
	@ApiModelProperty(notes="项目编号",allowEmptyValue=true,example="",allowableValues="")
	String projectId;

	/**合同编号**/
	public XmProjectContract(String htId) {
		this.htId = htId;
	}
    
    /**xm_project_contract**/
	public XmProjectContract() {
	}
	
	/**
	 * 合同编号
	 **/
	public void setHtId(String htId) {
		this.htId = htId;
	}
	/**
	 * 项目编号
	 **/
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	/**
	 * 合同编号
	 **/
	public String getHtId() {
		return this.htId;
	}
	/**
	 * 项目编号
	 **/
	public String getProjectId() {
		return this.projectId;
	}

}