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
 * @since 2024-9-6
 */
@Data
@TableName("xm_product")
@Schema(description="产品表")
public class XmProduct  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@Schema(description="产品编号,主键")
	String id;

	
	@Schema(description="产品名称")
	String productName;

	
	@Schema(description="机构号")
	String branchId;

	
	@Schema(description="备注")
	String remark;

	
	@Schema(description="版本号")
	String version;

	
	@Schema(description="产品经理编号")
	String pmUserid;

	
	@Schema(description="产品经理名称")
	String pmUsername;

	
	@Schema(description="创建日期")
	Date ctime;

	
	@Schema(description="归属部门")
	String deptid;

	
	@Schema(description="产品阶段:0未开始,1研发中,2已完成,3已关闭")
	String pstatus;

	
	@Schema(description="开始日期")
	Date startTime;

	
	@Schema(description="结束日期")
	Date endTime;

	
	@Schema(description="主管部门名称")
	String deptName;

	
	@Schema(description="主管领导编号")
	String admUserid;

	
	@Schema(description="主管领导名称")
	String admUsername;

	
	@Schema(description="副经理编号")
	String assUserid;

	
	@Schema(description="副经理名称")
	String assUsername;

	
	@Schema(description="当前流程实例编号")
	String bizProcInstId;

	
	@Schema(description="当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除")
	String bizFlowState;

	
	@Schema(description="是否为模板")
	String isTpl;

	
	@Schema(description="基线编号")
	String baselineId;

	
	@Schema(description="基线时间")
	Date baseTime;

	
	@Schema(description="产品编码")
	String code;

	
	@Schema(description="产品预计总工作量，应该大于一级需求总预算工作量")
	BigDecimal pbudgetWorkload;

	
	@Schema(description="产品预计总金额，应该大于一级需求总预算金额")
	BigDecimal pbudgetAmount;

	
	@Schema(description="从需求汇总来的总预算工作量")
	BigDecimal pmenuBudgetWorkload;

	
	@Schema(description="从需求汇总的总预算金额")
	BigDecimal pmenuBudgetAmount;

	
	@Schema(description="是否进行预算控制，计划中一级计划总预算不能大于项目预算")
	String budgetCtrl;

	
	@Schema(description="是否进行计划明细预算控制，计划中下级预算不能大于上级预算")
	String phaseBudgetCtrl;

	
	@Schema(description="计划是否进行实际金额控制，实际金额不能大于预算金额")
	String phaseActCtrl;

	
	@Schema(description="是否锁定不允许编号0否1是")
	String locked;

	
	@Schema(description="是否已删除0否一是")
	String del;

	
	@Schema(description="最后更新时间")
	Date ltime;

	
	@Schema(description="权限码0,1,2,3,4,5,67,8,9，逗号分割共10位,不定长，暂时只启用前8个位第0位代表团队建立及成员管理及crud权限：")
	String qxCode;

	
	@Schema(description="是否对外公开0-完全不可见，1-仅本司人员可见，2-关联人员可见（众包-外包-招投标）,3-本部门上级及下属部门可见,4-仅本部及上级可见，5-仅本部及下级可见,6-仅本部人员可见")
	String showOut;

	
	@Schema(description="部门编号全路径编号")
	String deptidPath;

	
	@Schema(description="项目创建人编号")
	String createUserid;

	
	@Schema(description="项目创建人")
	String createUsername;

	
	@Schema(description="计划预估工时，=由史诗、特性汇总而来，自顶向下逐层分解")
	BigDecimal pinitWorkload;

	
	@Schema(description="计划进度-0-100之间，=实际工时/计划预估工时*100")
	Integer pinitRate;

	@Schema(description = "标签，逗号分隔多个")
	String tagIds;


	@Schema(description = "是否全网公开")
	String tplType;

	/**
	 *产品编号
	 **/
	public XmProduct(String id) {
		this.id = id;
	}
    
    /**
     * 产品表
     **/
	public XmProduct() {
	}
}