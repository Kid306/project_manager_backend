package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.qx.DeptFilter;
import com.mdp.qx.HasQx;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.DeptPostUser;
import com.mdp.sys.entity.UserDept;
import com.mdp.sys.service.DeptPostService;
import com.mdp.sys.service.DeptPostUserService;
import com.mdp.sys.service.DeptService;
import com.mdp.sys.service.UserDeptService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.map;

/**
 * url编制采用rest风格,如对ADMIN.sys_dept_post_user 部门岗位用户关系表（根据部门岗位关系,岗位用户关系自动冗余）的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/post/deptPostUser/add <br>
 *  查询: sys/post/deptPostUser/list<br>
 *  模糊查询: sys/post/deptPostUser/listKey<br>
 *  修改: sys/post/deptPostUser/edit <br>
 *  删除: sys/post/deptPostUser/del<br>
 *  批量删除: sys/post/deptPostUser/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 post<br>
 * 实体 DeptPostUser 表 ADMIN.sys_dept_post_user 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.post.deptPostUserController")
@RequestMapping(value="/*/sys/deptPostUser")
@Api(tags={"部门岗位用户关系表（根据部门岗位关系,岗位用户关系自动冗余）操作接口"})
public class DeptPostUserController {
	
	static Log logger=LogFactory.getLog(DeptPostUserController.class);
	
	@Autowired
	private DeptPostUserService deptPostUserService;
	
	@Autowired
	private DeptService deptService;
	 

	@Autowired
	private UserDeptService userDeptService;
		
	@Autowired
	private DeptPostService deptPostService;
	
	@ApiOperation( value = "查询部门岗位用户关系表（根据部门岗位关系,岗位用户关系自动冗余）信息列表",notes="listDeptPostUser,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiResponses({
		@ApiResponse(code = 200,response=DeptPostUser.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listDeptPostUser( @ApiIgnore @RequestParam Map<String,Object> deptPostUser){
		 
		
		IPage page=QueryTools.initPage(deptPostUser);
		User user = LoginUtils.getCurrentUserInfo();
		QueryWrapper<DeptPostUser> qw= QueryTools.initQueryWrapper(DeptPostUser.class,deptPostUser);
 		List<Map<String,Object>>	deptPostUserList = deptPostUserService.selectListMapByWhere(page,qw,deptPostUser);

		return Result.ok().setData(deptPostUserList).setTotal(page.getTotal());
		
	}
	
	@ApiOperation( value = "通过用户编号及部门编号列表查询部门岗位用户关系列表及部门岗位关系列表",notes="listDeptPostUserWithDeptPosts,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({   
		@ApiImplicitParam(name="userid",value="用户编号",required=true), 
		@ApiImplicitParam(name="deptids",value="部门编号列表",required=true),      
	})
	@ApiResponses({
		@ApiResponse(code = 200,response=DeptPostUser.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},deptPostUsers:[{deptid:'',postId:'',userid:''}],deptPosts:[{deptid:'',postId:''}]")
	})
	@RequestMapping(value="/list/withDeptPosts",method=RequestMethod.GET)
	public Result listDeptPostUserWithDeptPosts( @RequestParam Map<String,Object> deptPostUser,@RequestParam(name="pageNum", defaultValue="1") Integer pageNum, @RequestParam(name="pageSize", defaultValue="1000") Integer pageSize){
		User user = LoginUtils.getCurrentUserInfo();
		deptPostUser.put("branchId",user.getBranchId());
		RequestUtils.transformArray(deptPostUser,"deptid");
		String[] deptids=(String[]) deptPostUser.get("deptid");
		String userid=(String) deptPostUser.get("userid");
		if(deptids==null || deptids.length==0) {
			return Result.error("listDeptPostUserWithDeptPosts01", "部门编号列表不能为空");
		}else if(StringUtils.isEmpty(userid)){
			return Result.error("listDeptPostUserWithDeptPosts02", "用户编号不能为空");
		}else {
			IPage page=QueryTools.initPage(deptPostUser);
			QueryWrapper<DeptPostUser> qw=QueryTools.initQueryWrapper(DeptPostUser.class,deptPostUser);
			List<Map<String,Object>>	deptPostUserList = deptPostUserService.selectListMapByWhere(page,qw,deptPostUser);
			List<Map<String,Object>> deptPosts=deptPostService.selectListMapByDeptids(deptids);
			return Result.ok().put("deptPostUsers",deptPostUserList).put("deptPosts",deptPosts);
		}

	}
	@ApiOperation( value = "查询用户拥有的菜单列表",notes="listDeptPostUserMenus,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({   
		@ApiImplicitParam(name="userid",value="用户编号",required=true), 
		@ApiImplicitParam(name="deptids",value="部门编号列表",required=true),      
	})
	@ApiResponses({
		@ApiResponse(code = 200,response=DeptPostUser.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},deptPostUsers:[{deptid:'',postId:'',userid:''}],deptPosts:[{deptid:'',postId:''}]")
	})
	@RequestMapping(value="/list/menus",method=RequestMethod.GET)
	public Result listDeptPostUserMenus(@RequestParam Map<String,Object> deptPostUser){
		 

		User user = LoginUtils.getCurrentUserInfo();
		deptPostUser.put("branchId",user.getBranchId());
		List<Map<String,Object>>  deptPostUserMenus=this.deptPostUserService.listDeptPostUserMenus(deptPostUser);
		
		return Result.ok().setData( deptPostUserMenus);
	}
	
