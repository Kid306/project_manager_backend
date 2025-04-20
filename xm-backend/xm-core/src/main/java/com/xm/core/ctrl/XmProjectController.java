package com.xm.core.ctrl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.audit.log.client.annotation.AuditLog;
import com.mdp.audit.log.client.annotation.OperType;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.EntityUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.cache.DeptRedisCacheService;
import com.mdp.safe.client.entity.Dept;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sensitive.SensitiveWordService;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmProduct;
import com.xm.core.entity.XmProductProjectLink;
import com.xm.core.entity.XmProject;
import com.xm.core.service.*;
import com.xm.core.vo.XmProjectCopyVo;
import com.xm.core.vo.XmProjectVo;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

/**
 * url编制采用rest风格,如对XM.xm_project xm_project的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmProject/add <br>
 *  查询: xm/xmProject/list<br>
 *  模糊查询: xm/xmProject/listKey<br>
 *  修改: xm/xmProject/edit <br>
 *  删除: xm/xmProject/del<br>
 *  批量删除: xm/xmProject/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmProject 表 XM.xm_project 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmProjectController")
@RequestMapping(value="/xm/core/xmProject")
@Api(tags={"xm_project操作接口"})
public class XmProjectController {
	
	static Log logger=LogFactory.getLog(XmProjectController.class);
	
	@Autowired
	private XmProjectService xmProjectService;

	@Autowired
	private XmProductService xmProductService;

	@Autowired
	private XmGroupService groupService;


	@Autowired
	private XmRecordService xmRecordService;


	@Autowired
	PushNotifyMsgService notifyMsgService;

	@Value("${mdp.platform-branch-id:platform-branch-001}")
	String platformBranchId="platform-branch-001";

	@Autowired
	XmProjectStateService xmProjectStateService;

	@Autowired
	private XmTaskService xmTaskService;
	@Autowired
	SensitiveWordService sensitiveWordService;

	@Autowired
	DeptRedisCacheService deptRedisCacheService;


	Map<String,Object> fieldsMap = BaseUtils.toMap(new XmProject());

	@ApiOperation( value = "查询xm_project信息列表",notes="listXmProject,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmProject.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderyBy = sex desc, student_id desc",required=false)
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmProject.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmProject(@ApiIgnore @RequestParam Map<String,Object> params){
		IPage page=QueryTools.initPage(params);
		String id= (String) params.get("id");
		User user = LoginUtils.getCurrentUserInfo();
		String queryScope= (String) params.get("queryScope");
		if(StringUtils.hasText(id)){
			queryScope="projectId";
			params.put("queryScope",queryScope);
		}
		if(!StringUtils.hasText(queryScope)){//如果是机构管理员，可以查询全机构数据，否则只能查自己相关的数据
			queryScope="compete";
			params.put("queryScope",queryScope);
		}
		if("branchId".equals(queryScope)&&!"1".equals(params.get("isTpl"))){//如果是机构管理员，可以查询全机构数据，否则只能查自己相关的数据
			if(!LoginUtils.isBranchAdmin(user.getBranchId())){
				return Result.error("no-qx-query-branch-scope-project","您不是机构管理员，不允许查询整个机构的项目");
			}
		}else if("createUserid".equals(queryScope)){
			params.put("createUserid",user.getUserid());
		}else if("pmUserid".equals(queryScope)){
			params.put("pmUserid",user.getUserid());
		}else if("assUserid".equals(queryScope)){
			params.put("assUserid",user.getUserid());
		}else if("admUserid".equals(queryScope)){
			params.put("admUserid",user.getUserid());
		}else if("compete".equals(queryScope)){
		}else if("projectId".equals(queryScope)){
			if(!StringUtils.hasText(id)){
				return Result.error("项目编号id必输");
			}
		}else if(queryScope.indexOf("myExecuserStatus")>=0){
			params.put("myExecuserStatus",queryScope.substring("myExecuserStatus".length()));
		}

		String linkProductId= (String) params.get("linkProductId");
		if(ObjectTools.isNotEmpty(linkProductId)){
			XmProduct xmProduct=this.xmProductService.getProductFromCache(linkProductId);
			if("1".equals(xmProduct.getIsTpl())){
				params.put("isTpl","1");
			}
		}
		if("branchId".equals(queryScope)){//如果是机构管理员，可以查询全机构数据，否则只能查自己相关的数据
			params.put("res.branchId",user.getBranchId());
		}else{
			params.remove("branchId");
		}

		QueryWrapper<XmProject> qw = QueryTools.initQueryWrapper(XmProject.class , params);
		if(!StringUtils.isEmpty(user.getDeptid())){
			Dept dept=deptRedisCacheService.getDept(user.getDeptid());
			if(dept==null){
				params.put("myIdPath","");
			}else{
				params.put("myIdPath",dept.getIdPath());
			}

		}
		params.put("myDeptid",user.getDeptid());
		params.put("linkBranchId",user.getBranchId());
		params.put("myUserid",user.getUserid());
		List<Map<String,Object>> datas = xmProjectService.selectListMapByWhere(page,qw,params);	//列出XmProject列表
		return Result.ok().setData(datas).setTotal(page.getTotal());
		
		
		
	}

	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiEntityParams( value = XmProject.class, props={ }, remark = "项目表", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmProject.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmProjectMap) {

			List<String> ids= (List<String>) xmProjectMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("$pks-0","ids不能为空");
			}

			if(ids.size()>1){
				return Result.error("$pks-1","一次只能更新一个项目");
			}
			Set<String> fields=new HashSet<>();
			fields.add("id");
			fields.add("code");
			fields.add("bizFlowState");
			for (String fieldName : xmProjectMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmProjectMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmProjectMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
			}
			User user=LoginUtils.getCurrentUserInfo();
			List<XmProject> xmProjectsDb=xmProjectService.selectListByIds(ids);
			if(xmProjectsDb==null ||xmProjectsDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			XmProject xmProject=BaseUtils.fromMap(xmProjectMap,XmProject.class);
			XmProject xmProjectDb=xmProjectsDb.get(0);
			if(!LoginUtils.isBranchAdmin(xmProjectDb.getBranchId())  && !groupService.checkUserIsProjectAdm(xmProjectDb,user.getUserid())){
				return Result.error("noqx-all","您无权限操作，产品管理人员、机构管理员有权限更新项目基础信息。");
			}
			if(xmProjectMap.containsKey("assUserid")){
				String assUserid= (String) xmProjectMap.get("assUserid");
				String assUsername= (String) xmProjectMap.get("assUsername");
				if(StringUtils.hasText(assUserid)){
					if(!user.getUserid().equals(xmProjectDb.getPmUserid()) && !user.getUserid().equals(xmProjectDb.getAdmUserid())){
						return Result.error("noqx-pm","您无权限操作。项目经理、总监可以委任项目副经理。");
					}
				}
			}
			if(xmProjectMap.containsKey("pmUserid")){
				String pmUserid= (String) xmProjectMap.get("pmUserid");
				String pmUsername= (String) xmProjectMap.get("pmUsername");
				if(StringUtils.hasText(pmUserid)){
					if(!user.getUserid().equals(xmProjectDb.getAdmUserid())){
						return Result.error("noqx-adm","您无权限操作，项目总监可以委任项目经理。");
					}
				}
			}
			if(xmProjectMap.containsKey("admUserid")){
				String admUserid= (String) xmProjectMap.get("admUserid");
				String admUsername= (String) xmProjectMap.get("admUsername");
				if(StringUtils.hasText(admUserid)){
					if(!LoginUtils.isBranchAdmin(xmProjectDb.getBranchId()) && !user.getUserid().equals(xmProjectDb.getAdmUserid())){
						return Result.error("noqx-adm","您无权限操作，项目总监、机构管理员可以委任项目总监。");
					}
				}
			}
			this.xmProjectService.editSomeFields(xmProjectMap);
			this.xmRecordService.addXmProjectRecord(xmProjectDb.getId(),"修改项目信息", ChangeLogService.getChangeLogsString(xmProjectMap,xmProjectDb));
			this.xmProjectService.clearProject(xmProjectDb.getId());
			if(StringUtils.hasText(xmProject.getPmUserid()) && !xmProject.getPmUserid().equals(xmProjectDb.getPmUserid())){
				notifyMsgService.pushMsg(user,xmProject.getPmUserid(),xmProject.getPmUsername(),"您成为项目【"+xmProjectDb.getName()+"】的项目经理，请及时跟进。",null);

			}
			if(StringUtils.hasText(xmProject.getAssUserid()) && !xmProject.getAssUserid().equals(xmProjectDb.getAssUserid())){
				notifyMsgService.pushMsg(user,xmProject.getAssUserid(),xmProject.getAssUsername(),"您成为项目【"+xmProjectDb.getName()+"】的副经理，请及时跟进。",null);

			}
			if(StringUtils.hasText(xmProject.getAdmUserid()) && !xmProject.getAdmUserid().equals(xmProjectDb.getAdmUserid())){
				notifyMsgService.pushMsg(user,xmProject.getAdmUserid(),xmProject.getAdmUsername(),"您成为项目【"+xmProjectDb.getName()+"】的项目总监，请及时跟进。",null);
			}
			//
		return Result.ok();
		
	}

	@ApiOperation( value = "新增一条xm_project信息",notes="addXmProject,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProject.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	//@HasQx(value = "xm_core_xmProject_add",name = "创建项目",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	public Result addXmProject(@RequestBody XmProjectVo xmProjectVo) {

			if(!StringUtils.hasText(xmProjectVo.getName())){
				return Result.error("name-0","项目名称不能为空");
			}
			if(xmProjectVo.getLinks()!=null && xmProjectVo.getLinks().size()>0){
				for (XmProductProjectLink link : xmProjectVo.getLinks()) {
					if(!StringUtils.hasText(link.getProductId())){
						return Result.error("productId-0","关联的产品编号不能为空");
					}
				}
			}
			Set<String> words=sensitiveWordService.getSensitiveWord(xmProjectVo.getName());
			if(words!=null && words.size()>0){
				return Result.error("name-sensitive-word","名字有敏感词"+words+",请修改后再提交");
			}
			words=sensitiveWordService.getSensitiveWord(xmProjectVo.getBaseRemark());
			if(words!=null && words.size()>0){
				return Result.error("remark-sensitive-word","备注中有敏感词"+words+",请修改后再提交");
			}

			words=sensitiveWordService.getSensitiveWord(xmProjectVo.getAssessRemarks());
			if(words!=null && words.size()>0){
				return Result.error("assessRemarks-sensitive-word","备注中有敏感词"+words+",请修改后再提交");
			}
			User user = LoginUtils.getCurrentUserInfo();
				xmProjectService.saveProject(xmProjectVo);
				notifyMsgService.pushMsg(user,xmProjectVo.getPmUserid(),xmProjectVo.getPmUsername(),"您成为项目【"+xmProjectVo.getName()+"】的项目经理，请及时跟进。",null);
				if(StringUtils.hasText(xmProjectVo.getAssUserid()) && !xmProjectVo.getAssUserid().equals(xmProjectVo.getPmUserid())){
					notifyMsgService.pushMsg(user,xmProjectVo.getAssUserid(),xmProjectVo.getAssUsername(),"您成为项目【"+xmProjectVo.getName()+"】的副经理、助理，请及时跟进。",null);

				}
				if(StringUtils.hasText(xmProjectVo.getAdmUserid()) && !xmProjectVo.getAdmUserid().equals(xmProjectVo.getPmUserid())){
					notifyMsgService.pushMsg(user,xmProjectVo.getAdmUserid(),xmProjectVo.getAdmUsername(),"您成为项目【"+xmProjectVo.getName()+"】的项目总监，请及时跟进。",null);
				}
				xmProjectService.clearProject(xmProjectVo.getId());
			xmProjectStateService.loadTasksToXmProjectState(xmProjectVo.getId());
				
			
		return Result.ok().setData(xmProjectVo);
		
	}
	@ApiOperation( value = "从回收站恢复项目",notes="unDelXmProject,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@RequestMapping(value="/unDel",method=RequestMethod.POST)
	//@HasQx(value = "xm_core_xmProject_unDel",name = "从回收站恢复项目",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	public Result unDelXmProject(@RequestBody XmProject xmProject){

			User user= LoginUtils.getCurrentUserInfo();
			XmProject xmProjectDb=this.xmProjectService.getProjectFromCache(xmProject.getId());
			if(xmProjectDb==null){
				return Result.error("项目不存在");
			}
			if(!user.getBranchId().equals(xmProjectDb.getBranchId())){
				return Result.error("branchId-not-right","该项目不属于您的组织，不允许您进行恢复");
			}
			if(!"1".equals(xmProjectDb.getDel())){
				return Result.error("status-not-0","该项目不属于删除状态，不允许恢复");
			}
			if(LoginUtils.isBranchAdmin(xmProjectDb.getBranchId()) || this.groupService.checkUserIsProjectAdm(xmProjectDb,user.getUserid())){
				XmProject xmProjectUpdate=new XmProject();
				xmProjectUpdate.setId(xmProjectDb.getId());
				xmProjectUpdate.setDel("0");
				xmProjectUpdate.setLtime(new Date());
				xmProjectService.updateSomeFieldByPk(xmProjectUpdate);
				xmProjectService.clearProject(xmProject.getId());
				xmRecordService.addXmProjectRecord(xmProject.getId(),"项目-从回收站恢复项目","从回收站恢复项目【"+xmProjectDb.getName()+"】");

			}else {
				return Result.error("您不是该项目管理人员，无权从回收站恢复项目");
			}

		return Result.ok();
		
	}
	@ApiOperation( value = "删除一条xm_project信息",notes="delXmProject,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	//@HasQx(value = "xm_core_xmProject_del",name = "删除项目",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	public Result delXmProject(@RequestBody XmProject xmProject){

			User user= LoginUtils.getCurrentUserInfo();
			if(xmProject==null || StringUtils.isEmpty(xmProject.getId())){
				return Result.error("id-0","项目编号不能为空");
			}
			XmProject xmProjectDb=this.xmProjectService.getProjectFromCache(xmProject.getId());
			if(xmProjectDb==null){
				return Result.error("项目不存在");
			}
			if(!user.getBranchId().equals(xmProjectDb.getBranchId())){
				return Result.error("branchId-not-right","该项目不属于您的组织，不允许您进行删除");
			}
			if(LoginUtils.isBranchAdmin(xmProjectDb.getBranchId()) || this.groupService.checkUserIsProjectAdm(xmProjectDb,user.getUserid())){
				XmProject xmProjectUpdate=new XmProject();
				xmProjectUpdate.setId(xmProjectDb.getId());
				xmProjectUpdate.setDel("1");
				xmProjectUpdate.setLtime(new Date());
				xmProjectService.updateSomeFieldByPk(xmProjectUpdate);
				xmProjectService.clearProject(xmProject.getId());
				xmRecordService.addXmProjectRecord(xmProject.getId(),"项目-删除","删除项目【"+xmProjectDb.getName()+"】");

			}else {
				return Result.error("您不是该项目管理人员，无权删除");
			}

		return Result.ok();
		
	}
	
	@ApiOperation( value = "根据主键修改一条xm_project信息",notes="editXmProject")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmProject.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmProject_editAssess",name = "修改项目估算",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/editAssess",method=RequestMethod.POST)
	public Result editXmProjectAssess(@RequestBody XmProject xmProject) {


			User user= LoginUtils.getCurrentUserInfo();
			if( !StringUtils.hasText(xmProject.getId())){
				return Result.error("id-0","项目编号不能为空");
			}
			XmProject xmProjectDb=this.xmProjectService.getProjectFromCache(xmProject.getId());
			if(xmProjectDb==null){
				return Result.error("data-0","项目不存在");
			}
			boolean isPm=groupService.checkUserIsProjectAdm(xmProjectDb,user.getUserid());
			if(   !isPm && !LoginUtils.isBranchAdmin(xmProjectDb.getBranchId())) {
				return Result.error("noqx","您无权操作！项目管理人员才能修改项目基础数据");
			}
			xmProjectService.updateByPk(xmProject);
			xmProjectService.clearProject(xmProject.getId());
			xmRecordService.addXmProjectRecord(xmProject.getId(),"项目-项目估算","修改项目【"+xmProjectDb.getName()+"】的预算数据");
			
		return Result.ok().setData(xmProject);
		
	}
	@ApiOperation( value = "创建项目代号",notes="createProjectCode")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmProject.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmProject_createProjectCode",name = "创建项目代号",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/createProjectCode",method=RequestMethod.POST)
	public Result createProjectCode() {

			User user= LoginUtils.getCurrentUserInfo();
			String code=this.xmProjectService.createProjectCode(user.getBranchId());
			
		return Result.ok().setData(code);
		
	}
	@ApiOperation( value = "根据主键修改一条xm_project信息",notes="editXmProject")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmProject.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmProject_editStatus",name = "修改项目状态",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/editStatus",method=RequestMethod.POST)
	public Result editXmProjectStatus(@RequestBody XmProject xmProject) {


			User user= LoginUtils.getCurrentUserInfo();
			if( !StringUtils.hasText(xmProject.getId())){
				return Result.error("id-0","项目编号不能为空");
			}
			XmProject xmProjectDb=this.xmProjectService.getProjectFromCache(xmProject.getId());
			if(xmProjectDb==null){
				return Result.error("data-0","项目不存在");
			}
 			boolean isPm=groupService.checkUserIsProjectAdm(xmProjectDb,user.getUserid());
			if( !isPm && !LoginUtils.isBranchAdmin(xmProjectDb.getBranchId())) {
 				return Result.error("noqx","您无权操作！项目管理人员才能修改项目状态");
			}
			xmProjectService.updateStatus(xmProject);
			xmProjectService.clearProject(xmProject.getId());
			xmRecordService.addXmProjectRecord(xmProject.getId(),"项目-项目状态变更","修改项目【"+xmProjectDb.getName()+"】的状态");


			
		return Result.ok().setData(xmProject);
		
	}
	
	@ApiOperation( value = "根据主键修改一条xm_project信息",notes="editXmProject")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmProject.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmProject_editBudget",name = "修改项目预算",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/editBudget",method=RequestMethod.POST)
	public Result editBudget(@RequestBody XmProject xmProject) {

			User user= LoginUtils.getCurrentUserInfo();
			if( !StringUtils.hasText(xmProject.getId())){
				return Result.error("id-0","项目编号不能为空");
			}
			XmProject xmProjectDb=this.xmProjectService.getProjectFromCache(xmProject.getId());
			if(xmProjectDb==null){
				return Result.error("data-0","项目不存在");
			}
			boolean isPm=groupService.checkUserIsProjectAdm(xmProjectDb,user.getUserid());
			if( !isPm && !LoginUtils.isBranchAdmin(xmProjectDb.getBranchId())) {
				return Result.error("noqx","您无权操作！项目管理人员才能修改项目预算");
			}
			xmProjectService.editBudget(xmProject);
			xmProjectService.clearProject(xmProject.getId());
			xmRecordService.addXmProjectRecord(xmProject.getId(),"项目-项目预算","修改项目【"+xmProjectDb.getName()+"】的预算数据");

			
		return Result.ok().setData(xmProject);
		
	}

	/**
	 *
	 * @param xmProject
	 * @return
	@ApiOperation( value = "根据主键修改一条xm_project信息",notes="editXmProject")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProject.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmProject_edit",name = "修改项目基础信息",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmProject(@RequestBody XmProject xmProject) {

			User user= LoginUtils.getCurrentUserInfo();
			if( !StringUtils.hasText(xmProject.getId())){
				return Result.error("id-0","项目编号不能为空");
			}
			XmProject xmProjectDb=this.xmProjectService.getProjectFromCache(xmProject.getId());
			if(xmProjectDb==null){
				return Result.error("data-0","项目不存在");
			}
			boolean isPm=groupService.checkUserIsProjectAdm(xmProjectDb,user.getUserid());
			if( !isPm && !LoginUtils.isBranchAdmin(xmProjectDb.getBranchId())) {
				return Result.error("noqx","您无权操作！项目管理人员才能修改项目基础信息");
			}
			xmProjectService.updateProject(xmProject);
			if(StringUtils.hasText(xmProject.getPmUserid()) && !xmProject.getPmUserid().equals(xmProjectDb.getPmUserid())){
				notifyMsgService.pushMsg(user,xmProject.getPmUserid(),xmProject.getPmUsername(),"您成为项目【"+xmProjectDb.getName()+"】的项目经理，请及时跟进。");

			}
 			if(StringUtils.hasText(xmProject.getAssUserid()) && !xmProject.getAssUserid().equals(xmProjectDb.getAssUserid())){
				notifyMsgService.pushMsg(user,xmProject.getAssUserid(),xmProject.getAssUsername(),"您成为项目【"+xmProjectDb.getName()+"】的副经理、助理，请及时跟进。");

			}
			if(StringUtils.hasText(xmProject.getAdmUserid()) && !xmProject.getAdmUserid().equals(xmProjectDb.getAdmUserid())){
				notifyMsgService.pushMsg(user,xmProject.getAdmUserid(),xmProject.getAdmUsername(),"您成为项目【"+xmProjectDb.getName()+"】的项目总监，请及时跟进。");
			}
			xmProjectService.clearProject(xmProject.getId());
			xmRecordService.addXmProjectRecord(xmProject.getId(),"项目-修改","修改项目【"+xmProjectDb.getName()+"】的基础信息", JSON.toJSONString(xmProject), JSON.toJSONString(xmProjectDb));

		
	}

	 */


	@ApiOperation( value = "存为模板",notes="editXmProject")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmProject.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmProject_copy_to",name = "存为新项目",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/copyTo",method=RequestMethod.POST)
	public Result copyTo(@RequestBody XmProjectCopyVo xmProject) {

			User user= LoginUtils.getCurrentUserInfo();
			if( !StringUtils.hasText(xmProject.getId())){
				return Result.error("id-0","请上送原项目编号参数id");
			}
			if( !StringUtils.hasText(xmProject.getName())){
				return Result.error("name-0","请上送新项目名称");
			}
			if(StringUtils.hasText(xmProject.getCode())){
				XmProject pq=new XmProject();
				pq.setBranchId(user.getBranchId());
				pq.setCode(xmProject.getCode());
				List<XmProject> xmProjectList=this.xmProjectService.selectListByWhere(pq);
				if(xmProjectList!=null && xmProjectList.size()>0){
					return Result.error("code-exists","项目代号【"+xmProject.getCode()+"】已存在，，请重新输入新的项目代号，如果为空，后台自动生成");
				}
			}
			XmProject xmProjectDb=this.xmProjectService.getProjectFromCache(xmProject.getId());
			if(xmProjectDb==null){
				return Result.error("项目不存在");
				
			}
			XmProject xmProjectTo=this.xmProjectService.copyProject(user,xmProject);

			xmProjectStateService.loadTasksToXmProjectState(xmProjectTo.getId());
			xmRecordService.addXmProjectRecord(xmProjectTo.getId(),"项目-通过拷贝创建新项目","拷贝项目【"+xmProjectTo.getName()+"】,创建新的项目【】");

		return Result.ok().setData(xmProjectTo);
		
	}

	
	/**
	@ApiOperation( value = "根据主键列表批量删除xm_project信息",notes="batchDelXmProject,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmProject(@RequestBody List<XmProject> xmProjects) {
		
		
		
			xmProjectService.batchDelete(xmProjects);
		return Result.ok();
		
	} 
	*/
	


	/**
	 * 流程审批过程中回调该接口，更新业务数据
	 * 如果发起流程时上送了restUrl，则无论流程中是否配置了监听器都会在流程发生以下事件时推送数据过来
	 * eventName: PROCESS_STARTED 流程启动完成 全局
	 *            PROCESS_COMPLETED 流程正常结束 全局
	 *            PROCESS_CANCELLED 流程删除 全局
	 *            create 人工任务启动
	 *            complete 人工任务完成  
	 *            assignment 人工任务分配给了具体的人
	 *            delete 人工任务被删除
	 *            
	 * 其中 create/complete/assignment/delete事件是需要在模型中人工节点上配置了委托代理表达式 ${taskBizCallListener}才会推送过来。
	 * 在人工任务节点上配置 任务监听器  建议事件为 complete,其它assignment/create/complete/delete也可以，一般建议在complete,委托代理表达式 ${taskBizCallListener}
	 * 
	 * @param flowVars {flowBranchId,agree,procInstId,assignee,actId,taskName,mainTitle,branchId,bizKey,commentMsg,eventName,modelKey} 等 
	 * @return 如果tips.isOk==false，将影响流程提交
	 **/
	//@AuditLog(firstMenu="办公平台",secondMenu="项目管理",func="processApprova",funcDesc="项目立项等审批",operType=OperType.UPDATE)
	@RequestMapping(value="/processApprova",method=RequestMethod.POST)
	public Result processApprova( @RequestBody Map<String,Object> flowVars){
		this.xmProjectService.processApprova(flowVars);
		return Result.ok();
	}
}
