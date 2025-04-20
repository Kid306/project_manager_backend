package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.swagger.ApiEntityParams;
import com.xm.core.entity.XmBranchState;
import com.xm.core.entity.XmBranchTaskTypeState;
import com.xm.core.service.XmBranchTaskTypeStateService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对XM.xm_branch_task_type_state 按机构编号任务类型汇总的操作有增删改查,对应的url分别为:<br>
 *  新增: core/xmBranchTaskTypeState/add <br>
 *  查询: core/xmBranchTaskTypeState/list<br>
 *  模糊查询: core/xmBranchTaskTypeState/listKey<br>
 *  修改: core/xmBranchTaskTypeState/edit <br>
 *  删除: core/xmBranchTaskTypeState/del<br>
 *  批量删除: core/xmBranchTaskTypeState/batchDel<br>
 * 组织 com.qqkj  顶级模块 xm 大模块 core 小模块 <br>
 * 实体 XmBranchTaskTypeState 表 XM.xm_branch_task_type_state 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmBranchTaskTypeStateController")
@RequestMapping(value="/xm/core/xmBranchTaskTypeState")
@Api(tags={"按机构编号任务类型汇总操作接口"})
public class XmBranchTaskTypeStateController {
	
	static Log logger=LogFactory.getLog(XmBranchTaskTypeStateController.class);
	
	@Autowired
	private XmBranchTaskTypeStateService xmBranchTaskTypeStateService;
	 
		
 
	
	@ApiOperation( value = "查询按机构编号任务类型汇总信息列表",notes="listXmBranchTaskTypeState,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(XmBranchTaskTypeState.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmBranchTaskTypeState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmBranchTaskTypeState(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmBranchTaskTypeState> qw = QueryTools.initQueryWrapper(XmBranchTaskTypeState.class , params);
		List<Map<String,Object>> datas = xmBranchTaskTypeStateService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmBranchTaskTypeState列表
		
	}
	
 
	@ApiOperation( value = "从项目任务类型汇总表汇总数据到机构任务类型汇总表",notes="")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/loadProjectTaskTypeStateToXmBranchTaskTypeState",method=RequestMethod.POST)
	public Result loadProjectTaskTypeStateToXmBranchTaskTypeState(@RequestBody XmBranchState xmBranchState){

			if(StringUtils.isEmpty(xmBranchState.getBranchId())) {
				return Result.error("机构编号branchId必填");
			}else {
	
				xmBranchTaskTypeStateService.loadProjectTaskTypeStateToXmBranchTaskTypeState(xmBranchState.getBranchId());
			}
		return Result.ok();
		
	}
	/**
	@ApiOperation( value = "新增一条按机构编号任务类型汇总信息",notes="addXmBranchTaskTypeState,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmBranchTaskTypeState.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmBranchTaskTypeState(@RequestBody XmBranchTaskTypeState xmBranchTaskTypeState) {

			if(StringUtils.isEmpty(xmBranchTaskTypeState.getId())) {
				xmBranchTaskTypeState.setId(xmBranchTaskTypeStateService.createKey("id"));
			}else{
				 XmBranchTaskTypeState xmBranchTaskTypeStateQuery = new  XmBranchTaskTypeState(xmBranchTaskTypeState.getId());
				if(xmBranchTaskTypeStateService.countByWhere(xmBranchTaskTypeStateQuery)>0){
					return Result.error("编号重复，请修改编号再提交");
					
				}
			}
			xmBranchTaskTypeStateService.insert(xmBranchTaskTypeState);
		
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条按机构编号任务类型汇总信息",notes="delXmBranchTaskTypeState,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmBranchTaskTypeState(@RequestBody XmBranchTaskTypeState xmBranchTaskTypeState){

			xmBranchTaskTypeStateService.deleteByPk(xmBranchTaskTypeState);
		return Result.ok();
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条按机构编号任务类型汇总信息",notes="editXmBranchTaskTypeState")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmBranchTaskTypeState.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmBranchTaskTypeState(@RequestBody XmBranchTaskTypeState xmBranchTaskTypeState) {

			xmBranchTaskTypeStateService.updateByPk(xmBranchTaskTypeState);
		
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除按机构编号任务类型汇总信息",notes="batchDelXmBranchTaskTypeState,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmBranchTaskTypeState(@RequestBody List<XmBranchTaskTypeState> xmBranchTaskTypeStates) {
		
		
		
			xmBranchTaskTypeStateService.batchDelete(xmBranchTaskTypeStates);
		return Result.ok();
		
	} 
	*/
}
