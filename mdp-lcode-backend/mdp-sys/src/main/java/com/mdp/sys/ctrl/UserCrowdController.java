package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.query.QueryTools;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.User;
import com.mdp.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * url编制采用rest风格,如对ADMIN.sys_user sys_user的操作有增删改查,对应的url分别为:<br>
 *  新增: sys/user/add <br>
 *  查询: sys/user/list<br>
 *  模糊查询: sys/user/listKey<br>
 *  修改: sys/user/edit <br>
 *  删除: sys/user/del<br>
 *  批量删除: sys/user/batchDel<br>
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 User 表 ADMIN.sys_user 当前主键(包括多主键): userid; 
 ***/
@RestController("mdp.sys.userCrowdController")
@RequestMapping(value="/*/sys/user/crowd")
@Api(tags={"sys_user操作接口"})
public class UserCrowdController {
	
	static Log logger=LogFactory.getLog(UserCrowdController.class);
	
	@Autowired
	private UserService userService;
	
	@ApiOperation( value = "查询sys_user信息列表",notes="listUser,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(User.class)
	@ApiResponses({
		@ApiResponse(code = 200,response= User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUser(@ApiIgnore @RequestParam Map<String,Object> params){
		 
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<User> qw= QueryTools.initQueryWrapper(User.class,params);
 		qw.eq("open","1");
		List<Map<String,Object>>	userList = userService.selectListMapByWhere(page,qw,params);
		if(userList!=null && userList.size()>0){
			for (Map<String, Object> map : userList) {
				map.put("idCardNo",UserService.idCardEncode((String) map.get("idCardNo")));
				map.put("email",UserService.emailEncode((String) map.get("email")));
				map.put("emailBak",UserService.emailEncode((String) map.get("emailBak")));
				map.put("phoneno",UserService.phoneEncode((String) map.get("phoneno")));
				map.put("password",null);
			}
		}
		
		return Result.ok().setData(userList).setTotal(page.getTotal());
		
	}

	
}
