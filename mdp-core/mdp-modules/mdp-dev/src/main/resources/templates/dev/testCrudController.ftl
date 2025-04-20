package ${largeModulePackage}.ctrl;

import java.text.SimpleDateFormat;
import java.util.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext; 
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import ${largeModulePackage}.entity.${entityName};
import com.mdp.core.utils.BaseUtils;
/**
 * @author code-gen
 * @since ${.now?date}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test${entityName}Controller {

	@Autowired
	public WebApplicationContext wac; // cached
	@Autowired
	public MockServletContext servletContext; // cached
	@Autowired
	public MockHttpSession session;
	@Autowired
	public MockHttpServletRequest request;
	@Autowired
	public MockHttpServletResponse response;
	@Autowired
	public ServletWebRequest webRequest;

	public MockMvc mockMvc;
	
	public MockHttpServletRequestBuilder msrb;
	 
	ObjectMapper om = new ObjectMapper();
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void add() throws Exception  {
		Map<String,Object> p=BaseUtils.map(<#list columnList as column>"${column.camelsColumnName}",<#if column.simpleJavaClassName='Date'>new Date("${column.testValue}")<#elseif column.simpleJavaClassName='String'>"${column.testValue}"<#elseif column.simpleJavaClassName='boolean'> ${column.testValue?c}<#elseif column.simpleJavaClassName='[B'>"${column.testValue}"<#else>${column.testValue}</#if><#if column_has_next>,</#if></#list>);
		${entityName} ${smallEntityName}=BaseUtils.fromMap(p,${entityName}.class);
		String json${entityName}=om.writeValueAsString(${smallEntityName});
		mockMvc.perform( post("${baseRequestUri}${smallModulePathExt}/${smallEntityName}/add").content(json${entityName}).contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
		   	.andExpect(jsonPath("tips.isOk").value(true));
	}

	@Test
	public void list() throws Exception  {
		mockMvc.perform( get("${baseRequestUri}${smallModulePathExt}/${smallEntityName}/list")
		.param("${primaryKeysList[0].camelsColumnName}","${primaryKeysList[0].testValue}").param("currentPage", "1").param("pageSize", "10"))
		   .andDo(print())
		   .andExpect(status().isOk()) 
		   .andExpect(jsonPath("tips.isOk").value(true))
		   .andExpect(jsonPath("data").isArray())
		   .andExpect(jsonPath("total").exists());
	}
	 
	@Test
	public void edit() throws Exception {
		Map<String,Object> p=BaseUtils.map(<#list columnList as column>"${column.camelsColumnName}",<#if column.simpleJavaClassName='Date'>new Date("${column.testValue}")<#elseif column.simpleJavaClassName='String'>"${column.testValue}"<#elseif column.simpleJavaClassName='boolean'> ${column.testValue?c}<#elseif column.simpleJavaClassName='[B'>"${column.testValue}"<#else>${column.testValue}</#if><#if column_has_next>,</#if></#list>);
		${entityName} ${smallEntityName}=BaseUtils.fromMap(p,${entityName}.class);
		String json${entityName}=om.writeValueAsString(${smallEntityName});
		mockMvc.perform( post("${baseRequestUri}${smallModulePathExt}/${smallEntityName}/edit").content(json${entityName}).contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("tips.isOk").value(true));
	}

	@Test
	public void del() throws Exception {
		mockMvc.perform( post("${baseRequestUri}${smallModulePathExt}/${smallEntityName}/del").content("${primaryKeysList[0].testValue}").contentType(MediaType.APPLICATION_JSON))		 
		   .andDo(print())
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("tips.isOk").value(true));
	}
	
	@Test
	public void batchDel() throws Exception { 
		Map<String,Object> p=BaseUtils.map(<#list columnList as column>"${column.camelsColumnName}",<#if column.simpleJavaClassName='Date'>new Date("${column.testValue}")<#elseif column.simpleJavaClassName='String'>"${column.testValue}"<#elseif column.simpleJavaClassName='boolean'> ${column.testValue?c}<#elseif column.simpleJavaClassName='[B'>"${column.testValue}"<#else>${column.testValue}</#if><#if column_has_next>,</#if></#list>);
		${entityName} ${smallEntityName}=BaseUtils.fromMap(p,${entityName}.class);
		List<${entityName}>  ${smallEntityName}s=new ArrayList<>();
		${smallEntityName}s.add(${smallEntityName});
		String json${entityName}=om.writeValueAsString(${smallEntityName}s);
		mockMvc.perform( post("${baseRequestUri}${smallModulePathExt}/${smallEntityName}/batchDel").content(json${entityName}).contentType(MediaType.APPLICATION_JSON))
		   .andDo(print())
		   .andExpect(status().isOk())  
		   .andExpect(jsonPath("tips.isOk").value(true));
	}
	 
}
