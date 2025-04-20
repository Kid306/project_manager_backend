package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.qx.HasQx;
import com.mdp.qx.HasRole;
import com.mdp.sys.entity.UserRole;
import com.mdp.sys.entity.UserRoleVo;
import com.mdp.sys.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
/**
 * url编制采用rest风格,如对ADMIN.sys_user_role 用户角色表的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/userRole/add <br>
 *  查询: sys/userRole/list<br>
 *  模糊查询: sys/userRole/listKey<br>
 *  修改: sys/userRole/edit <br>
 *  删除: sys/userRole/del<br>
 *  批量删除: sys/userRole/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserRole 表 ADMIN.sys_user_role 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.userRoleController")
@RequestMapping(value="/*/sys/userRole")
@Api(tags={"用户角色表操作接口"})
public class UserRoleController {
	
	static Log logger=LogFactory.getLog(UserRoleController.class);
	
	@Autowired
	private UserRoleService userRoleService;
	 
		 
	/**
	 * 批量更新某个用户的角色
	 */
	@HasRole
	@HasQx(value="sys_user_role_batchEdit",name="调整用户的角色",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchEdit")
	public Result batchEdit(@RequestBody UserRoleVo userRoleVo) {
		
		 
		try{  
			userRoleService.batchEdit(userRoleVo);
			return Result.ok();
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	} 
	
	@ApiOperation( value = "查询用户角色表信息列表",notes="listUserRole,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")

	@ApiResponses({
		@ApiResponse(code = 200,response= UserRole.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserRole( @ApiIgnore @RequestParam Map<String,Object>  params){
		 
		IPage page=QueryTools.initPage(params);
 		QueryWrapper<UserRole> qw= QueryTools.initQueryWrapper(UserRole.class,params);
		List<Map<String,Object>>	userRoleList = userRoleService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(userRoleList).setTotal(page.getTotal());

	}
	
	/**
	 * 请求,如list
	 * 分页参数 {pageNum:1,pageSize:10,total:0}
	 * 根据条件查询数据对象列表,如果不上传分页参数，将不会分页。后台自动分页，无需编程
	 */
	@HasRole
	//@HasQx(value="sys_user_role_add",name="将用户分配到部门",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/list/users")
	public Result listUsers( Map<String,Object> userRole){
		 
		List<UserRole>	userRoleList = userRoleService.listUsers( userRole);	//列出UserRole列表
		return Result.ok().setData(userRoleList);
		
		
	}

}
