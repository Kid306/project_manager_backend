package com.xm.core.service;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.*;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.calc.service.XmCalcServiceApi;
import com.xm.core.entity.*;
import com.xm.core.mapper.XmTaskMapper;
import com.xm.core.queue.XmTaskSumParentsPushService;
import com.xm.core.service.client.SysClient;
import com.xm.core.vo.BatchRelTasksWithMenu;
import com.xm.core.vo.BatchRelTasksWithPhase;
import com.xm.core.vo.UserSvrVo;
import com.xm.core.vo.XmTaskVo;
import com.xm.rpt.XmRptQueryServiceApi;
import com.xm.tools.XmUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmTask 表 XM.xm_task 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmTaskService")
public class XmTaskService extends BaseService<XmTaskMapper,XmTask> {
	
	final String TYPE = "任务";

	@Lazy
	@Autowired
	XmTaskExecuserService xmTaskExecuserService;

	
	@Autowired
	XmRecordService xmRecordService;
	@Lazy
	@Autowired
    XmTaskSkillService xmTaskSkillService;

	@Autowired
	XmTaskSumParentsPushService pushService;


	@Autowired
	PushNotifyMsgService notifyMsgService;
	@Lazy
	@Autowired
	XmProjectService xmProjectService;

	@Autowired
	SysClient sysClient;

	@Lazy
	@Autowired
	XmGroupService groupService;

	@Autowired
	XmCalcServiceApi xmCalcServiceApi;

	@Autowired
	XmRptQueryServiceApi xmRptQueryServiceApi;

