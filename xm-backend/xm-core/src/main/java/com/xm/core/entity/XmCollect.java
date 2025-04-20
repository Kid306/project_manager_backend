package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.mdp.core.dao.annotation.TableIds;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * @author 唛盟开源 code-gen
 * @since 2025-3-24
 */
@Data
@TableName("xm_collect")
@Schema(description="")
public class XmCollect  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="项目集编号,主键")
	String id;

	
	@Schema(description="项目集名称")
	String name;

	
	@Schema(description="创建人")
	String cuserid;

	
	@Schema(description="创建人姓名")
	String cusername;

	
	@Schema(description="创建时间")
	Date ctime;

	
	@Schema(description="归属机构号")
	String branchId;

	
	@Schema(description="部门编号")
	String deptid;

	
	@Schema(description="管理员编号")
	String admUserid;

	
	@Schema(description="管理员姓名")
	String admUsername;

	
	@Schema(description="开放程度，字典xm_showOut，0-完全不可见，1-仅本司人员可见，2-关联人员可见（众包-外包-招投标）,3-本部门上级及下属部门可见,4-仅本部及上级可见，5-仅本部及下级可见,6-仅本部人员可见")
	String openLvl;

	
	@Schema(description="部门编号全路径编号")
	String deptidPath;

	
	@Schema(description="状态")
	String status;

	
	@Schema(description="图标")
	String icon;

	@Schema(description = "标签，逗号分隔多个")
	String tagIds;
	/**
	 *项目集编号
	 **/
	public XmCollect(String id) {
		this.id = id;
	}
    
    /**
     * 
     **/
	public XmCollect() {
	}

}