package com.mdp.core.ctrl;

import com.mdp.core.entity.Dept;
import com.mdp.core.entity.Tips;
import com.mdp.core.service.DeptService;
import com.mdp.core.service.SequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器 主要实现对资源的请求访问控制,<br>
 * 1.包括接收请求并把其映射到对应的控制层函数中<br>
 * 2.调用服务层逻辑更新后台数据或者发起后端系统/外联系统通讯等<br>
 * 3.将服务层逻辑的结果进行整合处理并返回给页面进行展示,如果没有界面,则直接将数据返回给客户端.<br>
 * 
 * url编制采用rest风格,如对ADMIN.sys_dept的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/dept/add.json <br>
 *  查询: sys/dept/listPage.json<br>
 *  修改: sys/dept/edit.json <br>
 *  删除: sys/dept/del.json<br>
 *  批量删除: sys/dept/batchDel.json<br>
 * 组织 com.qqkj  顶级模块 mdp   大模块 sys   小模块 <br>
 * 表 ADMIN.sys_dept sys_dept  实体 Dept  表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	deptid,deptname,pdeptid,depttype,regionId,regionName,address,contactPerson,areaCode,telephone,mobilephone,sfax,email,state,createPerson,createDate,loperatePerson,loperateDate,deptManager,inChargeManager,isactive,isSubcompany,deptShortname;<br>
 * 当前表的所有字段名:<br>
 *	deptid,deptname,pdeptid,depttype,region_id,region_name,address,contact_person,area_code,telephone,mobilephone,sfax,email,state,create_person,create_date,loperate_person,loperate_date,dept_manager,in_charge_manager,isactive,is_subcompany,dept_shortname;<br>
 * 当前主键(包括多主键):<br>
 *	deptid;<br>
 ***/
@Controller("mdp.core.deptController")
@RequestMapping(value="/*/core/dept")
public class DeptController {
	
	static Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private SequenceService seqService;
		

	
	/**
	 * 新增一条数据
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> addDept(@RequestBody Dept dept) {
		Map<String,Object> m = new HashMap<>();
		Tips tips=new Tips("成功新增一条数据");
		if(StringUtils.isEmpty(dept.getDeptid())){
			dept.setDeptid(seqService.getTablePK("dept", "deptid"));
		}
		deptService.save(dept);
		m.put("data",dept);
		m.put("tips", tips);
		return m;
	}
	
	
	/**
	 * 根据主键删除1条数据
	*/
	@ResponseBody
	@RequestMapping(value="/del")
	public Map<String,Object> delDept(@RequestBody Dept dept){
		Map<String,Object> m = new HashMap<>();
		Tips tips=new Tips("成功删除一条数据");
		deptService.removeById(dept.getDeptid());
		m.put("tips", tips);
		return m;
	}
	 
	
	/**
	 * 根据主键修改一条数据
	 */
	@ResponseBody
	@RequestMapping(value="/edit")
	public Map<String,Object> editDept(@RequestBody Dept dept) {
		Map<String,Object> m = new HashMap<>();
		Tips tips=new Tips("成功更新一条数据");
		deptService.updateById(dept);
		m.put("data",dept);
		m.put("tips", tips);
		return m;
	}
	
	

	
	/**
	 * 批量删除
	 */
	@ResponseBody
	@RequestMapping(value="/batchDel")
	public Map<String,Object> batchDelDept(@RequestBody String[] deptids) {
		Map<String,Object> m = new HashMap<>();
		Tips tips=new Tips("成功删除"+deptids.length+"条数据");
		List<String> list=new ArrayList<String>();
		for(int i=0;i<deptids.length;i++){
			Dept dept=new Dept();
			dept.setDeptid(deptids[i]);
			list.add(dept.getDeptid());
		  }
		deptService.removeByIds(list);
		m.put("tips", tips);
		return m;
	} 
	
}
