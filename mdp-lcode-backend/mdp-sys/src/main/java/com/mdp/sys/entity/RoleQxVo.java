package com.mdp.sys.entity;

import java.io.Serializable;
import java.util.List;

public class RoleQxVo implements Serializable { 
	 
	private static final long serialVersionUID = -6309542454863728613L;
	
	/**
	 * 角色编号
	 */
	public String roleid;
	/**
	 * 操作权限主键编号列表
	 */
	public List<String> dbQxIds;
	
	 
			
}