	@Autowired
	XmWorkloadService xmWorkloadService;
	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}
	@Transactional
	public   int[] doBatchDelete(List<XmTask> batchValues) {
		int[] i2= super.batchDelete(batchValues);
		String[] ignorIds= batchValues.stream().map(k->k.getId()).toArray(String[]::new);
		List<Set<String>> list= XmUtils.parsePidPaths(batchValues.stream().map(k->k.getPidPaths()).collect(Collectors.toSet()),1,ignorIds);
		for (Set<String> set : list) {
			xmCalcServiceApi.xmTaskBatchSumParents( set.stream().collect(Collectors.toList()));
		}
		return i2;
	}

	public Map<String,Object> selectTotalPhaseAndTaskBudgetCost(String phaseId, List<String> excludeTaskIds){
		Map<String,Object> p=new HashMap<>();
		p.put("phaseId", phaseId); 
		p.put("excludeTaskIds", excludeTaskIds);
		return this.baseMapper.selectTotalPhaseAndTaskBudgetCost( p);
	} 
	

	/**
	 * 判断新增预算是否超出项目总预算
	 * @param addTaskBudgetCost
	 * @return
	 */
	public Tips judgetPhaseBudget(String phaseId, BigDecimal addTaskBudgetCost, BigDecimal addTaskBudgetIuserAt, BigDecimal addTaskBudgetOuserAt, BigDecimal addTaskBudgetNouserAt, List<String> excludeTaskIds){
		Tips tips=new Tips("成功");
		if(!StringUtils.hasText(phaseId)){
			tips.setErrMsg("phaseId参数不能为空");
			return tips;
		}
		Map<String,Object> g=this.selectTotalPhaseAndTaskBudgetCost(phaseId,excludeTaskIds);
		if(g==null || g.isEmpty()){
			return tips;
		}
		BigDecimal phaseBudgetCost=BigDecimal.ZERO;
		BigDecimal zero=BigDecimal.ZERO;
		
		if(addTaskBudgetCost==null) {
			addTaskBudgetCost=BigDecimal.ZERO;
		}
		if(addTaskBudgetIuserAt==null) {
			addTaskBudgetIuserAt=BigDecimal.ZERO;
		}
		if(addTaskBudgetOuserAt==null) {
			addTaskBudgetOuserAt=BigDecimal.ZERO;
		}
		if(addTaskBudgetNouserAt==null) {
			addTaskBudgetNouserAt=BigDecimal.ZERO;
		}
		BigDecimal phaseBudgetAt=NumberUtil.getBigDecimal(g.get("phaseBudgetAt"),zero);
		BigDecimal phaseBudgetIuserAt=NumberUtil.getBigDecimal(g.get("phaseBudgetIuserAt"),zero);
		BigDecimal phaseBudgetOuserAt=NumberUtil.getBigDecimal(g.get("phaseBudgetOuserAt"),zero); 
		BigDecimal phaseBudgetNouserAt=NumberUtil.getBigDecimal(g.get("phaseBudgetNouserAt"),zero); 
		
		BigDecimal taskBudgetIuserAt=NumberUtil.getBigDecimal(g.get("taskBudgetIuserAt"),zero);
		BigDecimal taskBudgetOuserAt=NumberUtil.getBigDecimal(g.get("taskBudgetOuserAt"),zero); 
		BigDecimal taskBudgetNouserAt=NumberUtil.getBigDecimal(g.get("taskBudgetNouserAt"),zero); 
		BigDecimal taskBudgetTotalCost=NumberUtil.getBigDecimal(g.get("budgetCost"),zero);  
		
		/**
		if(addTaskBudgetIuserAt.add(taskBudgetIuserAt).compareTo(phaseBudgetIuserAt)>0) {
			tips.setErrMsg("内部人力预算超出项目内部人力预算");
			return tips;
		}
		if(addTaskBudgetOuserAt.add(taskBudgetOuserAt).compareTo(phaseBudgetOuserAt)>0) {
			tips.setErrMsg("外部人力预算超出项目外部人力预算");
			return tips;
		}		
		if(addTaskBudgetNouserAt.add(taskBudgetNouserAt).compareTo(phaseBudgetNouserAt)>0) {
			tips.setErrMsg("非人力预算超出项目非人力预算");
			return tips;
		}
		**/
		BigDecimal totalTaskBudgetAt=taskBudgetTotalCost.add(addTaskBudgetCost);
		if(phaseBudgetAt.compareTo(totalTaskBudgetAt)<0) {
			tips.setErrMsg("任务合计总预算超出计划总预算"+totalTaskBudgetAt.subtract(phaseBudgetAt)+"元");
			return tips;
		}else {
			return tips;
		} 
		
	}
	/**
	 *
	 *
	 ifnull(p.budget_cost,0) as budget_cost,
	 ifnull(p.budget_workload,0) as budget_workload,
	 sum( ifnull(res.budget_cost,0) ) AS child_budget_cost,
	 sum( ifnull(res.budget_workload,0) ) AS child_budget_workload
	 * 判断新增预算是否超出项目总预算
	 * @param addTaskBudgetCost
	 * @return
	 */
	public Tips judgetTaskBudget(String parentTaskid, BigDecimal addTaskBudgetCost, BigDecimal addTaskBudgetIuserAt, BigDecimal addTaskBudgetOuserAt, BigDecimal addTaskBudgetNouserAt, List<String> excludeTaskIds){
		Tips tips=new Tips("成功");
		if(!StringUtils.hasText(parentTaskid)){
			tips.setErrMsg("parentTaskid参数不能为空");
			return tips;
		}
		Map<String,Object> g=this.selectTotalTaskBudgetCost(parentTaskid,excludeTaskIds);
		if(g==null || g.isEmpty()){
			return tips;
		}

		BigDecimal budgetAt=NumberUtil.getBigDecimal(g.get("budgetAt"),BigDecimal.ZERO);
		BigDecimal childBudgetAt=NumberUtil.getBigDecimal(g.get("childBudgetAt"),BigDecimal.ZERO);
		childBudgetAt=childBudgetAt.add(addTaskBudgetCost);
		if(budgetAt.compareTo(childBudgetAt)<0) {
			tips.setErrMsg("任务合计总预算超出上级总预算"+childBudgetAt.subtract(budgetAt)+"元");
			return tips;
		}else {
			return tips;
		}

	}

	private Map<String, Object> selectTotalTaskBudgetCost(String parentTaskid, List<String> excludeTaskIds) {
		return baseMapper.selectTotalTaskBudgetCost(map("parentTaskid",parentTaskid,"excludeTaskIds",excludeTaskIds));
	}

	public void updateTaskChildrenCntByTaskId(String taskId){
		baseMapper.updateTaskChildrenCntByTaskId(taskId);
	}

	public List<Map<String,Object>> getTask(IPage page,QueryWrapper<XmTask> qw,Map<String,Object> xmTask){
		List<Map<String,Object>> mapList = this.selectListMapByWhere(page,qw,xmTask);//所有数据
		return mapList;
	}
	@Transactional
	public XmTaskVo addTask(XmTaskVo xmTaskVo){
		Tips tips = new Tips();

		User user = LoginUtils.getCurrentUserInfo();
		if(!StringUtils.hasText(xmTaskVo.getCreateUserid())){
			xmTaskVo.setCreateUserid(user.getUserid());
			xmTaskVo.setCreateUsername(user.getUsername());
		}
		xmTaskVo.setBizFlowState("0");
		xmTaskVo.setCreateTime(new Date());
		xmTaskVo.setRate(0);
		xmTaskVo.setSortLevel(xmTaskVo.getSortLevel());
		if(StringUtils.isEmpty(xmTaskVo.getMilestone())){
			xmTaskVo.setMilestone("0");
		}
		//新增任务技能
		if(xmTaskVo.getSkills()!=null && xmTaskVo.getSkills().size()>0){
			xmTaskVo.setTaskSkillNames(xmTaskVo.getSkills().stream().map(k->k.getSkillName()).collect(Collectors.joining(",")));
			xmTaskVo.setTaskSkillIds(xmTaskVo.getSkills().stream().map(k->k.getSkillId()).collect(Collectors.joining(",")));
		}
		XmProject projectDb=xmProjectService.getProjectFromCache(xmTaskVo.getProjectId());
		//如果是由服务商提供服务构建的任务
		if(StringUtils.hasText(xmTaskVo.getServiceId())){
			Map<String,Object> userServiceData=sysClient.getUserSvrByServiceId(xmTaskVo.getServiceId());
			if(userServiceData!=null && !userServiceData.isEmpty()){
				User bidUser=BaseUtils.fromMap(userServiceData,User.class);
				UserSvrVo svrVo=BaseUtils.fromMap(userServiceData,UserSvrVo.class);
				XmTaskExecuser xmTaskExecuser=new XmTaskExecuser();
				xmTaskExecuser.setTaskId(xmTaskVo.getId());
				xmTaskExecuser.setTaskName(xmTaskVo.getName());
				xmTaskExecuser.setProjectId(xmTaskVo.getProjectId());
				xmTaskExecuser.setBidUserid(bidUser.getUserid());
				xmTaskExecuser.setBidUsername(bidUser.getUsername());
				xmTaskExecuser.setBidBranchId(bidUser.getBranchId());
				xmTaskExecuser.setBranchId(xmTaskVo.getCbranchId());
				xmTaskExecuser.setStatus("1");
				xmTaskExecuser.setQuoteAmount(svrVo.getPrice());
				xmTaskExecuser.setQuoteWorkload(xmTaskVo.getBudgetWorkload());
				xmTaskExecuser.setSkillRemark((String)userServiceData.get("skills"));
				User exeUserParams=new User();
				exeUserParams.setCpaOrg(bidUser.getBranchId());
				exeUserParams.setCpaUserid(bidUser.getUserid());
				exeUserParams.setUsername(bidUser.getUsername());
				exeUserParams.setDeptid(projectDb.getDeptid());
				exeUserParams.setBranchId(projectDb.getBranchId());
				User exeUserDb=sysClient.createUserIfNotExists(exeUserParams,projectDb.getDeptid(),projectDb.getBranchId());
				xmTaskExecuser.setPrjUserid(exeUserDb.getUserid());
				xmTaskExecuser.setPrjUsername(exeUserDb.getUsername());
				xmTaskExecuserService.addExecuser(xmTaskExecuser,!"0".equals(xmTaskVo.getStatus()));
				xmTaskVo.setExeUserids(exeUserDb.getUserid());
				xmTaskVo.setExeUsernames(exeUserDb.getUsername());
				xmTaskVo.setExecutorUserid(exeUserDb.getUserid());
				xmTaskVo.setExecutorUsername(exeUserDb.getUsername());
				xmTaskVo.setExecUsers(1);
				xmTaskVo.setStatus("1");
				xmTaskVo.setBidStep("4");
				xmTaskVo.setTaskState("1");
				xmTaskVo.setTaskClass("1");
				xmTaskVo.setEstate("1");
				xmTaskVo.setQuoteFinalAt(svrVo.getPrice());
				xmTaskVo.setBidEtime(new Date());
				this.setNull(xmTaskVo);
			}
		}
		if(ObjectTools.isEmpty(xmTaskVo.getParentTaskid())){
			xmTaskVo.setParentTaskid("0");
		}
		XmTask xmTask = new XmTask();
		BeanUtils.copyProperties(xmTaskVo,xmTask);

		this.setNull(xmTask);
		this.insert(xmTask);
 		this.sumParents(xmTask,xmTask.getId());
		//新增任务技能
		if(xmTaskVo.getSkills()!=null && xmTaskVo.getSkills().size()>0){
			for (XmTaskSkill skill : xmTaskVo.getSkills()) {
				skill.setTaskId(xmTaskVo.getId());
			}
			xmTaskSkillService.addSkill(xmTaskVo.getSkills());
		}



		//xmTaskExecuserService.updateXmTaskExeUseridsAndUsernamesByTaskId(xmTaskVo.getId());
		
		//xmTaskSkillService.updateXmTaskSkillIdsAndNamesByTaskId(xmTaskVo.getId());
		
		//新增日志
		xmRecordService.addXmTaskRecord(xmTask.getProductId(),xmTask.getProjectId(), xmTask.getId(),xmTask.getMenuId(), "任务-新增", "新增任务【"+xmTask.getName()+"】");
		//草稿不提醒
		if(!"0".equals(xmTaskVo.getStatus())){
			notifyMsgService.pushMsg(user, xmTask.getCreateUserid(), xmTask.getCreateUsername(), "您成为任务【" + xmTask.getName() + "】的负责人，请注意跟进。",null);
		}

		return xmTaskVo;
	}

	public void setNull(XmTask xmTask){
		if(ObjectTools.isEmpty(xmTask.getParentTaskid())){
			xmTask.setParentTaskid("0");
		}
		if(ObjectTools.isEmpty(xmTask.getPreTaskid())){
			xmTask.setPreTaskid(null);
		}
		if(ObjectTools.isEmpty(xmTask.getProductId())){
			xmTask.setProductId(null);
		}
		if(ObjectTools.isEmpty(xmTask.getMenuId())){
			xmTask.setMenuId(null);
		}
	}
	public XmTaskVo createByService(XmTaskVo xmTaskVo){

		return xmTaskVo;
	}

	/**
	 * 有执行人，有子任务都不允许删除
	 * @param xmTask
	 * @return
	 */
	@Override
	public boolean removeById(XmTask xmTask) {
		super.removeById(xmTask);
		this.sumParents(xmTask,xmTask.getId());
		return true;
	}

	@Transactional
	public void updateTask(XmTaskVo xmTaskVo,XmTask xmTaskDb) {
		XmTask xmTask = new XmTask();
		BeanUtils.copyProperties(xmTaskVo,xmTask);
		xmTask.setSortLevel(xmTaskVo.getSortLevel());
		if(StringUtils.isEmpty(xmTask.getMilestone())){
			xmTask.setMilestone("0");
		}
		xmTask.setLtime(new Date());

		this.setNull(xmTask);
		this.updateSomeFieldByPk(xmTask);
		if(xmTaskVo.getSkills()!=null && xmTaskVo.getSkills().size()>0){
			for (XmTaskSkill skill : xmTaskVo.getSkills()) {
				skill.setTaskId(xmTaskVo.getId());
			}
			this.xmTaskSkillService.insertOrDelete(xmTaskVo.getSkills());
		}
		if(StringUtils.hasText(xmTaskDb.getParentTaskid()) && !"0".equals(xmTaskDb.getParentTaskid())){
			pushService.pushXmTask(xmTaskDb);
		}
		xmRecordService.addXmTaskRecord(xmTaskDb.getProductId(),xmTaskDb.getProjectId(), xmTaskDb.getId(),xmTaskDb.getMenuId(), "任务-修改", "更新任务"+xmTask.getName());
	}

	@Transactional
	public void updateTime(XmTask xmTask,XmTask xmTaskDb) {
		//XmTask oldValue = this.selectOneObject(new XmTask(xmTask.getId()));
		XmTask xmTask2=new XmTask();
		xmTask2.setId(xmTask.getId());
		xmTask2.setStartTime(xmTask.getStartTime());
		xmTask2.setEndTime(xmTask.getEndTime());
		xmTask2.setActStartTime(xmTask.getActStartTime());
		xmTask2.setActEndTime(xmTask.getActEndTime());
		this.updateSomeFieldByPk(xmTask);
		if(StringUtils.hasText(xmTaskDb.getParentTaskid())&& !"0".equals(xmTaskDb.getParentTaskid())){
			pushService.pushXmTask(xmTaskDb);
		}
		//更新父任务的进度
		//updateParentProgress(xmTask.getParentTaskid());
		xmRecordService.addXmTaskRecord(xmTaskDb.getProductId(),xmTaskDb.getProjectId(), xmTaskDb.getId(), xmTaskDb.getMenuId(),"任务-计划", "更新任务计划开始时间为"+
				DateUtils.format(xmTask.getStartTime(),"yyyy-MM-dd")+",计划结束时间为"+DateUtils.format(xmTask.getEndTime(),"yyyy-MM-dd")+
				"实际开始时间:"+DateUtils.format(xmTask.getActStartTime(),"yyyy-MM-dd")+",实际结束时间为"+DateUtils.format(xmTask.getActEndTime(),"yyyy-MM-dd"));
	}
	@Transactional
	public void updateProgress(XmTask xmTask,XmTask xmTaskDb) {
 		XmTask xmTask2=new XmTask();
		xmTask2.setId(xmTask.getId());
		xmTask2.setRate(xmTask.getRate());
		if(xmTaskDb.getBudgetWorkload()==null){
			xmTaskDb.setBudgetWorkload(BigDecimal.ZERO);
		}
		xmTask2.setActWorkload(xmTaskDb.getBudgetWorkload().multiply(BigDecimal.valueOf(xmTask.getRate())).divide(BigDecimal.valueOf(100)));
		this.updateSomeFieldByPk(xmTask);
		this.sumParents(xmTaskDb,xmTaskDb.getId());
		xmRecordService.addXmTaskRecord(xmTaskDb.getProductId(),xmTaskDb.getProjectId(), xmTaskDb.getId(),xmTaskDb.getMenuId(), "任务-进度", "更新任务进度为"+xmTask2.getRate());
	}
	public Map<String,XmTask> selectTasksMapByTasks(List<XmTask> xmTasks){
		List<String> ids=new ArrayList<>();
		for (XmTask xmTask : xmTasks) {
			ids.add(xmTask.getId());
		}
		List<XmTask> tasks= this.selectTaskListByIds(ids);
		Map<String,XmTask> map=new HashMap<>();
		for (XmTask task : tasks) {
			map.put(task.getId(),task);
		}
		return map;
	}
	public List<XmTask> selectTaskListByTasks(List<XmTask> xmTasks){
		List<String> ids=new ArrayList<>();
		for (XmTask xmTask : xmTasks) {
			ids.add(xmTask.getId());
		}
		return this.selectTaskListByIds(ids);
	}
	public List<XmTask> selectTaskListByIds(List<String> ids){
		return baseMapper.selectTaskListByIds(map("ids",ids));
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
	 *            TASK_COMPLETED_FORM_DATA_UPDATE 人工任务提交完成后，智能表单数据更新
	 *
	 * 其中 create/complete/assignment/delete事件是需要在模型中人工节点上配置了委托代理表达式 ${taskBizCallListener}才会推送过来。
	 * 在人工任务节点上配置 任务监听器  建议事件为 complete,其它assignment/create/complete/delete也可以，一般建议在complete,委托代理表达式 ${taskBizCallListener}
	 *
	 * @param flowVars {flowBranchId,agree,procInstId,startUserid,assignee,actId,taskName,mainTitle,branchId,bizKey,commentMsg,eventName,modelKey} 等
	 * @return 如果tips.isOk==false，将影响流程提交
	 **/
	@Transactional
	public void processApprova(Map<String, Object> flowVars) {
		String eventName=(String) flowVars.get("eventName");
		String agree=(String) flowVars.get("agree");
		String bizKey=(String) flowVars.get("bizKey");
		XmTask bizXmTask=null;
		if("xm_task_approva".equals(bizKey) ) {
			if(!flowVars.containsKey("data")) {
				throw new BizException("缺乏任务信息，请将任务信息放在flowVars.data中");
			}
			bizXmTask= BaseUtils.fromMap((Map)flowVars.get("data"), XmTask.class);
			flowVars.put("xmTaskId", bizXmTask.getId());
		}else {
			throw new BizException("不支持的业务,请上送业务编码【bizKey】参数");
		}
		if("complete".equals(eventName)) {
			if("1".equals(agree)) {
				this.updateFlowStateByProcInst("", flowVars);
			}else {
				this.updateFlowStateByProcInst("", flowVars);
			}
		}else {
			if("PROCESS_STARTED".equals(eventName)) {
				Map<String,Object> bizQuery=new HashMap<>();
				bizQuery.put("id", bizXmTask.getId());
				if(StringUtils.isEmpty(bizXmTask.getId())) {
					throw new BizException("请上送任务编号flowVars.data.id");
				}
				QueryWrapper<XmTask> qw=new QueryWrapper();
				qw.eq("id",bizXmTask.getId());
				List<Map<String,Object>> bizList=this.listMaps(qw);
				if(bizList==null || bizList.size()==0) {
					throw new BizException("没有找到对应项目任务单,项目任务单为【"+bizXmTask.getId()+"】");
				}else {
					Map<String,Object> bizObject=bizList.get(0);
					if("1".equals(bizObject.get("bizFlowState"))) {
						throw new BizException("该项目任务单正在审批中，不能再发起审批");
					}
				}
				flowVars.put("id", this.createKey("id"));
				this.updateFlowStateByProcInst("1", flowVars);
			}else if("PROCESS_COMPLETED".equals(eventName)) {
				if("1".equals(agree)) {
					this.updateFlowStateByProcInst("2", flowVars);

				}else {
					this.updateFlowStateByProcInst("3", flowVars);
				}
			}else if("PROCESS_CANCELLED".equals(eventName)) {
				this.updateFlowStateByProcInst("4", flowVars);
			}
		}
	}
	@Transactional
	public void updateFlowStateByProcInst(String flowState,Map<String, Object> flowVars) {
		if(StringUtils.hasText((String) flowVars.get("xmTaskId"))){
			XmTask xmTask=new XmTask();
			xmTask.setId((String) flowVars.get("taskId"));
			xmTask.setBizFlowState(flowState);
			xmTask.setBizProcInstId((String) flowVars.get("procInstId"));
			this.updateSomeFieldByPk(xmTask);
		}
	}
	@Transactional
	public void batchImportFromTemplate(List<XmTask> xmTasks) {
		this.batchInsert(xmTasks);
		this.pushService.pushXmTasks(xmTasks);
		
	}
	
	/**
	 * 批量更新任务的需求为新的需求或者更新为空
	 * @param tasksWithMenu
	 */
	@Transactional
	public void batchRelTasksWithMenu(BatchRelTasksWithMenu tasksWithMenu,XmMenu xmMenuDb) {
		Map<String,Object> map=map("menuId",tasksWithMenu.getMenuId(),"menuName",xmMenuDb.getMenuName(),"productId",xmMenuDb.getProductId(),"taskIds",tasksWithMenu.getTaskIds());
		baseMapper.batchRelTasksWithMenu(map);
		//需要将历史报工指向新的故事
		xmWorkloadService.lambdaUpdate().set(XmWorkload::getMenuId,xmMenuDb.getMenuId()).in(XmWorkload::getTaskId,tasksWithMenu.getTaskIds().toArray()).update();
	}

	@Transactional
	public void batchInsertOrUpdate(List<XmTask> insertXmTasks,List<XmTask> editXmTasks) {
		List<XmTask> all=new ArrayList<>();
		if(insertXmTasks!=null && insertXmTasks.size()>0) {
			this.batchInsert(insertXmTasks);
			all.addAll(insertXmTasks);
		}
		if(editXmTasks!=null && editXmTasks.size()>0) {
			this.batchUpdate(editXmTasks);
			all.addAll(editXmTasks);
		}
		if(all.size()>0){
			this.pushService.pushXmTasks(all);
		}
	}

	public Map<String,Object> shareTaskDetail(Map<String, Object> xmTask) {
		return this.baseMapper.shareTaskDetail(xmTask);
	}

	public void updateChildrenCntByIds(List<String> ids) {
		baseMapper.updateChildrenCntByIds(ids);
	}


	public List<XmTask> parentIdPathsCalcBeforeSave(List<XmTask> nodes) {
		List<XmTask> noExistsList=nodes.stream().filter(i->!nodes.stream().filter(k->k.getId().equals(i.getParentTaskid())).findAny().isPresent()).collect(Collectors.toList());
		noExistsList=noExistsList.stream().filter(i->StringUtils.hasText(i.getParentTaskid()) && !"0".equals(i.getParentTaskid())).collect(Collectors.toList());
		Map<String,String> hadCalcMap=new HashMap<>();
		for (XmTask node : noExistsList) {
			if(hadCalcMap.containsKey(node.getParentTaskid())){
				String idPaths=hadCalcMap.get(node.getParentTaskid());
				node.setPidPaths(idPaths+node.getId()+",");
			}else{
				this.parentIdPathsCalcBeforeSave(node);
				String idPaths=node.getPidPaths();
				idPaths=idPaths.substring(0,idPaths.length()-node.getId().length()-1);
				hadCalcMap.put(node.getParentTaskid(),idPaths);
			}
		}
		for (XmTask node : nodes) {
			if(!StringUtils.hasText(node.getParentTaskid())||"0".equals(node.getParentTaskid())){
				node.setPidPaths("0,"+node.getId()+",");
				node.setParentTaskid("0");
				continue;
			}
			if(hadCalcMap.containsKey(node.getParentTaskid())){
				String idPaths=hadCalcMap.get(node.getParentTaskid());
				node.setPidPaths(idPaths+node.getId()+",");
			}
		}

		for (XmTask node : nodes) {
			if(!StringUtils.hasText(node.getParentTaskid())||"0".equals(node.getParentTaskid())){
				continue;
			}
			if(!hadCalcMap.containsKey(node.getParentTaskid())){
				List<XmTask> pnodeList=this.getParentList(node,nodes);
				if(pnodeList==null ||pnodeList.size()==0){//理论上不应该存在这种情况
					node.setPidPaths("0,"+node.getId()+",");
					node.setParentTaskid("0");
					continue;
				}
				XmTask topParent=pnodeList.get(pnodeList.size()-1);
				String idPath="0,";
				if(hadCalcMap.containsKey(topParent.getParentTaskid())){
					idPath=hadCalcMap.get(topParent.getParentTaskid());
				}
				for (int i = pnodeList.size() - 1; i >= 0; i--) {
					idPath=idPath+pnodeList.get(i).getId()+",";
				}
				node.setPidPaths(idPath+node.getId()+",");
			}
		}
		for (XmTask node : nodes) {
			String idPaths=node.getPidPaths();
			String[] idpss=idPaths.split(",");
			node.setLvl(idpss.length-1);
		}
		return nodes;
	}

	public static void main(String[] args) {
		String idpaths="0,1,2,3,";
		String[] idpss=idpaths.split(",");
		int lvl=idpss.length;

	}

	public Tips parentIdPathsCalcBeforeSave(XmTask currNode) {
		Tips tips = new Tips("成功");
		if (!StringUtils.hasText(currNode.getParentTaskid()) || "0".equals(currNode.getParentTaskid())) {
			currNode.setPidPaths("0," + currNode.getId() + ",");
			currNode.setLvl(1);
			currNode.setParentTaskid("0");
			return tips;
		} else {
			List<XmTask> parentList=this.getParentList(currNode);
			if(parentList==null ||parentList.size()==0){
				currNode.setPidPaths("0,"+currNode.getId()+",");
				currNode.setLvl(1);
				currNode.setParentTaskid("0");
				return tips;
			}
			String idPath="0,";
			for (int i = parentList.size() - 1; i >= 0; i--) {
				idPath=idPath+parentList.get(i).getId()+",";
			}
			currNode.setPidPaths(idPath+currNode.getId()+",");

			String idPaths=currNode.getPidPaths();
			String[] idpss=idPaths.split(",");
			currNode.setLvl(idpss.length-1);
		}
		return tips;
	}

	private List<XmTask> getParentList(XmTask currNode){
		List<XmTask> parentList=new ArrayList<>();
		XmTask current=currNode;
		while (true){
			if(!StringUtils.hasText(current.getParentTaskid()) || "0".equals(current.getParentTaskid())){
				return parentList;
			}
			current=this.getById(current.getParentTaskid());
			if(current==null){
				return parentList;
			}
			parentList.add(current);
		}
	}

	private List<XmTask> getParentList(XmTask currNode,List<XmTask> nodes){
		List<XmTask> parentList=new ArrayList<>();
		XmTask current=currNode;
		while (true){
			if(!StringUtils.hasText(current.getParentTaskid()) || "0".equals(current.getParentTaskid())){
				return parentList;
			}
			XmTask query=new XmTask();
			query.setId(current.getParentTaskid());
			Optional<XmTask> optional=nodes.stream().filter(i->i.getId().equals(query.getId())).findFirst();
			if(optional.isPresent()){
				current=optional.get();
				parentList.add(current);
			}else{
				return parentList;
			}

		}
	}


	@Transactional
	public void sumParents(XmTask node,String ignorId){

		xmCalcServiceApi.xmTaskSumParents(XmUtils.parsePidPaths(node.getPidPaths(),1,ignorId));

	}
	@Transactional
	public void batchSumParents(List<XmTask> xmTasks) {
		List<String> ids=xmTasks.stream().filter(k->"0".equals(k.getNtype())).map(k->k.getId()).collect(Collectors.toList());
		 List<Set<String>> pidPaths=XmUtils.parsePidPaths(xmTasks.stream().map(k->k.getPidPaths()).collect(Collectors.toList()), 1, ids.toArray(new String[ids.size()]));
		for (Set<String> pidPath : pidPaths) {
			this.xmCalcServiceApi.xmTaskBatchSumParents(pidPath.stream().collect(Collectors.toList()));
		}
	}


	public boolean checkExistsExecuser(XmTask node) {
		if("1".equals(node.getNtype())){
			return false;
		}
		String exec=node.getExeUserids();
		if(!StringUtils.hasText(exec)){
			return false;
		}
		if(exec.indexOf("(1)")>0 || exec.indexOf("(2)")>0|| exec.indexOf("(3)")>0|| exec.indexOf("(4)")>0|| exec.indexOf("(5)")>0){
			return true;
		}
		return false;
	}

	/**
	 * 检查是否能删除干净所有儿子孙子节点。
	 * @param delNode 当前删除节点
	 * @param delNodes 本批量需要删除的全部节点
	 * @return
	 */
	public boolean checkCanDelAllChild(XmTask delNode, List<XmTask> delNodes) {
		if(delNode==null){
			return true;
		}
		if(delNode.getChildrenCnt()==null||delNode.getChildrenCnt()<=0){
			return true;
		}
		List<XmTask> childList=delNodes.stream().filter(i-> !delNode.getId().equals(delNode.getParentTaskid()) && delNode.getId().equals(i.getParentTaskid())).collect(Collectors.toList());

		if(childList==null||childList.size()<delNode.getChildrenCnt()){
			return false;
		}
		for (XmTask n : childList) {
			if (!this.checkCanDelAllChild(n, delNodes)) {
				return false;
			}
		}
		return true;

	}

	public void batchRelTasksWithPhase(BatchRelTasksWithPhase tasksPhase) {
		baseMapper.batchRelTasksWithPhase(tasksPhase);
	}

	public Map<String,Object> calcProjectAndTaskBudget(String projectId,List<String> excludeTaskIds){
		Map<String,Object> map=new HashMap<>();
		if(excludeTaskIds!=null){
			map.put("excludeTaskIds",excludeTaskIds);
		}
		map.put("projectId",projectId);
		return super.baseMapper.calcProjectAndTaskBudget(map);
	}
	public Tips judgetProjectBudget(String projectId, BigDecimal addBudgetCost, List<String> excludeTaskIds) {
		Tips tips=new Tips("成功");
		Map<String,Object> data=this.calcProjectAndTaskBudget(projectId,excludeTaskIds);
		if(data==null || data.isEmpty()){
			tips.setErrMsg("项目不存在");
			return tips;
		}
		BigDecimal planTotalCost=NumberUtil.getBigDecimal(data.get("planTotalCost"),BigDecimal.ZERO);
		BigDecimal taskBudgetCost=NumberUtil.getBigDecimal(data.get("budgetAt"),BigDecimal.ZERO);
		BigDecimal chaochu=taskBudgetCost.add(addBudgetCost).subtract(planTotalCost);
		if(chaochu.compareTo(BigDecimal.ZERO)>0){
			tips.setErrMsg("超出项目总预算"+chaochu+"元");
			return tips;
		}
		return tips;
	}

	public List<XmTask> listTenTaskByProjectIdAndProductId(String projectId,String productId) {

		return baseMapper.listTenTaskByProjectIdAndProductId(map("projectId", projectId,  "productId",productId));
	}


	public List<XmTask> listTenTaskByProjectIdAndIterationId(String projectId, String iterationId) {

		return baseMapper.listTenTaskByProjectIdAndIterationId(map("projectId", projectId, "iterationId",iterationId));
	}

	@Transactional
	public void batchChangeParent(List<XmTask> xmTasks,XmTask parentTask) {
		if(parentTask!=null){
			baseMapper.batchChangeParent(map("taskIds",xmTasks.stream().map(i->i.getId()).collect(Collectors.toList()),"parentTaskid",parentTask.getId(),"parentTaskname",parentTask.getName(),"parentPidPaths",parentTask.getPidPaths()));
			xmCalcServiceApi.xmTaskSumParents(Arrays.stream(parentTask.getPidPaths().split(",")).collect(Collectors.toList()));
			//pushService.pushXmTask(parentTask);

		}else {
			baseMapper.batchChangeParent(map("taskIds",xmTasks.stream().map(i->i.getId()).collect(Collectors.toList()),"parentTaskid","0","parentTaskname","根","parentPidPaths","0,"));

		}

		// 同一个事物内，需要把原来的上级的子孙数目更新一下
		Set<String> oldPids=new HashSet<>();
		for (XmTask xmTask : xmTasks) {
			oldPids.add(xmTask.getParentTaskid());

		}
		if(parentTask!=null){
			oldPids.add(parentTask.getId());
		}
		oldPids.remove("0");
		if(oldPids.size()>0){

			this.updateChildrenCntByIds(oldPids.stream().collect(Collectors.toList()));
		}
	}

	/**
	 * 结算审批通过后，更新任务表数据
	 * @param taskId
	 */
	public void updateActCostAndActWorkloadAfterSettle(String taskId,String toTaskState) {
		baseMapper.updateActCostAndActWorkloadAfterSettle(map("id",taskId,"taskState",toTaskState));
	}

	public void calcWorkloadByRecord(String id) {
		if(!StringUtils.hasText(id)){
			return;
		}
		List<String> ids=new ArrayList<>();
		ids.add(id);
		calcWorkloadByRecord(ids);
	}
	public void calcWorkloadByRecord(List<String> ids) {
		if(ids==null || ids.size()<=0){
			return;
		}
		ids=ids.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
		baseMapper.calcWorkloadByRecord(ids);
	}



	public void batchUpdateMactWorkloadAndRate(List<String> ids,BigDecimal mactWorkload) {
		baseMapper.batchUpdateMactWorkloadAndRate(map("ids",ids,"mactWorkload",mactWorkload));
	}
	public List<Map<String, Object>> getXmTaskAttDist(IPage page, QueryWrapper ew, Map<String,Object> ext) {
		return xmRptQueryServiceApi.getXmTaskAttDist(page,ew,ext);
	}

	public List<Map<String, Object>> getXmTaskAgeDist(QueryWrapper ew, Map<String,Object> ext) {
		return xmRptQueryServiceApi.getXmTaskAgeDist(ew,ext);
	}

	public List<Map<String, Object>> getXmTaskSort(IPage page, QueryWrapper ew, Map<String,Object> ext) {
		return xmRptQueryServiceApi.getXmTaskSort(page,ew,ext);
	}

	@Override
	public String createKey(String keyName) {
		return "T"+getSequenceService().getCommonNo("{date62}{rands:2}");
	}

    public void upBrowseTimes(String id,Integer nums) {
		baseMapper.upBrowseTimes(map("id",id,"nums",nums));
    }

	public void updateSomeFieldByPkAfterPaySuccess(XmTask xmTaskUpdate) {
		baseMapper.updateSomeFieldByPkAfterPaySuccess(xmTaskUpdate);
	}

	public XmTask getOneWithChildrenCnt(String id) {
		List<String> ids=new ArrayList<>();
		ids.add(id);
		List<XmTask> xmTasks=this.selectTaskListByIds(ids);
		if(xmTasks==null || xmTasks.size()==0){
			return null;
		}else{
			return xmTasks.get(0);
		}
	}

	@Transactional
	public int editSomeFields(Map<String, Object> parameter,List<XmTask> tasks) {
		int i=0;
		if(parameter.containsKey("mactWorkload")){
			this.batchUpdateMactWorkloadAndRate((List<String>) parameter.get("$pks"),NumberUtil.getBigDecimal(parameter.get("mactWorkload"),BigDecimal.ZERO));
		}else if(parameter.containsKey("initWorkload")){
			this.batchUpdateInitWorkloadAndRate((List<String>) parameter.get("$pks"),NumberUtil.getBigDecimal(parameter.get("initWorkload"),BigDecimal.ZERO));
		}else if(parameter.containsKey("calcType")){
			this.batchUpdateInitWorkloadAndRateByCalcType(parameter);
		}else if(parameter.containsKey("rateRule")){
				String rateRule= (String) parameter.get("rateRule");
				Integer rate=NumberUtil.getInteger(parameter.get("initRate"),0);
				if("initWorkload".equals(rateRule)){


				this.batchUpdateInitWorkloadAndRateByActWorkload((List<String>) parameter.get("$pks"),rate);
				i= 1;
			} else if("actWorkload".equals(rateRule)){
					throw new BizException("暂不支持");
					//this.batchUpdateActWorkloadAndRateByBudgetWorkload((List<String>) parameter.get("$pks"),rate);
			} else if("none".equals(rateRule)){
					i= super.editSomeFields(parameter);
				}
		}else if(CollectionUtils.containsAny(parameter.get("taskState"),"2","3","4","9")){
				baseMapper.batchUpdateFinishState(parameter);
		}else if(parameter.containsKey("menuId")){
			this.editSomeFields(parameter);
			//需要将历史报工指向新的故事
			xmWorkloadService.lambdaUpdate().set(XmWorkload::getMenuId,parameter.get("menuId")).in(XmWorkload::getTaskId,((List) parameter.get("$pks")).toArray()).update();
		}else{
			i=super.editSomeFields(parameter);
		}

		if(parameter.containsKey("calcType") ||parameter.containsKey("rateRule") ||CollectionUtils.containsAny(parameter.get("taskState"),"2","3","4","9")||parameter.containsKey("initWorkload")||parameter.containsKey("mactWorkload")){
			//this.batchSumParents(tasks);
			this.pushService.pushXmTasks(tasks);
		}
		return i;
	}

	private void batchUpdateInitWorkloadAndRateByCalcType(Map<String, Object> parameter) {
		baseMapper.batchUpdateInitWorkloadAndRateByCalcType(parameter);
	}


	private void batchUpdateInitWorkloadAndRate(List<String> ids, BigDecimal initWorkload) {
		baseMapper.batchUpdateInitWorkloadAndRate(map("ids",ids,"initWorkload",initWorkload));
	}

	public void batchUpdateInitWorkloadAndRateByActWorkload(List<String> ids,Integer rate) {
		baseMapper.batchUpdateInitWorkloadAndRateByActWorkload(map("ids",ids,"initRate",rate));
	}

	/**
	 * 向上查找某个任务的所有依赖
	 * @param xmTask
	 * @param m
	 */
	public void deriveUpList(XmTask xmTask,Map<String,XmTask> m) {
		if(xmTask==null){
			return;
		}
		if(ObjectTools.isNotEmpty(xmTask.getPreTaskid()) && !xmTask.getPreTaskid().equals("0")){
			List<String> ids=Arrays.asList(xmTask.getPreTaskid().split(","));
			ids=ids.stream().filter(k->!m.containsKey(k)&&!"0".equals(k)).collect(Collectors.toList());
			if(ids.size()==0){
				return;
			}
			List<XmTask> ds=this.listByIds(ids);
			if(ds!=null && ds.size()>0){
				for (XmTask d : ds) {
					if(m.containsKey(d.getId())){
						continue;
					}
					m.put(d.getId(),d);
					this.deriveUpList(d,m);
				}
			}
		}
	}

	/**
	 * 查询某个任务的所有的上依赖及下依赖
	 * @param id
	 * @return
	 */
	public List<XmTask> deriveList(String id){
		XmTask xmTask=this.getById(id);
		Map<String,XmTask> m=new HashMap<>();
		m.put(xmTask.getId(),xmTask);
		this.deriveUpList(xmTask,m);
		this.deriveDownList(xmTask,m);
		return m.values().stream().collect(Collectors.toList());
	}

	/**
	 * 向下查找某个任务的所有依赖
	 * @param xmTask
	 * @param m
	 */
	public void deriveDownList(XmTask xmTask,Map<String,XmTask> m) {
		if(xmTask==null){
			return;
		}
		List<XmTask> ds=list(Wrappers.lambdaQuery(XmTask.class).apply(true,"INSTR(pre_taskid, {0})>0",xmTask.getId()));
			if(ds!=null && ds.size()>0){
				for (XmTask d : ds) {
					if(m.containsKey(d.getId())){
						continue;
					}
					m.put(d.getId(),d);
					this.deriveDownList(d,m);
				}
			}
		}


}

