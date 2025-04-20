package com.mdp.safe.client.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * 组织 com  顶级模块 mdp 大模块 menu  小模块 <br>
 * 实体 MenuModule所有属性名: <br>
 *	id,name,fee,billMode,uniFee,discount,url,ignoreUrl,bizFlowState,procInstId,status,mcate,logoUrl,feeDesc,udisRate,udis,saleDesc,ucheck,crowd,seq;<br>
 * 表 menu_module 模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问的所有字段名: <br>
 *	id,name,fee,bill_mode,uni_fee,discount,url,ignore_url,biz_flow_state,proc_inst_id,status,mcate,logo_url,fee_desc,udis_rate,udis,sale_desc,ucheck,crowd,seq;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@Schema(description="模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问")
public class Module implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@TableId
	@Schema(description="编号,主键")
	String id;


	@Schema(description="模块名称")
	String name;

	@Schema(description="模块费用")
	BigDecimal fee;

	@Schema(description="计费模式：0-不计费，1-按购买人数计费，2-总包费用,3-按实际使用人数每月计费")
	String billMode;

	@Schema(description="人均费用")
	BigDecimal uniFee;

	@Schema(description="折扣比率，可折上折，叠加折扣。-按最大人数、按月数优惠")
	String discount;

	@Schema(description="匹配的url,如果匹配得到，则计费，以逗号分割多个")
	String url;

	@Schema(description="匹配这个地址的不计费")
	String ignoreUrl;

	@Schema(description="审核状态")
	String bizFlowState;

	@Schema(description="审核流程实例编号")
	String procInstId;

	@Schema(description="0-下架，1-上架。开放后才可以购买使用")
	String status;

	@Schema(description="分类1-协同、2-研发、3-电商")
	String mcate;

	@Schema(description="logo地址")
	String logoUrl;

	@Schema(description="费用解释")
	String feeDesc;

	@Schema(description="人数折算比例。购买总人数*折扣率为当前模块购买人数")
	Integer udisRate;

	@Schema(description="是否折算人数0否1是")
	String udis;

	@Schema(description="营销文案")
	String saleDesc;

	@Schema(description="是否控制总人数0-否1是")
	String ucheck;

	@Schema(description="是否为众包")
	String crowd;

	@Schema(description="序号0-1000，默认999")
	Integer seq;

	/**编号**/
	public Module(String id) {
		this.id = id;
	}

	/**模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问**/
	public Module() {
	}

	/**
	 * 编号
	 **/
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 模块名称
	 **/
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 模块费用
	 **/
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	/**
	 * 计费模式：0-不计费，1-按购买人数计费，2-总包费用,3-按实际使用人数每月计费
	 **/
	public void setBillMode(String billMode) {
		this.billMode = billMode;
	}
	/**
	 * 人均费用
	 **/
	public void setUniFee(BigDecimal uniFee) {
		this.uniFee = uniFee;
	}
	/**
	 * 折扣比率，可折上折，叠加折扣。-按最大人数、按月数优惠
	 **/
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	/**
	 * 匹配的url,如果匹配得到，则计费，以逗号分割多个
	 **/
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 匹配这个地址的不计费
	 **/
	public void setIgnoreUrl(String ignoreUrl) {
		this.ignoreUrl = ignoreUrl;
	}
	/**
	 * 审核状态
	 **/
	public void setBizFlowState(String bizFlowState) {
		this.bizFlowState = bizFlowState;
	}
	/**
	 * 审核流程实例编号
	 **/
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	/**
	 * 0-下架，1-上架。开放后才可以购买使用
	 **/
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 分类1-协同、2-研发、3-电商
	 **/
	public void setMcate(String mcate) {
		this.mcate = mcate;
	}
	/**
	 * logo地址
	 **/
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * 费用解释
	 **/
	public void setFeeDesc(String feeDesc) {
		this.feeDesc = feeDesc;
	}
	/**
	 * 人数折算比例。购买总人数*折扣率为当前模块购买人数
	 **/
	public void setUdisRate(Integer udisRate) {
		this.udisRate = udisRate;
	}
	/**
	 * 是否折算人数0否1是
	 **/
	public void setUdis(String udis) {
		this.udis = udis;
	}
	/**
	 * 营销文案
	 **/
	public void setSaleDesc(String saleDesc) {
		this.saleDesc = saleDesc;
	}
	/**
	 * 是否控制总人数0-否1是
	 **/
	public void setUcheck(String ucheck) {
		this.ucheck = ucheck;
	}
	/**
	 * 是否为众包
	 **/
	public void setCrowd(String crowd) {
		this.crowd = crowd;
	}
	/**
	 * 序号0-1000，默认999
	 **/
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * 编号
	 **/
	public String getId() {
		return this.id;
	}
	/**
	 * 模块名称
	 **/
	public String getName() {
		return this.name;
	}
	/**
	 * 模块费用
	 **/
	public BigDecimal getFee() {
		return this.fee;
	}
	/**
	 * 计费模式：0-不计费，1-按购买人数计费，2-总包费用,3-按实际使用人数每月计费
	 **/
	public String getBillMode() {
		return this.billMode;
	}
	/**
	 * 人均费用
	 **/
	public BigDecimal getUniFee() {
		return this.uniFee;
	}
	/**
	 * 折扣比率，可折上折，叠加折扣。-按最大人数、按月数优惠
	 **/
	public String getDiscount() {
		return this.discount;
	}
	/**
	 * 匹配的url,如果匹配得到，则计费，以逗号分割多个
	 **/
	public String getUrl() {
		return this.url;
	}
	/**
	 * 匹配这个地址的不计费
	 **/
	public String getIgnoreUrl() {
		return this.ignoreUrl;
	}
	/**
	 * 审核状态
	 **/
	public String getBizFlowState() {
		return this.bizFlowState;
	}
	/**
	 * 审核流程实例编号
	 **/
	public String getProcInstId() {
		return this.procInstId;
	}
	/**
	 * 0-下架，1-上架。开放后才可以购买使用
	 **/
	public String getStatus() {
		return this.status;
	}
	/**
	 * 分类1-协同、2-研发、3-电商
	 **/
	public String getMcate() {
		return this.mcate;
	}
	/**
	 * logo地址
	 **/
	public String getLogoUrl() {
		return this.logoUrl;
	}
	/**
	 * 费用解释
	 **/
	public String getFeeDesc() {
		return this.feeDesc;
	}
	/**
	 * 人数折算比例。购买总人数*折扣率为当前模块购买人数
	 **/
	public Integer getUdisRate() {
		return this.udisRate;
	}
	/**
	 * 是否折算人数0否1是
	 **/
	public String getUdis() {
		return this.udis;
	}
	/**
	 * 营销文案
	 **/
	public String getSaleDesc() {
		return this.saleDesc;
	}
	/**
	 * 是否控制总人数0-否1是
	 **/
	public String getUcheck() {
		return this.ucheck;
	}
	/**
	 * 是否为众包
	 **/
	public String getCrowd() {
		return this.crowd;
	}
	/**
	 * 序号0-1000，默认999
	 **/
	public Integer getSeq() {
		return this.seq;
	}

}