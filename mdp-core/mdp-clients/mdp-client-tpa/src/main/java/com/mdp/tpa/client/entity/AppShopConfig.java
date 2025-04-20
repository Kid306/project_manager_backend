package com.mdp.tpa.client.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * 组织 com  顶级模块 mdp 大模块 app  小模块 <br> 
 * 实体 AppShopConfig所有属性名: <br>
 *	shopBranchId,shopId,tpServiceCharges,platServiceCharges,platShopDepositAccountId,platAssetAccountId,platShopId,shopServiceCharges,shopAssetAccountId,isPlatSc,isShopSc,acctPrjType,paySetType,setLevel,allowLocationWithdraw,settleShopId,isJoinPlat,shopDepositAccountId,settleBranchId,platBranchId,headLocationId,shopName,headLocationName,signValue,lastUpdateTime;<br>
 * 表 ADM.app_shop_config app_shop_config的所有字段名: <br>
 *	shop_branch_id,shop_id,tp_service_charges,plat_service_charges,plat_shop_deposit_account_id,plat_asset_account_id,plat_shop_id,shop_service_charges,shop_asset_account_id,is_plat_sc,is_shop_sc,acct_prj_type,pay_set_type,set_level,allow_location_withdraw,settle_shop_id,is_join_plat,shop_deposit_account_id,settle_branch_id,plat_branch_id,head_location_id,shop_name,head_location_name,sign_value,last_update_time;<br>
 * 当前主键(包括多主键):<br>
 *	shop_id;<br>
 */
