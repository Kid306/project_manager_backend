package com.xm.core.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.utils.LoginUtils;
import com.xm.core.entity.XmRecord;
import com.xm.core.service.XmRecordService;
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
 * url编制采用rest风格,如对XM.xm_record xm_record的操作有增删改查,对应的url分别为:<br>
 *  新增: xm/xmRecord/add <br>
 *  查询: xm/xmRecord/list<br>
 *  模糊查询: xm/xmRecord/listKey<br>
 *  修改: xm/xmRecord/edit <br>
 *  删除: xm/xmRecord/del<br>
 *  批量删除: xm/xmRecord/batchDel<br>
 * 组织 com.qqkj  顶级模块 oa 大模块 xm 小模块 <br>
 * 实体 XmRecord 表 XM.xm_record 当前主键(包括多主键): id; 
 ***/
@RestController("xm.core.xmRecordController")
@RequestMapping(value="/xm/core/xmRecord")
@Api(tags={"xm_record操作接口"})
public class XmRecordController {
	
	static Log logger=LogFactory.getLog(XmRecordController.class);
	
	@Autowired
	private XmRecordService xmRecordService;
	 
		
 
	
	@ApiOperation( value = "查询xm_record信息列表",notes="listXmRecord,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="id",value="日志编号,主键",required=false),
		@ApiImplicitParam(name="projectId",value="项目编号",required=false),
		@ApiImplicitParam(name="operUserid",value="操作人id",required=false),
		@ApiImplicitParam(name="operUsername",value="操作人名字",required=false),
		@ApiImplicitParam(name="operTime",value="操作时间",required=false),
		@ApiImplicitParam(name="objType",value="操作对象project/task",required=false),
		@ApiImplicitParam(name="action",value="操作的id",required=false),
		@ApiImplicitParam(name="oldValue",value="历史值",required=false),
		@ApiImplicitParam(name="newValue",value="新值",required=false),
		@ApiImplicitParam(name="remarks",value="备注",required=false),
		@ApiImplicitParam(name="taskId",value="任务编号",required=false),
		@ApiImplicitParam(name="reqNo",value="请求编号，用于跟踪日志",required=false),
		@ApiImplicitParam(name="branchId",value="机构编号",required=false),
		@ApiImplicitParam(name="ip",value="ip地址",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student_id desc",required=false),
		@ApiImplicitParam(name="count",value="是否进行总条数计算,count=true|false",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= XmRecord.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listXmRecord(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		RequestUtils.transformArray(params, "ids");		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<XmRecord> qw = QueryTools.initQueryWrapper(XmRecord.class , params);
		qw.eq("branch_id", LoginUtils.getCurrentUserInfo().getBranchId());
		List<Map<String,Object>> datas = xmRecordService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());	//列出XmRecord列表
		
	}
	
 
	
	/**
	@ApiOperation( value = "新增一条xm_record信息",notes="addXmRecord,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmRecord.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addXmRecord(@RequestBody XmRecord xmRecord) {

			if(StringUtils.isEmpty(xmRecord.getId())) {
				xmRecord.setId(xmRecordService.createKey("id"));
			}else{
				 XmRecord xmRecordQuery = new  XmRecord(xmRecord.getId());
				if(xmRecordService.countByWhere(xmRecordQuery)>0){
					return Result.error("编号重复，请修改编号再提交");
					
				}
			}
			xmRecordService.insert(xmRecord);
		
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条xm_record信息",notes="delXmRecord,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delXmRecord(@RequestBody XmRecord xmRecord){

			xmRecordService.deleteByPk(xmRecord);
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		
	}
	 */
	
	/**
	@ApiOperation( value = "根据主键修改一条xm_record信息",notes="editXmRecord")
	@ApiResponses({
		@ApiResponse(code = 200,response=XmRecord.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editXmRecord(@RequestBody XmRecord xmRecord) {

			xmRecordService.updateByPk(xmRecord);
		
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除xm_record信息",notes="batchDelXmRecord,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelXmRecord(@RequestBody List<XmRecord> xmRecords) {
		
		
		
			xmRecordService.batchDelete(xmRecords);
		return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		
	} 
	*/
}
