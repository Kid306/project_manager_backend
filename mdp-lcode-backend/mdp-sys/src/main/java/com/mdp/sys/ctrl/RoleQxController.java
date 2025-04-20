package com.mdp.sys.ctrl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.qx.HasQx;
import com.mdp.safe.client.cache.RoleQxsRedisCacheService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.RoleQx;
import com.mdp.sys.entity.RoleQxVo;
import com.mdp.sys.service.RoleQxService;
import com.mdp.sys.service.RoleService;
import com.mdp.sys.tools.QxTools;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.map;

/**
 * url编制采用rest风格,如对ADMIN.sys_role_qx 角色权限关系表的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/roleQx/add <br>
 *  查询: sys/roleQx/list<br>
 *  模糊查询: sys/roleQx/listKey<br>
 *  修改: sys/roleQx/edit <br>
 *  删除: sys/roleQx/del<br>
 *  批量删除: sys/roleQx/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 RoleQx 表 ADMIN.sys_role_qx 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.roleQxController")
@RequestMapping(value="/*/sys/roleQx")
@Api(tags={"角色权限关系表操作接口"})
public class RoleQxController {
	
	static Logger logger= LoggerFactory.getLogger(RoleQxController.class);
	
	@Autowired
	private RoleQxService roleQxService;

	@Autowired
	private RoleService roleService;

	@Autowired
	RoleQxsRedisCacheService roleQxsRedisCacheService;
		 
	
	@ApiOperation( value = "查询角色权限关系表信息列表",notes="listRoleQx,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="主键,主键",required=false),
		@ApiImplicitParam(name="qxId",value="权限编号增删改查批量更新导出导入",required=false),
		@ApiImplicitParam(name="roleid",value="角色编号",required=false),
		@ApiImplicitParam(name="pageSize",value="每页记录数",required=false),
		@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
		@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
		@ApiImplicitParam(name="orderFields",value="排序列 如性别、学生编号排序 ['sex','studentId']",required=false),
		@ApiImplicitParam(name="orderDirs",value="排序方式,与orderFields对应，升序 asc,降序desc 如 性别 升序、学生编号降序 ['asc','desc']",required=false) 
	})
	@ApiResponses({
		@ApiResponse(code = 200,response= RoleQx.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public Result listRoleQx(@ApiIgnore @RequestParam Map<String,Object> params){
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<RoleQx> qw= QueryTools.initQueryWrapper(RoleQx.class,params);
		List<Map<String,Object>> roleQxList = roleQxService.selectListMapByWhere(page,qw,params);
		return Result.ok().setData(roleQxList);
		
		
	}

	@ApiResponses({
			@ApiResponse(code = 200,response= RoleQx.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/getRoleQxs",method= RequestMethod.GET)
	public Result getRoleQxs( @RequestParam String roleid){
		
		Map<String,Object> params=map("roleid",roleid);
		IPage page=QueryTools.initPage(1,1000,0L,false);
		QueryWrapper<RoleQx> qw=QueryTools.initQueryWrapper(RoleQx.class,params);
		List<Map<String,Object>> roleQxList = roleQxService.selectListMapByWhere(page,qw,params);
		return Result.ok().setData(roleQxList);
	}




	/**
	 * 批量设置角色对应的权限
	 */
	@HasQx(value="sys_roleQx_batchEdit",name="给角色批量设置权限",moduleId = "sys",moduleName = "组织管理")
	@RequestMapping(value="/batchEdit",method=RequestMethod.POST)
	public Result batchEdit(@RequestBody RoleQxVo roleQxVo) {
		
		 
		try{
			if(StringUtils.isEmpty(roleQxVo.roleid)) {
				throw new BizException("角色编号不能为空");
			}
			User user= LoginUtils.getCurrentUserInfo();
			boolean isSuperAdmin=LoginUtils.isSuperAdmin();
			Tips tips=roleService.roleOpCheck(LoginUtils.getCurrentUserInfo(),isSuperAdmin,roleQxVo.roleid,null,null);
			Result.assertIsFalse(tips);
			QxTools.assertDisBranchAdmOpRoles(roleQxVo.roleid);
			tips=roleQxService.batchEdit(roleQxVo);
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
