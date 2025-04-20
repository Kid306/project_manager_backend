package com.xm.core.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.DateUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.XmProduct;
import com.xm.core.entity.XmProductProjectLink;
import com.xm.core.entity.XmProject;
import com.xm.core.entity.XmTask;
import com.xm.core.mapper.XmProjectMapper;
import com.xm.core.service.cache.XmProjectCacheService;
import com.xm.core.vo.XmProjectCopyVo;
import com.xm.core.vo.XmProjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmProject 表 XM.xm_project 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmProjectService")
public class XmProjectService extends BaseService<XmProjectMapper,XmProject> {


	@Value("${mdp.platform-branch-id:platform-branch-001}")
	String platformBranchId="platform-branch-001";

	@Autowired
	XmProductProjectLinkService linkService;

    @Autowired
    XmTaskService xmTaskService;

    @Autowired
    XmQuestionService xmQuestionService;

    
    @Autowired
    XmRecordService xmRecordService;

    
    @Autowired
    XmProjectCacheService xmProjectCacheService;
	@Lazy
	@Autowired
	XmProductService xmProductService;
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
    
    public XmProject getProjectFromCache(String projectId) {
    	XmProject projectCahce=xmProjectCacheService.getProject(projectId);
    	if(projectCahce==null) {
    		projectCahce = this.selectOneObject(new XmProject(projectId));
    		xmProjectCacheService.putProject(projectId, projectCahce);
    		return projectCahce;
    	}
    	return projectCahce;
    }
    @Transactional
    public XmProject copyProject(User user, XmProjectCopyVo xmProject){
		XmProject xmProjectDb=this.getProjectFromCache(xmProject.getId());
		if(xmProjectDb==null){
			 return null;
		}

		if(!"1".equals(xmProjectDb.getIsTpl())){
			if(!user.getBranchId().equals(xmProjectDb.getBranchId())){
				throw new BizException("您无权复制其它组织的项目。");
			}
		}else{
			if(!user.getBranchId().equals(xmProjectDb.getBranchId())){
				if(!"9".equals(xmProjectDb.getShowOut())){
					throw new BizException("您无权复制其它组织的项目");
				}
			}
		}
		String isTpl=xmProject.getIsTpl();
		XmProjectVo xmProjectTo=new XmProjectVo();
		BeanUtils.copyProperties(xmProjectDb,xmProjectTo);
		xmProjectTo.setId(null);
		xmProjectTo.setCode(xmProject.getCode());
		xmProjectTo.setName(xmProject.getName());
		if(StringUtils.hasText(xmProject.getName()) && xmProject.getName().equals(xmProjectDb.getName())){
			xmProjectTo.setName(xmProject.getName()+"(复制)");
		}
		xmProjectTo.setIsTpl(isTpl);
		xmProjectTo.setStatus("0");
		xmProjectTo.setFromTplId(xmProjectDb.getId());
		xmProjectTo.setBizFlowState("0");
		xmProjectTo.setBizProcInstId(null);
		xmProjectTo.setAdmUserid(user.getUserid());
		xmProjectTo.setAdmUsername(user.getUsername());
		xmProjectTo.setAssUserid(user.getUserid());
		xmProjectTo.setAssUsername(user.getUsername());
		xmProjectTo.setPmUserid(user.getUserid());
		xmProjectTo.setPmUsername(user.getUsername());
		xmProjectTo.setTplType(xmProject.getTplType());
		xmProjectTo.setShowOut("1".equals(xmProject.getTplType())?"9":"6");
		this.saveProject(xmProjectTo);
		XmProduct xmProductTo=new XmProduct();
		BeanUtils.copyProperties(xmProjectTo,xmProductTo);
		xmProductTo.setId(null);
		xmProductTo.setCode(null);
		xmProductTo.setProductName(xmProjectTo.getName());
		if("1".equals(xmProject.getCopyTask())){
			XmTask taskQ=new XmTask();
			taskQ.setProjectId(xmProjectDb.getId());
			List<XmTask> xmTasks=this.xmTaskService.selectListByWhere(taskQ);
			Map<String,String> newTaskIdMap=new HashMap<>();
			if(xmTasks!=null && xmTasks.size()>0){
				for (XmTask node : xmTasks) {
					newTaskIdMap.put(node.getId(),this.xmTaskService.createKey("id"));
				}
				for (XmTask node : xmTasks) {
					String oldId=node.getId();
					String newId=newTaskIdMap.get(oldId);
					node.setProjectId(xmProjectTo.getId());
					node.setId(newId);
					if(ObjectTools.isEmpty(node.getParentTaskid())||"0".equals(node.getParentTaskid())){
						node.setParentTaskid("0");
					}else{
						node.setParentTaskid(newTaskIdMap.get(node.getParentTaskid()));
					}
					node.setCbranchId(user.getBranchId());
					node.setCdeptid(user.getDeptid());
					node.setCreateUsername(user.getUsername());
					node.setCreateUserid(user.getUserid());
					node.setCreateTime(new Date());
					node.setPreTaskid(newTaskIdMap.get(node.getPreTaskid()));
					node.setIsTpl(isTpl);
					if(!"1".equals(xmProject.getCopyProduct())){
						node.setMenuId(null);
						node.setMenuName(null);
						node.setProductId(null);
					}

					node.setExeUsernames(null);
					node.setExeUserids(null);
					node.setRate(0);
					node.setActEndTime(null);
					node.setActStartTime(null);
					node.setExecutorUserid(null);
					node.setExecutorUsername(null);
				}
				if("1".equals(xmProject.getCopyProduct())){
					xmProductTo=this.xmProductService.copyTo(user,xmTasks,xmProductTo);
				}

				this.xmTaskService.parentIdPathsCalcBeforeSave(xmTasks);
				this.xmTaskService.batchImportFromTemplate(xmTasks);
			}
		}
		if(xmProductTo!=null && ObjectTools.isNotEmpty(xmProductTo.getId())){
			//构建项目与产品的关联关系
			List<XmProductProjectLink> links=new ArrayList<>();
				XmProductProjectLink link=new XmProductProjectLink();
				link.setSeq(999);
				link.setProductId(xmProductTo.getId());
				link.setProjectId(xmProjectTo.getId());
				link.setLinkStatus("1");
				link.setCtime(new Date());
				link.setCusername(user.getUsername());
				link.setCuserid(user.getUserid());
				links.add(link);
			this.linkService.batchInsert(links);
		}
		return xmProjectTo;
	}
    
