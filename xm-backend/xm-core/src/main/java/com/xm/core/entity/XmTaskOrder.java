package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_task_order")
@ApiModel(description="任务相关费用订单表")
public class XmTaskOrder  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="订单编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="下单用户编号",allowEmptyValue=true,example="",allowableValues="")
	String ouserid;

	
	@ApiModelProperty(notes="公司ID-下单客户对应的企业",allowEmptyValue=true,example="",allowableValues="")
	String obranchId;

	
	@ApiModelProperty(notes="订单状态0-初始，1-待确认，2-待付款，3-已付款，4-已完成，5-已取消-未付款前可取消，取消后可删除，6-退单-退单后变为已取消，8已关闭-售后完成后可以关闭订单",allowEmptyValue=true,example="",allowableValues="")
	String ostatus;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;

	
	@ApiModelProperty(notes="支付方式1微信2支付宝",allowEmptyValue=true,example="",allowableValues="")
	String payType;

	
	@ApiModelProperty(notes="支付状态0待付款，1已付款",allowEmptyValue=true,example="",allowableValues="")
	String payStatus;

	
	@ApiModelProperty(notes="支付时间",allowEmptyValue=true,example="",allowableValues="")
	Date payTime;

	
	@ApiModelProperty(notes="第三方支付订单编号",allowEmptyValue=true,example="",allowableValues="")
	String prepayId;

	
	@ApiModelProperty(notes="最终总费用=origin_fee",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal finalFee;

	
	@ApiModelProperty(notes="其它费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal othFee;

	
	@ApiModelProperty(notes="原始价格=top_fee+urgent_fee+crm_sup_fee+hot_fee+efunds+share_fee",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal originFee;

	
	@ApiModelProperty(notes="最终付款金额-客户付款后回填",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal payAt;

	
	@ApiModelProperty(notes="支付授权码",allowEmptyValue=true,example="",allowableValues="")
	String payAuthId;

	
	@ApiModelProperty(notes="支付账户对应的第三方openid,注意，下单根付款不一定是同一个人",allowEmptyValue=true,example="",allowableValues="")
	String payOpenid;

	
	@ApiModelProperty(notes="付款用户编号",allowEmptyValue=true,example="",allowableValues="")
	String payUserid;

	
	@ApiModelProperty(notes="付款用户名称",allowEmptyValue=true,example="",allowableValues="")
	String payUsername;

	
	@ApiModelProperty(notes="折扣率0-199",allowEmptyValue=true,example="",allowableValues="")
	Integer discount;

	
	@ApiModelProperty(notes="置顶费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal topFee;

	
	@ApiModelProperty(notes="置顶开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date topStime;

	
	@ApiModelProperty(notes="置顶结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date topEtime;

	
	@ApiModelProperty(notes="热搜费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal hotFee;

	
	@ApiModelProperty(notes="热搜开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date hotStime;

	
	@ApiModelProperty(notes="热搜结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date hotEtime;

	
	@ApiModelProperty(notes="是否置顶0否1待付款2已开通3已过期",allowEmptyValue=true,example="",allowableValues="")
	String top;

	
	@ApiModelProperty(notes="是否热搜0否1待付款2已开通3已过期",allowEmptyValue=true,example="",allowableValues="")
	String hot;

	
	@ApiModelProperty(notes="客服包办费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal crmSupFee;

	
	@ApiModelProperty(notes="加急费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal urgentFee;

	
	@ApiModelProperty(notes="是否加急0否1待付款2已开通3已过期",allowEmptyValue=true,example="",allowableValues="")
	String urgent;

	
	@ApiModelProperty(notes="是否客服包办0否1待付款2已开通3已过期",allowEmptyValue=true,example="",allowableValues="")
	String crmSup;

	
	@ApiModelProperty(notes="托管金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal efunds;

	
	@ApiModelProperty(notes="资金托管状况0-无须托管，1-待付款，2-已托管资金，3-已付款给服务商，4-已退款",allowEmptyValue=true,example="",allowableValues="")
	String estate;

	
	@ApiModelProperty(notes="托管资金付款给平台的时间",allowEmptyValue=true,example="",allowableValues="")
	Date etoPlatTime;

	
	@ApiModelProperty(notes="托管资金支付给服务商的时间",allowEmptyValue=true,example="",allowableValues="")
	Date etoDevTime;

	
	@ApiModelProperty(notes="托管资金退回甲方时间",allowEmptyValue=true,example="",allowableValues="")
	Date ebackTime;

	
	@ApiModelProperty(notes="任务编号",allowEmptyValue=true,example="",allowableValues="")
	String taskId;

	
	@ApiModelProperty(notes="置顶天数",allowEmptyValue=true,example="",allowableValues="")
	Integer topDays;

	
	@ApiModelProperty(notes="热搜天数",allowEmptyValue=true,example="",allowableValues="")
	Integer hotDays;

	
	@ApiModelProperty(notes="加急天数",allowEmptyValue=true,example="",allowableValues="")
	Integer urgentDays;

	
	@ApiModelProperty(notes="加急开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date urgentStime;

	
	@ApiModelProperty(notes="加急结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date urgentEtime;

	
	@ApiModelProperty(notes="定时检查日期是否已过期，已过期则取消任务中的置顶、加急、热搜状态计算状态0-无须计算，1-本周期已计算待下周期计算，2-结束",allowEmptyValue=true,example="",allowableValues="")
	String calcStatus;

	
	@ApiModelProperty(notes="计算时间",allowEmptyValue=true,example="",allowableValues="")
	Date calcTime;

	
	@ApiModelProperty(notes="是否开启分享赚0否1待付款2已开通3已过期",allowEmptyValue=true,example="",allowableValues="")
	String oshare;

	
	@ApiModelProperty(notes="分享赚佣金",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal shareFee;

	
	@ApiModelProperty(notes="付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)",allowEmptyValue=true,example="",allowableValues="")
	String payId;

	
	@ApiModelProperty(notes="第三方付款事务号",allowEmptyValue=true,example="",allowableValues="")
	String tranId;

	
	@ApiModelProperty(notes="订单备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="订单名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	
	@ApiModelProperty(notes="订单业务类型1-保证金，2-营销推广活动",allowEmptyValue=true,example="",allowableValues="")
	String bizType;

	
	@ApiModelProperty(notes="项目编号",allowEmptyValue=true,example="",allowableValues="")
	String projectId;

	/**
	 *订单编号
	 **/
	public XmTaskOrder(String id) {
		this.id = id;
	}
    
    /**
     * 任务相关费用订单表
     **/
	public XmTaskOrder() {
	}

}