package com.mdp.safe.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-24
 */
@Data
@TableName("sys_branch")
@Schema(description="管理端机构表（机构下面若干部门）")
public class Branch implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="机构编号,主键")
	String id;

	
	@Schema(description="机构名称")
	String branchName;

	
	@Schema(description="是否可用")
	String enabled;

	
	@Schema(description="行业分类")
	String industryCategory;

	
	@Schema(description="创建人编号-可以转让,创建人与机构管理员有同样的权限")
	String cuserid;

	
	@Schema(description="创建日期")
	Date cdate;

	
	@Schema(description="创建人姓名-可以转让")
	String cusername;

	
	@Schema(description="联系电话")
	String lphoneNo;

	
	@Schema(description="邮件")
	String emaill;

	
	@Schema(description="当前流程实例编号")
	String bizProcInstId;

	
	@Schema(description="当前流程状态")
	String bizFlowState;

	
	@Schema(description="上级机构")
	String pbranchId;

	
	@Schema(description="管理员编号（==机构编号，不允许修改，即机构主账户）")
	String admUserid;

	
	@Schema(description="管理员名称（==机构名称+'管理员'，不允许修改）")
	String admUsername;

	
	@Schema(description="联系人姓名")
	String lusername;

	
	@Schema(description="联系人编号")
	String luserid;

	
	@Schema(description="公司地址")
	String address;

	
	@Schema(description="机构类别0-个人虚拟机构，1-实体机构，个人虚拟机构的话sys_branch表没有真正的机构数据")
	String btype;

	
	@Schema(description="企业头像")
	String imgUrl;

	
	@Schema(description="税号-统一信用识别号")
	String bcode;

	
	@Schema(description="营业执照图片")
	String blicense;

	
	@Schema(description="法人名称")
	String legalPerson;

	
	@Schema(description="注册资本")
	BigDecimal regCapital;

	
	@Schema(description="企业简介")
	String remark;

	
	@Schema(description="人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段")
	String validLvls;

	
	@Schema(description="参考类型")
	String brelyType;

	
	@Schema(description="参考编号")
	String brelyId;

	
	@Schema(description="参考子类型")
	String brelyStype;

	
	@Schema(description="参考子编号")
	String brelySid;

	
	@Schema(description="申领状态，0未申领，1-已申领")
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