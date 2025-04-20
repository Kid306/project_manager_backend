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
@TableName("xm_risk")
@Schema(description="项目风险管理")
public class XmRisk  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="风险编号,主键")
	String id;

	
	@Schema(description="风险名称")
	String name;

	
	@Schema(description="风险类型,字典rtype,1-市场，2-技术，3-质量，4-成本，5-进度，6-人事")
	String rtype;

	
	@Schema(description="发生概率，字典odds,1-很高，2-高，3-中，4-低，5-很低")
	String odds;

	
	@Schema(description="影响程度,字典impact,1-很高，2-高，3-中，4-低，5-很低")
	String impact;

	
	@Schema(description="风险状态,字典rstatus,1-识别，2-评估，3-监控，4-应对，5-解除")
	String rstatus;

	
	@Schema(description="风险等级，字典rlvl,1-立即行动，2-立即关注，3-定期关注，4-无需处理")
	String rlvl;

	
	@Schema(description="风险描述")
	String remark;

	
	@Schema(description="应对策略，字典rsgy,1-上报，2-规避，3-转移，4-减轻，5-接受")
	String rsgy;

	
	@Schema(description="应对措施")
	String rway;

	
	@Schema(description="责任人编号")
	String ruserid;

	
	@Schema(description="责任人姓名")
	String rusername;

	
	@Schema(description="建立时间")
	Date ctime;

	
	@Schema(description="解除时间")
	Date closeTime;

	
	@Schema(description="部门编号")
	String deptid;

	
	@Schema(description="企业编号")
	String branchId;

	
	@Schema(description="项目编号")
	String projectId;

	
	@Schema(description="提出人编号")
	String cuserid;

	
	@Schema(description="提出人姓名")
	String cusername;

	/**
	 *风险编号
	 **/
	public XmRisk(String id) {
		this.id = id;
	}
    
    /**
     * 项目风险管理
     **/
	public XmRisk() {
	}

}