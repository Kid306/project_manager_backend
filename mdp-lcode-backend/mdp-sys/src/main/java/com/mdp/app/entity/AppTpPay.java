package  com.mdp.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 app  小模块 <br> 
 * 实体 AppTpPay所有属性名: <br>
 *	"mdpAppid","应用编号","name","应用名称","logoUrl","应用logo","remark","应用描述","payType","付款方式0余额1微信2V麦3支付宝4银行5网银6其他","deptid","归属部门","branchId","归属机构","cdate","创建日期","cuserid","创建人","enabled","应用状态0下架1上架","appid","对应第三方提供给的appid","payMchid","商户编号","payNotifyUrl","付款成功通知地址","openPay","是否开通支付","payKey","支付加密秘钥私钥","ips","服务器IP地址列表ip1,ip2,ip3","locationId","绑定的门店","shopId","绑定的商户编号","locationName","门店名称","shopName","商户名称","payAuthId","主键","payPubKey","支付加密秘钥公钥","tpServiceCharges","第三方手续费费率比如0.003","mdpServiceCharges","mdp平台附加手续费费率比如0.002","mdpPayAccountId","mdp平台分配给该商户（门店）预存款账户（一般商户编号-门店编号-01，用于mdp平台与该商户的结算），如果是总部店，为一般商户编号-0-01","mdpScAccountId","mdp平台手续费收取账户默认1001账户无论是门店交易还是商户交易，均要向平台缴纳一部分手续费","mdpShopServiceCharges","mdp平台商户手续费费率一般为门店订单交易需要支付一定的手续费给商户","settleShopId","结算商户编号如果是单商户模式、则为自己，否则为平台商户编号";<br>
 * 当前主键(包括多主键):<br>
 *	pay_auth_id;<br>
 */
 @Data
@TableName("app_tp_pay")
@ApiModel(description="MDP平台应用与第三方支付关系表")
public class AppTpPay  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String payAuthId;
	
	@ApiModelProperty(notes="应用编号",allowEmptyValue=true,example="",allowableValues="")
	String mdpAppid;
	
	@ApiModelProperty(notes="应用名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="应用logo",allowEmptyValue=true,example="",allowableValues="")
	String logoUrl;
	
	@ApiModelProperty(notes="应用描述",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="付款方式0余额1微信2V麦3支付宝4银行5网银6其他",allowEmptyValue=true,example="",allowableValues="")
	String payType;
	
	@ApiModelProperty(notes="归属部门",allowEmptyValue=true,example="",allowableValues="")
	String deptid;
	
	@ApiModelProperty(notes="归属机构",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date cdate;
	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;
	
	@ApiModelProperty(notes="应用状态0下架1上架",allowEmptyValue=true,example="",allowableValues="")
	String enabled;
	
	@ApiModelProperty(notes="对应第三方提供给的appid",allowEmptyValue=true,example="",allowableValues="")
	String appid;
	
	@ApiModelProperty(notes="商户编号",allowEmptyValue=true,example="",allowableValues="")
	String payMchid;
	
	@ApiModelProperty(notes="付款成功通知地址",allowEmptyValue=true,example="",allowableValues="")
	String payNotifyUrl;
	
	@ApiModelProperty(notes="是否开通支付",allowEmptyValue=true,example="",allowableValues="")
	String openPay;
	
	@ApiModelProperty(notes="支付加密秘钥私钥",allowEmptyValue=true,example="",allowableValues="")
	String payKey;
	
	@ApiModelProperty(notes="服务器IP地址列表ip1,ip2,ip3",allowEmptyValue=true,example="",allowableValues="")
	String ips;
	
	@ApiModelProperty(notes="绑定的门店",allowEmptyValue=true,example="",allowableValues="")
	String locationId;
	
	@ApiModelProperty(notes="绑定的商户编号",allowEmptyValue=true,example="",allowableValues="")
	String shopId;
	
	@ApiModelProperty(notes="门店名称",allowEmptyValue=true,example="",allowableValues="")
	String locationName;
	
	@ApiModelProperty(notes="商户名称",allowEmptyValue=true,example="",allowableValues="")
	String shopName;
	
	@ApiModelProperty(notes="支付加密秘钥公钥",allowEmptyValue=true,example="",allowableValues="")
	String payPubKey;
	
	@ApiModelProperty(notes="第三方手续费费率比如0.003",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal tpServiceCharges;
	
	@ApiModelProperty(notes="mdp平台附加手续费费率比如0.002",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal mdpServiceCharges;
	
	@ApiModelProperty(notes="mdp平台分配给该商户（门店）预存款账户（一般商户编号-门店编号-01，用于mdp平台与该商户的结算），如果是总部店，为一般商户编号-0-01",allowEmptyValue=true,example="",allowableValues="")
	String mdpPayAccountId;
	
	@ApiModelProperty(notes="mdp平台手续费收取账户默认1001账户无论是门店交易还是商户交易，均要向平台缴纳一部分手续费",allowEmptyValue=true,example="",allowableValues="")
	String mdpScAccountId;
	
	@ApiModelProperty(notes="mdp平台商户手续费费率一般为门店订单交易需要支付一定的手续费给商户",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal mdpShopServiceCharges;
	
	@ApiModelProperty(notes="结算商户编号如果是单商户模式、则为自己，否则为平台商户编号",allowEmptyValue=true,example="",allowableValues="")
	String settleShopId;

	/**
	 *主键
	 **/
	public AppTpPay(String payAuthId) {
		this.payAuthId = payAuthId;
	}
    
    /**
     * MDP平台应用与第三方支付关系表
     **/
	public AppTpPay() {
	}

}