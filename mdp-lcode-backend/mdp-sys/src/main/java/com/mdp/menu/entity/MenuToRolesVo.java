package com.mdp.menu.entity;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * 组织 com.qqkj  顶级模块 mdp 大模块 res  小模块 <br> 
 * 实体 ResOperRole所有属性名: <br>
 *	id,roleid,operId;<br>
 * 表 ADMIN.res_oper_role 角色资源关系表的所有字段名: <br>
 *	id,roleid,oper_id;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@ApiModel(description="角色资源关系表")
public class MenuToRolesVo implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -664684104957484091L;

    /**
	 * 菜单编号
	 */
	String mid;

	/**
     * 角色编号
	 */
	List<String> roleids;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public List<String> getRoleids() {
		return roleids;
	}

	public void setRoleids(List<String> roleids) {
		this.roleids = roleids;
	}
}