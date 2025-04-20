package com.mdp.login.entity;

import io.swagger.annotations.ApiModel;

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
public class ValidCodeVo extends ValidCode  {
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 用户类型 suerType: cust|staff
     */
    String userType;

}