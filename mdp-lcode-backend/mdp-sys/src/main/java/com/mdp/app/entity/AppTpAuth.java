package  com.mdp.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 app  小模块 <br> 
 * 实体 AppTpAuth所有属性名: <br>
 *	"authId","第三方应用编号","mdpAppid","应用编号","appid","对应第三方提供给的appid","appSecret","加密串","appToken","登录验证码","name","应用名称","logoUrl","应用logo","remark","应用描述","bizType","应用分类","deptid","归属部门","branchId","归属机构","loginUrl","登录地址","cdate","创建日期","cuserid","创建人","authUrl","授权地址","authPattern","监听发起授权地址","encKey","秘钥EncodingAESKey(消息加密密钥)","enabled","应用状态0下架1上架","payMchid","商户编号（作废）","payNotifyUrl","付款成功通知地址（作废）","openPay","开通支付（作废）","payKey","支付加密秘钥（作废）","msgNotifyUrl","消息推送地址","ips","服务器IP地址列表ip1,ip2,ip3","locationId","绑定的门店（运营方总店）","shopId","绑定的商户编号（运营方总商户编号）","locationName","门店名称","shopName","商户名称","multiLoca","是否为多店0否1是","genUseridType","创建userid的方式创same-branchId不同机构，same-shopId不同商户，same-phone不同手机号码，same-authId不同授权码，same-mdpAppid不同的mdp应用编号","useridLocationidType","用户与门店关联关系0-不创建，1-创建","pubAuthId","第三方应用编号";<br>
 * 当前主键(包括多主键):<br>
 *	auth_id;<br>
 */
 @Data
@TableName("app_tp_auth")
@ApiModel(description="MDP平台应用与第三方应用关系")
public class AppTpAuth  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="第三方应用编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String authId;
	
	@ApiModelProperty(notes="应用编号",allowEmptyValue=true,example="",allowableValues="")
	String mdpAppid;
	
	@ApiModelProperty(notes="对应第三方提供给的appid",allowEmptyValue=true,example="",allowableValues="")
	String appid;
	
	@ApiModelProperty(notes="加密串",allowEmptyValue=true,example="",allowableValues="")
	String appSecret;
	
	@ApiModelProperty(notes="登录验证码",allowEmptyValue=true,example="",allowableValues="")
	String appToken;
	
	@ApiModelProperty(notes="应用名称",allowEmptyValue=true,example="",allowableValues="")
	String name;
	
	@ApiModelProperty(notes="应用logo",allowEmptyValue=true,example="",allowableValues="")
	String logoUrl;
	
	@ApiModelProperty(notes="应用描述",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="应用分类",allowEmptyValue=true,example="",allowableValues="")
	String bizType;
	
	@ApiModelProperty(notes="归属部门",allowEmptyValue=true,example="",allowableValues="")
	String deptid;
	
	@ApiModelProperty(notes="归属机构",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="登录地址",allowEmptyValue=true,example="",allowableValues="")
	String loginUrl;
	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date cdate;
	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;
	
	@ApiModelProperty(notes="授权地址",allowEmptyValue=true,example="",allowableValues="")
	String authUrl;
	
	@ApiModelProperty(notes="监听发起授权地址",allowEmptyValue=true,example="",allowableValues="")
	String authPattern;
	
	@ApiModelProperty(notes="秘钥EncodingAESKey(消息加密密钥)",allowEmptyValue=true,example="",allowableValues="")
	String encKey;
	
	@ApiModelProperty(notes="应用状态0下架1上架",allowEmptyValue=true,example="",allowableValues="")
	String enabled;
	
	@ApiModelProperty(notes="商户编号（作废）",allowEmptyValue=true,example="",allowableValues="")
	String payMchid;
	
	@ApiModelProperty(notes="付款成功通知地址（作废）",allowEmptyValue=true,example="",allowableValues="")
	String payNotifyUrl;
	
	@ApiModelProperty(notes="开通支付（作废）",allowEmptyValue=true,example="",allowableValues="")
	String openPay;
	
	@ApiModelProperty(notes="支付加密秘钥（作废）",allowEmptyValue=true,example="",allowableValues="")
	String payKey;
	
	@ApiModelProperty(notes="消息推送地址",allowEmptyValue=true,example="",allowableValues="")
	String msgNotifyUrl;
	
	@ApiModelProperty(notes="服务器IP地址列表ip1,ip2,ip3",allowEmptyValue=true,example="",allowableValues="")
	String ips;
	
	@ApiModelProperty(notes="绑定的门店（运营方总店）",allowEmptyValue=true,example="",allowableValues="")
	String locationId;
	
	@ApiModelProperty(notes="绑定的商户编号（运营方总商户编号）",allowEmptyValue=true,example="",allowableValues="")
	String shopId;
	
	@ApiModelProperty(notes="门店名称",allowEmptyValue=true,example="",allowableValues="")
	String locationName;
	
	@ApiModelProperty(notes="商户名称",allowEmptyValue=true,example="",allowableValues="")
	String shopName;
	
	@ApiModelProperty(notes="是否为多店0否1是",allowEmptyValue=true,example="",allowableValues="")
	String multiLoca;
	
	@ApiModelProperty(notes="创建userid的方式创same-branchId不同机构，same-shopId不同商户，same-phone不同手机号码，same-authId不同授权码，same-mdpAppid不同的mdp应用编号",allowEmptyValue=true,example="",allowableValues="")
	String genUseridType;
	
	@ApiModelProperty(notes="用户与门店关联关系0-不创建，1-创建",allowEmptyValue=true,example="",allowableValues="")
	String useridLocationidType;
	
	@ApiModelProperty(notes="第三方应用编号",allowEmptyValue=true,example="",allowableValues="")
	String pubAuthId;

	/**
	 *第三方应用编号
	 **/
	public AppTpAuth(String authId) {
		this.authId = authId;
	}
    
    /**
     * MDP平台应用与第三方应用关系
     **/
	public AppTpAuth() {
	}

}