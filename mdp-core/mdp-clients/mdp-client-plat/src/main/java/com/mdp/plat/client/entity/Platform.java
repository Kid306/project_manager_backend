package com.mdp.plat.client.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.HashMap;

/**
 * 组织 com  顶级模块 mdp 大模块 plat  小模块 <br> 
 * 实体 Platform所有属性名: <br>
 *	id,platformName,status,ctime,ltime,cuserid,cusername,logoUrl,platformTitle,keywords,minBuyAmount,autoConfirmReceipt,canBill,billContextList,cutStock,extConfig,plusSales,evaluate,discountPct,usePriceGroup,shopId,branchId;<br>
 * 表 ADM.plat_platform plat_platform的所有字段名: <br>
 *	id,platform_name,status,ctime,ltime,cuserid,cusername,logo_url,platform_title,keywords,min_buy_amount,auto_confirm_receipt,can_bill,bill_context_list,cut_stock,ext_config,plus_sales,evaluate,discount_pct,use_price_group,shop_id,branch_id;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@Schema(description="plat_platform")
public class Platform extends HashMap<String,Object> implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//@Schema(description="平台编号建议为platform-001,主键")
	//String id;
  	
	
	//@Schema(description="平台名称")
	//String platformName;
	
	//@Schema(description="0-未启用，1-已启用，只能有一条数据有效")
	//String status;
	
	//@Schema(description="创建时间")
	//Date ctime;
	
	//@Schema(description="更新时间")
	//Date ltime;
	
	//@Schema(description="创建人编号")
	//String cuserid;
	
	//@Schema(description="创建人姓名")
	//String cusername;
	
	//@Schema(description="平台logo图片地址")
	//String logoUrl;
	
	//@Schema(description="前端显示的平台名称")
	//String platformTitle;
	
	//@Schema(description="关键词逗号分割")
	//String keywords;
	
	//@Schema(description="最小购买金额，达到此购物金额，才能提交订单")
	//Integer minBuyAmount;
	
	//@Schema(description="自动确认收货0否1是，确认收货时长请在订单设置中进行设置")
	//String autoConfirmReceipt;
	
	//@Schema(description="是否可开发票0否1是")
	//String canBill;
	
	//@Schema(description="发票内容选项,逗号分割")
	//String billContextList;
	
	//@Schema(description="扣减库存时机0-下单付款完成，1-发货后")
	//String cutStock;
	
	//@Schema(description="其它全局控制参数")
	//String extConfig;
	
	//@Schema(description="增加销量时机0-发货后，1-付款后")
	//String plusSales;
	
	//@Schema(description="评价是否需要审核0否1是")
	//String evaluate;
	
	//@Schema(description="全局折扣0~100之间")
	//Integer discountPct;
	
	//@Schema(description="是否使用价格套0否1是")
	//String usePriceGroup;
	
	//@Schema(description="平台商户编号默认platform-shop-001")
	//String shopId;
	
	//@Schema(description="平台机构编号默认platform-branch-001")
	//String branchId;

	//@Schema(description="平台locationId shopId-01")
	//String branchId;

	/**平台编号建议为platform-001**/
	public Platform(String id) {
		super.put("id",id);
	}
    
    /**plat_platform**/
	public Platform() {
	}
	
	/**
	 * 平台编号建议为platform-001
	 **/
	public void setId(String id) {
		this.put("id",id);
	}
	/**
	 * 平台名称
	 **/
	public void setPlatformName(String platformName) {
		this.put("platformName", platformName);
	}
	/**
	 * 0-未启用，1-已启用，只能有一条数据有效
	 **/
	public void setStatus(String status) {
		this.put("status", status);
	}
	/**
	 * 创建时间
	 **/
	public void setCtime(Date ctime) {
		this.put("ctime", ctime);
	}
	/**
	 * 更新时间
	 **/
	public void setLtime(Date ltime) {
		this.put("ltime", ltime);
	}
	/**
	 * 创建人编号
	 **/
	public void setCuserid(String cuserid) {
		this.put("cuserid", cuserid);
	}
	/**
	 * 创建人姓名
	 **/
	public void setCusername(String cusername) {
		this.put("cusername", cusername);
	}
	/**
	 * 平台logo图片地址
	 **/
	public void setLogoUrl(String logoUrl) {
		this.put("logoUrl", logoUrl);
	}
	/**
	 * 前端显示的平台名称
	 **/
	public void setPlatformTitle(String platformTitle) {
		this.put("platformTitle", platformTitle);
	}
	/**
	 * 关键词逗号分割
	 **/
	public void setKeywords(String keywords) {
		this.put("keywords", keywords);
	}
	/**
	 * 最小购买金额，达到此购物金额，才能提交订单
	 **/
	public void setMinBuyAmount(Integer minBuyAmount) {
		this.put("minBuyAmount", minBuyAmount);
	}
	/**
	 * 自动确认收货0否1是，确认收货时长请在订单设置中进行设置
	 **/
	public void setAutoConfirmReceipt(String autoConfirmReceipt) {
		this.put("autoConfirmReceipt", autoConfirmReceipt);
	}
	/**
	 * 是否可开发票0否1是
	 **/
	public void setCanBill(String canBill) {
		this.put("canBill", canBill);
	}
	/**
	 * 发票内容选项,逗号分割
	 **/
	public void setBillContextList(String billContextList) {
		this.put("billContextList", billContextList);
	}
	/**
	 * 扣减库存时机0-下单付款完成，1-发货后
	 **/
	public void setCutStock(String cutStock) {
		this.put("cutStock", cutStock);
	}
	/**
	 * 其它全局控制参数
	 **/
	public void setExtConfig(String extConfig) {
		this.put("extConfig", extConfig);
	}
	/**
	 * 增加销量时机0-发货后，1-付款后
	 **/
	public void setPlusSales(String plusSales) {
		this.put("plusSales", plusSales);
	}
	/**
	 * 评价是否需要审核0否1是
	 **/
	public void setEvaluate(String evaluate) {
		this.put("evaluate", evaluate);
	}
	/**
	 * 全局折扣0~100之间
	 **/
	public void setDiscountPct(Integer discountPct) {
		this.put("discountPct", discountPct);
	}
	/**
	 * 是否使用价格套0否1是
	 **/
	public void setUsePriceGroup(String usePriceGroup) {
		this.put("usePriceGroup", usePriceGroup);
	}
	/**
	 * 平台商户编号默认platform-shop-001
	 **/
	public void setShopId(String shopId) {
		this.put("shopId", shopId);
	}
	/**
	 * 平台机构编号默认platform-branch-001
	 **/
	public void setBranchId(String branchId) {
		this.put("branchId", branchId);
	}
	/**
	 * 平台商户总部店铺
	 **/
	public void setLocationId(String locationId) {
		this.put("locationId", locationId);
	}
	
	/**
	 * 平台编号建议为platform-001
	 **/
	public String getId() {
		return (String)this.get("id");
	}
	/**
	 * 平台名称
	 **/
	public String getPlatformName() {
		return (String)this.get("platformName");
	}
	/**
	 * 0-未启用，1-已启用，只能有一条数据有效
	 **/
	public String getStatus() {
		return (String)this.get("status");
	}
	/**
	 * 创建时间
	 **/
	public Date getCtime() {
		return (Date)this.get("ctime");
	}
	/**
	 * 更新时间
	 **/
	public Date getLtime() {
		return (Date)this.get("ltime");
	}
	/**
	 * 创建人编号
	 **/
	public String getCuserid() {
		return (String)this.get("cuserid");
	}
	/**
	 * 创建人姓名
	 **/
	public String getCusername() {
		return (String)this.get("cusername");
	}
	/**
	 * 平台logo图片地址
	 **/
	public String getLogoUrl() {
		return (String)this.get("logoUrl");
	}
	/**
	 * 前端显示的平台名称
	 **/
	public String getPlatformTitle() {
		return (String)this.get("platformTitle");
	}
	/**
	 * 关键词逗号分割
	 **/
	public String getKeywords() {
		return (String)this.get("keywords");
	}
	/**
	 * 最小购买金额，达到此购物金额，才能提交订单
	 **/
	public Integer getMinBuyAmount() {
		return (Integer) this.get("minBuyAmount");
	}
	/**
	 * 自动确认收货0否1是，确认收货时长请在订单设置中进行设置
	 **/
	public String getAutoConfirmReceipt() {
		return (String)this.get("autoConfirmReceipt");
	}
	/**
	 * 是否可开发票0否1是
	 **/
	public String getCanBill() {
		return (String)this.get("canBill");
	}
	/**
	 * 发票内容选项,逗号分割
	 **/
	public String getBillContextList() {
		return (String)this.get("billContextList");
	}
	/**
	 * 扣减库存时机0-下单付款完成，1-发货后
	 **/
	public String getCutStock() {
		return (String)this.get("cutStock");
	}
	/**
	 * 其它全局控制参数
	 **/
	public String getExtConfig() {
		return (String)this.get("extConfig");
	}
	/**
	 * 增加销量时机0-发货后，1-付款后
	 **/
	public String getPlusSales() {
		return (String)this.get("plusSales");
	}
	/**
	 * 评价是否需要审核0否1是
	 **/
	public String getEvaluate() {
		return (String)this.get("evaluate");
	}
	/**
	 * 全局折扣0~100之间
	 **/
	public Integer getDiscountPct() {
		return (Integer) this.get("discountPct");
	}
	/**
	 * 是否使用价格套0否1是
	 **/
	public String getUsePriceGroup() {
		return (String)this.get("usePriceGroup");
	}
	/**
	 * 平台商户编号默认platform-shop-001
	 **/
	public String getShopId() {
		return (String)this.get("shopId");
	}
	/**
	 * 平台机构编号默认platform-branch-001
	 **/
	public String getBranchId() {
		return (String) this.get("branchId");
	}
	/**
	 * 平台总部商店编号
	 **/
	public String getLocationId() {
		return (String) this.get("locationId");
	}

	/**
	 * 扩展字段
	 **/
	public String getExtInfos() {
		return (String) this.get("extInfos");
	}

	/**
	 * 扩展字段
	 **/
	public void setExtInfos(String extInfos) {
		this.put ("extInfos",extInfos);
	}
}