package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.meta.client.service.ItemService;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmProject;
import com.xm.core.entity.XmTask;
import com.xm.core.entity.XmTaskExecuser;
import com.xm.core.service.*;
import com.xm.core.service.client.AcClient;
import com.xm.core.service.client.MkClient;
import com.xm.core.service.client.SysClient;
import com.xm.core.vo.XmTaskAcceptanceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.map;

/**
 * url编制采用rest风格,如对XM.xm_task_execuser xm_task_execuser的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmTaskExecuser/add <br>
 *  查询: xm/xmTaskExecuser/list<br>
 *  模糊查询: xm/xmTaskExecuser/listKey<br>
 *  修改: xm/xmTaskExecuser/edit <br>
 *  删除: xm/xmTaskExecuser/del<br>
 *  批量删除: xm/xmTaskExecuser/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmTaskExecuser 表 XM.xm_task_execuser 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmTaskExecuserController")
@RequestMapping(value="/xm/core/xmTaskExecuser")
@Api(tags={"xm_task_execuser操作接口"})
public class XmTaskExecuserController {
	
	static Log logger=LogFactory.getLog(XmTaskExecuserController.class);
	
	@Autowired
	private XmTaskExecuserService xmTaskExecuserService;

	@Autowired
	private XmTaskService xmTaskService;

	@Autowired
	private XmProjectService xmProjectService;


	@Autowired
	private XmProjectQxService projectQxService;


	@Autowired
	PushNotifyMsgService notifyMsgService;


	@Autowired
	XmRecordService xmRecordService;

	@Autowired
	ItemService itemService;

	@Autowired
	MkClient mkClient;

	@Autowired
	AcClient acClient;

	@Autowired
	SysClient sysClient;

	@Value(value = "${mdp.platform-branch-id:platform-branch-001}")
	String platformBranchId;
	

	@Autowired
    XmGroupService groupService;
	
	@ApiOperation( value = "查询xm_task_execuser信息列表",notes="listXmTaskExecuser,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(value = XmTaskExecuser.class)
	@ApiResponses({
		@ApiResponse(code = 200,response= XmTaskExecuser.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTaskExecuser(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		String projectId= (String) params.get("projectId");
		if(StringUtils.hasText(projectId)){
			if(!groupService.checkUserExistsProjectGroup(projectId,user.getUserid())){
				params.put("linkBranchId",user.getBranchId());
			}
		}else{
			params.put("linkBranchId",user.getBranchId());
		}


		QueryWrapper<XmTaskExecuser> qw = QueryTools.initQueryWrapper(XmTaskExecuser.class , params);
		List<Map<String,Object>> datas = xmTaskExecuserService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTaskExecuser列表
		
	}


	@ApiOperation( value = "查询xm_task_execuser信息列表",notes="listXmTaskExecuser,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(value = XmTaskExecuser.class)
	@ApiResponses({
			@ApiResponse(code = 200,response= XmTaskExecuser.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listWithTask",method=RequestMethod.GET)
	public Result listXmTaskExecuserWithTask(@ApiIgnore @RequestParam Map<String,Object> params){
		
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		String projectId= (String) params.get("projectId");
		if(StringUtils.hasText(projectId)){
			if(!groupService.checkUserExistsProjectGroup(projectId,user.getUserid())){
				params.put("linkBranchId",user.getBranchId());
			}
		}else{
			params.put("linkBranchId",user.getBranchId());
		}
		QueryTools.alias(params,"projectId res.projectId");
		QueryWrapper<XmTaskExecuser> qw = QueryTools.initQueryWrapper(XmTaskExecuser.class , params);

		List<Map<String,Object>>	datas = xmTaskExecuserService.selectListMapByWhereWithTask(page,qw,params);	//列出XmTaskExecuser列表
		
		return Result.ok().setData(datas).setTotal(page.getTotal());
		
		
	}
	
	@ApiOperation( value = "新增一条xm_task_execuser信息",notes="addXmTaskExecuser,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskExecuser.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	////@HasQx(value = "xm_core_xmTaskExecuser_add",name = "新增任务执行者",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmTaskExecuser(@RequestBody XmTaskExecuser xmTaskExecuser) {


			 User user=LoginUtils.getCurrentUserInfo();
			 if(ObjectTools.isEmpty(xmTaskExecuser.getTaskId())){
				 return Result.error("taskId-required","任务编号不能为空");
			 }
			XmTask xmTaskDb=xmTaskService.getById(xmTaskExecuser.getTaskId());
			if(xmTaskDb==null){
				return Result.error("任务已不存在");
				
			}
			String projectId=xmTaskDb.getProjectId();
			XmProject xmProjectDb=this.xmProjectService.getProjectFromCache(projectId);
			xmTaskExecuser.setProjectId(projectId);
			xmTaskExecuser.setBranchId(xmProjectDb.getBranchId());
			if(!"0".equals(xmTaskDb.getTaskState()) && !"1".equals(xmTaskDb.getTaskState()) ){
				return Result.error("该任务已经处于完工、结算状态，不允许再修改");
				
			}
			if(ObjectTools.isNotEmpty(xmTaskExecuser.getBidUserid()) && !user.getUserid().equals(xmTaskExecuser.getBidUserid())){
				User userDb=sysClient.getUserByUserid(xmTaskExecuser.getBidUserid());
				if(userDb==null){
					return Result.error("userid-0","候选人不存在");
				}
 				xmTaskExecuser.setBidBranchId(userDb.getUserid());
				xmTaskExecuser.setBidUsername(userDb.getUsername());
			}else{
				xmTaskExecuser.setPrjUserid(user.getUserid());
				xmTaskExecuser.setPrjUsername(user.getUsername());
				xmTaskExecuser.setBidUserid(user.getUserid());
				xmTaskExecuser.setBidUsername(user.getUsername());
				xmTaskExecuser.setBidBranchId(user.getBranchId());
			}

			if("1".equals(xmTaskDb.getCrowd())){
				Map<String,Object>  result=sysClient.checkUserInterests(xmTaskExecuser.getBidUserid(),xmTaskDb.getBudgetAt(),xmTaskDb.getBudgetWorkload(),1);
 				Tips tips2= (Tips) result.get("tips");
 				Result.assertIsFalse(tips2);
				Map<String,Object> data= (Map<String, Object>) result.get("data");
				if(data!=null && data.containsKey("sfeeRate")){
					xmTaskExecuser.setSfeeRate(NumberUtil.getInteger(data.get("sfeeRate"),0));
					if(xmTaskExecuser.getQuoteAmount()!=null){
						xmTaskExecuser.setSfee(xmTaskExecuser.getQuoteAmount().multiply(BigDecimal.valueOf(xmTaskExecuser.getSfeeRate()/100)));
					}
				}
				xmTaskExecuser.setStatus("0");   //如果是众包，智能添加为候选人
			}else {
				//如果不是众包，需要判断是否已加入项目组组织架构中，如未加入，需要提示其先加入
 				Tips tips =projectQxService.checkProjectQx(xmProjectDb,0,user,xmTaskExecuser.getBidUserid(),xmTaskExecuser.getBidUsername(),xmTaskExecuser.getBidBranchId());
				Result.assertIsFalse(tips);
				//检查是否已经存在执行人
				XmTaskExecuser query=new XmTaskExecuser();
				query.setTaskId(xmTaskDb.getId());
				List<XmTaskExecuser> xmTaskExecusersDb=this.xmTaskExecuserService.selectListByWhere(query);
				if(xmTaskExecusersDb !=null && xmTaskExecusersDb.size()>0) {
					// 把现有执行人变更为候选状态
					List<XmTaskExecuser> dbs=xmTaskExecusersDb.stream().filter(k->!"0".equals(k.getStatus()) && !"7".equals(k.getStatus()) && !xmTaskExecuser.getBidUserid().equals(k.getBidUserid())).collect(Collectors.toList());
					if(dbs.size()>0){
						this.xmTaskExecuserService.lambdaUpdate().set(XmTaskExecuser::getStatus,"0").in(XmTaskExecuser::getBidUserid,dbs.stream().map(k->k.getBidUserid()).toArray()).eq(XmTaskExecuser::getTaskId,xmTaskDb.getId()).update();
					}
					// 如果已存在，则变更其为执行人
					Optional<XmTaskExecuser> op=xmTaskExecusersDb.stream().filter(k->k.getBidUserid().equals(xmTaskExecuser.getBidUserid())).findAny();
					if(op.isPresent()){
						XmTaskExecuser exe=op.get();
						if("0".equals(exe.getStatus()) || "7".equals(exe.getStatus()) ||"8".equals(exe.getStatus())){
							this.xmTaskExecuserService.lambdaUpdate().set(XmTaskExecuser::getStatus,"1").eq(XmTaskExecuser::getTaskId,exe.getTaskId()).eq(XmTaskExecuser::getBidUserid,exe.getBidUserid()).update();
						}
						return Result.ok();
					}

				}
				xmTaskExecuser.setStatus("1");//如果不是众包，则添加为执行人

			}
			boolean sendMsg=!"0".equals(xmTaskDb.getStatus());
			xmTaskExecuserService.addExecuser(xmTaskExecuser,sendMsg);
			if(sendMsg){
				notifyMsgService.pushMsg(user, xmTaskDb.getCreateUserid(), xmTaskDb.getCreateUsername(), "用户【"+xmTaskExecuser.getBidUsername()+"】投标任务【"+xmTaskDb.getName()+"】，请及时跟进！",null);
			}
			sysClient.pushBidsAfterBidSuccess(xmTaskExecuser.getBidUserid(),xmTaskDb.getId(),xmTaskDb.getBudgetAt(),xmTaskDb.getBudgetWorkload(),1);
			return Result.ok();
	}
	
	@ApiOperation( value = "执行人离开任务",notes="editXmTaskExecuser")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskExecuser.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	////@HasQx(value = "xm_core_xmTaskExecuser_leave",name = "执行人离开任务",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/leave",method=RequestMethod.POST)
	public Result leave(@RequestBody List<XmTaskExecuser> xmTaskExecusers) {

			if(xmTaskExecusers==null || xmTaskExecusers.size()==0){
				return Result.error("执行人列表不能为空");
				
			}
			User user=LoginUtils.getCurrentUserInfo();
			List<XmTaskExecuser> xmTaskExecuserListDb=this.xmTaskExecuserService.listByIds(xmTaskExecusers.stream().map(i->map("taskId",i.getTaskId(),"bidUserid",i.getBidUserid())).collect(Collectors.toList()));
			if(xmTaskExecuserListDb==null || xmTaskExecuserListDb.size()==0){
				return Result.error("data-0","执行人/候选人都已不存在");
			}
			String taskId=xmTaskExecuserListDb.get(0).getTaskId();
			XmTask xmTask= xmTaskService.getById(xmTaskExecuserListDb.get(0).getTaskId());
			if(xmTask==null ){
				return Result.error("任务已不存在");
				
			}
			List<String> noAllowUsers=new ArrayList<>();
			List<XmTaskExecuser> allowUsers=new ArrayList<>();
			List<String> allowUserNames=new ArrayList<>();

			for (XmTaskExecuser xmTaskExecuser : xmTaskExecuserListDb) {
				if(!taskId.equals(xmTaskExecuser.getTaskId())){
					return Result.error("批量操作只允许在同一个任务进行");
				}
 				if(!user.getUserid().equals(xmTaskExecuser.getBidUserid())) {//只有组长、任务责任人可以请别人请离开任务
	 				Tips tips =projectQxService.checkProjectQx(xmProjectService.getProjectFromCache(xmTask.getProjectId()),2, user,xmTaskExecuser.getBidUserid(),xmTaskExecuser.getBidUsername(),xmTaskExecuser.getBidBranchId());
					Result.assertIsFalse(tips);
					allowUsers.add(xmTaskExecuser);
					allowUserNames.add(xmTaskExecuser.getBidUsername());

				}else {//自己离开任务，可以的
					allowUsers.add(xmTaskExecuser);
					allowUserNames.add(xmTaskExecuser.getBidUsername());
				}
			} 
			if(allowUsers.size()>0) {
				xmTaskExecuserService.batchLeave(allowUsers);
			}
			List<String> msgs=new ArrayList<>();
			if(allowUsers.size()>0){
				String allowUserNamesStr=StringUtils.arrayToDelimitedString(allowUserNames.toArray(), "、");
				msgs.add("成功将【"+allowUserNamesStr+"】请离任务;");
			}
			if(noAllowUsers.size()>0){
				String allowUserNamesStr=StringUtils.arrayToDelimitedString(noAllowUsers.toArray(), "、");
				msgs.add("以下人员您无权操作，【"+allowUserNamesStr+"】;");
			}
			if(allowUserNames.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining(" ")));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining(" ")));
			}
		
	}


	@ApiOperation( value = "候选人变更为执行人",notes="editXmTaskExecuser")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskExecuser.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	////@HasQx(value = "xm_core_xmTaskExecuser_execute",name = "修改任务执行人基础信息",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/execute",method=RequestMethod.POST)
	public Result execute(@RequestBody  XmTaskExecuser xmTaskExecuser) {

		if(ObjectTools.isEmpty(xmTaskExecuser.getBidUserid())){
			return Result.error("bidUserid-required","投标人编号不能为空");
		}
		if(ObjectTools.isEmpty(xmTaskExecuser.getTaskId())){
			return Result.error("taskId-required","任务编号不能为空");
		}
		/**
		 * 如果是候选人变更为执行人，需要检查该候选人是否已加入项目中的某个组
		 */
		String taskId=xmTaskExecuser.getTaskId();
		XmTask xmTask= xmTaskService.getById(taskId);
		if(xmTask==null ){
			return Result.error("任务已不存在"); 
		} 
		if(!"0".equals(xmTask.getTaskState()) && !"1".equals(xmTask.getTaskState()) ){
			return Result.error("该任务已经处于完工、结算状态，不允许再修改"); 
		}
		User user=LoginUtils.getCurrentUserInfo();

		String projectId=xmTask.getProjectId();
		XmProject xmProject=xmProjectService.getProjectFromCache(projectId);
		if(xmProject==null ){
			return Result.error("project-0","项目已不存在");
		}
		
		 Tips tips=projectQxService.checkProjectQx(xmProject,2,user);
		Result.assertIsFalse(tips);  
		//一个任务只能一个执行人
		xmTaskExecuserService.becomeExecute(xmTask,xmTaskExecuser);
		return Result.ok("变更成功"); 
		
	}

	@ApiOperation( value = "验收付款",notes="acceptance")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskExecuser.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	////@HasQx(value = "xm_core_xmTaskExecuser_execute",name = "修改任务执行人基础信息",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/acceptance",method=RequestMethod.POST)
	public Result acceptance(@RequestBody XmTaskAcceptanceVo xmTaskAcceptanceVo) {

			String taskId=xmTaskAcceptanceVo.getTaskId();
			if(!StringUtils.hasText(taskId)){
				return Result.error("taskId-0","任务编号不能为空");
			}
			XmTask xmTaskDb= xmTaskService.getById(taskId);
			if(xmTaskDb==null ){
				return Result.error("任务已不存在");
			}
			if("3".equals(xmTaskDb.getTaskState()) ||"4".equals(xmTaskDb.getTaskState()) || "9".equals(xmTaskDb.getTaskState())){
				return Result.error("该任务已验收，不能重复验收");
			}


			User user=LoginUtils.getCurrentUserInfo();

			String projectId=xmTaskDb.getProjectId();
			Tips tips=projectQxService.checkProjectQx(xmProjectService.getProjectFromCache(projectId),2,user );
			Result.assertIsFalse(tips);
			boolean needPay=false;
			if("1".equals(xmTaskDb.getCrowd())){
				boolean isTaskCreater=user.getUserid().equals(xmTaskDb.getCreateUserid());
 				Tips tips1=groupService.checkIsProjectAdmOrTeamHeadOrAss(user,xmTaskDb.getExecutorUserid(),projectId);
				if(  !isTaskCreater && !tips1.isOk() ) {
					return Result.error("您无权验收该任务！");
				}
				if("2".equals(xmTaskDb.getEstate()) && xmTaskDb.getEfunds()!=null && xmTaskDb.getEfunds().compareTo(BigDecimal.ZERO)>0){
					needPay=true;
				}
			}
			XmTask xmTaskUpdate=new XmTask();
			xmTaskUpdate.setId(xmTaskDb.getId());
			xmTaskUpdate.setTaskState("4");
			if(needPay){
				//XmTaskExecuser xmTaskExecuserDb=this.xmTaskExecuserService.selectOneById(map("taskId",xmTaskDb.getId(),"userid",xmTaskDb.getExecutorUserid()));
				//调用ac系统付款给服务商
 				Tips payTips=acClient.platformBalancePayToClient(xmTaskDb.getExecutorUserid(),"3","1",xmTaskDb.getId(),xmTaskDb.getQuoteFinalAt(),"任务【"+xmTaskDb.getName()+"】验收完毕，发放佣金.");
				Result.assertIsFalse(payTips);
				xmTaskUpdate.setEtoDevTime(new Date());
				xmTaskUpdate.setBidStep("7");
				xmTaskUpdate.setEstate("3");
			}
			String taskState= xmTaskUpdate.getTaskState();
			if("3".equals(taskState)||"4".equals(taskState)||"9".equals(taskState)){
				xmTaskUpdate.setEndTime(new Date());
				xmTaskUpdate.setActEndTime(new Date());
			}
			xmTaskService.updateById(xmTaskUpdate,true);
			if("2".equals(xmTaskDb.getOshare()) && xmTaskDb.getShareFee()!=null && xmTaskDb.getShareFee().compareTo(BigDecimal.ZERO)>0){
				 mkClient.pushAfterTaskAcceptanceSuccess(xmTaskDb.getExecutorUserid(),xmTaskDb.getExecutorUsername(),xmTaskDb.getProjectId(),xmTaskDb.getId(),xmTaskDb.getShareFee());
			}
			if(needPay){
				sysClient.pushPayAtAfterTaskAcceptanceSuccess(xmTaskDb.getExecutorUserid(),xmTaskDb.getId(),xmTaskDb.getEfunds(),xmTaskDb.getBudgetWorkload());

				notifyMsgService.pushMsg(user, xmTaskDb.getExecutorUserid(), xmTaskDb.getExecutorUsername(), "您执行的任务【" + xmTaskDb.getName() + "】已验收通过，已发放佣金【"+xmTaskDb.getEfunds()+"】。",null);
				xmRecordService.addXmTaskRecord(xmTaskDb.getProductId(),xmTaskDb.getProjectId(), xmTaskDb.getId(),xmTaskDb.getMenuId(), "任务-验收", "验收任务【"+xmTaskDb.getName()+"】，验收通过。已发放佣金【"+xmTaskDb.getEfunds()+"】元");
			}else{
				notifyMsgService.pushMsg(user, xmTaskDb.getExecutorUserid(), xmTaskDb.getExecutorUsername(), "您执行的任务【" + xmTaskDb.getName() + "】已验收通过",null);
				xmRecordService.addXmTaskRecord(xmTaskDb.getProductId(),xmTaskDb.getProjectId(), xmTaskDb.getId(),xmTaskDb.getMenuId(), "任务-验收", "验收任务【"+xmTaskDb.getName()+"】，验收通过。");
			}
		return Result.ok();
		
	}


	@ApiOperation( value = "候选人报价",notes="quotePrice")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskExecuser.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	////@HasQx(value = "xm_core_xmTaskExecuser_quotePrice",name = "项目中的任务报价",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/quotePrice",method=RequestMethod.POST)
	public Result quotePrice(@RequestBody XmTaskExecuser xmTaskExecuser) {
		if(ObjectTools.isEmpty(xmTaskExecuser.getBidUserid())){
			return Result.error("bidUserid-required","投标人编号不能为空");
		}
		if(ObjectTools.isEmpty(xmTaskExecuser.getTaskId())){
			return Result.error("taskId-required","任务编号不能为空");
		}
		String taskId=xmTaskExecuser.getTaskId();
		XmTask xmTask= xmTaskService.getById(taskId);
		if(xmTask==null ){
			return Result.error("任务已不存在");
			
		}
		if(!"0".equals(xmTask.getTaskState()) && !"1".equals(xmTask.getTaskState()) ){
			return Result.error("该任务已经处于完工、结算计划，不允许再修改报价");
			
		}
		if("2".equals(xmTask.getEstate())||"3".equals(xmTask.getEstate())){
			return Result.error("estate-not-0-1-3","当前任务已缴纳保证金，无法再变更报价信息。");
		}
		User user=LoginUtils.getCurrentUserInfo();
		String projectId=xmTaskExecuser.getProjectId();
		if(!user.getUserid().equals(xmTaskExecuser.getBidUserid())) {
			Tips tips=projectQxService.checkProjectQx(xmProjectService.getProjectFromCache(projectId),2,user,xmTaskExecuser.getBidUserid(),xmTaskExecuser.getBidUsername(),xmTaskExecuser.getBidBranchId() );
			Result.assertIsFalse(tips);
		}
		XmTaskExecuser xmTaskExecuserDb = xmTaskExecuserService.selectOneObject(new XmTaskExecuser(xmTaskExecuser.getTaskId(),xmTaskExecuser.getBidUserid()));
		if("0".equals(xmTaskExecuserDb.getStatus())) {
			xmTaskExecuserService.quotePrice(xmTaskExecuser);
			notifyMsgService.pushMsg(user, xmTask.getCreateUserid(), xmTask.getCreateUsername(), user.getUsername()+"修改任务【" + xmTask.getId() + "-" + xmTask.getName() + "】的报价信息，请尽快选标！",null);


		}else {
			return Result.error("只有修改处于候选状态的投标人的报价信息");
		}
		return Result.ok();
		
	}
	@ApiOperation( value = "变更为候选人",notes="quotePrice")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskExecuser.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	////@HasQx(value = "xm_core_xmTaskExecuser_candidate",name = "变更成为任务候选人",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/candidate",method=RequestMethod.POST)
	public Result becomeCandidate(@RequestBody XmTaskExecuser xmTaskExecuser) {
		if(ObjectTools.isEmpty(xmTaskExecuser.getBidUserid())){
			return Result.error("bidUserid-required","投标人编号不能为空");
		}
		if(ObjectTools.isEmpty(xmTaskExecuser.getTaskId())){
			return Result.error("taskId-required","任务编号不能为空");
		}
		String taskId=xmTaskExecuser.getTaskId();
		XmTask xmTask= xmTaskService.selectOneObject(new XmTask(taskId));
		if(xmTask==null ){
			return Result.error("任务已不存在");
			
		}
		if(!"0".equals(xmTask.getTaskState()) && !"1".equals(xmTask.getTaskState()) ){
			return Result.error("该任务已经不需要候选人");
			
		}
		User user=LoginUtils.getCurrentUserInfo();
		String projectId=xmTaskExecuser.getProjectId();
		if(!user.getUserid().equals(xmTaskExecuser.getBidUserid())) {
			Tips  tips=projectQxService.checkProjectQx(xmProjectService.getProjectFromCache(projectId),2,user,xmTaskExecuser.getBidUserid(),xmTaskExecuser.getBidUsername(),xmTaskExecuser.getBidBranchId());
			Result.assertIsFalse(tips);
		}

		xmTaskExecuserService.becomeCandidate(xmTaskExecuser);
		notifyMsgService.pushMsg(user, xmTask.getCreateUserid(), xmTask.getCreateUsername(),  user.getUsername()+"投标任务【" + xmTask.getId() + "-" + xmTask.getName() + "】，请尽快选标！",null);

		return Result.ok();
		
	}


	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result delXmTaskExecuser(@RequestBody List<XmTaskExecuser> xmTaskExecusers){
		List<XmTaskExecuser> dbs=this.xmTaskExecuserService.listByIds(xmTaskExecusers);
		if(dbs.size()<=0){
			return Result.error("data-0","数据不存在");
		}
		XmTaskExecuser db=dbs.get(0);
		if(dbs.stream().filter(k->!k.getTaskId().equals(db.getTaskId())).findAny().isPresent()){
			return Result.error("not-same-task","批量删除只能在同一个任务内进行");
		}

		String taskId=db.getTaskId();
		XmTask xmTaskDb= xmTaskService.getById(taskId);
		if(xmTaskDb==null ){
			return Result.error("任务已不存在");

		}
		User user=LoginUtils.getCurrentUserInfo();
		XmProject xmProject=xmProjectService.getProjectFromCache(xmTaskDb.getProjectId());
		Tips tips = projectQxService.checkProjectQxBatch(xmProject,2,user,xmTaskDb.getCreateUserid(),xmTaskDb.getExecutorUserid());
		Result.assertIsFalse(tips);
		this.xmTaskExecuserService.removeBatch(xmTaskExecusers,xmTaskDb.getId());
		for (XmTaskExecuser xmTaskExecuserDb : dbs) {
			notifyMsgService.pushMsg(user, xmTaskDb.getCreateUserid(), xmTaskDb.getCreateUsername(), xmTaskExecuserDb.getBidUsername()+"离开任务【" + xmTaskDb.getId() + "-" + xmTaskDb.getName() + "】！",null);
			notifyMsgService.pushMsg(user, xmTaskExecuserDb.getBidUserid(), xmTaskExecuserDb.getBidUsername(), "您已离开任务【" + xmTaskDb.getId() + "-" + xmTaskDb.getName() + "】！",null);

		}
		return Result.ok();
	}
	
	/***/
	@ApiOperation( value = "删除一条xm_task_execuser信息",notes="delXmTaskExecuser,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	////@HasQx(value = "xm_core_xmTaskExecuser_del",name = "删除项目中任务的执行人",moduleId = "xm-project",moduleName = "管理端-项目管理系统")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTaskExecuser(@RequestBody XmTaskExecuser xmTaskExecuser){
		if(ObjectTools.isEmpty(xmTaskExecuser.getBidUserid())){
			return Result.error("bidUserid-required","投标人编号不能为空");
		}
		if(ObjectTools.isEmpty(xmTaskExecuser.getTaskId())){
			return Result.error("taskId-required","任务编号不能为空");
		}
		String taskId=xmTaskExecuser.getTaskId();
		XmTask xmTaskDb= xmTaskService.selectOneObject(new XmTask(taskId));
		if(xmTaskDb==null ){
			return Result.error("任务已不存在");
			
		}
		User user=LoginUtils.getCurrentUserInfo();
		String projectId=xmTaskDb.getProjectId();

		XmTaskExecuser xmTaskExecuserDb = xmTaskExecuserService.selectOneObject(new XmTaskExecuser(xmTaskDb.getId(),xmTaskExecuser.getBidUserid()));
		if(xmTaskExecuserDb !=null ) {
			if(!user.getUserid().equals(xmTaskExecuser.getBidUserid())) {
				Tips tips =projectQxService.checkProjectQx(xmProjectService.getProjectFromCache(projectId),2,user,xmTaskExecuserDb.getBidUserid(),xmTaskExecuserDb.getBidUsername(),xmTaskExecuserDb.getBidBranchId());
				Result.assertIsFalse(tips);
			}
			xmTaskExecuserService.delete(xmTaskExecuser);
			notifyMsgService.pushMsg(user, xmTaskDb.getCreateUserid(), xmTaskDb.getCreateUsername(), xmTaskExecuserDb.getBidUsername()+"离开任务【" + xmTaskDb.getId() + "-" + xmTaskDb.getName() + "】！",null);
			notifyMsgService.pushMsg(user, xmTaskExecuserDb.getBidUserid(), xmTaskExecuserDb.getBidUsername(), "您已离开任务【" + xmTaskDb.getId() + "-" + xmTaskDb.getName() + "】！",null);


		}
		else {
			return Result.error("没有查到数据");
		}
		return Result.ok();
	}
}
