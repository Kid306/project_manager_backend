package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.*;
import com.xm.core.queue.XmTaskSumParentsPushService;
import com.xm.core.service.*;
import com.xm.rpt.XmRptQueryServiceApi;
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

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.map;

/**
 * url编制采用rest风格,如对xm_workload 工时登记表的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmWorkload/add <br>
 *  查询: core/xmWorkload/list<br>
 *  模糊查询: core/xmWorkload/listKey<br>
 *  修改: core/xmWorkload/edit <br>
 *  删除: core/xmWorkload/del<br>
 *  批量删除: core/xmWorkload/batchDel<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmWorkload 表 xm_workload 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmWorkloadController")
@RequestMapping(value="/xm/core/xmWorkload")
@Api(tags={"工时登记表操作接口"})
public class XmWorkloadController {
	
	static Logger logger =LoggerFactory.getLogger(XmWorkloadController.class);
	
	@Autowired
	private XmWorkloadService xmWorkloadService;

	@Autowired
	XmTaskService xmTaskService;

	@Autowired
	XmGroupService xmGroupService;
	@Autowired
	XmMenuService xmMenuService;


	@Autowired
	XmTestCaseService xmTestCaseService;

	@Autowired
	XmProjectService xmProjectService;

	@Autowired
	XmTestPlanCaseService xmTestPlanCaseService;


	@Autowired
	XmQuestionService xmQuestionService;

	@Autowired
	XmTaskSbillService xmTaskSbillService;

	@Autowired
	XmTaskSumParentsPushService pushService;

	@Autowired
	XmRptQueryServiceApi rptQueryServiceApi;

	@Autowired
	XmRecordService xmRecordService;

	Map<String,Object> fieldsMap = BaseUtils.toMap(new XmWorkload());
 
	
	@ApiOperation( value = "查询工时登记表信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response= XmWorkload.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmWorkload(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");
		RequestUtils.transformArray(params, "wstatuses");
		RequestUtils.transformArray(params, "sstatuses");		
		IPage page=QueryTools.initPage(params);
		String taskId= (String) params.get("taskId");
		String sbillId= (String) params.get("sbillId");
		String projectId= (String) params.get("projectId");
		String userid= (String) params.get("userid");
		User user=LoginUtils.getCurrentUserInfo();
		params.put("linkBranchId",user.getBranchId());
		QueryWrapper<XmWorkload> qw = QueryTools.initQueryWrapper(XmWorkload.class , params);
		List<Map<String,Object>> datas = xmWorkloadService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmWorkload列表

	}
	@ApiOperation( value = "查询项目每日登记工时情况",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmWorkload.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listProjectWorkloadSetDay",method=RequestMethod.GET)
	public Result listProjectWorkloadSetDay(@ApiIgnore @RequestParam Map<String,Object> params){
		
				
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("linkBranchId",user.getBranchId());
		QueryWrapper<XmWorkload> qw = QueryTools.initQueryWrapper(XmWorkload.class , params);
		List<Map<String,Object>>	datas = xmWorkloadService.listProjectWorkloadSetDay(page,qw,params);	//列出XmWorkload列表
		return Result.ok().setData(datas);
	}


	@ApiOperation( value = "查询项目每月登记工时情况",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmWorkload.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listProjectWorkloadSetMonth",method=RequestMethod.GET)
	public Result listProjectWorkloadSetMonth(@ApiIgnore @RequestParam Map<String,Object> params){
		
				
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("linkBranchId",user.getBranchId());
		QueryWrapper<XmWorkload> qw = QueryTools.initQueryWrapper(XmWorkload.class , params);
		List<Map<String,Object>>	datas = xmWorkloadService.listProjectWorkloadSetMonth(page,qw,params);	//列出XmWorkload列表
		return Result.ok().setData(datas);
	}

	@ApiOperation( value = "查询平均人效按日期变化趋势",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmWorkload.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listSumWorkloadHumanEffectTrend",method=RequestMethod.GET)
	public Result listSumWorkloadHumanEffectTrend(@ApiIgnore @RequestParam Map<String,Object> params){


		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("linkBranchId",user.getBranchId());
		QueryWrapper<XmWorkload> qw = QueryTools.initQueryWrapper(XmWorkload.class , params);
		List<Map<String,Object>>	datas = rptQueryServiceApi.listSumWorkloadHumanEffectTrend(page,qw,params);	//列出XmWorkload列表
		return Result.ok().setData(datas);
	}

	@ApiOperation( value = "查询平均人效排行榜",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmWorkload.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listSumWorkloadHumanEffectSort",method=RequestMethod.GET)
	public Result listSumWorkloadHumanEffectSort(@ApiIgnore @RequestParam Map<String,Object> params){


		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("linkBranchId",user.getBranchId());
		QueryWrapper<XmWorkload> qw = QueryTools.initQueryWrapper(XmWorkload.class , params);
		List<Map<String,Object>>	datas = rptQueryServiceApi.listSumWorkloadHumanEffectSort(page,qw,params);	//列出XmWorkload列表
		return Result.ok().setData(datas);
	}
	@ApiOperation( value = "按任务及报工人查询待确认工时",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmWorkload.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/ListGroupByTaskIdAndUserid",method=RequestMethod.GET)
	public Result ListGroupByTaskIdAndUserid(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");
		RequestUtils.transformArray(params, "wstatuses");
		RequestUtils.transformArray(params, "sstatuses");		
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("linkBranchId",user.getBranchId());
		String queryScope= (String) params.get("queryScope");
		if("my".equals(queryScope)){
			params.put("userid",user.getUserid());
		}
		List<Map<String,Object>>	datas = xmWorkloadService.ListGroupByTaskIdAndUserid(params);	//列出XmWorkload列表
		return Result.ok().setData(datas);
	}

	@ApiOperation( value = "按任务及报工人查询待确认工时",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response= XmWorkload.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/ListGroupByTaskIdAndUseridToSet",method=RequestMethod.GET)
	public Result ListGroupByTaskIdAndUseridToSet(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");
		RequestUtils.transformArray(params, "wstatuses");
		RequestUtils.transformArray(params, "sstatuses");		
		IPage page=QueryTools.initPage(params);
		String queryScope= (String) params.get("queryScope");
		User user=LoginUtils.getCurrentUserInfo();
		params.put("linkBranchId",user.getBranchId());
		if("my".equals(queryScope)){
			params.put("userid",user.getUserid());
		}
		List<Map<String,Object>>	datas = xmWorkloadService.ListGroupByTaskIdAndUseridToSet(params);	//列出XmWorkload列表
		return Result.ok().setData(datas);
	}


	@ApiOperation( value = "新增一条工时登记表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response= XmWorkload.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmWorkload(@RequestBody XmWorkload xmWorkload) {

			User user= LoginUtils.getCurrentUserInfo();
			xmWorkload.setId(xmWorkloadService.createKey("id"));
			xmWorkload.setCuserid(user.getUserid());
			xmWorkload.setCusername(user.getUsername());
			if(!StringUtils.hasText(xmWorkload.getUserid())||xmWorkload.getUserid().equals(user.getUserid())){
				xmWorkload.setUserid(user.getUserid());
				xmWorkload.setUsername(user.getUsername());
				xmWorkload.setUbranchId(user.getBranchId());
			}else{
				if(!StringUtils.hasText(xmWorkload.getUbranchId())){
					return Result.error("ubranchId-0","请上送用户归属机构号");
				}
			}
			//报工类型1-任务，2-缺陷，3-测试用例设计，4-测试执行
			if(!StringUtils.hasText(xmWorkload.getBizType())) {
				return Result.error("bizType-0","请上送报工类型");
			}
			if("1".equals(xmWorkload.getBizType())){
				if(!StringUtils.hasText(xmWorkload.getTaskId())){
					return Result.error("taskId-0","请上送任务编号");
				}
			}
			if("2".equals(xmWorkload.getBizType())){
				if(!StringUtils.hasText(xmWorkload.getBugId())){
					return Result.error("bugId-0","请上送缺陷编号");
				}
			}
			if("3".equals(xmWorkload.getBizType())){
				if(!StringUtils.hasText(xmWorkload.getCaseId())){
					return Result.error("caseId-0","请上送测试用例编号");
				}
			}
			if("4".equals(xmWorkload.getBizType())){
				if(!StringUtils.hasText(xmWorkload.getPlanId())){
					return Result.error("planId-0","请上送测试计划编号");
				}
				if(!StringUtils.hasText(xmWorkload.getCaseId())){
					return Result.error("caseId-0","请上送测试用例编号");
				}
			}

			if(!StringUtils.hasText(xmWorkload.getUserid())){
				return Result.error("userid-0","请上送工作人员编号");
			}
			if(!StringUtils.hasText(xmWorkload.getUbranchId())){
				return Result.error("ubranchId-0","请上送工作人员归属机构");
			}

			if(!StringUtils.hasText(xmWorkload.getBizDate())) {
				return Result.error("bizDate-0","请上送日期");
			}
			if(xmWorkload.getWorkload()==null) {
				return Result.error("workload-0","工时不能为空");
			}

			if(xmWorkload.getWorkload().compareTo(BigDecimal.ZERO)==0) {
				return Result.error("workload-0","工时不能为0");
			}
			XmWorkload xmWorkloadCount=new XmWorkload();
			//xmWorkloadCount.setUserid(user.getUserid());
			xmWorkloadCount.setBizDate(xmWorkload.getBizDate());
			//报工类型1-任务，2-缺陷，3-测试用例设计，4-测试执行
			if("1".equals(xmWorkload.getBizType())){
				xmWorkloadCount.setTaskId(xmWorkload.getTaskId());
			}
			if("2".equals(xmWorkload.getBizType())){
				xmWorkloadCount.setBugId(xmWorkload.getBugId());
			}
			if("3".equals(xmWorkload.getBizType())){
				xmWorkloadCount.setCaseId(xmWorkload.getCaseId());
			}
			if("4".equals(xmWorkload.getBizType())){
				xmWorkloadCount.setPlanId(xmWorkload.getPlanId());
				xmWorkloadCount.setCaseId(xmWorkload.getCaseId());
			}
			xmWorkloadCount.setBizType(xmWorkload.getBizType());
			long count=this.xmWorkloadService.countByWhere(xmWorkloadCount);
			if(count>0){
				return Result.error("data-1","当前工作项今天已经报工");
			}
			//报工类型1-任务，2-缺陷，3-测试用例设计，4-测试执行
			if("1".equals(xmWorkload.getBizType())){
				XmTask xmTaskDb=this.xmTaskService.selectOneObject(new XmTask(xmWorkload.getTaskId()));
				if(xmTaskDb==null ){
					return Result.error("data-0","任务已不存在");
				}
				if("1".equals(xmTaskDb.getNtype())){
					return Result.error("ntype-1",xmTaskDb.getName()+"为计划，不是任务，不用登记工时");
				}
				if("3".equals(xmTaskDb.getTaskState())){
					return Result.error("taskState-3",xmTaskDb.getName()+"已结算完毕，不能再提交工时");
				}
				//待他人报工，需要检查我的权限，需要项目管理人员才有权限代他人报工。
				if(!xmWorkload.getUserid().equals(user.getUserid())){
	 				Tips tips3=xmGroupService.checkIsProjectAdmOrTeamHeadOrAss(user,xmWorkload.getUserid(),xmTaskDb.getProjectId());
					if(!tips3.isOk()){
						return Result.error("no-qx-for-oth-user","无权限代他人报工。只有项目管理人员可以代他人报工。");
					}
				}
				if(!(xmWorkload.getUserid().equals(xmTaskDb.getCreateUserid())|| xmWorkload.getUserid().equals(xmTaskDb.getExecutorUserid()))){
					return Result.error("no-create-or-not-exec",xmWorkload.getUserid()+"不是任务的负责人也不是执行人，不能报工。");
				}
				xmWorkload.setBizName(xmTaskDb.getName());
				xmWorkload.setMenuId(xmTaskDb.getMenuId());
				xmWorkload.setProductId(xmTaskDb.getProductId());
				xmWorkload.setCtime(new Date());
				xmWorkload.setWstatus("0");
				xmWorkload.setProjectId(xmTaskDb.getProjectId());
				xmWorkload.setBranchId(xmTaskDb.getCbranchId());
				xmWorkloadService.insert(xmWorkload);
				xmRecordService.addXmTaskRecord(xmTaskDb.getProjectId(),xmTaskDb.getId(),"任务-报工",String.format("【%s】提交工时【%s】,预计还要【%s】才能完成任务,工时备注【%s】",xmWorkload.getUsername(),xmWorkload.getWorkload(), ObjectTools.isEmpty(xmWorkload.getRworkload())?"未填写":xmWorkload.getRworkload(),xmWorkload.getRemark()));
				List<String> ids=new ArrayList<>();
				ids.add(xmTaskDb.getId());
				if(xmWorkload.getRworkload()!=null && BigDecimal.ZERO.compareTo(xmWorkload.getRworkload())<=0){
					BigDecimal newInitWorkload= xmWorkload.getRworkload().add(NumberUtil.getBigDecimal(xmWorkload.getWorkload(),BigDecimal.ZERO)).add(NumberUtil.getBigDecimal(xmTaskDb.getSumActWorkload(),BigDecimal.ZERO));

					this.xmTaskService.editSomeFields(map("$pks",ids,"initWorkload",newInitWorkload),Arrays.asList(xmTaskDb));
				}
				this.xmTaskService.calcWorkloadByRecord(ids);
				pushService.pushXmTask(xmTaskDb);


			}else if("2".equals(xmWorkload.getBizType())){//报工类型1-任务，2-缺陷，3-测试用例设计，4-测试执行
				XmQuestion xmQuestionDb=xmQuestionService.selectOneById(xmWorkload.getBugId());
				if(xmQuestionDb==null){
					return Result.error("bug-0","缺陷已不存在");
				}
				if(StringUtils.hasText(xmQuestionDb.getProjectId())){
					XmProject xmProject=xmProjectService.getProjectFromCache(xmQuestionDb.getProjectId());
					if(xmProject==null){
						return Result.error("project-0","项目已不存在");
					}
					xmWorkload.setProjectId(xmProject.getId());
					xmWorkload.setBranchId(xmProject.getBranchId());
				}

				if (!(xmWorkload.getUserid().equals(xmQuestionDb.getCreateUserid())||xmWorkload.getUserid().equals(xmQuestionDb.getHandlerUserid()))) {
					return Result.error("userid-err",xmWorkload.getUserid()+"不是当前缺陷的负责人或者创建人，无须报工。");
				}

				xmWorkload.setBizName(xmQuestionDb.getName());
				xmWorkload.setCtime(new Date());
				xmWorkload.setCuserid(user.getUserid());
				xmWorkload.setWstatus("0");
				xmWorkload.setProjectId(xmQuestionDb.getProjectId());
				xmWorkload.setProductId(xmQuestionDb.getProductId());
				xmWorkload.setMenuId(xmQuestionDb.getMenuId());
				xmWorkload.setFuncId(xmQuestionDb.getFuncId());
				xmWorkload.setCaseId(xmQuestionDb.getCaseId());
				xmWorkload.setPlanId(xmQuestionDb.getPlanId());
				xmWorkload.setTaskId(xmQuestionDb.getTaskId());
				xmWorkloadService.insert(xmWorkload);
				xmRecordService.addXmBugRecord(xmWorkload.getProductId(),xmWorkload.getProjectId(),xmWorkload.getBugId(),xmWorkload.getTaskId(),"缺陷-报工",String.format("【%s】提交工时【%s】,预计还要【%s】才能完成,工时备注【%s】",xmWorkload.getUsername(),xmWorkload.getWorkload(), ObjectTools.isEmpty(xmWorkload.getRworkload())?"未填写":xmWorkload.getRworkload(),xmWorkload.getRemark()));


				List<String> ids=new ArrayList<>();
				ids.add(xmQuestionDb.getId());
				if(xmWorkload.getRworkload()!=null && BigDecimal.ZERO.compareTo(xmWorkload.getRworkload())<=0){
					BigDecimal newBudgetWorkload= xmWorkload.getRworkload().add(NumberUtil.getBigDecimal(xmWorkload.getWorkload(),BigDecimal.ZERO)).add(NumberUtil.getBigDecimal(xmQuestionDb.getActWorkload(),BigDecimal.ZERO));

					this.xmQuestionService.batchUpdateBudgetWorkloadAndRate(ids,newBudgetWorkload );
				}
				this.xmQuestionService.calcWorkloadByRecord(ids);
			}else if("3".equals(xmWorkload.getBizType())){//报工类型1-任务，2-缺陷，3-测试用例设计，4-测试执行
				XmTestCase xmTestCaseDb=this.xmTestCaseService.selectOneById(xmWorkload.getCaseId());
				if(xmTestCaseDb==null){
					return Result.error("case-0","用例已不存在");
				}
				if(!(xmWorkload.getUserid().equals(xmTestCaseDb.getCuserid())||xmWorkload.getUserid().equals(xmTestCaseDb.getLuserid()))){
					return Result.error("userid-err",xmWorkload.getUserid()+"不是当前用例的负责人或者责任人，无须报工。");
				}

				xmWorkload.setBizName(xmTestCaseDb.getCaseName());
				xmWorkload.setCtime(new Date());
				xmWorkload.setWstatus("0");
				xmWorkload.setProductId(xmTestCaseDb.getProductId());
				xmWorkload.setMenuId(xmTestCaseDb.getMenuId());
				xmWorkload.setFuncId(xmTestCaseDb.getFuncId());
 				xmWorkload.setBranchId(xmTestCaseDb.getCbranchId());
 				xmWorkloadService.insert(xmWorkload);
				xmRecordService.addXmCaseRecord(xmWorkload.getProductId(),xmWorkload.getCaseId(),xmWorkload.getMenuId(),"产品-用例-报工",String.format("【%s】提交工时【%s】,预计还要【%s】才能完成,工时备注【%s】",xmWorkload.getUsername(),xmWorkload.getWorkload(), ObjectTools.isEmpty(xmWorkload.getRworkload())?"未填写":xmWorkload.getRworkload(),xmWorkload.getRemark()));

				List<String> ids=new ArrayList<>();
				ids.add(xmTestCaseDb.getId());
				if(xmWorkload.getRworkload()!=null && BigDecimal.ZERO.compareTo(xmWorkload.getRworkload())<=0){
					BigDecimal newBudgetWorkload= xmWorkload.getRworkload().add(NumberUtil.getBigDecimal(xmWorkload.getWorkload(),BigDecimal.ZERO)).add(NumberUtil.getBigDecimal(xmTestCaseDb.getActWorkload(),BigDecimal.ZERO));

					this.xmTestCaseService.batchUpdateBudgetWorkloadAndRate(ids,newBudgetWorkload );
				}
				this.xmTestCaseService.calcWorkloadByRecord(ids);
			}else if("4".equals(xmWorkload.getBizType())){//报工类型1-任务，2-缺陷，3-测试用例设计，4-测试执行
				Map<String,Object> p=map("planId",xmWorkload.getPlanId(),"caseId",xmWorkload.getCaseId());
				List<Map<String,Object>> xmTestPlanCaseDbs=this.xmTestPlanCaseService.selectListMapByWhere(QueryTools.initPage(p),QueryTools.initQueryWrapper(XmTestPlanCase.class,p),p);
				if(xmTestPlanCaseDbs==null||xmTestPlanCaseDbs.size()==0){
					return Result.error("xmTestPlanCaseDb-0","执行用例已不存在");
				}
				Map<String,Object> xmTestPlanCaseDb=xmTestPlanCaseDbs.get(0);
				XmTestPlanCase xmTestPlanCaseDb2=fromMap(xmTestPlanCaseDb,XmTestPlanCase.class);
				if(!(xmWorkload.getUserid().equals(xmTestPlanCaseDb.get("execUserid")))){
					return Result.error("userid-err",xmWorkload.getUserid()+"不是当前用例的执行人，无须报工。");
				}
				String projectId= (String) xmTestPlanCaseDb.get("projectId");
				if(StringUtils.hasText(projectId)){
					XmProject xmProject=xmProjectService.getProjectFromCache(projectId);
					if(xmProject==null){
						return Result.error("project-0","项目已不存在");
					}

					xmWorkload.setProjectId(xmProject.getId());
					xmWorkload.setBranchId(xmProject.getBranchId());
				}else{
					return Result.error("projectId-0","项目编号不能为空");
				}
				xmWorkload.setBizName((String) xmTestPlanCaseDb.get("caseName"));
				xmWorkload.setCtime(new Date());
				xmWorkload.setCuserid(user.getUserid());
				xmWorkload.setWstatus("0");
				xmWorkload.setProductId((String) xmTestPlanCaseDb.get("productId"));
				xmWorkload.setFuncId((String) xmTestPlanCaseDb.get("funcId"));
				xmWorkload.setMenuId((String) xmTestPlanCaseDb.get("menuId"));
				xmWorkload.setCaseId((String) xmTestPlanCaseDb.get("caseId"));
				xmWorkload.setPlanId((String) xmTestPlanCaseDb.get("planId"));
				xmWorkloadService.insert(xmWorkload);
				xmRecordService.addXmCaseExeRecord(xmWorkload.getProductId(),xmWorkload.getProjectId(),xmWorkload.getPlanId(),xmWorkload.getMenuId(),"产品-用例-报工",String.format("【%s】提交工时【%s】,预计还要【%s】才能完成,工时备注【%s】",xmWorkload.getUsername(),xmWorkload.getWorkload(), ObjectTools.isEmpty(xmWorkload.getRworkload())?"未填写":xmWorkload.getRworkload(),xmWorkload.getRemark()));

				List<Map<String,Object>> ids=new ArrayList<>();
				ids.add(xmTestPlanCaseDb);
				if(xmWorkload.getRworkload()!=null && BigDecimal.ZERO.compareTo(xmWorkload.getRworkload())<=0){
					BigDecimal newBudgetWorkload= xmWorkload.getRworkload().add(NumberUtil.getBigDecimal(xmWorkload.getWorkload(),BigDecimal.ZERO)).add(NumberUtil.getBigDecimal(xmTestPlanCaseDb2.getActWorkload(),BigDecimal.ZERO));

					this.xmTestPlanCaseService.batchUpdateBudgetWorkloadAndRate(ids,newBudgetWorkload );
				}
				this.xmTestPlanCaseService.calcWorkloadByRecord(ids);
			}
		return Result.ok().setData(xmWorkload);
	}
	@ApiOperation( value = "根据主键修改一条工时登记表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response= XmWorkload.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmWorkload(@RequestBody XmWorkload xmWorkload) {

            if(!StringUtils.hasText(xmWorkload.getId())) {
                 return Result.error("pk-not-exists","请上送主键参数id");
            }
            XmWorkload xmWorkloadDb = xmWorkloadService.selectOneObject(xmWorkload);
            if( xmWorkloadDb == null ){
                return Result.error("data-not-exists","数据不存在，无法修改");
            }
			XmTask xmTaskDb=this.xmTaskService.selectOneObject(new XmTask(xmWorkloadDb.getTaskId()));
			if(xmTaskDb==null ){
				return Result.error("data-0","任务已不存在");
			}
			if("1".equals(xmTaskDb.getNtype())){
				return Result.error("ntype-1",xmTaskDb.getName()+"为计划，不是任务，不用登记工时");
			}
			User user= LoginUtils.getCurrentUserInfo();
			if(!(user.getUserid().equals(xmTaskDb.getCreateUserid())|| user.getUserid().equals(xmTaskDb.getExecutorUserid()))){
 				Tips isCreate=xmGroupService.checkIsProjectAdmOrTeamHeadOrAss(user,xmTaskDb.getCreateUserid(),xmTaskDb.getProjectId());
				if(!isCreate.isOk()){
	 				Tips isExec=xmGroupService.checkIsProjectAdmOrTeamHeadOrAss(user,xmTaskDb.getExecutorUserid(),xmTaskDb.getProjectId());
					if(!isExec.isOk()){
						return Result.error("noqx-0","你无权针对该业务进行报工");
					}

				}
			}
			xmWorkloadService.updateSomeFieldByPk(xmWorkload);
			pushService.pushXmTask(xmTaskDb);
			this.xmTaskService.calcWorkloadByRecord(xmWorkload.getTaskId());

		return Result.ok();
	}
	

	@ApiOperation( value = "根据主键列表批量删除工时登记表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmWorkload(@RequestBody List<XmWorkload> xmWorkloads) {

			if(xmWorkloads.stream().filter(i->!StringUtils.hasText(i.getId())).findAny().isPresent()){
				return Result.error("id-0","主键不能为空");
			}
			xmWorkloads = xmWorkloadService.selectListByIds(xmWorkloads.stream().map(i->i.getId()).collect(Collectors.toList()));
			if(xmWorkloads==null || xmWorkloads.size()==0){
				return Result.error("data-0","工时已不存在");
			}
			User user= LoginUtils.getCurrentUserInfo();
			List<String> taskIds=xmWorkloads.stream().map(i->i.getTaskId()).collect(Collectors.toSet()).stream().collect(Collectors.toList());
			List<XmTask> tasksDb=this.xmTaskService.selectListByIds(taskIds);
			Map<String,XmTask> taskMap=new HashMap<>();
			Map<String,XmTask> canDelTaskMap=new HashMap<>();
			for (XmTask xmTask : tasksDb) {
				taskMap.put(xmTask.getId(),xmTask);
			}
			Set<String> xmMenuIds=new HashSet<>();
			for (XmTask xmTaskDb : tasksDb) {
				if(!(user.getUserid().equals(xmTaskDb.getCreateUserid())|| user.getUserid().equals(xmTaskDb.getExecutorUserid()))){
	 				Tips isCreate=xmGroupService.checkIsProjectAdmOrTeamHeadOrAss(user,xmTaskDb.getCreateUserid(),xmTaskDb.getProjectId());
					if(!isCreate.isOk()){
		 				Tips isExec=xmGroupService.checkIsProjectAdmOrTeamHeadOrAss(user,xmTaskDb.getExecutorUserid(),xmTaskDb.getProjectId());
						if(!isExec.isOk()){
							break;
						}

					}
				}
				canDelTaskMap.put(xmTaskDb.getId(),xmTaskDb);
				if(StringUtils.hasText(xmTaskDb.getMenuId())){
					xmMenuIds.add(xmTaskDb.getMenuId());
				}
			}
			List<XmWorkload> canDel=new ArrayList<>();
			List<XmWorkload> state1Ndel=new ArrayList<>();
			List<XmWorkload> noQxDel=new ArrayList<>();
			for (XmWorkload xmWorkload : xmWorkloads) {
				 if(canDelTaskMap.containsKey(xmWorkload.getTaskId())){
				 	if(!"1".equals(xmWorkload.getWstatus())){
				 		canDel.add(xmWorkload);
					} else{
						state1Ndel.add(xmWorkload);
					}
				 }else{
				 	if(!taskMap.containsKey(xmWorkload.getTaskId())){//对应任务已被删除，不存在了
				 		if(!"1".equals(xmWorkload.getWstatus())){
							canDel.add(xmWorkload);
						}else{
							state1Ndel.add(xmWorkload);
						}
					}else{
						noQxDel.add(xmWorkload);
					}
				 }
			}

			List<String> msgs=new ArrayList<>();
			if(canDel.size()>0){
				xmWorkloadService.batchDelete(canDel);
				this.xmTaskService.calcWorkloadByRecord(canDelTaskMap.keySet().stream().collect(Collectors.toList()));
 				pushService.pushXmTasks(canDelTaskMap.values().stream().collect(Collectors.toList()));
				msgs.add("成功删除"+canDel.size()+"条工时单据。");
			}
			if(state1Ndel.size()>0){
 				msgs.add("以下"+state1Ndel.size()+"条工时单据状态为确认状态，不允许删除。【"+state1Ndel.stream().map(i->i.getUsername()+i.getBizDate()).collect(Collectors.joining(","))+"】");
			}
			if(noQxDel.size()>0){
				msgs.add("以下"+noQxDel.size()+"条工时单据无权限删除，您只能删除你负责的任务的工时单据，【"+noQxDel.stream().map(i->i.getUsername()+i.getBizDate()).collect(Collectors.joining(","))+"】.");
			}
			if(canDel.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
		
	}





	@ApiOperation( value = "批量更新工时表状态各个字段",notes="xmWorkloadMap")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmMenu.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmWorkload_editSomeFields",name = "批量修改修改任务中的某些字段",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> xmWorkloadMap) {

			List<String> ids= (List<String>) xmWorkloadMap.get("$pks");

			if(ids==null || ids.size()==0){
				return Result.error("ids-0","$pks不能为空");
			}
			Set<String> fields=new HashSet<>();
			fields.add("workload");
			fields.add("userid");
			fields.add("username");
			fields.add("projectId");
			fields.add("sbillId");

			for (String fieldName : xmWorkloadMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			List<XmWorkload> xmWorkloadsDb= xmWorkloadService.selectListByIds(ids);
			if(xmWorkloadsDb==null ||xmWorkloadsDb.size()==0){
				return Result.error("tasks-0","该工时已不存在");
			}
			String wstatus= (String) xmWorkloadMap.get("wstatus");
			String sstatus= (String) xmWorkloadMap.get("sstatus");
			if(StringUtils.hasText(sstatus)){
				if(!"0".equals(sstatus) && !"1".equals(sstatus)){
					return Result.error("sstatus-not-01","只能修改为无需结算或者待结算");
				}
			}
			if(StringUtils.hasText(wstatus)){
				if(!"0".equals(wstatus) && !"1".equals(wstatus) ){
					return Result.error("wstatus-not-01","工时状态不正确");
				}
			}
			List<String> taskIds=xmWorkloadsDb.stream().map(i->i.getTaskId()).collect(Collectors.toSet()).stream().collect(Collectors.toList());

			Map<String,XmTask> taskMap=new HashMap<>();
			if(xmWorkloadMap.containsKey("sstatus") && "1".equals(sstatus)){
				List<XmTask> tasks=this.xmTaskService.selectListByIds(taskIds);
				for (XmTask task : tasks) {
					taskMap.put(task.getId(),task);
				}
			}

			List<XmWorkload> canChanges=new ArrayList<>();
			List<XmWorkload> sstatusNot01=new ArrayList<>();
			List<XmWorkload> taskStateNot34=new ArrayList<>();
			for (XmWorkload xmWorkload : xmWorkloadsDb) {
				if(!"1".equals(xmWorkload.getSstatus()) && !"0".equals(xmWorkload.getSstatus()) &&StringUtils.hasText(xmWorkload.getSstatus())){
					sstatusNot01.add(xmWorkload);
				}else{
					if(xmWorkloadMap.containsKey("sstatus") && "1".equals(sstatus)){
						XmTask task=taskMap.get(xmWorkload.getTaskId());
						if(task==null || (!"2".equals(task.getTaskState()) && !"3".equals(task.getTaskState()))){
							taskStateNot34.add(xmWorkload);
							continue;
						}
					}
					canChanges.add(xmWorkload);
				}
			}



			if(canChanges.size()>0){
				xmWorkloadMap.put("$pks",canChanges.stream().map(i->i.getId()).collect(Collectors.toList()));
				Set<String> fieldKey=xmWorkloadMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
				fieldKey=fieldKey.stream().filter(i->xmWorkloadMap.get(i)!=null).collect(Collectors.toSet());

				if(fieldKey.size()>0){
					xmWorkloadService.editSomeFieldsWithSbillIds(xmWorkloadMap,null);
				}
			}
			List<String> msgs=new ArrayList<>();
			if(canChanges.size()>0){
				msgs.add("成功修改"+canChanges.size()+"条工时清单");
			}

			if(sstatusNot01.size()>0){
				msgs.add("有"+sstatusNot01.size()+"条工时不是待结算状态，不允许更改");
			}
			if(taskStateNot34.size()>0){
				msgs.add("有"+taskStateNot34.size()+"条工时对应的任务不是已完工状态，不允许进入结算池");
			}
			if(canChanges.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
		
	}
}
