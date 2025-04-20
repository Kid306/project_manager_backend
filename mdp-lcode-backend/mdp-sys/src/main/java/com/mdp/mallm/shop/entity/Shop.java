package  com.mdp.mallm.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 mallm.shop  小模块 <br> 
 * 实体 Shop所有属性名: <br>
 *	"id","店铺编号","admin","管理员","createTime","创建时间","isEnable","是否启用0否1","shopType","服务站类别","shopImage","店铺照片","shopBrand","店铺品牌名称","shopLogo","店铺logo","shopRemark","店铺介绍","deptid","所属机构","branchId","云用户机构编号","logoUrl","上传至第三方服务器后返回的logo","localProtocol","授权函本地存储图片","protocol","授权函ID，即通过","endTime","授权函有效期截止时间（东八区时间，单位为秒），需要与提交的扫描件一致","localAgreement","营业执照或个体工商户营业执照彩照或扫描件","agreementMediaId","营业执照或个体工商户营业执照彩照或扫描件的media_id(即上传第三方服务器返回的id)","localOperator","营业执照内登记的经营者身份证彩照或扫描件","operatorMediaId","营业执照内登记的经营者身份证彩照或扫描件的media_id(即上传第三方服务器返回的id)","appId","子商户的公众号app_id，配置后子商户卡券券面上的app_id为该app_id。注意：该app_id须经过认证","primaryCategoryId","一级类目id,可以通过本文档中接口查询(微信接口)","secondaryCategoryId","二级类目id，可以通过本文档中接口查询(微信接口)","merchantId","子商户id，对于一个母商户公众号下唯一","status","子商户状态，CHECKING","cuser","创建人","ldate","最后操作时间","phoneno","联系电话","lopuserid","最后操作人","bizProcInstId","当前流程实例编号","bizFlowState","当前流程状态","acctPrjType","核算项目platform/shop/location","platBranchId","归属平台机构号","platShopId","归属平台商户号","isJoinPlat","是否加入平台显示","shareLvl","0-门店私有，1-商户私有，所有店铺共享，2-平台共有，所有商户共享","mngUserid","管理员账户","mngUsername","管理员姓名";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("shop")
@ApiModel(description="商户信息")
public class Shop  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="店铺编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="管理员",allowEmptyValue=true,example="",allowableValues="")
	String admin;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date createTime;
	
	@ApiModelProperty(notes="是否启用0否1",allowEmptyValue=true,example="",allowableValues="")
	String isEnable;
	
	@ApiModelProperty(notes="服务站类别",allowEmptyValue=true,example="",allowableValues="")
	String shopType;
	
	@ApiModelProperty(notes="店铺照片",allowEmptyValue=true,example="",allowableValues="")
	String shopImage;
	
	@ApiModelProperty(notes="店铺品牌名称",allowEmptyValue=true,example="",allowableValues="")
	String shopBrand;
	
	@ApiModelProperty(notes="店铺logo",allowEmptyValue=true,example="",allowableValues="")
	String shopLogo;
	
	@ApiModelProperty(notes="店铺介绍",allowEmptyValue=true,example="",allowableValues="")
	String shopRemark;
	
	@ApiModelProperty(notes="所属机构",allowEmptyValue=true,example="",allowableValues="")
	String deptid;
	
	@ApiModelProperty(notes="云用户机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="上传至第三方服务器后返回的logo",allowEmptyValue=true,example="",allowableValues="")
	String logoUrl;
	
	@ApiModelProperty(notes="授权函本地存储图片",allowEmptyValue=true,example="",allowableValues="")
	String localProtocol;
	
	@ApiModelProperty(notes="授权函ID，即通过",allowEmptyValue=true,example="",allowableValues="")
	String protocol;
	
	@ApiModelProperty(notes="授权函有效期截止时间（东八区时间，单位为秒），需要与提交的扫描件一致",allowEmptyValue=true,example="",allowableValues="")
	Date endTime;
	
	@ApiModelProperty(notes="营业执照或个体工商户营业执照彩照或扫描件",allowEmptyValue=true,example="",allowableValues="")
	String localAgreement;
	
	@ApiModelProperty(notes="营业执照或个体工商户营业执照彩照或扫描件的media_id(即上传第三方服务器返回的id)",allowEmptyValue=true,example="",allowableValues="")
	String agreementMediaId;
	
	@ApiModelProperty(notes="营业执照内登记的经营者身份证彩照或扫描件",allowEmptyValue=true,example="",allowableValues="")
	String localOperator;
	
	@ApiModelProperty(notes="营业执照内登记的经营者身份证彩照或扫描件的media_id(即上传第三方服务器返回的id)",allowEmptyValue=true,example="",allowableValues="")
	String operatorMediaId;
	
	@ApiModelProperty(notes="子商户的公众号app_id，配置后子商户卡券券面上的app_id为该app_id。注意：该app_id须经过认证",allowEmptyValue=true,example="",allowableValues="")
	String appId;
	
	@ApiModelProperty(notes="一级类目id,可以通过本文档中接口查询(微信接口)",allowEmptyValue=true,example="",allowableValues="")
	String primaryCategoryId;
	
	@ApiModelProperty(notes="二级类目id，可以通过本文档中接口查询(微信接口)",allowEmptyValue=true,example="",allowableValues="")
	String secondaryCategoryId;
	
	@ApiModelProperty(notes="子商户id，对于一个母商户公众号下唯一",allowEmptyValue=true,example="",allowableValues="")
	String merchantId;
	
	@ApiModelProperty(notes="子商户状态，CHECKING",allowEmptyValue=true,example="",allowableValues="")
	String status;
	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String cuser;
	
	@ApiModelProperty(notes="最后操作时间",allowEmptyValue=true,example="",allowableValues="")
	Date ldate;
	
	@ApiModelProperty(notes="联系电话",allowEmptyValue=true,example="",allowableValues="")
	String phoneno;
	
	@ApiModelProperty(notes="最后操作人",allowEmptyValue=true,example="",allowableValues="")
	String lopuserid;
	
	@ApiModelProperty(notes="当前流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String bizProcInstId;
	
	@ApiModelProperty(notes="当前流程状态",allowEmptyValue=true,example="",allowableValues="")
	String bizFlowState;
	
	@ApiModelProperty(notes="核算项目platform/shop/location",allowEmptyValue=true,example="",allowableValues="")
	String acctPrjType;
	
	@ApiModelProperty(notes="归属平台机构号",allowEmptyValue=true,example="",allowableValues="")
	String platBranchId;
	
	@ApiModelProperty(notes="归属平台商户号",allowEmptyValue=true,example="",allowableValues="")
	String platShopId;
	
	@ApiModelProperty(notes="是否加入平台显示",allowEmptyValue=true,example="",allowableValues="")
	String isJoinPlat;
	
	@ApiModelProperty(notes="0-门店私有，1-商户私有，所有店铺共享，2-平台共有，所有商户共享",allowEmptyValue=true,example="",allowableValues="")
	String shareLvl;
	
	@ApiModelProperty(notes="管理员账户",allowEmptyValue=true,example="",allowableValues="")
	String mngUserid;
	
	@ApiModelProperty(notes="管理员姓名",allowEmptyValue=true,example="",allowableValues="")
	String mngUsername;

	/**
	 *店铺编号
	 **/
	public Shop(String id) {
		this.id = id;
	}
    
    /**
     * 商户信息
     **/
	public Shop() {
	}

}