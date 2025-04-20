package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 GuardOrder所有属性名: <br>
 *	"id","订单编号","name","订单名称","obranchId","下单机构号码","ouserid","下单用户编号","ousername","下单用户名称","finalFee","价格=orign_fee*odis_rate","status","订单状态0-初始，1-待确认，2-待付款，3-已付款，4-已完成，5-已取消-未付款前可取消，取消后可删除，6-退单-退单后变为已取消，8已关闭-售后完成后可以关闭订单","sstatus","结算状态0-待结算，1-已结算","ctime","创建时间","payTime","付款时间","payCtime","付款确认时间","orginFee","折扣前总价=sys_guard中价格","poid","上级订单-如果是续费订单，记录原订单号","startTime","启用日期","endTime","结束日期","payType","支付方式1-微信，2-支付宝，3-线下支付","payId","付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)","prepayId","预下单付款订单号（第三方返回）","topenId","第三方账号编号","pbankId","收款银行编号(支付方式为3时必填)","pbankName","收款银行名称(支付方式为3时必填)","pbankCardNo","收款银行卡号(支付方式为3时必填)","pbankUsername","收款账户姓名(支付方式为3时必填)","remark","备注","finishTime","完成时间","closeTime","关闭时间","setTime","结算时间","ofinalFee","最终订单价格、最终付款金额=final_fee+oth_fee","odisRate","订单折扣率(下单后后台根据不同客户进行折扣调整)=将在模块表中执行折扣操作，再从新合计价格","othFee","其它费用","otype","订单类型0-电商商城商品，1-应用模块使用购买，2-vip购买会员，3-任务相关的保证金、佣金、分享赚佣金、加急热搜金额等、4-服务商付款服务保障押金","osource","订单来源1-前端客户下单，2-后台待客下单","memType","客户类型-参考sys_user表mem_type","atype","账户类型-根据sys_user表atype","saleUserid","销售经理编号","saleUsername","销售经理名称","custPhone","客户联系电话","custAddress","客户联系地址","payAt","最终付款金额-客户付款后回填","obranchName","下单机构名称","ooper","订单操作类型1-新购，2-续费","tranId","第三方付款事务号","invoice","是否已开票","invoiceTime","开票时间","invoiceId","发票编号","guardId","服务保障编号","guardName","服务保障名称","payStatus","付款状态0-未付款，1-已付款";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_guard_order")
