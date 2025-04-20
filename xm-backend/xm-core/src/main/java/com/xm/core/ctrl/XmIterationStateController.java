package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.xm.core.entity.XmIterationState;
import com.xm.core.service.XmIterationStateService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对XM.xm_iteration_state 迭代定义的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmIterationState/add <br>
 *  查询: core/xmIterationState/list<br>
 *  模糊查询: core/xmIterationState/listKey<br>
 *  修改: core/xmIterationState/edit <br>
 *  删除: core/xmIterationState/del<br>
 *  批量删除: core/xmIterationState/batchDel<br>
 * 组织 com.qqkj  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmIterationState 表 XM.xm_iteration_state 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmIterationStateController")
@RequestMapping(value="/xm/core/xmIterationState")
@Api(tags={"迭代定义操作接口"})
public class XmIterationStateController {
	
	static Log logger=LogFactory.getLog(XmIterationStateController.class);
	
	@Autowired
	private XmIterationStateService xmIterationStateService;
	 
		
 
	
	@ApiOperation( value = "查询迭代定义信息列表",notes="listXmIterationState,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="id",value="迭代编码,主键",required=false),
		@ApiImplicitParam(name="distBudgetCost",value="已分配到任务的预算从任务表汇总而来",required=false),
		@ApiImplicitParam(name="distBudgetWorkload",value="已分配到任务的预算工作量从任务表汇总而来",required=false),
		@ApiImplicitParam(name="actCost",value="实际成本从任务表汇总而来",required=false),
		@ApiImplicitParam(name="actWorkload",value="实际工作量从任务表汇总而来",required=false),
		@ApiImplicitParam(name="actStaffNum",value="实际投入人员数",required=false),
		@ApiImplicitParam(name="finishRate",value="进度",required=false),
		@ApiImplicitParam(name="testCases",value="测试案例总数",required=false),
		@ApiImplicitParam(name="execCases",value="测试中案例总数",required=false),
		@ApiImplicitParam(name="designCases",value="设计中案例总数",required=false),
		@ApiImplicitParam(name="finishCases",value="完成案例总数",required=false),
		@ApiImplicitParam(name="projectCnt",value="关联项目数",required=false),
		@ApiImplicitParam(name="productCnt",value="关联产品数",required=false),
		@ApiImplicitParam(name="menuCnt",value="关联需求数",required=false),
		@ApiImplicitParam(name="taskCnt",value="关联任务数",required=false),
		@ApiImplicitParam(name="finishTaskCnt",value="已完成的任务数",required=false),
		@ApiImplicitParam(name="calcTime",value="计算日期",required=false),
		@ApiImplicitParam(name="iterationName",value="迭代名称",required=false),
		@ApiImplicitParam(name="budgetCost",value="预算成本",required=false),
		@ApiImplicitParam(name="budgetWorkload",value="预算工作量",required=false),
		@ApiImplicitParam(name="iterationId",value="迭代编号",required=false),
		@ApiImplicitParam(name="bizDate",value="业务日期yyyy-MM-dd字符串",required=false),
		@ApiImplicitParam(name="closedBugCnt",value="已关闭bug总数",required=false),
		@ApiImplicitParam(name="resolvedBugCnt",value="已解决bug总数",required=false),
		@ApiImplicitParam(name="activeBugCnt",value="激活的bug总数",required=false),
		@ApiImplicitParam(name="confirmedBugCnt",value="已解决bug总数",required=false),
		@ApiImplicitParam(name="bugCnt",value="bug总数",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmIterationState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmIterationState(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmIterationState> qw = QueryTools.initQueryWrapper(XmIterationState.class , params);
		List<Map<String,Object>> datas = xmIterationStateService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmIterationState列表
		
	}

	@ApiOperation( value = "计算bug、task、测试案例、等数据",notes="loadTasksToXmIterationState")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/loadTasksToXmIterationState",method=RequestMethod.POST)
	public Result loadTasksToXmIterationState(@RequestBody Map<String,Object> params) {
		
		
		
			int i= xmIterationStateService.loadTasksToXmIterationState((String) params.get("iterationId"));
		return Result.ok();
		
	}  
 
	
	/**
	@ApiOperation( value = "新增一条迭代定义信息",notes="addXmIterationState,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmIterationState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmIterationState(@RequestBody XmIterationState xmIterationState) {

			if(StringUtils.isEmpty(xmIterationState.getId())) {
				xmIterationState.setId(xmIterationStateService.createKey("id"));
			}else{
				 XmIterationState xmIterationStateQuery = new  XmIterationState(xmIterationState.getId());
				if(xmIterationStateService.countByWhere(xmIterationStateQuery)>0){
					return Result.error("编号重复，请修改编号再提交");
					
				}
			}
			xmIterationStateService.insert(xmIterationState);
		
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条迭代定义信息",notes="delXmIterationState,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmIterationState(@RequestBody XmIterationState xmIterationState){

			xmIterationStateService.deleteByPk(xmIterationState);
		return Result.ok();
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条迭代定义信息",notes="editXmIterationState")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmIterationState.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmIterationState(@RequestBody XmIterationState xmIterationState) {

			xmIterationStateService.updateByPk(xmIterationState);
		
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除迭代定义信息",notes="batchDelXmIterationState,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmIterationState(@RequestBody List<XmIterationState> xmIterationStates) {
		
		
		
			xmIterationStateService.batchDelete(xmIterationStates);
		return Result.ok();
		
	} 
	*/
}
