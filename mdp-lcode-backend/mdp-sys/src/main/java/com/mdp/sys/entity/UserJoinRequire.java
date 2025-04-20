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
 * @since 2024-5-13
 */
@Data
@TableName("sys_user_join_require")
@ApiModel(description="企业入驻审核流程")
public class UserJoinRequire  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="审批状态同意1不同意0",allowEmptyValue=true,example="",allowableValues="")
	String agree;

	
	@ApiModelProperty(notes="用户编号",allowEmptyValue=true,example="",allowableValues="")
	String joinUserid;

	
	@ApiModelProperty(notes="用户名称",allowEmptyValue=true,example="",allowableValues="")
	String joinUsername;

	
	@ApiModelProperty(notes="要加入的机构号",allowEmptyValue=true,example="",allowableValues="")
	String joinBranchId;

	
	@ApiModelProperty(notes="要加入的部门号",allowEmptyValue=true,example="",allowableValues="")
	String joinDeptid;

	
	@ApiModelProperty(notes="申请加入时间",allowEmptyValue=true,example="",allowableValues="")
	Date createDate;

	
	@ApiModelProperty(notes="同意时间",allowEmptyValue=true,example="",allowableValues="")
	Date agreeDate;

	
	@ApiModelProperty(notes="状态0初始1同意2拒绝",allowEmptyValue=true,example="",allowableValues="")
	String joinStatus;

	
	@ApiModelProperty(notes="加入理由",allowEmptyValue=true,example="",allowableValues="")
	String joinReason;

	
	@ApiModelProperty(notes="当前流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String bizProcInstId;

	
	@ApiModelProperty(notes="当前流程状态",allowEmptyValue=true,example="",allowableValues="")
	String bizFlowState;

	
	@ApiModelProperty(notes="联系电话",allowEmptyValue=true,example="",allowableValues="")
	String joinUserPhoneno;

	/**
	 *主键
	 **/
	public UserJoinRequire(String id) {
		this.id = id;
	}
    
    /**
     * 企业入驻审核流程
     **/
	public UserJoinRequire() {
	}

}