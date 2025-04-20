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
 * @since 2024-9-9
 */
@Data
@TableName("xm_menu")
@Schema(description="用户故事")
public class XmMenu  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="功能编号,主键")
	String menuId;

	
	@Schema(description="开始时间")
	Date startTime;

	
	@Schema(description="功能名称")
	String menuName;

	
	@Schema(description="上级功能")
	String pmenuId;

	
	@Schema(description="归属产品编号")
	String productId;

	
	@Schema(description="备注")
	String remark;

	
	@Schema(description="状态0初始1待评审2待设计3待开发4待sit测试5待uat测试6已测试待上线7已上线8已下线9已删除")
	String status;

	
	@Schema(description="是否已上线")
	String online;

	
	@Schema(description="需求链接")
	String demandUrl;

	
	@Schema(description="代码链接")
	String codeUrl;

	
	@Schema(description="设计链接")
	String designUrl;

	
	@Schema(description="文档链接")
	String docUrl;

	
	@Schema(description="帮助文档链接")
	String helpUrl;

	
	@Schema(description="操作手册链接")
	String operDocUrl;

	
	@Schema(description="排序序号")
	String seqNo;

	
	@Schema(description="故事管理员编号")
	String mmUserid;

	
	@Schema(description="故事管理员姓名")
	String mmUsername;

	
	@Schema(description="创建时间")
	Date ctime;

	
	@Schema(description="节点类型0-叶子节点，1非叶子节点")
	String ntype;

	
	@Schema(description="开始版本")
	String sinceVersion;

	
	@Schema(description="儿子节点个数")
	Integer childrenCnt;

	
	@Schema(description="更新时间")
	Date ltime;

	
	@Schema(description="标签编号,逗号分割")
	String tagIds;

	
	@Schema(description="标签名称,逗号分割")
	String tagNames;

	
	@Schema(description="父级id逗号分割，最后一个为本节点节点编号,以,号结尾")
	String pidPaths;

	
	@Schema(description="层级0-顶级，1-一级，2-二级，3-三级，4-四级。总共5级")
	Integer lvl;

	
	@Schema(description="是否为模板")
	String isTpl;

	
	@Schema(description="前置需求编号，逗号分隔多个")
	String phaseId;

	
	@Schema(description="迭代编号")
	String iterationId;

	
	@Schema(description="需求来源1部门意见、2用户反馈、3技术反馈、4运营反馈、5团队讨论、6老板需求、7自身需求")
	String source;

	
	@Schema(description="提出人编号")
	String proposerId;

	
	@Schema(description="提出人姓名")
	String proposerName;

	
	@Schema(description="需求层次0-基础需求,1-增值需求,2-扩展需求")
	String dlvl;

	
	@Schema(description="需求类型;0-新增功能;1-功能改进;2-bug修复;3-用户体验;4-UI优化;5-内部需求;6-删除需求;7-接口需求;")
	String dtype;

	
	@Schema(description="优先级;0-紧急重要；1-紧急不重要；2-不紧急重要；3-不紧急不重要")
	String priority;

	
	@Schema(description="需求分类1-史诗，2-特性，3-用户故事，4-任务，5-缺陷")
	String dclass;

	
	@Schema(description="迭代名称")
	String iterationName;

	
	@Schema(description="结束时间")
	Date endTime;

	
	@Schema(description="功能菜单编号-故事才有")
	String funcId;

	
	@Schema(description="功能菜单名称-故事才有")
	String funcName;

	
	@Schema(description="评论数")
	Integer comments;

	
	@Schema(description="点赞数")
	Integer ups;

	
	@Schema(description="阅读数")
	Integer readNum;

	
	@Schema(description="产品归属企业")
	String pbranchId;

	
	@Schema(description="儿子节点树-不包括孙子，不包括故事")
	Integer subEfCnt;

	
	@Schema(description="儿子故事树-不包括孙子，仅仅计算故事数量")
	Integer subStoryCnt;

	
	@Schema(description="实际完成工时统计")
	String calcType;

	/**
	 *功能编号
	 **/
	public XmMenu(String menuId) {
		this.menuId = menuId;
	}
    
    /**
     * 用户故事
     **/
	public XmMenu() {
	}

}