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
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 <br> 
 * 实体 UserInterests所有属性名: <br>
 *	"userid","用户编号","ctime","创建时间","ltime","更新时间","isFree","是否付费获取权益","rtimeRule","续会时间叠加规则：1.有效期日期后叠加续会时间","rtimeType","续会时间类型：1.天数","rtime","下次续会时间yyyy-MM-dd型","itype","权益分类","shopId","商户编号","instId","当前流程实例编号","flowState","当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除","smaxAt","单个任务最大金额（任务型用户才有）0代表不限制","totalAt","累计接单金额（任务型用户才有）0代表不限制","mtype","适用会员类型（2商户型、1普通型、3任务型）","totalExp","累计经验值0代表不限制","smaxExp","单个任务最大经验值0代表不限制","bids","投标次数上限","sfeeRate","服务费率0-100之间","ctotalExp","累计完成工作量","currBids","当前月份投标次数（每月最后一天清零）","ctotalAt","当前累计完成金额","ctotalBids","累计投标总数","mfee","月均费用","maxUsers","最大人员数","currUsers","当前人员数","maxRtime","产品模块下次续费截止时间yyyy-MM-dd类型","mver","产品版本0免费版1企业版","srvTimes","累计服务次数","idBak","覆盖上一条的等级编号，即变成当前等级之前的等级编号","guardRtime","服务保障下次续费时间","guardName","服务保障名字","upRate","好评率","ilevel","等级高低，数字越高越高级","cmonthBids","当月投标数","cmonthAt","当月金额","cmonthExp","当月经验值","ctotalReceiveAt","当前收款总额","csixExp","六个月经验分","csixAt","六个月金额","csixBids","六个月投标次数";<br>
 * 当前主键(包括多主键):<br>
 *	userid;<br>
 */
 @Data
@TableName("sys_user_interests")
@ApiModel(description="个人权益关联表")
public class UserInterests  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="用户编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String userid;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;
	
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
	
	@ApiModelProperty(notes="累计完成工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal ctotalExp;
	
	@ApiModelProperty(notes="当前月份投标次数（每月最后一天清零）",allowEmptyValue=true,example="",allowableValues="")
	Integer currBids;
	
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
	
	@ApiModelProperty(notes="累计服务次数",allowEmptyValue=true,example="",allowableValues="")
	Integer srvTimes;
	
	@ApiModelProperty(notes="覆盖上一条的等级编号，即变成当前等级之前的等级编号",allowEmptyValue=true,example="",allowableValues="")
	String idBak;
	
	@ApiModelProperty(notes="服务保障下次续费时间",allowEmptyValue=true,example="",allowableValues="")
	Date guardRtime;
	
	@ApiModelProperty(notes="服务保障名字",allowEmptyValue=true,example="",allowableValues="")
	String guardName;
	
	@ApiModelProperty(notes="好评率",allowEmptyValue=true,example="",allowableValues="")
	Integer upRate;
	
	@ApiModelProperty(notes="等级高低，数字越高越高级",allowEmptyValue=true,example="",allowableValues="")
	Integer ilevel;
	
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

	/**
	 *用户编号
	 **/
	public UserInterests(String userid) {
		this.userid = userid;
	}
    
    /**
     * 个人权益关联表
     **/
	public UserInterests() {
	}

}