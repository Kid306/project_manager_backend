package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.mdp.core.dao.annotation.TableIds;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 唛盟开源 code-gen
 * @since 2025-3-24
 */
@Data
@TableName("xm_collect_link")
@Schema(description="")
public class XmCollectLink  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableIds(type = IdType.ASSIGN_ID)
	@Schema(description="项目集编号,主键")
	String collectId;

	@TableIds(type = IdType.ASSIGN_ID)
	@Schema(description="项目编号")
	String projectId;

	/**
	 *项目集编号
	 **/
	public XmCollectLink(String collectId) {
		this.collectId = collectId;
	}
    
    /**
     * 
     **/
	public XmCollectLink() {
	}

}