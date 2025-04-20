package com.mdp.audit.log.client.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * 组织 com.qqkj  顶级模块 audit 大模块 base  小模块 <br> 
 * 实体 OperLog所有属性名: <br>
 *	id,userid,username,opTime,firstMenu,secondMenu,func,funcDesc,ip,ctime,params,branchId,branchName,execResult,opType,httpMethod,reqUrl,clientInfo,deptid,deptName;<br>
 * 表 MAUDIT.audit_oper_log audit_oper_log的所有字段名: <br>
 *	id,userid,username,op_time,first_menu,second_menu,func,func_desc,ip,ctime,params,branch_id,branch_name,exec_result,op_type,http_method,req_url,client_info,deptid,dept_name;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@Schema(description="audit_oper_log")
public class OperLog implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Schema(description="id,主键")
	String id;

	@Schema(description="系统分类编号")
	String categoryId;

	@Schema(description="系统分类名称")
	String categoryName;
	
	@Schema(description="用户id")
	String userid;
	
	@Schema(description="用户名称")
	String username;
	
	@Schema(description="响应时间")
	Integer opTime;
	
	@Schema(description="一级菜单")
	String firstMenu;
	
	@Schema(description="二级菜单")
	String secondMenu;
	
	@Schema(description="功能，菜单按钮")
	String func;
	
	@Schema(description="功能描述")
	String funcDesc;
	
	@Schema(description="IP地址")
	String ip;
	
	@Schema(description="操作时间")
	Date ctime;
	
	@Schema(description="请求参数")
	String params;
	
	@Schema(description="机构编号")
	String branchId;
	
	@Schema(description="机构名称")
	String branchName;
	
	@Schema(description="执行结果")
	String execResult;
	
	@Schema(description="c-r-u-d-down-up")
	String opType;
	
	@Schema(description="http请求方式post/get")
	String httpMethod;
	
	@Schema(description="http请求地址")
	String reqUrl;
	
	@Schema(description="客户端信息")
	String clientInfo;
	
	@Schema(description="归属部门编号")
	String deptid;
	
	@Schema(description="归属部门名称")
	String deptName;

	/**id**/
	public OperLog(String id) {
		this.id = id;
	}
    
    /**audit_oper_log**/
	public OperLog() {
	}
	
	/**
	 * id
	 **/
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 用户id
	 **/
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * 用户名称
	 **/
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 响应时间
	 **/
	public void setOpTime(Integer opTime) {
		this.opTime = opTime;
	}
	/**
	 * 一级菜单
	 **/
	public void setFirstMenu(String firstMenu) {
		this.firstMenu = firstMenu;
	}
	/**
	 * 二级菜单
	 **/
	public void setSecondMenu(String secondMenu) {
		this.secondMenu = secondMenu;
	}
	/**
	 * 功能，菜单按钮
	 **/
	public void setFunc(String func) {
		this.func = func;
	}
	/**
	 * 功能描述
	 **/
	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}
	/**
	 * IP地址
	 **/
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 操作时间
	 **/
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	/**
	 * 请求参数
	 **/
	public void setParams(String params) {
		this.params = params;
	}
	/**
	 * 机构编号
	 **/
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * 机构名称
	 **/
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * 执行结果
	 **/
	public void setExecResult(String execResult) {
		this.execResult = execResult;
	}
	/**
	 * c-r-u-d-down-up
	 **/
	public void setOpType(String opType) {
		this.opType = opType;
	}
	/**
	 * http请求方式post/get
	 **/
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	/**
	 * http请求地址
	 **/
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	/**
	 * 客户端信息
	 **/
	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}
	/**
	 * 归属部门编号
	 **/
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	/**
	 * 归属部门名称
	 **/
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	/**
	 * id
	 **/
	public String getId() {
		return this.id;
	}
	/**
	 * 用户id
	 **/
	public String getUserid() {
		return this.userid;
	}
	/**
	 * 用户名称
	 **/
	public String getUsername() {
		return this.username;
	}
	/**
	 * 响应时间
	 **/
	public Integer getOpTime() {
		return this.opTime;
	}
	/**
	 * 一级菜单
	 **/
	public String getFirstMenu() {
		return this.firstMenu;
	}
	/**
	 * 二级菜单
	 **/
	public String getSecondMenu() {
		return this.secondMenu;
	}
	/**
	 * 功能，菜单按钮
	 **/
	public String getFunc() {
		return this.func;
	}
	/**
	 * 功能描述
	 **/
	public String getFuncDesc() {
		return this.funcDesc;
	}
	/**
	 * IP地址
	 **/
	public String getIp() {
		return this.ip;
	}
	/**
	 * 操作时间
	 **/
	public Date getCtime() {
		return this.ctime;
	}
	/**
	 * 请求参数
	 **/
	public String getParams() {
		return this.params;
	}
	/**
	 * 机构编号
	 **/
	public String getBranchId() {
		return this.branchId;
	}
	/**
	 * 机构名称
	 **/
	public String getBranchName() {
		return this.branchName;
	}
	/**
	 * 执行结果
	 **/
	public String getExecResult() {
		return this.execResult;
	}
	/**
	 * c-r-u-d-down-up
	 **/
	public String getOpType() {
		return this.opType;
	}
	/**
	 * http请求方式post/get
	 **/
	public String getHttpMethod() {
		return this.httpMethod;
	}
	/**
	 * http请求地址
	 **/
	public String getReqUrl() {
		return this.reqUrl;
	}
	/**
	 * 客户端信息
	 **/
	public String getClientInfo() {
		return this.clientInfo;
	}
	/**
	 * 归属部门编号
	 **/
	public String getDeptid() {
		return this.deptid;
	}
	/**
	 * 归属部门名称
	 **/
	public String getDeptName() {
		return this.deptName;
	}


	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}