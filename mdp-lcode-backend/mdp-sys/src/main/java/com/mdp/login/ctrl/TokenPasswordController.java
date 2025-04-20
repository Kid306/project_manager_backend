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

import com.mdp.core.SpringUtils;
import com.mdp.core.api.Sequence;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.RequestUtils;
import com.mdp.login.entity.RequestTokenParams;
import com.mdp.login.integration.IntegrationParams;
import com.mdp.login.integration.IntegrationParamsContext;
import com.mdp.login.integration.authenticator.IntegrationAuthenticator;
import com.mdp.login.service.SysUserQueryService;
import com.mdp.login.service.UserDetailsServiceImpl;
import com.mdp.safe.client.dict.AuthType;
import com.mdp.safe.client.dict.GrantType;
import com.mdp.safe.client.dict.UserType;
import com.mdp.safe.client.entity.CommonUserDetails;
import com.mdp.safe.client.entity.SafeAuthority;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.jwt.JwtLocalLoginService;
import com.mdp.safe.client.jwt.JwtTool;
import com.mdp.safe.client.pwd.SafePasswordEncoder;
import com.mdp.safe.client.service.MdpLoginQueueService;
import com.mdp.safe.client.utils.LoginUtils;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */

@Controller
public class TokenPasswordController {

	PasswordEncoder passwordEncoder=new SafePasswordEncoder();

	@Autowired
	JwtLocalLoginService jwtLoginService;


	WebClient webClient=WebClient.create();


	Logger logger= LoggerFactory.getLogger(TokenPasswordController.class);


	@Autowired
	MdpLoginQueueService loginRecordService;

	@Autowired
	Sequence sequenceService;

	@Autowired
	SysUserQueryService userQueryService;


	private Collection<IntegrationAuthenticator> authenticators;
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	/**
	 * 密码登陆
	 * 支持手机、邮箱、微信等多种方式登陆
	 *
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/login/token", params = {"grantType=password"})
	public String clientPasswordGrantWeb(Model model, @RequestParam Map<String,Object> params, RedirectAttributes attributes ) {
		RequestTokenParams requestTokenParams=BaseUtils.fromMap(params,RequestTokenParams.class);
		Map<String,Object> result=this.clientPasswordGrant(params,requestTokenParams);
		Tips tips= (Tips) result.get("tips");
		if(tips.isOk()){
			String redirectUri= (String) params.get("redirectUri");
			OAuth2AccessTokenResponse response= ((OAuth2AccessTokenResponse)(result.get("data")));
			if(StringUtils.hasText(redirectUri)){
				attributes.addAttribute("accessToken",response.getAccessToken().getTokenValue());
				User user= LoginUtils.getCurrentUserInfo();
				attributes.addAttribute("userid",user.getUserid());
				attributes.addAttribute("username",user.getUsername());
				return "redirect:"+redirectUri;
			}else {
				model.addAllAttributes(result);
				return "index";
			}
		}else{
			model.addAllAttributes(result);
			return "";
		}

	}
	/**
	 * 密码登陆
	 * 支持手机、邮箱、微信等多种方式登陆
	 *
	 * @param webparams
	 * @return
	 */
	@PostMapping(value = "/login/token", params = "grantType=password")
	@ResponseBody  public Result clientPasswordGrant(@RequestParam Map<String,Object> webparams , @RequestBody RequestTokenParams paramsVo) {
		try {

			String authType = paramsVo.getAuthType();
			if (!StringUtils.hasText(authType)) {
				authType = "password";
			}
			String userid = paramsVo.getUserid();
			String userloginid = paramsVo.getUserloginid();
			String password = paramsVo.getPassword();

			String userType = (String) webparams.get("userType");

			//设置集成登录信息
			IntegrationParams integrationParams = new IntegrationParams();
			String grantType= (String) webparams.get("grantType");
			if(!StringUtils.hasText(grantType)){
				grantType= GrantType.password.name();
			}
			if(!StringUtils.hasText(authType) && GrantType.password.name().equals(grantType) ){
				authType= AuthType.password.name();
			}

			if (!StringUtils.hasText(userType)) {
				userType= UserType.staff.name();
			}

			integrationParams.setUserloginid(userloginid);
			integrationParams.setAuthType(authType);
			integrationParams.setUserType(userType);

			Map<String,Object> data=webparams;
			data.put("userloginid",userloginid);
			data.put("userType",userType);
			data.put("authType",authType);
			data.put("grantType",grantType);
			data.put("password",password);
			integrationParams.setDatas(data);
			IntegrationParamsContext.set(integrationParams);
			IntegrationAuthenticator authenticator = findIntegrationAuthenticator(integrationParams);
			if(authenticator==null){
				return Result.error("login-method-not-support","不支持当前登录方式");
			}else{
				try{
					//预处理
					authenticator.prepare(integrationParams);
					CommonUserDetails userDetails=(CommonUserDetails)userDetailsService.loadUserByUsername(userloginid);
 					User user=userDetails.getUser();
					Map<String, Object> claims = new HashMap<>();
					claims.put("userInfo", userDetails.getUser().toSimpleString());
					claims.put("roles", SafeAuthority.toRolesString((Collection<GrantedAuthority>) userDetails.getAuthorities()));
					String jwtToken = JwtTool.createJWT(user.getUserid(), claims, 7 * 24 * 60 * 60 * 1000);
					OAuth2AccessTokenResponse response = OAuth2AccessTokenResponse.withToken(jwtToken)
							.tokenType(OAuth2AccessToken.TokenType.BEARER).build();
 					jwtLoginService.doLocalLoginUseJwtToken(response.getAccessToken().getTokenValue());
  					pushLoginRecord(paramsVo, user);
					//后置处理
					authenticator.complete(integrationParams);
					return Result.ok().setData(response).put("userInfo", user).put("roles", userDetails.getAuthorities());

				}finally {
					IntegrationParamsContext.clear();
				}
			}


		}catch (BizException e){
			logger.error("登录错误",e);
			pushLoginErrRecord(paramsVo,e.getMessage());
			return Result.error(e);
		}catch (Exception e1){
			logger.error("获取令牌错误",e1);
			pushLoginErrRecord(paramsVo,"获取令牌错误");
			return Result.error("get-ticket-error","获取令牌错误").put("moreMsg",e1.getMessage());
		}

	}

