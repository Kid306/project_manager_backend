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
 * 实体 InterestsOrders所有属性名: <br>
 *	"userid","用户编号","ilvlId","等级ID","ilvlName","等级名称","idesc","等级描述","branchId","公司ID-下单客户对应的企业","ilevel","1-粉丝,2-","discount","权益，折扣","istatus","等级状态","ctime","创建时间","ltime","更新时间","picUrl","等级图标url","isFree","是否付费获取权益","rtimeRule","续会时间叠加规则：1.有效期日期后叠加续会时间","rtimeType","续会时间类型：1.天数","rtime","续会时间yyyy-MM-dd型","itype","权益分类","shopId","商户编号","instId","当前流程实例编号","flowState","当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除","smaxAt","单个任务最大金额（任务型用户才有）0代表不限制","totalAt","累计接单金额（任务型用户才有）0代表不限制","mtype","适用会员类型（2商户型、1普通型、3任务型）","totalExp","累计经验值0代表不限制","smaxExp","单个任务最大经验值0代表不限制","bids","投标次数上限","sfeeRate","服务费率0-100之间","idBak","覆盖上一条的等级编号，即变成当前等级之前的等级编号","payType","支付方式","payStatus","支付状态0待付款，1已付款","payTime","支付时间","prepayId","第三方支付订单编号","id","订单编号","finalFee","最终总费用=mfee*","mfee","月均费用","othFee","其它费用","months","购买月数","originFee","原始价格=mfee*months","payAt","最终付款金额-客户付款后回填","ver","版本1-个人版，2-企业版","payAuthId","支付授权码","payOpenid","支付账户对应的第三方openid,注意，下单根付款不一定是同一个人","payUserid","付款用户编号","payUsername","付款用户名称","invoice","是否已开票","invoiceTime","开票时间","invoiceId","发票编号","payId","付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)","otye","订单类型，此处填2代表会员等级订单","tranId","第三方付款事务号（付款通知中返回）","name","订单名称","remark","订单说明";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_interests_orders")
