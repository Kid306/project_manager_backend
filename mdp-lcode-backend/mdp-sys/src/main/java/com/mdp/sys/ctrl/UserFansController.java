package com.mdp.sys.ctrl;

import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.BaseUtils;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.FansCpdVo;
import com.mdp.sys.entity.UserFans;
import com.mdp.sys.service.UserFansService;
import com.mdp.sys.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.mdp.core.utils.BaseUtils.map;
import static com.mdp.core.utils.BaseUtils.toMap;


/**
 * url编制采用rest风格,如对sys_user_fans sys_user_fans的操作有增删改查,对应的url分别为:<br>
 * 组织 com  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserFans 表 sys_user_fans 当前主键(包括多主键): userid,fuserid; 
 ***/
@RestController("mdp.sys.userFansController")
@RequestMapping(value="/*/sys/userFans")
@Api(tags={"sys_user_fans操作接口"})
public class UserFansController {
	
	static Logger logger =LoggerFactory.getLogger(UserFansController.class);
	
	@Autowired
	private UserFansService userFansService;



	@Autowired
	PushNotifyMsgService pushNotifyMsgService;
	 

	Map<String,Object> fieldsMap = toMap(new UserFans());

	@ApiOperation( value = "查询属于我的粉丝列表",notes=" ")
	@ApiEntityParams( UserFans.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
	})
	@ApiResponses({
			@ApiResponse(code = 200,response=com.mdp.sys.entity.User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list/myFans",method=RequestMethod.GET)
	public Result myFans( @ApiIgnore @RequestParam Map<String,Object> userFans){
		


		User user=LoginUtils.getCurrentUserInfo();
		userFans.put("fuserid",user.getUserid());
		List<Map<String,Object>>	userFansList = userFansService.myFans(userFans);	//列出UserFans列表
		if(userFans!=null){
			for (Map<String, Object> map : userFansList) {
				map.put("idCardNo",null);
				map.put("password",null);
				String phoneno= (String) map.get("phoneno");
				map.put("phoneno", UserService.phoneEncode(phoneno));
			}
		}
		
		return Result.ok().setData(userFansList);

	}


	@ApiOperation( value = "查询某个人的粉丝数，及其关注别人的数量",notes=" ")

	@ApiImplicitParams({
			@ApiImplicitParam(name="calcUserid",value="用户编号",required=true),
	})
	@ApiResponses({
			@ApiResponse(code = 200,response= FansCpdVo.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/fansCpd",method=RequestMethod.GET)
	public Result fansCpd( @ApiIgnore Map<String,Object> userFans){
		
		
		if(userFans.containsKey("myUserid")){
			User user=LoginUtils.getCurrentUserInfo();
			userFans.put("userid",user.getUserid());
			userFans.put("fuserid",user.getUserid());
		}else if(userFans.containsKey("calcUserid")){
			String calcUserid= (String) userFans.get("calcUserid");
			userFans.put("userid",calcUserid);
			userFans.put("fuserid",calcUserid);
		}else{
			return Result.error("error-01","参数错误，myUserid or calcUserid");
		}

		FansCpdVo	fansCpdVo = userFansService.fansCpd(userFans);	//列出UserFans列表
		if(fansCpdVo==null){
			fansCpdVo=new FansCpdVo();
			fansCpdVo.setMyFansCnt(0);
			fansCpdVo.setMyFocusCnt(0);
		}
 		return Result.ok().setData(fansCpdVo);

	}

	@ApiOperation( value = "查询我关注的用户列表",notes=" ")
	@ApiEntityParams( UserFans.class )
	@ApiImplicitParams({
			@ApiImplicitParam(name="pageSize",value="每页大小，默认20条",required=false),
			@ApiImplicitParam(name="pageNum",value="当前页码,从1开始",required=false),
			@ApiImplicitParam(name="total",value="总记录数,服务器端收到0时，会自动计算总记录数，如果上传>0的不自动计算",required=false),
			@ApiImplicitParam(name="count",value="是否计算总记录条数，如果count=true,则计算计算总条数，如果count=false 则不计算",required=false),
			@ApiImplicitParam(name="orderBy",value="排序列 如性别、学生编号排序 orderBy = sex desc,student desc",required=false),
	})
	@ApiResponses({
			@ApiResponse(code = 200,response=com.mdp.sys.entity.User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list/myFocus",method=RequestMethod.GET)
	public Result myFocus( @ApiIgnore Map<String,Object> userFans){
		
		

		User user=LoginUtils.getCurrentUserInfo();
		userFans.put("userid",user.getUserid());
		List<Map<String,Object>>	userFansList = userFansService.myFocus(userFans);	//列出UserFans列表
		if(userFans!=null){
			for (Map<String, Object> map : userFansList) {
				map.put("idCardNo",null);
				map.put("password",null);
				String phoneno= (String) map.get("phoneno");
				map.put("phoneno", UserService.phoneEncode(phoneno));
			}
		}
		
		return Result.ok().setData(userFansList);

	}



	@ApiOperation( value = "新增一条sys_user_fans信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200,response=UserFans.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	}) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUserFans(@RequestBody UserFans userFans) {
		
		
		try{
			if(!StringUtils.hasText(userFans.getFuserid())){
				return Result.error("fuserid-0","关注对象不能为空");
			}
			User user=LoginUtils.getCurrentUserInfo();
			if(user.getUserid().equals(userFans.getFuserid())){
				return Result.error("fuserid-1","不能自己关注自己");
			}
			userFans.setUserid(user.getUserid());
			userFans.setFtime(new Date());
			if(userFansService.countByWhere(BaseUtils.fromMap(map("userid",userFans.getUserid(),"fuserid",userFans.getFuserid()),UserFans.class))>0){
				return Result.error("fuserid-1","已关注");
			}
			userFansService.insert(userFans);
			pushNotifyMsgService.pushMsg(user, userFans.getFuserid(),userFans.getFuserid(),user.getUsername()+"关注了您",null);

			return Result.ok().setData(userFans);
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

	@ApiOperation( value = "删除一条sys_user_fans信息",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserFans(@RequestBody UserFans userFans){
		
		
		try{
            User user=LoginUtils.getCurrentUserInfo();
            userFans.setUserid(user.getUserid());
            if(!StringUtils.hasText(userFans.getFuserid())) {
                 return Result.error("pk-not-exists","请上送主键参数fuserid");
            }
            UserFans userFansDb = userFansService.selectOneObject(userFans);
            if( userFansDb == null ){
                return Result.error("data-not-exists","数据不存在，无法删除");
            }
			userFansService.deleteByPk(userFans);
            return Result.ok();
		}catch (BizException e) { 
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}  
	}

}
