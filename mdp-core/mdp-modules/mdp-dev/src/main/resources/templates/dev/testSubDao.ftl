package ${largeModulePackage}.dao;

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
import com.mdp.core.dao.BaseDao;
import com.mdp.mybatis.PageUtils;
import com.github.pagehelper.Page;
import ${largeModulePackage}.entity.${entityName};
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @author code-gen
 * @since ${.now?date}
 */
 @RunWith(SpringJUnit4ClassRunner.class)
 @SpringBootTest
public class Test${entityName}Dao  {

	@Autowired
	BaseDao baseDao;

	/**
	 * 新增一条数据
	 ***/
	@Test
	public void insert() {
		Map<String,Object> p=BaseUtils.map(<#list columnList as column>"${column.camelsColumnName}",<#if column.simpleJavaClassName='Date'>new Date("${column.testValue}")<#elseif column.simpleJavaClassName='String'>"${column.testValue}"<#elseif column.simpleJavaClassName='boolean'> ${column.testValue?c}<#elseif column.simpleJavaClassName='[B'>"${column.testValue}"<#else>${column.testValue}</#if><#if column_has_next>,</#if></#list>);
		${entityName} ${smallEntityName}=BaseUtils.fromMap(p,${entityName}.class);
		baseDao.insert(${smallEntityName});
		//Assert.assertEquals(1, result);
	} 
}
