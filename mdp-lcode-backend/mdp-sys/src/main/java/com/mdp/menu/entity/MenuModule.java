package  com.mdp.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 组织 com  顶级模块 mdp 大模块 menu  小模块 <br> 
 * 实体 MenuModule所有属性名: <br>
 *	"id","编号","name","模块名称","fee","模块费用","billMode","计费模式：0-不计费，1-按购买人数计费，2-总包费用,3-按实际使用人数每月计费","uniFee","人均费用，单位人天","discount","折扣比率，可折上折，叠加折扣。-按最大人数、按天数优惠","url","匹配的url,如果匹配得到，则计费，以逗号分割多个","ignoreUrl","匹配这个地址的不计费","bizFlowState","审核状态","procInstId","审核流程实例编号","status","0-下架，1-上架。开放后才可以购买使用","mcate","分类1-协同、2-研发、3-电商","logoUrl","logo地址","feeDesc","费用解释","udisRate","人数折算比例。企业总人数*折扣率为当前模块购买人数","udis","是否折算人数0否1是，按企业总人数*人数折算比例计算","saleDesc","营销文案","ucheck","是否控制总人数0-否1是","crowd","是否为众包","seq","序号0-1000，默认999";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("menu_module")
@ApiModel(description="模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问")
public class MenuModule  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="模块名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="模块费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal fee;
	
	@ApiModelProperty(notes="计费模式：0-不计费，1-按购买人数计费，2-总包费用,3-按实际使用人数每月计费",allowEmptyValue=true,example="",allowableValues="")
	String billMode;
	
	@ApiModelProperty(notes="人均费用，单位人天",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal uniFee;
	
	@ApiModelProperty(notes="折扣比率，可折上折，叠加折扣。-按最大人数、按天数优惠",allowEmptyValue=true,example="",allowableValues="")
	String discount;
	
	@ApiModelProperty(notes="匹配的url,如果匹配得到，则计费，以逗号分割多个",allowEmptyValue=true,example="",allowableValues="")
	String url;
	
	@ApiModelProperty(notes="匹配这个地址的不计费",allowEmptyValue=true,example="",allowableValues="")
	String ignoreUrl;
	
	@ApiModelProperty(notes="审核状态",allowEmptyValue=true,example="",allowableValues="")
	String bizFlowState;
	
	@ApiModelProperty(notes="审核流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String procInstId;
	
	@ApiModelProperty(notes="0-下架，1-上架。开放后才可以购买使用",allowEmptyValue=true,example="",allowableValues="")
	String status;
	
	@ApiModelProperty(notes="分类1-协同、2-研发、3-电商",allowEmptyValue=true,example="",allowableValues="")
	String mcate;
	
	@ApiModelProperty(notes="logo地址",allowEmptyValue=true,example="",allowableValues="")
	String logoUrl;
	
	@ApiModelProperty(notes="费用解释",allowEmptyValue=true,example="",allowableValues="")
	String feeDesc;
	
	@ApiModelProperty(notes="人数折算比例。企业总人数*折扣率为当前模块购买人数",allowEmptyValue=true,example="",allowableValues="")
	Integer udisRate;
	
	@ApiModelProperty(notes="是否折算人数0否1是，按企业总人数*人数折算比例计算",allowEmptyValue=true,example="",allowableValues="")
	String udis;
	
	@ApiModelProperty(notes="营销文案",allowEmptyValue=true,example="",allowableValues="")
	String saleDesc;
	
	@ApiModelProperty(notes="是否控制总人数0-否1是",allowEmptyValue=true,example="",allowableValues="")
	String ucheck;
	
	@ApiModelProperty(notes="是否为众包",allowEmptyValue=true,example="",allowableValues="")
	String crowd;
	
	@ApiModelProperty(notes="序号0-1000，默认999",allowEmptyValue=true,example="",allowableValues="")
	Integer seq;

	/**
	 *编号
	 **/
	public MenuModule(String id) {
		this.id = id;
	}
    
    /**
     * 模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问
     **/
	public MenuModule() {
	}

}