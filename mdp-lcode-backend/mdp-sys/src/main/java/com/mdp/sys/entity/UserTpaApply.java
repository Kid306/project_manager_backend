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
 * 实体 UserTpaApply所有属性名: <br>
 *	"id","主键","userid","申请人的用户编号(第三方)","username","用户名称","phoneNo","电话号码","postName","申请的岗位名称","branchId","申请的机构编号","shopId","申请的商户编号","shopName","申请的商户名称","locationId","申请的门店编号","businessName","申请的门店名称","deptid","申请的门店的部门编号","longitude","申请的门店的经度","latitude","申请的门店的纬度","province","申请的门店的省份","provinceCode","申请的门店的省份代码","city","申请的门店的城市","cityCode","很轻的门店的城市代码","district","申请的门店的区县","districtId","申请的门店的区县代码","address","申请的门店的地址","source","申请来源(从哪个途径申请)","status","申请的状态(0.申请中","loginNo","后台登录账号","remark","备注","createDate","申请时间","updateDate","修改时间","cuserid","创建人id","lopuserid","最后操作人";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@TableName("sys_user_tpa_apply")
@ApiModel(description="岗位申请记录")
public class UserTpaApply  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;
	
	@ApiModelProperty(notes="申请人的用户编号(第三方)",allowEmptyValue=true,example="",allowableValues="")
	String userid;
	
	@ApiModelProperty(notes="用户名称",allowEmptyValue=true,example="",allowableValues="")
	String username;
	
	@ApiModelProperty(notes="电话号码",allowEmptyValue=true,example="",allowableValues="")
	String phoneNo;
	
	@ApiModelProperty(notes="申请的岗位名称",allowEmptyValue=true,example="",allowableValues="")
	String postName;
	
	@ApiModelProperty(notes="申请的机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="申请的商户编号",allowEmptyValue=true,example="",allowableValues="")
	String shopId;
	
	@ApiModelProperty(notes="申请的商户名称",allowEmptyValue=true,example="",allowableValues="")
	String shopName;
	
	@ApiModelProperty(notes="申请的门店编号",allowEmptyValue=true,example="",allowableValues="")
	String locationId;
	
	@ApiModelProperty(notes="申请的门店名称",allowEmptyValue=true,example="",allowableValues="")
	String businessName;
	
	@ApiModelProperty(notes="申请的门店的部门编号",allowEmptyValue=true,example="",allowableValues="")
	String deptid;
	
	@ApiModelProperty(notes="申请的门店的经度",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal longitude;
	
	@ApiModelProperty(notes="申请的门店的纬度",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal latitude;
	
	@ApiModelProperty(notes="申请的门店的省份",allowEmptyValue=true,example="",allowableValues="")
	String province;
	
	@ApiModelProperty(notes="申请的门店的省份代码",allowEmptyValue=true,example="",allowableValues="")
	String provinceCode;
	
	@ApiModelProperty(notes="申请的门店的城市",allowEmptyValue=true,example="",allowableValues="")
	String city;
	
	@ApiModelProperty(notes="很轻的门店的城市代码",allowEmptyValue=true,example="",allowableValues="")
	String cityCode;
	
	@ApiModelProperty(notes="申请的门店的区县",allowEmptyValue=true,example="",allowableValues="")
	String district;
	
	@ApiModelProperty(notes="申请的门店的区县代码",allowEmptyValue=true,example="",allowableValues="")
	String districtId;
	
	@ApiModelProperty(notes="申请的门店的地址",allowEmptyValue=true,example="",allowableValues="")
	String address;
	
	@ApiModelProperty(notes="申请来源(从哪个途径申请)",allowEmptyValue=true,example="",allowableValues="")
	String source;
	
	@ApiModelProperty(notes="申请的状态(0.申请中",allowEmptyValue=true,example="",allowableValues="")
	String status;
	
	@ApiModelProperty(notes="后台登录账号",allowEmptyValue=true,example="",allowableValues="")
	String loginNo;
	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;
	
	@ApiModelProperty(notes="申请时间",allowEmptyValue=true,example="",allowableValues="")
	Date createDate;
	
	@ApiModelProperty(notes="修改时间",allowEmptyValue=true,example="",allowableValues="")
	Date updateDate;
	
	@ApiModelProperty(notes="创建人id",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;
	
	@ApiModelProperty(notes="最后操作人",allowEmptyValue=true,example="",allowableValues="")
	String lopuserid;

	/**
	 *主键
	 **/
	public UserTpaApply(String id) {
		this.id = id;
	}
    
    /**
     * 岗位申请记录
     **/
	public UserTpaApply() {
	}

}