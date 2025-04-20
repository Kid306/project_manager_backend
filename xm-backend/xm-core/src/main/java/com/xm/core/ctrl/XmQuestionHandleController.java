package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.xm.core.entity.XmQuestionHandle;
import com.xm.core.service.XmQuestionHandleService;
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
 * url编制采用rest风格,如对XM.xm_question_handle xm_question_handle的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmQuestionHandle/add <br>
 *  查询: xm/xmQuestionHandle/list<br>
 *  模糊查询: xm/xmQuestionHandle/listKey<br>
 *  修改: xm/xmQuestionHandle/edit <br>
 *  删除: xm/xmQuestionHandle/del<br>
 *  批量删除: xm/xmQuestionHandle/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmQuestionHandle 表 XM.xm_question_handle 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmQuestionHandleController")
@RequestMapping(value="/xm/core/xmQuestionHandle")
@Api(tags={"xm_question_handle操作接口"})
public class XmQuestionHandleController {
	
	static Log logger=LogFactory.getLog(XmQuestionHandleController.class);
	
	@Autowired
	private XmQuestionHandleService xmQuestionHandleService;
	 
		
 
	
	@ApiOperation( value = "查询xm_question_handle信息列表",notes="listXmQuestionHandle,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="id",value="主键,主键",required=false),
		@ApiImplicitParam(name="handlerUserid",value="处理人编号",required=false),
		@ApiImplicitParam(name="handlerUsername",value="处理人",required=false),
		@ApiImplicitParam(name="handleSolution",value="解决方案:",required=false),
		@ApiImplicitParam(name="receiptMessage",value="回执信息",required=false),
		@ApiImplicitParam(name="receiptTime",value="回执时间",required=false),
		@ApiImplicitParam(name="handleStatus",value="create创建（active激活）–confirm确认（confirmed已确认）–solve解决（resolved已解决）–close关闭（closed已关闭）",required=false),
		@ApiImplicitParam(name="bizProcInstId",value="当前流程实例编号",required=false),
		@ApiImplicitParam(name="bizFlowState",value="当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除",required=false),
		@ApiImplicitParam(name="questionId",value="问题编号",required=false),
		@ApiImplicitParam(name="lastUpdateTime",value="最后更新日期",required=false),
		@ApiImplicitParam(name="createTime",value="创建时间",required=false),
		@ApiImplicitParam(name="actWorkload",value="实际工时",required=false),
		@ApiImplicitParam(name="actCostAmount",value="实际金额",required=false),
		@ApiImplicitParam(name="urls",value="链接地址列表逗号分隔",required=false),
		@ApiImplicitParam(name="targetUserid",value="指派给谁",required=false),
		@ApiImplicitParam(name="targetUsername",value="指派给谁",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmQuestionHandle.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmQuestionHandle(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmQuestionHandle> qw = QueryTools.initQueryWrapper(XmQuestionHandle.class , params);
		List<Map<String,Object>> datas = xmQuestionHandleService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmQuestionHandle列表
		
	}
	
 

	@ApiOperation( value = "新增一条xm_question_handle信息",notes="addXmQuestionHandle,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmQuestionHandle.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmQuestionHandle(@RequestBody XmQuestionHandle xmQuestionHandle) {

			if(StringUtils.isEmpty(xmQuestionHandle.getId())) {
				xmQuestionHandle.setId(xmQuestionHandleService.createKey("id"));
			}else{
				 XmQuestionHandle xmQuestionHandleQuery = new  XmQuestionHandle(xmQuestionHandle.getId());
				if(xmQuestionHandleService.countByWhere(xmQuestionHandleQuery)>0){
					return Result.error("编号重复，请修改编号再提交");
					
				}
			}
			xmQuestionHandleService.insert(xmQuestionHandle);
			
		return Result.ok();  
		
	}
	
	/**
	@ApiOperation( value = "删除一条xm_question_handle信息",notes="delXmQuestionHandle,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmQuestionHandle(@RequestBody XmQuestionHandle xmQuestionHandle){

			xmQuestionHandleService.deleteByPk(xmQuestionHandle);
		return Result.ok();
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条xm_question_handle信息",notes="editXmQuestionHandle")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmQuestionHandle.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmQuestionHandle(@RequestBody XmQuestionHandle xmQuestionHandle) {

			xmQuestionHandleService.updateByPk(xmQuestionHandle);
		
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除xm_question_handle信息",notes="batchDelXmQuestionHandle,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmQuestionHandle(@RequestBody List<XmQuestionHandle> xmQuestionHandles) {
		
		
		
			xmQuestionHandleService.batchDelete(xmQuestionHandles);
		return Result.ok();
		
	} 
	*/
}
