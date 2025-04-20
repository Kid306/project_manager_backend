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
@TableName("xm_test_casedb")
@ApiModel(description="测试用例库")
public class XmTestCasedb  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="用例库名称",allowEmptyValue=true,example="",allowableValues="")
	String name;

	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="归属机构编号",allowEmptyValue=true,example="",allowableValues="")
	String cbranchId;

	
	@ApiModelProperty(notes="产品编号",allowEmptyValue=true,example="",allowableValues="")
	String productId;

	
	@ApiModelProperty(notes="产品名称",allowEmptyValue=true,example="",allowableValues="")
	String productName;

	
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

	
	@ApiModelProperty(notes="测试计划数",allowEmptyValue=true,example="",allowableValues="")
	Integer testPlans;

	
	@ApiModelProperty(notes="需求数目",allowEmptyValue=true,example="",allowableValues="")
	Integer menus;

	
	@ApiModelProperty(notes="功能模块数",allowEmptyValue=true,example="",allowableValues="")
	Integer funcs;

	
	@ApiModelProperty(notes="状态0初始，1-启用，2关闭",allowEmptyValue=true,example="",allowableValues="")
	String status;

	
	@ApiModelProperty(notes="预算工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal budgetWorkload;

	
	@ApiModelProperty(notes="实际工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actWorkload;

	
	@ApiModelProperty(notes="产品归属企业",allowEmptyValue=true,example="",allowableValues="")
	String pbranchId;

	@ApiModelProperty(notes="环境变量",allowEmptyValue=true,example="",allowableValues="")
	String envJson;



	/**
	 *主键
	 **/
	public XmTestCasedb(String id) {
		this.id = id;
	}
    
    /**
     * 测试用例库
     **/
	public XmTestCasedb() {
	}

}