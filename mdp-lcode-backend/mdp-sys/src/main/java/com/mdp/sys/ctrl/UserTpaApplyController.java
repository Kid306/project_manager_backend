package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.UserTpaApply;
import com.mdp.sys.service.UserTpaApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
/**
 * url编制采用rest风格,如对ADM.sys_user_tpa_apply sys_user_tpa_apply的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/sysUserTpaApply/add <br>
 *  查询: sys/sysUserTpaApply/list<br>
 *  模糊查询: sys/sysUserTpaApply/listKey<br>
 *  修改: sys/sysUserTpaApply/edit <br>
 *  删除: sys/sysUserTpaApply/del<br>
 *  批量删除: sys/sysUserTpaApply/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 SysUserTpaApply 表 ADM.sys_user_tpa_apply 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.userTpaApplyController")
@RequestMapping(value="/*/sys/userTpaApply")
@Api(tags={"sys_user_tpa_apply操作接口"})
public class UserTpaApplyController {
	
	static Log logger=LogFactory.getLog(UserTpaApplyController.class);
	    
	@Autowired
	private UserTpaApplyService userTpaApplyService;
	 
		
 
	
	@ApiOperation( value = "查询sys_user_tpa_apply信息列表",notes="listSysUserTpaApply,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(UserTpaApply.class)
	@ApiResponses({
		@ApiResponse(code = 200,response= UserTpaApply.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listSysUserTpaApply( @ApiIgnore @RequestParam Map<String,Object>  params){
		 
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<UserTpaApply> qw= QueryTools.initQueryWrapper(UserTpaApply.class,params);
		List<Map<String,Object>>	sysUserTpaApplyList = userTpaApplyService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(sysUserTpaApplyList).setTotal(page.getTotal());
		
	}
	
 
	
	
	@ApiOperation( value = "新增一条sys_user_tpa_apply信息",notes="addSysUserTpaApply,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserTpaApply.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addSysUserTpaApply(@RequestBody UserTpaApply sysUserTpaApply) {
		
		
		try{
			if(StringUtils.isEmpty(sysUserTpaApply.getId())) {
				sysUserTpaApply.setId(userTpaApplyService.createKey("id"));
			}else{
				UserTpaApply sysUserTpaApplyQuery = new  UserTpaApply(sysUserTpaApply.getId());
				if(userTpaApplyService.countByWhere(sysUserTpaApplyQuery)>0){
					return Result.error("id-exists","编号重复，请修改编号再提交");
				}
			}
			userTpaApplyService.insert(sysUserTpaApply);
			return Result.ok().setData(sysUserTpaApply);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	
	
	/**
	@ApiOperation( value = "删除一条sys_user_tpa_apply信息",notes="delSysUserTpaApply,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delSysUserTpaApply(@RequestBody SysUserTpaApply sysUserTpaApply){
		
		
		try{
			userTpaApplyService.deleteByPk(sysUserTpaApply);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	 */
	
	
	@ApiOperation( value = "根据主键修改一条sys_user_tpa_apply信息",notes="editSysUserTpaApply")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserTpaApply.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editSysUserTpaApply(@RequestBody UserTpaApply sysUserTpaApply) {
		
		
		try{
			userTpaApplyService.updateByPk(sysUserTpaApply);
			return Result.ok().setData(sysUserTpaApply);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	
	

	
	
	@ApiOperation( value = "根据主键列表批量删除sys_user_tpa_apply信息",notes="batchDelSysUserTpaApply,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelSysUserTpaApply(@RequestBody List<UserTpaApply> sysUserTpaApplys) {
		
		 
		try{ 
			userTpaApplyService.batchDelete(sysUserTpaApplys);
			return Result.ok();
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}
	
	@RequestMapping(value="/addNew",method=RequestMethod.POST)
	public Result addSysUserTpaApply(@RequestBody Map<String,Object> params) {
		
		
		UserTpaApply result=null;
		try{
			result=userTpaApplyService.addNew(params);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
		return Result.ok().setData( result);
	}
	
}
