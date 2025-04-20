package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.mdp.core.dao.annotation.TableIds;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @author 唛盟开源 code-gen
 * @since 2025-4-10
 */
@Data
@TableName("xm_comment")
@Schema(description="评论表")
public class XmComment  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="主键,主键")
	String id;

	
	@Schema(description="评论人")
	String userid;

	
	@Schema(description="评论人姓名")
	String username;

	
	@Schema(description="星级")
	String star;

	
	@Schema(description="时间")
	Date cdate;

	
	@Schema(description="主体业务编号，需求编号、任务编号等主体业务编号")
	String bizId;

	
	@Schema(description="上级评论")
	String pid;

	
	@Schema(description="点赞数量")
	BigDecimal ups;

	
	@Schema(description="是否显示0否1是")
	String isShow;

	
	@Schema(description="回复用户编号")
	String toUserid;

	
	@Schema(description="回复用户名")
	String toUsername;

	
	@Schema(description="层级0,1,2,3,4")
	String lvl;

	
	@Schema(description="评论内容")
	String context;

	
	@Schema(description="机构编号")
	String branchId;

	
	@Schema(description="ip地址")
	String ip;

	
	@Schema(description="城市编号")
	String cityId;

	
	@Schema(description="城市名称")
	String cityName;

	
	@Schema(description="状态0未审核，1已审核，3审核不通过")
	String status;

	
	@Schema(description="儿子节点数量")
	Integer childNums;

	
	@Schema(description="上级业务编号，主体业务编号的上级编号")
	String pbizId;

	
	@Schema(description="评论对象类型，比如产品、团队、风险、需求等，字典objType")
	String objType;

	
	@Schema(description="通知谁看")
	String notifyUserids;

	/**
	 *主键
	 **/
	public XmComment(String id) {
		this.id = id;
	}
    
    /**
     * 评论表
     **/
	public XmComment() {
	}

}