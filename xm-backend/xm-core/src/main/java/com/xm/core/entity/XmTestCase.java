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
@TableName("xm_test_case")
@ApiModel(description="测试用例")
public class XmTestCase  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="标题",allowEmptyValue=true,example="",allowableValues="")
	String caseName;

	
	@ApiModelProperty(notes="备注",allowEmptyValue=true,example="",allowableValues="")
	String caseRemark;

	
	@ApiModelProperty(notes="测试步骤",allowEmptyValue=true,example="",allowableValues="")
	String testStep;

	
	@ApiModelProperty(notes="期望结果",allowEmptyValue=true,example="",allowableValues="")
	String expectResult;

	
	@ApiModelProperty(notes="关联的故事",allowEmptyValue=true,example="",allowableValues="")
	String menuId;

	
	@ApiModelProperty(notes="关联故事名",allowEmptyValue=true,example="",allowableValues="")
	String menuName;

	
	@ApiModelProperty(notes="创建时间",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="更新时间",allowEmptyValue=true,example="",allowableValues="")
	Date ltime;

	
	@ApiModelProperty(notes="更新人编号",allowEmptyValue=true,example="",allowableValues="")
	String luserid;

	
	@ApiModelProperty(notes="更新人姓名",allowEmptyValue=true,example="",allowableValues="")
	String lusername;

	
	@ApiModelProperty(notes="创建机构",allowEmptyValue=true,example="",allowableValues="")
	String cbranchId;

	
	@ApiModelProperty(notes="模块编号",allowEmptyValue=true,example="",allowableValues="")
	String moduleId;

	
	@ApiModelProperty(notes="模块名称",allowEmptyValue=true,example="",allowableValues="")
	String moduleName;

	
	@ApiModelProperty(notes="用例状态0-草稿，1-评审中，2-审核通过，3-审核未通过，4-废弃",allowEmptyValue=true,example="",allowableValues="")
	String caseStatus;

	
	@ApiModelProperty(notes="创建人编号",allowEmptyValue=true,example="",allowableValues="")
	String cuserid;

	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String cusername;

	
	@ApiModelProperty(notes="产品编号",allowEmptyValue=true,example="",allowableValues="")
	String productId;

	
	@ApiModelProperty(notes="版本号",allowEmptyValue=true,example="",allowableValues="")
	String verNum;

	
	@ApiModelProperty(notes="用例库编号",allowEmptyValue=true,example="",allowableValues="")
	String casedbId;

	
	@ApiModelProperty(notes="用例库名称",allowEmptyValue=true,example="",allowableValues="")
	String casedbName;

	
	@ApiModelProperty(notes="功能菜单编号",allowEmptyValue=true,example="",allowableValues="")
	String funcId;

	
	@ApiModelProperty(notes="功能菜单名称",allowEmptyValue=true,example="",allowableValues="")
	String funcName;

	
	@ApiModelProperty(notes="上级菜单名称列表逗号分割",allowEmptyValue=true,example="",allowableValues="")
	String funcPnames;

	
	@ApiModelProperty(notes="前置条件描述",allowEmptyValue=true,example="",allowableValues="")
	String preRemark;

	
	@ApiModelProperty(notes="用例类型-与bug类型相同",allowEmptyValue=true,example="",allowableValues="")
	String caseType;

	
	@ApiModelProperty(notes="优先级",allowEmptyValue=true,example="",allowableValues="")
	String cpriority;

	
	@ApiModelProperty(notes="预算工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal budgetWorkload;

	
	@ApiModelProperty(notes="实际工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal actWorkload;

	
	@ApiModelProperty(notes="原估工时",allowEmptyValue=true,example="",allowableValues="")
	BigDecimal initWorkload;

	
	@ApiModelProperty(notes="是否可作为回归测试用例0-否，1-是",allowEmptyValue=true,example="",allowableValues="")
	String retest;

	
	@ApiModelProperty(notes="产品归属企业",allowEmptyValue=true,example="",allowableValues="")
	String pbranchId;

	
	@ApiModelProperty(notes="测试方式0-手工，1-自动",allowEmptyValue=true,example="",allowableValues="")
	String testType;

	
	@ApiModelProperty(notes="自动测试步骤",allowEmptyValue=true,example="",allowableValues="")
	String autoStep;

	@ApiModelProperty(notes="进度0-100",allowEmptyValue=true,example="",allowableValues="")
	Integer rate;

	@ApiModelProperty(notes="排序号",allowEmptyValue=true,example="",allowableValues="")
	String sortNo;

	/**
	 *主键
	 **/
	public XmTestCase(String id) {
		this.id = id;
	}
    
    /**
     * 测试用例
     **/
	public XmTestCase() {
	}

}