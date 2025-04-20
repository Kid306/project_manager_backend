package  ${largeModulePackage}.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.mdp.core.dao.annotation.TableIds;
import com.baomidou.mybatisplus.annotation.TableName;
<#list importClassList as column>
import ${column.fullJavaClassName};
</#list>

/**
 * @author 唛盟开源 code-gen
 * @since ${.now?date}
 */
@Data
@TableName("${tableName}")
@Schema(description="${tableRemarks}")
public class ${entityName}  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	<#if (primaryKeysList?size <= 1)>
	<#list primaryKeysList as column>
	@TableId(type = IdType.ASSIGN_ID)
	<#if (printTableField==true)>@TableField("${column.columnName}")</#if>
	@Schema(description="${column.remarks},主键")
	${column.simpleJavaClassName} ${column.camelsColumnName};
  	</#list>
  	</#if>
    <#if (primaryKeysList?size > 1 )>
    <#list primaryKeysList as column>
    @TableIds
	<#if (printTableField==true)>@TableField("${column.columnName}")</#if>
    @Schema(description="${column.remarks},主键")
    ${column.simpleJavaClassName} ${column.camelsColumnName};
    </#list>
    </#if>
	<#list columnExcludePkList as column>

	<#if (printTableField==true)>@TableField("${column.columnName}")</#if>
	@Schema(description="${column.remarks}")
	${column.simpleJavaClassName} ${column.camelsColumnName};
  	</#list>

	/**
	 *<#list primaryKeysList as column>${column.remarks}<#if column_has_next>,</#if></#list>
	 **/
	public ${entityName}(<#list primaryKeysList as column>${column.simpleJavaClassName} ${column.camelsColumnName}<#if column_has_next>,</#if></#list>) {
		<#list primaryKeysList as column>
		this.${column.camelsColumnName} = ${column.camelsColumnName};
		</#list>
	}
    
    /**
     * ${tableRemarks}
     **/
	public ${entityName}() {
	}

}