package com.xm.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.*;
import com.xm.core.mapper.XmRecordMapper;
import com.xm.core.vo.enums.ObjType;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmRecord 表 XM.xm_record 当前主键(包括多主键): id; 
 ***/
@Service("xm.core.xmRecordService")
public class XmRecordService extends BaseService<XmRecordMapper,XmRecord> implements XmRecordServiceApi{

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
	
	public XmRecord initXmRecord( ) {
		User user=LoginUtils.getCurrentUserInfo();
		XmRecord record=new XmRecord();
		record.setId(this.createKey("id"));
		record.setOperTime(new Date());
		record.setGloNo(MDC.get("gloNo"));
		record.setOperUserid(user.getUserid());
		record.setOperUsername(user.getUsername());
		record.setBranchId(user.getBranchId());
		record.setIp(RequestUtils.getIpAddr(RequestUtils.getRequest()));
		return record;
	}

	/**
	 * 针对产品的所有的操作日志用此方法
	 * @param productId 产品编号
	 * @param action 操作如 新增产品，修改产品，修改产品进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmProductRecord(String productId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProductId(productId);
		record.setAction(action);
		record.setBizId(productId);
		record.setRemarks(remarks);
		record.setObjType(ObjType.Product.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}
	
	/**
	 * 针对项目的所有的操作日志用此方法
	 * @param projectId 项目编号
	 * @param action 操作如 新增项目，修改项目，修改项目进度 等
	 * @param remarks 人性化语言描述 
	 */
	@Async
	public void addXmProjectRecord(String projectId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProjectId(projectId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setBizId(projectId);
		record.setObjType(ObjType.Project.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}

	/**
	 * 针对项目下的任务的所有操作用此方法
	 * @param tasks 任务列表
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmTaskRecord(List<XmTask> tasks,String action, String remarks) {
		User user=LoginUtils.getCurrentUserInfo();
		List<XmRecord> records=new ArrayList<>();
		String ip= RequestUtils.getIpAddr(RequestUtils.getRequest());
		for (XmTask task : tasks) {
			XmRecord record=new XmRecord();
			record.setId(this.createKey("id"));
			record.setOperTime(new Date());
			record.setGloNo(MDC.get("gloNo"));
			record.setOperUserid(user.getUserid());
			record.setOperUsername(user.getUsername());
			record.setBranchId(user.getBranchId());
			record.setProductId(task.getProductId());
			record.setBizId(task.getId());
			record.setProjectId(task.getProjectId());
			record.setPbizId(task.getMenuId());
			record.setAction(action);
			record.setRemarks(remarks+" 任务名称【"+task.getName()+"】");
			record.setObjType(ObjType.Task.getId());
			records.add(record);
			record.setIp(ip);
		}
		if(records.size()>0){
			super.batchInsert(records);
		}
	}
	/**
	 * 针对kpi的所有操作用此方法
	 * @param xmKpis kpi列表
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmKpiRecord(List<XmKpi> xmKpis, String action, String remarks) {
		User user=LoginUtils.getCurrentUserInfo();
		List<XmRecord> records=new ArrayList<>();
		String ip= RequestUtils.getIpAddr(RequestUtils.getRequest());
		for (XmKpi task : xmKpis) {
			XmRecord record=new XmRecord();
			record.setId(this.createKey("id"));
			record.setOperTime(new Date());
			record.setGloNo(MDC.get("gloNo"));
			record.setOperUserid(user.getUserid());
			record.setOperUsername(user.getUsername());
			record.setBranchId(user.getBranchId());
			record.setBizId(task.getId());
			record.setProjectId(task.getProjectId());
			record.setPbizId(task.getProjectId());
			record.setTopId(task.getDeptid());
			record.setAction(action);
			record.setRemarks(remarks+" 指标名称【"+task.getKpiName()+"】");
			record.setObjType(ObjType.Kpi.getId());
			records.add(record);
			record.setIp(ip);
		}
		if(records.size()>0){
			super.batchInsert(records);
		}
	}

	/**
	 * 针对项目下的缺陷的所有操作记录方法
	 * @param projectId 项目编号
	 * @param bugId 缺陷编号
	 * @param menuId 任务编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmBugRecord(String productId,String projectId,String bugId,String menuId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProductId(productId);
		record.setProjectId(projectId);
		record.setBizId(bugId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setPbizId(menuId);
		record.setObjType(ObjType.Bug.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}

	/**
	 * 针对产品下的用例的所有操作记录方法
	 * @param productId 产品编号
	 * @param caseId 用例编号
	 * @param menuId 模块编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmCaseRecord(String productId,String caseId,String menuId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProductId(productId);
		record.setBizId(caseId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setPbizId(menuId);
		record.setObjType(ObjType.Case.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}

	/**
	 * 针对产品下的用例执行的所有操作记录方法
	 * @param productId 产品编号
	 * @param projectId 项目编号
	 * @param caseId 用例编号
	 * @param planId 测试计划编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmCaseExeRecord(String productId,String projectId,String caseId,String planId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProjectId(projectId);
		record.setProductId(productId);
		record.setBizId(caseId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setPbizId(planId);
		record.setObjType(ObjType.CaseExe.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}
	/**
	 * 针对项目下的任务的所有操作用此方法
	 * @param projectId 项目编号
	 * @param budgetId 预算编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmBudgetRecord(String projectId,String budgetId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProjectId(projectId);
		record.setBizId(budgetId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setObjType(ObjType.Budget.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}

	/**
	 * 针对项目下的任务的所有操作用此方法
	 * @param projectId 项目编号
	 * @param taskId 任务编号
	 * @param menuId 故事编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmTaskRecord(String productId,String projectId,String taskId,String menuId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProductId(productId);
		record.setProjectId(projectId);
		record.setBizId(taskId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setPbizId(menuId);
		record.setObjType(ObjType.Task.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}

	/**
	 * 针对项目下的任务的所有操作用此方法
	 * @param projectId 项目编号
	 * @param taskId 任务编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmTaskRecord(String projectId,String taskId,String action,String remarks) {
		this.addXmTaskRecord(null,projectId,taskId,null,action,remarks);
	}
	/**
	 * 针对产品下的需求的所有操作用此方法
	 * @param menus 需求列表
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述 ,自动添加 需求名称【xxxxx】
	 */
	@Async
	public void addXmMenuRecord(List<XmMenu> menus, String action, String remarks) {
		User user=LoginUtils.getCurrentUserInfo();
		List<XmRecord> records=new ArrayList<>();

		String ip= RequestUtils.getIpAddr(RequestUtils.getRequest());
		for (XmMenu menu : menus) {
			XmRecord record=new XmRecord();
			record.setId(this.createKey("id"));
			record.setOperTime(new Date());
			record.setGloNo(MDC.get("gloNo"));
			record.setOperUserid(user.getUserid());
			record.setOperUsername(user.getUsername());
			record.setBranchId(user.getBranchId());
			record.setProductId(menu.getProductId());
			record.setBizId(menu.getMenuId());
			record.setTopId(menu.getIterationId());
			record.setPbizId(menu.getMenuId());
			record.setAction(action);
			record.setRemarks(remarks+" 需求名称【"+menu.getMenuName()+"】");
			record.setObjType(ObjType.Menu.getId());
			records.add(record);
			record.setPbizId(menu.getIterationId());
			record.setIp(ip);
		}
		if(records.size()>0){
			super.batchInsert(records);
		}

	}
	/**
	 * 针对产品下的需求的所有操作用此方法
	 * @param productId 产品编号
	 * @param menuId 需求编号
	 * @param iterationId 迭代编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmMenuRecord(String productId,String menuId,String iterationId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProductId(productId);
		record.setBizId(menuId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setPbizId(menuId);
		record.setTopId(iterationId);
		record.setObjType(ObjType.Menu.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}
	/**
	 * 针对迭代下的所有操作用此方法
	 * @param iterationId 迭代编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述 ,自动添加 需求名称【xxxxx】
	 */
	@Async
	public void addXmIterationRecord(String productId,String iterationId, String action, String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProductId(productId);
		 record.setBizId(iterationId);
		 //record.setPbizId(iterationId);
		 record.setTopId(iterationId);
		 record.setObjType(ObjType.Iteration.getId());
		 record.setAction(action);
		 record.setRemarks(remarks);
		record.setGloNo(MDC.get("gloNo"));
		 super.insert(record);

	}
	/**
	 * 测试库日志
	 * @param productId 产品编号
	 * @param casedbId 测试库编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmCasedbRecord(String productId,String casedbId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setBizId(casedbId);
		record.setProductId(productId);
		record.setTopId(casedbId);
		record.setObjType(ObjType.Casedb.getId());
		record.setAction(action);
		record.setRemarks(remarks);
		record.setGloNo(MDC.get("gloNo"));
		super.insert(record);
	}

	/**
	 * 测试库日志
	 * @param productId 产品编号
	 * @param projectId 项目编号
	 * @param planId 计划编号
	 * @param casedbId 测试库编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmTestPlanRecord(String productId,String projectId,String planId, String casedbId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setBizId(planId);
		record.setPbizId(planId);
		record.setProductId(productId);
		record.setProjectId(projectId);
		record.setObjType(ObjType.CasePlan.getId());
		record.setTopId(casedbId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setGloNo(MDC.get("gloNo"));
		super.insert(record);
	}
	/**
	 * 测试库日志
	 * @param productId 产品编号
	 * @param projectId 项目编号
	 * @param planId 计划编号
	 * @param caseId 用例编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmCasePlanRecord(String productId,String projectId,String planId,String caseId, String casedbId, String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setBizId(caseId);
		record.setPbizId(planId);
		record.setProductId(productId);
		record.setProjectId(projectId);
		record.setObjType(ObjType.CasePlan.getId());
		record.setAction(action);
		record.setRemarks(remarks);
		record.setGloNo(MDC.get("gloNo"));
		super.insert(record);
	}

	/**
	 * 针对项目下的任务的所有操作用此方法
	 * @param projectId 项目编号
	 * @param groupId 小组编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述 
	 */
	@Async
	public void addXmGroupRecord(String projectId,String groupId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProjectId(projectId);
		record.setProductId(projectId);
		record.setBizId(groupId);
		record.setAction(action);
		record.setRemarks(remarks); 
		record.setObjType(ObjType.Group.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}

	/**
	 * 针对项目下的任务的所有操作用此方法
	 * @param projectId 项目编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 * @param newValue 需要记录下来的新数据 可空
	 * @param oldValue 需要记录下来的旧数据 可空
	 */
	@Async
	public void addXmGroupRecord(String projectId,String groupId,String action,String remarks,String newValue,String oldValue) {
		XmRecord record=this.initXmRecord();
		record.setProjectId(projectId);
		record.setBizId(groupId);
		record.setProductId(projectId);
		record.setAction(action);
		record.setRemarks(remarks); 
		record.setObjType(ObjType.Group.getId());
		record.setNewValue(newValue);
		record.setOldValue(oldValue);
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}



	/**
	 * 针对项目下的任务的所有操作用此方法
	 * @param projectId 项目编号
	 * @param costId 成本编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmCostRecord(String projectId,String costId,String action,String remarks) {
		XmRecord record=this.initXmRecord();
		record.setProjectId(projectId);
		record.setBizId(costId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setObjType(ObjType.Cost.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}

	/**
	 * 针对项目下的任务的所有操作用此方法
	 * @param projectId 项目编号
	 * @param costId 成本编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 * @param newValue 需要记录下来的新数据 可空
	 * @param oldValue 需要记录下来的旧数据 可空
	 */
	@Async
	public void addXmCostRecord(String projectId,String costId,String action,String remarks,String newValue,String oldValue) {
		XmRecord record=this.initXmRecord();
		record.setProjectId(projectId);
		record.setBizId(costId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setObjType(ObjType.Cost.getId());
		record.setNewValue(newValue);
		record.setOldValue(oldValue);
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}
	@Override
	public String createKey(String keyName) {
		return "R"+getSequenceService().getCommonNo("{date62:yyyyMMddHHmmss}{rands:4}");
	}

    public List<String> selectChangeProjectIds() {
		return baseMapper.selectChangeProjectIds(map());
    }

	public List<String> selectChangeProductIds() {
		return baseMapper.selectChangeProductIds(map());
	}

	public List<String> selectChangeBranchIds() {
		return baseMapper.selectChangeBranchIds(map());
	}
	public List<String> selectChangeCollectIds() {
		return baseMapper.selectChangeCollectIds(map());
	}

	public List<String> selectChangeIterationIds() {
		return baseMapper.selectChangeIterationIds(map());
	}

	/**
	 * 针对kpi的所有操作用此方法
	 * @param deptid 归属部门
	 * @param projectId 项目编号
	 * @param kpiId 指标编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmKpiRecord(String deptid, String projectId,String kpiId, String action, String remarks) {
		XmRecord record=this.initXmRecord();
		record.setTopId(deptid);
		record.setPbizId(projectId);
		record.setProjectId(projectId);
		record.setBizId(kpiId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setObjType(ObjType.Kpi.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}
	/**
	 * 针对风险的所有操作用此方法
	 * @param deptid 归属部门
	 * @param projectId 项目编号
	 * @param riskId 风险编号
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmRiskRecord(String deptid, String projectId,String riskId, String action, String remarks) {
		XmRecord record=this.initXmRecord();
		record.setTopId(deptid);
		record.setPbizId(projectId);
		record.setProjectId(projectId);
		record.setBizId(riskId);
		record.setAction(action);
		record.setRemarks(remarks);
		record.setObjType(ObjType.Risk.getId());
		record.setGloNo(MDC.get("gloNo"));
		this.insert(record);
	}
	/**
	 * 针对风险的所有操作用此方法
	 * @param xmRisks 指标列表
	 * @param action 操作如 新增任务，修改任务信息，修改任务进度 等
	 * @param remarks 人性化语言描述
	 */
	@Async
	public void addXmRiskRecord(List<XmRisk> xmRisks, String action, String remarks) {
		User user=LoginUtils.getCurrentUserInfo();
		List<XmRecord> records=new ArrayList<>();
		String ip= RequestUtils.getIpAddr(RequestUtils.getRequest());
		for (XmRisk task : xmRisks) {
			XmRecord record=new XmRecord();
			record.setId(this.createKey("id"));
			record.setOperTime(new Date());
			record.setGloNo(MDC.get("gloNo"));
			record.setOperUserid(user.getUserid());
			record.setOperUsername(user.getUsername());
			record.setBranchId(user.getBranchId());
			record.setBizId(task.getId());
			record.setProjectId(task.getProjectId());
			record.setPbizId(task.getProjectId());
			record.setTopId(task.getDeptid());
			record.setAction(action);
			record.setRemarks(remarks+" 风险名称【"+task.getName()+"】");
			record.setObjType(ObjType.Risk.getId());
			records.add(record);
			record.setIp(ip);
		}
		if(records.size()>0){
			super.batchInsert(records);
		}
	}
}

