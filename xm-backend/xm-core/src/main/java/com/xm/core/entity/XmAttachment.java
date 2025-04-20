package com.xm.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 组织 com.qqkj  顶级模块 oa 大模块 xm  小模块 <br> 
 * 实体 XmAttachment所有属性名: <br>
 *	id,originType,originId,name,addr,type,remark;<br>
 * 表 XM.xm_attachment xm_attachment的所有字段名: <br>
 *	id,origin_type,origin_id,name,addr,type,remark;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@ApiModel(description="xm_attachment")
public class XmAttachment  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
  	
	
	@ApiModelProperty(notes="附件来源类型，0任务，1问题，2文档",allowEmptyValue=true,example="",allowableValues="")
	String originType;
	
	@ApiModelProperty(notes="来源id",allowEmptyValue=true,example="",allowableValues="")
	String originId;
	
	@ApiModelProperty(notes="附件名字",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="附件地址",allowEmptyValue=true,example="",allowableValues="")
	String addr;
	
	@ApiModelProperty(notes="附件类型",allowEmptyValue=true,example="",allowableValues="")
	String type;
	
	@ApiModelProperty(notes="备注说明",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	/**主键**/
	public XmAttachment(String id) {
		this.id = id;
	}
    
    /**xm_attachment**/
	public XmAttachment() {
	}
	
	/**
	 * 主键
	 **/
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 附件来源类型，0任务，1问题，2文档
	 **/
	public void setOriginType(String originType) {
		this.originType = originType;
	}
	/**
	 * 来源id
	 **/
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	/**
	 * 附件名字
	 **/
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 附件地址
	 **/
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 附件类型
	 **/
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 备注说明
	 **/
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 主键
	 **/
	public String getId() {
		return this.id;
	}
	/**
	 * 附件来源类型，0任务，1问题，2文档
	 **/
	public String getOriginType() {
		return this.originType;
	}
	/**
	 * 来源id
	 **/
	public String getOriginId() {
		return this.originId;
	}
	/**
	 * 附件名字
	 **/
	public String getName() {
		return this.name;
	}
	/**
	 * 附件地址
	 **/
	public String getAddr() {
		return this.addr;
	}
	/**
	 * 附件类型
	 **/
	public String getType() {
		return this.type;
	}
	/**
	 * 备注说明
	 **/
	public String getRemark() {
		return this.remark;
	}

}