package  com.mdp.sys.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-22
 */
@Data
@TableName("sys_user")
@ApiModel(description="用户表")
public class User  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="内部用户编号(账户编号)，如果是机构管理员账户，则=机构号,主键",allowEmptyValue=true,example="",allowableValues="")
	String userid;

	
	@ApiModelProperty(notes="全局唯一编号，也叫主账户，同一个人（比如同一个微信号，同一个邮箱，同一个手机号视为同一个人）。同一个人在mdp有唯一的主账号。",allowEmptyValue=true,example="",allowableValues="")
	String unionid;

	
	@ApiModelProperty(notes="登录展示使用用户编号",allowEmptyValue=true,example="",allowableValues="")
	String displayUserid;

	
	@ApiModelProperty(notes="是否被锁定0否1是",allowEmptyValue=true,example="",allowableValues="")
	String locked;

	
	@ApiModelProperty(notes="启用日期",allowEmptyValue=true,example="",allowableValues="")
	Date startdate;

	
	@ApiModelProperty(notes="昵称",allowEmptyValue=true,example="",allowableValues="")
	String nickname;

	
	@ApiModelProperty(notes="用户名称",allowEmptyValue=true,example="",allowableValues="")
	String username;

	
	@ApiModelProperty(notes="移动电话号码",allowEmptyValue=true,example="",allowableValues="")
	String phoneno;

	
	@ApiModelProperty(notes="密码",allowEmptyValue=true,example="",allowableValues="")
	String password;

	
	@ApiModelProperty(notes="盐值",allowEmptyValue=true,example="",allowableValues="")
	String salt;

	
	@ApiModelProperty(notes="密码类型1指纹2密码",allowEmptyValue=true,example="",allowableValues="")
	String pwdtype;

	
	@ApiModelProperty(notes="头像地址",allowEmptyValue=true,example="",allowableValues="")
	String headimgurl;

	
	@ApiModelProperty(notes="国家",allowEmptyValue=true,example="",allowableValues="")
	String country;

	
	@ApiModelProperty(notes="城市",allowEmptyValue=true,example="",allowableValues="")
	String city;

	
	@ApiModelProperty(notes="省份",allowEmptyValue=true,example="",allowableValues="")
	String province;

	
	@ApiModelProperty(notes="详细地址",allowEmptyValue=true,example="",allowableValues="")
	String address;

	
	@ApiModelProperty(notes="性别",allowEmptyValue=true,example="",allowableValues="")
	String sex;

	
	@ApiModelProperty(notes="到期日期",allowEmptyValue=true,example="",allowableValues="")
	Date enddate;

	
	@ApiModelProperty(notes="区县编号",allowEmptyValue=true,example="",allowableValues="")
	String districtId;

	
	@ApiModelProperty(notes="邮箱",allowEmptyValue=true,example="",allowableValues="")
	String email;

	
	@ApiModelProperty(notes="指纹1",allowEmptyValue=true,example="",allowableValues="")
	String fgOne;

	
	@ApiModelProperty(notes="指纹2",allowEmptyValue=true,example="",allowableValues="")
	String fgTwo;

	
	@ApiModelProperty(notes="指纹3",allowEmptyValue=true,example="",allowableValues="")
	String fgThr;

	
	@ApiModelProperty(notes="身份证号码",allowEmptyValue=true,example="",allowableValues="")
	String idCardNo;

	
	@ApiModelProperty(notes="办公室电话",allowEmptyValue=true,example="",allowableValues="")
	String officePhoneno;

	
	@ApiModelProperty(notes="当前流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String bizProcInstId;

	
	@ApiModelProperty(notes="当前流程状态",allowEmptyValue=true,example="",allowableValues="")
	String bizFlowState;

	
	@ApiModelProperty(notes="从平台角度看会员类型0-个人账户、1-企业管理员账户、2-企业员工账户，个人账户无须绑定机构号，个人子账户可升级为企业员工账户，企业账户必须绑定机构编号branchId个人账户升级后，保留个人主账户，个人子账户绑定企业编号成为企业员工账户",allowEmptyValue=true,example="",allowableValues="")
	String memType;

	
	@ApiModelProperty(notes="机构会员自己的机构会员的机构号，理解为客户的客户",allowEmptyValue=true,example="",allowableValues="")
	String orgId;

	
	@ApiModelProperty(notes="备用邮箱",allowEmptyValue=true,example="",allowableValues="")
	String emailBak;

	
	@ApiModelProperty(notes="1-高风险，2-中风险，3-低风险",allowEmptyValue=true,example="",allowableValues="")
	String pwdStrong;

	
	@ApiModelProperty(notes="锁定类型:0-注册等待邮箱验证，1-注册等待修改初始密码，2-注册等待验证手机号码，3-密码高风险，等待重新修改密码，9-业务需要锁定禁止登录，10-账户被锁定，请联系客服",allowEmptyValue=true,example="",allowableValues="")
	String lockType;

	
	@ApiModelProperty(notes="锁定原因",allowEmptyValue=true,example="",allowableValues="")
	String lockRemark;

	
	@ApiModelProperty(notes="更新日期",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;

	
	@ApiModelProperty(notes="相对于平台来说的账户类型0-子账户，1-主账户。",allowEmptyValue=true,example="",allowableValues="")
	String atype;

	
	@ApiModelProperty(notes="机构主子账户归属的机构编号,如果是个人，这里填虚拟机构编号，作为虚拟的机构号，方便将来升级成企业号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="洲别",allowEmptyValue=true,example="",allowableValues="")
	String continent;

	
	@ApiModelProperty(notes="从入驻企业角度看协作类型0-企业内部人员，1-客户，2-供应商，3-上级机构，4-下属机构",allowEmptyValue=true,example="",allowableValues="")
	String cpaType;

	
	@ApiModelProperty(notes="协作组织0-个人，1机构，如果是机构，机构号填入orgId",allowEmptyValue=true,example="",allowableValues="")
	String cpaOrg;


	@ApiModelProperty(notes="协作组织的用户编号",allowEmptyValue=true,example="",allowableValues="")
	String cpaUserid;

	
	@ApiModelProperty(notes="个人账户拥有的角色，逗号分割",allowEmptyValue=true,example="",allowableValues="")
	String roleids;

	
	@ApiModelProperty(notes="生日",allowEmptyValue=true,example="",allowableValues="")
	Date birthday;

	
	@ApiModelProperty(notes="商户编号",allowEmptyValue=true,example="",allowableValues="")
	String shopId;

	
	@ApiModelProperty(notes="职业编号",allowEmptyValue=true,example="",allowableValues="")
	String profeId;

	
	@ApiModelProperty(notes="职业名称",allowEmptyValue=true,example="",allowableValues="")
	String profeName;

	
	@ApiModelProperty(notes="等级会员，根据经验值而定",allowEmptyValue=true,example="",allowableValues="")
	String gradeId;

	
	@ApiModelProperty(notes="等级会员名称",allowEmptyValue=true,example="",allowableValues="")
	String gradeName;

	
	@ApiModelProperty(notes="权益等级青铜、白银、黄金、紫金、钻石",allowEmptyValue=true,example="",allowableValues="")
	String ilvlId;

	
	@ApiModelProperty(notes="权益等级名称",allowEmptyValue=true,example="",allowableValues="")
	String ilvlName;

	
	@ApiModelProperty(notes="会员权益状态0-无效，1-有效，2-过期",allowEmptyValue=true,example="",allowableValues="")
	String istatus;

	
	@ApiModelProperty(notes="权益开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date istime;

	
	@ApiModelProperty(notes="权益结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date ietime;

	
	@ApiModelProperty(notes="人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段",allowEmptyValue=true,example="",allowableValues="")
	String validLvls;

	
	@ApiModelProperty(notes="个性化签名",allowEmptyValue=true,example="",allowableValues="")
	String features;

	
	@ApiModelProperty(notes="职业类型1-开发类，2-测试类，3-设计类，4-管理类；",allowEmptyValue=true,example="",allowableValues="")
	String profeType;

	
	@ApiModelProperty(notes="用户账户状态0-初始，1-起效，2-注销申请，3-注销后删除",allowEmptyValue=true,example="",allowableValues="")
	String ustatus;

	
	@ApiModelProperty(notes="信用等级编号",allowEmptyValue=true,example="",allowableValues="")
	String creditId;

	
	@ApiModelProperty(notes="信用等级分数",allowEmptyValue=true,example="",allowableValues="")
	Integer creditScore;

	
	@ApiModelProperty(notes="服务保障等级0-初始，1-金，2-银，3-铜",allowEmptyValue=true,example="",allowableValues="")
	String guardId;

	
	@ApiModelProperty(notes="是否对互联网用户开放查询0-否1是",allowEmptyValue=true,example="",allowableValues="")
	String open;

	
	@ApiModelProperty(notes="简介备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="营业时间说明09:00-12:00",allowEmptyValue=true,example="",allowableValues="")
	String bizHours;

	
	@ApiModelProperty(notes="技能编号列表",allowEmptyValue=true,example="",allowableValues="")
	String skillIds;

	
	@ApiModelProperty(notes="技能名称列表",allowEmptyValue=true,example="",allowableValues="")
	String skillNames;

	/**
	 *内部用户编号(账户编号)，如果是机构管理员账户，则=机构号
	 **/
	public User(String userid) {
		this.userid = userid;
	}
    
    /**
     * 用户表
     **/
	public User() {
	}

}