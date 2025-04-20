package com.mdp.sys.ctrl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.qx.DataLvl;
import com.mdp.qx.DeptFilter;
import com.mdp.qx.HasDataLvl;
import com.mdp.qx.HasQx;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.Post;
import com.mdp.sys.service.PostRoleService;
import com.mdp.sys.service.PostService;
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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * url编制采用rest风格,如对ADMIN.岗位 岗位的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/post/post/add <br>
 *  查询: sys/post/post/list<br>
 *  模糊查询: sys/post/post/listKey<br>
 *  修改: sys/post/post/edit <br>
 *  删除: sys/post/post/del<br>
 *  批量删除: sys/post/post/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 post<br>
 * 实体 Post 表 ADMIN.岗位 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.post.postController")
@RequestMapping(value="/*/sys/post")
@Api(tags={"岗位操作接口"})
public class PostController {
	
	static Log logger=LogFactory.getLog(PostController.class);
	
	@Autowired
	private PostService postService;

	@Autowired
	PostRoleService postRoleService;
 
	
	@ApiOperation( value = "查询岗位信息列表",notes="listPost,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiResponses({
		@ApiResponse(code = 200,response= Post.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listPost( @ApiIgnore @RequestParam Map<String,Object>  params){
		 
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<Post> qw= QueryTools.initQueryWrapper(Post.class,params);
		List<Map<String,Object>>	postList = postService.selectListMapByWhere(page,qw,params);
		
		return Result.ok().setData(postList).setTotal(page.getTotal());
		
	}

	
	@ApiOperation( value = "查询未加入部门的岗位信息列表",notes="listPostNotInDeptid,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiResponses({
		@ApiResponse(code = 200,response=Post.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list/notInDeptid",method=RequestMethod.GET)
	public Result listPostNotInDeptid(@RequestParam Map<String,Object> post){
		List<Map<String,Object>>	postList = postService.listPostNotInDeptid(post);	//列出Post列表
		return Result.ok().setData(postList);
		
	}
	
	/***/
	@ApiOperation( value = "新增一条岗位信息",notes="addPost,主键如果为空，后台自动生成")
	@ApiResponses({
		@ApiResponse(code = 200,response=Post.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@HasQx(value = "sys_post_post_add",name="增加岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")

	@DeptFilter(min= DataLvl.branch)
	public Result addPost(@RequestBody Post post) {
		
		
		try{

			if(StringUtils.isEmpty(post.getId())) {
				post.setId(postService.createKey("id"));
			}else{
				Post postQuery = new  Post(post.getId());
				if(postService.countByWhere(postQuery)>0){
					return Result.error("id-exists","编号重复，请修改编号再提交");
				}
			}
			
			User user=LoginUtils.getCurrentUserInfo();
			if(ObjectTools.isEmpty(post.getBranchId())){
				post.setBranchId(user.getBranchId());
			}
			if(!user.getBranchId().equals(post.getBranchId())) {
				if(!LoginUtils.isSuperAdmin()) {
					return Result.error("branch-not-right", "无权操作");
				}
			}
			if(!LoginUtils.isBranchAdmin(post.getBranchId())){ 
				return Result.error("not-branch-admin", "只有机构管理员有权限操作");
			}
			post.setCdate(new Date());
			postService.insert(post);
			return Result.ok().setData(post);
		}catch (BizException e) { 
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}  
	}
	
	
	/***/
	@ApiOperation( value = "删除一条岗位信息",notes="delPost,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	@HasQx(value = "sys_post_post_del",name="删除岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")
	public Result delPost(@RequestBody Post post){
		Assert.notNull(post.getId());
		Post postDb=postService.getById(post.getId());
		Assert.notNull(postDb);
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isSuperAdmin() && !user.getBranchId().equals(postDb.getBranchId())) {
			return Result.error("branch-not-right", "机构号不正确");
		}
		if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(postDb.getBranchId())){
			return Result.error("not-branch-admin","您不是机构管理员，无权操作");
		}
		postService.removeById(postDb.getId());
		return Result.ok();
	}

	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiEntityParams( value =Post.class, props={ }, remark = "个人信息", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response= Post.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> params) {


		try{
			 this.postService.editSomeFields(params);
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
	@ApiOperation( value = "根据主键修改一条岗位信息",notes="editPost")
	@ApiResponses({
		@ApiResponse(code = 200,response=Post.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@HasQx(value = "sys_post_post_edit",name="修改岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")

	@DeptFilter(min=DataLvl.branch)
	public Result editPost(@RequestBody Post post) {
		Assert.notNull(post.getId());
		Post postDb=postService.getById(post.getId());
		Assert.notNull(postDb);
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isSuperAdmin() && !user.getBranchId().equals(post.getBranchId())) {
			return Result.error("branch-not-right", "机构号不正确");
		}
		if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(post.getBranchId())){
			return Result.error("not-branch-admin","您不是机构管理员，无权操作");
		}
		postService.updateById(post,true);
		return Result.ok().setData(post);
	}
	
	

	
	/***/
	@ApiOperation( value = "根据主键列表批量删除岗位信息",notes="batchDelPost,仅需要上传主键字段")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	}) 
	@RequestMapping(value="/batchDel",method=RequestMethod.POST) 

	@HasDataLvl(DataLvl.branch)
	@HasQx(value = "sys_post_post_batchDel",name="批量删除岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")
	public Result batchDelPost(@RequestBody List<Post> posts) {
		

		User user=LoginUtils.getCurrentUserInfo();
		List<Post> postsDb=this.postService.selectListByIds(posts.stream().map(i->i.getId()).collect(Collectors.toList()));
		if(postsDb==null || postsDb.size()==0){
			return Result.error("data-0","数据不存在");
		}
		for (Post post : postsDb) {
			if(!user.getBranchId().equals(post.getBranchId())) {
				if(!LoginUtils.isSuperAdmin()) {
					return Result.error("branch-not-right", "无权跨机构操作数据");
				}
			}
		}
		if(!LoginUtils.isBranchAdmin(postsDb.get(0).getBranchId())){
			return Result.error("not-branch-admin","只有机构管理员有权限操作");
		}

		postService.batchDelete(postsDb);
		return Result.ok();
	} 
	
}
