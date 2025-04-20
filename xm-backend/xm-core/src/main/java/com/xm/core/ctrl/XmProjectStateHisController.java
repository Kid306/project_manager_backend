package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.XmProjectStateHis;
import com.xm.core.service.XmProjectStateHisService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对XM.xm_project_state_his 项目指标日统计表的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmProjectStateHis/add <br>
 *  查询: core/xmProjectStateHis/list<br>
 *  模糊查询: core/xmProjectStateHis/listKey<br>
 *  修改: core/xmProjectStateHis/edit <br>
 *  删除: core/xmProjectStateHis/del<br>
 *  批量删除: core/xmProjectStateHis/batchDel<br>
 * 组织 com.qqkj  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmProjectStateHis 表 XM.xm_project_state_his 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmProjectStateHisController")
@RequestMapping(value="/xm/core/xmProjectStateHis")
@Api(tags={"项目指标日统计表操作接口"})
public class XmProjectStateHisController {
	
	static Log logger=LogFactory.getLog(XmProjectStateHisController.class);
	
	@Autowired
	private XmProjectStateHisService xmProjectStateHisService;
	 
		
 
	
	@ApiOperation( value = "查询项目指标日统计表信息列表",notes="listXmProjectStateHis,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="id",value="主键,主键",required=false),
		@ApiImplicitParam(name="projectId",value="项目编号",required=false),
		@ApiImplicitParam(name="bizDate",value="统计日期yyyy-mm-dd类型",required=false),
		@ApiImplicitParam(name="totalFileCnt",value="文件数据",required=false),
		@ApiImplicitParam(name="totalBugCnt",value="bug数目",required=false),
		@ApiImplicitParam(name="totalTaskCnt",value="任务数",required=false),
		@ApiImplicitParam(name="totalBudgetNouserAmount",value="项目总非人力预算-来自项目表",required=false),
		@ApiImplicitParam(name="projectName",value="项目名称",required=false),
		@ApiImplicitParam(name="totalStaffCnt",value="总参与人数",required=false),
		@ApiImplicitParam(name="calcTime",value="统计执行日期",required=false),
		@ApiImplicitParam(name="calcStatus",value="0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖",required=false),
		@ApiImplicitParam(name="totalCostNouserAmount",value="项目总非人力成本",required=false),
		@ApiImplicitParam(name="totalClosedBugCnt",value="已关闭bug总数",required=false),
		@ApiImplicitParam(name="totalResolvedBugCnt",value="已解决bug总数",required=false),
		@ApiImplicitParam(name="totalCompleteTaskCnt",value="已完成任务总数-来自任务表",required=false),
		@ApiImplicitParam(name="totalPhaseCnt",value="项目计划数",required=false),
		@ApiImplicitParam(name="totalCompletePhaseCnt",value="项目计划已完成数",required=false),
		@ApiImplicitParam(name="totalNeedPayAmount",value="待付款总金额",required=false),
		@ApiImplicitParam(name="totalFinishPayAmount",value="已付款总金额",required=false),
		@ApiImplicitParam(name="totalNeedColAmount",value="待收款总金额",required=false),
		@ApiImplicitParam(name="totalFinishColAmount",value="已收款总金额",required=false),
		@ApiImplicitParam(name="totalCostUserAmount",value="项目总人力成本",required=false),
		@ApiImplicitParam(name="totalBudgetIuserAmount",value="项目总内部人力预算-来自项目表",required=false),
		@ApiImplicitParam(name="totalPlanWorkload",value="项目总预算工作量-来自项目表",required=false),
		@ApiImplicitParam(name="totalRiskCnt",value="项目风险总数",required=false),
		@ApiImplicitParam(name="totalCompleteRiskCnt",value="已完成风险总数",required=false),
		@ApiImplicitParam(name="branchId",value="机构编号",required=false),
		@ApiImplicitParam(name="branchName",value="机构名称",required=false),
		@ApiImplicitParam(name="totalBudgetOuserAmount",value="项目总外购人力预算-来自项目表",required=false),
		@ApiImplicitParam(name="totalCompleteWorkload",value="已完成工作量-来自计划中实际完成工作量",required=false),
		@ApiImplicitParam(name="totalCostIuserAmount",value="项目总内部人力成本金额",required=false),
		@ApiImplicitParam(name="totalCostOuserAmount",value="项目总外购人力成本金额",required=false),
		@ApiImplicitParam(name="totalProgress",value="项目进度0~100之间，来自任务表",required=false),
		@ApiImplicitParam(name="totalActiveBugCnt",value="激活的bug总数",required=false),
		@ApiImplicitParam(name="totalConfirmedBugCnt",value="已解决bug总数",required=false),
		@ApiImplicitParam(name="projectStatus",value="项目状态，0-初始，1-立项中，2-执行中，3-已结项，4-暂停",required=false),
		@ApiImplicitParam(name="totalActWorkload",value="实际总工作量，来自任务表",required=false),
		@ApiImplicitParam(name="totalActOutWorkload",value="实际外购总工作量，来自任务表",required=false),
		@ApiImplicitParam(name="totalActInnerWorkload",value="实际内部总工作量，来自任务表",required=false),
		@ApiImplicitParam(name="totalTaskBudgetCostAt",value="已经分配到任务的总预算",required=false),
		@ApiImplicitParam(name="totalTaskOutCnt",value="外购任务数，来自任务表",required=false),
		@ApiImplicitParam(name="totalNeedPayCnt",value="待付款笔数",required=false),
		@ApiImplicitParam(name="totalFinishPayCnt",value="完成付款总比数",required=false),
		@ApiImplicitParam(name="totalFinishPayUserCnt",value="已付款总人数",required=false),
		@ApiImplicitParam(name="totalNeedPayUserCnt",value="待付款总人数",required=false),
		@ApiImplicitParam(name="totalPlanIuserWorkload",value="内部人力总工作量-应该大于或等于计划内部人力总成本",required=false),
		@ApiImplicitParam(name="totalPlanOuserWorkload",value="外购人力总工作量-应该大于或等于计划外购人力总成本",required=false),
		@ApiImplicitParam(name="testCases",value="测试案例总数",required=false),
		@ApiImplicitParam(name="execCases",value="测试中案例总数",required=false),
		@ApiImplicitParam(name="designCases",value="设计中案例总数",required=false),
		@ApiImplicitParam(name="finishCases",value="完成案例总数",required=false),
		@ApiImplicitParam(name="iterationCnt",value="迭代数",required=false),
		@ApiImplicitParam(name="productCnt",value="产品数",required=false),
		@ApiImplicitParam(name="menuCnt",value="需求数",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmProjectStateHis.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmProjectStateHis(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		User user= LoginUtils.getCurrentUserInfo();
		params.put("branchId",user.getBranchId());
		QueryWrapper<XmProjectStateHis> qw = QueryTools.initQueryWrapper(XmProjectStateHis.class , params);
		List<Map<String,Object>> datas = xmProjectStateHisService.selectListMapByWhere(page,qw,params);
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmProjectStateHis列表
		
	}
	
 
	
	/**
	@ApiOperation( value = "新增一条项目指标日统计表信息",notes="addXmProjectStateHis,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProjectStateHis.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmProjectStateHis(@RequestBody XmProjectStateHis xmProjectStateHis) {

			if(StringUtils.isEmpty(xmProjectStateHis.getId())) {
				xmProjectStateHis.setId(xmProjectStateHisService.createKey("id"));
			}else{
				 XmProjectStateHis xmProjectStateHisQuery = new  XmProjectStateHis(xmProjectStateHis.getId());
				if(xmProjectStateHisService.countByWhere(xmProjectStateHisQuery)>0){
					return Result.error("编号重复，请修改编号再提交");
					
				}
			}
			xmProjectStateHisService.insert(xmProjectStateHis);
		
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条项目指标日统计表信息",notes="delXmProjectStateHis,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmProjectStateHis(@RequestBody XmProjectStateHis xmProjectStateHis){

			xmProjectStateHisService.deleteByPk(xmProjectStateHis);
		return Result.ok();
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条项目指标日统计表信息",notes="editXmProjectStateHis")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProjectStateHis.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmProjectStateHis(@RequestBody XmProjectStateHis xmProjectStateHis) {

			xmProjectStateHisService.updateByPk(xmProjectStateHis);
		
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除项目指标日统计表信息",notes="batchDelXmProjectStateHis,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmProjectStateHis(@RequestBody List<XmProjectStateHis> xmProjectStateHiss) {
		
		
		
			xmProjectStateHisService.batchDelete(xmProjectStateHiss);
		return Result.ok();
		
	} 
	*/
}