    public void clearProject(String projectId) {
    	xmProjectCacheService.clear(projectId);
    }
    

    
    public void updateProject(XmProject xmProject) {
        String projectId = xmProject.getId();
        
        XmProject oldValue = new XmProject();
        oldValue =this.selectOneObject(new XmProject(projectId)); 
          
        if(StringUtils.isEmpty(xmProject.getCode())){
            xmProject.setCode(this.createKey("code"));
        }
		xmProject.setLtime(new Date());
        this.updateByPk(xmProject);
        
        xmRecordService.addXmProjectRecord(xmProject.getId(),  "项目-修改项目基础信息", "更新项目"+xmProject.getName());
    }
    
    public void updateStatus(XmProject xmProject) {
    	XmProject xmProject2=new XmProject(xmProject.getId()); 
    	xmProject2.setStatus(xmProject.getStatus());
        this.updateSomeFieldByPk(xmProject2);
        xmRecordService.addXmProjectRecord(xmProject.getId(),  "项目-修改项目状态", "更新项目"+xmProject.getName()+"状态为"+xmProject2.getStatus() );   
         
    }

    @Transactional
    public XmProject saveProject(XmProjectVo xmProjectVo) {
        Tips tips = new Tips();
        User user = LoginUtils.getCurrentUserInfo();

		xmProjectVo.setBranchId(user.getBranchId());
        if(!StringUtils.isEmpty(xmProjectVo.getId()) && xmProjectVo.getId().equals(xmProjectVo.getCode())) {
        	  tips.setErrMsg("id不能事先预设");
              throw new BizException(tips);
        } 
        if(StringUtils.hasText(xmProjectVo.getCode())) {
        	XmProject xmProjectQuery = new  XmProject();
            xmProjectQuery.setCode(xmProjectVo.getCode());
			xmProjectQuery.setBranchId(user.getBranchId());
            if(this.countByWhere(xmProjectQuery)>0){
                tips.setErrMsg("项目代号重复，请修改代号再提交");
                throw new BizException(tips);
            }
            xmProjectVo.setId(this.createProjectId(xmProjectVo.getCode()));
        }else {
        	xmProjectVo.setCode(this.createProjectCode(user.getBranchId()));
        	xmProjectVo.setId(this.createProjectId(xmProjectVo.getCode()));
        }
        
        //获取当前登录用户信息
        xmProjectVo.setCreateUserid(user.getUserid());
        xmProjectVo.setCreateUsername(user.getUsername());
        xmProjectVo.setCreateTime(new Date());
        xmProjectVo.setStatus("0");
        if(!StringUtils.hasText(xmProjectVo.getIsTpl())){
        	xmProjectVo.setIsTpl("0");
		}
        xmProjectVo.setLocked("0");
        xmProjectVo.setDel("0");
        xmProjectVo.setLtime(new Date());
        XmProject projectDb=new XmProject();
        BeanUtils.copyProperties(xmProjectVo,projectDb);


        if(xmProjectVo.getLinks()!=null && xmProjectVo.getLinks().size()>0){
			for (XmProductProjectLink link : xmProjectVo.getLinks()) {
				link.setProjectId(xmProjectVo.getId());
				link.setCtime(new Date());
				link.setLinkStatus("1");
				link.setCuserid(user.getUserid());
				link.setCusername(user.getUsername());
			}
			this.linkService.batchInsert(xmProjectVo.getLinks());
		}
        this.insert(projectDb);
        xmRecordService.addXmProjectRecord(xmProjectVo.getId(),  "项目-新增项目", "新建项目"+xmProjectVo.getName());
        return xmProjectVo;
    }
    public String createProjectCode(String branchId){
    	XmProject projectQ=new XmProject();
    	projectQ.setBranchId(branchId);
    	long count=this.countByWhere(projectQ);
		String seq=(count%10000+1)+"";
		int preLength=4-seq.length();

		if(preLength>0){
			for (int i = 0; i < preLength; i++) {
				seq="0"+seq;
			}
		}
		String code=getSequenceService().getCommonNo("PJ{date:yyyy}-"+seq+"-{rands:2}");
		return code;

	}
	public String createProjectId(String code){
		String id=getSequenceService().getCommonNo(code+"-{rands:4}");
		XmProject xmProject=new XmProject(id);
		long idcount=this.countByWhere(xmProject);
		while (idcount>0){
			id=getSequenceService().getCommonNo(code+"-{rands:4}");
			xmProject=new XmProject(id);
			idcount=this.countByWhere(xmProject);
		}
		return id;

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
	public void processApprova(Map<String, Object> flowVars) { 
		String eventName=(String) flowVars.get("eventName"); 
		String agree=(String) flowVars.get("agree"); 
		String bizKey=(String) flowVars.get("bizKey");
		XmProject bizProject=new XmProject();
		if(!flowVars.containsKey("data")){
			throw new BizException("缺乏项目信息，请将项目的待审批信息放在flowVars.data中");
		}else{

			bizProject= BaseUtils.fromMap((Map)flowVars.get("data"), XmProject.class);
			if(!StringUtils.hasText(bizProject.getId())){
				throw new BizException("缺乏项目编号，请将项目编号放在flowVars.data.id中上传");
			}
			if(StringUtils.isEmpty(bizProject.getBranchId())) {
				throw new BizException("请上送机构编号flowVars.data.branchId");
			}
		}
		if("xm_project_baseinfo_change_approva".equals(bizKey) ) { //项目基本信息修改

		}else if("xm_project_start_approva".equals(bizKey) ) { //项目立项

		}else if("xm_project_delay_approva".equals(bizKey) ) { //项目逾期
			if(bizProject.getEndTime()==null) {
				throw new BizException("缺乏日期信息，请将变更的日期信息放在flowVars.data.endTime中");
			}
		}else if("xm_project_over_approva".equals(bizKey) ) { //结项

		}else if("xm_project_budget_change_approva".equals(bizKey) ) { //总预算调整
			if( bizProject.getPlanTotalCost() ==null ) {
				throw new BizException("缺乏预算信息，请将变更的预算信息放在flowVars.data中");
			}
		}else if("xm_project_restart_approva".equals(bizKey) ) { //总预算调整
			if(!flowVars.containsKey("data")) {
				throw new BizException("缺乏项目编号，请将项目编号放在flowVars.data.id中");
			}
		}else if("xm_project_pause_approva".equals(bizKey) ) { //总预算调整
			if(!flowVars.containsKey("data")) {
				throw new BizException("缺乏项目编号，请将项目编号放在flowVars.data.id中");
			}

		}else if("xm_project_comm_change_approva".equals(bizKey) ) { //通用变更流程
			if(!flowVars.containsKey("data")) {
				throw new BizException("缺乏项目编号，请将项目编号放在flowVars.data.id中");
			}

		}else{
			throw new BizException("不支持的业务,请上送业务编码【bizKey】参数");
		}


		if("TASK_COMPLETED".equals(eventName)) {
			if("1".equals(agree)) {
				this.updateFlowStateByProcInst("",bizProject, flowVars);
			}else {
				this.updateFlowStateByProcInst("",bizProject, flowVars);
			}
		}else {

			bizProject= BaseUtils.fromMap((Map)flowVars.get("data"), XmProject.class); 
			flowVars.put("projectId", bizProject.getId());
			if("PROCESS_STARTED".equals(eventName)) {   

				Map<String,Object> bizQuery=new HashMap<>(); 
				bizQuery.put("id", bizProject.getId());
				if(StringUtils.isEmpty(bizProject.getId())) {
					throw new BizException("请上送flowVars.data.id");
				}
				if(StringUtils.isEmpty(bizProject.getBranchId())) {
					throw new BizException("请上送flowVars.data.branchId");
				}
				XmProject xmProject=this.getById(bizProject.getId());
 				if(xmProject==null ) {
					throw new BizException("没有找到对应项目,项目为【"+bizProject.getId()+"】");
				}else {
					if("1".equals(xmProject.getBizFlowState())) {
						throw new BizException("该项目正在审批中，不能再发起审批");
					}
				}
				this.updateFlowStateByProcInst("1",bizProject, flowVars);
			}else if("PROCESS_COMPLETED".equals(eventName)) {
				if(!"0".equals(agree)) {
					
					if("xm_project_baseinfo_change_approva".equals(bizKey)) {//修改基本信息
						XmProject project=new XmProject();
						project.setId(bizProject.getId());
						//project.setStatus(bizProject.getStatus()); 
						project.setName(bizProject.getName()); 
						project.setPriority(bizProject.getPriority());
						project.setUrgent(bizProject.getUrgent());
						project.setBaseRemark(bizProject.getBaseRemark());
						project.setAssess(bizProject.getAssess());
						project.setName(bizProject.getName());
						project.setBudgetCtrl(bizProject.getBudgetCtrl());
						project.setDescription(bizProject.getDescription());
						project.setAssessRemarks(bizProject.getAssessRemarks());
						project.setBizProcInstId((String) flowVars.get("procInstId"));
						project.setBizFlowState("2");
						//project.setCode(project.getCode());
						this.updateSomeFieldByPk(project);
				        xmRecordService.addXmProjectRecord(bizProject.getId(),  "项目-基本信息", "修改基本信息" );  

					}else if("xm_project_start_approva".equals(bizKey)) {//立项 立项通过需要把预算数据同步到财务系统，把项目数据同步到财务系统
						XmProject project=new XmProject();
						project.setId(bizProject.getId());
						project.setStatus("3");
						project.setBizProcInstId((String) flowVars.get("procInstId"));
						project.setBizFlowState("2");
						this.updateSomeFieldByPk(project);
				        xmRecordService.addXmProjectRecord(bizProject.getId(),  "项目-立项", "项目立项通过审批" );  
				        this.createBaseline(bizProject.getId(),"项目立项通过审批");
				        
					}else if("xm_project_comm_change_approva".equals(bizKey)) {//通用变更流程
						 bizProject.setBizFlowState("2");
						this.updateSomeFieldByPk(bizProject);
						xmRecordService.addXmProjectRecord(bizProject.getId(),  "项目-变更", "通用变更流程" );
						this.createBaseline(bizProject.getId(),"项目变更通过审批");

					}else if("xm_project_delay_approva".equals(bizKey) ) { //项目逾期
						XmProject project=new XmProject(); 
						project.setId(bizProject.getId()); 
						project.setEndTime(bizProject.getEndTime());
						project.setBizProcInstId((String) flowVars.get("procInstId"));
						project.setBizFlowState("2");
						this.updateSomeFieldByPk(project);
						 xmRecordService.addXmProjectRecord(bizProject.getId(),  "项目-逾期", "项目逾期申请通过审批" );   
					}else if("xm_project_over_approva".equals(bizKey) ) { //结项
						XmProject project=new XmProject();
						project.setId(bizProject.getId());
						project.setBizProcInstId((String) flowVars.get("procInstId"));
						project.setBizFlowState("2");
						project.setStatus("6");
						this.updateSomeFieldByPk(project);
						this.createBaseline(bizProject.getId(),"项目结项申请通过审批");
						 xmRecordService.addXmProjectRecord(bizProject.getId(),  "项目-结项", "项目结项申请通过审批" );   
					}else if("xm_project_budget_change_approva".equals(bizKey) ) { //总预算调整，需要同步预算到财务系统
						

						this.editBudget(bizProject);
						xmRecordService.addXmProjectRecord(bizProject.getId(),  "项目-预算调整", "项目预算调整申请通过审批" );   
					}else if("xm_project_restart_approva".equals(bizKey) ) { //重新启动
						XmProject project=new XmProject();
						project.setId(bizProject.getId());
						project.setStatus("4");
						this.updateSomeFieldByPk(project);
						xmRecordService.addXmProjectRecord(bizProject.getId(),  "项目-重新启动", "项目重新启动申请通过审批" );   
					}else if("xm_project_pause_approva".equals(bizKey) ) { //暂停
						XmProject project=new XmProject();
						project.setId(bizProject.getId());
						project.setLocked("0");
						project.setBizProcInstId((String) flowVars.get("procInstId"));
						project.setBizFlowState("2");
						this.updateSomeFieldByPk(project);
						xmRecordService.addXmProjectRecord(bizProject.getId(),  "项目-暂停", "项目暂停申请通过审批" );   
					}
 				}else {
					this.updateFlowStateByProcInst("3",bizProject, flowVars);
				} 
			}else if("PROCESS_CANCELLED".equals(eventName)) { 
				this.updateFlowStateByProcInst("4",bizProject, flowVars);
			}
		} 
	}

	public void updateFlowStateByProcInst(String flowState,XmProject project,Map<String, Object> flowVars) {
		if(!StringUtils.hasText(flowState)){
			return;
		}
		XmProject projectUpdate=new XmProject();
		projectUpdate.setId(project.getId());
		projectUpdate.setBizFlowState(flowState);
		projectUpdate.setBizProcInstId((String) flowVars.get("procInstId"));
		this.updateSomeFieldByPk(projectUpdate);
	}

	Date getDateFromObject(Object date){
		if(date instanceof Date) {
			return (Date) date;
		}else if (date instanceof String) {
			return DateUtils.parse((String) date);
		}else if(date instanceof Long ){
			Date d=new Date();
			d.setTime((long) date);
			return d;
		}else {
			return null;
		}
	}
	public XmProject editBudget(XmProject xmProject) {

		//todo 同步预算数据到财务系统

		BigDecimal planTotalCost=xmProject.getPlanTotalCost();
		BigDecimal planIuserAt=xmProject.getPlanIuserAt();
		BigDecimal planOuserAt=xmProject.getPlanOuserAt();
		BigDecimal planNouserAt=xmProject.getPlanNouserAt();
		XmProject xmProject2=new XmProject();
		xmProject2.setPlanIuserAt(planIuserAt);
		xmProject2.setPlanOuserAt(planOuserAt);
		xmProject2.setPlanNouserAt(planNouserAt);
		xmProject2.setId(xmProject.getId());
		xmProject2.setPlanTotalCost(planIuserAt.add(planOuserAt).add(planNouserAt));
		xmProject.setPlanTotalCost(xmProject2.getPlanTotalCost());
		xmProject2.setBudgetMarginRate(xmProject.getBudgetMarginRate());
		xmProject2.setTotalReceivables(xmProject.getTotalReceivables());
		xmProject2.setTaxRate(xmProject.getTaxRate());
		xmProject2.setPlanIuserCnt(xmProject.getPlanIuserCnt());
		xmProject2.setPlanOuserCnt(xmProject.getPlanOuserCnt());
		xmProject2.setPlanIuserWorkload(xmProject.getPlanIuserWorkload());
		xmProject2.setPlanOuserWorkload(xmProject.getPlanOuserWorkload());
		xmProject2.setPlanIuserPrice(xmProject.getPlanIuserPrice());
		xmProject2.setPlanOuserPrice(xmProject.getPlanOuserPrice());
		xmProject2.setPlanWorkingHours(xmProject.getPlanWorkingHours());
		xmProject2.setPlanWorkload(xmProject.getPlanWorkload());
		xmProject2.setContractAmt(xmProject.getContractAmt());
		this.updateSomeFieldByPk(xmProject2);
		 xmRecordService.addXmProjectRecord(xmProject.getId(),  "项目-修改项目预算", "修改项目["+xmProject.getName()+"]的预算为["+xmProject2.getPlanTotalCost()+"]");
		 return xmProject;
	}
	public void createBaseline(String projectId,String remark) {
		XmProject p=new XmProject();
		p.setId(projectId);
		//XmProject p2=this.selectOneObject(p);
	}
}

