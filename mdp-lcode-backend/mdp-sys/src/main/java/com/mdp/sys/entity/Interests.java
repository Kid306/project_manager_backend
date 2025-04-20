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
 * 实体 Interests所有属性名: <br>
 *	"ilvlId","等级ID","ilvlName","等级名称","idesc","等级描述","branchId","公司ID","ilevel","1-粉丝,2-","istatus","等级状态","ctime","创建时间","ltime","更新时间","picUrl","等级图标url","isFree","是否付费获取权益","rtimeRule","续会时间叠加规则：1.有效期日期后叠加续会时间","rtimeType","续会时间类型：1.天数","rtime","续会时间yyyy-MM-dd型","itype","权益分类(1-唛盟众包,2-电商平台)","shopId","商户编号","instId","当前流程实例编号","flowState","当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除","smaxAt","单个任务最大金额（任务型用户才有）0代表不限制","totalAt","累计接单金额（任务型用户才有）0代表不限制","mtype","适用会员类型（2商户型、1普通型、3任务型）","totalExp","累计经验值0代表不限制","smaxExp","单个任务最大经验值0代表不限制","bids","投标次数上限","sfeeRate","服务费率0-100之间","mfee","每个月费用","discount","折扣比率，";<br>
 * 当前主键(包括多主键):<br>
 *	ilvl_id;<br>
 */
 @Data
@TableName("sys_interests")
@ApiModel(description="会员权益表")
public class Interests  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="等级ID,主键",allowEmptyValue=true,example="",allowableValues="")
	String ilvlId;
	
	@ApiModelProperty(notes="等级名称",allowEmptyValue=true,example="",allowableValues="")
	String ilvlName;
	
	@ApiModelProperty(notes="等级描述",allowEmptyValue=true,example="",allowableValues="")
	String idesc;
	
	@ApiModelProperty(notes="公司ID",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="1-粉丝,2-",allowEmptyValue=true,example="",allowableValues="")
	Integer ilevel;
	
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
	
	@ApiModelProperty(notes="续会时间yyyy-MM-dd型",allowEmptyValue=true,example="",allowableValues="")
	String rtime;
	
	@ApiModelProperty(notes="权益分类(1-唛盟众包,2-电商平台)",allowEmptyValue=true,example="",allowableValues="")
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
	
	@ApiModelProperty(notes="每个月费用",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal mfee;
	
	@ApiModelProperty(notes="折扣比率，",allowEmptyValue=true,example="",allowableValues="")
	String discount;

	/**
	 *等级ID
	 **/
	public Interests(String ilvlId) {
		this.ilvlId = ilvlId;
	}
    
    /**
     * 会员权益表
     **/
	public Interests() {
	}

}