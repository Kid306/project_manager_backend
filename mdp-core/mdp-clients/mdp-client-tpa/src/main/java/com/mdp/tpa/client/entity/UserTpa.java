package com.mdp.tpa.client.entity;

import java.util.Date;

/**
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserTpa所有属性名: <br>
 *	unionid,openid,userid,locked,startdate,nickname,username,phoneno,country,city,headimgurl,province,userunionid,mdpAppid,deptid,branchId,appid;<br>
 * 表 ADMIN.sys_user_tpa 第三方系统向我方开放的用户列表的所有字段名: <br>
 *	unionid,openid,userid,locked,startdate,nickname,username,phoneno,country,city,headimgurl,province,userunionid,mdp_appid,deptid,branch_id,tp_appid;<br>
 * 当前主键(包括多主键):<br>
 *	openid;<br>
 */
public class UserTpa  implements java.io.Serializable ,Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * mdp内部第三方账号编号，用于对第三方系统进行开放,主键
	 */ 
	String id;
	
	/**
	 * 帐户加密后的编号，用于对第三方系统进行开放
	 */ 
	String openid;
  	
	/**
	 *  全局唯一编号
	 */ 
	String unionid;
	
	/**
	 *  MDP用户编号
	 */ 
	String userid;
	
	/**
	 *  是否被锁定
	 */ 
	String locked;
	
	/**
	 *  启用日期
	 */ 
	Date startdate;
	
	/**
	 *  昵称
	 */ 
	String nickname;
	
	/**
	 *  用户名称
	 */ 
	String username;
	
	/**
	 *  移动电话号码
	 */ 
	String phoneno;
	
	/**
	 *  国家
	 */ 
	String country;
	
	/**
	 *  城市
	 */ 
	String city;
	
	/**
	 *  头像地址
	 */ 
	String headimgurl;
	
	/**
	 *  省份
	 */ 
	String province;
	
	/**
	 *  对应本系统user表的UNIONID
	 */ 
	String userunionid;
	
	/**
	 *  前端应用系统编号
	 */ 
	String mdpAppid;
	
	/**
	 *  机构编号
	 */ 
	String deptid;
	
	/**
	 *  云用户机构编号
	 */ 
	String branchId;
	
	/**
	 *  第三方登录应用系统编号-第三方提供
	 */ 
	String appid;
	
	/**
	 *  第三方登录应用系统编号-mdp提供
	 */ 
	String authId;
	
	/**
	 *  性别 0女1男2中性
	 */ 
	String gender;
	
	/**
	 * sessionKey第三方系统回传的密钥串 服务端存储
	 */
	String sessionKey;
	
	String shopId;
	
	String locationId;
	
	String shopName;
	
	String locationName;

	/**
	 * wxa/wxpub/wxopen/zfbpub/zfbpay
	 */
	String bizType;

	/**帐户加密后的编号，用于对第三方系统进行开放**/
	public UserTpa(String openid) {
		this.openid = openid;
	}
    
    /**第三方系统向我方开放的用户列表**/
	public UserTpa() {
	}
	
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setUserunionid(String userunionid) {
		this.userunionid = userunionid;
	}
	public void setMdpAppid(String mdpAppid) {
		this.mdpAppid = mdpAppid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getUnionid() {
		return this.unionid;
	}
	public String getOpenid() {
		return this.openid;
	}
	public String getUserid() {
		return this.userid;
	}
	public String getLocked() {
		return this.locked;
	}
	public Date getStartdate() {
		return this.startdate;
	}
	public String getNickname() {
		return this.nickname;
	}
	public String getUsername() {
		return this.username;
	}
	public String getPhoneno() {
		return this.phoneno;
	}
	public String getCountry() {
		return this.country;
	}
	public String getCity() {
		return this.city;
	}
	public String getHeadimgurl() {
		return this.headimgurl;
	}
	public String getProvince() {
		return this.province;
	}
	public String getUserunionid() {
		return this.userunionid;
	}
	public String getMdpAppid() {
		return this.mdpAppid;
	}
	public String getDeptid() {
		return this.deptid;
	}
	public String getBranchId() {
		return this.branchId;
	}
	public String getAppid() {
		return this.appid;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public UserTpa clone() {
		
		try {
			return (UserTpa) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
}