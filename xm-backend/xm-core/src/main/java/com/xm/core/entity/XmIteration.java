package  com.xm.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author code-gen
 * @since 2023-10-3
 */
@Data
@TableName("xm_iteration")
@ApiModel(description="迭代定义")
public class XmIteration  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="迭代编码,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="迭代名称",allowEmptyValue=true,example="",allowableValues="")
	String iterationName;

	
	@ApiModelProperty(notes="开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date startTime;

	
	@ApiModelProperty(notes="结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date endTime;

	
	@ApiModelProperty(notes="上线时间",allowEmptyValue=true,example="",allowableValues="")
	Date onlineTime;

	
	@ApiModelProperty(notes="上级迭代-作废，不以树状结构",allowEmptyValue=true,example="",allowableValues="")
	String pid;

	
	@ApiModelProperty(notes="负责人",allowEmptyValue=true,example="",allowableValues="")
	String adminUserid;

	
	@ApiModelProperty(notes="负责人姓名",allowEmptyValue=true,example="",allowableValues="")
	String adminUsername;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="预算成本",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal budgetCost;

	
	@ApiModelProperty(notes="预算工作量",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal budgetWorkload;

	
	@ApiModelProperty(notes="顺序号",allowEmptyValue=true,example="",allowableValues="")
	String seqNo;

	
	@ApiModelProperty(notes="迭代状态0未结束1已结束",allowEmptyValue=true,example="",allowableValues="")
	String istatus;

	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建人人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String remark;

	
	@ApiModelProperty(notes="迭代阶段:0未开始,1需求评审,2计划会,3研发中,4测试中,5迭代上线,6已完成7已关闭",allowEmptyValue=true,example="",allowableValues="")
	String iphase;

	
	@ApiModelProperty(notes="是否为模板",allowEmptyValue=true,example="",allowableValues="")
	String isTpl;

	
	@ApiModelProperty(notes="产品编号",allowEmptyValue=true,example="",allowableValues="")
	String productId;

	
	@ApiModelProperty(notes="产品名称",allowEmptyValue=true,example="",allowableValues="")
	String productName;

	@Schema(description = "标签，逗号分隔多个")
	String tagIds;

	/**
	 *迭代编码
	 **/
	public XmIteration(String id) {
		this.id = id;
	}
    
    /**
     * 迭代定义
     **/
	public XmIteration() {
	}

}