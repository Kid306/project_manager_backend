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
 * @since 2023-9-24
 */
@Data
@TableName("sys_branch")
@ApiModel(description="管理端机构表（机构下面若干部门）")
public class Branch  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="机构编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="机构名称",allowEmptyValue=true,example="",allowableValues="")
	String branchName;

	
	@ApiModelProperty(notes="是否可用",allowEmptyValue=true,example="",allowableValues="")
	String enabled;

	
	@ApiModelProperty(notes="行业分类",allowEmptyValue=true,example="",allowableValues="")
	String industryCategory;

	
	@ApiModelProperty(notes="创建人编号-可以转让,创建人与机构管理员有同样的权限",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date cdate;

	
	@ApiModelProperty(notes="创建人姓名-可以转让",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="联系电话",allowEmptyValue=true,example="",allowableValues="")
	String lphoneNo;

	
	@ApiModelProperty(notes="邮件",allowEmptyValue=true,example="",allowableValues="")
	String emaill;

	
	@ApiModelProperty(notes="当前流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String bizProcInstId;

	
	@ApiModelProperty(notes="当前流程状态",allowEmptyValue=true,example="",allowableValues="")
	String bizFlowState;

	
	@ApiModelProperty(notes="上级机构",allowEmptyValue=true,example="",allowableValues="")
	String pbranchId;

	
	@ApiModelProperty(notes="管理员编号（==机构编号，不允许修改，即机构主账户）",allowEmptyValue=true,example="",allowableValues="")
	String admUserid;

	
	@ApiModelProperty(notes="管理员名称（==机构名称+'管理员'，不允许修改）",allowEmptyValue=true,example="",allowableValues="")
	String admUsername;

	
	@ApiModelProperty(notes="联系人姓名",allowEmptyValue=true,example="",allowableValues="")
	String lusername;

	
	@ApiModelProperty(notes="联系人编号",allowEmptyValue=true,example="",allowableValues="")
	String luserid;

	
	@ApiModelProperty(notes="公司地址",allowEmptyValue=true,example="",allowableValues="")
	String address;

	
	@ApiModelProperty(notes="机构类别0-个人虚拟机构，1-实体机构，个人虚拟机构的话sys_branch表没有真正的机构数据",allowEmptyValue=true,example="",allowableValues="")
	String btype;

	
	@ApiModelProperty(notes="企业头像",allowEmptyValue=true,example="",allowableValues="")
	String imgUrl;

	
	@ApiModelProperty(notes="税号-统一信用识别号",allowEmptyValue=true,example="",allowableValues="")
	String bcode;

	
	@ApiModelProperty(notes="营业执照图片",allowEmptyValue=true,example="",allowableValues="")
	String blicense;

	
	@ApiModelProperty(notes="法人名称",allowEmptyValue=true,example="",allowableValues="")
	String legalPerson;

	
	@ApiModelProperty(notes="注册资本",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal regCapital;

	
	@ApiModelProperty(notes="企业简介",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段",allowEmptyValue=true,example="",allowableValues="")
	String validLvls;

	
	@ApiModelProperty(notes="参考类型",allowEmptyValue=true,example="",allowableValues="")
	String brelyType;

	
	@ApiModelProperty(notes="参考编号",allowEmptyValue=true,example="",allowableValues="")
	String brelyId;

	
	@ApiModelProperty(notes="参考子类型",allowEmptyValue=true,example="",allowableValues="")
	String brelyStype;

	
	@ApiModelProperty(notes="参考子编号",allowEmptyValue=true,example="",allowableValues="")
	String brelySid;

	
	@ApiModelProperty(notes="申领状态，0未申领，1-已申领",allowEmptyValue=true,example="",allowableValues="")
	String claimStatus;

	/**
	 *机构编号
	 **/
	public Branch(String id) {
		this.id = id;
	}
    
    /**
     * 管理端机构表（机构下面若干部门）
     **/
	public Branch() {
	}

}