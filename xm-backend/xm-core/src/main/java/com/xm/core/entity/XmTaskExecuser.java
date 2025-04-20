package  com.xm.core.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mdp.core.dao.annotation.TableIds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_task_execuser")
@ApiModel(description="任务候选人、执行人表")
public class XmTaskExecuser  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
    @TableIds
	
    @ApiModelProperty(notes="任务id,主键",allowEmptyValue=true,example="",allowableValues="")
    String taskId;
    @TableIds
	
    @ApiModelProperty(notes="投标人用户编号-该用户编号不一定属于项目归属公司的内部账户,主键",allowEmptyValue=true,example="",allowableValues="")
    String bidUserid;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date createTime;

	
	@ApiModelProperty(notes="执行人id-投标者在项目归属公司的内部用户编号，对应sys_user.userid。投标人中标后，项目公司设立内部用户编号后回填，也就是中标后，项目公司应该给中标人设立内部用户编号，让其具有更多的权限",allowEmptyValue=true,example="",allowableValues="")
	String prjUserid;

	
	@ApiModelProperty(notes="加入时间",allowEmptyValue=true,example="",allowableValues="")
	Date startTime;

	
	@ApiModelProperty(notes="离开时间",allowEmptyValue=true,example="",allowableValues="")
	Date endTime;

	
	@ApiModelProperty(notes="执行人状态0候选排队中1执行任务中7放弃任务8黑名单",allowEmptyValue=true,example="",allowableValues="")
	String status;

	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remarks;

	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String createUserid;

	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String createUsername;

	
	@ApiModelProperty(notes="执行人姓名",allowEmptyValue=true,example="",allowableValues="")
	String prjUsername;

	
	@ApiModelProperty(notes="任务能力匹配分数100分，占比50%",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal matchScore;

	
	@ApiModelProperty(notes="报价天数，不包括周六日",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal quoteWeekday;

	
	@ApiModelProperty(notes="报价金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal quoteAmount;

	
	@ApiModelProperty(notes="报价时间",allowEmptyValue=true,example="",allowableValues="")
	Date quoteTime;

	
	@ApiModelProperty(notes="项目编号",allowEmptyValue=true,example="",allowableValues="")
	String projectId;

	
	@ApiModelProperty(notes="阶段计划编号",allowEmptyValue=true,example="",allowableValues="")
	String phaseId;

	
	@ApiModelProperty(notes="技能说明",allowEmptyValue=true,example="",allowableValues="")
	String skillRemark;

	
	@ApiModelProperty(notes="报价工作量单位人时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal quoteWorkload;

	
	@ApiModelProperty(notes="报价-开始工作日期",allowEmptyValue=true,example="",allowableValues="")
	Date quoteStartTime;

	
	@ApiModelProperty(notes="报价-结束工作日期",allowEmptyValue=true,example="",allowableValues="")
	Date quoteEndTime;

	
	@ApiModelProperty(notes="项目所属机构",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="阶段计划名称",allowEmptyValue=true,example="",allowableValues="")
	String phaseName;

	
	@ApiModelProperty(notes="任务名称",allowEmptyValue=true,example="",allowableValues="")
	String taskName;

	
	@ApiModelProperty(notes="推荐人编号",allowEmptyValue=true,example="",allowableValues="")
	String distUserid;

	
	@ApiModelProperty(notes="推荐人姓名",allowEmptyValue=true,example="",allowableValues="")
	String distUsername;

	
	@ApiModelProperty(notes="分享码",allowEmptyValue=true,example="",allowableValues="")
	String shareKey;

	
	@ApiModelProperty(notes="服务费率",allowEmptyValue=true,example="",allowableValues="")
	Integer sfeeRate;

	
	@ApiModelProperty(notes="众包服务费",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal sfee;

	
	@ApiModelProperty(notes="省编号",allowEmptyValue=true,example="",allowableValues="")
	String provinceId;

	
	@ApiModelProperty(notes="省名称",allowEmptyValue=true,example="",allowableValues="")
	String provinceName;

	
	@ApiModelProperty(notes="城市编号",allowEmptyValue=true,example="",allowableValues="")
	String cityId;

	
	@ApiModelProperty(notes="城市名称",allowEmptyValue=true,example="",allowableValues="")
	String cityName;

	
	@ApiModelProperty(notes="区县编号",allowEmptyValue=true,example="",allowableValues="")
	String areaId;

	
	@ApiModelProperty(notes="区县名称",allowEmptyValue=true,example="",allowableValues="")
	String areaName;

	
	@ApiModelProperty(notes="能力等级编号",allowEmptyValue=true,example="",allowableValues="")
	String gradeId;

	
	@ApiModelProperty(notes="保障等级编号",allowEmptyValue=true,example="",allowableValues="")
	String guardId;

	
	@ApiModelProperty(notes="会员等级编号",allowEmptyValue=true,example="",allowableValues="")
	String ilvlId;

	
	@ApiModelProperty(notes="信用等级编号",allowEmptyValue=true,example="",allowableValues="")
	String creditId;

	
	@ApiModelProperty(notes="投标总数",allowEmptyValue=true,example="",allowableValues="")
	Integer ctotalBids;

	
	@ApiModelProperty(notes="服务总次数",allowEmptyValue=true,example="",allowableValues="")
	Integer srvTimes;

	
	@ApiModelProperty(notes="本月获得经验值",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal cmonthExp;

	
	@ApiModelProperty(notes="本月投标数",allowEmptyValue=true,example="",allowableValues="")
	Integer cmonthBids;

	
	@ApiModelProperty(notes="是否开启投标直通车",allowEmptyValue=true,example="",allowableValues="")
	String bidDirect;

	
	@ApiModelProperty(notes="技能编号列表",allowEmptyValue=true,example="",allowableValues="")
	String skillIds;

	
	@ApiModelProperty(notes="技能名称列表",allowEmptyValue=true,example="",allowableValues="")
	String skillNames;

	
	@ApiModelProperty(notes="好评率",allowEmptyValue=true,example="",allowableValues="")
	Integer upRate;

	
	@ApiModelProperty(notes="雇主打分100分，占比50%,默认60分",allowEmptyValue=true,example="",allowableValues="")
	Integer adjustScore;

	
	@ApiModelProperty(notes="总得分(100分)=match_score*50%+adjust_score*50",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal finalScore;

	
	@ApiModelProperty(notes="打分说明",allowEmptyValue=true,example="",allowableValues="")
	String adjustRemark;

	
	@ApiModelProperty(notes="最近6个月投标次数",allowEmptyValue=true,example="",allowableValues="")
	Integer csixBids;

	
	@ApiModelProperty(notes="最近6个月经验值总额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal csixExp;

	
	@ApiModelProperty(notes="最近6个月收入总额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal csixAt;

	
	@ApiModelProperty(notes="投标人名称",allowEmptyValue=true,example="",allowableValues="")
	String bidUsername;

	
	@ApiModelProperty(notes="投标人归属公司-根据cpa_userid带出,该机构号不一定等同于项目归属公司编号。如果投标人属于项目归属公司，则等于项目归属公司编号，否则不同",allowEmptyValue=true,example="",allowableValues="")
	String bidBranchId;

	/**
	 *任务id,投标人用户编号-该用户编号不一定属于项目归属公司的内部账户
	 **/
	public XmTaskExecuser(String taskId,String bidUserid) {
		this.taskId = taskId;
		this.bidUserid = bidUserid;
	}
    
    /**
     * 任务候选人、执行人表
     **/
	public XmTaskExecuser() {
	}

}