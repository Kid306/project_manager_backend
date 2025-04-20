/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mdp.login.ctrl;


import com.mdp.core.entity.Result;
import com.mdp.login.entity.RequestUserInfoParams;
import com.mdp.login.entity.Scope;
import com.mdp.login.service.SysUserQueryService;
import com.mdp.plat.client.PlatClientService;
import com.mdp.plat.client.entity.PlatformVo;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.cache.ModuleBranchRedisCacheService;
import com.mdp.safe.client.entity.Menu;
import com.mdp.safe.client.entity.ModuleBranch;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.service.UserResourceQueryService;
import com.mdp.safe.client.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */

@RestController
public class UserInfoController {
	@Autowired
	SysUserQueryService userQueryService;

	@Autowired
	UserResourceQueryService userResourceQueryService;


	@Autowired
	PlatClientService platClientService;

	@Autowired
	ModuleBranchRedisCacheService moduleBranchRedisCacheService;




	/**
	 * 密码登陆
	 * 支持手机、邮箱、微信等多种方式登陆
	 *
	 * @return
	 */
	@PostMapping(value = "/user/info")
	@ResponseBody
	public Result userInfo(@RequestBody RequestUserInfoParams params) {
		User user= LoginUtils.getCurrentUserInfo();
		Map<String,Object> extsParams=new HashMap<>();
		if(LoginUtils.isGuest()){
			return Result.error("user-not-login","未登录用户");
		}
		if(!StringUtils.hasText(user.getUserType())){
			user.setUserType("staff");
			extsParams.put("userType",user.getUserType());
		}else{
			extsParams.put("userType",user.getUserType());
		}
		user=this.getUserBaseInfo(user);
		Result result=Result.ok().put(Scope.userInfo.name(),user).put(Scope.roles.name(),LoginUtils.getAuthorities());
		String[] roleids=LoginUtils.getMyRoleids();
		if(params==null || params.getScopes()==null){
			return result;
		}else {
			if(params.getScopes().contains(Scope.menus.name())){
				if(!LoginUtils.isSuperAdmin()){
					Map<String, Menu> menusMap=  userResourceQueryService.loadRoleMenusByRoleids(roleids);
					Map<String,ModuleBranch> moduleBranchMap=new HashMap<>();
					Map<String,Menu> hasQxMenusMap=new HashMap<>();
					for (Menu menu : menusMap.values()) {
						if("0".equals(menu.getSupVers())){
							hasQxMenusMap.put(menu.getId(),menu);
						}else {
							String moduleId=menu.getModuleId();
							if( !StringUtils.hasText(menu.getSupVers()) || "0".equals(menu.getSupVers())){
								hasQxMenusMap.put(menu.getId(),menu);
							}else {
								ModuleBranch moduleBranch=moduleBranchMap.get(moduleId);
								if(moduleBranch==null && !moduleBranchMap.containsKey(moduleId)){
									moduleBranch = moduleBranchRedisCacheService.getModuleBranch(user.getBranchId(),moduleId);
									moduleBranchMap.put(moduleId,moduleBranch);
								}
								if(moduleBranch!=null && "1".equals(moduleBranch.getStatus())){
									if(StringUtils.hasText(moduleBranch.getVer()) && menu.getSupVers().contains(moduleBranch.getVer())){
										hasQxMenusMap.put(menu.getId(),menu);
									}
								}
							}
						}
					}
					result.put("menus",hasQxMenusMap.values());
				}else{
					result.put("menus",null);//超级管理员前端判断全部显示
				}

			}
			if(params.getScopes().contains(Scope.qxs.name())){
				//Map<String, Qx> qxsMap=  userResourceQueryService.loadRoleQxsByRoleids(roleids);
				//result.put("qxs",qxsMap.values());
			}
			if(params.getScopes().contains(Scope.posts.name())){
				result.put("posts",userResourceQueryService.loadPostsByUserid(user.getUserid()));
			}
			if(params.getScopes().contains(Scope.depts.name())){
				//List<Dept> depts=userResourceQueryService.loadDeptsByDeptids(user.getDeptids().toArray(new String[0]));
				//result.put("depts",depts);
			}
			if(params.getScopes().contains(Scope.branchs.name())){
				//List<Branch> branchs=userResourceQueryService.loadBranchsByBranchIds(  user.getBranchIds().toArray(new String[0]));
				//result.put("branchs",branchs);
			}
		}
		return result;
	}

	/**
	 * 查询用户的基本信息
	 * 支持手机、邮箱、微信等多种方式登陆
	 *
	 * @return
	 */
	@RequestMapping(value = "/user/baseinfo",method = RequestMethod.GET)
	@HasRole
	@ResponseBody
	public Result baseinfo() {
		User user= LoginUtils.getCurrentUserInfo();
		user=getUserBaseInfo(user);
		return Result.ok().setData(user);
	}
	/**
	 * 根据登录账户查询用户列表，一般在登录前查询一次，如果有多个账户，由用户选择一个登录
	 * 支持手机、邮箱、微信等多种方式登陆
	 *
	 * @return
	 */
	@RequestMapping(value = "/user/queryByUserloginid",method = RequestMethod.GET)
	@ResponseBody
	public Result queryByUserloginid(@RequestParam Map<String,Object> user) {
		String userloginid= (String) user.get("userloginid");
		if(!StringUtils.hasText(userloginid)){
			return Result.error("userloginid-0","请上送登录账户");
		}
		String idType= (String) user.get("idType");
		if(!StringUtils.hasText(idType)){
			idType="all";
		}
		List<User> users=this.userQueryService.queryByUserloginid(userloginid,idType,user);
		List<User> usersDb=new ArrayList<>();
		if(users!=null && users.size()>0){
			for (User userDb : users) {//去掉敏感数据
				userDb.setPassword(null);
				userDb.setEmail(null);
				userDb.setPhoneno(null);
				userDb.setTpaOpenid(null);
				userDb.put("address",null);
				userDb.put("birthday",null);
				userDb.put("sex",null);
				userDb.put("unionid",null);
				usersDb.add(userDb);
			}
		}
		return Result.ok().setData(usersDb);
	}

	/**
	 * 根据登录账户查询用户列表，一般在登录前查询一次，如果有多个账户，由用户选择一个登录
	 * 支持手机、邮箱、微信等多种方式登陆
	 *
	 * @return
	 */
	@RequestMapping(value = "/user/queryMyUsers",method = RequestMethod.GET)
	@HasRole
	@ResponseBody
	public Result queryMyUsers() {
		User cuser=LoginUtils.getCurrentUserInfo();
		if(LoginUtils.isGuest()){
			return Result.error ("user-0","未登录用户不允许查询");
		}
		List<User> users=this.userQueryService.queryMyUsers(cuser);
		List<User> usersDb=new ArrayList<>();
		if(users!=null && users.size()>0){
			for (User userDb : users) {//去掉敏感数据
				userDb.setPassword(null);
				userDb.setEmail(null);
				userDb.setPhoneno(null);
				userDb.setTpaOpenid(null);
				userDb.put("address",null);
				userDb.put("birthday",null);
				userDb.put("sex",null);
				userDb.put("unionid",null);
				usersDb.add(userDb);
			}
		}
		return Result.ok().setData(usersDb);
	}

	User getUserBaseInfo(User user){
		User userDb=userQueryService.getUserByUserid(user.getUserid(), Collections.emptyMap());
		user.putAll(userDb);
		return user;
	}

	public String getPlatformBranchId(){
		PlatformVo platformVo=platClientService.getPlatformVo();
		return platformVo.getBranchId();
	}

}
