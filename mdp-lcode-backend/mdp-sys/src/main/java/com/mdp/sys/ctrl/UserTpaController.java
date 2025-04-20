package com.mdp.sys.ctrl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.UserTpa;
import com.mdp.sys.service.UserDataBaseService;
import com.mdp.sys.service.UserTpaService;
import com.mdp.sys.vo.InviteScanVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
/**
 * @author maimeng-mdp code-gen
 * @since 2023-9-22
 */
@RestController
@RequestMapping(value="/mdp/sys/userTpa")
@Api(tags={"第三方系统向我方开放的用户列表-操作接口"})
public class UserTpaController {
	
	static Logger logger =LoggerFactory.getLogger(UserTpaController.class);
	
	@Autowired
	private UserTpaService userTpaService;

	@Autowired
	UserDataBaseService userDataBaseService;

	@ApiOperation( value = "第三方系统向我方开放的用户列表-查询列表",notes=" ")
	@ApiEntityParams(UserTpa.class)
	@ApiResponses({
		@ApiResponse(code = 200,response=UserTpa.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUserTpa(@ApiIgnore @RequestParam Map<String,Object> params){
		try {
			User user=LoginUtils.getCurrentUserInfo();
			QueryWrapper<UserTpa> qw = QueryTools.initQueryWrapper(UserTpa.class , params);
			qw.eq("bind_branch_id",user.getBranchId());
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = userTpaService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		}catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error(e);
		}
	}
	@ApiOperation( value = "第三方系统向我方开放的用户列表-查询列表",notes=" ")
	@ApiEntityParams(UserTpa.class)
	@ApiResponses({
			@ApiResponse(code = 200,response=UserTpa.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},total:总记录数,data:[数据对象1,数据对象2,...]}")
	})
	@RequestMapping(value="/listByUserid",method=RequestMethod.GET)
	public Result listByUserid(@ApiIgnore @RequestParam Map<String,Object> params){
		try {
			String userid= (String) params.get("userid");
			if(ObjectTools.isEmpty(userid)){
				return Result.error("userid-required","用户编号不能为空");
			}
			QueryWrapper<UserTpa> qw = QueryTools.initQueryWrapper(UserTpa.class , params);
			IPage page=QueryTools.initPage(params);
			List<Map<String,Object>> datas = userTpaService.selectListMapByWhere(page,qw,params);
			return Result.ok("query-ok","查询成功").setData(datas).setTotal(page.getTotal());
		}catch (BizException e) {
			return Result.error(e);
		}catch (Exception e) {
			return Result.error(e);
		}
	}
	@ApiOperation( value = "用户扫码 群发邀请码 绑定第三方账户到系统用户上，如果系统用户不存在则创建系统用户，存在则绑定第三方账户，",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@RequestMapping(value="/invite",method=RequestMethod.POST)
	public Result invite(@RequestBody InviteScanVo vo){
		logger.debug("InviteScanVo:"+ JSON.toJSONString(vo));

		com.mdp.sys.entity.User user=userDataBaseService.invite(vo.getInviteId(),vo.getTpa(),vo.getTpadbExists());
 		return Result.ok().setData(user);
	}
	@ApiOperation( value = "用户扫码 指定用户编号的邀请码 绑定第三方账户到系统用户上，如果系统用户不存在则创建系统用户，存在则绑定第三方账户，",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@RequestMapping(value="/bind",method=RequestMethod.POST)
	public Result bind(@RequestBody InviteScanVo vo){
		logger.debug("InviteScanVo:"+ JSON.toJSONString(vo));

		com.mdp.sys.entity.User user=userDataBaseService.bind(vo.getInviteId(),vo.getTpa(),vo.getTpadbExists());
		return Result.ok().setData(user);
	}
	@ApiOperation( value = "用户扫码 认领企业邀请码 绑定第三方账户到系统用户上，如果系统用户不存在则创建系统用户，存在则绑定第三方账户，",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@RequestMapping(value="/claim",method=RequestMethod.POST)
	public Result claim(@RequestBody InviteScanVo vo){
		logger.debug("InviteScanVo:"+ JSON.toJSONString(vo));

		com.mdp.sys.entity.User user=userDataBaseService.claim(vo.getInviteId(),vo.getTpa(),vo.getTpadbExists());
		return Result.ok().setData(user);
	}
	@ApiOperation( value = "用户扫码登录，自动注册",notes=" ")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})
	@RequestMapping(value="/registerUserByTpa",method=RequestMethod.POST)
	public Result registerUserByTpa(@RequestBody InviteScanVo vo){
		logger.debug("InviteScanVo:"+ JSON.toJSONString(vo));
		com.mdp.sys.entity.User user=userDataBaseService.registerUserByTpa(vo.getInviteId(),vo.getTpa(),vo.getTpadbExists());
		return Result.ok().setData(user);
	}


	@ApiOperation( value = "第三方系统向我方开放的用户列表-删除",notes=" ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	}) 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUserTpa(@RequestBody UserTpa userTpa){
		userTpaService.removeById(userTpa);
        return Result.ok("del-ok","删除成功！");
	}


	@ApiOperation( value = "第三方系统向我方开放的用户列表-根据主键查询一条数据",notes=" ")
     @ApiResponses({
            @ApiResponse(code = 200,response=UserTpa.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
    })
    @RequestMapping(value="/queryById",method=RequestMethod.GET)
    public Result queryById(UserTpa userTpa) {
        UserTpa data = (UserTpa) userTpaService.getById(userTpa);
        return Result.ok().setData(data);
    }

}
