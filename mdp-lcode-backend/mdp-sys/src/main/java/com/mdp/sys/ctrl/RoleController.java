package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.qx.DataLvl;
import com.mdp.qx.DeptFilter;
import com.mdp.qx.HasQx;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.cache.RoleQxsRedisCacheService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.Role;
import com.mdp.sys.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 * url编制采用rest风格,如对ADMIN.sys_role 角色管理的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/role/add <br>
 *  查询: sys/role/list<br>
 *  模糊查询: sys/role/listKey<br>
 *  修改: sys/role/edit <br>
 *  删除: sys/role/del<br>
 *  批量删除: sys/role/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Role 表 ADMIN.sys_role 当前主键(包括多主键): roleid; 
 ***/
@RestController("mdp.sys.roleController")
@RequestMapping(value="/*/sys/role")
@Api(tags={"角色管理操作接口"})
public class RoleController {
	
	static Log logger=LogFactory.getLog(RoleController.class);

	@Value(value = "${mdp.platform-branch-id:platform-branch-001}")
	String platformBranchId;
	
	@Autowired
	private RoleService roleService;


	@Autowired
	RoleQxsRedisCacheService roleQxsRedisCacheService;
	  
	@ApiOperation( value = "查询角色管理信息列表",notes="listRole,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(value = Role.class)
	@ApiResponses({
		@ApiResponse(code = 200,response= Role.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listRole(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<Role> qw= QueryTools.initQueryWrapper(Role.class,params);
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isSuperAdmin()) {
			qw.in("branch_id", Arrays.asList(platformBranchId,user.getBranchId()));
		}
		List<Map<String,Object>> roleList = roleService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(roleList).setTotal(page.getTotal());
		
	}

	@ApiResponses({
			@ApiResponse(code = 200,response= Role.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getRole",method=RequestMethod.GET)
	public Result getRole( @RequestParam String roleid){
		 return Result.ok("","成功").setData(this.roleService.selectOneById(roleid));
	}

	@ApiOperation( value = "查询不包含在某个岗位的角色列表",notes="listRoleNotInPostId,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(value = Role.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=Role.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list/notInPostId",method=RequestMethod.GET)
	public Result listRoleNotInPostId( @ApiIgnore @RequestParam Map<String,Object> role){
		 
		
		IPage page=QueryTools.initPage(role);
		QueryWrapper<Role> qw= QueryTools.initQueryWrapper(Role.class,role);
		if(!LoginUtils.isSuperAdmin()) {
			String paramBranchId=(String) role.get("branchId"); 
			if( StringUtils.isEmpty(paramBranchId) ) {
				return Result.error("不是超级管理员,也不是平台管理员，请上送机构编号参数branchId");
			}else {
				if(LoginUtils.getCurrentUserInfo().hasBranchId(paramBranchId)) {
					role.put("branchId", paramBranchId);
				}else {
					return Result.error("您不是超级管理员,也不是平台管理员，不允许查询其它机构的数据");
				}
			}
		}
		List<Map<String,Object>>	roleList = roleService.selectListNotInPostId(role);	//列出Role列表
		return Result.ok().setData(roleList).setTotal(page.getTotal());
		
	}
 
	
	/***/
	@ApiOperation( value = "新增一条角色管理信息",notes="addRole,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=Role.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@DeptFilter()
	@HasQx(value="sys_role_add",name="新增角色",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addRole(@RequestBody Role role) {
		
		
		try{
			if(StringUtils.isEmpty(role.getRoleid())) {
				role.setRoleid(roleService.createKey("roleid"));
			} else {  
				Role roleQuery=new Role(role.getRoleid());
				if(roleService.countByWhere(roleQuery)>0) { 
					return Result.error("role-id-exists","角色编码已存在");
				}; 
			}
			User user=LoginUtils.getCurrentUserInfo();

			boolean isSuperAdmin=LoginUtils.isSuperAdmin();
			if(!isSuperAdmin && !LoginUtils.isBranchAdmin(role.getBranchId())){
				return Result.error("need-branch-admin","您不是机构管理员，无权增加角色。");
			}
			if("1".equals(role.getRoletype()) && !isSuperAdmin){
				return Result.error("no-qx-add-pub","无权增加公共角色");
			}
			if(role.getDataLvl()!=null && role.getDataLvl()>LoginUtils.getMaxDataLvl().getLvl()){
				return Result.error("data-lvl","数据权限级别不能高于您当前拥有的数据权限级别");
			}
			if("1".equalsIgnoreCase(role.getRoletype())){
				role.setBranchId(platformBranchId);
			}else{
				role.setBranchId(user.getBranchId());
			}

				roleService.insert(role);
			return Result.ok().setData(role);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	
	
	/***/
	@ApiOperation( value = "删除一条角色管理信息",notes="delRole,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@DeptFilter(min=DataLvl.branch)

	@HasQx(value="sys_role_del",name="删除角色",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delRole(@RequestBody Role role){
		
		 
		try{
			User user=LoginUtils.getCurrentUserInfo();
			boolean isSuperAdmin=LoginUtils.isSuperAdmin();
			Tips tips = this.roleService.roleOpCheck(user,isSuperAdmin,role.getRoleid(),null,null);
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
	 
	
	/***/
	@ApiOperation( value = "根据主键修改一条角色管理信息",notes="editRole")
	@ApiResponses({
		@ApiResponse(code = 200,response=Role.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@DeptFilter()
	@HasRole(roles = {"superAdmin","branchAdmin"})
	@HasQx(value="sys_role_edit",name="修改角色",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editRole(@RequestBody Role role) {
		
		 
		try{
			User user=LoginUtils.getCurrentUserInfo();
			boolean isSuperAdmin=LoginUtils.isSuperAdmin();
			Tips tips = this.roleService.roleOpCheck(user,isSuperAdmin,role.getRoleid(),role.getDataLvl(),role.getRoletype());
			Result.assertIsFalse(tips);  
			roleService.updateByPk(role); 
			return Result.ok().setData(role); 
			
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}


	/***/
	@ApiOperation( value = "批量修改某些几个字段",notes="editSomeFields")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})

	@HasQx(value="sys_role_editSomeFields",name="批量修改某些几个字段",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields(@RequestBody Map<String,Object> params) {


		List<String> pks= (List<String>) params.get("$pks");
		try{
			User user=LoginUtils.getCurrentUserInfo();
			int i=0;
			boolean isSuperAdmin = LoginUtils.isSuperAdmin();
			Tips tips=null;
			List<Role> list=roleService.listByIds(pks);
			for (Role role : list) {
				tips = this.roleService.roleOpCheck(user,isSuperAdmin,role.getRoleid(),null,null);
				Result.assertIsFalse(tips);
			}

			roleService.editSomeFields(params);
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
	@ApiOperation( value = "根据主键列表批量删除角色管理信息",notes="batchDelRole,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 

	@HasQx(value="sys_role_batchDel",name="批量删除角色",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelRole(@RequestBody List<Role> roles) {
		
		 
		List<Role> list=roles;
		try{ 
			User user=LoginUtils.getCurrentUserInfo();
			String[] roleids =new String[roles.size()];
			int i=0;
			boolean isSuperAdmin = LoginUtils.isSuperAdmin();
			Tips tips=null;
			list=roleService.listByIds(roles);
			for (Role role : list) {
				  tips = this.roleService.roleOpCheck(user,isSuperAdmin,role.getRoleid(),null,null);
				  Result.assertIsFalse(tips);
			}
			  
			roleService.batchDelete(list); 
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
