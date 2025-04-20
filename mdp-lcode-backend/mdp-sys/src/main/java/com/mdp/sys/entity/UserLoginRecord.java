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
 * @author code-gen
 * @since 2023-10-1
 */
@Data
@TableName("sys_user_login_record")
@ApiModel(description="用户登录信息登记")
public class UserLoginRecord  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="用户编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;

	
	@ApiModelProperty(notes="商户编号",allowEmptyValue=true,example="",allowableValues="")
	String shopId;

	
	@ApiModelProperty(notes="门店编号",allowEmptyValue=true,example="",allowableValues="")
	String locationId;

	
	@ApiModelProperty(notes="登录的商户编号",allowEmptyValue=true,example="",allowableValues="")
	String loginShopid;

	
	@ApiModelProperty(notes="登录的门店编号",allowEmptyValue=true,example="",allowableValues="")
	String loginLocationid;

	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="1-微信扫门店二维码，2-点击小程序登录，3-账户密码登录，4-手机号码登录",allowEmptyValue=true,example="",allowableValues="")
	String loginType;

	
	@ApiModelProperty(notes="登录时间",allowEmptyValue=true,example="",allowableValues="")
	Date loginTime;

	
	@ApiModelProperty(notes="登录机构号",allowEmptyValue=true,example="",allowableValues="")
	String loginBranchId;

	
	@ApiModelProperty(notes="登录用户名",allowEmptyValue=true,example="",allowableValues="")
	String username;

	
	@ApiModelProperty(notes="授权码",allowEmptyValue=true,example="",allowableValues="")
	String authId;

	
	@ApiModelProperty(notes="mdp平台appid",allowEmptyValue=true,example="",allowableValues="")
	String mdpAppid;

	
	@ApiModelProperty(notes="批处理编号",allowEmptyValue=true,example="",allowableValues="")
	String lockNo;

	
	@ApiModelProperty(notes="批处理状态0-待处理1-处理中2处理完毕",allowEmptyValue=true,example="",allowableValues="")
	String lockStatus;

	
	@ApiModelProperty(notes="批处理时间",allowEmptyValue=true,example="",allowableValues="")
	Date lockTime;

	
	@ApiModelProperty(notes="登录手机号",allowEmptyValue=true,example="",allowableValues="")
	String phoneno;

	
	@ApiModelProperty(notes="登录ip",allowEmptyValue=true,example="",allowableValues="")
	String loginIp;

	
	@ApiModelProperty(notes="mem-会员端用户，adm-管理端用户",allowEmptyValue=true,example="",allowableValues="")
	String userType;

	
	@ApiModelProperty(notes="登录设备编号",allowEmptyValue=true,example="",allowableValues="")
	String loginDeviceId;

	
	@ApiModelProperty(notes="设备特征码",allowEmptyValue=true,example="",allowableValues="")
	String loginDeviceSn;

	
	@ApiModelProperty(notes="请求特征码",allowEmptyValue=true,example="",allowableValues="")
	String userAgent;

	
	@ApiModelProperty(notes="请求特征码-方便前端补充回填部分信息",allowEmptyValue=true,example="",allowableValues="")
	String reqNo;

	
	@ApiModelProperty(notes="设备类型COMPUTER/MOBILE/TABLET/GAME_CONSOLE/DMR/WEARABLE/UNKNOWN",allowEmptyValue=true,example="",allowableValues="")
	String deviceType;

	
	@ApiModelProperty(notes="操作系统Windows/ios/Android",allowEmptyValue=true,example="",allowableValues="")
	String os;

	
	@ApiModelProperty(notes="操作系统版本如Android",allowEmptyValue=true,example="",allowableValues="")
	String osVersion;

	
	@ApiModelProperty(notes="操作系统名称如Android",allowEmptyValue=true,example="",allowableValues="")
	String osName;

	
	@ApiModelProperty(notes="浏览器渲染引擎如WEBKIT",allowEmptyValue=true,example="",allowableValues="")
	String renderingEngine;

	
	@ApiModelProperty(notes="设备生产厂商",allowEmptyValue=true,example="",allowableValues="")
	String deviceManufacturer;

	
	@ApiModelProperty(notes="浏览器组",allowEmptyValue=true,example="",allowableValues="")
	String browerGroup;

	
	@ApiModelProperty(notes="浏览器名称",allowEmptyValue=true,example="",allowableValues="")
	String borderName;

	
	@ApiModelProperty(notes="浏览器版本",allowEmptyValue=true,example="",allowableValues="")
	String borderVersion;

	
	@ApiModelProperty(notes="经度",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal longitude;

	
	@ApiModelProperty(notes="纬度",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal latitude;

	
	@ApiModelProperty(notes="区域编号精确到4级镇、街道",allowEmptyValue=true,example="",allowableValues="")
	String regionId;

	
	@ApiModelProperty(notes="定位街道名称",allowEmptyValue=true,example="",allowableValues="")
	String regionName;

	
	@ApiModelProperty(notes="定位格式化地址名称",allowEmptyValue=true,example="",allowableValues="")
	String formatAddress;

	
	@ApiModelProperty(notes="定位区县编号",allowEmptyValue=true,example="",allowableValues="")
	String districtId;

	
	@ApiModelProperty(notes="用户类型",allowEmptyValue=true,example="",allowableValues="")
	String memType;

	
	@ApiModelProperty(notes="1-登录成功，0-登录失败",allowEmptyValue=true,example="",allowableValues="")
	String loginStatus;

	
	@ApiModelProperty(notes="登录成功或者失败的说明",allowEmptyValue=true,example="",allowableValues="")
	String loginMsg;

	
	@ApiModelProperty(notes="授权码",allowEmptyValue=true,example="",allowableValues="")
	String authType;

	
	@ApiModelProperty(notes="授权码",allowEmptyValue=true,example="",allowableValues="")
	String grantType;

	/**
	 *主键
	 **/
	public UserLoginRecord(String id) {
		this.id = id;
	}
    
    /**
     * 用户登录信息登记
     **/
	public UserLoginRecord() {
	}

}