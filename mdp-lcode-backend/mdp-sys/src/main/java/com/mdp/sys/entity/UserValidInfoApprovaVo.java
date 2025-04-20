package com.mdp.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserValidInfo所有属性名: <br>
 *	"userid","用户编号，如果是机构就是机构编号对应的用户编号","idFront","身份证正面","idBack","身份证反面","idHold","手持身份证","bizLicense","营业执照","oths","其它逗号分割多个","idNo","身份证号","idEtime","身份证到期日期","uscc","统一信用代码号税号营业执照号等","actBname","机构实名名称","actUname","实名用户名称或者法人名称","ctime","新增时间","ltime","修改时间","flowState","审核状态0-初始，1-审核中，2-通过，3-拒绝","validLvls","人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段","isOrg","是否为机构，0-否，1-是";<br>
 * 当前主键(包括多主键):<br>
 *	userid;<br>
 */
 @Data
@ApiModel(description="用户实名验证资料库")
public class UserValidInfoApprovaVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes="用户编号，如果是机构就是机构编号对应的用户编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String userid;

	@ApiModelProperty(notes="审核状态0-初始，1-审核中，2-通过，3-拒绝",allowEmptyValue=true,example="",allowableValues="")
	String flowState;

	@ApiModelProperty(notes="审核原因说明",allowEmptyValue=true,example="",allowableValues="")
	String validRemarks;

}