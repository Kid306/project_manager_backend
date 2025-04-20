package  com.mdp.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 组织 com  顶级模块 mdp 大模块 app  小模块 <br> 
 * 实体 AppShopConfig所有属性名: <br>
 *	"shopBranchId","商户自身归属机构号","shopId","商户编号","tpServiceCharges","第三方手续费费率如支付宝/微信支付的费率比如0.003","platServiceCharges","mdp平台附加手续费费率比如0.002,即该商户下所有交易需要支付给平台的手续费费率","platShopDepositAccountId","mdp平台分配给该商户（门店）预存款账户（一般商户编号-门店编号-01，用于mdp平台与该商户的结算，商户充值等），如果是总部店，为一般商户编号-0-01","platAssetAccountId","平台资产账户，商户缴纳给平台手续费等，从商户预存款账户转出，转入该账户计入平台主营业务收入","platShopId","归属平台商户编号","shopServiceCharges","mdp平台商户手续费费率一般为门店订单交易需要支付一定的手续费给商户","shopAssetAccountId","商户内部主营业务收入账户，在途资金到账后，转入主营业务收入","isPlatSc","商户是否需要向平台缴纳手续费","isShopSc","门店是否需要向商户缴纳手续费","acctPrjType","核算项目platform-由平台结算，shop-由商户独立结算，location-由门店独立结算","paySetType","支付结算方式platform-使用平台支付通道结算，shop-使用商户结算通道，location-使用门店独立结算通道","setLevel","结算级别1-商户2-门店，将在途资金账户余额归入商户的预存款账户还是门店预存款账户","allowLocationWithdraw","是否允许门店提现0否1允许3按门店配置自定义","settleShopId","归属计算商户pay_set_type=platform时，指向平台商户编号platform_shop_id，pay_set_type=shop时指向本商户编号shop_id","isJoinPlat","是否加入平台，如果加入平台将在平台的大商城可以展现数据-同步到shop表，避免多表关联","shopDepositAccountId","商户预存款账户，shop模式下，作为客户","settleBranchId","结算商户对应的机构号","platBranchId","商户归属的平台机构号","headLocationId","总部店铺编号-一般取商户号-0代表总部店铺","shopName","商户名称","headLocationName","总部店名称","signValue","签名信息","lastUpdateTime","最后更新时间yyyy-MM-dd";<br>
 * 当前主键(包括多主键):<br>
 *	shop_id;<br>
 */
 @Data
@TableName("app_shop_config")
@ApiModel(description="app_shop_config")
public class AppShopConfig  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="商户编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String shopId;
	
	@ApiModelProperty(notes="商户自身归属机构号",allowEmptyValue=true,example="",allowableValues="")
	String shopBranchId;
	
	@ApiModelProperty(notes="第三方手续费费率如支付宝/微信支付的费率比如0.003",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal tpServiceCharges;
	
	@ApiModelProperty(notes="mdp平台附加手续费费率比如0.002,即该商户下所有交易需要支付给平台的手续费费率",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal platServiceCharges;
	
	@ApiModelProperty(notes="mdp平台分配给该商户（门店）预存款账户（一般商户编号-门店编号-01，用于mdp平台与该商户的结算，商户充值等），如果是总部店，为一般商户编号-0-01",allowEmptyValue=true,example="",allowableValues="")
	String platShopDepositAccountId;
	
	@ApiModelProperty(notes="平台资产账户，商户缴纳给平台手续费等，从商户预存款账户转出，转入该账户计入平台主营业务收入",allowEmptyValue=true,example="",allowableValues="")
	String platAssetAccountId;
	
	@ApiModelProperty(notes="归属平台商户编号",allowEmptyValue=true,example="",allowableValues="")
	String platShopId;
	
	@ApiModelProperty(notes="mdp平台商户手续费费率一般为门店订单交易需要支付一定的手续费给商户",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal shopServiceCharges;
	
	@ApiModelProperty(notes="商户内部主营业务收入账户，在途资金到账后，转入主营业务收入",allowEmptyValue=true,example="",allowableValues="")
	String shopAssetAccountId;
	
	@ApiModelProperty(notes="商户是否需要向平台缴纳手续费",allowEmptyValue=true,example="",allowableValues="")
	String isPlatSc;
	
	@ApiModelProperty(notes="门店是否需要向商户缴纳手续费",allowEmptyValue=true,example="",allowableValues="")
	String isShopSc;
	
	@ApiModelProperty(notes="核算项目platform-由平台结算，shop-由商户独立结算，location-由门店独立结算",allowEmptyValue=true,example="",allowableValues="")
	String acctPrjType;
	
	@ApiModelProperty(notes="支付结算方式platform-使用平台支付通道结算，shop-使用商户结算通道，location-使用门店独立结算通道",allowEmptyValue=true,example="",allowableValues="")
	String paySetType;
	
	@ApiModelProperty(notes="结算级别1-商户2-门店，将在途资金账户余额归入商户的预存款账户还是门店预存款账户",allowEmptyValue=true,example="",allowableValues="")
	String setLevel;
	
	@ApiModelProperty(notes="是否允许门店提现0否1允许3按门店配置自定义",allowEmptyValue=true,example="",allowableValues="")
	String allowLocationWithdraw;
	
	@ApiModelProperty(notes="归属计算商户pay_set_type=platform时，指向平台商户编号platform_shop_id，pay_set_type=shop时指向本商户编号shop_id",allowEmptyValue=true,example="",allowableValues="")
	String settleShopId;
	
	@ApiModelProperty(notes="是否加入平台，如果加入平台将在平台的大商城可以展现数据-同步到shop表，避免多表关联",allowEmptyValue=true,example="",allowableValues="")
	String isJoinPlat;
	
	@ApiModelProperty(notes="商户预存款账户，shop模式下，作为客户",allowEmptyValue=true,example="",allowableValues="")
	String shopDepositAccountId;
	
	@ApiModelProperty(notes="结算商户对应的机构号",allowEmptyValue=true,example="",allowableValues="")
	String settleBranchId;
	
	@ApiModelProperty(notes="商户归属的平台机构号",allowEmptyValue=true,example="",allowableValues="")
	String platBranchId;
	
	@ApiModelProperty(notes="总部店铺编号-一般取商户号-0代表总部店铺",allowEmptyValue=true,example="",allowableValues="")
	String headLocationId;
	
	@ApiModelProperty(notes="商户名称",allowEmptyValue=true,example="",allowableValues="")
	String shopName;
	
	@ApiModelProperty(notes="总部店名称",allowEmptyValue=true,example="",allowableValues="")
	String headLocationName;
	
	@ApiModelProperty(notes="签名信息",allowEmptyValue=true,example="",allowableValues="")
	String signValue;
	
	@ApiModelProperty(notes="最后更新时间yyyy-MM-dd",allowEmptyValue=true,example="",allowableValues="")
	String lastUpdateTime;

	/**
	 *商户编号
	 **/
	public AppShopConfig(String shopId) {
		this.shopId = shopId;
	}
    
    /**
     * app_shop_config
     **/
	public AppShopConfig() {
	}

}