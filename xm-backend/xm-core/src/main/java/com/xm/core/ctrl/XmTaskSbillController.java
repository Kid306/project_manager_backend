package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.DateUtils;
import com.mdp.core.utils.NumberUtil;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.XmTask;
import com.xm.core.entity.XmTaskSbill;
import com.xm.core.entity.XmTaskSbillDetail;
import com.xm.core.entity.XmWorkload;
import com.xm.core.service.XmTaskSbillDetailService;
import com.xm.core.service.XmTaskSbillService;
import com.xm.core.service.XmTaskService;
import com.xm.core.service.XmWorkloadService;
import com.xm.core.vo.BatchJoinToSbillVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.map;
/**
 * url编制采用rest风格,如对xm_task_sbill 任务结算表的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmTaskSbill/add <br>
 *  查询: core/xmTaskSbill/list<br>
 *  模糊查询: core/xmTaskSbill/listKey<br>
 *  修改: core/xmTaskSbill/edit <br>
 *  删除: core/xmTaskSbill/del<br>
 *  批量删除: core/xmTaskSbill/batchDel<br>
 * 组织 com  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmTaskSbill 表 xm_task_sbill 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmTaskSbillController")
@RequestMapping(value="/xm/core/xmTaskSbill")
@Api(tags={"任务结算表操作接口"})
public class XmTaskSbillController {
	
	static Logger logger =LoggerFactory.getLogger(XmTaskSbillController.class);
	
	@Autowired
	private XmTaskSbillService xmTaskSbillService;
	@Autowired
	XmTaskSbillDetailService xmTaskSbillDetailService;

	@Autowired
	XmTaskService xmTaskService;


	@Autowired
	XmWorkloadService xmWorkloadService;

	
	@ApiOperation( value = "查询任务结算表信息列表",notes=" ") 
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskSbill.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmTaskSbill(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		User user=LoginUtils.getCurrentUserInfo();
		params.put("branchId",user.getBranchId());
		QueryTools.alias(params,"* res.");
		QueryWrapper<XmTaskSbill> qw = QueryTools.initQueryWrapper(XmTaskSbill.class , params);
		List<Map<String,Object>> datas = xmTaskSbillService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmTaskSbill列表

	}
	
 
	

	@ApiOperation( value = "新增一条任务结算表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskSbill.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmTaskSbill(@RequestBody XmTaskSbill xmTaskSbill) {

		    boolean createPk=false;
			if(StringUtils.isEmpty(xmTaskSbill.getId())) {
			    createPk=true;
				xmTaskSbill.setId(xmTaskSbillService.createKey("id"));
			}
			if(createPk==false){
                 if(xmTaskSbillService.selectOneObject(xmTaskSbill) !=null ){
                    return Result.error("编号重复，请修改编号再提交"); 
                }
            }
			User user = LoginUtils.getCurrentUserInfo();
			xmTaskSbill.setAmt(BigDecimal.ZERO);
			xmTaskSbill.setCtime(new Date());
			xmTaskSbill.setCuserid(user.getUserid());
			xmTaskSbill.setCusername(user.getUsername());
			xmTaskSbill.setBizDate(DateUtils.getDate("yyyy-MM-dd"));
			xmTaskSbill.setBranchId(user.getBranchId());
			xmTaskSbill.setDeptid(user.getDeptid());
			xmTaskSbill.setWorkload(BigDecimal.ZERO);
			xmTaskSbill.setBizFlowState("0");
			xmTaskSbill.setBizProcInstId(xmTaskSbill.getId());
			xmTaskSbill.setLtime(new Date());
			xmTaskSbill.setStatus("0");
			xmTaskSbill.setFmsg("");

			xmTaskSbillService.insert(xmTaskSbill);
			return Result.ok();
	}

	

	@ApiOperation( value = "删除一条任务结算表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmTaskSbill(@RequestBody XmTaskSbill xmTaskSbill){
		
		
		if( xmTaskSbill==null || !StringUtils.hasText(xmTaskSbill.getId())){
			return Result.error("请上送结算单编号"); 
		}
		XmTaskSbill sbillDb=this.xmTaskSbillService.selectOneById(xmTaskSbill.getId());
		if(!"0".equals(sbillDb.getStatus())){
			return Result.error("只有待提交的结算单才能删除"); 
		}
		if(!("0".equals(sbillDb.getBizFlowState()) || "4".equals(sbillDb.getBizFlowState()))){
			return Result.error("已发审数据不允许删除"); 
		}
				
			//删除结算单时候，要一起恢复工时单为未加入结算状态
			xmTaskSbillService.deleteByPkAndReturnWorkload(sbillDb);
		return Result.ok();
		
	}



	@ApiOperation( value = "批量将工时加入到一个结算单中",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200,response=XmTaskSbill.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/batchJoinToSbill",method=RequestMethod.POST)
	public Result batchJoinToSbill(@RequestBody BatchJoinToSbillVo batchJoinToSbill) {

		
		
		if(!StringUtils.hasText(batchJoinToSbill.getSbillId())){
			return Result.error("sbillId-0","请上送结算单编号");
		}
		if(batchJoinToSbill.getWorkloadIds()==null){
			return Result.error("workloadIds-0","请上送workloadIds");
		}
		User user=LoginUtils.getCurrentUserInfo();
				
			XmTaskSbill sbillDb=this.xmTaskSbillService.selectOneById(batchJoinToSbill.getSbillId());
			if(sbillDb==null){
				return Result.error("sbill-0","结算单不存在");
			}
			if(!"0".equals(sbillDb.getStatus())){
				return Result.error("status-not-0","结算单已提交，不允许再加入工时");
			}
			if(!user.getUserid().equals(sbillDb.getCuserid())){
				return Result.error("cuserid-0","结算单不是您的结算单，您不能操作");
			}
			List<XmWorkload> workloadsDb= xmWorkloadService.selectListByIds(batchJoinToSbill.getWorkloadIds());
			if(workloadsDb==null || workloadsDb.size()<=0){
				return Result.error("workloadsDb-0","工时单已不存在");
			}
			List<XmWorkload> workloadsDb2=workloadsDb.stream().filter(i->!StringUtils.hasText(i.getSbillId()) && "1".equals(i.getSstatus())&&"1".equals(i.getWstatus())).collect(Collectors.toList());
 			if(workloadsDb2==null || workloadsDb2.size()<=0){
				return Result.error("workloadsDb-0","不存在可以结算的工时单。");
			}

			if(workloadsDb2.stream().map(i->i.getProjectId()).collect(Collectors.toSet()).size()>1){
				return Result.error("projectId-not-1","不能一次性处理多个项目的工时单，请选择同一个项目的工时单再尝试。");
			}
			String projectId= sbillDb.getProjectId();
			if(workloadsDb2.stream().filter(k->!k.getProjectId().equals(projectId)).findAny().isPresent()){
				return Result.error("projectId-0",String.format("结算单项目编号为%s,请选择同项目的工时单加入结算单。",projectId));
			}
			List<XmTask> xmTasksDb=this.xmTaskService.selectListByIds(workloadsDb2.stream().map(i->i.getTaskId()).collect(Collectors.toList()));
			if(xmTasksDb==null || xmTasksDb.size()==0){
				return Result.error("xmTasksDb-0","相关任务已不存在。");
			}
//			List<XmTask> xmTasksDb2=xmTasksDb.stream().filter(i->"2".equals(i.getTaskState())).collect(Collectors.toList());
//			if(xmTasksDb2==null || xmTasksDb2.size()==0){
//				return Result.error("taskState-not-2","任务必须是已完工状态才能结算。");
//			}
			List<XmWorkload> workloadsDb3=workloadsDb2.stream().filter(i->xmTasksDb.stream().filter(k->k.getId().equals(i.getTaskId())).findAny().isPresent()).collect(Collectors.toList());


			//检查是否已有同样的数据加入了结算单，如果有，需要合并
			List<Map<String,Object>> userTasks=workloadsDb3.stream().map(i->map("userid",i.getUserid(),"taskId",i.getTaskId())).collect(Collectors.toList());
			Map<String,Object> userTasksMap=map("userTasks",userTasks);
			List<XmTaskSbillDetail> details=xmTaskSbillDetailService.selectListByUserTasks(userTasksMap);
			List<XmTaskSbillDetail> sameSbillDetails=details.stream().filter(i->sbillDb.getId().equals(i.getSbillId())).collect(Collectors.toList());
			List<XmTaskSbillDetail> othSbillDetails=details.stream().filter(i->!sbillDb.getId().equals(i.getSbillId())).collect(Collectors.toList());
 			for (XmTaskSbillDetail i : othSbillDetails) {
				if(!"4".equals(i.getSstatus())){
					return Result.error("user-task-exists-not-4",String.format("任务【%s】，人员【%s】存在未完成的结算单【%s】，暂时不允许发起结算。",i.getTaskName(),i.getUsername(),i.getSbillId()));
				}
			}
			if(sameSbillDetails!=null && sameSbillDetails.size()>0){
				for (XmTaskSbillDetail detail : sameSbillDetails) {
					//进行合并操作
					for (XmWorkload xmWorkload : workloadsDb3) {
						if(detail.getUserid().equals(xmWorkload.getUserid()) && detail.getTaskId().equals(xmWorkload.getTaskId())){
							detail.setWorkload(NumberUtil.getBigDecimal(detail.getWorkload(),BigDecimal.ZERO).add(NumberUtil.getBigDecimal(xmWorkload.getWorkload(),BigDecimal.ZERO)));
							detail.setSworkload(NumberUtil.getBigDecimal(detail.getSworkload(),BigDecimal.ZERO).add(NumberUtil.getBigDecimal(xmWorkload.getWorkload(),BigDecimal.ZERO)));
						}
					}
				}
			}


			List<XmWorkload> workloadsDb4=workloadsDb3.stream().filter(i->!sameSbillDetails.stream().filter(k->k.getUserid().equals(i.getUserid()) && k.getTaskId().equals(i.getTaskId())).findAny().isPresent()).collect(Collectors.toList());
			Map<String,XmTaskSbillDetail> detailMap=new HashMap<>();
			for (XmWorkload xmWorkload : workloadsDb4) {
				XmTaskSbillDetail detail=detailMap.get(xmWorkload.getUserid()+"-"+xmWorkload.getTaskId());
				if(detail==null){
					detail=new XmTaskSbillDetail();
					BeanUtils.copyProperties(xmWorkload,detail);
					XmTask xmTask=xmTasksDb.stream().filter(i->i.getId().equals(xmWorkload.getTaskId())).findAny().get();
					detail.setSworkload(NumberUtil.getBigDecimal(xmWorkload.getWorkload(),BigDecimal.ZERO));
					detail.setId(this.xmTaskSbillDetailService.createKey("id"));
					detail.setBizDate(DateUtils.getDate("yyyy-MM-dd"));
					detail.setBizMonth(DateUtils.getDate("yyyy-MM"));
					detail.setSbillId(batchJoinToSbill.getSbillId());
					detail.setProjectId(projectId);
					detail.setBranchId(user.getBranchId());
					detail.setCtime(new Date());
					detail.setBudgetAt(xmTask.getBudgetAt());
					detail.setBudgetWorkload(xmTask.getBudgetWorkload());
					detail.setQuoteAt(xmTask.getQuoteFinalAt());
					detail.setTaskOut(xmTask.getTaskOut());
					detail.setCrowd(xmTask.getCrowd());
					detail.setSschemel(xmTask.getSettleSchemel());
					detail.setShareFee(xmTask.getShareFee());
					detail.setOshare(xmTask.getOshare());
					detail.setTaskName(xmTask.getName());
					detail.setUniPrice("1".equals(xmTask.getTaskOut())?xmTask.getUniOutPrice():xmTask.getUniInnerPrice());
					detailMap.put(xmWorkload.getUserid()+"-"+xmWorkload.getTaskId(),detail);
				}else{
					detail.setWorkload(detail.getWorkload().add(NumberUtil.getBigDecimal(xmWorkload.getWorkload(),BigDecimal.ZERO)));
					detail.setSworkload(detail.getSworkload().add(NumberUtil.getBigDecimal(xmWorkload.getWorkload(),BigDecimal.ZERO)));
				}

			}
			List<XmTaskSbillDetail> canAdd= detailMap.values().stream().collect(Collectors.toList());
			for (XmTaskSbillDetail d : canAdd) {
				List<XmTaskSbillDetail> othDetails=othSbillDetails.stream().filter(i->i.getTaskId().equals(d.getTaskId()) && i.getUserid().equals(d.getUserid())).collect(Collectors.toList());
				BigDecimal tactAt=BigDecimal.ZERO;
				for (XmTaskSbillDetail othDetail : othDetails) {
					tactAt=tactAt.add(othDetail.getAmt());
				}
				d.setTactAt(tactAt);
				this.xmTaskSbillDetailService.preCalcSamt(d);

			}
			this.xmTaskSbillService.batchJoinToSbill(workloadsDb3.stream().map(i->i.getId()).collect(Collectors.toList()), canAdd,sameSbillDetails);


		return Result.ok();
		
	}

	@ApiOperation( value = "根据主键修改一条任务结算表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmTaskSbill.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmTaskSbill(@RequestBody XmTaskSbill xmTaskSbill) {
		
		
		if( xmTaskSbill==null || !StringUtils.hasText(xmTaskSbill.getId())){
			return Result.error("请上送结算单编号"); 
		}
		XmTaskSbill sbillDb=this.xmTaskSbillService.selectOneById(xmTaskSbill.getId());

		if(!"0".equals(sbillDb.getStatus())){
			return Result.error("只能修改待提交的结算单"); 
		}
		if(!("0".equals(sbillDb.getBizFlowState()) || "4".equals(sbillDb.getBizFlowState()))){
			return Result.error("已发审数据不允许修改"); 
		}
				
			xmTaskSbill.setLtime(new Date());
			xmTaskSbillService.updateByPk(xmTaskSbill);
		return Result.ok();
	}

	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除任务结算表信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmTaskSbill(@RequestBody List<XmTaskSbill> xmTaskSbills) {

			xmTaskSbillService.batchDelete(xmTaskSbills);
		}catch (BizException e) { 
			Tips tips=e.getTips();
			logger.error("",e);
		}catch (Exception e) {
			return Result.error(e.getMessage());
			logger.error("",e);
		}
		
	}
	*/

	@ApiOperation( value = "流程审批信息，审批通过则更新sbill审批状态",notes="从workflow传过来")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/processApprova",method=RequestMethod.POST)
	public Result sbillProcessApprova(@RequestBody Map<String,Object> paramMap) {
		Map<String,Object> map=new HashMap<>();
		

		String sbillId= (String) paramMap.get("sbillId");
		if( !StringUtils.hasText(sbillId)){
			return Result.error("结算单ID必传");
		}
		map.putAll(paramMap);
		this.xmTaskSbillService.processApprova(map);
		return Result.ok();
	}
}
