package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author 唛盟开源 code-gen
 * @since 2024-7-6
 */
@Data
@TableName("xm_group")
@Schema(description="团队表")
public class XmGroup  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="主键,主键")
	String id;

	
	@Schema(description="团队名称")
	String groupName;

	
	@Schema(description="项目编号-属于产品线则可为空")
	String projectId;

	
	@Schema(description="项目团队类型编号")
	String pgTypeId;

	
	@Schema(description="团队类型名称")
	String pgTypeName;

	
	@Schema(description="团队负责人")
	String leaderUserid;

	
	@Schema(description="负责人姓名")
	String leaderUsername;

	
	@Schema(description="创建时间")
	Date ctime;

	
	@Schema(description="更新时间")
	Date ltime;

	
	@Schema(description="产品编号，属于项目组的团队则可为空")
	String productId;

	
	@Schema(description="归属机构编号")
	String branchId;

	
	@Schema(description="团队类别0-项目小组，1-产品小组，2-团队；团队下挂项目团队或者产品团队。产品团队下只能挂产品团队，项目团队下只能挂项目团队")
	String pgClass;

	
	@Schema(description="上级团队编号")
	String pgroupId;

	
	@Schema(description="级别0级1级2级3级4级")
	Integer lvl;

	
	@Schema(description="上级编号路径逗号分割,0,开始，本组编号+逗号结束")
	String pidPaths;

	
	@Schema(description="是否为模板")
	String isTpl;

	
	@Schema(description="副组长编号")
	String assUserid;

	
	@Schema(description="副组长姓名")
	String assUsername;

	
	@Schema(description="下级团队数量")
	Integer childrenCnt;

	
	@Schema(description="组员数量")
	Integer userCnt;

	
	@Schema(description="权限码")
	String qxCode;

	
	@Schema(description="是否计算工作量0否1是")
	String calcWorkload;

	
	@Schema(description="节点类型0管理团队、1执行团队")
	String ntype;

	
	@Schema(description="协作公司编号")
	String crowBranchId;

	
	@Schema(description="协作公司名称")
	String crowBranchName;

	
	@Schema(description="是否众包团队")
	String isCrow;

	/**
	 *主键
	 **/
	public XmGroup(String id) {
		this.id = id;
	}
    
    /**
     * 团队表
     **/
	public XmGroup() {
	}

}