@Schema(description="app_shop_config")
public class AppShopConfig implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Schema(description="商户编号,主键")
	String shopId;
  	
	
	@Schema(description="商户自身归属机构号")
	String shopBranchId;
	
	@Schema(description="第三方手续费费率如支付宝/微信支付的费率比如0.003")
	BigDecimal tpServiceCharges;
	
	@Schema(description="mdp平台附加手续费费率比如0.002,即该商户下所有交易需要支付给平台的手续费费率")
	BigDecimal platServiceCharges;
	
	@Schema(description="mdp平台分配给该商户（门店）预存款账户（一般商户编号-门店编号-01，用于mdp平台与该商户的结算，商户充值等），如果是总部店，为一般商户编号-0-01")
	String platShopDepositAccountId;
	
	@Schema(description="平台资产账户，商户缴纳给平台手续费等，从商户预存款账户转出，转入该账户计入平台主营业务收入")
	String platAssetAccountId;
	
	@Schema(description="归属平台商户编号")
	String platShopId;
	
	@Schema(description="mdp平台商户手续费费率一般为门店订单交易需要支付一定的手续费给商户")
	BigDecimal shopServiceCharges;
	
	@Schema(description="商户内部主营业务收入账户，在途资金到账后，转入主营业务收入")
	String shopAssetAccountId;
	
	@Schema(description="商户是否需要向平台缴纳手续费")
	String isPlatSc;
	
	@Schema(description="门店是否需要向商户缴纳手续费")
	String isShopSc;
	
	@Schema(description="核算项目platform-由平台结算，shop-由商户独立结算，location-由门店独立结算")
	String acctPrjType;
	
	@Schema(description="支付结算方式platform-使用平台支付通道结算，shop-使用商户结算通道，location-使用门店独立结算通道")
	String paySetType;
	
	@Schema(description="结算级别1-商户2-门店，将在途资金账户余额归入商户的预存款账户还是门店预存款账户")
	String setLevel;
	
	@Schema(description="是否允许门店提现0否1允许3按门店配置自定义")
	String allowLocationWithdraw;
	
	@Schema(description="归属计算商户pay_set_type=platform时，指向平台商户编号platform_shop_id，pay_set_type=shop时指向本商户编号shop_id")
	String settleShopId;
	
	@Schema(description="是否加入平台，如果加入平台将在平台的大商城可以展现数据-同步到shop表，避免多表关联")
	String isJoinPlat;
	
	@Schema(description="商户预存款账户，shop模式下，作为客户")
	String shopDepositAccountId;
	
	@Schema(description="结算商户对应的机构号")
	String settleBranchId;
	
	@Schema(description="商户归属的平台机构号")
	String platBranchId;
	
	@Schema(description="总部店铺编号")
	String headLocationId;
	
	@Schema(description="商户名称")
	String shopName;
	
	@Schema(description="总部店名称")
	String headLocationName;
	
	@Schema(description="签名信息")
	String signValue;
	
	@Schema(description="最后更新时间yyyy-MM-dd")
	String lastUpdateTime;

	/**商户编号**/
	public AppShopConfig(String shopId) {
		this.shopId = shopId;
	}
    
    /**app_shop_config**/
	public AppShopConfig() {
	}
	
	/**
	 * 商户自身归属机构号
	 **/
	public void setShopBranchId(String shopBranchId) {
		this.shopBranchId = shopBranchId;
	}
	/**
	 * 商户编号
	 **/
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	/**
	 * 第三方手续费费率如支付宝/微信支付的费率比如0.003
	 **/
	public void setTpServiceCharges(BigDecimal tpServiceCharges) {
		this.tpServiceCharges = tpServiceCharges;
	}
	/**
	 * mdp平台附加手续费费率比如0.002,即该商户下所有交易需要支付给平台的手续费费率
	 **/
	public void setPlatServiceCharges(BigDecimal platServiceCharges) {
		this.platServiceCharges = platServiceCharges;
	}
	/**
	 * mdp平台分配给该商户（门店）预存款账户（一般商户编号-门店编号-01，用于mdp平台与该商户的结算，商户充值等），如果是总部店，为一般商户编号-0-01
	 **/
	public void setPlatShopDepositAccountId(String platShopDepositAccountId) {
		this.platShopDepositAccountId = platShopDepositAccountId;
	}
	/**
	 * 平台资产账户，商户缴纳给平台手续费等，从商户预存款账户转出，转入该账户计入平台主营业务收入
	 **/
	public void setPlatAssetAccountId(String platAssetAccountId) {
		this.platAssetAccountId = platAssetAccountId;
	}
	/**
	 * 归属平台商户编号
	 **/
	public void setPlatShopId(String platShopId) {
		this.platShopId = platShopId;
	}
	/**
	 * mdp平台商户手续费费率一般为门店订单交易需要支付一定的手续费给商户
	 **/
	public void setShopServiceCharges(BigDecimal shopServiceCharges) {
		this.shopServiceCharges = shopServiceCharges;
	}
	/**
	 * 商户内部主营业务收入账户，在途资金到账后，转入主营业务收入
	 **/
	public void setShopAssetAccountId(String shopAssetAccountId) {
		this.shopAssetAccountId = shopAssetAccountId;
	}
	/**
	 * 商户是否需要向平台缴纳手续费
	 **/
	public void setIsPlatSc(String isPlatSc) {
		this.isPlatSc = isPlatSc;
	}
	/**
	 * 门店是否需要向商户缴纳手续费
	 **/
	public void setIsShopSc(String isShopSc) {
		this.isShopSc = isShopSc;
	}
	/**
	 * 核算项目platform-由平台结算，shop-由商户独立结算，location-由门店独立结算
	 **/
	public void setAcctPrjType(String acctPrjType) {
		this.acctPrjType = acctPrjType;
	}
	/**
	 * 支付结算方式platform-使用平台支付通道结算，shop-使用商户结算通道，location-使用门店独立结算通道
	 **/
	public void setPaySetType(String paySetType) {
		this.paySetType = paySetType;
	}
	/**
	 * 结算级别1-商户2-门店，将在途资金账户余额归入商户的预存款账户还是门店预存款账户
	 **/
	public void setSetLevel(String setLevel) {
		this.setLevel = setLevel;
	}
	/**
	 * 是否允许门店提现0否1允许3按门店配置自定义
	 **/
	public void setAllowLocationWithdraw(String allowLocationWithdraw) {
		this.allowLocationWithdraw = allowLocationWithdraw;
	}
	/**
	 * 归属计算商户pay_set_type=platform时，指向平台商户编号platform_shop_id，pay_set_type=shop时指向本商户编号shop_id
	 **/
	public void setSettleShopId(String settleShopId) {
		this.settleShopId = settleShopId;
	}
	/**
	 * 是否加入平台，如果加入平台将在平台的大商城可以展现数据-同步到shop表，避免多表关联
	 **/
	public void setIsJoinPlat(String isJoinPlat) {
		this.isJoinPlat = isJoinPlat;
	}
	/**
	 * 商户预存款账户，shop模式下，作为客户
	 **/
	public void setShopDepositAccountId(String shopDepositAccountId) {
		this.shopDepositAccountId = shopDepositAccountId;
	}
	/**
	 * 结算商户对应的机构号
	 **/
	public void setSettleBranchId(String settleBranchId) {
		this.settleBranchId = settleBranchId;
	}
	/**
	 * 商户归属的平台机构号
	 **/
	public void setPlatBranchId(String platBranchId) {
		this.platBranchId = platBranchId;
	}
	/**
	 * 总部店铺编号
	 **/
	public void setHeadLocationId(String headLocationId) {
		this.headLocationId = headLocationId;
	}
	/**
	 * 商户名称
	 **/
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * 总部店名称
	 **/
	public void setHeadLocationName(String headLocationName) {
		this.headLocationName = headLocationName;
	}
	/**
	 * 签名信息
	 **/
	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
	/**
	 * 最后更新时间yyyy-MM-dd
	 **/
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	/**
	 * 商户自身归属机构号
	 **/
	public String getShopBranchId() {
		return this.shopBranchId;
	}
	/**
	 * 商户编号
	 **/
	public String getShopId() {
		return this.shopId;
	}
	/**
	 * 第三方手续费费率如支付宝/微信支付的费率比如0.003
	 **/
	public BigDecimal getTpServiceCharges() {
		return this.tpServiceCharges;
	}
	/**
	 * mdp平台附加手续费费率比如0.002,即该商户下所有交易需要支付给平台的手续费费率
	 **/
	public BigDecimal getPlatServiceCharges() {
		return this.platServiceCharges;
	}
	/**
	 * mdp平台分配给该商户（门店）预存款账户（一般商户编号-门店编号-01，用于mdp平台与该商户的结算，商户充值等），如果是总部店，为一般商户编号-0-01
	 **/
	public String getPlatShopDepositAccountId() {
		return this.platShopDepositAccountId;
	}
	/**
	 * 平台资产账户，商户缴纳给平台手续费等，从商户预存款账户转出，转入该账户计入平台主营业务收入
	 **/
	public String getPlatAssetAccountId() {
		return this.platAssetAccountId;
	}
	/**
	 * 归属平台商户编号
	 **/
	public String getPlatShopId() {
		return this.platShopId;
	}
	/**
	 * mdp平台商户手续费费率一般为门店订单交易需要支付一定的手续费给商户
	 **/
	public BigDecimal getShopServiceCharges() {
		return this.shopServiceCharges;
	}
	/**
	 * 商户内部主营业务收入账户，在途资金到账后，转入主营业务收入
	 **/
	public String getShopAssetAccountId() {
		return this.shopAssetAccountId;
	}
	/**
	 * 商户是否需要向平台缴纳手续费
	 **/
	public String getIsPlatSc() {
		return this.isPlatSc;
	}
	/**
	 * 门店是否需要向商户缴纳手续费
	 **/
	public String getIsShopSc() {
		return this.isShopSc;
	}
	/**
	 * 核算项目platform-由平台结算，shop-由商户独立结算，location-由门店独立结算
	 **/
	public String getAcctPrjType() {
		return this.acctPrjType;
	}
	/**
	 * 支付结算方式platform-使用平台支付通道结算，shop-使用商户结算通道，location-使用门店独立结算通道
	 **/
	public String getPaySetType() {
		return this.paySetType;
	}
	/**
	 * 结算级别1-商户2-门店，将在途资金账户余额归入商户的预存款账户还是门店预存款账户
	 **/
	public String getSetLevel() {
		return this.setLevel;
	}
	/**
	 * 是否允许门店提现0否1允许3按门店配置自定义
	 **/
	public String getAllowLocationWithdraw() {
		return this.allowLocationWithdraw;
	}
	/**
	 * 归属计算商户pay_set_type=platform时，指向平台商户编号platform_shop_id，pay_set_type=shop时指向本商户编号shop_id
	 **/
	public String getSettleShopId() {
		return this.settleShopId;
	}
	/**
	 * 是否加入平台，如果加入平台将在平台的大商城可以展现数据-同步到shop表，避免多表关联
	 **/
	public String getIsJoinPlat() {
		return this.isJoinPlat;
	}
	/**
	 * 商户预存款账户，shop模式下，作为客户
	 **/
	public String getShopDepositAccountId() {
		return this.shopDepositAccountId;
	}
	/**
	 * 结算商户对应的机构号
	 **/
	public String getSettleBranchId() {
		return this.settleBranchId;
	}
	/**
	 * 商户归属的平台机构号
	 **/
	public String getPlatBranchId() {
		return this.platBranchId;
	}
	/**
	 * 总部店铺编号
	 **/
	public String getHeadLocationId() {
		return this.headLocationId;
	}
	/**
	 * 商户名称
	 **/
	public String getShopName() {
		return this.shopName;
	}
	/**
	 * 总部店名称
	 **/
	public String getHeadLocationName() {
		return this.headLocationName;
	}
	/**
	 * 签名信息
	 **/
	public String getSignValue() {
		return this.signValue;
	}
	/**
	 * 最后更新时间yyyy-MM-dd
	 **/
	public String getLastUpdateTime() {
		return this.lastUpdateTime;
	}

}