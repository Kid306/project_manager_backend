package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.qx.HasQx;
import com.mdp.qx.HasRole;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.Qx;
import com.mdp.sys.service.QxService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * url编制采用rest风格,如对ADMIN.sys_qx 权限定义的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/qx/add <br>
 *  查询: sys/qx/list<br>
 *  模糊查询: sys/qx/listKey<br>
 *  修改: sys/qx/edit <br>
 *  删除: sys/qx/del<br>
 *  批量删除: sys/qx/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Qx 表 ADMIN.sys_qx 当前主键(包括多主键): qx_id; 
 ***/
@RestController("mdp.sys.qxController")
@RequestMapping(value="/*/sys/qx")
@Api(tags={"权限定义操作接口"})
public class QxController {
	
	static Log logger=LogFactory.getLog(QxController.class);
	
	@Autowired
	private QxService qxService;


	
	@ApiOperation( value = "查询权限定义信息列表",notes="listQx,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="qxId",value="权限编号,主键",required=false),
		@ApiImplicitParam(name="qxName",value="权限名称",required=false),
		@ApiImplicitParam(name="qxCode",value="权限编码",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderFields",value="排序列 如性别、学生编号排序 ['sex','studentId']",required=false),
		@ApiImplicitParam(name="orderDirs",value="排序方式,与orderFields对应，升序 asc,降序desc 如 性别 升序、学生编号降序 ['asc','desc']",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= Qx.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listQx( @ApiIgnore @RequestParam Map<String,Object>  params) {
		Map<String, Object> m = new HashMap<>();
		IPage page=QueryTools.initPage(params);
		QueryWrapper<Qx> qw= QueryTools.initQueryWrapper(Qx.class,params);
		List<Map<String, Object>> qxList = new ArrayList<>();
		qxList = qxService.selectListMapByWhere(page,qw,params);
		return Result.ok().setData(qxList).setTotal(page.getTotal());
		
		
	}
	
 
	
	/***/
	@ApiOperation( value = "新增一条权限定义信息",notes="addQx,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=Qx.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = "superAdmin")
	@HasQx(value="sys_qx_add",name="新增权限定义",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addQx(@RequestBody Qx qx) {
		
		 
		try{ 	
				if(StringUtils.isEmpty(qx.getQxId())) {
					return Result.error("qx-id-required","权限编码不能为空");
				}
				Qx qxQuery=new Qx(qx.getQxId());
				if(qxService.countByWhere(qxQuery)>0) {
					return Result.error("qx-id-exists","权限编码已存在");

				}; 
			qxService.insert(qx);
			return Result.ok().setData(qx);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	
	
	/** */
	@ApiOperation( value = "删除一条权限定义信息",notes="delQx,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@HasRole(roles = "superAdmin")
	@HasQx(value="sys_qx_del",name="删除权限定义",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delQx(@RequestBody Qx qx){
		
		 
		try{
			qxService.deleteByPk(qx);
			return Result.ok();
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	
	
	/***/
	@ApiOperation( value = "根据主键修改一条权限定义信息",notes="editQx")
	@ApiResponses({
		@ApiResponse(code = 200,response=Qx.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = "superAdmin")
	@HasQx(value="sys_qx_edit",name="修改权限定义",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editQx(@RequestBody Qx qx) {
		
		 
		try{
			qxService.updateByPk(qx);

			return Result.ok().setData(qx);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	
	

	
	/***/
	@ApiOperation( value = "根据主键列表批量删除权限定义信息",notes="batchDelQx,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@HasRole(roles = "superAdmin")
	@HasQx(value="sys_qx_batchDel",name="批量删除权限定义",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelQx(@RequestBody String[] qxIds) {
		
		
		List<Qx> list=new ArrayList<Qx>();
		try{
			for(int i=0;i<qxIds.length;i++){
				Qx qx=new Qx();
				qx.setQxId(qxIds[i]);
				list.add(qx);
			  }
			qxService.batchDelete(list);
			return Result.ok();
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiEntityParams( value = Qx.class, props={ }, remark = "个人信息", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response= Qx.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {


		try{
			this.qxService.editSomeFields(params);
			return Result.ok();
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}
	/***/
	@ApiOperation( value = "根据主键列表批量删除权限定义信息",notes="batchDelQx,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/autoRegist",method=RequestMethod.POST)
	public Result autoRegist(@RequestBody List<Qx> qxs) {
		
		
		try{
			qxService.autoRegist(qxs);
			return Result.ok();
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}
}
