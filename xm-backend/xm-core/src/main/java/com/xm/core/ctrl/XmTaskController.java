package com.xm.core.ctrl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.audit.log.client.annotation.AuditLog;
import com.mdp.audit.log.client.annotation.OperType;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.*;
import com.mdp.meta.client.service.ItemService;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sensitive.SensitiveWordService;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.PubTool;
import com.xm.core.entity.*;
import com.xm.core.queue.XmTaskSumParentsPushService;
import com.xm.core.service.*;
import com.xm.core.service.client.SysClient;
import com.xm.core.service.push.XmPushMsgService;
import com.xm.core.vo.*;
import io.swagger.annotations.*;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static com.mdp.core.utils.BaseUtils.map;

/**
 * url编制采用rest风格,如对XM.xm_task xm_task的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmTask/add <br>
 *  查询: xm/xmTask/list<br>
 *  模糊查询: xm/xmTask/listKey<br>
 *  修改: xm/xmTask/edit <br>
 *  删除: xm/xmTask/del<br>
 *  批量删除: xm/xmTask/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmTask 表 XM.xm_task 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmTaskController")
@RequestMapping(value="/xm/core/xmTask")
@Api(tags={"任务操作接口"})
public class XmTaskController {
	
	static Log logger=LogFactory.getLog(XmTaskController.class);
 
	
	@Autowired
	private XmTaskService xmTaskService;


	@Autowired
	private XmMenuService xmMenuService;
	
	@Autowired
	XmGroupService groupService;
	
	@Autowired
	private XmRecordService xmRecordService;
	
	@Autowired
	private XmPushMsgService xmPushMsgService;
	@Autowired
	private XmProjectService xmProjectService;
	@Autowired
	private XmProjectQxService projectQxService;

	@Autowired
	XmMenuService xmMenusService;

	@Autowired
	XmMenuOperQxService menuOperQxService;

	@Autowired
	XmProductService xmProductService;

	@Autowired
	XmTaskSumParentsPushService pushService;

	@Autowired
	XmTaskExecuserController execuserController;

	@Autowired
	SensitiveWordService sensitiveWordService;


	@Autowired
	PushNotifyMsgService notifyMsgService;

	@Autowired
	SysClient sysClient;

	ItemService itemService;


	Map<String,Object> fieldsMap = BaseUtils.toMap(new XmTask());
	Map<String,Object> fieldNameMap=map("id","任务编号","name","任务名称","parentTaskid","父任务编号","parentTaskname","父任务名称","projectId","项目编号","projectName","项目名称","level","任务级别","sortLevel","序号","executorUserid","任务执行人编号","executorUsername","任务执行人","preTaskid","前置任务编号","preTaskname","前置任务名称","startTime","任务开始时间","endTime","任务结束时间","milestone","里程碑","description","任务描述","remarks","备注","createUserid","任务创建人编号（谁创建谁负责）","createUsername","任务创建人（谁创建谁负责）","createTime","创建时间","rate","任务进度0-100（=实际工时/(实际工时+剩余工时)*100）","budgetAt","当前任务预算金额（calc_type=2时预算工时*单价，calc_type=1时下级汇总）","budgetWorkload","预算工时（calc_type=2时手工填写，calc_type=1时下级汇总）","actAt","当前任务实际费用金额（calc_type=2时，取实际工时*单价，calc_type=1时取下级汇总数据）待结算金额","actWorkload","任务取工时表报工工时汇总，","taskState","任务状态0待领取1已领取执行中2已完工3已结算4已关闭","taskType","0售前方案1投标2需求3设计4开发5测试6验收7部署8运维--来自基础数据表taskType","taskClass","1需结算0不需结算","toTaskCenter","是否发布到任务大厅0否1是,1时互联网可访问","actStartTime","实际开始时间-任务状态变成执行中的时间","actEndTime","实际结束时间-任务状态变成完工状态时的时间","bizProcInstId","当前流程实例编号","bizFlowState","当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除","phaseId","项目阶段编号(作废)","phaseName","项目阶段名称(作废)","taskSkillNames","技能列表,逗号分隔","exeUsernames","执行人列表逗号分隔如陈x(审核人),王x(监控人)","taskSkillIds","技能编号列表逗号分隔","exeUserids","执行人编号列表逗号分隔如u1(1),u2(2)","taskOut","执行方式-0内研1外购","planType","计划类型w1-周,w2-2周,w3-3周,m1-1月,m2-2月,q1-季,q2-半年，y1-年","settleSchemel","任务结算方案-来自数字字典xmTaskSettleSchemel","menuId","归属功能编号","menuName","归属功能名称","productId","产品编号根据功能变化带进","cbranchId","创建机构","cdeptid","创建部门","tagIds","标签编号，逗号分割","tagNames","标签名称，逗号分割","ntype","节点类型0-任务，1-计划。计划下可建立计划和任务，任务下不允许再扩展。也就是非叶子节点都是计划，叶子节点有可能是计划或者任务","childrenCnt","儿子节点个数","ltime","更新时间","pidPaths","父级id逗号分割，最后一个为本节点节点编号,以,号结尾","lvl","层级0-顶级，1-一级，2-二级，3-三级，4-四级。总共5级","isTpl","是否为模板","keyPath","是否为关键路径上的节点","uniInnerPrice","内部单位工时单价","uniOutPrice","外部单位工时单价","calcType","数据统计方式","ptype","计划分类0-项目，1产品,空为不区分","wtype","报工方式1-强制每日报工，2-工期内报工，0-无需报工","bctrl","报工限制0-不限制，1-不得超出预估工时","initWorkload","原始预估工作量，budget_workload发生变化后，进行备份","shareFee","分享赚佣金","oshare","开启分享赚功能0-否1是","crowd","是否众包0否1是，众包属于外购的一种");

	@ApiOperation( value = "查询任务信息列表",notes="listXmTask,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmTask.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderyBy = sex desc, student_id desc",required=false)
	})
	@ApiResponses({
			@ApiResponse(code = 200,response= XmTask.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getTask",method=RequestMethod.GET)
	public Result getTask(@ApiIgnore @RequestParam Map<String,Object> params){
		RequestUtils.transformArray(params, "ids");
		RequestUtils.transformArray(params, "skillIds");
		RequestUtils.transformArray(params, "tagIdList");
		RequestUtils.transformArray(params, "lvls");		
		IPage page=QueryTools.initPage(params);
		String taskOut= (String) params.get("taskOut");
		String projectId= (String) params.get("projectId");
		String myExecuserStatus= (String) params.get("myExecuserStatus");
		String myFocus= (String) params.get("myFocus");
		String createUserid= (String) params.get("createUserid");
		String executorUserid= (String) params.get("executorUserid");
		String menuId= (String) params.get("menuId");
		String productId= (String) params.get("productId");
		String iterationId= (String) params.get("iterationId");
		String parentTaskid= (String) params.get("parentTaskid");
		User user = LoginUtils.getCurrentUserInfo();
		params.put("userid",user.getUserid());
		if( !(StringUtils.hasText(projectId) || StringUtils.hasText(parentTaskid)
				|| StringUtils.hasText(myExecuserStatus)|| StringUtils.hasText(myFocus)|| StringUtils.hasText(createUserid)
				|| StringUtils.hasText(executorUserid) || StringUtils.hasText(menuId) || StringUtils.hasText(productId)|| StringUtils.hasText(iterationId)) ){

			params.put("compete",user.getUserid());
		}
		QueryWrapper<XmTask> qw = QueryTools.initQueryWrapper(XmTask.class , params);
		List<Map<String,Object>> datas = xmTaskService.getTask(page,qw,params);	//列出XmTask列表
 		if("1".equals(params.get("withParents"))  && !"1".equals(params.get("isTop"))&& datas.size()>0){
			Set<String> pidPathsSet=new HashSet<>();
			Set<String> idSet=new HashSet<>();
			for (Map<String, Object> map : datas) {
				String id= (String) map.get("id");
				idSet.add(id);
				String pidPaths= (String) map.get("pidPaths");
				if(pidPaths==null || pidPaths.length()<=2){
					continue;
				}
				pidPathsSet.addAll(PubTool.getPidSet(pidPaths,id));
			}
			List<String> ids=pidPathsSet.stream().filter(i->!idSet.contains(i)).collect(Collectors.toList());
			if(ids!=null && ids.size()>0){
				QueryWrapper qw2=new QueryWrapper();
				qw2.in("id",ids.toArray());
				List<Map<String,Object>> parentList=xmTaskService.listMaps(qw2);
 				if(parentList!=null && parentList.size()>0){
					datas.addAll(parentList);
					return Result.ok().setData(datas).setTotal(page.getSize()+parentList.size());
				}
			}
		}
		return Result.ok().setData(datas).setTotal(page.getTotal());
		
		
		
	}



	@ApiOperation(  value = "查询任务信息列表-互联网大厅首页专用、免登录", notes="listXmTask,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmTask.class) 
	@ApiImplicitParams({
 			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",dataType = "int" ,required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",dataType = "int" ,required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",dataType = "int" ,required=false),			@ApiImplicitParam(name="count",value="是否进行总记录数计算，默认是计算，如果需要关闭，请上送count=false",dataType = "int" ,required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",dataType = "string" ,required=false),
 	}) 
	@ApiResponses({
			@ApiResponse(code = 200,response= XmTask.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getOutTask",method=RequestMethod.GET)
	public Result getOutTask(@ApiIgnore @RequestParam Map<String,Object> params){
		RequestUtils.transformArray(params, "skillIds");		
		IPage page=QueryTools.initPage(params);
		params.put("taskOut","1");
		params.put("ntype","0");
		params.put("toTaskCenter","1");

		QueryWrapper<XmTask> qw = QueryTools.initQueryWrapper(XmTask.class , params);
		List<Map<String,Object>> datas=xmTaskService.getTask(page,qw,params);

		return Result.ok().setData(datas).setTotal(page.getTotal());
		
		
	}
	@RequestMapping(value="/getXmTaskAttDist",method=RequestMethod.GET)
	public Result getXmTaskAttDist(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		params.put("pbranchId",user.getBranchId());

		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmTask> qw = QueryTools.initQueryWrapper(XmTask.class , params);
		List<Map<String,Object>> datas= this.xmTaskService.getXmTaskAttDist(page,qw,params);
		return Result.ok("ok","成功").setData(datas).setTotal(page.getTotal());
	}

	@RequestMapping(value="/getXmTaskAgeDist",method=RequestMethod.GET)
	public Result getXmTaskAgeDist(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();
		params.put("pbranchId",user.getBranchId());

		QueryWrapper<XmTask> qw = QueryTools.initQueryWrapper(XmTask.class , params);
		List<Map<String,Object>> datas= this.xmTaskService.getXmTaskAgeDist(qw,params);
		return Result.ok("ok","成功").setData(datas).setTotal(datas.size());
	}


	@ApiOperation("批量更新任务的浏览量")
	@RequestMapping(value="/upBrowseTimes",method=RequestMethod.POST)
	public Result upBrowseTimes(  @RequestBody List<UpBrowseTimesVo> browseTimesVos){
		User user=LoginUtils.getCurrentUserInfo();
		if(browseTimesVos!=null && browseTimesVos.size()>0){
			for (UpBrowseTimesVo browseTimesVo : browseTimesVos) {
				XmTaskCalcService.putReadNum(browseTimesVo.getTaskId(),browseTimesVo.getNums());
			}
		}

		return Result.ok("成功");
	}

	@ApiOperation("统计所有上级的进度情况")
	@RequestMapping(value="/calcProgress",method=RequestMethod.POST)
	public Result calcProgress( @ApiIgnore @RequestBody List<String> ids){
		User user=LoginUtils.getCurrentUserInfo();
		List<XmTask> tasks=this.xmTaskService.listByIds(ids);
		List<XmTask> node0s=tasks.stream().filter(k->"0".equals(k.getNtype())).collect(Collectors.toList());
		if(node0s.size()>0){
			this.xmTaskService.calcWorkloadByRecord(node0s.stream().map(k->k.getId()).collect(Collectors.toList()));
		}
		this.xmTaskService.batchSumParents(tasks);
		return Result.ok("成功");
	}

	@ApiOperation("按项目类型初始化一级计划")
	@RequestMapping(value="/initProjectPhasePlan",method=RequestMethod.POST)
	public Result initProjectPhasePlan( @ApiIgnore @RequestBody PhasePlanVO phasePlanVO){
		User user=LoginUtils.getCurrentUserInfo();
		String projectId=phasePlanVO.getProjectId();
		if(ObjectTools.isEmpty(projectId)){
			return Result.error("projectId-0","项目编号不能为空");
		}
		XmProject project=xmProjectService.getProjectFromCache(projectId);
		Tips hasQx=projectQxService.checkProjectQx(project,2,user);
		if(!hasQx.isOk()){
			return Result.error(LangTips.fromTips(hasQx));
		}
		if(phasePlanVO.getTasks()==null || phasePlanVO.getTasks().size()==0){
			return Result.error("tasks-0","阶段计划不能为空");
		}

//		ItemVo item=itemService.getDict("all","projectPhasePlan"+project.getXmType());
//		if(item==null || item.getOptions()==null || item.getOptions().size()==0){
//			return Result.error("item-0","没有找到项目类型匹配的阶段计划节点，请在【项目配置->项目类型】中进行配置后重试！");
//		}
		List<XmTask> tasks=phasePlanVO.getTasks();
 		List<XmTask> can=new ArrayList<>();
		List<XmTask> phasePlansDb = this.xmTaskService.list(new LambdaQueryWrapper<XmTask>().eq(XmTask::getProjectId,projectId).isNotNull(XmTask::getPhaseId).eq(XmTask::getNtype,"1").eq(XmTask::getLvl,1));
		if(phasePlansDb!=null && phasePlansDb.size()>0){
			can=tasks.stream().filter(k->!phasePlansDb.stream().filter(d->k.getPhaseId().equals(d.getPhaseId())).findAny().isPresent()).collect(Collectors.toList());
		}else{
			can=tasks;
		}
		List<XmTask> phasePlansNew=new ArrayList<>();
		if(can.size()>0){
			Date now=new Date();
			int i=0;
			for (XmTask xmTask : can) {
				xmTask.setPtype("1");
				xmTask.setId(this.xmTaskService.createKey("id"));
				xmTask.setNtype("1");
				xmTask.setLvl(1);
				xmTask.setCreateTime(new Date());
				xmTask.setCdeptid(user.getDeptid());
				xmTask.setCbranchId(user.getBranchId());
				xmTask.setProjectName(project.getName());
				xmTask.setProjectId(projectId);
				xmTask.setCreateUserid(user.getUserid());
				xmTask.setCreateUsername(user.getUsername());
				xmTask.setTaskState("0");
				xmTask.setParentTaskid("0");
				xmTask.setParentTaskname("根");
				if(ObjectTools.isEmpty(xmTask.getStartTime())){
					xmTask.setStartTime(DateUtil.beginOfMonth(DateUtil.offsetMonth(now,i+1)));
				}
				if(ObjectTools.isEmpty(xmTask.getEndTime())){
					xmTask.setEndTime(DateUtil.endOfMonth(DateUtil.offsetMonth(now,i+1)));
				}
				LocalDate startOfMonth = LocalDate.of(xmTask.getStartTime().getYear(),xmTask.getStartTime().getMonth()+1,1);
				LocalDate endOfMonth = startOfMonth.with(TemporalAdjusters.lastDayOfMonth());

				long workdays= LongStream.rangeClosed(0, endOfMonth.toEpochDay())
						.mapToObj(startOfMonth::plusDays)
						.filter(day -> day.getDayOfWeek() != DayOfWeek.SATURDAY && day.getDayOfWeek() != DayOfWeek.SUNDAY)
						.count();
				xmTask.setBudgetWorkload(BigDecimal.valueOf(workdays).multiply(BigDecimal.valueOf(8)));
				phasePlansNew.add(xmTask);
				i=i+1;
			}
			this.xmTaskService.parentIdPathsCalcBeforeSave(phasePlansNew);
			this.xmTaskService.saveBatch(phasePlansNew);
		}else{
			return Result.error("phase-exists","阶段计划已存在，无须再进行初始化");
		}

		return Result.ok("成功");
	}
	@RequestMapping(value="/getXmTaskSort",method=RequestMethod.GET)
	public Result getXmTaskSort(@ApiIgnore @RequestParam Map<String,Object> params){
		User user=LoginUtils.getCurrentUserInfo();		
		IPage page=QueryTools.initPage(params);
		params.put("pbranchId",user.getBranchId());
		QueryWrapper<XmTask> qw = QueryTools.initQueryWrapper(XmTask.class , params);
		List<Map<String,Object>> datas= this.xmTaskService.getXmTaskSort(page,qw,params);
		return Result.ok().setData(datas).setTotal(page.getTotal());
		
		
	}
	/***/
	@ApiOperation( value = "根据主键批量修改修改任务中的某些字段信息",notes="editXmMenu")
	@ApiEntityParams( value = XmTask.class, props={ }, remark = "任务", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=XmMenu.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmTask_editSomeFields",name = "批量修改修改任务中的某些字段",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> xmTaskMap) {

			User user = LoginUtils.getCurrentUserInfo();
			List<String> ids= (List<String>) xmTaskMap.get("$pks");

			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}
		xmTaskMap.put("ltime",new Date());
			if(xmTaskMap.containsKey("executorUserid")){
				if(ids.size()>1){
					List<Tips> errs=new ArrayList<>();
					List<Tips> oks=new ArrayList<>();
					String msg="";
					for (String id : ids) {
						XmTaskExecuser xmTaskExecuser=new XmTaskExecuser();
						xmTaskExecuser.setTaskId(id);
						xmTaskExecuser.setBidUserid((String)xmTaskMap.get("executorUserid"));
						xmTaskExecuser.setBidUsername((String)xmTaskMap.get("executorUsername"));
						Map<String,Object> result=execuserController.addXmTaskExecuser(xmTaskExecuser);
		 				Tips tips= (Tips) result.get("tips");
		 				tips.put("taskId",id);
						if(!tips.isOk()){
							errs.add(tips);
						}else{
							oks.add(tips);
						}


					}

					if(errs.size()>0){
						msg="以下"+errs.size()+"个任务更新不成功："+errs.stream().map(i->"["+i.get("taskId")+"]"+i.getMsg()).collect(Collectors.joining(";"));
					}
					if(oks.size()>0){
						msg="成功设置"+oks.size()+"个任务的执行人。"+msg;
						return Result.ok(msg);
					}else{
						return Result.error(msg);
					}
				}else if(ids.size()==1){
					XmTaskExecuser xmTaskExecuser=new XmTaskExecuser();
					xmTaskExecuser.setTaskId(ids.get(0));
					xmTaskExecuser.setBidUserid((String)xmTaskMap.get("executorUserid"));
					xmTaskExecuser.setBidUsername((String)xmTaskMap.get("executorUsername"));
					return execuserController.addXmTaskExecuser(xmTaskExecuser);
				}
			}
			Set<String> fields=new HashSet<>();
			fields.add("childrenCnt");
			//fields.add("ntype");
			fields.add("pidPaths");
			fields.add("parentTaskid");
			fields.add("parentTaskname");
			fields.add("executorUserid");
			fields.add("quoteFinalAt");
			//fields.add("ptype");
			for (String fieldName : xmTaskMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=xmTaskMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->xmTaskMap.get(i)!=null).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
 			}
			if(fieldKey.contains("budgetAt") && ids.size()>1){
				return Result.error("ids-to0-more","修改预算只能一次修改一条数据");
			}
			XmTask xmTask= BaseUtils.fromMap(xmTaskMap,XmTask.class);
			List<XmTask> xmTasksDb=xmTaskService.selectListByIds(ids);
			if(xmTasksDb==null ||xmTasksDb.size()==0){
				return Result.error("tasks-0","该任务已不存在");
			}
			Map<String,XmProject> projectMap=new HashMap<>();
			
			if(xmTaskMap.containsKey("createUserid")){
				String createUserid=(String) xmTaskMap.get("createUserid");
				String createUsername=(String) xmTaskMap.get("createUsername");
				String cbranchId=(String) xmTaskMap.get("cbranchId");
				Set<String> projects=xmTasksDb.stream().map(i->i.getProjectId()).collect(Collectors.toSet());
				for (String project : projects) {
					XmProject xmProject=xmProjectService.getProjectFromCache(project);
					if(xmProject==null){
						return Result.error("project-not-exists","项目【%s】不存在",project);
					}
					projectMap.put(xmProject.getId(),xmProject);
	 				Tips  tips1=projectQxService.checkProjectQx(xmProject,2,user,createUserid,createUsername,cbranchId);
					Result.assertIsFalse(tips1);
				}

			}

			List<XmTask> can=new ArrayList<>();
			List<XmTask> no=new ArrayList<>();
			for (XmTask xmTaskDb : xmTasksDb) {
				XmProject xmProject=projectMap.get(xmTaskDb.getProjectId());
				if(xmProject==null || StringUtils.isEmpty(xmProject.getId()) || !projectMap.containsKey(xmProject.getId())){

					xmProject=xmProjectService.getProjectFromCache(xmTaskDb.getProjectId());
					if(xmProject==null){
						return Result.error("project-not-exists","项目【%s】不存在",xmTaskDb.getProjectId());
					}
					projectMap.put(xmTaskDb.getProjectId(),xmProject);
				}
				if(groupService.checkUserIsProjectAdm(xmProject,user.getUserid())){
					can.add(xmTaskDb);
				}else{
	 				Tips tips =projectQxService.checkProjectQx(xmProject,2,user,xmTaskDb.getCreateUserid(),xmTaskDb.getCreateUsername(),xmTaskDb.getCbranchId());
					if(!tips.isOk()){
						no.add(xmTaskDb);
					}else{
						can.add(xmTaskDb);
					}
				}
			}


		List<XmTask> canOper2=new ArrayList<>();
		Map<String,Tips> noPtypeTips = new HashMap<>();
		// 如果是更改需求类型，有子节点不允许转成故事，
		if(xmTaskMap.containsKey("ptype")){
			int ptype=NumberUtil.getInteger(xmTaskMap.get("ptype"));
			for (XmTask task : can) {
				int mdclass=NumberUtil.getInteger(task.getPtype());
				if(ptype>2 && mdclass<3 && task.getChildrenCnt()>0){
					Tips tips1= LangTips.errMsg("ptype-err-1","【%s】有子节点，不允许变更为【任务】",task.getName());
					noPtypeTips.put(tips1.getMsg(),tips1);
				}else if(ptype==1 && ObjectTools.isNotEmpty(task.getParentTaskid()) && !"0".equals(task.getParentTaskid())){
					Tips tips1= LangTips.errMsg("ptype-err-2","【%s】有上级，不允许变更为【阶段】",task.getName());
					noPtypeTips.put(tips1.getMsg(),tips1);
				}else{
					canOper2.add(task);
				}
			}
			if(ptype>=3){
				xmTaskMap.put("ntype","0");
			}else{
				xmTaskMap.put("ntype","1");
			}
			can=canOper2;
		}


			if(can.size()>0 && fieldKey.contains("budgetAt")){

				XmTask taskDb=can.get(0);
				XmProject xmProject=projectMap.get(taskDb.getProjectId());
				BigDecimal budgetAt=NumberUtil.getBigDecimal(xmTaskMap.get("budgetAt"),BigDecimal.ZERO);
				if(xmProject.getMaxTaskAmt()!=null && xmProject.getMaxTaskAmt().compareTo(BigDecimal.ZERO)>0){
					if(budgetAt.compareTo(xmProject.getMaxTaskAmt())>0){
						return Result.error("budgetAt-maxTaskAmt-0",String.format("单个任务的金额超出预算。每个任务的预算最大为%s元",xmProject.getMaxTaskAmt()));
					}
				}
				if("1".equals(xmProject.getBudgetCtrl())){
					if(taskDb.getLvl()<=1){
		 				Tips tips =this.xmTaskService.judgetProjectBudget(taskDb.getProjectId(),budgetAt,Arrays.asList(taskDb.getId()));
		 				Result.assertIsFalse(tips);
					}else {
		 				Tips tips =this.xmTaskService.judgetTaskBudget(taskDb.getParentTaskid(),budgetAt,null,null,null,Arrays.asList(taskDb.getId()));
						Result.assertIsFalse(tips);

					}
				}
			}
			List<XmTask> noExecs=new ArrayList<>();
			if(can.size()>0 && xmTaskMap.containsKey("taskState")){
				String taskState= (String) xmTaskMap.get("taskState");
				if( StringUtils.hasText(taskState)  && !"0".equals(taskState) && !"9".equals(taskState)){
					for (XmTask task : can) {
						if("0".equals(task.getNtype()) && !StringUtils.hasText(task.getExecutorUserid()) && ("0".equals(task.getTaskState())||!StringUtils.hasText(task.getTaskState()))){
							noExecs.add(task);
						}
					}
				}
			}
			can=can.stream().filter(i->!noExecs.stream().filter(k->k.getId().equals(i.getId())).findAny().isPresent()).collect(Collectors.toList());
			if(can.size()>0 && xmTaskMap.containsKey("taskState")){
				String taskState= (String) xmTaskMap.get("taskState");
				if("3".equals(taskState)||"4".equals(taskState)||"9".equals(taskState)){
					xmTaskMap.put("endTime",new Date());
					xmTaskMap.put("actEndTime",new Date());
				}
			}
			if(can.size()>0){
				xmTaskMap.put("$pks",can.stream().map(i->i.getId()).collect(Collectors.toList()));

				xmTaskService.editSomeFields(xmTaskMap,can);
				for (XmTask task : can) {
					String remark= ChangeLogService.getChangeLogsString(xmTaskMap,task);
					xmRecordService.addXmTaskRecord(task.getProductId(),task.getProjectId(),task.getId(), task.getMenuId(),"任务-批量修改","修改任务："+remark);
				}
				if(xmTaskMap.containsKey("createUserid")){
					String createUserid= (String) xmTaskMap.get("createUserid");
					String createUsername= (String) xmTaskMap.get("createUsername");
					for (XmTask task : can) {
						if(!user.getUserid().equals(createUserid) && !"0".equals(task.getStatus())) {
							notifyMsgService.pushMsg(user, createUserid, createUsername, "您成为任务【" + task.getName() + "】的负责人，请注意跟进。",null);
						}
					}
				}


			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新%s个任务",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个任务无权限更新,【%s】。",no.size(),no.stream().map(i->i.getName()).collect(Collectors.joining(","))));
			}
			if(noExecs.size()>0){
				msgs.add(String.format("以下%s个任务未设置执行人，不能变更为待执行状态,【%s】。",noExecs.size(),noExecs.stream().map(i->i.getName()).collect(Collectors.joining(","))));
			}
			if(noPtypeTips.keySet().size()>0){
				for (String msg : noPtypeTips.keySet()) {
					msgs.add(msg);
				}
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			} 
		
	}


	@ApiOperation( value = "演进分析数据查询",notes="deriveList,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(value = XmTask.class,props = {"id"})
	@ApiResponses({
			@ApiResponse(code = 200,response= XmTask.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/deriveList",method=RequestMethod.GET)
	public Result deriveList(@ApiIgnore @RequestParam Map<String,Object> params){


		String id=(String) params.get("id");
		if(!StringUtils.hasText(id)){
			return Result.error("任务编号id必传");
		}
		List<XmTask> tasks=xmTaskService.deriveList(id);
		return Result.ok().setData(tasks).setTotal(tasks.size());

	}

	@ApiOperation( value = "查询互联网开放的任务的信息详情，免登录",notes="taskDetail,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(value = XmTask.class,props = {"id"})
	@ApiResponses({
			@ApiResponse(code = 200,response= XmTask.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/shareTaskDetail",method=RequestMethod.GET)
	public Result shareTaskDetail(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		String id=(String) params.get("id");
		String shareKey= (String) params.get("shareKey");
		if(!StringUtils.hasText(id)){
			return Result.error("任务编号id必传");
		}
		if(!StringUtils.hasText(shareKey)){
			//return Result.error("分享码shareKey必传");
		}

			Map<String,Object> taskDb= xmTaskService.shareTaskDetail(params);
			//  检测任务是否可被查询
			if(taskDb==null|| taskDb.isEmpty()){
				return Result.error("data-0","数据不存在");
			}
			String toTaskCenter= (String) taskDb.get("toTaskCenter");
			String crowd= (String) taskDb.get("crowd");

			if( ! "1".equals(crowd) ){
				return Result.error("crowd-0","非众包任务，无权查看");
			}
			if( ! "1".equals(toTaskCenter)){
				return Result.error("toTaskCenter-0","未开放互联网访问权限");
			}
			XmTaskCalcService.putReadNum((String) taskDb.get("id"),1);
			return Result.ok().setData(taskDb);
		
	}


	@ApiOperation( value = "查询任务的信息详情，必须登录",notes="taskDetail,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(value = XmTask.class,props = {"id"})
	@ApiResponses({
			@ApiResponse(code = 200,response= XmTask.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/taskDetail",method=RequestMethod.GET)
	public Result taskDetail(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		String id=(String) params.get("id");
		if(!StringUtils.hasText(id)){
			return Result.error("任务编号id必传");
		}

			Map<String,Object> taskDb= xmTaskService.shareTaskDetail(params);
			//  检测任务是否可被查询
			if(taskDb==null|| taskDb.isEmpty()){
				return Result.error("data-0","数据不存在");
			}
			XmTaskCalcService.putReadNum((String) taskDb.get("id"),1);

			return Result.ok().setData(taskDb);
		
	}
	@ApiOperation( value = "新增一条任务信息",notes="addXmTask,主键如果为空，后台自动生成")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTask.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmTask_addTask",name = "新增任务",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/addTask",method=RequestMethod.POST)
	public Result addTask(@RequestBody XmTaskVo xmTaskVo) {



//			if(!StringUtils.hasText(xmTaskVo.getNtype())){
//				return Result.error("节点类型ntype不能为空");
//			}
			if(!StringUtils.hasText(xmTaskVo.getPtype())){
				return Result.error("计划类型ptype不能为空");
			}
			if(!StringUtils.hasText(xmTaskVo.getProjectId())){
				return Result.error("projectId-0","项目编号不能为空");
			}

			Set<String> words=sensitiveWordService.getSensitiveWord(xmTaskVo.getName());
			if(words!=null && words.size()>0){
				return Result.error("name-sensitive-word","名字有敏感词"+words+",请修改后再提交");
			}
			words=sensitiveWordService.getSensitiveWord(xmTaskVo.getRemarks());
			if(words!=null && words.size()>0){
				return Result.error("remark-sensitive-word","备注中有敏感词"+words+",请修改后再提交");
			}
			words=sensitiveWordService.getSensitiveWord(xmTaskVo.getDescription());
			if(words!=null && words.size()>0){
				return Result.error("description-sensitive-word","详情中有敏感词"+words+",请修改后再提交");
			}

			if("3".equals(xmTaskVo.getPtype())){
				xmTaskVo.setNtype("0");
			}else{
				xmTaskVo.setNtype("1");
			}
			User user=LoginUtils.getCurrentUserInfo();
			if(StringUtils.isEmpty(xmTaskVo.getCreateUserid())){
				xmTaskVo.setCreateUserid(user.getUserid());
				xmTaskVo.setCreateUsername(user.getUsername());
			}
			if(ObjectTools.isNotEmpty(xmTaskVo.getParentTaskid()) && !"0".equals(xmTaskVo.getParentTaskid())){
				XmTask parent=this.xmTaskService.getById(xmTaskVo.getParentTaskid());
				if("3".equals(parent.getPtype())){
					return Result.error("parent-is-task","【%s】为任务，不允许下面再建立子任务",parent.getName());
				}
			}

			XmProject xmProject=xmProjectService.getProjectFromCache(xmTaskVo.getProjectId());
			Tips tips1=projectQxService.checkProjectQx(xmProject,2,user);
			Result.assertIsFalse(tips1);
			if(StringUtils.hasText(xmTaskVo.getCreateUserid()) && !xmTaskVo.getCreateUserid().equals(user.getUserid())){
 				tips1=projectQxService.checkProjectQx(xmProject,2,user,xmTaskVo.getCreateUserid(),xmTaskVo.getCreateUsername(),null);
				Result.assertIsFalse(tips1);
			}

			if("1".equals(xmProject.getMenuLink()) && "0".equals(xmTaskVo.getNtype())){
				if(!StringUtils.hasText(xmTaskVo.getMenuId())){
					return Result.error("menuId-0","该项目配置了任务必须关联需求，请关联需求后再提交");
				}
			}
			if("1".equals(xmProject.getPhaseLink()) && "0".equals(xmTaskVo.getNtype())){
				if(!StringUtils.hasText(xmTaskVo.getParentTaskid())){
					return Result.error("parentTaskid-0","该项目配置了任务必须关联上级计划，请关联计划后再提交");
				}
			}
			if(ObjectTools.isNotEmpty(xmTaskVo.getMenuId())){
				XmMenu xmMenuDb=this.xmMenusService.getById(xmTaskVo.getMenuId());
				if(xmMenuDb==null){
					return Result.error("menu-0","需求不存在");
				}
				if(!"3".equals(xmMenuDb.getDclass())){
					return Result.error("menu-dclass-not-3","该需求不是故事，不允许创建任务");
				}
				if(!groupService.checkUserExistsProjectGroup(xmTaskVo.getProjectId(),xmMenuDb.getMmUserid())){
					return Result.error("menu-mmUserid-not-in-project","需求负责人【%s】不在项目中，您无权限关联其负责的故事，请将【%s】拉入项目团队中",xmMenuDb.getMmUsername(),xmMenuDb.getMmUsername());
				}
			}

			this.initForAdd(xmTaskVo);

			if(xmTaskVo.getStartTime()==null){
				xmTaskVo.setStartTime( xmTaskVo.getCreateTime());
			}
			if(xmTaskVo.getEndTime()==null){
				xmTaskVo.setEndTime(DateUtils.addDays(xmTaskVo.getCreateTime(),14));
			}
			if( !StringUtils.hasText(xmTaskVo.getMilestone()) ){
				xmTaskVo.setMilestone("0");
			}
			if(xmTaskVo.getBudgetAt()==null){
				xmTaskVo.setBudgetAt(BigDecimal.ZERO);
			}
			if(StringUtils.isEmpty(xmTaskVo.getId())) {
				xmTaskVo.setId(this.xmTaskService.createKey("id"));
			}else{
				XmTask xmTaskQuery = new  XmTask(xmTaskVo.getId());
				if(this.xmTaskService.countByWhere(xmTaskQuery)>0){
					return Result.error("编号重复，请修改编号再提交");
				}
			}
			if(ObjectTools.isEmpty(xmTaskVo.getParentTaskid())){
				xmTaskVo.setParentTaskid("0");
			}
			this.xmTaskService.parentIdPathsCalcBeforeSave(xmTaskVo);
			if("1".equals(xmProject.getBudgetCtrl())){
				if(xmTaskVo.getBudgetAt()!=null  && xmTaskVo.getBudgetAt().compareTo(BigDecimal.ZERO)>0){
					if(xmTaskVo.getLvl()<=1){
		 				Tips tips =xmTaskService.judgetProjectBudget(xmTaskVo.getProjectId(),xmTaskVo.getBudgetAt(),null);
						Result.assertIsFalse(tips);
					}else{
		 				Tips tips =xmTaskService.judgetTaskBudget(xmTaskVo.getParentTaskid(), xmTaskVo.getBudgetAt(),null,null,null,null);
						Result.assertIsFalse(tips);
					}
				}
			}

			//新增任务技能
			if(xmTaskVo.getSkills()!=null && xmTaskVo.getSkills().size()>0){
				for (XmTaskSkill skill : xmTaskVo.getSkills()) {
					if(!StringUtils.hasText(skill.getSkillId())){
						return Result.error("skillId-0","标签编号不能为空");
					}
					if(!StringUtils.hasText(skill.getSkillName())){
						return Result.error("skillName-0","标签名称不能为空");
					}
					/**
					 *  这个不控制
					if(!StringUtils.hasText(skill.getCategoryId())){
						return Result.error("categoryId-0","标签分类不能为空");
					}
					 */
				}
			}
			xmTaskVo = xmTaskService.addTask(xmTaskVo);
			
		return Result.ok().setData(xmTaskVo);
		
	}


	
	@ApiOperation( value = "查询任务信息列表",notes="listXmTask,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmTask.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false)
	})
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTask.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTask(@ApiIgnore @RequestParam Map<String,Object> params){
		 User user=LoginUtils.getCurrentUserInfo();
		RequestUtils.transformArray(params, "ids");
		RequestUtils.transformArray(params, "tagIdList");		
		IPage page=QueryTools.initPage(params);

		String taskOut= (String) params.get("taskOut");
		if(!"1".equals(taskOut)){
			String projectId= (String) params.get("projectId");
			String linkProductId= (String) params.get("linkProductId");
			if(ObjectTools.isNotEmpty(projectId)){
				XmProject xmProject=this.xmProjectService.getProjectFromCache(projectId);
				if(xmProject==null){
					return Result.error("project-not-exists","项目不存在");
				}
				//如果是全网公开的模板，则允许查询下面所有计划任务等
				//否则，需要限定只能访问本企业数据
				if(!"9".equals(xmProject.getShowOut())){
					params.put("cbranchId",user.getBranchId());
				}
			}else if(ObjectTools.isNotEmpty(linkProductId)){
				XmProduct xmProduct=this.xmProductService.getProductFromCache(linkProductId);
				if(xmProduct==null){
					return Result.error("product-not-exists","产品不存在");
				}
				//如果是全网公开的模板，则允许查询下面所有计划任务等
				//否则，需要限定只能访问本企业数据
				if(!"9".equals(xmProduct.getShowOut())){
					params.put("cbranchId",user.getBranchId());
				}
			}else{
				params.put("cbranchId",user.getBranchId());
			}


		}
		QueryWrapper<XmTask> qw = QueryTools.initQueryWrapper(XmTask.class , params);
		List<Map<String,Object>> datas = xmTaskService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTask列表
		
	}
	
	@ApiOperation( value = "删除一条任务信息",notes="delXmTask,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	//@HasQx(value = "xm_core_xmTask_del",name = "删除任务",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTask(@RequestBody XmTask xmTask){

			User user=LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(xmTask.getId())){
				return Result.error("任务编号不能为空");
				
			}
			XmTask xmTaskDb=this.xmTaskService.getOneWithChildrenCnt(xmTask.getId());
			if(xmTaskDb==null){
				return Result.error("data-0","数据已不存在");
			}

			if(xmTaskDb.getChildrenCnt()!=null && xmTaskDb.getChildrenCnt()>0){
					return Result.error("childrenCnt-no-0","有%s个子计划、任务不能删除",xmTaskDb.getChildrenCnt());
			};
			XmProject xmProject=xmProjectService.getProjectFromCache(xmTaskDb.getProjectId());
			if(xmProject!=null && groupService.checkUserIsProjectAdm(xmProject,user.getUserid())){
 				Tips tips1=projectQxService.checkProjectQx(xmProject,2,user,xmTaskDb.getCreateUserid(),xmTaskDb.getCreateUsername(),xmTaskDb.getCbranchId());
				 Result.assertIsFalse(tips1);
			}


			if(xmTaskService.checkExistsExecuser(xmTaskDb)){
				return Result.error("existsExecuser","有待验收、待结算的执行人，不能删除");
			};


			xmTaskService.removeById(xmTaskDb);
			xmRecordService.addXmTaskRecord(xmTaskDb.getProductId(),xmTaskDb.getProjectId(), xmTaskDb.getId(), xmTaskDb.getMenuId(),"删除任务", "删除任务【"+xmTaskDb.getName()+"】");

		return Result.ok();  
		
	}


	@ApiOperation( value = "根据主键修改一条任务信息",notes="setTaskCreateUser")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTask.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	////@HasQx(value = "xm_core_xmTask_setTaskCreateUser",name = "修改任务责任人",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/setTaskCreateUser",method=RequestMethod.POST)
	public Result setTaskCreateUser(@RequestBody XmTaskVo xmTaskVo) {

			User user=LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(xmTaskVo.getId())){
				return Result.error("任务编号不能为空");
				
			}
			XmTask xmTaskDb=this.xmTaskService.selectOneObject(xmTaskVo);
			if(xmTaskDb==null){
				return Result.error("该任务不存在");
				
			}


			XmProject xmProject=xmProjectService.getProjectFromCache(xmTaskDb.getProjectId());
			
			if(!groupService.checkUserIsProjectAdm(xmProject,user.getUserid())){
 				Tips tips1=projectQxService.checkProjectQx(xmProject,2,user,xmTaskDb.getCreateUserid(),xmTaskDb.getCreateUsername(),xmTaskDb.getCbranchId());
				Result.assertIsFalse(tips1);
			}

			Tips tips1=projectQxService.checkProjectQx(xmProject,2,user,xmTaskVo.getCreateUserid(),xmTaskVo.getCreateUsername(),xmTaskVo.getCbranchId());
			Result.assertIsFalse(tips1);
			XmTask xmTask=new XmTask(xmTaskVo.getId());
			xmTask.setCreateUserid(xmTaskVo.getCreateUserid());
			xmTask.setCreateUsername(xmTaskVo.getCreateUsername());
			xmTask.setCbranchId(xmTaskVo.getCbranchId());
			 this.xmTaskService.updateSomeFieldByPk(xmTask);
			 this.xmRecordService.addXmTaskRecord(xmTaskDb.getProductId(),xmTaskDb.getProjectId(),xmTaskDb.getId(),xmTaskDb.getMenuId(),"项目-修改任务责任人","修改任务【"+xmTaskDb.getName()+"】责任人。原责任人【"+xmTaskDb.getCreateUsername()+"】，新责任人【"+xmTask.getCreateUsername()+"】");
			
		return Result.ok();
		
	}
	@ApiOperation( value = "根据主键修改一条任务信息",notes="editXmTask")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTask.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmTask_editXmTask",name = "修改任务",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmTask(@RequestBody XmTaskVo xmTaskVo) {

			User user=LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(xmTaskVo.getId())){
				return Result.error("任务编号不能为空");
				
			}

			XmTask xmTaskDb=this.xmTaskService.selectOneObject(xmTaskVo);
			if(xmTaskDb==null){
				return Result.error("data-0","任务已不存在");
			}
			if("1".equals(xmTaskDb.getNtype())){
				 if("0".equals(xmTaskVo.getNtype()) && xmTaskDb.getChildrenCnt()!=null && xmTaskDb.getChildrenCnt()>0){
					 return Result.error("ntype-not-right","当前为计划节点，并且具有"+xmTaskDb.getChildrenCnt()+"个子节点，不能变更为任务节点");
				 }
			}else{
				if(xmTaskDb.getChildrenCnt()!=null && xmTaskDb.getChildrenCnt()>0){
					xmTaskVo.setNtype("1");
				}
			}

			XmProject xmProject=xmProjectService.getProjectFromCache(xmTaskDb.getProjectId());
			Tips tips1=projectQxService.checkProjectQx(xmProject,2,user,xmTaskDb.getCreateUserid(),xmTaskDb.getCreateUsername(),xmTaskDb.getCbranchId());
			Result.assertIsFalse(tips1);

			this.xmTaskService.parentIdPathsCalcBeforeSave(xmTaskVo);
			if(xmTaskVo.getBudgetAt()==null)xmTaskVo.setBudgetAt(BigDecimal.ZERO);
			if(xmTaskDb.getBudgetAt()==null)xmTaskDb.setBudgetAt(BigDecimal.ZERO);
			List<String> excludeIds=new ArrayList<>();
			excludeIds.add(xmTaskDb.getId());
			if( "1".equals(xmProject.getBudgetCtrl()) && xmTaskDb.getBudgetAt().compareTo(xmTaskVo.getBudgetAt())!=0){
				if(xmTaskVo.getLvl()<=1){
	 				Tips tips =xmTaskService.judgetProjectBudget(xmTaskDb.getProjectId(), xmTaskVo.getBudgetAt(),excludeIds);
	 				Result.assertIsFalse(tips);
				}else if(StringUtils.hasText(xmTaskDb.getParentTaskid())){
	 				Tips tips =xmTaskService.judgetTaskBudget(xmTaskDb.getParentTaskid(), xmTaskVo.getBudgetAt(),null,null,null,excludeIds);
					Result.assertIsFalse(tips);

				}
			}

			xmTaskService.updateTask(xmTaskVo,xmTaskDb);
			
		return Result.ok().setData(xmTaskVo);
		
	}
	@ApiOperation( value = "根据主键修改一条任务信息",notes="editXmTask")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTask.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	//@HasQx(value = "xm_core_xmTask_editTime",name = "修改任务时间",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/editTime",method=RequestMethod.POST)
	public Result editTime(@RequestBody XmTask xmTask) {

			User user=LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(xmTask.getId())){
				return Result.error("任务编号不能为空");
				
			}
			XmTask xmTaskDb=xmTaskService.selectOneObject(xmTask);
			if(xmTaskDb==null){
				return Result.error("data-0","任务已不存在");
			}
			XmProject xmProject=xmProjectService.getProjectFromCache(xmTaskDb.getProjectId());
			
			Tips tips1=projectQxService.checkProjectQx(xmProject,2,user,xmTaskDb.getCreateUserid(),xmTaskDb.getCreateUsername(),xmTaskDb.getCbranchId());
			if(!tips1.isOk()){
 				tips1=projectQxService.checkProjectQx(xmProject,2,user,xmTaskDb.getExecutorUserid(),xmTaskDb.getExecutorUsername(),null);
				Result.assertIsFalse(tips1);
			}
			xmTaskService.updateTime(xmTask,xmTaskDb);
			
		return Result.ok();
		
	}


	public void initForAdd(XmTask g){
		User user=LoginUtils.getCurrentUserInfo();
		g.setCbranchId(user.getBranchId());
		if(ObjectTools.isEmpty(g.getCreateUserid())){
			g.setCreateUserid(user.getUserid());
			g.setCreateUsername(user.getUsername());
		}
		if("3".equals(g.getPtype())){
			g.setNtype("0");
		}else{
			g.setNtype("1");
		}
		g.setExecutorUserid(null);
		g.setExecutorUsername(null);
		g.setCbranchId(user.getBranchId());
		g.setExeUserids(null);
		g.setExeUsernames(null);
		g.setCdeptid(user.getDeptid());
		g.setCrmSup("0");
		g.setBidStep("0");
		g.setTaskOut("0");
		g.setCrowd("0");
		g.setEtoPlatTime(null);
		g.setEtoDevTime(null);
		g.setEbackTime(null);
		g.setHotEtime(null);
		g.setHotStime(null);
		g.setBrowseTimes(null);
		g.setActStartTime(null);
		g.setActEndTime(null);
		g.setOshare("0");
		g.setQuoteFinalAt(BigDecimal.ZERO);
		g.setTopEtime(null);
		g.setTopStime(null);
		g.setEstate("0");
		g.setInitRate(0);
		g.setRate(0);
		g.setActWorkload(BigDecimal.ZERO);
		g.setCreateTime(new Date());
		g.setLtime(new Date());
		g.setSumActWorkload(BigDecimal.ZERO);
		g.setSonSumInitWorkload(BigDecimal.ZERO);
		g.setSonSumInitWorkload(BigDecimal.ZERO);
		g.setSonSumMactWorkload(BigDecimal.ZERO);
		g.setMactWorkload(BigDecimal.ZERO);
		if(ObjectTools.isEmpty(g.getCalcType())){
			if("0".equals(g.getNtype())){
				g.setCalcType("1");
			}else{
				g.setCalcType("2");
			}
		}
		if(g.getBudgetAt()==null)g.setBudgetAt(BigDecimal.ZERO);
	}

	@ApiOperation( value = "批量导入任务-从需求导入",notes="")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	//@HasQx(value = "xm_core_xmTask_batchImportFromTemplate",name = "从模板导入任务",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/batchImportFromMenu",method=RequestMethod.POST)
	public Result batchImportFromMenu(@RequestBody ImportTaskByMenuVo batchImportVo) {

		List<XmMenu> xmMenus=batchImportVo.getXmMenus();
		User user=LoginUtils.getCurrentUserInfo();
		if("some".equals(batchImportVo.getSourceType()) && (xmMenus==null || xmMenus.size()==0)){
			return Result.error("需求列表不能为空");
		}
		if("all".equals(batchImportVo.getSourceType()) && ObjectTools.isEmpty(batchImportVo.getSourceProductId())){
			return Result.error("sourceProductId-0","请上送源产品编号");
		}
		if(!StringUtils.hasText(batchImportVo.getProjectId())){
			return Result.error("projectId-0","请上送目标项目编号");
		}

		if("all".equals(batchImportVo.getSourceType())){
			xmMenus=this.xmMenuService.list(new LambdaQueryWrapper<XmMenu>().eq(XmMenu::getProductId,batchImportVo.getSourceProductId()));
			if(xmMenus.size()==0){
				return Result.error("menus-data-0","产品【%s】没有需求项可导入",batchImportVo.getSourceProductId());
			}
		}
		if(xmMenus.size()==0){
			return Result.error("需求列表不能为空");
		}
		List<XmTask> xmTasks=new ArrayList<>();
		for (XmMenu xmMenu : xmMenus) {
			XmTask xmTask=PubTool.xmMenuToXmTask(xmMenu);
			xmTask.setProjectId(batchImportVo.getProjectId());
			if("0".equals(xmMenu.getNtype())){
				xmTask.setMenuId(xmMenu.getMenuId());
				xmTask.setProductId(xmMenu.getProductId());
				xmTask.setMenuName(xmMenu.getMenuName());
			}
			xmTasks.add(xmTask);
		}
		ImportTaskByTplVo vo=new ImportTaskByTplVo();
		vo.setXmTasks(xmTasks);
		vo.setDays(batchImportVo.getDays());
		vo.setParentTaskid(batchImportVo.getParentTaskid());
		vo.setProjectId(batchImportVo.getProjectId());
		vo.setSourceType("some");
		return this.batchImportFromTemplate(vo);
	}

	@ApiOperation( value = "批量导入任务-从模板导入",notes="batchDelXmTask,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	//@HasQx(value = "xm_core_xmTask_batchImportFromTemplate",name = "从模板导入任务",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/batchImportFromTemplate",method=RequestMethod.POST)
	public Result batchImportFromTemplate(@RequestBody ImportTaskByTplVo batchImportVo) {

			List<XmTask> xmTasks=batchImportVo.getXmTasks();
			User user=LoginUtils.getCurrentUserInfo();
 			if("some".equals(batchImportVo.getSourceType()) && (xmTasks==null || xmTasks.size()==0)){
				return Result.error("工作项列表不能为空");
			}
			 if("all".equals(batchImportVo.getSourceType()) && ObjectTools.isEmpty(batchImportVo.getSourceProjectId())){
				 return Result.error("sourceProjectId-0","请上送源项目编号");
			 }
			if(!StringUtils.hasText(batchImportVo.getProjectId())){
				return Result.error("projectId-0","请上送目标项目编号");
			}

			String projectId=batchImportVo.getProjectId();
			XmProject xmProject=xmProjectService.getProjectFromCache(projectId);
			Tips tips1=projectQxService.checkProjectQx(xmProject,2,user);
			Result.assertIsFalse(tips1);
			if("all".equals(batchImportVo.getSourceType())){
				xmTasks=this.xmTaskService.list(new LambdaQueryWrapper<XmTask>().eq(XmTask::getProjectId,batchImportVo.getSourceProjectId()));
				if(xmTasks.size()==0){
					return Result.error("tasks-data-0","项目【%s】没有工作项可导入",batchImportVo.getSourceProjectId());
				}
			}
			for (XmTask xmTask : xmTasks) {
				if("3".equals(xmTask.getPtype())) {
					xmTask.setNtype("0");
				}else {
					xmTask.setNtype("1");
				}
			}

			// 是否关联了上级计划
			if("1".equals(xmProject.getPhaseLink()) && (ObjectTools.isEmpty(batchImportVo.getParentTaskid()) ||"0".equals(batchImportVo.getParentTaskid()))){
				for (XmTask k : xmTasks) {
					if(NumberUtil.getInteger(k.getPtype())>1 && (ObjectTools.isEmpty(k.getPreTaskid()) || "0".equals(k.getParentTaskid()))){
						return Result.error("parentTaskid-0","该项目配置了任务必须关联上级计划，请关联计划后再提交.缺上级计划的任务【%s】",k.getName());
					}
				}
			}

			// 是否关联了需求
			if("1".equals(xmProject.getMenuLink())){
				for (XmTask xmTask : xmTasks) {
					if(!StringUtils.hasText(xmTask.getMenuId())||"0".equals(xmTask.getMenuId())){
						return Result.error("menuId-0","该项目配置了任务必须关联需求，请关联需求后再提交;缺需求的任务：【%s】",xmTask.getName());
					}
				}
			}
			XmTask parent;
			if(ObjectTools.isNotEmpty(batchImportVo.getParentTaskid()) && !"0".equals(batchImportVo.getParentTaskid())){
				parent=this.xmTaskService.getById(batchImportVo.getParentTaskid());
				if(parent==null){
					return Result.error("parent-task-data-0","上级【%s】不存在",batchImportVo.getParentTaskid());
				}
				if("0".equals(parent.getNtype())){
					return Result.error("ntype-0","不能导入计划/任务到任务中。【%s】属于任务，不允许下挂任何子孙节点",parent.getName());
				}
			} else {
                parent = null;
            }
        	Map<String,String> newIdMap=new HashMap<>();
			if(StringUtils.hasText(batchImportVo.getParentTaskid())){
				newIdMap.put(batchImportVo.getParentTaskid(),batchImportVo.getParentTaskid());
			}else{
				batchImportVo.setParentTaskid("0");

				newIdMap.put(batchImportVo.getParentTaskid(),batchImportVo.getParentTaskid());
			}
			for (XmTask xmTask : xmTasks) {
				newIdMap.put(xmTask.getId(),this.xmTaskService.createKey("id"));
			}
 			for (XmTask g : xmTasks) {
				this.initForAdd(g);
 				g.setId(newIdMap.get(g.getId()));
 				if(StringUtils.hasText(g.getParentTaskid())){
 					if(newIdMap.containsKey(g.getParentTaskid())){
 						g.setParentTaskid(newIdMap.get(g.getParentTaskid()));
					}else{
						 g.setParentTaskid(batchImportVo.getParentTaskid());
					}
				}else{
					 g.setParentTaskid(batchImportVo.getParentTaskid());
				}
 				g.setPbranchId(xmProject.getBranchId());

				g.setProjectId(projectId);

				//前置处理
				if(ObjectTools.isNotEmpty(g.getPreTaskid())){
					String[] preTaskids=g.getPreTaskid().split(",");
					List<String> newPres=new ArrayList<>();
					for (String preTaskid : preTaskids) {
						if(newIdMap.containsKey(preTaskid)){
							newPres.add(newIdMap.get(preTaskid));
						}
					}
					if(newPres.size()>0){
						g.setPreTaskid(CollectionUtil.join(newPres,","));
					}
				}
			}
		/**
		 * 计算子节点数量
		 */
		//任务,如果有子节点，自动升级为计划
		for (XmTask xmTask : xmTasks) {
			List<XmTask> childs=xmTasks.stream().filter(k->xmTask.getId().equals(k.getParentTaskid())).collect(Collectors.toList());
			if(childs.size()>0){
				xmTask.setChildrenCnt(childs.size());
				xmTask.setNtype("1");
				if(!("1".equals(xmTask.getPtype()) ||"2".equals(xmTask.getPtype()))){
					xmTask.setPtype("2");
				}
			}else{
				if("3".equals(xmTask.getPtype())){
					xmTask.setNtype("0");
				}else if("1".equals(xmTask.getPtype()) ||"2".equals(xmTask.getPtype())){
					xmTask.setNtype("1");
				}else{
					if("0".equals(xmTask.getNtype())){
						xmTask.setPtype("3");
					}else if("1".equals(xmTask.getNtype())){
						xmTask.setPtype("2");
					}
				}
			}
		}

		for (XmTask xmTask : xmTasks) {
			xmTask.setSubTaskCnt(xmTasks.stream().filter(k->xmTask.getId().equals(k.getParentTaskid()) && "0".equals(k.getNtype())).collect(Collectors.toList()).size());
			xmTask.setSubPlanCnt(xmTasks.stream().filter(k->xmTask.getId().equals(k.getParentTaskid()) && "1".equals(k.getNtype())).collect(Collectors.toList()).size());

			xmTask.setChildrenCnt(xmTask.getSubPlanCnt()+xmTask.getSubTaskCnt());
		}


			xmTaskService.parentIdPathsCalcBeforeSave(xmTasks);

			//排序号生成 按##.##.##格式生成
 			PubTool.initTasksSeqNo(parent,xmTasks);

			 // 日期处理
		for (XmTask xmTask : xmTasks) {
			if(xmTask.getStartTime()!=null){
				xmTask.setStartTime(DateUtil.offsetDay(xmTask.getStartTime(),batchImportVo.getDays()));
			}
			if(xmTask.getEndTime()!=null){
				xmTask.setEndTime(DateUtil.offsetDay(xmTask.getEndTime(),batchImportVo.getDays()));
			}
		}

			List<XmTask> tasksLvl1=xmTasks.stream().filter(i->i.getLvl()<=1).collect(Collectors.toList());
			if("1".equals(xmProject.getBudgetCtrl())){
				if(tasksLvl1.size()>0){
					BigDecimal totalTaskBudgetAt=BigDecimal.ZERO;
					for (XmTask task : tasksLvl1) {
						totalTaskBudgetAt=totalTaskBudgetAt.add(task.getBudgetAt());
					}
					if(totalTaskBudgetAt.compareTo(BigDecimal.ZERO)>0){
		 				Tips tips =xmTaskService.judgetProjectBudget(projectId,totalTaskBudgetAt,tasksLvl1.stream().map(i->i.getId()).collect(Collectors.toList()));
						if(!tips.isOk()){
							return Result.error(tips.getMsg()+" 相关任务【"+tasksLvl1.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
 						}
					}
				}else{
					List<XmTask> finalXmTasks = xmTasks;
					List<XmTask> tasks=xmTasks.stream().filter(i->!finalXmTasks.stream().filter(k->k.getId().equals(i.getParentTaskid())).findAny().isPresent()).collect(Collectors.toList());
					tasks=tasks.stream().filter(i->StringUtils.hasText(i.getParentTaskid())).collect(Collectors.toList());
					if(tasks.size()>0){
						Set<String> parentTaskIdSet=tasks.stream().map(i->i.getParentTaskid()).collect(Collectors.toSet());
						for (String pid : parentTaskIdSet) {
							BigDecimal childBudgetAt=BigDecimal.ZERO;
							List<XmTask> childs=xmTasks.stream().filter(i->pid.equals(i.getParentTaskid())).collect(Collectors.toList());
							for (XmTask child : childs) {
								childBudgetAt=childBudgetAt.add(child.getBudgetAt());
							}
							if(childBudgetAt.compareTo(BigDecimal.ZERO)>0){
				 				Tips tips = xmTaskService.judgetTaskBudget(pid,childBudgetAt,null,null,null,childs.stream().map(i->i.getId()).collect(Collectors.toList()));
								if(!tips.isOk()){
									return Result.error("budget-not-enought",tips.getMsg()+" 相关任务【"+childs.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
								}
							}
						}
					}
				}
			}


				xmTaskService.batchImportFromTemplate(xmTasks);
		if(parent!=null){
			// 设置默认值，防止空指针异常
			parent.setChildrenCnt(NumberUtil.getInteger(parent.getChildrenCnt(),0));
			parent.setSubPlanCnt(NumberUtil.getInteger(parent.getSubPlanCnt(),0));
			parent.setSubTaskCnt(NumberUtil.getInteger(parent.getSubTaskCnt(),0));
			List<XmTask> childs=xmTasks.stream().filter(k->parent.getId().equals(k.getParentTaskid())).collect(Collectors.toList());
			if(childs.size()>0){
				parent.setChildrenCnt(childs.size()+NumberUtil.getInteger(parent.getChildrenCnt(),0));
				parent.setNtype("1");
				// 计算子计划数目
				List<XmTask> planChilds=childs.stream().filter(k->"1".equals(k.getNtype())).collect(Collectors.toList());
				parent.setSubPlanCnt(planChilds.size()+NumberUtil.getInteger(parent.getSubPlanCnt(),0));
				parent.setSubTaskCnt(parent.getChildrenCnt()-NumberUtil.getInteger(parent.getSubPlanCnt(),0));
			}
		}
			if(parent!=null){
				this.xmTaskService.lambdaUpdate().set(XmTask::getChildrenCnt,parent.getChildrenCnt()).set(XmTask::getSubPlanCnt,parent.getSubPlanCnt()).set(XmTask::getSubTaskCnt,parent.getSubTaskCnt()).eq(XmTask::getId,parent.getId()).update();
			}
				for (XmTask t : xmTasks) {

					if(!user.getUserid().equals(t.getCreateUserid()) && !"0".equals(t.getStatus())) {
						notifyMsgService.pushMsg(user.getUserid(), t.getCreateUserid(),  "您成为任务【" + t.getName() + "】的负责人，请注意跟进。","");
					}
					xmRecordService.addXmTaskRecord(t.getProductId(),t.getProjectId(), t.getId(),t.getMenuId(), "任务-批量新增", "新增任务"+t.getName());
					
				}
		return Result.ok();
	}

	/**
	 *
	@ApiOperation( value = "批量将任务与一个项目计划关联",notes="")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	//@HasQx(value = "xm_core_xmTask_batchRelTasksWithPhase",name = "批量将任务与一个项目计划关联",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/batchRelTasksWithPhase",method=RequestMethod.POST)
	public Result batchRelTasksWithPhase(@RequestBody BatchRelTasksWithPhase tasksPhase) {

			User user=LoginUtils.getCurrentUserInfo();

			if(tasksPhase==null){
				return Result.error("params-0","参数不能为空");
			}

			String phaseId=tasksPhase.getPhaseId();
			if( !StringUtils.hasText(phaseId) ){
				return Result.error("phaseId-0","项目计划编号不能为空");
			}
			XmProjectPhase xmProjectPhaseDb=this.xmProjectPhaseService.selectOneObject(new XmProjectPhase(phaseId));


			if(xmProjectPhaseDb==null){
				return Result.error("phase-0","计划【"+xmProjectPhaseDb.getName()+"】已不存在");
			}
			if("1".equals(xmProjectPhaseDb.getNtype())){
				return Result.error("phase-ntype-1","【"+xmProjectPhaseDb.getName()+"】属于计划集，无需关联任务。");
			}
			XmProject xmProjectDb=this.xmProjectService.getProjectFromCache(xmProjectPhaseDb.getProjectId());
			if(xmProjectDb==null){
				return Result.error("project-0","项目已不存在");
			}
			if("8".equals(xmProjectDb.getStatus())){
				return Result.error("project-status-8","项目已完成,不能再修改");
			}
			if( "9".equals(xmProjectDb.getStatus())){
				return Result.error("project-status-9","项目关闭,不能再修改");
			}
			List<Dept> pgroups=groupService.getProjectGroupVoList(xmProjectDb.getId());
			if(pgroups==null || pgroups.size()==0){
				return Result.error("group-0","该项目还未建立项目团队，请先进行团队成员维护");
			}
			List<XmTask> allowTasks=new ArrayList<>();
			List<XmTask> noAllowTasks=new ArrayList<>();
			List<XmTask> ntype1Tasks=new ArrayList<>();
			List<XmTask> tasksDb=this.xmTaskService.selectTaskListByIds(tasksPhase.getTaskIds());
			for (XmTask task : tasksDb) {
				if("1".equals(task.getNtype())){
					ntype1Tasks.add(task);
					continue;
				}
				boolean isMyCreate=user.getUserid().equals(task.getCreateUserid());
				if(isMyCreate){
					allowTasks.add(task);
				}else{
					boolean isHead=groupService.checkUserIsOtherUserTeamHeadOrAss(pgroups,task.getCreateUserid(),user.getUserid());
					if(!isHead){
						noAllowTasks.add(task);
					}else {
						allowTasks.add(task);
					}
				}

			}
			List<String> msgs=new ArrayList<>();
			if(allowTasks.size()>0){
				BatchRelTasksWithPhase tasksWithPhase=new BatchRelTasksWithPhase();
				tasksWithPhase.setPhaseId(phaseId);
				tasksWithPhase.setTaskIds(allowTasks.stream().map(i->i.getId()).collect(Collectors.toList()));
				xmTaskService.batchRelTasksWithPhase(tasksWithPhase);
			}
			msgs.add("成功将"+allowTasks.size()+"个任务与计划关联。");
			for (XmTask t : allowTasks) {
				xmRecordService.addXmTaskRecord(t.getProjectId(), t.getId(), "任务-批量更新任务", "将任务"+t.getName()+"与计划【"+xmProjectPhaseDb.getId()+"-"+xmProjectPhaseDb.getName()+"】关联",null,null);
			}
			if(ntype1Tasks.size()>0){
				msgs.add("以下"+ntype1Tasks.size()+"个任务属于计划项，无需关联计划。【"+ntype1Tasks.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
			}
			if(noAllowTasks.size()>0){
				msgs.add("以下"+noAllowTasks.size()+"个任务无权操作，只有任务负责人、项目经理、组长可以批量将任务与项目计划进行关联,【"+noAllowTasks.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
			}
			if(allowTasks.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining(" ")));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining(" ")));
			}

		return Result.ok();
		
	}
	 **/
	@ApiOperation( value = "批量将多个任务与一个用户需求关联",notes="")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	//@HasQx(value = "xm_core_xmTask_batchRelTasksWithMenu",name = "批量将任务与一个用户需求关联",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/batchRelTasksWithMenu",method=RequestMethod.POST)
	public Result batchRelTasksWithMenu(@RequestBody BatchRelTasksWithMenu tasksMenu) {

			User user=LoginUtils.getCurrentUserInfo();

			if(tasksMenu==null||tasksMenu.getTaskIds()==null||tasksMenu.getTaskIds().size()==0 ){
				return Result.error("tasks-0","任务列表不能为空");
			};
			if(!StringUtils.hasText(tasksMenu.getMenuId()) ){
				return Result.error("menuId-0","需求编号不能为空");
			};
			//XmMenu xmMenuDb= menuOperQxService.getUserCanOpMenuById(tasksMenu.getMenuId(), user.getUserid(), false);
			XmMenu xmMenuDb=xmMenusService.getById(tasksMenu.getMenuId());
 			if(xmMenuDb==null){
				return Result.error("menu-0","故事已不存在");
			}
			if(!"3".equals(xmMenuDb.getDclass())){
				return Result.error("menuId-dclass-not-3","该需求不属于故事");
			}
			if("8".equals(xmMenuDb.getStatus())){
				return Result.error("menu-status-8","需求已下线");
			}

			if("9".equals(xmMenuDb.getStatus())){
				return Result.error("menu-status-9","需求已删除");
			}
			List<XmTask> tasksDb=this.xmTaskService.selectTaskListByIds(tasksMenu.getTaskIds());
			if(tasksDb==null || tasksDb.size()==0){
				return Result.error("tasks-not-exists","任务不存在");
			}
			List<XmTask> allowTasks=new ArrayList<>();
			List<XmTask> ntype1Tasks=new ArrayList<>();
			List<XmTask> noAllowTasks=new ArrayList<>();

			List<XmTask> notSameProjectTasks=new ArrayList<>();
 			for (XmTask xmTask : tasksDb) {
				xmTask.setLtime(new Date());
				if("1".equals(xmTask.getNtype())){
					ntype1Tasks.add(xmTask);
				}
			}
			Map<String,List<XmTask>> projectTasksMap=new HashMap<>();
			for (XmTask xmTask : tasksDb) {
				 List<XmTask> projectTasks=projectTasksMap.get(xmTask.getProjectId());
				 if(projectTasks==null){
					projectTasks=new ArrayList<>();
					projectTasksMap.put(xmTask.getProjectId(),projectTasks);
				 }
				projectTasks.add(xmTask);
			}
			
			for (Map.Entry<String, List<XmTask>> pt : projectTasksMap.entrySet()) {
				XmProject xmProjectDb=this.xmProjectService.getProjectFromCache(pt.getKey());
 				Tips tips1=projectQxService.checkProjectQx(xmProjectDb,0,user);
				 //判断我根需求人员是否在同一个项目组
				boolean sameProject=this.groupService.checkIsSameProject(pt.getKey(),user.getUserid(),xmMenuDb.getMmUserid());
				if(!tips1.isOk() ){
						noAllowTasks.addAll(pt.getValue());
				}else if(!sameProject){
					notSameProjectTasks.addAll(pt.getValue());
				}else{
					for (XmTask xmTask : pt.getValue()) {
		 				tips1=projectQxService.checkProjectQx(xmProjectDb,0,user, xmTask.getCreateUserid(),xmTask.getCreateUsername(),xmTask.getCbranchId());
						if(!tips1.isOk()){
							noAllowTasks.add(xmTask);
						}
					}
				}
			}

			allowTasks=tasksDb.stream().filter(i->!noAllowTasks.stream().filter(k->k.getId().equals(i.getId())).findAny().isPresent()).collect(Collectors.toList());
			allowTasks=allowTasks.stream().filter(i->!ntype1Tasks.stream().filter(k->k.getId().equals(i.getId())).findAny().isPresent()).collect(Collectors.toList());
			allowTasks=allowTasks.stream().filter(i->!notSameProjectTasks.stream().filter(k->k.getId().equals(i.getId())).findAny().isPresent()).collect(Collectors.toList());


			List<String> msgs=new ArrayList<>();
			if(allowTasks.size()>0){
				BatchRelTasksWithMenu tasksWithMenu=new BatchRelTasksWithMenu();
				tasksWithMenu.setMenuId(xmMenuDb.getMenuId());
				tasksWithMenu.setTaskIds(allowTasks.stream().map(i->i.getId()).collect(Collectors.toList()));
				xmTaskService.batchRelTasksWithMenu(tasksWithMenu,xmMenuDb);
			}
			msgs.add("成功将"+allowTasks.size()+"个任务与需求关联。");
			for (XmTask t : allowTasks) {
				xmRecordService.addXmTaskRecord(t.getProductId(),t.getProjectId(), t.getId(),xmMenuDb.getMenuId(), "批量更新任务", "将任务"+t.getName()+"与需求【"+xmMenuDb.getMenuId()+"-"+xmMenuDb.getMenuName()+"】关联");
			}
			if(ntype1Tasks.size()>0){
				msgs.add("以下"+ntype1Tasks.size()+"个任务属于计划项，无需关联需求。【"+ntype1Tasks.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
			}
			if(noAllowTasks.size()>0){
				msgs.add("以下"+noAllowTasks.size()+"个任务无权操作，只有任务负责人、项目经理、组长、产品组组长、需求管理组人员可以批量将任务与需求进行关联,【"+noAllowTasks.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
			}
			if(notSameProjectTasks.size()>0){
				String projectNames=notSameProjectTasks.stream().map(k->k.getProjectName()).collect(Collectors.toSet()).stream().collect(Collectors.joining(","));
				msgs.add(String.format("项目【%s】未将需求人员【%s】加入到项目组，因此无权限将任务关联到【%s】负责的故事中",projectNames,xmMenuDb.getMmUsername(),xmMenuDb.getMmUsername()));
			}
			if(allowTasks.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining(" ")));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining(" ")));
			}
		
	} 
	/**
	*/
	@ApiOperation( value = "根据主键列表批量删除任务信息",notes="batchDelXmTask,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	//@HasQx(value = "xm_core_xmTask_batchDel",name = "批量删除任务",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmTask(@RequestBody List<XmTask> xmTasks) {


			User user=LoginUtils.getCurrentUserInfo();

			if(xmTasks==null || xmTasks.size()==0){
				return Result.error("任务列表不能为空");
				
			}
			XmTask xmTask=xmTasks.get(0);
			if(!StringUtils.hasText(xmTask.getId())){
				return Result.error("id-0","任务编号不能为空");
			}
			XmTask xmTaskDb=this.xmTaskService.getById(xmTask.getId());
			if(xmTaskDb==null){
				return Result.error("data-0","计划任务已不存在");
			}
			String projectId=xmTaskDb.getProjectId();
			XmProject xmProject=xmProjectService.getProjectFromCache(projectId);
			
			Tips tips=projectQxService.checkProjectQx(xmProject,2,user);
			Result.assertIsFalse(tips);
			List<XmTask> canOper=new ArrayList<>();
			List<XmTask> noOper=new ArrayList<>();
			Map<String,Tips> noTipsMap=new HashMap<>();
			Map<String,XmTask> delNodesDbMap=this.xmTaskService.selectTasksMapByTasks(xmTasks);
			for (XmTask node : delNodesDbMap.values()) {
				if(!projectId.equals(node.getProjectId()) ){
					return Result.error("not-same-project","所有任务必须同属于一个项目");
				}
			}
			if(groupService.checkUserIsProjectAdm(xmProject,user.getUserid())){
				canOper.addAll(delNodesDbMap.values());
			}else{
				for (XmTask node : delNodesDbMap.values()) {
	 				Tips tips1=projectQxService.checkProjectQx(xmProject,2,user,node.getCreateUserid(),node.getCreateUsername(),node.getCbranchId());
					if(!tips1.isOk()){
						noOper.add(node);
						noTipsMap.put(tips1.getMsg(),tips1);
					}else {
						canOper.add(node);
					}

				}
			}

			if(canOper.size()==0){
				return Result.error("noqx-del",String.format("无权限删除，原因【%s】",noTipsMap.keySet().stream().collect(Collectors.joining(";"))));
			}

			List<XmTask> existsExecuserList=new ArrayList<>();
			List<XmTask> noExecuserList=new ArrayList<>();
			if(canOper.size()>0){
				for (XmTask node : canOper) {
					if(this.xmTaskService.checkExistsExecuser(node)){
						existsExecuserList.add(node);
					}else{
						noExecuserList.add(node);
					}
				}
			}
			List<XmTask> hadChildNodes=new ArrayList<>();
			List<XmTask> canDelNodes=new ArrayList<>();
			if(noExecuserList.size()>0){
				for (XmTask node : noExecuserList) {
					boolean canDel=this.xmTaskService.checkCanDelAllChild(node,noExecuserList);
					if(canDel){
						canDelNodes.add(node);
					}else{
						hadChildNodes.add(node);
					}
				}
			}
			if(canDelNodes.size()>0){
				this.xmTaskService.doBatchDelete(canDelNodes);
				xmRecordService.addXmTaskRecord(canDelNodes,"任务-批量删除","删除任务");
			}
			List<String> msgs=new ArrayList<>();
			msgs.add("删除了"+canDelNodes.size()+"个任务。");
			if(hadChildNodes.size()>0){
				msgs.add("以下"+hadChildNodes.size()+"个任务存在未删除的子任务，不能删除。如确实需要删除任务，请先删除子任务。【"+hadChildNodes.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
			}
			if(existsExecuserList.size()>0){
				msgs.add("以下"+existsExecuserList.size()+"个任务存在待结算的执行人，不能删除。如确实需要删除，请在【任务编辑->执行人】中删除执行人后再删除任务【"+existsExecuserList.stream().map(i->i.getName()).collect(Collectors.joining(","))+"】");
			}
			if(noOper.size()>0){
				msgs.add(String.format("以下%s个任务无权限删除，原因【%s】",noOper.size(),noTipsMap.keySet().stream().collect(Collectors.joining(";"))));
			}
			if(canDelNodes.size()==0){
				return Result.error(msgs.stream().collect(Collectors.joining(" ")));
			}else{
				return Result.ok(msgs.stream().collect(Collectors.joining(" ")));
			}
	}



	/***/
	@ApiOperation( value = "批量修改任务的上级",notes="batchChangeParentTask,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	//@HasQx(value = "xm_core_xmTask_batchChangeParentTask",name = "批量修改任务的上级",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/batchChangeParentTask",method=RequestMethod.POST)
	public Result batchChangeParentTask(@RequestBody List<BatchChangeParentTaskVo> xmTasksVo) {

			User user=LoginUtils.getCurrentUserInfo();

			if(xmTasksVo==null || xmTasksVo.size()==0){
				return Result.error("任务列表不能为空");
				
			}
			Set<String> childIds=new HashSet<>();
			Set<String> parentTaskids=new HashSet<>();
			Map<String,String> newIdLinks=new HashMap<>();
		for (BatchChangeParentTaskVo link : xmTasksVo) {
			if(ObjectTools.isEmpty(link.getParentTaskid())||"0".equals(link.getParentTaskid())){
				link.setParentTaskid("0");
			}else{
				parentTaskids.add(link.getParentTaskid());
			}

			if(ObjectTools.isEmpty(link.getId())){
				return Result.error("任务编号不能为空");
			}
			childIds.add(link.getId());
			newIdLinks.put(link.getId(),link.getParentTaskid());
		}


 			List<XmTask> childTasks=this.xmTaskService.selectTaskListByIds(childIds.stream().collect(Collectors.toList()));
		List<XmTask> parentTasks=new ArrayList<>();
		if(!parentTaskids.isEmpty()){
			parentTasks=this.xmTaskService.selectTaskListByIds(parentTaskids.stream().collect(Collectors.toList()));

		}
		if(childTasks==null || childTasks.size()==0){
			return Result.error("data-0","数据不存在");
		}

		// 过滤掉新旧上级一致的任务
		childTasks=childTasks.stream().filter(k->!k.getParentTaskid().equals(newIdLinks.get(k.getId()))).collect(Collectors.toList());
		if(childTasks==null || childTasks.size()==0){
			return Result.error("id-link-same-0","新旧上级一致，无须保存");
		}
		// 不允许阶段向计划、任务迁移
		List<XmTask> phaseTasks=childTasks.stream().filter(k->"1".equals(k.getPtype())).collect(Collectors.toList());
		if(phaseTasks.size()>0){
			return Result.error("phase-not-allowt-to-23","以下工作项为【阶段】，不允许挂载到任何工作项下。【%s】",phaseTasks.stream().map(k->k.getName()).collect(Collectors.joining(",")));
		}
		String projectId=childTasks.get(0).getProjectId();
		Map<String,XmTask> xmTaskMap=new HashMap<>();
		for (XmTask xmTask : childTasks) {
			xmTaskMap.put(xmTask.getId(),xmTask);
			// 进行是否同项目检测
			if(!projectId.equals(xmTask.getProjectId())){
				return Result.error("task-must-same-project","所有任务必须同项目，%s归属项目为%s,与其它任务归属不一致",xmTask.getName(),xmTask.getProjectId());
			}
		}
		for (XmTask xmTask : parentTasks) {
			xmTaskMap.put(xmTask.getId(),xmTask);
			// 进行是否同项目检测
			if(!projectId.equals(xmTask.getProjectId())){
				return Result.error("task-must-same-project","所有任务必须同项目，%s归属项目为%s,与其它任务归属不一致",xmTask.getName(),xmTask.getProjectId());
			}
		}
		// 不允许工作项向任务迁移
		for (String parentTaskid : parentTaskids) {
			XmTask xmTask=xmTaskMap.get(parentTaskid);
			if(!"1".equals(xmTask.getNtype())){
				return Result.error("plan-join-task-err-01","【%s】为任务，不允许下挂计划/子任务",xmTask.getName());
			}
		}

		// 进行权限判断 0.机构管理员不受权限约束， 1.项目经理、副经理有权限调整整个项目的任务，2. 组长有权限调整组内任务
 		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			 boolean isProjectAdm=this.groupService.checkUserIsProjectAdm(projectId,user.getUserid());
			 if(!isProjectAdm){
				 for (XmTask xmTask : xmTaskMap.values()) {
					 // 允许调整组内任务
					 Tips tips2=this.groupService.checkIsProjectAdmOrTeamHeadOrAss(user,xmTask.getCreateUserid(),projectId);
					 if("not-head".equals(tips2.getTipscode())){
						 return Result.error("not-user-head-01","您不是任务【%s】,负责人【%s】的组长/经理，无权限修改其归属",xmTask.getName(),xmTask.getCreateUsername());
					 }
					 Result.assertIsFalse(tips2);
					 // 不允许调整非组内任务
					 XmTask tardgetTask=xmTaskMap.get(newIdLinks.get(xmTask.getId()));
					 tips2=this.groupService.checkIsProjectAdmOrTeamHeadOrAss(user,tardgetTask.getCreateUserid(),projectId);
					 if("not-head".equals(tips2.getTipscode())){
						 return Result.error("not-user-head-02","您不是任务【%s】,负责人【%s】的组长/经理，无权限将【%s】挂接上去",tardgetTask.getName(),tardgetTask.getCreateUsername(),xmTask.getName());
					 }
					 Result.assertIsFalse(tips2);
				 }

			 }
		}


		/**
		 * 按上级进行分组
		 */
		Map<String,List<XmTask>> xmMaps=new HashMap<>();
		for (XmTask xmTask : childTasks) {
			String newPid=newIdLinks.get(xmTask.getId());
			XmTask parent=null;
			String pidPaths=null;
			if(ObjectTools.isEmpty(newPid)||"0".equals(newPid)){//向根迁移
				pidPaths="0,";
			}else{
				parent=xmTaskMap.get(newPid);
				pidPaths=parent.getPidPaths();
			}

			List<XmTask> tasks=xmMaps.get(pidPaths);
			if(tasks==null){
				tasks=new ArrayList<>();
				tasks.add(xmTask);
			}else{
				tasks.add(xmTask);
			}
			xmMaps.put(pidPaths,tasks);
		}

		// 分组后要进行降序排序，从底层往上更新
		List<String> pidPathsList = xmMaps.keySet().stream().sorted((x,y)->{
			return y.split(",").length-x.split(",").length;
		}).collect(Collectors.toList());
		for (String pidPaths : pidPathsList) {
			List<XmTask> tasks=xmMaps.get(pidPaths);
			//上级为顶级的情况处理
			if("0".equals(pidPaths)||"0,".equals(pidPaths)||ObjectTools.isEmpty(pidPaths)){
				this.xmTaskService.batchChangeParent(tasks,null);
				for (XmTask task : tasks) {
					this.xmRecordService.addXmTaskRecord(task.getProductId(),task.getProjectId(),task.getId(), task.getMenuId(),"任务-批量更新上级",String.format("批量更新上级:新值【%s】【%s】，旧值【%s】【%s】","0","顶级",task.getParentTaskid(),task.getParentTaskname()));
				}
			}else{
				String[] parentTaskIds=pidPaths.split(",");
				String parentTaskId=parentTaskIds[parentTaskIds.length-1];
				//必须重新查询,因为可能上一次循环已经更新了数据库中的pidPaths
				XmTask parentTask=this.xmTaskService.getById(parentTaskId);
				this.xmTaskService.batchChangeParent(tasks,parentTask);
				for (XmTask task : tasks) {
					this.xmRecordService.addXmTaskRecord(task.getProductId(),task.getProjectId(),task.getId(), task.getMenuId(),"任务-批量更新上级",String.format("批量更新上级:新值【%s】【%s】，旧值【%s】【%s】",parentTask.getId(),parentTask.getName(),task.getParentTaskid(),task.getParentTaskname()));
				}
			}

		}
		return Result.ok();
		
	}
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
	@AuditLog(firstMenu="办公平台",secondMenu="项目任务管理",func="processApprova",funcDesc="任务审核",operType=OperType.UPDATE)
	@RequestMapping(value="/processApprova",method=RequestMethod.POST)
	public Result processApprova( @RequestBody Map<String,Object> flowVars){
			this.xmTaskService.processApprova(flowVars);
			return Result.ok();
	}
}
