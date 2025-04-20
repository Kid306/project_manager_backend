package  com.mdp.plat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 plat  小模块 <br> 
 * 实体 Platform所有属性名: <br>
 *	"id","平台编号建议为platform-001","platformName","平台名称","status","0-未启用，1-已启用，只能有一条数据有效","ctime","创建时间","ltime","更新时间","cuserid","创建人编号","cusername","创建人姓名","logoUrl","平台logo图片地址","platformTitle","前端显示的平台名称","keywords","关键词逗号分割","minBuyAmount","最小购买金额，达到此购物金额，才能提交订单","autoConfirmReceipt","自动确认收货0否1是，确认收货时长请在订单设置中进行设置","canBill","是否可开发票0否1是","billContextList","发票内容选项,逗号分割","cutStock","扣减库存时机0-下单付款完成，1-发货后","extConfig","其它全局控制参数","plusSales","增加销量时机0-发货后，1-付款后","evaluate","评价是否需要审核0否1是","discountPct","全局折扣0~100之间","usePriceGroup","是否使用价格套0否1是","shopId","平台商户编号默认platform-shop-001","branchId","平台机构编号默认platform-branch-001","remark","remark","locationId","默认门店编号","extInfos","扩展字段";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("plat_platform")
@ApiModel(description="plat_platform")
public class Platform  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="平台编号建议为platform-001,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="平台名称",allowEmptyValue=true,example="",allowableValues="")
	String platformName;
	
	@ApiModelProperty(notes="0-未启用，1-已启用，只能有一条数据有效",allowEmptyValue=true,example="",allowableValues="")
	String status;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;
	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;
	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;
	
	@ApiModelProperty(notes="平台logo图片地址",allowEmptyValue=true,example="",allowableValues="")
	String logoUrl;
	
	@ApiModelProperty(notes="前端显示的平台名称",allowEmptyValue=true,example="",allowableValues="")
	String platformTitle;
	
	@ApiModelProperty(notes="关键词逗号分割",allowEmptyValue=true,example="",allowableValues="")
	String keywords;
	
	@ApiModelProperty(notes="最小购买金额，达到此购物金额，才能提交订单",allowEmptyValue=true,example="",allowableValues="")
	Integer minBuyAmount;
	
	@ApiModelProperty(notes="自动确认收货0否1是，确认收货时长请在订单设置中进行设置",allowEmptyValue=true,example="",allowableValues="")
	String autoConfirmReceipt;
	
	@ApiModelProperty(notes="是否可开发票0否1是",allowEmptyValue=true,example="",allowableValues="")
	String canBill;
	
	@ApiModelProperty(notes="发票内容选项,逗号分割",allowEmptyValue=true,example="",allowableValues="")
	String billContextList;
	
	@ApiModelProperty(notes="扣减库存时机0-下单付款完成，1-发货后",allowEmptyValue=true,example="",allowableValues="")
	String cutStock;
	
	@ApiModelProperty(notes="其它全局控制参数",allowEmptyValue=true,example="",allowableValues="")
	String extConfig;
	
	@ApiModelProperty(notes="增加销量时机0-发货后，1-付款后",allowEmptyValue=true,example="",allowableValues="")
	String plusSales;
	
	@ApiModelProperty(notes="评价是否需要审核0否1是",allowEmptyValue=true,example="",allowableValues="")
	String evaluate;
	
	@ApiModelProperty(notes="全局折扣0~100之间",allowEmptyValue=true,example="",allowableValues="")
	Integer discountPct;
	
	@ApiModelProperty(notes="是否使用价格套0否1是",allowEmptyValue=true,example="",allowableValues="")
	String usePriceGroup;
	
	@ApiModelProperty(notes="平台商户编号默认platform-shop-001",allowEmptyValue=true,example="",allowableValues="")
	String shopId;
	
	@ApiModelProperty(notes="平台机构编号默认platform-branch-001",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="remark",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="默认门店编号",allowEmptyValue=true,example="",allowableValues="")
	String locationId;
	
	@ApiModelProperty(notes="扩展字段",allowEmptyValue=true,example="",allowableValues="")
	String extInfos;

	/**
	 *平台编号建议为platform-001
	 **/
	public Platform(String id) {
		this.id = id;
	}
    
    /**
     * plat_platform
     **/
	public Platform() {
	}

}