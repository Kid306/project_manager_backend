package com.mdp.sys.ctrl;

import com.mdp.core.entity.Result;
import com.mdp.sys.entity.User;
import com.mdp.sys.service.UserService;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RestController("mdp.sys.userNoauthController")
@RequestMapping(value="/*/sys/user/noauth")
@Api(tags={"sys_user操作接口"})
public class UserNoauthController {

	static Log logger=LogFactory.getLog(UserNoauthController.class);



	@Autowired
	private UserService userService;


	@ApiOperation( value = "查询sys_user信息",notes="listUser,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({
			@ApiImplicitParam(name="userid",value="内部用户编号,主键",required=false),
	})
	@ApiResponses({
			@ApiResponse(code = 200,response= User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public Result detail(@RequestParam String userid){
		
		
		if( StringUtils.isEmpty(userid) ) {
			return Result.error("userid-0","请上送userid");
		}
		Map<String,Object> user=this.userService.detailWithInterests(userid);
		if(user!=null){
			user.put("idCardNo",UserService.idCardEncode((String) user.get("idCardNo")));
			user.put("email",UserService.emailEncode((String) user.get("email")));
			user.put("emailBak",UserService.emailEncode((String) user.get("emailBak")));
			user.put("phoneno",UserService.phoneEncode((String) user.get("phoneno")));
 			user.put("password","******");
		}
		return Result.ok().setData(user);
	}


}
