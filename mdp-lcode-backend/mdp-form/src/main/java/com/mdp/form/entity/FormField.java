package com.mdp.form.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author code-gen
 * @since 2023-10-7
 */
@Data
@ApiModel(description="表单字段定义")
public class FormField implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
     @ApiModelProperty(name = "字段显示名称")
	String title;

	@ApiModelProperty(name = "字段编码，前端组件绑定使用，驼峰命名")
	String field;

	@ApiModelProperty(name = "关联字典代码")
	String itemCode;

	@ApiModelProperty(name = "前端组件类型-指向组件的.name")
	String type;

	@ApiModelProperty(name = "子组件")
	List<FormField> children;

}