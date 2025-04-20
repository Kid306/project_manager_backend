package  com.mdp.form.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author code-gen
 * @since 2024-4-27
 */
@Data
@TableName("form_def")
@ApiModel(description="表单定义")
public class FormDef  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.ASSIGN_ID)
	
	@ApiModelProperty(notes="主键,主键",allowEmptyValue=true,example="",allowableValues="")
	String id;

	
	@ApiModelProperty(notes="表名-当dataType=2,3时有效",allowEmptyValue=true,example="",allowableValues="")
	String tableName;

	
	@ApiModelProperty(notes="数据源-当dataType=2,3时有效",allowEmptyValue=true,example="",allowableValues="")
	String ds;

	
	@ApiModelProperty(notes="表单名称",allowEmptyValue=true,example="",allowableValues="")
	String formName;

	
	@ApiModelProperty(notes="创建人",allowEmptyValue=true,example="",allowableValues="")
	String userid;

	
	@ApiModelProperty(notes="创建部门",allowEmptyValue=true,example="",allowableValues="")
	String deptid;

	
	@ApiModelProperty(notes="表单类型",allowEmptyValue=true,example="",allowableValues="")
	String formType;

	
	@ApiModelProperty(notes="是否为模板",allowEmptyValue=true,example="",allowableValues="")
	String tpl;

	
	@ApiModelProperty(notes="业务分类",allowEmptyValue=true,example="",allowableValues="")
	String bizType;

	
	@ApiModelProperty(notes="创建日期",allowEmptyValue=true,example="",allowableValues="")
	Date ctime;

	
	@ApiModelProperty(notes="机构编号",allowEmptyValue=true,example="",allowableValues="")
	String branchId;

	
	@ApiModelProperty(notes="分类编号",allowEmptyValue=true,example="",allowableValues="")
	String categoryId;

	
	@ApiModelProperty(notes="标签编号列表",allowEmptyValue=true,example="",allowableValues="")
	String tagIds;

	
	@ApiModelProperty(notes="标签名字列表",allowEmptyValue=true,example="",allowableValues="")
	String tagNames;

	
	@ApiModelProperty(notes="创建人姓名",allowEmptyValue=true,example="",allowableValues="")
	String username;

	
	@ApiModelProperty(notes="创建部门",allowEmptyValue=true,example="",allowableValues="")
	String deptName;

	
	@ApiModelProperty(notes="数据存储模式1-api,crud，2-指定表格，3-独立表格（表单设计表根根谁自动变更），4-指定数据集，5-外部处理，9-存粹展示",allowEmptyValue=true,example="",allowableValues="")
	String dataType;

	
	@ApiModelProperty(notes="表单的扩展字段信息，对应formCreate.option",allowEmptyValue=true,example="",allowableValues="")
	String options;

	
	@ApiModelProperty(notes="函数列表",allowEmptyValue=true,example="",allowableValues="")
	String funcs;

	
	@ApiModelProperty(notes="表单生成规则列表，即页面元素定义列表，对应formCreate.rule",allowEmptyValue=true,example="",allowableValues="")
	String rules;

	
	@ApiModelProperty(notes="表单权限信息json字符串",allowEmptyValue=true,example="",allowableValues="")
	String formQx;

	
	@ApiModelProperty(notes="审核状态",allowEmptyValue=true,example="",allowableValues="")
	String flowState;

	
	@ApiModelProperty(notes="审核时间",allowEmptyValue=true,example="",allowableValues="")
	Date flowTime;

	
	@ApiModelProperty(notes="审核人",allowEmptyValue=true,example="",allowableValues="")
	String flowUserid;

	
	@ApiModelProperty(notes="发审人",allowEmptyValue=true,example="",allowableValues="")
	String flowStartUserid;

	
	@ApiModelProperty(notes="发审时间",allowEmptyValue=true,example="",allowableValues="")
	Date flowStartTime;

	
	@ApiModelProperty(notes="流程实例编号",allowEmptyValue=true,example="",allowableValues="")
	String procInstId;

	/**
	 *主键
	 **/
	public FormDef(String id) {
		this.id = id;
	}
    
    /**
     * 表单定义
     **/
	public FormDef() {
	}

}