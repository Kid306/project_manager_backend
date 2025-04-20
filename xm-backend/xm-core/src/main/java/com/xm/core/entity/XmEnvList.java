package  com.xm.core.entity;

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
 * @since 2023-10-3
 */
@Data
@TableName("xm_env_list")
@ApiModel(description="项目环境清单")
public class XmEnvList  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="备注说明",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="内网ip地址",allowEmptyValue=true,example="",allowableValues="")
	String ipAddress;

	
	@ApiModelProperty(notes="内网访问端口",allowEmptyValue=true,example="",allowableValues="")
	String port;

	
	@ApiModelProperty(notes="归属机构",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="访问用户编号",allowEmptyValue=true,example="",allowableValues="")
	String accessUserid;

	
	@ApiModelProperty(notes="访问密码",allowEmptyValue=true,example="",allowableValues="")
	String accessPassword;

	
	@ApiModelProperty(notes="访问链接",allowEmptyValue=true,example="",allowableValues="")
	String accessUrl;

	
	@ApiModelProperty(notes="供应商",allowEmptyValue=true,example="",allowableValues="")
	String supplier;

	
	@ApiModelProperty(notes="外网ip地址",allowEmptyValue=true,example="",allowableValues="")
	String webIpAddress;

	
	@ApiModelProperty(notes="外网端口",allowEmptyValue=true,example="",allowableValues="")
	String webPort;

	
	@ApiModelProperty(notes="添加人员",allowEmptyValue=true,example="",allowableValues="")
	String createUserid;

	
	@ApiModelProperty(notes="添加人员姓名",allowEmptyValue=true,example="",allowableValues="")
	String createUsername;

	
	@ApiModelProperty(notes="添加时间",allowEmptyValue=true,example="",allowableValues="")
	Date createTime;

	
	@ApiModelProperty(notes="状态0不可用1已启用2已过期",allowEmptyValue=true,example="",allowableValues="")
	String envState;

	
	@ApiModelProperty(notes="有效日期开始",allowEmptyValue=true,example="",allowableValues="")
	Date startTime;

	
	@ApiModelProperty(notes="有效日期结束",allowEmptyValue=true,example="",allowableValues="")
	Date endTime;

	
	@ApiModelProperty(notes="费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal feeAmount;

	
	@ApiModelProperty(notes="计费规则",allowEmptyValue=true,example="",allowableValues="")
	String feeRule;

	
	@ApiModelProperty(notes="归属项目编号",allowEmptyValue=true,example="",allowableValues="")
	String projectId;

	
	@ApiModelProperty(notes="0-全部可看，1-同机构可看，2-同机构同项目可看，9-仅自己可看",allowEmptyValue=true,example="",allowableValues="")
	String readQx;

	
	@ApiModelProperty(notes="0-全部可写，1-同机构可写，2-同机构同项目可写，3-同项目上级可写，9-仅自己可修改",allowEmptyValue=true,example="",allowableValues="")
	String writeQx;

	
	@ApiModelProperty(notes="修改时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;

	
	@ApiModelProperty(notes="修改人编号",allowEmptyValue=true,example="",allowableValues="")
	String luserid;

	
	@ApiModelProperty(notes="修改人姓名",allowEmptyValue=true,example="",allowableValues="")
	String lusername;

	
	@ApiModelProperty(notes="名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	/**
	 *主键
	 **/
	public XmEnvList(String id) {
		this.id = id;
	}
    
    /**
     * 项目环境清单
     **/
	public XmEnvList() {
	}

}