	private void pushLoginErrRecord(RequestTokenParams paramsVo,String errMsg){
		try {
		HttpServletRequest request=RequestUtils.getRequest();
		String loginIp=RequestUtils.getIpAddr(request);
		Map<String,Object> loginRecord=new HashMap<>();
		String userid=paramsVo.getUserloginid();
		loginRecord.put("reqNo", MDC.get("gloNo"));
		loginRecord.put("loginIp",loginIp);
		loginRecord.put("id",this.sequenceService.getTablePK("login_record","id"));
		loginRecord.put("loginTime",new Date());



		//获取浏览器信息
		String ua = request.getHeader("User-Agent");
		//转成UserAgent对象
		UserAgent userAgent = UserAgent.parseUserAgentString(ua);
		//获取浏览器信息
		Browser browser = userAgent.getBrowser();
		//获取系统信息
		OperatingSystem os = userAgent.getOperatingSystem();
		//系统名称
		String system = os.getName();
		//浏览器名称

		Version borderVersion= browser.getVersion(ua);
		if(borderVersion!=null){
			loginRecord.put("borderVersion",borderVersion.getVersion());
		}
		String browserName = browser.getName();
		loginRecord.put("userAgent",ua);
		loginRecord.put("userid",paramsVo.getUserloginid());
		if("sms".equals(paramsVo.getAuthType())){
			loginRecord.put("phoneno",paramsVo.getUserloginid());
		}
		loginRecord.put("os",os.getName());
		loginRecord.put("borderName",browserName);
		loginRecord.put("browerGroup",browser.getGroup().getName());
		loginRecord.put("renderingEngine",browser.getRenderingEngine().name());
		loginRecord.put("deviceManufacturer",os.getManufacturer().name());
		loginRecord.put("deviceType",os.getDeviceType().name());
		loginRecord.put("loginBranchId", paramsVo.getBranchId());
		loginRecord.put("loginStatus","0");
		loginRecord.put("loginMsg",errMsg);
		loginRecord.put("authType",paramsVo.getAuthType());
		loginRecord.put("grantType",paramsVo.getGrantType());
		loginRecordService.pushLoginRecord(loginRecord);


	}catch (Exception e){

	}
	}

	private void pushLoginRecord(RequestTokenParams paramsVo,User user) {
		try {
		HttpServletRequest request=RequestUtils.getRequest();
		String loginIp=RequestUtils.getIpAddr(request);
		Map<String,Object> loginRecord=new HashMap<>();
		loginRecord.putAll(user);
		loginRecord.put("reqNo", MDC.get("gloNo"));
		loginRecord.put("loginIp",loginIp);
		loginRecord.put("id",this.sequenceService.getTablePK("login_record","id"));
		loginRecord.put("loginTime",new Date());



		//获取浏览器信息
		String ua = request.getHeader("User-Agent");
		//转成UserAgent对象
		UserAgent userAgent = UserAgent.parseUserAgentString(ua);
		//获取浏览器信息
		Browser browser = userAgent.getBrowser();
		//获取系统信息
		OperatingSystem os = userAgent.getOperatingSystem();
		//系统名称
		String system = os.getName();
		//浏览器名称

		Version borderVersion= browser.getVersion(ua);
		if(borderVersion!=null){
			loginRecord.put("borderVersion",borderVersion.getVersion());
		}
		String browserName = browser.getName();
		loginRecord.put("userAgent",ua);
		loginRecord.put("os",os.getName());
		loginRecord.put("borderName",browserName);
		loginRecord.put("browerGroup",browser.getGroup().getName());
		loginRecord.put("renderingEngine",browser.getRenderingEngine().name());
		loginRecord.put("deviceManufacturer",os.getManufacturer().name());
		loginRecord.put("deviceType",os.getDeviceType().name());
		loginRecord.put("loginBranchId",user.getBranchId());
		loginRecord.put("memType",user.getMemType());
		loginRecord.put("loginShopId",user.getShopId());
		loginRecord.put("loginLocationId",user.getLocationId());
		loginRecord.put("loginStatus","1");
		loginRecord.put("loginMsg","成功");
		loginRecord.put("authType",paramsVo.getAuthType());
		loginRecord.put("grantType",paramsVo.getGrantType());
		loginRecordService.pushLoginRecord(loginRecord);


		}catch (Exception e){

		}
	}

	public IntegrationAuthenticator findIntegrationAuthenticator(IntegrationParams integrationParams){
		this.init();
		if (this.authenticators != null) {
			for (IntegrationAuthenticator authenticator : authenticators) {
				if (authenticator.supportAuthType(integrationParams)) {
					return authenticator;
				}
			}
		}
		return null;
	}
	public void init(){
		if(this.authenticators==null){
			Map<String, IntegrationAuthenticator> beans= SpringUtils.getBeansOfType(IntegrationAuthenticator.class);
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxffffffffffffffffffff");

			if(beans!=null){
				this.authenticators=beans.values();
			}else {
				this.authenticators=new HashSet<>();
			}
		}
	}
}
