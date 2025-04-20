package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.sys.entity.DeptLocation;
import com.mdp.sys.service.DeptLocationService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
/**
 * url编制采用rest风格,如对ADMIN.sys_dept_location sys_dept_location的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/deptLocation/add <br>
 *  查询: sys/deptLocation/list<br>
 *  模糊查询: sys/deptLocation/listKey<br>
 *  修改: sys/deptLocation/edit <br>
 *  删除: sys/deptLocation/del<br>
 *  批量删除: sys/deptLocation/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 DeptLocation 表 ADMIN.sys_dept_location 当前主键(包括多主键): deptid; 
 ***/
@RestController("mdp.sys.deptLocationController")
@RequestMapping(value="/*/sys/deptLocation")
@Api(tags={"sys_dept_location操作接口"})
public class DeptLocationController {
	
	static Log logger=LogFactory.getLog(DeptLocationController.class);
	
	@Autowired
	private DeptLocationService deptLocationService;
	 
		
	 
	
	@ApiOperation( value = "查询sys_dept_location信息列表",notes="listDeptLocation,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="deptid",value="部门地址编号,主键",required=false),
		@ApiImplicitParam(name="longitude",value="经度",required=false),
		@ApiImplicitParam(name="latitude",value="纬度",required=false),
		@ApiImplicitParam(name="address",value="地址",required=false),
		@ApiImplicitParam(name="province",value="省",required=false),
		@ApiImplicitParam(name="city",value="市",required=false),
		@ApiImplicitParam(name="district",value="区",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderFields",value="排序列 如性别、学生编号排序 ['sex','studentId']",required=false),
		@ApiImplicitParam(name="orderDirs",value="排序方式,与orderFields对应，升序 asc,降序desc 如 性别 升序、学生编号降序 ['asc','desc']",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= DeptLocation.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listDeptLocation( @ApiIgnore @RequestParam Map<String,Object>  params){
		 
		IPage page=QueryTools.initPage(params);
		QueryWrapper<DeptLocation> qw= QueryTools.initQueryWrapper(DeptLocation.class,params);
		List<Map<String,Object>>	deptLocationList = deptLocationService.selectListMapByWhere(page,qw,params);
		return Result.ok().setData(deptLocationList);
		
		
	}
	@RequestMapping(value="/getDeptLocation",method=RequestMethod.POST)
	public Result getDeptLocation( @RequestBody Map<String,Object> map){
		
		DeptLocation deptLocation = deptLocationService.getDeptLocation(map);//检查id是否为空或者null
 		return Result.ok().put("deptLocation",deptLocation);
		
		
	}
	
	@RequestMapping(value="/addAndEditDeptLocation",method=RequestMethod.POST)
	public Result addAndEditDeptLocation(@RequestBody Map<String,Object> deptLocation) {
		
		
		try{
			deptLocationService.addAndEditDeptLocation(deptLocation);
			 return Result.ok().setData(deptLocation);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	
	/**
	@ApiOperation( value = "新增一条sys_dept_location信息",notes="addDeptLocation,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=DeptLocation.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addDeptLocation(@RequestBody DeptLocation deptLocation) {
		
		
		try{
			if(StringUtils.isEmpty(deptLocation.getDeptid())) {
				deptLocation.setDeptid(deptLocationService.createKey("deptid"));
			} 
			deptLocationService.insert(deptLocation);
			return Result.ok().setData(deptLocation);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	*/
	
	/**
	@ApiOperation( value = "删除一条sys_dept_location信息",notes="delDeptLocation,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	*/
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delDeptLocation(@RequestBody DeptLocation deptLocation){
		
		
		try{
			deptLocationService.deleteByPk(deptLocation);
			return Result.ok();
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	 
	
	/**
	@ApiOperation( value = "根据主键修改一条sys_dept_location信息",notes="editDeptLocation")
	@ApiResponses({
		@ApiResponse(code = 200,response=DeptLocation.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editDeptLocation(@RequestBody DeptLocation deptLocation) {
		
		
		try{
			deptLocationService.updateByPk(deptLocation);
			return Result.ok().setData(deptLocation);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	*/
	

	
	/**
	@ApiOperation( value = "根据主键列表批量删除sys_dept_location信息",notes="batchDelDeptLocation,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelDeptLocation(@RequestBody List<DeptLocation> deptLocations) {
		
		 
		try{ 
			deptLocationService.batchDelete(deptLocations);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	} 
	*/
}
