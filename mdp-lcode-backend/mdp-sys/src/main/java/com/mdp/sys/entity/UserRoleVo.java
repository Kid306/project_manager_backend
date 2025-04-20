package com.mdp.sys.entity;

import java.io.Serializable;
import java.util.List;

public class UserRoleVo implements Serializable {
	
	String userid;
	
	List<String> roleids;
	
	List<String> delRoleids;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<String> getRoleids() {
		return roleids;
	}

	public void setRoleids(List<String> roleids) {
		this.roleids = roleids;
	}

	public List<String> getDelRoleids() {
		return delRoleids;
	}

	public void setDelRoleids(List<String> delRoleids) {
		this.delRoleids = delRoleids;
	}
}
