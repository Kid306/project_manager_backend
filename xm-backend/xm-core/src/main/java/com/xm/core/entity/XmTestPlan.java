package  com.xm.core.entity;

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
 * @since 2023-10-3
 */
@Data
@TableName("xm_test_plan")
@ApiModel(description="测试计划")
public class XmTestPlan  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="测试计划编号,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="计划名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	
	@ApiModelProperty(notes="用例库编号",allowEmptyValue=true,example="",allowableValues="")
	String casedbId;

	
	@ApiModelProperty(notes="用例库名称",allowEmptyValue=true,example="",allowableValues="")
	String casedbName;

	
	@ApiModelProperty(notes="项目编号",allowEmptyValue=true,example="",allowableValues="")
	String projectId;

	
	@ApiModelProperty(notes="项目名称",allowEmptyValue=true,example="",allowableValues="")
	String projectName;

	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建人名称",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="开始时间",allowEmptyValue=true,example="",allowableValues="")
	Date stime;

	
	@ApiModelProperty(notes="结束时间",allowEmptyValue=true,example="",allowableValues="")
	Date etime;

	
	@ApiModelProperty(notes="状态0-未开始，1-进行中，2已结束",allowEmptyValue=true,example="",allowableValues="")
	String status;

	
	@ApiModelProperty(notes="测试结果0未通过，1已通过",allowEmptyValue=true,example="",allowableValues="")
	String tcode;

	
	@ApiModelProperty(notes="总用例数",allowEmptyValue=true,example="",allowableValues="")
	Integer totalCases;

	
	@ApiModelProperty(notes="通过用例数",allowEmptyValue=true,example="",allowableValues="")
	Integer okCases;

	
	@ApiModelProperty(notes="失败用例数",allowEmptyValue=true,example="",allowableValues="")
	Integer errCases;

	
	@ApiModelProperty(notes="忽略用例数",allowEmptyValue=true,example="",allowableValues="")
	Integer igCases;

	
	@ApiModelProperty(notes="阻塞用例数",allowEmptyValue=true,example="",allowableValues="")
	Integer blCases;

	
	@ApiModelProperty(notes="产品编号",allowEmptyValue=true,example="",allowableValues="")
	String productId;

	
	@ApiModelProperty(notes="产品名称",allowEmptyValue=true,example="",allowableValues="")
	String productName;

	
	@ApiModelProperty(notes="评审结果0-待评审，1-已评审通过，2-已拒绝",allowEmptyValue=true,example="",allowableValues="")
	String flowState;

	
	@ApiModelProperty(notes="bug数目",allowEmptyValue=true,example="",allowableValues="")
	Integer bugCnt;

	
	@ApiModelProperty(notes="已关闭bug总数",allowEmptyValue=true,example="",allowableValues="")
	Integer closedBugs;

	
	@ApiModelProperty(notes="已解决bug总数",allowEmptyValue=true,example="",allowableValues="")
	Integer resolvedBugs;

	
	@ApiModelProperty(notes="激活的bug总数",allowEmptyValue=true,example="",allowableValues="")
	Integer activeBugs;

	
	@ApiModelProperty(notes="已解决bug总数",allowEmptyValue=true,example="",allowableValues="")
	Integer confirmedBugs;

	
	@ApiModelProperty(notes="需求数目",allowEmptyValue=true,example="",allowableValues="")
	Integer menus;

	
	@ApiModelProperty(notes="功能模块数",allowEmptyValue=true,example="",allowableValues="")
	Integer funcs;

	
	@ApiModelProperty(notes="预算工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal budgetWorkload;

	
	@ApiModelProperty(notes="实际工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actWorkload;

	
	@ApiModelProperty(notes="报告总结",allowEmptyValue=true,example="",allowableValues="")
	String summaryRemark;

	
	@ApiModelProperty(notes="创建机构编号",allowEmptyValue=true,example="",allowableValues="")
	String cbranchId;

	
	@ApiModelProperty(notes="未测用例数",allowEmptyValue=true,example="",allowableValues="")
	Integer toTestCases;

	
	@ApiModelProperty(notes="产品归属企业",allowEmptyValue=true,example="",allowableValues="")
	String pbranchId;

	
	@ApiModelProperty(notes="计划类型0-普通测试，2-迭代测试，1-发布测试，",allowEmptyValue=true,example="",allowableValues="")
	String ptype;

	@ApiModelProperty(notes="测试方式0-手工，1-自动化",allowEmptyValue=true,example="",allowableValues="")
	String testType;

	@ApiModelProperty(notes="环境变量",allowEmptyValue=true,example="",allowableValues="")
	String envJson;
	/**
	 *测试计划编号
	 **/
	public XmTestPlan(String id) {
		this.id = id;
	}
    
    /**
     * 测试计划
     **/
	public XmTestPlan() {
	}

}