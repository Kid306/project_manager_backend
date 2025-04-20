package com.mdp.micro;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-20
 */
@Data
@TableName("sys_user")
@Schema(description="用户表")
public class SysUser  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="内部用户编号(账户编号)，如果是机构管理员账户，则=机构号,主键")
	String userid;

	
	@Schema(description="全局唯一编号，也叫主账户，同一个人（比如同一个微信号，同一个邮箱，同一个手机号视为同一个人）。同一个人在mdp有唯一的主账号。")
	String unionid;

	
	@Schema(description="登录展示使用用户编号")
	String displayUserid;

	
	@Schema(description="是否被锁定0否1是")
	String locked;

	
	@Schema(description="启用日期")
	Date startdate;

	
	@Schema(description="昵称")
	String nickname;

	
	@Schema(description="用户名称")
	String username;

	
	@Schema(description="移动电话号码")
	String phoneno;

	
	@Schema(description="密码")
	String password;

	
	@Schema(description="盐值")
	String salt;

	
	@Schema(description="密码类型1指纹2密码")
	String pwdtype;

	
	@Schema(description="头像地址")
	String headimgurl;

	
	@Schema(description="国家")
	String country;

	
	@Schema(description="城市")
	String city;

	
	@Schema(description="省份")
	String province;

	
	@Schema(description="详细地址")
	String address;

	
	@Schema(description="性别")
	String sex;

	
	@Schema(description="到期日期")
	Date enddate;

	
	@Schema(description="区县编号")
	String districtId;

	
	@Schema(description="邮箱")
	String email;

	
	@Schema(description="指纹1")
	String fgOne;

	
	@Schema(description="指纹2")
	String fgTwo;

	
	@Schema(description="指纹3")
	String fgThr;

	
	@Schema(description="身份证号码")
	String idCardNo;

	
	@Schema(description="办公室电话")
	String officePhoneno;

	
	@Schema(description="当前流程实例编号")
	String bizProcInstId;

	
	@Schema(description="当前流程状态")
	String bizFlowState;

	
	@Schema(description="从平台角度看会员类型0-个人账户、1-企业管理员账户、2-企业员工账户，个人账户无须绑定机构号，个人子账户可升级为企业员工账户，企业账户必须绑定机构编号branchId个人账户升级后，保留个人主账户，个人子账户绑定企业编号成为企业员工账户")
	String memType;

	
	@Schema(description="机构会员自己的机构会员的机构号，理解为客户的客户")
	String orgId;

	
	@Schema(description="备用邮箱")
	String emailBak;

	
	@Schema(description="1-高风险，2-中风险，3-低风险")
	String pwdStrong;

	
	@Schema(description="锁定类型:0-注册等待邮箱验证，1-注册等待修改初始密码，2-注册等待验证手机号码，3-密码高风险，等待重新修改密码，9-业务需要锁定禁止登录，10-账户被锁定，请联系客服")
	String lockType;

	
	@Schema(description="锁定原因")
	String lockRemark;

	
	@Schema(description="更新日期")
	Date ltime;

	
	@Schema(description="相对于平台来说的账户类型0-子账户，1-主账户。")
	String atype;

	
	@Schema(description="机构主子账户归属的机构编号,如果是个人，这里填虚拟机构编号，作为虚拟的机构号，方便将来升级成企业号")
	String branchId;

	
	@Schema(description="洲别")
	String continent;

	
	@Schema(description="从入驻企业角度看协作类型0-企业内部人员，1-客户，2-供应商，3-上级机构，4-下属机构")
	String cpaType;

	
	@Schema(description="协作组织0-个人，1机构，如果是机构，机构号填入orgId")
	String cpaOrg;

	
	@Schema(description="个人账户拥有的角色，逗号分割")
	String roleids;

	
	@Schema(description="生日")
	Date birthday;

	
	@Schema(description="商户编号")
	String shopId;

	
	@Schema(description="职业编号")
	String profeId;

	
	@Schema(description="职业名称")
	String profeName;

	
	@Schema(description="等级会员，根据经验值而定")
	String gradeId;

	
	@Schema(description="等级会员名称")
	String gradeName;

	
	@Schema(description="权益等级青铜、白银、黄金、紫金、钻石")
	String ilvlId;

	
	@Schema(description="权益等级名称")
	String ilvlName;

	
	@Schema(description="会员权益状态0-无效，1-有效，2-过期")
	String istatus;

	
	@Schema(description="权益开始时间")
	Date istime;

	
	@Schema(description="权益结束时间")
	Date ietime;

	
	@Schema(description="人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段")
	String validLvls;

	
	@Schema(description="个性化签名")
	String features;

	
	@Schema(description="职业类型1-开发类，2-测试类，3-设计类，4-管理类；")
	String profeType;

	
	@Schema(description="用户账户状态0-初始，1-起效，2-注销申请，3-注销后删除")
	String ustatus;

	
	@Schema(description="信用等级编号")
	String creditId;

	
	@Schema(description="信用等级分数")
	Integer creditScore;

	
	@Schema(description="服务保障等级0-初始，1-金，2-银，3-铜")
	String guardId;

	
	@Schema(description="是否对互联网用户开放查询0-否1是")
	String open;

	
	@Schema(description="简介备注")
	String remark;

	
	@Schema(description="营业时间说明09:00-12:00")
	String bizHours;

	
	@Schema(description="技能编号列表")
	String skillIds;

	
	@Schema(description="技能名称列表")
	String skillNames;

	/**
	 *内部用户编号(账户编号)，如果是机构管理员账户，则=机构号
	 **/
	public SysUser(String userid) {
		this.userid = userid;
	}
    
    /**
     * 用户表
     **/
	public SysUser() {
	}

}