@ApiModel(description="购买服务保障订单表")
public class GuardOrder  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="订单编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="订单名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="下单机构号码",allowEmptyValue=true,example="",allowableValues="")
	String obranchId;
	
	@ApiModelProperty(notes="下单用户编号",allowEmptyValue=true,example="",allowableValues="")
	String ouserid;
	
	@ApiModelProperty(notes="下单用户名称",allowEmptyValue=true,example="",allowableValues="")
	String ousername;
	
	@ApiModelProperty(notes="价格=orign_fee*odis_rate",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal finalFee;
	
	@ApiModelProperty(notes="订单状态0-初始，1-待确认，2-待付款，3-已付款，4-已完成，5-已取消-未付款前可取消，取消后可删除，6-退单-退单后变为已取消，8已关闭-售后完成后可以关闭订单",allowEmptyValue=true,example="",allowableValues="")
	String status;
	
	@ApiModelProperty(notes="结算状态0-待结算，1-已结算",allowEmptyValue=true,example="",allowableValues="")
	String sstatus;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="付款时间",allowEmptyValue=true,example="",allowableValues="")
	Date payTime;
	
	@ApiModelProperty(notes="付款确认时间",allowEmptyValue=true,example="",allowableValues="")
	Date payCtime;
	
	@ApiModelProperty(notes="折扣前总价=sys_guard中价格",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal orginFee;
	
	@ApiModelProperty(notes="上级订单-如果是续费订单，记录原订单号",allowEmptyValue=true,example="",allowableValues="")
	String poid;
	
	@ApiModelProperty(notes="启用日期",allowEmptyValue=true,example="",allowableValues="")
	Date startTime;
	
	@ApiModelProperty(notes="结束日期",allowEmptyValue=true,example="",allowableValues="")
	Date endTime;
	
	@ApiModelProperty(notes="支付方式1-微信，2-支付宝，3-线下支付",allowEmptyValue=true,example="",allowableValues="")
	String payType;
	
	@ApiModelProperty(notes="付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)",allowEmptyValue=true,example="",allowableValues="")
	String payId;
	
	@ApiModelProperty(notes="预下单付款订单号（第三方返回）",allowEmptyValue=true,example="",allowableValues="")
	String prepayId;
	
	@ApiModelProperty(notes="第三方账号编号",allowEmptyValue=true,example="",allowableValues="")
	String topenId;
	
	@ApiModelProperty(notes="收款银行编号(支付方式为3时必填)",allowEmptyValue=true,example="",allowableValues="")
	String pbankId;
	
	@ApiModelProperty(notes="收款银行名称(支付方式为3时必填)",allowEmptyValue=true,example="",allowableValues="")
	String pbankName;
	
	@ApiModelProperty(notes="收款银行卡号(支付方式为3时必填)",allowEmptyValue=true,example="",allowableValues="")
	String pbankCardNo;
	
	@ApiModelProperty(notes="收款账户姓名(支付方式为3时必填)",allowEmptyValue=true,example="",allowableValues="")
	String pbankUsername;
	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="完成时间",allowEmptyValue=true,example="",allowableValues="")
	Date finishTime;
	
	@ApiModelProperty(notes="关闭时间",allowEmptyValue=true,example="",allowableValues="")
	Date closeTime;
	
	@ApiModelProperty(notes="结算时间",allowEmptyValue=true,example="",allowableValues="")
	Date setTime;
	
	@ApiModelProperty(notes="最终订单价格、最终付款金额=final_fee+oth_fee",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal ofinalFee;
	
	@ApiModelProperty(notes="订单折扣率(下单后后台根据不同客户进行折扣调整)=将在模块表中执行折扣操作，再从新合计价格",allowEmptyValue=true,example="",allowableValues="")
	Integer odisRate;
	
	@ApiModelProperty(notes="其它费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal othFee;
	
	@ApiModelProperty(notes="订单类型0-电商商城商品，1-应用模块使用购买，2-vip购买会员，3-任务相关的保证金、佣金、分享赚佣金、加急热搜金额等、4-服务商付款服务保障押金",allowEmptyValue=true,example="",allowableValues="")
	String otype;
	
	@ApiModelProperty(notes="订单来源1-前端客户下单，2-后台待客下单",allowEmptyValue=true,example="",allowableValues="")
	String osource;
	
	@ApiModelProperty(notes="客户类型-参考sys_user表mem_type",allowEmptyValue=true,example="",allowableValues="")
	String memType;
	
	@ApiModelProperty(notes="账户类型-根据sys_user表atype",allowEmptyValue=true,example="",allowableValues="")
	String atype;
	
	@ApiModelProperty(notes="销售经理编号",allowEmptyValue=true,example="",allowableValues="")
	String saleUserid;
	
	@ApiModelProperty(notes="销售经理名称",allowEmptyValue=true,example="",allowableValues="")
	String saleUsername;
	
	@ApiModelProperty(notes="客户联系电话",allowEmptyValue=true,example="",allowableValues="")
	String custPhone;
	
	@ApiModelProperty(notes="客户联系地址",allowEmptyValue=true,example="",allowableValues="")
	String custAddress;
	
	@ApiModelProperty(notes="最终付款金额-客户付款后回填",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal payAt;
	
	@ApiModelProperty(notes="下单机构名称",allowEmptyValue=true,example="",allowableValues="")
	String obranchName;
	
	@ApiModelProperty(notes="订单操作类型1-新购，2-续费",allowEmptyValue=true,example="",allowableValues="")
	String ooper;
	
	@ApiModelProperty(notes="第三方付款事务号",allowEmptyValue=true,example="",allowableValues="")
	String tranId;
	
	@ApiModelProperty(notes="是否已开票",allowEmptyValue=true,example="",allowableValues="")
	String invoice;
	
	@ApiModelProperty(notes="开票时间",allowEmptyValue=true,example="",allowableValues="")
	Date invoiceTime;
	
	@ApiModelProperty(notes="发票编号",allowEmptyValue=true,example="",allowableValues="")
	String invoiceId;
	
	@ApiModelProperty(notes="服务保障编号",allowEmptyValue=true,example="",allowableValues="")
	String guardId;
	
	@ApiModelProperty(notes="服务保障名称",allowEmptyValue=true,example="",allowableValues="")
	String guardName;
	
	@ApiModelProperty(notes="付款状态0-未付款，1-已付款",allowEmptyValue=true,example="",allowableValues="")
	String payStatus;

	/**
	 *订单编号
	 **/
	public GuardOrder(String id) {
		this.id = id;
	}
    
    /**
     * 购买服务保障订单表
     **/
	public GuardOrder() {
	}

}