package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.qx.HasQx;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.Post;
import com.mdp.sys.entity.PostRole;
import com.mdp.sys.entity.RolesToPostVo;
import com.mdp.sys.service.PostRoleService;
import com.mdp.sys.service.PostService;
import com.mdp.sys.service.RoleService;
import com.mdp.sys.tools.QxTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * url编制采用rest风格,如对ADMIN.sys_post_role 岗位角色关系表的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/post/postRole/add <br>
 *  查询: sys/post/postRole/list<br>
 *  模糊查询: sys/post/postRole/listKey<br>
 *  修改: sys/post/postRole/edit <br>
 *  删除: sys/post/postRole/del<br>
 *  批量删除: sys/post/postRole/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 post<br>
 * 实体 PostRole 表 ADMIN.sys_post_role 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.post.postRoleController")
@RequestMapping(value="/*/sys/postRole")
@Api(tags={"岗位角色关系表操作接口"})
public class PostRoleController {
	
	static Log logger=LogFactory.getLog(PostRoleController.class);
	
	@Autowired
	private PostRoleService postRoleService;
	 
	@Autowired
	private RoleService roleService;


	@Autowired
	private PostService postService;
	
	@ApiOperation( value = "查询岗位角色关系表信息列表",notes="listPostRole,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(PostRole.class)
	@ApiResponses({
		@ApiResponse(code = 200,response= PostRole.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listPostRole( @ApiIgnore @RequestParam Map<String,Object>  params){
		 
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<PostRole> qw= QueryTools.initQueryWrapper(PostRole.class,params);
		List<Map<String,Object>>	postRoleList = postRoleService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(postRoleList).setTotal(page.getTotal());
		
	}
	

	
	/** */
	@ApiOperation( value = "删除一条岗位角色关系表信息",notes="delPostRole,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)  

	@HasQx(value = "sys_post_postRole_del",name="删除岗位与角色关联关系",moduleId = "mdp-sys",moduleName = "后台管理系统")
	public Result delPostRole(@RequestBody PostRole postRole){
		
		
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("not-branch-admin","您无权操作，只有机构管理员有权限操作。");
		}
		try{
			 Post postDb=this.postService.selectOneById(postRole.getPostId());
			 if(postDb==null){
			 	return Result.error("data-0","岗位不存在。");
			 }
			 if(!user.getBranchId().equals(postDb.getBranchId())){
				 return Result.error("branchId-0","不属于您机构的岗位，不允许设置。");
			 }
			 postRoleService.deleteByPk(postRole);
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
	@ApiOperation( value = "根据主键列表批量删除岗位角色关系表信息",notes="batchDelPostRole,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	@HasQx(value = "sys_post_postRole_batchDel",name="批量删除岗位与角色关联关系",moduleId = "mdp-sys",moduleName = "后台管理系统")

	public Result batchDelPostRole(@RequestBody List<PostRole> postRoles) {
		
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("not-branch-admin","您无权操作，只有机构管理员有权限操作。");
		}
		
		String[] list=new String[postRoles.size()];
		try{
			List<String> postIds=postRoles.stream().map(i->i.getPostId()).collect(Collectors.toSet()).stream().collect(Collectors.toList());
			List<Post> posts=this.postService.selectListByIds(postIds);
			Map<String,Post> canOpPosts=new HashMap<>();

			for (Post post : posts) {
				if(post.getBranchId().equals(user.getBranchId())){
					canOpPosts.put(post.getId(),post);
				}
			}
			List<PostRole> canOp=new ArrayList<>();
			List<PostRole> noSameBranch=new ArrayList<>();
			for (PostRole postRole : postRoles) {
				if(!canOpPosts.containsKey(postRole.getPostId())){
					noSameBranch.add(postRole);
				}else{
					canOp.add(postRole);
				}
			}
			List<String> msgs=new ArrayList<>();

			if(canOp.size()>0) {
				postRoleService.batchDelete(canOp);
				msgs.add(String.format("成功删除%s个岗位",canOp.size()));
			}
			if(noSameBranch.size()>0){
				msgs.add(String.format("有%s个岗位不是您机构的岗位，不能删除。",noSameBranch.size()));
			}
			if(canOp.size()>0) {
				return Result.ok(String.join("",msgs));
			}else {
				return Result.error(String.join("",msgs));
			}
			
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	@RequestMapping(value="/batchAdd",method=RequestMethod.POST)
	@HasQx(value = "sys_post_postRole_batchAdd",name="批量新增岗位角色干系",moduleId = "mdp-sys",moduleName = "后台管理系统")

	public Result batchAddPostRole(@RequestBody List<PostRole> postRoles) {

		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("not-branch-admin","您无权操作，只有机构管理员有权限操作。");
		}
		for (PostRole postRole : postRoles) {
			if (ObjectTools.isEmpty(postRole.getPostId())) {
				return Result.error("postId-required", "岗位编号不能为空");
			}
			if(ObjectTools.isEmpty(postRole.getRoleid())){
				return Result.error("roleid-required", "角色编号不能为空");
			}
		}
		QxTools.assertDisBranchAdmOpRoles(postRoles.stream().map(k->k.getRoleid()).toArray(String[]::new));
 		this.postRoleService.batchAdd(postRoles);
		return Result.ok();
	}
		@ApiOperation( value = "",notes="setRolesToPost,批量设置角色到某个岗位上，将删除原来该岗位上的角色")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/setRolesToPost",method=RequestMethod.POST)

	@HasQx(value = "sys_post_postRole_setRolesToPost",name="批量设置角色到某个岗位上，将删除原来该岗位上的角色",moduleId = "mdp-sys",moduleName = "后台管理系统")
	public Result setRolesToPost(@RequestBody RolesToPostVo postRoles) {
		
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("not-branch-admin","您无权操作，只有机构管理员有权限操作。");
		}
		 
 		try{
			if(ObjectTools.isEmpty(postRoles.getPostId())){
				return Result.error("postId-required","岗位编号不能为空");
			}
			Post post=this.postService.getById(postRoles.getPostId());
			if(post==null){
				return Result.error("post-not-exists","岗位已不存在");
			}
			if(!LoginUtils.isBranchAdmin(post.getBranchId())) {
				return Result.error("no-qx-set-roles-to-other-branch-post","你无权将角色赋予其它机构的岗位");
			}
			PostRole postRole=new PostRole();
			postRole.setPostId(postRoles.getPostId());
			if(postRoles.getRoleids()!=null && postRoles.getRoleids().size()>0){
				QxTools.assertDisBranchAdmOpRoles(postRoles.getRoleids().toArray(new String[postRoles.getRoleids().size()]));
			}
			this.postRoleService.deleteByWhere(postRole);
  			if(postRoles.getRoleids()!=null && postRoles.getRoleids().size()>0){
				List<PostRole> prs=postRoles.getRoleids().stream().map(k->{
					PostRole pr=new PostRole();
					pr.setPostId(postRoles.getPostId());
					pr.setRoleid(k);
					return pr;
				}).collect(Collectors.toList());
				this.postRoleService.batchAdd(prs);
			}

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
