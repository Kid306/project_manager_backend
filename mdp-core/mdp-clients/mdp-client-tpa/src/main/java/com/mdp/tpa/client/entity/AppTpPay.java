package com.mdp.tpa.client.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组织 com.qqkj.mdp  顶级模块 safe 大模块 common  小模块 <br> 
 * 实体 AppTpPay所有属性名: <br>
 *	mdpAppid,name,logoUrl,remark,payType,deptid,branchId,cdate,cuserid,enabled,appid,payMchid,payNotifyUrl,openPay,payKey,ips,locationId,shopId,locationName,shopName,payAuthId,payPubKey,tpServiceCharges,mdpServiceCharges,mdpPayAccountId;<br>
 * 表 ADM.app_tp_pay app_tp_pay的所有字段名: <br>
 *	mdp_appid,name,logo_url,remark,pay_type,deptid,branch_id,cdate,cuserid,enabled,appid,pay_mchid,pay_notify_url,open_pay,pay_key,ips,location_id,shop_id,location_name,shop_name,pay_auth_id,pay_pub_key,tp_service_charges,mdp_service_charges,mdp_pay_account_id;<br>
 * 当前主键(包括多主键):<br>
 *	pay_auth_id;<br>
 */
@Schema(description="app_tp_pay")
public class AppTpPay  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Schema(description="主键,主键")
	String payAuthId;
  	
	
	@Schema(description="应用编号")
	String mdpAppid;
	
	@Schema(description="应用名称")
	String name;
	
	@Schema(description="应用logo")
	String logoUrl;
	
	@Schema(description="应用描述")
	String remark;
	
	@Schema(description="付款方式0余额1微信2V麦3支付宝4银行5网银6其他")
	String payType;
	
	@Schema(description="归属部门")
	String deptid;
	
	@Schema(description="归属机构")
	String branchId;
	
	@Schema(description="创建日期")
	Date cdate;
	
	@Schema(description="创建人")
	String cuserid;
	
	@Schema(description="应用状态0下架1上架")
	String enabled;
	
	@Schema(description="对应第三方提供给的appid")
	String appid;
	
	@Schema(description="商户编号")
	String payMchid;
	
	@Schema(description="付款成功通知地址")
	String payNotifyUrl;
	
	@Schema(description="是否开通支付")
	String openPay;
	
	@Schema(description="支付加密秘钥私钥")
	String payKey;
	
	@Schema(description="服务器IP地址列表ip1,ip2,ip3")
	String ips;
	
	@Schema(description="绑定的门店")
	String locationId;
	
	@Schema(description="绑定的商户编号")
	String shopId;
	
	@Schema(description="门店名称")
	String locationName;
	
	@Schema(description="商户名称")
	String shopName;
	
	@Schema(description="支付加密秘钥公钥")
	String payPubKey;
	
	@Schema(description="第三方手续费费率比如0.003")
	BigDecimal tpServiceCharges;
	
	@Schema(description="mdp平台附加手续费费率比如0.002")
	BigDecimal mdpServiceCharges;
	
	@Schema(description="mdp平台商户手续费费率一般为门店订单交易需要支付一定的手续费给商户")
	BigDecimal mdpShopServiceCharges;
	
	@Schema(description="mdp平台商户账户（一般商户编号+门店编号，用于mdp平台与该商户的结算）")
	String mdpPayAccountId;
	
	@Schema(description="mdp平台手续费收取账户默认1001账户无论是门店交易还是商户交易，均要向平台缴纳一部分手续费")
	String mdpScAccountId;
	
	@Schema(description="结算商户编号、如果是单商户模式则为自身shopId，否则为平台商户编号")
	String settleShopId;
 

	/**主键**/
	public AppTpPay(String payAuthId) {
		this.payAuthId = payAuthId;
	}
    
    /**app_tp_pay**/
	public AppTpPay() {
	}
	
	/**
	 * 应用编号
	 **/
	public void setMdpAppid(String mdpAppid) {
		this.mdpAppid = mdpAppid;
	}
	/**
	 * 应用名称
	 **/
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 应用logo
	 **/
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * 应用描述
	 **/
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 付款方式0余额1微信2V麦3支付宝4银行5网银6其他
	 **/
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 归属部门
	 **/
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	/**
	 * 归属机构
	 **/
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	/**
	 * 创建日期
	 **/
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	/**
	 * 创建人
	 **/
	public void setCuserid(String cuserid) {
		this.cuserid = cuserid;
	}
	/**
	 * 应用状态0下架1上架
	 **/
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	/**
	 * 对应第三方提供给的appid
	 **/
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * 商户编号
	 **/
	public void setPayMchid(String payMchid) {
		this.payMchid = payMchid;
	}
	/**
	 * 付款成功通知地址
	 **/
	public void setPayNotifyUrl(String payNotifyUrl) {
		this.payNotifyUrl = payNotifyUrl;
	}
	/**
	 * 是否开通支付
	 **/
	public void setOpenPay(String openPay) {
		this.openPay = openPay;
	}
	/**
	 * 支付加密秘钥私钥
	 **/
	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}
	/**
	 * 服务器IP地址列表ip1,ip2,ip3
	 **/
	public void setIps(String ips) {
		this.ips = ips;
	}
	/**
	 * 绑定的门店
	 **/
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * 绑定的商户编号
	 **/
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	/**
	 * 门店名称
	 **/
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	/**
	 * 商户名称
	 **/
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * 主键
	 **/
	public void setPayAuthId(String payAuthId) {
		this.payAuthId = payAuthId;
	}
	/**
	 * 支付加密秘钥公钥
	 **/
	public void setPayPubKey(String payPubKey) {
		this.payPubKey = payPubKey;
	}
	/**
	 * 第三方手续费费率比如0.003
	 **/
	public void setTpServiceCharges(BigDecimal tpServiceCharges) {
		this.tpServiceCharges = tpServiceCharges;
	}
	/**
	 * mdp平台附加手续费费率比如0.002
	 **/
	public void setMdpServiceCharges(BigDecimal mdpServiceCharges) {
		this.mdpServiceCharges = mdpServiceCharges;
	}
	/**
	 * mdp平台商户账户（一般商户编号+门店编号，用于mdp平台与该商户的结算）
	 **/
	public void setMdpPayAccountId(String mdpPayAccountId) {
		this.mdpPayAccountId = mdpPayAccountId;
	}
	
	/**
	 * 应用编号
	 **/
	public String getMdpAppid() {
		return this.mdpAppid;
	}
	/**
	 * 应用名称
	 **/
	public String getName() {
		return this.name;
	}
	/**
	 * 应用logo
	 **/
	public String getLogoUrl() {
		return this.logoUrl;
	}
	/**
	 * 应用描述
	 **/
	public String getRemark() {
		return this.remark;
	}
	/**
	 * 付款方式0余额1微信2V麦3支付宝4银行5网银6其他
	 **/
	public String getPayType() {
		return this.payType;
	}
	/**
	 * 归属部门
	 **/
	public String getDeptid() {
		return this.deptid;
	}
	/**
	 * 归属机构
	 **/
	public String getBranchId() {
		return this.branchId;
	}
	/**
	 * 创建日期
	 **/
	public Date getCdate() {
		return this.cdate;
	}
	/**
	 * 创建人
	 **/
	public String getCuserid() {
		return this.cuserid;
	}
	/**
	 * 应用状态0下架1上架
	 **/
	public String getEnabled() {
		return this.enabled;
	}
	/**
	 * 对应第三方提供给的appid
	 **/
	public String getAppid() {
		return this.appid;
	}
	/**
	 * 商户编号
	 **/
	public String getPayMchid() {
		return this.payMchid;
	}
	/**
	 * 付款成功通知地址
	 **/
	public String getPayNotifyUrl() {
		return this.payNotifyUrl;
	}
	/**
	 * 是否开通支付
	 **/
	public String getOpenPay() {
		return this.openPay;
	}
	/**
	 * 支付加密秘钥私钥
	 **/
	public String getPayKey() {
		return this.payKey;
	}
	/**
	 * 服务器IP地址列表ip1,ip2,ip3
	 **/
	public String getIps() {
		return this.ips;
	}
	/**
	 * 绑定的门店
	 **/
	public String getLocationId() {
		return this.locationId;
	}
	/**
	 * 绑定的商户编号
	 **/
	public String getShopId() {
		return this.shopId;
	}
	/**
	 * 门店名称
	 **/
	public String getLocationName() {
		return this.locationName;
	}
	/**
	 * 商户名称
	 **/
	public String getShopName() {
		return this.shopName;
	}
	/**
	 * 主键
	 **/
	public String getPayAuthId() {
		return this.payAuthId;
	}
	/**
	 * 支付加密秘钥公钥
	 **/
	public String getPayPubKey() {
		return this.payPubKey;
	}
	/**
	 * 第三方手续费费率比如0.003
	 **/
	public BigDecimal getTpServiceCharges() {
		return this.tpServiceCharges;
	}
	/**
	 * mdp平台附加手续费费率比如0.002
	 **/
	public BigDecimal getMdpServiceCharges() {
		return this.mdpServiceCharges;
	}
	/**
	 * mdp平台商户账户（一般商户编号+门店编号，用于mdp平台与该商户的结算）
	 **/
	public String getMdpPayAccountId() {
		return this.mdpPayAccountId;
	}

	public BigDecimal getMdpShopServiceCharges() {
		return mdpShopServiceCharges;
	}

	public void setMdpShopServiceCharges(BigDecimal mdpShopServiceCharges) {
		this.mdpShopServiceCharges = mdpShopServiceCharges;
	}

	public String getMdpScAccountId() {
		return mdpScAccountId;
	}

	public void setMdpScAccountId(String mdpScAccountId) {
		this.mdpScAccountId = mdpScAccountId;
	}

	public String getSettleShopId() {
		return settleShopId;
	}

	public void setSettleShopId(String settleShopId) {
		this.settleShopId = settleShopId;
	}

}