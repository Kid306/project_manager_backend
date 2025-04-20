package com.mdp.login.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 组织 com  顶级模块 mk 大模块 mem  小模块 <br> 
 * 实体 MemberValidCode所有属性名: <br>
 *	userid,valiCode,codeSendTime,codeEmail,codeScene,codeValidTime,id;<br>
 * 表 MK.mem_member_valid_code 会员表（前端商城的所有字段名: <br>
 *	userid,vali_code,code_send_time,code_email,code_scene,code_valid_time,id;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@ApiModel(description="会员表（前端商城")
@TableName("sys_user_valid_code")
public class ValidCode implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;


	@ApiModelProperty(notes="内部用户编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;

	@ApiModelProperty(notes="验证码",allowEmptyValue=true,example="",allowableValues="")
	String valiCode;

	@ApiModelProperty(notes="验证码发送时间",allowEmptyValue=true,example="",allowableValues="")
	Date codeSendTime;

	@ApiModelProperty(notes="验证码接收邮箱编号",allowEmptyValue=true,example="",allowableValues="")
	String codeEmail;

	@ApiModelProperty(notes="验证场景0-重置密码，1-注册验证，2-更换常用邮箱第一步验证旧邮箱,，3-更换常用邮箱第二步验证新邮箱，4-更换常用邮箱第一步验证旧邮箱,，5-更换常用邮箱第二步验证新邮箱，",allowEmptyValue=true,example="",allowableValues="")
	String codeScene;

	@ApiModelProperty(notes="验证码验证时间",allowEmptyValue=true,example="",allowableValues="")
	Date codeValidTime;

	/**主键**/
	public ValidCode(String id) {
		this.id = id;
	}

    /**会员表（前端商城**/
	public ValidCode() {
	}
	
	/**
	 * 内部用户编号
	 **/
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * 验证码
	 **/
	public void setValiCode(String valiCode) {
		this.valiCode = valiCode;
	}
	/**
	 * 验证码发送时间
	 **/
	public void setCodeSendTime(Date codeSendTime) {
		this.codeSendTime = codeSendTime;
	}
	/**
	 * 验证码接收邮箱编号
	 **/
	public void setCodeEmail(String codeEmail) {
		this.codeEmail = codeEmail;
	}
	/**
	 * 验证场景0-重置密码，1-注册验证，2-更换常用邮箱第一步验证旧邮箱,，3-更换常用邮箱第二步验证新邮箱，4-更换常用邮箱第一步验证旧邮箱,，5-更换常用邮箱第二步验证新邮箱，
	 **/
	public void setCodeScene(String codeScene) {
		this.codeScene = codeScene;
	}
	/**
	 * 验证码验证时间
	 **/
	public void setCodeValidTime(Date codeValidTime) {
		this.codeValidTime = codeValidTime;
	}
	/**
	 * 主键
	 **/
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 内部用户编号
	 **/
	public String getUserid() {
		return this.userid;
	}
	/**
	 * 验证码
	 **/
	public String getValiCode() {
		return this.valiCode;
	}
	/**
	 * 验证码发送时间
	 **/
	public Date getCodeSendTime() {
		return this.codeSendTime;
	}
	/**
	 * 验证码接收邮箱编号
	 **/
	public String getCodeEmail() {
		return this.codeEmail;
	}
	/**
	 * 验证场景0-重置密码，1-注册验证，2-更换常用邮箱第一步验证旧邮箱,，3-更换常用邮箱第二步验证新邮箱，4-更换常用邮箱第一步验证旧邮箱,，5-更换常用邮箱第二步验证新邮箱，
	 **/
	public String getCodeScene() {
		return this.codeScene;
	}
	/**
	 * 验证码验证时间
	 **/
	public Date getCodeValidTime() {
		return this.codeValidTime;
	}
	/**
	 * 主键
	 **/
	public String getId() {
		return this.id;
	}

}