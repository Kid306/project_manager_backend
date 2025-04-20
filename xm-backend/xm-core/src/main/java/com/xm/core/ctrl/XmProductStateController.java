package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.XmProductState;
import com.xm.core.service.XmProductStateService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对XM.xm_product_state 功能状态表,无需前端维护，所有数据由汇总统计得出的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmProductState/add <br>
 *  查询: core/xmProductState/list<br>
 *  模糊查询: core/xmProductState/listKey<br>
 *  修改: core/xmProductState/edit <br>
 *  删除: core/xmProductState/del<br>
 *  批量删除: core/xmProductState/batchDel<br>
 * 组织 com.qqkj  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmProductState 表 XM.xm_product_state 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmProductStateController")
@RequestMapping(value="/xm/core/xmProductState")
@Api(tags={"功能状态表,无需前端维护，所有数据由汇总统计得出操作接口"})
public class XmProductStateController {
	
	static Log logger=LogFactory.getLog(XmProductStateController.class);
	
	@Autowired
	private XmProductStateService xmProductStateService;
	 
		
 
	
	@ApiOperation( value = "查询功能状态表,无需前端维护，所有数据由汇总统计得出信息列表",notes="listXmProductState,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="id",value="主键,主键",required=false),
		@ApiImplicitParam(name="planStartTime",value="开始时间",required=false),
		@ApiImplicitParam(name="planEndTime",value="结束时间",required=false),
		@ApiImplicitParam(name="actStartTime",value="实际开始时间",required=false),
		@ApiImplicitParam(name="actEndTime",value="实际结束时间",required=false),
		@ApiImplicitParam(name="planWorkload",value="计划工作量，根据关联任务汇总",required=false),
		@ApiImplicitParam(name="actWorkload",value="实际工作量，根据关联任务汇总",required=false),
		@ApiImplicitParam(name="planCostAmount",value="计划成本，根据关联任务汇总",required=false),
		@ApiImplicitParam(name="actCostAmount",value="实际成本金额根据关联任务汇总",required=false),
		@ApiImplicitParam(name="finishRate",value="总体完成比例0-100之间,根据taskType进行汇总",required=false),
		@ApiImplicitParam(name="demandRate",value="需求完成率0-100之间,根据taskType进行汇总",required=false),
		@ApiImplicitParam(name="designRate",value="设计完成率0-100之间,根据taskType进行汇总",required=false),
		@ApiImplicitParam(name="devRate",value="开发完成率0-100之间,根据taskType进行汇总",required=false),
		@ApiImplicitParam(name="uatRate",value="uat测试完成率0-100之间,根据taskType进行汇总",required=false),
		@ApiImplicitParam(name="sitRate",value="sit测试完成率0-100之间,根据taskType进行汇总",required=false),
		@ApiImplicitParam(name="ctime",value="创建时间",required=false),
		@ApiImplicitParam(name="ltime",value="更新时间",required=false),
		@ApiImplicitParam(name="cuserid",value="创建人编号",required=false),
		@ApiImplicitParam(name="cusername",value="创建人姓名",required=false),
		@ApiImplicitParam(name="calcTime",value="汇总时间",required=false),
		@ApiImplicitParam(name="planWorkhours",value="工时数",required=false),
		@ApiImplicitParam(name="planWorkerCnt",value="总人数",required=false),
		@ApiImplicitParam(name="closedBugs",value="总关闭bugs",required=false),
		@ApiImplicitParam(name="activeBugs",value="激活bugs",required=false),
		@ApiImplicitParam(name="confirmedBugs",value="已确认bugs总数",required=false),
		@ApiImplicitParam(name="resolvedBugs",value="已解决bugs总数",required=false),
		@ApiImplicitParam(name="productId",value="产品编号",required=false),
		@ApiImplicitParam(name="productName",value="产品名称",required=false),
		@ApiImplicitParam(name="testCases",value="测试案例总数",required=false),
		@ApiImplicitParam(name="execCases",value="测试中案例总数",required=false),
		@ApiImplicitParam(name="designCases",value="设计中案例总数",required=false),
		@ApiImplicitParam(name="finishCases",value="完成案例总数",required=false),
		@ApiImplicitParam(name="projectCnt",value="关联项目数",required=false),
		@ApiImplicitParam(name="iterationCnt",value="关联迭代数",required=false),
		@ApiImplicitParam(name="taskCnt",value="任务总数",required=false),
		@ApiImplicitParam(name="finishTaskCnt",value="已完成的任务总数",required=false),
		@ApiImplicitParam(name="bizDate",value="业务日期yyyy-MM-dd字符串",required=false),
		@ApiImplicitParam(name="branchId",value="机构号码",required=false),
		@ApiImplicitParam(name="bugCnt",value="bug总数",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmProductState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmProductState(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		User user= LoginUtils.getCurrentUserInfo();
		params.put("branchId",user.getBranchId());
		QueryWrapper<XmProductState> qw = QueryTools.initQueryWrapper(XmProductState.class , params);
		List<Map<String,Object>> datas = xmProductStateService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmProductState列表
		
	}
	@ApiOperation( value = "计算bug、task、测试案例、等数据",notes="loadTasksToXmProductState")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/loadTasksToXmProductState",method=RequestMethod.POST)
	public Result loadTasksToXmProductState(@RequestBody Map<String,Object> params) {
		
		
		
			int i= xmProductStateService.loadTasksToXmProductState((String) params.get("productId"));
		return Result.ok();
		
	}  
	
	/**
	@ApiOperation( value = "新增一条功能状态表,无需前端维护，所有数据由汇总统计得出信息",notes="addXmProductState,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProductState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmProductState(@RequestBody XmProductState xmProductState) {

			if(StringUtils.isEmpty(xmProductState.getId())) {
				xmProductState.setId(xmProductStateService.createKey("id"));
			}else{
				 XmProductState xmProductStateQuery = new  XmProductState(xmProductState.getId());
				if(xmProductStateService.countByWhere(xmProductStateQuery)>0){
					return Result.error("编号重复，请修改编号再提交");
					
				}
			}
			xmProductStateService.insert(xmProductState);
		
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条功能状态表,无需前端维护，所有数据由汇总统计得出信息",notes="delXmProductState,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmProductState(@RequestBody XmProductState xmProductState){

			xmProductStateService.deleteByPk(xmProductState);
		return Result.ok();
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条功能状态表,无需前端维护，所有数据由汇总统计得出信息",notes="editXmProductState")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProductState.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmProductState(@RequestBody XmProductState xmProductState) {

			xmProductStateService.updateByPk(xmProductState);
		
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除功能状态表,无需前端维护，所有数据由汇总统计得出信息",notes="batchDelXmProductState,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmProductState(@RequestBody List<XmProductState> xmProductStates) {
		
		
		
			xmProductStateService.batchDelete(xmProductStates);
		return Result.ok();
		
	} 
	*/
}
