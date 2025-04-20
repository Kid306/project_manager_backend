package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.qx.DeptFilter;
import com.mdp.qx.HasQx;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.BatchSetDeptsToPostVo;
import com.mdp.sys.entity.BatchSetPostsToDeptVo;
import com.mdp.sys.entity.DeptPost;
import com.mdp.sys.service.DeptPostService;
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
 * url编制采用rest风格,如对ADMIN.sys_dept_post 部门岗位关系表的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/post/deptPost/add <br>
 *  查询: sys/post/deptPost/list<br>
 *  模糊查询: sys/post/deptPost/listKey<br>
 *  修改: sys/post/deptPost/edit <br>
 *  删除: sys/post/deptPost/del<br>
 *  批量删除: sys/post/deptPost/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 post<br>
 * 实体 DeptPost 表 ADMIN.sys_dept_post 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.post.deptPostController")
@RequestMapping(value="/*/sys/deptPost")
@Api(tags={"部门岗位关系表操作接口"})
public class DeptPostController {
	
	static Log logger=LogFactory.getLog(DeptPostController.class);
	
	@Autowired
	private DeptPostService deptPostService;
	 
		
 
	
	@ApiOperation( value = "查询部门岗位关系表信息列表",notes="listDeptPost,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiResponses({
		@ApiResponse(code = 200,response= DeptPost.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listDeptPost( @ApiIgnore @RequestParam Map<String,Object>  deptPost){
		 
		
		IPage page=QueryTools.initPage(deptPost);
		QueryWrapper<DeptPost> qw= QueryTools.initQueryWrapper( DeptPost.class,deptPost);
		List<Map<String,Object>>	deptPostList = deptPostService.selectListMapByWhere(page,qw,deptPost);
		
		return Result.ok().setData(deptPostList).setTotal(page.getTotal());
		
	}
	

	@ApiOperation( value = "批量维护多个岗位到一个部门",notes="batchSetPostsToDept")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@HasQx(value = "sys_dept_post_batchSetPostsToDept",name="批量维护多个岗位到一个部门",moduleId = "mdp-sys",moduleName = "后台管理系统")
	@RequestMapping(value="/batchSetPostsToDept",method=RequestMethod.POST)
	@DeptFilter
	public Result batchSetPostsToDept(@RequestBody BatchSetPostsToDeptVo batchSetPostsToDept) {
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
		}
			deptPostService.batchSetPostsToDept(batchSetPostsToDept);
			return Result.ok();
	}
	@HasQx(value = "sys_dept_post_batchDel",name="批量删除部门岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	@DeptFilter
	public Result batchDel(@RequestBody List<DeptPost> dps) {
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
		}
		this.deptPostService.removeByIds(dps);
		return Result.ok();

	}

	@HasQx(value = "sys_dept_post_batchAdd",name="批量新增部门岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")
	@RequestMapping(value="/batchAdd",method=RequestMethod.POST)
	@DeptFilter
	public Result batchAdd(@RequestBody List<DeptPost> dps) {
		User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
		}
 		this.deptPostService.batchAddDeptPost(dps);
		return Result.ok();

	}

	@ApiOperation( value = "批量维护多个部门到一个岗位",notes="batchSetDeptsToPost")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@HasQx(value = "sys_dept_post_batchSetDeptsToPost",name="批量维护多个部门到一个岗位",moduleId = "mdp-sys",moduleName = "后台管理系统")
	@RequestMapping(value="/batchSetDeptsToPost",method=RequestMethod.POST)
	public Result batchSetDeptsToPost(@RequestBody BatchSetDeptsToPostVo batchSetDeptsToPost) {
		User user= LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isBranchAdmin(user.getBranchId())){
			return Result.error("must-branchAdmin-role","只有机构管理员可以操作");
		}
			deptPostService.batchSetDeptsToPost(batchSetDeptsToPost);
			return Result.ok();
	}
}
