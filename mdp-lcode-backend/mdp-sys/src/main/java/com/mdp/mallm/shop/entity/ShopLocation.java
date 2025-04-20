package  com.mdp.mallm.shop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 mallm.shop  小模块 <br> 
 * 实体 ShopLocation所有属性名: <br>
 *	"id","门店编号","shopId","店铺编号","longitude","经度","latitude","纬度","address","地址","rank","服务范围","province","省","city","市","district","区","admin","主负责人","createTime","创建时间","title","门店简称","formatAddress","地址简称","country","国家","districtId","区县编号","deptid","机构编号","branchId","云用户机构编号","businessName","门店名称（仅为商户名，如：国美、麦当劳，不应包含地区、地址、分店名等信息，错误示例：北京国美）","branchName","分店名称（不应包含地区信息，不应与门店名有重复，错误示例：北京王府井店）","telephone","门店的电话（纯数字，区号、分机号均由“-”隔开）","categories","门店的类型（不同级分类用“,”隔开，如：美食，川菜，火锅。详细分类参见附件：微信门店类目表）","offsetType","坐标类型：","recommend","推荐品，餐厅可为推荐菜；酒店为推荐套房；景点为推荐游玩景点等，针对自己行业的推荐内容","special","特色服务，如免费wifi，免费停车，送货上门等商户能提供的特色功能或服务","introduction","商户简介，主要介绍商户信息等","openTime","营业时间，24","avgPrice","人均价格，大于0","status","审核状态","reason","不通过原因","poiId","微信传回的对应id","serialNum","门店编号","admcity","职能城市","admcityCode","职能城市代码","admarea","职能地区","admareaCode","职能地区代码","provinceCode","省份代码","cityCode","城市代码","street","街道","streetCode","街道代码","localtion","定位地址","shopStatus","门店状态:1-预热","labelId","标签id","serviceContent","服务内容","orders","排序","dataStatus","状态:0-正常","cuserid","创建人","lopuserid","最后操作人","lopcreate","最后操作时间","locationType","门店类型:1-直营","regionId","区域编号精确到4级街道或者乡镇","goodsImageShowType","多个商品情况下商品的展示方式(1:三个商品并排，2：两列，第一列放一个商品，第二列两个商品)","bizProcInstId","当前流程实例编号","bizFlowState","当前流程状态","shareLvl","0-门店私有，1-商户私有，所有店铺共享，2-平台共有，所有商户共享","isHead","是否总部店","mngUserid","管理员账户","mngUsername","管理员姓名";<br>
 * 当前主键(包括多主键):<br>
 *	id,shop_id;<br>
 */
 @Data
