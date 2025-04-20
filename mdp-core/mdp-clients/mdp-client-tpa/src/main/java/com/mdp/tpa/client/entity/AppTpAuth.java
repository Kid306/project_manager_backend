package com.mdp.tpa.client.entity;

import java.util.Date;

/**
 * 组织 com.qqkj  顶级模块 mdp 大模块 app  小模块 <br> 
 * 实体 AppTpAuth所有属性名: <br>
 *	mdpAppid,name,logoUrl,remark,bizType,deptid,branchId,appToken,loginUrl,appid,cdate,cuserid,appSecret,authUrl,authPattern,encKey,payMchid,payNotifyUrl,enabled,openPay;<br>
 * 表 ADMIN.app_tp_auth MDP平台应用与第三方应用关系的所有字段名: <br>
 *	mdp_appid,name,logo_url,remark,biz_type,deptid,branch_id,app_token,login_url,tp_appid,cdate,cuserid,app_secret,auth_url,auth_pattern,enc_key,pay_mchid,pay_notify_url,enabled,open_pay;<br>
 * 当前主键(包括多主键):<br>
 *	tp_appid;<br>
 */
public class AppTpAuth  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 第三方应用编号 第三方系统提供
	 */ 
	String appid;
	
	/**
	 * 第三方应用编号,主键 authId mdp平台提供
	 */ 
	String authId;
  	
	/**
	 *  应用编号
	 */ 
	String mdpAppid;
	/**
	 * 应用名称  
	 */ 
	String name;
	/**
	 *   应用logo
	 */ 
	String logoUrl;
	/**
	 *   应用描述
	 */ 
	String remark;
	/**
	 *   应用分类
	 */ 
	String bizType;
	/**
	 *   归属部门
	 */ 
	String deptid;
	/**
	 *   归属机构
	 */ 
	String branchId;
	/**
	 *   登录验证码
	 */ 
	String appToken;
	/**
	 *   登录地址
	 */ 
	String loginUrl;
	/**
	 *   创建日期
	 */ 
	Date cdate;
	/**
	 *   创建人
	 */ 
	String cuserid;
	/**
	 *   加密串
	 */ 
	String appSecret;
	/**
	 *   授权地址
	 */ 
	String authUrl;
	/**
	 *   监听发起授权地址
	 */ 
	String authPattern;
	/**
	 *   秘钥
	 */ 
	String encKey;
	/**
	 *   商户编号
	 */ 
	String payMchid;
	/**
	 *   付款成功通知地址
	 */ 
	String payNotifyUrl;
	
	/**
	 *   付款密钥
	 */ 
	String payKey;
	
	/**
	 *   应用状态0下架1上架
	 */ 
	String enabled;
	/**
	 *  开通支付 
	 */ 
	String openPay;
	
	/**
	 *  消息通知地址 
	 */ 
	String msgNotifyUrl;
	
	/**
	 * 默认的门店编号
	 */
	String locationId;
	
	/**
	 * 门店名称
	 */
	String locationName;
	/**
	 * 默认的商户编号
	 */
	String shopId;
	
	/**
	 * 商户名称
	 */
	String shopName;
	
	/**
	 * 创建userid的方式创same-branchId不同机构，same-shopId不同商户，same-phone不同手机号码，same-authId不同授权码，same-mdpAppid不同的mdp应用编号
	 */
	String genUseridType;
	
	/**
	 * 用户与门店关联关系0-不创建，1-创建
	 */
	String useridLocationIdType;
	
	/**
	 *  消息通知地址 
	 */ 
	String ips;
	
	/**
	 * 是否为多店铺经营
	 */
	String multiLoca;
	
	/**
	 * 同主体下的微信公众号授权编号
	 */
	String pubAuthId;
	
	/**第三方应用编号**/
	public AppTpAuth(String appid) {
		this.appid = appid;
	}
    
    /**MDP平台应用与第三方应用关系**/
	public AppTpAuth() {
	}
	
	public void setMdpAppid(String mdpAppid) {
		this.mdpAppid = mdpAppid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public void setCuserid(String cuserid) {
		this.cuserid = cuserid;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	public void setAuthPattern(String authPattern) {
		this.authPattern = authPattern;
	}
	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}
	public void setPayMchid(String payMchid) {
		this.payMchid = payMchid;
	}
	public void setPayNotifyUrl(String payNotifyUrl) {
		this.payNotifyUrl = payNotifyUrl;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public void setOpenPay(String openPay) {
		this.openPay = openPay;
	}
	
	public String getMdpAppid() {
		return this.mdpAppid;
	}
	public String getName() {
		return this.name;
	}
	public String getLogoUrl() {
		return this.logoUrl;
	}
	public String getRemark() {
		return this.remark;
	}
	public String getBizType() {
		return this.bizType;
	}
	public String getDeptid() {
		return this.deptid;
	}
	public String getBranchId() {
		return this.branchId;
	}
	public String getAppToken() {
		return this.appToken;
	}
	public String getLoginUrl() {
		return this.loginUrl;
	}
	public String getAppid() {
		return this.appid;
	}
	public Date getCdate() {
		return this.cdate;
	}
	public String getCuserid() {
		return this.cuserid;
	}
	public String getAppSecret() {
		return this.appSecret;
	}
	public String getAuthUrl() {
		return this.authUrl;
	}
	public String getAuthPattern() {
		return this.authPattern;
	}
	public String getEncKey() {
		return this.encKey;
	}
	public String getPayMchid() {
		return this.payMchid;
	}
	public String getPayNotifyUrl() {
		return this.payNotifyUrl;
	}
	public String getEnabled() {
		return this.enabled;
	}
	public String getOpenPay() {
		return this.openPay;
	}

	public String getPayKey() {
		return payKey;
	}

	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getMsgNotifyUrl() {
		return msgNotifyUrl;
	}

	public void setMsgNotifyUrl(String msgNotifyUrl) {
		this.msgNotifyUrl = msgNotifyUrl;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getMultiLoca() {
		return multiLoca;
	}

	public void setMultiLoca(String multiLoca) {
		this.multiLoca = multiLoca;
	}

	public String getGenUseridType() {
		return genUseridType;
	}

	public void setGenUseridType(String genUseridType) {
		this.genUseridType = genUseridType;
	}

	public String getUseridLocationIdType() {
		return useridLocationIdType;
	}

	public void setUseridLocationIdType(String useridLocationIdType) {
		this.useridLocationIdType = useridLocationIdType;
	}

	public String getPubAuthId() {
		return pubAuthId;
	}

	public void setPubAuthId(String pubAuthId) {
		this.pubAuthId = pubAuthId;
	}
}