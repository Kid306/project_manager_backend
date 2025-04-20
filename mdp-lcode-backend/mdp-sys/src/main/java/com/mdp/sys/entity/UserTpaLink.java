package  com.mdp.sys.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2023-9-22
 */
@Data
@TableName("sys_user_tpa_link")
@ApiModel(description="sys_user_tpa_link")
public class UserTpaLink  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="用户编号,主键",allowEmptyValue=true,example="",allowableValues="")
    String sysUserid;
    @TableIds
	
    @ApiModelProperty(notes="第三方应用的sys_user_tpa.id,主键",allowEmptyValue=true,example="",allowableValues="")
    String tpaId;

	
	@ApiModelProperty(notes="绑定时间",allowEmptyValue=true,example="",allowableValues="")
	Date bindTime;

	
	@ApiModelProperty(notes="绑定的机构号=sys_user.branch_id",allowEmptyValue=true,example="",allowableValues="")
	String bindBranchId;

	/**
	 *用户编号,第三方应用的sys_user_tpa.id
	 **/
	public UserTpaLink(String sysUserid,String tpaId) {
		this.sysUserid = sysUserid;
		this.tpaId = tpaId;
	}
    
    /**
     * sys_user_tpa_link
     **/
	public UserTpaLink() {
	}

}