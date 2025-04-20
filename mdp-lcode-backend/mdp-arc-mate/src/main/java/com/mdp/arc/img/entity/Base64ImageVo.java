package com.mdp.arc.img.entity;

public class Base64ImageVo {
	/**
	 * 分类编号
	 */
	String categoryId;
	/**
	 * 文件名 如  aaaaa.jpej
	 */
	String name;
	
	/**
	 * tag 标签
	 */
	String tag;
	
	/**
	 * 数据
	 */
	String fileData;  
	/**
	 * 备注
	 */
	String remark;
	/**
	 * 部门
	 */
	String deptid;
	/**
	 * 机构
	 */
	String branchId; 
	
	/**
	 * 是否将图片信息存储入库0 否，1是
	 */
	String storedb;
	
	/**
	 * 压缩比例尺寸 0.1~1之间
	 */
	Float scale;
	
	/**
	 * 压缩质量 0.1~1之间
	 */
	Float outputQuality;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	 
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getStoredb() {
		return storedb;
	}
	public void setStoredb(String storedb) {
		this.storedb = storedb;
	}
	public Float getScale() {
		return scale;
	}
	public void setScale(Float scale) {
		this.scale = scale;
	}
	public Float getOutputQuality() {
		return outputQuality;
	}
	public void setOutputQuality(Float outputQuality) {
		this.outputQuality = outputQuality;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	} 
}
