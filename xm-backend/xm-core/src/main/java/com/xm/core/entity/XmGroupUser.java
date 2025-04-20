package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author 唛盟开源 code-gen
 * @since 2024-7-6
 */
@Data
@TableName("xm_group_user")
@Schema(description="团队成员表")
public class XmGroupUser  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @Schema(description="团队编号,主键")
    String groupId;
    @TableIds
	
    @Schema(description="团队成员编号,主键")
    String userid;

	
	@Schema(description="加入时间")
	Date joinTime;

	
	@Schema(description="团队成员")
	String username;

	
	@Schema(description="离队时间")
	Date outTime;

	
	@Schema(description="当前状态0参与中1已退出团队")
	String status;

	
	@Schema(description="组员原归属机构编号")
	String obranchId;

	
	@Schema(description="是否私人加入0否1是")
	String isPri;

	
	@Schema(description="排序号--从1开始")
	Integer seqNo;

	
	@Schema(description="项目编号")
	String projectId;

	
	@Schema(description="产品编号")
	String productId;

	
	@Schema(description="0-项目小组，1-产品小组，2-团队")
	String pgClass;

	
	@Schema(description="原归属机构名称")
	String obranchName;

	/**
	 *团队编号,团队成员编号
	 **/
	public XmGroupUser(String groupId,String userid) {
		this.groupId = groupId;
		this.userid = userid;
	}
    
    /**
     * 团队成员表
     **/
	public XmGroupUser() {
	}

}