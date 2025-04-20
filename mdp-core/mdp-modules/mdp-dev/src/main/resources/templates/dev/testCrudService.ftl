package  ${largeModulePackage}.service;

import java.util.*;
import java.text.SimpleDateFormat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mdp.core.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired; 
import  ${largeModulePackage}.service.${entityName}Service;
import  ${largeModulePackage}.entity.${entityName};
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @author code-gen
 * @since ${.now?date}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test${entityName}Service  {

	@Autowired
	${entityName}Service ${smallEntityName}Service;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map(<#list columnList as column>"${column.camelsColumnName}",<#if column.simpleJavaClassName='Date'>new Date("${column.testValue}")<#elseif column.simpleJavaClassName='String'>"${column.testValue}"<#elseif column.simpleJavaClassName='boolean'> ${column.testValue?c}<#elseif column.simpleJavaClassName='[B'>"${column.testValue}"<#else>${column.testValue}</#if><#if column_has_next>,</#if></#list>);
		${entityName} ${smallEntityName}=BaseUtils.fromMap(p,${entityName}.class);
		${smallEntityName}Service.save(${smallEntityName});
		//Assert.assertEquals(1, result);
	}
	 
}
