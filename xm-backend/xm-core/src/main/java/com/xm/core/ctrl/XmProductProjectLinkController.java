package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.XmMenu;
import com.xm.core.entity.XmProductProjectLink;
import com.xm.core.entity.XmTask;
import com.xm.core.service.XmGroupService;
import com.xm.core.service.XmProductProjectLinkService;
import com.xm.core.service.XmTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

/**
 * url编制采用rest风格,如对XM.xm_product_project_link 产品与项目的关联关系表，一般由产品经理挂接项目到产品上的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmProductProjectLink/add <br>
 *  查询: core/xmProductProjectLink/list<br>
 *  模糊查询: core/xmProductProjectLink/listKey<br>
 *  修改: core/xmProductProjectLink/edit <br>
 *  删除: core/xmProductProjectLink/del<br>
 *  批量删除: core/xmProductProjectLink/batchDel<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmProductProjectLink 表 XM.xm_product_project_link 当前主键(包括多主键): project_id; 
 ***/
@RestController("xm.core.xmProductProjectLinkController")
@RequestMapping(value="/xm/core/xmProductProjectLink")
@Api(tags={"产品与项目的关联关系表，一般由产品经理挂接项目到产品上操作接口"})
public class XmProductProjectLinkController {
	
	static Logger logger =LoggerFactory.getLogger(XmProductProjectLinkController.class);
	
	@Autowired
	private XmProductProjectLinkService xmProductProjectLinkService;
	@Autowired
	XmGroupService xmGroupService;

	@Autowired
	XmTaskService xmTaskService;


	Map<String,Object> fieldsMap = BaseUtils.toMap(new XmProductProjectLink());
	
	@ApiOperation( value = "查询产品与项目的关联关系表，一般由产品经理挂接项目到产品上信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProductProjectLink.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmProductProjectLink(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "projectIds");		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmProductProjectLink> qw = QueryTools.initQueryWrapper(XmProductProjectLink.class , params);
		List<Map<String,Object>> datas = xmProductProjectLinkService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmProductProjectLink列表
		
	}
	
 

	@ApiOperation( value = "新增一条产品与项目的关联关系表，一般由产品经理挂接项目到产品上信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProductProjectLink.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmProductProjectLink(@RequestBody XmProductProjectLink xmProductProjectLink) {

			User user = LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(xmProductProjectLink.getProductId())){
				return Result.error("productId-0","产品编号不能为空");
			}
			if(!StringUtils.hasText(xmProductProjectLink.getProjectId())){
				return Result.error("projectId-0","项目编号不能为空");
			}
			 if(xmProductProjectLinkService.selectOneObject(xmProductProjectLink) !=null ){
				return Result.error("已加入，无需再添加");
				
			}
			if(!xmGroupService.checkUserIsProductAdm(xmProductProjectLink.getProductId(),user.getUserid())){
				if(!xmGroupService.checkUserIsProjectAdm(xmProductProjectLink.getProjectId(),user.getUserid())){
					return Result.error("not-pm","您不是项目管理人员、也不是产品管理人员，无权关联");
				}
			};
			xmProductProjectLink.setCtime(new Date());
			xmProductProjectLink.setLinkStatus("1");
			if(xmProductProjectLink.getSeq()==null){
				xmProductProjectLink.setSeq(999);
			}

			xmProductProjectLink.setCuserid(user.getUserid());
			xmProductProjectLink.setCusername(user.getUsername());
			xmProductProjectLinkService.insert(xmProductProjectLink);
		return Result.ok();
	}

	@ApiOperation( value = "删除一条产品与项目的关联关系表，一般由产品经理挂接项目到产品上信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmProductProjectLink(@RequestBody XmProductProjectLink xmProductProjectLink){

			User user = LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(xmProductProjectLink.getProductId())){
				return Result.error("productId-0","产品编号不能为空");
			}
			if(!StringUtils.hasText(xmProductProjectLink.getProjectId())){
				return Result.error("projectId-0","项目编号不能为空");
			}
			if(!xmGroupService.checkUserIsProductAdm(xmProductProjectLink.getProductId(),user.getUserid())){
				if(!xmGroupService.checkUserIsProjectAdm(xmProductProjectLink.getProjectId(),user.getUserid())){
					return Result.error("not-pm","您不是项目管理人员、也不是产品管理人员，无权取消关联");
				}
			};
			List<XmTask> tasks=xmTaskService.listTenTaskByProjectIdAndProductId(xmProductProjectLink.getProjectId(),xmProductProjectLink.getProductId());
			if(tasks!=null && tasks.size()>0){
				return Result.error("tasks-not-0","存在至少"+tasks.size()+"个任务与产品关联，不能移出.关联任务【"+tasks.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
			}
			xmProductProjectLinkService.deleteByPk(xmProductProjectLink);
		return Result.ok();
		
	}
	
	/**
	@ApiOperation( value = "根据主键修改一条产品与项目的关联关系表，一般由产品经理挂接项目到产品上信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProductProjectLink.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmProductProjectLink(@RequestBody XmProductProjectLink xmProductProjectLink) {

			xmProductProjectLinkService.updateByPk(xmProductProjectLink);
		
	}
	*/

	@ApiOperation( value = "根据主键批量修改修改任务中的某些字段信息",notes="editXmMenu")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmMenu.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
 	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> map) {

			List<Map<String,Object>> ids= (List<Map<String, Object>>) map.get("$pks");

			if(ids==null || ids.size()==0){
				Result.error("ids-0","ids不能为空");
			}

			Set<String> fields=new HashSet<>();
			fields.add("productId");
			fields.add("projectId");
			for (String fieldName : map.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=map.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->map.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
			}
			this.xmProductProjectLinkService.editSomeFields(map);
		return Result.ok();
		
	}
	

	@ApiOperation( value = "根据主键列表批量删除产品与项目的关联关系表，一般由产品经理挂接项目到产品上信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmProductProjectLink(@RequestBody List<XmProductProjectLink> xmProductProjectLinks) {
		if(xmProductProjectLinks.size()>1){
			return Result.error("batchDel-gt-1-err","一次只能删除一条记录");
		}
		return delXmProductProjectLink(xmProductProjectLinks.get(0));
		
	}
}
