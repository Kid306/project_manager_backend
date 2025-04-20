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
@TableName("xm_task_bid_order")
@ApiModel(description="任务相关投标直通车订单表")
public class XmTaskBidOrder  implements java.io.Serializable {
	
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

	
	@ApiModelProperty(notes="原始价格=任务佣金*平台配置的投标直通车收费比率",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal originFee;

	
	@ApiModelProperty(notes="最终付款金额-客户付款后回填",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal payAt;

	
	@ApiModelProperty(notes="支付账户对应的第三方openid,注意，下单根付款不一定是同一个人",allowEmptyValue=true,example="",allowableValues="")
	String payOpenid;

	
	@ApiModelProperty(notes="付款用户编号",allowEmptyValue=true,example="",allowableValues="")
	String payUserid;

	
	@ApiModelProperty(notes="付款用户名称",allowEmptyValue=true,example="",allowableValues="")
	String payUsername;

	
	@ApiModelProperty(notes="任务编号",allowEmptyValue=true,example="",allowableValues="")
	String taskId;

	
	@ApiModelProperty(notes="定时检查日期是否已过期，已过期则取消任务中的置顶、加急、热搜状态计算状态0-无须计算，1-本周期已计算待下周期计算，2-结束",allowEmptyValue=true,example="",allowableValues="")
	String calcStatus;

	
	@ApiModelProperty(notes="计算时间",allowEmptyValue=true,example="",allowableValues="")
	Date calcTime;

	
	@ApiModelProperty(notes="付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)",allowEmptyValue=true,example="",allowableValues="")
	String payId;

	
	@ApiModelProperty(notes="第三方付款事务号",allowEmptyValue=true,example="",allowableValues="")
	String tranId;

	
	@ApiModelProperty(notes="订单备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="订单名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	
	@ApiModelProperty(notes="订单业务类",allowEmptyValue=true,example="",allowableValues="")
	String bizType;

	
	@ApiModelProperty(notes="项目编号",allowEmptyValue=true,example="",allowableValues="")
	String projectId;

	
	@ApiModelProperty(notes="订单类型7-投标直通车",allowEmptyValue=true,example="",allowableValues="")
	String otype;

	
	@ApiModelProperty(notes="任务预算金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal taskBudgetAt;

	/**
	 *订单编号
	 **/
	public XmTaskBidOrder(String id) {
		this.id = id;
	}
    
    /**
     * 任务相关投标直通车订单表
     **/
	public XmTaskBidOrder() {
	}

}