@TableName("shop_location")
@ApiModel(description="门店地址表")
public class ShopLocation  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
    @ApiModelProperty(notes="门店编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String id;
    @TableIds
    @ApiModelProperty(notes="店铺编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String shopId;
	
	@ApiModelProperty(notes="经度",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal longitude;
	
	@ApiModelProperty(notes="纬度",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal latitude;
	
	@ApiModelProperty(notes="地址",allowEmptyValue=true,example="",allowableValues="")
	String address;
	
	@ApiModelProperty(notes="服务范围",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal rank;
	
	@ApiModelProperty(notes="省",allowEmptyValue=true,example="",allowableValues="")
	String province;
	
	@ApiModelProperty(notes="市",allowEmptyValue=true,example="",allowableValues="")
	String city;
	
	@ApiModelProperty(notes="区",allowEmptyValue=true,example="",allowableValues="")
	String district;
	
	@ApiModelProperty(notes="主负责人",allowEmptyValue=true,example="",allowableValues="")
	String admin;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date createTime;
	
	@ApiModelProperty(notes="门店简称",allowEmptyValue=true,example="",allowableValues="")
	String title;
	
	@ApiModelProperty(notes="地址简称",allowEmptyValue=true,example="",allowableValues="")
	String formatAddress;
	
	@ApiModelProperty(notes="国家",allowEmptyValue=true,example="",allowableValues="")
	String country;
	
	@ApiModelProperty(notes="区县编号",allowEmptyValue=true,example="",allowableValues="")
	String districtId;
	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String deptid;
	
	@ApiModelProperty(notes="云用户机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="门店名称（仅为商户名，如：国美、麦当劳，不应包含地区、地址、分店名等信息，错误示例：北京国美）",allowEmptyValue=true,example="",allowableValues="")
	String businessName;
	
	@ApiModelProperty(notes="分店名称（不应包含地区信息，不应与门店名有重复，错误示例：北京王府井店）",allowEmptyValue=true,example="",allowableValues="")
	String branchName;
	
	@ApiModelProperty(notes="门店的电话（纯数字，区号、分机号均由“-”隔开）",allowEmptyValue=true,example="",allowableValues="")
	String telephone;
	
	@ApiModelProperty(notes="门店的类型（不同级分类用“,”隔开，如：美食，川菜，火锅。详细分类参见附件：微信门店类目表）",allowEmptyValue=true,example="",allowableValues="")
	String categories;
	
	@ApiModelProperty(notes="坐标类型：",allowEmptyValue=true,example="",allowableValues="")
	String offsetType;
	
	@ApiModelProperty(notes="推荐品，餐厅可为推荐菜；酒店为推荐套房；景点为推荐游玩景点等，针对自己行业的推荐内容",allowEmptyValue=true,example="",allowableValues="")
	String recommend;
	
	@ApiModelProperty(notes="特色服务，如免费wifi，免费停车，送货上门等商户能提供的特色功能或服务",allowEmptyValue=true,example="",allowableValues="")
	String special;
	
	@ApiModelProperty(notes="商户简介，主要介绍商户信息等",allowEmptyValue=true,example="",allowableValues="")
	String introduction;
	
	@ApiModelProperty(notes="营业时间，24",allowEmptyValue=true,example="",allowableValues="")
	String openTime;
	
	@ApiModelProperty(notes="人均价格，大于0",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal avgPrice;
	
	@ApiModelProperty(notes="审核状态",allowEmptyValue=true,example="",allowableValues="")
	String status;
	
	@ApiModelProperty(notes="不通过原因",allowEmptyValue=true,example="",allowableValues="")
	String reason;
	
	@ApiModelProperty(notes="微信传回的对应id",allowEmptyValue=true,example="",allowableValues="")
	String poiId;
	
	@ApiModelProperty(notes="门店编号",allowEmptyValue=true,example="",allowableValues="")
	String serialNum;
	
	@ApiModelProperty(notes="职能城市",allowEmptyValue=true,example="",allowableValues="")
	String admcity;
	
	@ApiModelProperty(notes="职能城市代码",allowEmptyValue=true,example="",allowableValues="")
	String admcityCode;
	
	@ApiModelProperty(notes="职能地区",allowEmptyValue=true,example="",allowableValues="")
	String admarea;
	
	@ApiModelProperty(notes="职能地区代码",allowEmptyValue=true,example="",allowableValues="")
	String admareaCode;
	
	@ApiModelProperty(notes="省份代码",allowEmptyValue=true,example="",allowableValues="")
	String provinceCode;
	
	@ApiModelProperty(notes="城市代码",allowEmptyValue=true,example="",allowableValues="")
	String cityCode;
	
	@ApiModelProperty(notes="街道",allowEmptyValue=true,example="",allowableValues="")
	String street;
	
	@ApiModelProperty(notes="街道代码",allowEmptyValue=true,example="",allowableValues="")
	String streetCode;
	
	@ApiModelProperty(notes="定位地址",allowEmptyValue=true,example="",allowableValues="")
	String localtion;
	
	@ApiModelProperty(notes="门店状态:1-预热",allowEmptyValue=true,example="",allowableValues="")
	String shopStatus;
	
	@ApiModelProperty(notes="标签id",allowEmptyValue=true,example="",allowableValues="")
	String labelId;
	
	@ApiModelProperty(notes="服务内容",allowEmptyValue=true,example="",allowableValues="")
	String serviceContent;
	
	@ApiModelProperty(notes="排序",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal orders;
	
	@ApiModelProperty(notes="状态:0-正常",allowEmptyValue=true,example="",allowableValues="")
	String dataStatus;
	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;
	
	@ApiModelProperty(notes="最后操作人",allowEmptyValue=true,example="",allowableValues="")
	String lopuserid;
	
	@ApiModelProperty(notes="最后操作时间",allowEmptyValue=true,example="",allowableValues="")
	Date lopcreate;
	
	@ApiModelProperty(notes="门店类型:1-直营",allowEmptyValue=true,example="",allowableValues="")
	String locationType;
	
	@ApiModelProperty(notes="区域编号精确到4级街道或者乡镇",allowEmptyValue=true,example="",allowableValues="")
	String regionId;
	
	@ApiModelProperty(notes="多个商品情况下商品的展示方式(1:三个商品并排，2：两列，第一列放一个商品，第二列两个商品)",allowEmptyValue=true,example="",allowableValues="")
	String goodsImageShowType;
	
	@ApiModelProperty(notes="当前流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String bizProcInstId;
	
	@ApiModelProperty(notes="当前流程状态",allowEmptyValue=true,example="",allowableValues="")
	String bizFlowState;
	
	@ApiModelProperty(notes="0-门店私有，1-商户私有，所有店铺共享，2-平台共有，所有商户共享",allowEmptyValue=true,example="",allowableValues="")
	String shareLvl;
	
	@ApiModelProperty(notes="是否总部店",allowEmptyValue=true,example="",allowableValues="")
	String isHead;
	
	@ApiModelProperty(notes="管理员账户",allowEmptyValue=true,example="",allowableValues="")
	String mngUserid;
	
	@ApiModelProperty(notes="管理员姓名",allowEmptyValue=true,example="",allowableValues="")
	String mngUsername;

	/**
	 *门店编号,店铺编号
	 **/
	public ShopLocation(String id,String shopId) {
		this.id = id;
		this.shopId = shopId;
	}
    
    /**
     * 门店地址表
     **/
	public ShopLocation() {
	}

}