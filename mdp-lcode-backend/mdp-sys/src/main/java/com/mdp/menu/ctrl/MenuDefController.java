package com.mdp.menu.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.menu.entity.MenuDef;
import com.mdp.menu.entity.MenuModule;
import com.mdp.menu.service.MenuDefService;
import com.mdp.qx.HasQx;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对ADMIN.menu_def 前端功能菜单表的操作有增删改查,对应的url分别为:<br>
 *  新增: menu/menuDef/add <br>
 *  查询: menu/menuDef/list<br>
 *  模糊查询: menu/menuDef/listKey<br>
 *  修改: menu/menuDef/edit <br>
 *  删除: menu/menuDef/del<br>
 *  批量删除: menu/menuDef/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 menu 小模块 <br>
 * 实体 MenuDef 表 ADMIN.menu_def 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.menu.menuDefController")
@RequestMapping(value="/*/menu/menuDef")
@Api(tags={"前端功能菜单表操作接口"})
public class MenuDefController {
	
	static Log logger=LogFactory.getLog(MenuDefController.class);
	
	@Autowired
	private MenuDefService menuDefService;

	  
	
	@ApiOperation( value = "查询前端功能菜单表信息列表",notes="listMenuDef,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'},如果是超级管理员，查询所有，如果不是，根据角色查询")
	@ApiResponses({
		@ApiResponse(code = 200,response= MenuDef.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listMenuDef( @ApiIgnore @RequestParam Map<String,Object>  menuDef){
		 
		IPage page=QueryTools.initPage(menuDef);
		User user=LoginUtils.getCurrentUserInfo();
		QueryTools.alias(menuDef,"* res.");
		QueryWrapper<MenuDef> qw= QueryTools.initQueryWrapper(MenuDef.class,menuDef);
		Map<String,Object> ext=new HashMap<>();
		List<Map<String,Object>>	menuDefList = menuDefService.selectListMapByWhere(page,qw,ext);
		
		return Result.ok().setData(menuDefList).setTotal(page.getTotal());
		
		
	}
	@ApiOperation( value = "查询前端功能菜单表信息列表",notes="listMenuDef,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}，如果是超级管理员，查所有，如果不是，按角色查询所有上级")
	@ApiImplicitParams({   
		@ApiImplicitParam(name="deptid",value="部门编号",required=false),
		@ApiImplicitParam(name="branchId",value="机构编号",required=false)
	})
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuDef.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list/tree",method=RequestMethod.GET)
	public Result listMenuTee( @ApiIgnore @RequestParam Map<String,Object>  menuDef){
		IPage page=QueryTools.initPage(menuDef);
		User user=LoginUtils.getCurrentUserInfo();

		QueryTools.alias(menuDef,"* res.");
		QueryWrapper<MenuDef> qw= QueryTools.initQueryWrapper(MenuDef.class,menuDef);
		List<Map<String,Object>>	menuDefList = menuDefService.selectListMapByWhere(page,qw,menuDef);
 		return Result.ok().setData(menuDefList).setTotal(page.getTotal());
	}

	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiResponses({
			@ApiResponse(code = 200,response= MenuModule.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole(roles = {"superAdmin"})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> params) {

			List<String> ids= (List<String>) params.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("ids-0","ids不能为空");
			}
			this.menuDefService.editSomeFields(params);
			return Result.ok();
	}
	
	/***/
	@ApiOperation( value = "新增一条前端功能菜单表信息",notes="addMenuDef,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuDef.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@HasRole(roles= {"superAdmin"})
	@HasQx(value="sys_menu_def_add",name="新增菜单",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addMenuDef(@RequestBody MenuDef menuDef) {
		
		 
		try{
			if(StringUtils.isEmpty(menuDef.getId())) {
				menuDef.setId(menuDefService.createKey("id"));
			}else{
				 MenuDef menuDefQuery = new  MenuDef(menuDef.getId());
				if(menuDefService.countByWhere(menuDefQuery)>0){
					return Result.error("id-exists","编号重复，请修改编号再提交");
				}
			}
			
			menuDefService.insert(menuDef); 
			return Result.ok().setData(menuDef);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
		
	}
	
	
	/***/
	@ApiOperation( value = "删除一条前端功能菜单表信息",notes="delMenuDef,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@HasRole(roles= {"superAdmin"})
	@HasQx(value="sys_menu_def_del",name="删除菜单",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delMenuDef(@RequestBody MenuDef menuDef){
		
		 
		try{
			MenuDef menuDefQ=new MenuDef();
			menuDefQ.setPid(menuDef.getId());
			if(menuDefService.countByWhere(menuDefQ)>0) { 
				return Result.error("had-sub-menus-not-allow-del","该菜单有子菜单，不能直接删除，请先删除子菜单");
			};
			menuDefService.deleteByPk(menuDef);
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
	@ApiOperation( value = "根据主键修改一条前端功能菜单表信息",notes="editMenuDef")
	@ApiResponses({
		@ApiResponse(code = 200,response=MenuDef.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@HasRole(roles= {"superAdmin"})
	@HasQx(value="sys_menu_def_edit",name="修改菜单",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editMenuDef(@RequestBody MenuDef menuDef) {
		 
		try{
			menuDefService.updateByPk(menuDef);
			return Result.ok().setData(menuDef);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}   
	}
	
	

	
	/***/
	@ApiOperation( value = "根据主键列表批量删除前端功能菜单表信息",notes="batchDelMenuDef,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@HasRole(roles= {"superAdmin"})
	@HasQx(value="sys_menu_def_batchDel",name="批量删除菜单",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelMenuDef(@RequestBody List<MenuDef> menuDefs) {
		
		 
		try{ 
			if(menuDefService.countChildrenByIds(menuDefs)>0) {
				return Result.error("had-sub-menus-not-allow-del","该菜单有子菜单，不能直接删除，请先删除子菜单");
			}
			menuDefService.batchDelete(menuDefs);
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
	@ApiOperation( value = "根据主键列表批量删除前端功能菜单表信息",notes="batchDelMenuDef,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@HasRole(roles= {"superAdmin"})
	@HasQx(value="sys_menu_def_batchInsert",name="批量新增菜单",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchInsert",method=RequestMethod.POST)
	public Result batchInsertMenuDef(@RequestBody List<MenuDef> menuDefs) {
		
		
		try{ 

			this.menuDefService.saveOrUpdateBatch(menuDefs);

			return Result.ok();
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	} 
	@ApiOperation( value = "批量保存按钮",notes="batchSaveButton,按钮列表(包括新增修改的按钮)")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@HasRole(roles= {"superAdmin"})
	@HasQx(value="sys_menu_def_batchSaveButton",name="批量调整菜单",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchSaveButton",method=RequestMethod.POST)
	public Result batchSaveButton(@RequestBody List<MenuDef> menuDefs) {
		
		 
		try{ 
			Tips tips=menuDefService.batchSaveButton(menuDefs); 
			Result.assertIsFalse(tips);
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
