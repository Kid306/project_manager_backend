package com.mdp.sys.entity;

import java.io.Serializable;
import java.util.List;

public class UserDeptVo implements Serializable {
	
	String branchId;
	
	String userid;
	
	List<String> deptids;
	
	List<String> delDeptids;
	
	List<DeptPostUser> addDeptPostUsers;
	
	List<DeptPostUser> delDeptPostUsers;

 

	public List<DeptPostUser> getAddDeptPostUsers() {
		return addDeptPostUsers;
	}

	public void setAddDeptPostUsers(List<DeptPostUser> addDeptPostUsers) {
		this.addDeptPostUsers = addDeptPostUsers;
	} 

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<String> getDeptids() {
		return deptids;
	}

	public void setDeptids(List<String> deptids) {
		this.deptids = deptids;
	}

	public List<String> getDelDeptids() {
		return delDeptids;
	}

	public void setDelDeptids(List<String> delDeptids) {
		this.delDeptids = delDeptids;
	}

	public List<DeptPostUser> getDelDeptPostUsers() {
		return delDeptPostUsers;
	}

	public void setDelDeptPostUsers(List<DeptPostUser> delDeptPostUsers) {
		this.delDeptPostUsers = delDeptPostUsers;
	}
 

}
