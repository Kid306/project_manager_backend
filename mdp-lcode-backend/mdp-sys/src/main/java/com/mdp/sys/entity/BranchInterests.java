package  com.mdp.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author code-gen
 * @since 2024-5-11
 */
@Data
@TableName("sys_branch_interests")
@ApiModel(description="机构权益关联表")
public class BranchInterests  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="公司ID,主键",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="等级ID",allowEmptyValue=true,example="",allowableValues="")
	String ilvlId;

	
	@ApiModelProperty(notes="等级名称",allowEmptyValue=true,example="",allowableValues="")
	String ilvlName;

	
	@ApiModelProperty(notes="等级描述",allowEmptyValue=true,example="",allowableValues="")
	String idesc;

	
	@ApiModelProperty(notes="1-粉丝,2-",allowEmptyValue=true,example="",allowableValues="")
	Integer ilevel;

	
	@ApiModelProperty(notes="权益，折扣",allowEmptyValue=true,example="",allowableValues="")
	Integer discount;

	
	@ApiModelProperty(notes="等级状态",allowEmptyValue=true,example="",allowableValues="")
	String istatus;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;

	
	@ApiModelProperty(notes="等级图标url",allowEmptyValue=true,example="",allowableValues="")
	String picUrl;

	
	@ApiModelProperty(notes="是否付费获取权益",allowEmptyValue=true,example="",allowableValues="")
	String isFree;

	
	@ApiModelProperty(notes="续会时间叠加规则：1.有效期日期后叠加续会时间",allowEmptyValue=true,example="",allowableValues="")
	String rtimeRule;

	
	@ApiModelProperty(notes="续会时间类型：1.天数",allowEmptyValue=true,example="",allowableValues="")
	String rtimeType;

	
	@ApiModelProperty(notes="下次续会时间yyyy-MM-dd型",allowEmptyValue=true,example="",allowableValues="")
	String rtime;

	
	@ApiModelProperty(notes="权益分类",allowEmptyValue=true,example="",allowableValues="")
	String itype;

	
	@ApiModelProperty(notes="商户编号",allowEmptyValue=true,example="",allowableValues="")
	String shopId;

	
	@ApiModelProperty(notes="当前流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String instId;

	
	@ApiModelProperty(notes="当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除",allowEmptyValue=true,example="",allowableValues="")
	String flowState;

	
	@ApiModelProperty(notes="单个任务最大金额（任务型用户才有）0代表不限制",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal smaxAt;

	
	@ApiModelProperty(notes="累计接单金额（任务型用户才有）0代表不限制",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal totalAt;

	
	@ApiModelProperty(notes="适用会员类型（2商户型、1普通型、3任务型）",allowEmptyValue=true,example="",allowableValues="")
	String mtype;

	
	@ApiModelProperty(notes="累计经验值0代表不限制",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal totalExp;

	
	@ApiModelProperty(notes="单个任务最大经验值0代表不限制",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal smaxExp;

	
	@ApiModelProperty(notes="投标次数上限",allowEmptyValue=true,example="",allowableValues="")
	Integer bids;

	
	@ApiModelProperty(notes="服务费率0-100之间",allowEmptyValue=true,example="",allowableValues="")
	Integer sfeeRate;

	
	@ApiModelProperty(notes="覆盖上一条的等级编号，即变成当前等级之前的等级编号",allowEmptyValue=true,example="",allowableValues="")
	String idBak;

	
	@ApiModelProperty(notes="累计完成工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal ctotalExp;

	
	@ApiModelProperty(notes="当前累计完成金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal ctotalAt;

	
	@ApiModelProperty(notes="累计投标总数",allowEmptyValue=true,example="",allowableValues="")
	Integer ctotalBids;

	
	@ApiModelProperty(notes="月均费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal mfee;

	
	@ApiModelProperty(notes="最大人员数",allowEmptyValue=true,example="",allowableValues="")
	Integer maxUsers;

	
	@ApiModelProperty(notes="当前人员数",allowEmptyValue=true,example="",allowableValues="")
	Integer currUsers;

	
	@ApiModelProperty(notes="产品模块下次续费截止时间yyyy-MM-dd类型",allowEmptyValue=true,example="",allowableValues="")
	String maxRtime;

	
	@ApiModelProperty(notes="产品版本0免费版1企业版",allowEmptyValue=true,example="",allowableValues="")
	String mver;

	
	@ApiModelProperty(notes="诚信保障金等级金银铜，1-金，2-银，3-铜,0-初始，过期后归0",allowEmptyValue=true,example="",allowableValues="")
	String guardId;

	
	@ApiModelProperty(notes="累计服务次数",allowEmptyValue=true,example="",allowableValues="")
	Integer srvTimes;

	
	@ApiModelProperty(notes="服务保障名称",allowEmptyValue=true,example="",allowableValues="")
	String guardName;

	
	@ApiModelProperty(notes="服务保障下次续费时间",allowEmptyValue=true,example="",allowableValues="")
	Date guardRtime;

	
	@ApiModelProperty(notes="当月投标数",allowEmptyValue=true,example="",allowableValues="")
	Integer cmonthBids;

	
	@ApiModelProperty(notes="当月金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal cmonthAt;

	
	@ApiModelProperty(notes="当月经验值",allowEmptyValue=true,example="",allowableValues="")
	Integer cmonthExp;

	
	@ApiModelProperty(notes="当前收款总额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal ctotalReceiveAt;

	
	@ApiModelProperty(notes="六个月经验分",allowEmptyValue=true,example="",allowableValues="")
	Integer csixExp;

	
	@ApiModelProperty(notes="六个月金额",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal csixAt;

	
	@ApiModelProperty(notes="六个月投标次数",allowEmptyValue=true,example="",allowableValues="")
	Integer csixBids;

	
	@ApiModelProperty(notes="清除时间",allowEmptyValue=true,example="",allowableValues="")
	Date clearTime;

	/**
	 *公司ID
	 **/
	public BranchInterests(String branchId) {
		this.branchId = branchId;
	}
    
    /**
     * 机构权益关联表
     **/
	public BranchInterests() {
	}

}