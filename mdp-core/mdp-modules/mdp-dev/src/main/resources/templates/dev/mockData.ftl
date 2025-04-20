 import Mock from 'mockjs';

const ${entityName}s = [];

for (let i = 0; i < 25; i++) {
  ${entityName}s.push(Mock.mock({
  	<#list primaryKeysList as column>
  		<#if column.simpleJavaClassName=='String'> 
		${column.camelsColumnName}:Mock.Random.string(5),
		<#elseif column.simpleJavaClassName=='Date'>
		${column.camelsColumnName}:Mock.Random.date('yyyy-MM-dd'),
		<#elseif column.simpleJavaClassName=='boolean'>
		${column.camelsColumnName}: Mock.Random.integer(0, 1),
		<#elseif column.simpleJavaClassName=='Number' >
		'${column.camelsColumnName}|0-${column.columnSize%10}': 1,
		<#elseif column.simpleJavaClassName=='Integer' || column.simpleJavaClassName=='int'>
		'${column.camelsColumnName}|0-${column.columnSize%10}': 1,
		<#else>
		${column.camelsColumnName}:  Mock.Random.string(5),
		</#if>
  	</#list>
  	<#list columnExcludePkList as column> 
		<#if column.simpleJavaClassName=='String' && column.columnSize gt 2 > 
		${column.camelsColumnName}:Mock.Random.ctitle(1,${column.columnSize%10})<#if column_has_next> , </#if>
		<#elseif column.simpleJavaClassName=='String' && column.columnSize lte 2 > 
		${column.camelsColumnName}:Mock.Random.character('lower')<#if column_has_next> , </#if>
		<#elseif column.simpleJavaClassName=='Date'>
		${column.camelsColumnName}:Mock.Random.date('yyyy-MM-dd')<#if column_has_next> , </#if>
		<#elseif column.simpleJavaClassName=='boolean'>
		${column.camelsColumnName}: Mock.Random.integer(0, 1)<#if column_has_next> , </#if>
		<#elseif column.simpleJavaClassName=='Number'>
		'${column.camelsColumnName}|0-${column.columnSize}': 1<#if column_has_next> , </#if>
		<#elseif column.simpleJavaClassName=='Integer' || column.simpleJavaClassName=='int'>
		'${column.camelsColumnName}|0-${column.columnSize}': 1<#if column_has_next> , </#if>
		<#else>
		${column.camelsColumnName}:  Mock.Random.cname()<#if column_has_next> , </#if>
		</#if> 
	</#list> 
  }));
}

export { ${entityName}s };
 