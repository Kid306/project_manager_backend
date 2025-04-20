package  com.mdp.plat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2024-5-15
 */
@Data
@TableName("plat_bank_account")
@ApiModel(description="平台收付款账户")
public class BankAccount  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="银行卡账户编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String cardAccountId;
    @TableIds
	
    @ApiModelProperty(notes="平台编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String platformId;

	
	@ApiModelProperty(notes="银行名称",allowEmptyValue=true,example="",allowableValues="")
	String bankName;

	
	@ApiModelProperty(notes="银行机构码",allowEmptyValue=true,example="",allowableValues="")
	String bankCode;

	
	@ApiModelProperty(notes="银行卡账户名称",allowEmptyValue=true,example="",allowableValues="")
	String cardAccountName;

	
	@ApiModelProperty(notes="银行分支机构名称",allowEmptyValue=true,example="",allowableValues="")
	String subBankName;

	
	@ApiModelProperty(notes="分支机构编码",allowEmptyValue=true,example="",allowableValues="")
	String subBankCode;

	
	@ApiModelProperty(notes="新增时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;

	
	@ApiModelProperty(notes="操作用户编号",allowEmptyValue=true,example="",allowableValues="")
	String opUserid;

	
	@ApiModelProperty(notes="操作用户名称",allowEmptyValue=true,example="",allowableValues="")
	String opUsername;

	
	@ApiModelProperty(notes="账户状态0无效1有效",allowEmptyValue=true,example="",allowableValues="")
	String status;

	
	@ApiModelProperty(notes="账户性质0-平台收款",allowEmptyValue=true,example="",allowableValues="")
	String type;

	
	@ApiModelProperty(notes="银行logo图片",allowEmptyValue=true,example="",allowableValues="")
	String bankImgUrl;

	/**
	 *银行卡账户编号,平台编号
	 **/
	public BankAccount(String cardAccountId,String platformId) {
		this.cardAccountId = cardAccountId;
		this.platformId = platformId;
	}
    
    /**
     * 平台收付款账户
     **/
	public BankAccount() {
	}

}