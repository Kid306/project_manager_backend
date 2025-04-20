package  com.mdp.mo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 组织 com  顶级模块 mdp 大模块 mo  小模块 <br> 
 * 实体 MoOrderModule所有属性名: <br>
 *	"orderId","订单编号","moduleId","模块编号","name","模块名称","fee","模块费用","billMode","计费模式：0-不计费，1-按购买人数计费，2-总包费用,3-按实际使用人数每月计费","uniFee","人均费用,单位人天","discount","折扣比率，可折上折，叠加折扣。-按最大人数、按月数优惠","mcate","分类(关联mo_cate)","logoUrl","logo地址","feeDesc","费用解释","udisRate","人数折算比例。购买总人数*折扣率为当前模块购买人数","udis","是否折算人数0否1是","musers","购买人数=订单表中购买总人数*人数折扣","finalFee","最终总费用=orgin_fee*dis_rate","days","购买天数","orginFee","原始总费用，未进行折扣方案前的总费用如果计费模式为1，则为uni_fee*musers*days单价*折扣方案天数折扣*折扣方案中人数折扣，如果计费模式为2，则为fee","disRate","订单折扣率（可能会根据客户类型后台改订单折扣，从新计算订单价格）","ucheck","是否控制总人数0-否1-是","ousers","企业总人数=订单表中ousers","ver","购买的版本0免费版，1企业版";<br>
 * 当前主键(包括多主键):<br>
 *	order_id,module_id;<br>
 */
 @Data
@TableName("mo_order_module")
@ApiModel(description="订单与模块关系表")
public class MoOrderModule  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="订单编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String orderId;
    @TableIds
    @ApiModelProperty(notes="模块编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String moduleId;
	
	@ApiModelProperty(notes="模块名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="模块费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal fee;
	
	@ApiModelProperty(notes="计费模式：0-不计费，1-按购买人数计费，2-总包费用,3-按实际使用人数每月计费",allowEmptyValue=true,example="",allowableValues="")
	String billMode;
	
	@ApiModelProperty(notes="人均费用,单位人天",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal uniFee;
	
	@ApiModelProperty(notes="折扣比率，可折上折，叠加折扣。-按最大人数、按月数优惠",allowEmptyValue=true,example="",allowableValues="")
	String discount;
	
	@ApiModelProperty(notes="分类(关联mo_cate)",allowEmptyValue=true,example="",allowableValues="")
	String mcate;
	
	@ApiModelProperty(notes="logo地址",allowEmptyValue=true,example="",allowableValues="")
	String logoUrl;
	
	@ApiModelProperty(notes="费用解释",allowEmptyValue=true,example="",allowableValues="")
	String feeDesc;
	
	@ApiModelProperty(notes="人数折算比例。购买总人数*折扣率为当前模块购买人数",allowEmptyValue=true,example="",allowableValues="")
	Integer udisRate;
	
	@ApiModelProperty(notes="是否折算人数0否1是",allowEmptyValue=true,example="",allowableValues="")
	String udis;
	
	@ApiModelProperty(notes="购买人数=订单表中购买总人数*人数折扣",allowEmptyValue=true,example="",allowableValues="")
	Integer musers;
	
	@ApiModelProperty(notes="最终总费用=orgin_fee*dis_rate",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal finalFee;
	
	@ApiModelProperty(notes="购买天数",allowEmptyValue=true,example="",allowableValues="")
	Integer days;
	
	@ApiModelProperty(notes="原始总费用，未进行折扣方案前的总费用如果计费模式为1，则为uni_fee*musers*days单价*折扣方案天数折扣*折扣方案中人数折扣，如果计费模式为2，则为fee",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal orginFee;
	
	@ApiModelProperty(notes="订单折扣率（可能会根据客户类型后台改订单折扣，从新计算订单价格）",allowEmptyValue=true,example="",allowableValues="")
	Integer disRate;
	
	@ApiModelProperty(notes="是否控制总人数0-否1-是",allowEmptyValue=true,example="",allowableValues="")
	String ucheck;
	
	@ApiModelProperty(notes="企业总人数=订单表中ousers",allowEmptyValue=true,example="",allowableValues="")
	Integer ousers;
	
	@ApiModelProperty(notes="购买的版本0免费版，1企业版",allowEmptyValue=true,example="",allowableValues="")
	String ver;

	/**
	 *订单编号,模块编号
	 **/
	public MoOrderModule(String orderId,String moduleId) {
		this.orderId = orderId;
		this.moduleId = moduleId;
	}
    
    /**
     * 订单与模块关系表
     **/
	public MoOrderModule() {
	}

}