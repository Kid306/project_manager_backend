package com.mdp.sys.entity;

import io.swagger.annotations.ApiModel;

/**
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 User所有属性名: <br>
 *	unionid,displayUserid,userid,locked,startdate,nickname,username,phoneno,password,salt,fingerpassword1,fingerpassword2,fingerpassword3,fingerpassword4,pwdtype,headimgurl,country,city,province,address,sex,enddate,districtId,userid,userAccount,userPwd,userName,userDesc;<br>
 * 表 ADMIN.sys_user sys_user的所有字段名: <br>
 *	unionid,display_userid,userid,locked,startdate,nickname,username,phoneno,password,salt,fingerpassword1,fingerpassword2,fingerpassword3,fingerpassword4,pwdtype,headimgurl,country,city,province,address,sex,enddate,district_id,userid,user_account,user_pwd,user_name,user_desc;<br>
 * 当前主键(包括多主键):<br>
 *	userid;<br>
 */
@ApiModel(description="sys_user")
public class UserVo  implements java.io.Serializable { 
	
	User user;
	UserDept userDept;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserDept getUserDept() {
		return userDept;
	}
	public void setUserDept(UserDept userDept) {
		this.userDept = userDept;
	}
}