package com.mdp.core.entity;

import com.mdp.core.utils.LogUtils;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.HashMap;

public class Tips extends HashMap<String,Object>{
	/**
	 * 成功或者失败
	 */
	//public boolean isOk=true;
	/**
	 * 提示码、错误码 一般类名+代码行号
	 */
	//public String tipscode="tips01";
	/**
	 * *提示信息
	 * */
	//public String msg="成功";
	
	/**
	 * 错误字段英文名称
	 */
	//public String fieldName;

	// 前端接收到错误码后的下一个响应操作，'redo','openUi','confirm'
 	// String nextAction,
	
	public Tips setErrMsg(String msg, Object...msgVars){
		this.put("isOk", false); 
		this.put("msg", msg);
		this.put("msgVars",msgVars);
		return this;
	}
	 
	
	/**
	 * 设置返回客户端的提示信息及错误字段
	 * @param tipscode 提示码、错误码 一般类名+代码行号 
	 * @param msg 错误提示信息
	 */
	public Tips setErrMsg(String tipscode, String msg,Object...msgVars){
		this.put("tipscode", tipscode);    
		this.put("isOk", false); 
		this.put("msg", msg);
		this.put("msgVars",msgVars);
		return this;
	}

	/**
	 * 设置返回客户端的提示信息及错误字段
	 * @param tipscode 提示码、错误码 一般类名+代码行号
	 * @param msg 错误提示信息
	 */
	public Tips setOkMsg(String tipscode, String msg,Object...msgVars){
		this.put("tipscode", tipscode);
		this.put("isOk", true);
		this.put("msg", msg);
		this.put("msgVars",msgVars);
		return this;
	}
	
	public Tips setOkMsg(String msg,Object...msgVars){
		this.put("isOk", true); 
		this.put("msg", msg);
		this.put("msgVars",msgVars);
		return this;
	}
	

	public Tips() {
		this.put("isOk", true);   
		this.getAndPutMdcNoToTips();
	}
	
	public Tips(String msg,Object...msgVars) {
		this.put("isOk", true); 
		this.put("msg", msg);
		this.put("msgVars",msgVars);
		this.getAndPutMdcNoToTips();
	}
	
	public Tips(boolean isOk,String msg ,Object...msgVars) {
		this.put("isOk", isOk);
		this.put("msg", msg);
		this.put("msgVars",msgVars);
		this.getAndPutMdcNoToTips();
		
	}
	public Tips(String tipscode,String msg ,Object...msgVars) {
		this.put("isOk", true);
		this.put("msg", msg);
		this.put("msgVars",msgVars);
		this.getAndPutMdcNoToTips();

	}
	public Tips(boolean isOk,String tipscode,String msg,Object...msgVars ) {
		this.put("isOk", isOk);
		this.put("msg", msg);
		this.put("tipscode", tipscode);
		this.put("msgVars",msgVars);
		this.getAndPutMdcNoToTips();
	}
	
	public Object[] getMsgVars(){
		return (Object[]) this.get("msgVars");
	}

	public Tips create(){
		return new Tips();
	}


	public Tips setIsOk(boolean isOk) {
		super.put("isOk",isOk);
		return this;
	}
	 
	public boolean getIsOk() {
		return (boolean) this.get("isOk");
	}
	 
	public String getTipscode() {
		return (String) this.get("tipscode");
	}
	public String getMsg() {
		return (String) this.get("msg");
	}
	public Tips setMsg(String msg) {
		this.put("msg", msg);
		this.remove("tipscode");
		this.remove("msgVars");
		return this;
	}
	public String getFieldName() {
		return (String) this.get("fieldName");
	}
	public Tips setFieldName(String fieldName) {
		this.put("fieldName", fieldName);
		return this;
	}

	public boolean isOk() {

		return (boolean) this.get("isOk");
	}
	/**
	 * 初始化日志中的全局流水号，请求流水号到tips中，用于返回客户端
	 */
	public void getAndPutMdcNoToTips() {
		String reqNo=MDC.get(LogUtils.REQ_NO_NAME);
		String gloNo=MDC.get(LogUtils.GLO_NO_NAME);
		if(!StringUtils.isEmpty(reqNo)) {
			this.put(LogUtils.REQ_NO_NAME, reqNo);
		}
		if(!StringUtils.isEmpty(gloNo)) {
			this.put(LogUtils.GLO_NO_NAME, gloNo);
		} 
	}

	public Tips put(String key,Object value){
		super.put(key,value);
		return this;
	}
}
