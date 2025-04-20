package com.mdp.sys.ctrl;

import com.mdp.core.entity.Result;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.Branch;
import com.mdp.sys.service.BranchService;
import com.mdp.sys.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.toMap;

/**
 * url编制采用rest风格,如对ADMIN.sys_branch 管理端机构表（机构下面若干部门）的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/branch/add <br>
 *  查询: sys/branch/list<br>
 *  模糊查询: sys/branch/listKey<br>
 *  修改: sys/branch/edit <br>
 *  删除: sys/branch/del<br>
 *  批量删除: sys/branch/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Branch 表 ADMIN.sys_branch 当前主键(包括多主键): id; 
 ***/
@RestController("mdp.sys.branchCpdController")
@RequestMapping(value="/*/sys/branch")
@Api(tags={"管理端机构表（机构下面若干部门）操作接口"})
public class BranchCpdController {
	
	static Log logger=LogFactory.getLog(BranchCpdController.class);
	
	@Autowired
	private BranchService branchService;
	@Autowired
	private DeptService deptService;

	Map<String,Object> fieldsMap = toMap(new Branch());
	
	@ApiOperation( value = "统计机构人数",notes="listBranch,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")

	@ApiResponses({
		@ApiResponse(code = 200,response= Branch.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@HasRole
	@RequestMapping(value="/calcBranchUsers",method=RequestMethod.GET)
	public Result calcBranchUsers(  ){
		 
		
		User user=LoginUtils.getCurrentUserInfo();
		Map<String,Object> data=this.branchService.calcBranchUsers(user.getBranchId());
		if(data==null||data.isEmpty()){
			data=new HashMap<>();
			data.put("currUsers",1);
			data.put("maxUsers",10);
			data.put("branchId",user.getBranchId());
			data.put("imgUrl",user.getHeadimgurl());
			data.put("branchName",user.getBranchName());
		}
		return Result.ok().setData(data);
		
	}


}