@ApiModel(description="会员购买权益关联表")
public class InterestsOrders  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="订单编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="用户编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;
	
	@ApiModelProperty(notes="等级ID",allowEmptyValue=true,example="",allowableValues="")
	String ilvlId;
	
	@ApiModelProperty(notes="等级名称",allowEmptyValue=true,example="",allowableValues="")
	String ilvlName;
	
	@ApiModelProperty(notes="等级描述",allowEmptyValue=true,example="",allowableValues="")
	String idesc;
	
	@ApiModelProperty(notes="公司ID-下单客户对应的企业",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="1-粉丝,2-",allowEmptyValue=true,example="",allowableValues="")
	Integer ilevel;
	
	@ApiModelProperty(notes="权益，折扣",allowEmptyValue=true,example="",allowableValues="")
	Integer discount;
	
	@ApiModelProperty(notes="等级状态",allowEmptyValue=true,example="",allowableValues="")
	String istatus;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;
	
	@ApiModelProperty(notes="等级图标url",allowEmptyValue=true,example="",allowableValues="")
	String picUrl;
	
	@ApiModelProperty(notes="是否付费获取权益",allowEmptyValue=true,example="",allowableValues="")
	String isFree;
	
	@ApiModelProperty(notes="续会时间叠加规则：1.有效期日期后叠加续会时间",allowEmptyValue=true,example="",allowableValues="")
	String rtimeRule;
	
	@ApiModelProperty(notes="续会时间类型：1.天数",allowEmptyValue=true,example="",allowableValues="")
	String rtimeType;
	
	@ApiModelProperty(notes="续会时间yyyy-MM-dd型",allowEmptyValue=true,example="",allowableValues="")
	String rtime;
	
	@ApiModelProperty(notes="权益分类",allowEmptyValue=true,example="",allowableValues="")
	String itype;
	
	@ApiModelProperty(notes="商户编号",allowEmptyValue=true,example="",allowableValues="")
	String shopId;
	
	@ApiModelProperty(notes="当前流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String instId;
	
	@ApiModelProperty(notes="当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除",allowEmptyValue=true,example="",allowableValues="")
	String flowState;
	
	@ApiModelProperty(notes="单个任务最大金额（任务型用户才有）0代表不限制",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal smaxAt;
	
	@ApiModelProperty(notes="累计接单金额（任务型用户才有）0代表不限制",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal totalAt;
	
	@ApiModelProperty(notes="适用会员类型（2商户型、1普通型、3任务型）",allowEmptyValue=true,example="",allowableValues="")
	String mtype;
	
	@ApiModelProperty(notes="累计经验值0代表不限制",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal totalExp;
	
	@ApiModelProperty(notes="单个任务最大经验值0代表不限制",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal smaxExp;
	
	@ApiModelProperty(notes="投标次数上限",allowEmptyValue=true,example="",allowableValues="")
	Integer bids;
	
	@ApiModelProperty(notes="服务费率0-100之间",allowEmptyValue=true,example="",allowableValues="")
	Integer sfeeRate;
	
	@ApiModelProperty(notes="覆盖上一条的等级编号，即变成当前等级之前的等级编号",allowEmptyValue=true,example="",allowableValues="")
	String idBak;
	
	@ApiModelProperty(notes="支付方式",allowEmptyValue=true,example="",allowableValues="")
	String payType;
	
	@ApiModelProperty(notes="支付状态0待付款，1已付款",allowEmptyValue=true,example="",allowableValues="")
	String payStatus;
	
	@ApiModelProperty(notes="支付时间",allowEmptyValue=true,example="",allowableValues="")
	Date payTime;
	
	@ApiModelProperty(notes="第三方支付订单编号",allowEmptyValue=true,example="",allowableValues="")
	String prepayId;
	
	@ApiModelProperty(notes="最终总费用=mfee*",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal finalFee;
	
	@ApiModelProperty(notes="月均费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal mfee;
	
	@ApiModelProperty(notes="其它费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal othFee;
	
	@ApiModelProperty(notes="购买月数",allowEmptyValue=true,example="",allowableValues="")
	Integer months;
	
	@ApiModelProperty(notes="原始价格=mfee*months",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal originFee;
	
	@ApiModelProperty(notes="最终付款金额-客户付款后回填",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal payAt;
	
	@ApiModelProperty(notes="版本1-个人版，2-企业版",allowEmptyValue=true,example="",allowableValues="")
	String ver;
	
	@ApiModelProperty(notes="支付授权码",allowEmptyValue=true,example="",allowableValues="")
	String payAuthId;
	
	@ApiModelProperty(notes="支付账户对应的第三方openid,注意，下单根付款不一定是同一个人",allowEmptyValue=true,example="",allowableValues="")
	String payOpenid;
	
	@ApiModelProperty(notes="付款用户编号",allowEmptyValue=true,example="",allowableValues="")
	String payUserid;
	
	@ApiModelProperty(notes="付款用户名称",allowEmptyValue=true,example="",allowableValues="")
	String payUsername;
	
	@ApiModelProperty(notes="是否已开票",allowEmptyValue=true,example="",allowableValues="")
	String invoice;
	
	@ApiModelProperty(notes="开票时间",allowEmptyValue=true,example="",allowableValues="")
	Date invoiceTime;
	
	@ApiModelProperty(notes="发票编号",allowEmptyValue=true,example="",allowableValues="")
	String invoiceId;
	
	@ApiModelProperty(notes="付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)",allowEmptyValue=true,example="",allowableValues="")
	String payId;
	
	@ApiModelProperty(notes="订单类型，此处填2代表会员等级订单",allowEmptyValue=true,example="",allowableValues="")
	String otye;
	
	@ApiModelProperty(notes="第三方付款事务号（付款通知中返回）",allowEmptyValue=true,example="",allowableValues="")
	String tranId;
	
	@ApiModelProperty(notes="订单名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="订单说明",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	@ApiModelProperty(notes="订单操作类型1-新购，2-续费，3-升级",allowEmptyValue=true,example="",allowableValues="")
	String ooper;

	@ApiModelProperty(notes="1-已执行（立即生效的默认已执行，定时任务的定时任务执行完后更新为一已执行），2-待执行",allowEmptyValue=true,example="",allowableValues="")
	String calcStatus;

	@ApiModelProperty(notes="订单处理状态1-立即生效(新购、升级时可以立即生效、续费而且会员等级前后一致情况下可以立即生效)，2-到期定时任务处理（一般是续费并且续费等级不一致情况下需要等上一个等级过期再处理）；",allowEmptyValue=true,example="",allowableValues="")
	String calcState;

	@ApiModelProperty(notes="执行时间",allowEmptyValue=true,example="",allowableValues="")
	Date calcExecTime;

	@ApiModelProperty(notes="上一会员等级的待续费时间yyyy-MM-dd型",allowEmptyValue=true,example="",allowableValues="")
	String oldRtime;


	/**
	 *订单编号
	 **/
	public InterestsOrders(String id) {
		this.id = id;
	}
    
    /**
     * 会员购买权益关联表
     **/
	public InterestsOrders() {
	}

}