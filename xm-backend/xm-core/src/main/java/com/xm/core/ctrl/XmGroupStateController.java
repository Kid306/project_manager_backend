package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.core.valid.ValidUtil;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmGroupState;
import com.xm.core.service.XmGroupStateService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对XM.xm_group_state 功能状态表,无需前端维护，所有数据由汇总统计得出的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmGroupState/add <br>
 *  查询: core/xmGroupState/list<br>
 *  模糊查询: core/xmGroupState/listKey<br>
 *  修改: core/xmGroupState/edit <br>
 *  删除: core/xmGroupState/del<br>
 *  批量删除: core/xmGroupState/batchDel<br>
 * 组织 com.qqkj  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmProjectGroupState 表 XM.xm_group_state 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmGroupStateController")
@RequestMapping(value="/xm/core/xmGroupState")
@Api(tags={"功能状态表,无需前端维护，所有数据由汇总统计得出操作接口"})
public class XmGroupStateController {
	
	static Log logger=LogFactory.getLog(XmGroupStateController.class);
	
	@Autowired
	private XmGroupStateService xmGroupStateService;
	 
		
 
	
	@ApiOperation( value = "查询功能状态表,无需前端维护，所有数据由汇总统计得出信息列表",notes="listXmProjectGroupState,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmGroupState.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmGroupState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmProjectGroupState(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page= QueryTools.initPage(params);
		QueryWrapper<XmGroupState> qw = QueryTools.initQueryWrapper(XmGroupState.class , params);
		List<Map<String,Object>> datas = xmGroupStateService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmProjectGroupState列表
		
	}
	
 

	@ApiOperation( value = "计算bug、task、测试案例、等数据",notes="loadTasksToXmProjectGroupState")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/loadTasksToXmGroupState",method=RequestMethod.POST)
	public Result loadTasksToXmProjectGroupState(@RequestBody Map<String,Object> params) {
		ValidUtil.isRequired("projectId","项目编号",params.get("projectId"));
			int i= xmGroupStateService.loadTasksToXmProjectGroupState((String) params.get("projectId"));
		return Result.ok();
		
	}  
	/**
	@ApiOperation( value = "新增一条功能状态表,无需前端维护，所有数据由汇总统计得出信息",notes="addXmProjectGroupState,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProjectGroupState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmProjectGroupState(@RequestBody XmProjectGroupState xmGroupState) {

			if(StringUtils.isEmpty(xmGroupState.getId())) {
				xmGroupState.setId(xmGroupStateService.createKey("id"));
			}else{
				 XmProjectGroupState xmGroupStateQuery = new  XmProjectGroupState(xmGroupState.getId());
				if(xmGroupStateService.countByWhere(xmGroupStateQuery)>0){
					return Result.error("编号重复，请修改编号再提交");
					
				}
			}
			xmGroupStateService.insert(xmGroupState);
		
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条功能状态表,无需前端维护，所有数据由汇总统计得出信息",notes="delXmProjectGroupState,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmProjectGroupState(@RequestBody XmProjectGroupState xmGroupState){

			xmGroupStateService.deleteByPk(xmGroupState);
		return Result.ok();
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条功能状态表,无需前端维护，所有数据由汇总统计得出信息",notes="editXmProjectGroupState")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmProjectGroupState.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmProjectGroupState(@RequestBody XmProjectGroupState xmGroupState) {

			xmGroupStateService.updateByPk(xmGroupState);
		
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除功能状态表,无需前端维护，所有数据由汇总统计得出信息",notes="batchDelXmProjectGroupState,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmProjectGroupState(@RequestBody List<XmProjectGroupState> xmGroupStates) {
		
		
		
			xmGroupStateService.batchDelete(xmGroupStates);
		return Result.ok();
		
	} 
	*/
}