	@ApiOperation( value = "查询用户拥有的角色列表",notes="listDeptPostUserRoles,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({   
		@ApiImplicitParam(name="userid",value="用户编号",required=true), 
		@ApiImplicitParam(name="deptids",value="部门编号列表",required=true),      
	})
	@ApiResponses({
		@ApiResponse(code = 200,response=DeptPostUser.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},deptPostUsers:[{deptid:'',postId:'',userid:''}],deptPosts:[{deptid:'',postId:''}]")
	})
	@RequestMapping(value="/list/roles",method=RequestMethod.GET)
	public Result listDeptPostUserRoles( @RequestParam Map<String,Object> deptPostUser){
		 


		User user = LoginUtils.getCurrentUserInfo();
		deptPostUser.put("branchId",user.getBranchId());
		List<Map<String,Object>>  deptPostUserRoles=this.deptPostUserService.listDeptPostUserRoles(deptPostUser);
		
		return Result.ok().setData( deptPostUserRoles);
	}
	@RequestMapping(value="/batchAdd",method=RequestMethod.POST)
	@HasQx(value = "sys_post_deptPostUser_batchAdd",name="批量将用户加入部门岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")
	@DeptFilter
	public Result batchAdd(@RequestBody List<DeptPostUser> dpus){
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员有权限更新");
		}
		for (DeptPostUser dpu : dpus) {
			if(ObjectTools.isEmpty(dpu.getPostId())){
				return Result.error("postId-required","岗位编号不能为空");
			}
			if(ObjectTools.isEmpty(dpu.getDeptid())){
				return Result.error("deptid-required","部门编号不能为空");
			}
			if(ObjectTools.isEmpty(dpu.getUserid())){
				return Result.error("userid-required","用户编号不能为空");
			}
		}
		List<DeptPostUser> deptPostUsers=new ArrayList<>();
		for (DeptPostUser dpu : dpus) {
			DeptPostUser dpu2=this.deptPostUserService.getOne(Wrappers.<DeptPostUser>lambdaQuery().eq(DeptPostUser::getPostId,dpu.getPostId())
					.eq(DeptPostUser::getDeptid,dpu.getDeptid()).eq(DeptPostUser::getUserid,dpu.getUserid()));
			if(dpu2!=null){
				continue;
			}
			dpu.setId(this.deptPostUserService.createKey("id"));
			dpu.setStartDate(new Date());
			dpu.setLastDate(new Date());
			deptPostUsers.add(dpu);
		}
		if(deptPostUsers.size()>0){
			this.deptPostUserService.saveBatch(deptPostUsers);
		}
		// 需要同步更新部门表
		Map<String,String> userDeptMap=new HashMap<>();
		for (DeptPostUser dpu : deptPostUsers) {
			userDeptMap.put(dpu.getUserid(),dpu.getDeptid());
		}
		List<UserDept> userDepts=userDeptService.listByIds(userDeptMap.entrySet().stream().map(e->map("userid",e.getKey(),"deptid",e.getValue())).collect(Collectors.toList()));

		if(userDepts!=null && userDepts.size()>0){
			for (UserDept userDept : userDepts) {
				String deptid=userDeptMap.get(userDept.getUserid());
				if(ObjectTools.isNotEmpty(deptid) && deptid.equals(userDept.getDeptid())){
					userDeptMap.remove(userDept.getUserid());
				}
			}
		}
		List<UserDept> uds=new ArrayList<>();
		if(!userDeptMap.isEmpty()){
			for (Map.Entry<String, String> en : userDeptMap.entrySet()) {
				 UserDept userDept=new UserDept();
				 userDept.setUserid(en.getKey());
				 userDept.setDeptid(en.getValue());
				 userDept.setEnabled("1");
				 uds.add(userDept);
			}
			userDeptService.saveBatch(uds);
		}

			return Result.ok();
	}

	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	@HasQx(value = "sys_post_deptPostUser_batchDel",name="批量将用户移出岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")
	@DeptFilter
	public Result batchDel(@RequestBody List<DeptPostUser> dpus){
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员有权限更新");
		}
		for (DeptPostUser dpu : dpus) {
			if(ObjectTools.isEmpty(dpu.getId())){
				return Result.error("id-required","编号不能为空");
			}
		}
	 	this.deptPostUserService.removeByIds(dpus);
		return Result.ok();
	}
	@ApiOperation( value = "删除一条部门岗位用户关系表（根据部门岗位关系,岗位用户关系自动冗余）信息",notes="delDeptPostUser,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST) 

	@HasQx(value = "sys_post_deptPostUser_del",name="将用户移出岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")
	@DeptFilter
	public Result delDeptPostUser(@RequestBody DeptPostUser deptPostUser){

		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员有权限更新");
		}
			deptPostUserService.deleteByPk(deptPostUser);
			return Result.ok();
	}


	@ApiOperation( value = "设置主岗位，一个机构下一个用户只能有一个主岗位",notes="delDeptPostUser,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@RequestMapping(value="/setPostMaster",method=RequestMethod.POST)

	@HasQx(value = "sys_post_deptPostUser_setPostMaster",name="设置用户的主要岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")
	@DeptFilter
	public Result setPostMaster(@RequestBody DeptPostUser deptPostUser){
		
		
		try{

			User u=LoginUtils.getCurrentUserInfo();
			if(!StringUtils.hasText(deptPostUser.getId())){
				return Result.error("id-is-null","主键参数id必传");
			}

			if(!StringUtils.hasText(deptPostUser.getMaster())){
				return Result.error("master-is-null","参数master必传");
			}
			DeptPostUser deptPostUserDb=this.deptPostUserService.selectOneObject(deptPostUser);
			if(deptPostUserDb==null){
				return Result.error("data-is-null","数据不存在");
			}
			this.deptPostUserService.setPostMaster(deptPostUserDb.getUserid(),deptPostUser.getId(),deptPostUser.getMaster(),u.getBranchId());
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
