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
 * @since 2025-4-10
 */
@Data
@TableName("xm_risk_handle")
@Schema(description="风险处理进展情况")
public class XmRiskHandle  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="主键,主键")
	String id;

	
	@Schema(description="处理人编号")
	String handlerUserid;

	
	@Schema(description="处理人")
	String handlerUsername;

	
	@Schema(description="解决方案:")
	String handleSolution;

	
	@Schema(description="回执信息")
	String receiptMessage;

	
	@Schema(description="回执时间")
	Date receiptTime;

	
	@Schema(description="=bugStatus")
	String handleStatus;

	
	@Schema(description="问题编号")
	String riskId;

	
	@Schema(description="最后更新日期")
	Date lastUpdateTime;

	
	@Schema(description="创建时间")
	Date createTime;

	
	@Schema(description="链接地址列表逗号分隔")
	String urls;

	
	@Schema(description="指派给谁")
	String targetUserid;

	
	@Schema(description="指派给谁")
	String targetUsername;

	
	@Schema(description="操作人编号")
	String operUserid;

	
	@Schema(description="操作人姓名")
	String operUsername;

	
	@Schema(description="原状态")
	String oldStatus;

	
	@Schema(description="通知谁看，逗号分隔多个")
	String notifyUserids;

	
	@Schema(description="通知谁看的名称，逗号分隔多个")
	String notifyUsernames;

	/**
	 *主键
	 **/
	public XmRiskHandle(String id) {
		this.id = id;
	}
    
    /**
     * 风险处理进展情况
     **/
	public XmRiskHandle() {
	}

}