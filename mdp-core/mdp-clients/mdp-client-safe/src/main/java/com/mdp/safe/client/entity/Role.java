package com.mdp.safe.client.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.mdp.qx.DataLvl;

public class Role implements java.io.Serializable{
 
	private static final long serialVersionUID = 569753402728601328L;

	/**
	 * 角色编号
	 */
	@TableId
	String roleid;
	
	/**
	 * 角色名称
	 */
	String rolename;

	/**
	 * 归属机构编号
	 */
	String branchId;

	/**
	 * 角色类型 0 私有，1 公有
	 */
	String roletype;

	/**
	 * @see  DataLvl
	 */
	Integer dataLvl;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getDataLvl() {
		return dataLvl;
	}

	public void setDataLvl(Integer dataLvl) {
		this.dataLvl = dataLvl;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}
}
