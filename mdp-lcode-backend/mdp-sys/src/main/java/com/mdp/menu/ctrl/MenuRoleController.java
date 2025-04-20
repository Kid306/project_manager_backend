package com.mdp.menu.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.menu.entity.MenuRole;
import com.mdp.menu.entity.MenuToRolesVo;
import com.mdp.menu.entity.MenusToRoleVo;
import com.mdp.menu.entity.MenusToRolesVo;
import com.mdp.menu.service.MenuRoleService;
import com.mdp.safe.client.cache.RoleMenusRedisCacheService;
import com.mdp.safe.client.cache.RoleRedisCacheService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
/**
 * url编制采用rest风格,如对ADMIN.menu_role 菜单角色分配的操作有增删改查,对应的url分别为:<br>
 *  新增: menu/menuRole/add <br>
 *  查询: menu/menuRole/list<br>
 *  模糊查询: menu/menuRole/listKey<br>
 *  修改: menu/menuRole/edit <br>
 *  删除: menu/menuRole/del<br>
 *  批量删除: menu/menuRole/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 menu 小模块 <br>
 * 实体 MenuRole 表 ADMIN.menu_role 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.menu.menuRoleController")
@RequestMapping(value="/*/menu/menuRole")
@Api(tags={"菜单角色分配操作接口"})
public class MenuRoleController {
	
	static Log logger=LogFactory.getLog(MenuRoleController.class);
	
	@Autowired
	private MenuRoleService menuRoleService;
	@Autowired
	RoleMenusRedisCacheService roleMenusRedisCacheService;

	@Autowired
	RoleRedisCacheService roleRedisCacheService;
	 
	@ApiOperation( value = "查询菜单角色分配信息列表",notes="listMenuRole,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({  
		@ApiImplicitParam(name="id",value="编号,主键",required=false),
		@ApiImplicitParam(name="roleid",value="用户组编号",required=false),
		@ApiImplicitParam(name="mid",value="菜单编号",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderFields",value="排序列 如性别、学生编号排序 ['sex','studentId']",required=false),
		@ApiImplicitParam(name="orderDirs",value="排序方式,与orderFields对应，升序 asc,降序desc 如 性别 升序、学生编号降序 ['asc','desc']",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= MenuRole.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listMenuRole( @ApiIgnore @RequestParam Map<String,Object>  menuRole){
		 
		IPage page=QueryTools.initPage(menuRole);
		QueryWrapper<MenuRole> qw= QueryTools.initQueryWrapper(MenuRole.class,menuRole);
		List<Map<String,Object>>	menuRoleList = menuRoleService.selectListMapByWhere(page,qw,menuRole);
		return Result.ok().setData(menuRoleList).setTotal(page.getTotal());
		
		
	}
	
	@ApiOperation( value = "批量分配一个菜单给多个角色",notes="batchEditMenuToRoles,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 

	//@HasQx(value="sys_menu_role_batchEditMenuToRoles",name="批量分配一个菜单给多个角色",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchEditMenuToRoles",method=RequestMethod.POST)
	public Result batchEditMenuToRoles(@RequestBody MenuToRolesVo menuVo) {
		
		 
		try{
			Tips tips=menuRoleService.batchEditMenuToRoles(menuVo);
			Result.assertIsFalse(tips);
			roleMenusRedisCacheService.clearRoleMenus( menuVo.getRoleids().toArray(new String[menuVo.getRoleids().size()]));
			 return Result.ok();
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "批量分配多个菜单到多个角色",notes="batchEditMenusToRoles,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})

	//@HasQx(value="sys_menu_role_batchEditMenusToRoles",name="批量分配多个菜单到多个角色",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchEditMenusToRoles",method=RequestMethod.POST)
	public Result batchEditMenusToRoles(@RequestBody MenusToRolesVo menuVo) {
		
		
		try{
			if(menuVo.getMids()==null ||menuVo.getMids().size()==0){
				return Result.error("mids不能为空","请选中菜单");
			}
			if(menuVo.getRoleids()==null ||menuVo.getRoleids().size()==0){
				return Result.error("roleids不能为空","请选中角色");
			}
			Tips tips=menuRoleService.batchEditMenusToRoles(menuVo);
			Result.assertIsFalse(tips);
			roleMenusRedisCacheService.clearRoleMenus(menuVo.getRoleids().toArray(new String[menuVo.getRoleids().size()]));
			return Result.ok();
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}

	@ApiOperation( value = "批量分配多个菜单给到一个角色",notes="batchEditMenusToRole,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 

	//@HasQx(value="sys_menu_role_batchEditMenusToRole",name="批量分配多个菜单给到一个角色",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchEditMenusToRole",method=RequestMethod.POST)
	public Result batchEditMenusToRole(@RequestBody MenusToRoleVo menuVo) {

		try{
			Tips tips=menuRoleService.batchEditMenusToRole(menuVo);
			Result.assertIsFalse(tips);
			roleMenusRedisCacheService.clearRoleMenus(menuVo.getRoleid());
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
	@ApiOperation( value = "新增一条菜单角色分配信息",notes="addMenuRole,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuRole.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addMenuRole(@RequestBody MenuRole menuRole) {
		
		
		try{
			if(StringUtils.isEmpty(menuRole.getId())) {
				menuRole.setId(menuRoleService.createKey("id"));
			}else{
				 MenuRole menuRoleQuery = new  MenuRole(menuRole.getId());
				if(menuRoleService.countByWhere(menuRoleQuery)>0){
					return Result.error("id-exists","编号重复，请修改编号再提交");
				}
			}
			menuRoleService.insert(menuRole);
			return Result.ok().setData(menuRole);
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
	@ApiOperation( value = "删除一条菜单角色分配信息",notes="delMenuRole,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delMenuRole(@RequestBody MenuRole menuRole){
		
		
		try{
			menuRoleService.deleteByPk(menuRole);
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
	@ApiOperation( value = "根据主键修改一条菜单角色分配信息",notes="editMenuRole")
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuRole.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editMenuRole(@RequestBody MenuRole menuRole) {
		
		
		try{
			menuRoleService.updateByPk(menuRole);
			return Result.ok().setData(menuRole);
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
	@ApiOperation( value = "根据主键列表批量删除菜单角色分配信息",notes="batchDelMenuRole,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelMenuRole(@RequestBody List<MenuRole> menuRoles) {
		
		 
		try{ 
			menuRoleService.batchDelete(menuRoles);
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
