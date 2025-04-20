package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.menu.service.MenuModuleBranchService;
import com.mdp.qx.DeptFilter;
import com.mdp.qx.HasQx;
import com.mdp.safe.client.cache.DeptRedisCacheService;
import com.mdp.safe.client.entity.Dept;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.DeptPostUser;
import com.mdp.sys.entity.UserDept;
import com.mdp.sys.entity.UserDeptVo;
import com.mdp.sys.service.DeptPostService;
import com.mdp.sys.service.DeptPostUserService;
import com.mdp.sys.service.UserDeptService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对ADMIN.sys_user_dept 用户部门关系表的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/userDept/add <br>
 *  查询: sys/userDept/list<br>
 *  模糊查询: sys/userDept/listKey<br>
 *  修改: sys/userDept/edit <br>
 *  删除: sys/userDept/del<br>
 *  批量删除: sys/userDept/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserDept 表 ADMIN.sys_user_dept 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.userDeptController")
@RequestMapping(value="/*/sys/userDept")
@Api(tags={"用户部门关系表操作接口"})
public class UserDeptController {
	
	static Log logger=LogFactory.getLog(UserDeptController.class);
	
	@Autowired
	private UserDeptService userDeptService;
	
	@Autowired
	private DeptPostUserService deptPostUserService;
	
	@Autowired
	DeptPostService deptPostService;

	@Autowired
	MenuModuleBranchService menuModuleBranchService;

	@Autowired
	DeptRedisCacheService deptRedisCacheService;
		 
	
	@ApiOperation( value = "查询用户部门关系表信息列表",notes="listUserDept,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(UserDept.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=UserDept.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserDept(@ApiIgnore @RequestParam Map<String,Object> userDept){

		IPage page=QueryTools.initPage(userDept);
		QueryTools.alias(userDept,"deptid res.deptid","userid res.userid");
 		QueryWrapper<UserDept> qw= QueryTools.initQueryWrapper(UserDept.class,userDept);

		List<Map<String,Object>>	userDeptList = userDeptService.selectListMapByWhere(page,qw,userDept);
		
		return Result.ok().setData(userDeptList).setTotal(page.getTotal());
		
		
	}
	
 
	
	/***/
	@ApiOperation( value = "新增一条用户部门关系表信息",notes="addUserDept,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserDept.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})  
	@DeptFilter()
	@HasQx(value="sys_user_dept_add",name="将用户分配到部门",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserDept(@RequestBody UserDept userDept) {
			User user=LoginUtils.getCurrentUserInfo();
			if(!LoginUtils.isBranchAdmin(user.getBranchId())){
				return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
			}
			Dept dept=deptRedisCacheService.getDept(userDept.getDeptid());
			if(dept==null){
				return Result.error("dept-0","部门不存在");
			}
			if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(dept.getBranchId())){
				if(!LoginUtils.checkIsMyDeptScope(userDept.getDeptid())){
					return Result.error("dept-lvl-0","您无权限调整该部门数据");
				}
			}
			userDeptService.insert(userDept);
			return Result.ok().setData(userDept);
	}
	
	
	/***/
	@ApiOperation( value = "删除一条用户部门关系表信息",notes="delUserDept,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@DeptFilter()
	@HasQx(value="sys_user_dept_del",name="将用户移出部门",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserDept(@RequestBody UserDept userDept){
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
		}
			Dept dept=deptRedisCacheService.getDept(userDept.getDeptid());
			if(dept==null){
				return Result.error("dept-0","部门不存在");
			}
			if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(dept.getBranchId())){
				if(!LoginUtils.checkIsMyDeptScope(userDept.getDeptid())){
					return Result.error("dept-lvl-0","您无权限删除该部门数据");
				}
			}
 			userDeptService.deleteByPk(userDept);
			return Result.ok();
	}
	 
	
	/***/
	@ApiOperation( value = "根据主键修改一条用户部门关系表信息",notes="editUserDept")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserDept.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@DeptFilter()
	@HasQx(value="sys_user_dept_edit",name="调整用户归属部门",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUserDept(@RequestBody UserDept userDept) {
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
		}
			Dept dept=deptRedisCacheService.getDept(userDept.getDeptid());
			if(dept==null){
				return Result.error("dept-0","部门不存在");
			}
			if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(dept.getBranchId())){
				if(!LoginUtils.checkIsMyDeptScope(userDept.getDeptid())){
					return Result.error("dept-lvl-0","您无权限修改该部门数据");
				}
			}
			userDeptService.updateByPk(userDept);
			return Result.ok().setData(userDept);
	}
	
	/**
	 * 批量更新某个用户的角色
	 */

	@HasQx(value="sys_user_dept_batchEdit",name="批量调整用户归属部门",moduleId = "mdp-sys",moduleName = "组织管理")
	@DeptFilter(onlySameBranchCheck = true)
	@RequestMapping(value="/batchEdit",method=RequestMethod.POST)
	public Result batchEdit(@RequestBody UserDeptVo userDeptVo) {
		
		
		if(StringUtils.isEmpty(userDeptVo.getBranchId())) {
			return Result.error("机构编号不能为空");
		}
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId()) || !LoginUtils.isBranchAdmin(userDeptVo.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
		}
			Tips tips=userDeptService.batchEdit(userDeptVo);
			Result.assertIsFalse(tips);
			return Result.ok();
	}  
	/**
	 * 
	 * @cdate 2020/2/14 15:25
	 * @author LinYuKun
	 * @param userDeptVo
	 * @return
	 */
	@RequestMapping(value="/batchEditNoauth",method=RequestMethod.POST)
	public Result batchEditNoauth(@RequestBody UserDeptVo userDeptVo) {
		return this.batchEdit(userDeptVo);
	}
	
	/***/
	@ApiOperation( value = "根据主键列表批量删除用户部门关系表信息",notes="batchDelUserDept,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@DeptFilter()
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUserDept(@RequestBody List<UserDept> userDepts) {
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
		}
		userDeptService.batchDeleteWithRelate(userDepts);
			return Result.ok();
	}
	@ApiOperation( value = "批量拉人进入部门",notes="batchAdd")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@RequestMapping(value="/batchAdd",method=RequestMethod.POST)
	public Result batchAdd(@RequestBody List<UserDept> userDepts) {
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
		}
		for (UserDept userDept : userDepts) {

			if(ObjectTools.isEmpty(userDept.getDeptid())){
				return Result.error("deptid-required","部门编号不能为空");
			}
			if(ObjectTools.isEmpty(userDept.getUserid())){
				return Result.error("userid-required","用户编号不能为空");
			}
			Dept dept=deptRedisCacheService.getDept(userDept.getDeptid());
			if(dept==null){
				return Result.error("dept-0","部门不存在");
			}
			if(!LoginUtils.isBranchAdmin(dept.getBranchId())){
				return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
			}
			userDept.setEnabled("0");
		}
		userDeptService.saveBatch(userDepts);
		return Result.ok();
	}
}
