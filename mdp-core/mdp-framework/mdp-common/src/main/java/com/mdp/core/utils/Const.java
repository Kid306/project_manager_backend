package com.mdp.core.utils;


/***
 * mdp 平台的常量
 * @author cyc
 *
 */
public class Const {

	public static final String DB_TYPE_MYSQL = "MYSQL";
	public static final String DB_TYPE_ORACLE = "ORACLE";
	public static final String DB_TYPE_DM = "DM";//达梦数据库
	public static final String DB_TYPE_POSTGRESQL = "POSTGRESQL";
	public static final String DB_TYPE_SQLSERVER = "SQLSERVER";
	public static final String DB_TYPE_MARIADB = "MARIADB";
	public static final String DB_TYPE_DB2 = "DB2";
	public static final String DB_TYPE_HSQL = "HSQL";
	
	/**顶级部门编号**/
	public static final String topDeptid = Const.deptidSplitChar+"0";
	
	public static final String deptidSplitChar="A";

	/**菜单根节点编号**/
	public static final String menuRoot = "root";

	/**第三方账号对象**/
	public static final String tpa = "tpa";

	/**当前登录用户所拥有的菜单,页面使用 ${sessionScope.menus}访问
	 *  java获取方式 SecurityUtils.getSubject().getSession().getAttribute("menus");
	 * ***/
	public static final String menus = "menus";

	/**超级管理员的角色**/
	public static final String superAdminRole = "superAdmin";
	
	/**超级管理员的编号**/
	public static final String superAdminUserid = "superAdmin";
	
	/**新注册用户默认角色/用户组*/
	public static final String newUserRole="newUser";


	/**当前登录用户编号,页面使用 ${sessionScope.user.username}访问
	 * 当前登录用户名称,页面使用 ${sessionScope.user.simplename}访问
	 * 当前登录用户角色编号,页面使用 ${sessionScope.user.roleid}访问
	 * 当前登录用户角色名称,页面使用 ${sessionScope.user.rolename}访问
	 * 当前登录用户部门编号,页面使用 ${sessionScope.user.deptid}访问
	 * 当前登录用户部门名称,页面使用 ${sessionScope.user.deptName}访问
	 * 当前登录用户,页面使用 ${sessionScope.user}访问 
	 * java获取方式 SecurityUtils.getSubject().getSession().getAttribute("user");
	 * java或者 ContextHolder.getCurrentUser();
	 ****/
	public static String user="user";
	
	/**
	  *当前登录用户所属部门列表,页面使用 ${sessionScope.depts}访问
	  * java获取方式 SecurityUtils.getSubject().getSession().getAttribute("depts");
	  ****/
	public static String depts="depts";
	
	/**
	 *角色列表,页面使用 ${sessionScope.roles}访问
	 *java获取方式 SecurityUtils.getSubject().getSession().getAttribute("roles");
	 ****/
	public static String roles="roles";
	
	
}
