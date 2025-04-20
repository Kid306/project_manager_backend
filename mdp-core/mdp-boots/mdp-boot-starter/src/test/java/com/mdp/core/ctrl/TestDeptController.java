package com.mdp.core.ctrl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdp.core.entity.Dept;
import com.mdp.core.utils.BaseUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * 组织 com.qqkj<br>
 * 顶级模块 mdp<br>
 * 大模块 sys<br>
 * 小模块 <br>
 * 表 ADMIN.sys_dept sys_dept<br>
 * 实体 Dept<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	deptid,deptname,pdeptid,depttype,regionId,regionName,address,contactPerson,areaCode,telephone,mobilephone,sfax,email,state,createPerson,createDate,loperatePerson,loperateDate,deptManager,inChargeManager,isactive,isSubcompany,deptShortname;<br>
 * 当前表的所有字段名:<br>
 *	deptid,deptname,pdeptid,depttype,region_id,region_name,address,contact_person,area_code,telephone,mobilephone,sfax,email,state,create_person,create_date,loperate_person,loperate_date,dept_manager,in_charge_manager,isactive,is_subcompany,dept_shortname;<br>
 * 当前主键(包括多主键):<br>
 *	deptid;<br>
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestDeptController {

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
		Map<String,Object> p= BaseUtils.map("deptid","hlJ5","deptname","OeEa","pdeptid","vVIR","depttype","8U","regionId","1Mqc","regionName","4fGA","address","Q7bX","contactPerson","W79F","areaCode","FOUQ","telephone","4a7Z","mobilephone","R7wz","sfax","YSYp","email","5KJ5","state","QOCk","createPerson","9oW8","createDate",parse("2017-10-18 23:57:3"),"loperatePerson","k37X","loperateDate",parse("2017-10-18 23:57:3"),"deptManager","hF06","inChargeManager","x9av","isactive",5,"isSubcompany",5,"deptShortname","hjrG");
		Dept dept=BaseUtils.fromMap(p,Dept.class);
		String jsonDept=om.writeValueAsString(dept);
		mockMvc.perform( post("/*/core/dept/add").content(jsonDept).contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
		   	.andExpect(jsonPath("tips.isOk").value(true));
	}

	@Test
	public void listPage() throws Exception  {
		mockMvc.perform( get("/*/core/dept/listPage")
		.param("deptid","hlJ5").param("pageSize", "9").param("start", "3"))
		   .andDo(print())
		   .andExpect(status().isOk()) 
		   .andExpect(jsonPath("tips.isOk").value(true))
		   .andExpect(jsonPath("data").isArray());
	}
	
	

	@Test
	public void edit() throws Exception {
		Map<String,Object> p=BaseUtils.map("deptid","hlJ5","deptname","OeEa","pdeptid","vVIR","depttype","8U","regionId","1Mqc","regionName","4fGA","address","Q7bX","contactPerson","W79F","areaCode","FOUQ","telephone","4a7Z","mobilephone","R7wz","sfax","YSYp","email","5KJ5","state","QOCk","createPerson","9oW8","createDate",parse("2017-10-18 23:57:3"),"loperatePerson","k37X","loperateDate",parse("2017-10-18 23:57:3"),"deptManager","hF06","inChargeManager","x9av","isactive",5,"isSubcompany",5,"deptShortname","hjrG");
		Dept dept=BaseUtils.fromMap(p,Dept.class);
		String jsonDept=om.writeValueAsString(dept);
		mockMvc.perform( post("/*/core/dept/edit").content(jsonDept).contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("tips.isOk").value(true));
	}

	@Test
	public void del() throws Exception {
		mockMvc.perform( post("/*/core/dept/del").content("hlJ5").contentType(MediaType.APPLICATION_JSON))		 
		   .andDo(print())
		   .andExpect(status().isOk())
		   .andExpect(jsonPath("tips.isOk").value(true));
	}
	
	@Test
	public void batchDel() throws Exception { 
		String[] deptids=new String[3];
		deptids[0]="1";
		deptids[1]="2";
		deptids[2]="3";
		String jsonDept=om.writeValueAsString(deptids);
		mockMvc.perform( post("/*/core/dept/batchDel").content(jsonDept).contentType(MediaType.APPLICATION_JSON))
		   .andDo(print())
		   .andExpect(status().isOk())  
		   .andExpect(jsonPath("tips.isOk").value(true));
	}
		/**
	 * 将字符串类型的日期转成Date对象
	 * @param source 如2015-10-23或者 2015-10-23 15:30:25等
	 *        2015-10-23 15:30:25 对应的pattern 为 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parse(String source){
		 
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			try {
				return sdf.parse(source);
			} catch (Exception e) {
			}   
		
		return null;
	}
}
