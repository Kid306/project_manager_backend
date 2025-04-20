package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmTaskSkill;
import com.xm.core.service.XmTaskSkillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对XM.xm_task_skill xm_task_skill的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmTaskSkill/add <br>
 *  查询: xm/xmTaskSkill/list<br>
 *  模糊查询: xm/xmTaskSkill/listKey<br>
 *  修改: xm/xmTaskSkill/edit <br>
 *  删除: xm/xmTaskSkill/del<br>
 *  批量删除: xm/xmTaskSkill/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmTaskSkill 表 XM.xm_task_skill 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmTaskSkillController")
@RequestMapping(value="/xm/core/xmTaskSkill")
@Api(tags={"xm_task_skill操作接口"})
public class XmTaskSkillController {
	
	static Log logger=LogFactory.getLog(XmTaskSkillController.class);
	
	@Autowired
	private XmTaskSkillService xmTaskSkillService;
	 
		
 
	
	@ApiOperation( value = "查询xm_task_skill信息列表",notes="listXmTaskSkill,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmTaskSkill.class)
	@ApiResponses({
		@ApiResponse(code = 200,response= XmTaskSkill.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTaskSkill(@ApiIgnore @RequestParam Map<String,Object> params){
				
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmTaskSkill> qw = QueryTools.initQueryWrapper(XmTaskSkill.class , params);
		List<Map<String,Object>> datas = xmTaskSkillService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTaskSkill列表
		
	}
	
	@ApiOperation( value = "批量添加技能",notes="batchDelXmTaskSkill,仅需要上传主键字段")
	@ApiResponses({
	@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	//@HasQx(value = "xm_core_xmTaskSkill_batchAdd",name = "批量新增任务的技能要求",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/batchAdd",method=RequestMethod.POST)
	public Result batchAddSkill(@RequestBody List<XmTaskSkill> xmTaskSkills) {

			xmTaskSkillService.insertOrDelete(xmTaskSkills);
		return Result.ok();
		
	}
	
 
	
	/**
	@ApiOperation( value = "新增一条xm_task_skill信息",notes="addXmTaskSkill,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskSkill.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmTaskSkill(@RequestBody XmTaskSkill xmTaskSkill) {

			if(StringUtils.isEmpty(xmTaskSkill.getId())) {
				xmTaskSkill.setId(xmTaskSkillService.createKey("id"));
			}else{
				 XmTaskSkill xmTaskSkillQuery = new  XmTaskSkill(xmTaskSkill.getId());
				if(xmTaskSkillService.countByWhere(xmTaskSkillQuery)>0){
					return Result.error("编号重复，请修改编号再提交");
					
				}
			}
			xmTaskSkillService.insert(xmTaskSkill);
		
	}
	*/
	
	@ApiOperation( value = "删除一条xm_task_skill信息",notes="delXmTaskSkill,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	//@HasQx(value = "xm_core_xmTaskSkill_del",name = "删除任务的技能要求",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTaskSkill(@RequestBody XmTaskSkill xmTaskSkill){

			xmTaskSkillService.deleteByPk(xmTaskSkill);
		return Result.ok();
		
	}
	
	/**
	@ApiOperation( value = "根据主键修改一条xm_task_skill信息",notes="editXmTaskSkill")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskSkill.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmTaskSkill(@RequestBody XmTaskSkill xmTaskSkill) {

			xmTaskSkillService.updateByPk(xmTaskSkill);
		
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除xm_task_skill信息",notes="batchDelXmTaskSkill,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmTaskSkill(@RequestBody List<XmTaskSkill> xmTaskSkills) {
		
		
		
			xmTaskSkillService.batchDelete(xmTaskSkills);
		return Result.ok();
		
	} 
	*/
}
