package  com.mdp.sys.entity.mem;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组织 com  顶级模块 mdp 大模块 sys  小模块 mem<br> 
 * 实体 MemGrade所有属性名: <br>
 *	"gradeId","等级ID","gradeName","等级名称","gdesc","等级描述","branchId","公司ID","glevel","1-粉丝,2-","ilvlId","权益","gstatus","等级状态","ctime","创建时间","ltime","更新时间","picUrl","等级图标url","flevel","外部系统会员等级","isFree","是否付费","bpicUrl","会员卡背景图标url","gtype","等级分类(1-唛盟众包,2-电商平台)","shopId","商户编号","instId","当前流程实例编号","flowState","当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除","mtype","适用会员类型（2商户型、1普通型、3任务型）","startExp","经验值下限","endExp","经验值上限","startAt","金钱下限","endAt","金钱上限";<br>
 * 当前主键(包括多主键):<br>
 *	grade_id;<br>
 */
 @Data
@TableName("mem_grade")
@ApiModel(description="会员等级定义表")
public class MemGrade  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(notes="等级ID,主键",allowEmptyValue=true,example="",allowableValues="")
	String gradeId;
	
	@ApiModelProperty(notes="等级名称",allowEmptyValue=true,example="",allowableValues="")
	String gradeName;
	
	@ApiModelProperty(notes="等级描述",allowEmptyValue=true,example="",allowableValues="")
	String gdesc;
	
	@ApiModelProperty(notes="公司ID",allowEmptyValue=true,example="",allowableValues="")
	String branchId;
	
	@ApiModelProperty(notes="1-粉丝,2-",allowEmptyValue=true,example="",allowableValues="")
	Integer glevel;
	
	@ApiModelProperty(notes="权益",allowEmptyValue=true,example="",allowableValues="")
	String ilvlId;
	
	@ApiModelProperty(notes="等级状态",allowEmptyValue=true,example="",allowableValues="")
	String gstatus;
	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;
	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;
	
	@ApiModelProperty(notes="等级图标url",allowEmptyValue=true,example="",allowableValues="")
	String picUrl;
	
	@ApiModelProperty(notes="外部系统会员等级",allowEmptyValue=true,example="",allowableValues="")
	String flevel;
	
	@ApiModelProperty(notes="是否付费",allowEmptyValue=true,example="",allowableValues="")
	String isFree;
	
	@ApiModelProperty(notes="会员卡背景图标url",allowEmptyValue=true,example="",allowableValues="")
	String bpicUrl;
	
	@ApiModelProperty(notes="等级分类(1-唛盟众包,2-电商平台)",allowEmptyValue=true,example="",allowableValues="")
	String gtype;
	
	@ApiModelProperty(notes="商户编号",allowEmptyValue=true,example="",allowableValues="")
	String shopId;
	
	@ApiModelProperty(notes="当前流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String instId;
	
	@ApiModelProperty(notes="当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除",allowEmptyValue=true,example="",allowableValues="")
	String flowState;
	
	@ApiModelProperty(notes="适用会员类型（2商户型、1普通型、3任务型）",allowEmptyValue=true,example="",allowableValues="")
	String mtype;
	
	@ApiModelProperty(notes="经验值下限",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal startExp;
	
	@ApiModelProperty(notes="经验值上限",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal endExp;
	
	@ApiModelProperty(notes="金钱下限",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal startAt;
	
	@ApiModelProperty(notes="金钱上限",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal endAt;

	/**
	 *等级ID
	 **/
	public MemGrade(String gradeId) {
		this.gradeId = gradeId;
	}
    
    /**
     * 会员等级定义表
     **/
	public MemGrade() {
	}

}