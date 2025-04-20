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


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdp.core.api.Sequence;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.service.SequenceService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.RequestUtils;
import com.mdp.email.client.service.EmailService;
import com.mdp.login.client.SysClient;
import com.mdp.login.entity.RequestTokenParams;
import com.mdp.login.entity.ValidCode;
import com.mdp.login.entity.ValidCodeVo;
import com.mdp.login.service.SysUserQueryService;
import com.mdp.login.service.UserOperService;
import com.mdp.login.service.ValidCodeService;
import com.mdp.login.util.ValidUtils;
import com.mdp.msg.client.PushNotifyMsgService;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.dict.AuthType;
import com.mdp.safe.client.dict.TpaType;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.service.TpaUserRegisterService;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sms.client.api.SmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */

@Controller
public class UserOperController {

	@Autowired
	UserOperService userOperService;

	@Autowired
	SmsCodeService smsCodeService;

	@Autowired
	SysUserQueryService userQueryService;


	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired(required = false)
	Sequence sequenceService;

	@Autowired
	TokenPasswordController tokenPasswordController;


	@Autowired(required = false)
	List<TpaUserRegisterService> tpaUserRegisterServiceList;
	@Autowired
	EmailService emailService;
	@Autowired
	ValidCodeService validCodeService;

	@Autowired
	SysClient sysClient;



	@Autowired
	PushNotifyMsgService pushNotifyMsgService;


	@Value("${spring.mail.username:}")
	String fromEmail="";
	/**
	 * userType:cust-会员，staff.后台管理员，默认后台管理员
	 * validType:0-不验证，1-邮箱，2-手机号码，3-邮箱、手机号码
	 * codeScene验证场景0-重置密码，1-注册验证，2-更换常用邮箱，3-更换备用邮箱
	 * 用户注册，适用于前端客户手动注册，要求上送短信验证码，手机号码，
	 * userid/displayUserid/username/phoneno/password/email必须
	 *
	 * @return
	 */
	@PostMapping(value = "/user/register")
	@ResponseBody
	public Result register(@RequestBody User params) {
		Tips tips = new Tips("成功");
		/**
		 * 关键要素 账号、手机号码、密码、邮箱不能为空
		 */

		String displayUserid=params.getDisplayUserid();
 		String phoneno=params.getPhoneno();
		String password=params.getPassword();
		String email=params.getEmail();
		String codeEmail=email;
		String username=params.getUsername();
		String userType=params.getUserType();
		String memType=params.getMemType();
		String branchId= (String) params.get("branchId");
		String branchName= (String) params.get("branchName");
		String profeId= (String) params.get("profeId");
		String profeName= (String) params.get("profeName");
		String userid="";
		if(!StringUtils.hasText(memType)){
			memType="0";
		}
		if(sequenceService==null){
			sequenceService=new SequenceService();
		}
		userid=sequenceService.getUserid();
		if(!StringUtils.hasText(branchId)){
			branchId=userid;
		}
		if(!StringUtils.hasText(branchName)){
			branchName=username;
		}
 		String validType= (String) params.get("validType");
		if(!StringUtils.hasText(validType)){
			if(StringUtils.hasText(userType)){
				if(userType.equals("cust")){
					validType="1";
				}
			}else{
				validType="2";
			}
		}
		if(StringUtils.hasText(email)){
			 tips= ValidUtils.validEmail(email);
		}

		if(!StringUtils.hasText(userType)){
			 userType="2";
			 params.setUserType(userType);
		}
		if(!StringUtils.hasText(displayUserid)){
			tips.setErrMsg("userid-not-allow-null","用户编号不能为空");
		}

		if("2".equals(validType)||"3".equals(validType)) {
			if (!StringUtils.hasText(phoneno)) {
				tips.setErrMsg("phoneno-not-allow-null","手机号码不能为空");
			}
		}

		if("1".equals(validType)||"3".equals(validType)){
			if(!StringUtils.hasText(email)){
				tips.setErrMsg("email-not-allow-null","邮箱不能为空");
			}
		}
		if(!StringUtils.hasText(password)){
			tips.setErrMsg("password-not-allow-null","密码不能为空");
		}else if(password.length()<=10){
			tips.setErrMsg("password-not-encode","密码必须为加密后传输");
		}
		if(!StringUtils.hasText(username)){
			username=displayUserid;
			params.setUsername(username);
			//tips.setErrMsg("用户名称不能为空");
		}
		params.put("memType",memType);
		if(tips.isOk()){
			//User userDb=userQueryService.getUserByUserid(userid,params);
			List<User> usersDb=userQueryService.queryByUserloginid(displayUserid,"displayUserid",Collections.emptyMap());
			if( usersDb != null && usersDb.size()>0 ){
				tips.setErrMsg("userid-had-exists","账户已存在");
			}
		}
		if(tips.isOk()){
			if(StringUtils.hasText(phoneno)){
				List<User> usersDb=userQueryService.queryByUserloginid(phoneno,"phoneno",params);
				if( usersDb != null && usersDb.size()>0 ){
					tips.setErrMsg("phoneno-had-exists","手机号码已存在");
				}
				if(tips.isOk() && (validType.equals("2")||validType.equals("3"))){
					String smsCode= (String) params.get("smsCode");
					if(StringUtils.hasText(smsCode)){
						tips=smsCodeService.validateSmsCodeUseCache(phoneno,smsCode,"register");
					}else {
						tips.setErrMsg("valid-code-not-allow-null","短信验证码不能为空");
					}
				}
			}
		}
		if(tips.isOk()){
			if(StringUtils.hasText(email)){
				List<User> usersDb=userQueryService.queryByUserloginid(email,"email",params);

 				if( usersDb != null && usersDb.size()>0){
					tips.setErrMsg("email-had-exists","邮箱号码已存在");
				}
			}
		}


		if(tips.isOk()){


			if("cust".equals(userType)){
				if(!validType.equals("0")){
					params.put("locked","1");//先锁定，邮箱验证通过后再解锁
					params.put("lockType","0");
					params.put("lockRemark","");
				}else{
					params.put("locked","0");
				}

			}
			params.put("userType",userType);
			password=passwordEncoder.encode(password);
			params.put("password",password);
			params.put("ctime",new Date());
			params.put("memType",memType);
			params.put("branchName",branchName);
			params.put("branchId",branchId);
			params.put("profeId",profeId);
			params.put("profeName",profeName);
			params.put("gradeId","mg_001");
			params.put("gradeName","一星学徒");
			params.put("ilvlId","1");
			params.put("ilvlName","普通");
			params.put("guardId","0");
			params.put("open","1");
			params.put("userid",userid);

			User user=userOperService.userRegister(params);
			if("1".equals(validType) && StringUtils.hasText(email)){

				String valiCode=createValiCode();//生成一个随机码
				HttpServletRequest request= RequestUtils.getRequest();
				String callbackUri = (String)params.get("callbackUri");
				if(!StringUtils.hasText(callbackUri)){
					callbackUri = request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
					String requestUri=request.getRequestURI();
					requestUri=requestUri.substring(0,requestUri.indexOf("register"));
					callbackUri=callbackUri+requestUri+"validEmailCode";
					String queryString=request.getQueryString();
					if(StringUtils.hasText(queryString)){
						callbackUri=callbackUri+"?"+queryString;
					}
				}
				if(callbackUri.indexOf("?")>=0){
					callbackUri=callbackUri+"&valiCode="+valiCode+"&codeEmail="+codeEmail;
				}else{
					callbackUri=callbackUri+"?valiCode="+valiCode+"&codeEmail="+codeEmail;
				}
				String text="尊敬的会员您好，请点击以下链接进行验证邮箱<a href='"+callbackUri+"'>去验证邮箱</a>";
				emailService.sendSimpleMail("验证邮箱",fromEmail,email,text);
				ValidCodeVo validCodeVo=new ValidCodeVo();
				validCodeVo.setUserid(user.getUserid());
				validCodeVo.setValiCode(valiCode);
				validCodeVo.setCodeEmail(codeEmail);
				validCodeVo.setCodeSendTime(new Date());
				validCodeVo.setCodeScene("1");
				validCodeVo.setUserType(userType);
				validCodeVo.setId(validCodeService.createKey("id"));
				validCodeService.insert(validCodeVo);
			}
			tips.setOkMsg("register-ok","注册成功");
		}
		return Result.ok().setTips(LangTips.fromTips(tips));
	}

	@PostMapping(value = "/user/unregister")
	@ResponseBody
	public Result register() { 
		User user=LoginUtils.getCurrentUserInfo();
		if(LoginUtils.isGuest()){
			return Result.error("guest-no-need-register","游客无须注销");
		}
		this.userOperService.userUnregister(user);
		pushNotifyMsgService.pushMsg("platform-branch-001","superAdmin","平台管理员",user.getUserid(),user.getUsername(),user.getUsername()+"账户注销申请，请审核",null);
 		return Result.ok();
	}
	/**
	 * 绑定主账户
	 * @return
	 */
	@PostMapping(value = "/user/bindMainAccount")
	@HasRole
	@ResponseBody
	public Result bindMainAccount(@RequestBody User params) {
 		User user=LoginUtils.getCurrentUserInfo();
 		if(LoginUtils.isGuest()){
 			return Result.error("no-login","未登录不允许绑定");
		}
 		if(!StringUtils.hasText(params.getUserid())){
			return Result.error("userid-0","请上送账户编号");
		}
 		if(!StringUtils.hasText(user.getUnionid())){
			return Result.error("unionid-0","当前账户没有主账户编号，不允许绑定");
		}
		if(!"1".equals(user.getAtype())){
			return Result.error("atype-0","当前账户不是主账户，不允许绑定");
		}
		params=this.userQueryService.getUserByUserid(params.getUserid(),Collections.emptyMap());
		if(params==null){
			return Result.error("user-0","账户不存在", (Object) "1");
		}
		if("1".equals(params.getAtype())){
			return Result.error("atype-1","【%】属于主账户，不允许绑定其它主账户", (Object) params.getUsername());
		}
		User userUpdate=new User();
		userUpdate.setUserid(params.getUserid());
		userUpdate.setUnionid(user.getUnionid());
		this.userOperService.userUpdate(userUpdate);
		sysClient.pushUserCreditScore(params.getUserid(),"6",params.getUserid(),"微信认证");
		return Result.ok();
	}
	/**
	 * 第三方登录注册用户，必须上送 tpaType/tpaOpenid
	 * 最好上送 phoneno/userid,如果没有上送userid将自动生成
	 * @return
	 */
	@PostMapping(value = "/user/register/tpa")
	@ResponseBody
	public Result registerByTpa(@RequestBody User params) {
		if(this.tpaUserRegisterServiceList==null || this.tpaUserRegisterServiceList.size()==0){
			return Result.error("tpa-register-not-foud","未找到第三方登录注册器");
		}
		TpaType tpaType=TpaType.valueOf((String) params.get("tpaType"));
		TpaUserRegisterService tpaUserRegisterService=null;
			for (TpaUserRegisterService tpaService : this.tpaUserRegisterServiceList) {
				if(tpaService.supportTpa(tpaType)){
					tpaUserRegisterService=tpaService;
					break;
				}
			}
		if(tpaUserRegisterService==null){
			return Result.error("tpa-register-not-foud","未找到第三方登录注册器");
		}
		User user=tpaUserRegisterService.register(params);
		return Result.ok();
	}

	/**
	 * 注册时，用户输入完整账号后，检查账号是否已存在
	 *
	 * @return
	 */
	@PostMapping(value = "/user/check/displayUserid")
	@ResponseBody
	public Result checkDisplayUserid(@RequestBody User params) {
		if(StringUtils.hasText(params.getDisplayUserid())){
			List<User> usersDb=userQueryService.queryByUserloginid(params.getDisplayUserid(),"displayUserid",Collections.emptyMap());
			if( usersDb != null && usersDb.size()>0 ){
				return Result.error("displayUserid-exists","账户已存在");
			}
		}else{
			return Result.error("displayUserid-not-allow-empty","账户不能为空");
		}
		return Result.ok();
	}
	/**
	 * 注册时，用户输入完整账号后，检查账号是否已存在
	 *
	 * @return
	 */
	@PostMapping(value = "/user/check/userid")
	@ResponseBody
	public Result checkUserid(@RequestBody User params) {
		if(StringUtils.hasText(params.getUserid())){
			User userDb=userQueryService.getUserByUserid(params.getUserid(),Collections.emptyMap());
			if( userDb != null ){
				return Result.error("userid-exists","账户已存在");

			}
		}else{
			return Result.error("userid-not-allow-empty","账户不能为空");
		}
		return Result.ok();
	}
	/**
	 * 注册时，在用户输入完整手机号码后，自动发起检查手机号码是否已存在
	 * 已存在则不允许注册，可以选则手机验证码登录、密码登录等
	 * @return
	 */
	@PostMapping(value = "/user/check/phoneno")
	@ResponseBody
	public Result checkPhoneno(@RequestBody User params) {
		String phoneno=params.getPhoneno();
		if(!StringUtils.hasText(phoneno)){
			return Result.error("phoneno-not-allow-empty","手机号码不能为空");
		}

		Tips tips= ValidUtils.validPhoneno(phoneno);

		if(tips.isOk()==false){
			 return Result.error(LangTips.fromTips(tips));
		}
		List<User> usersDb=userQueryService.queryByUserloginid(phoneno,"phoneno",params);
		if( usersDb != null && usersDb.size()>0 ){
			return Result.error("phoneno-exists","手机号码已存在");

		}
		return Result.ok();
	}
	/**
	 * 注册时，在用户输入完整手机号码后，自动发起检查手机号码是否已存在
	 * 已存在则不允许注册，可以选则手机验证码登录、密码登录等
	 * @return
	 */
	@PostMapping(value = "/user/check/email")
	@ResponseBody
	public Result checkEmail(@RequestBody User params) {
		String email=params.getEmail();
		if(!StringUtils.hasText(email)){
			return Result.error("email-not-allow-empty","邮箱不能为空");
		}

		Tips tips=ValidUtils.validEmail(email);
		if(tips.isOk()==false){
			return Result.error(LangTips.fromTips(tips));
		}
		try {
			List<User> usersDb = userQueryService.queryByUserloginid(email,"email", params);
			if( usersDb != null && usersDb.size()>0 ){
				return Result.error("email-exists","邮箱已存在");
			}
		}catch (Exception e){
			return Result.error(e);
		}
		return Result.ok();
	}
    /**
     * 修改用户的基本信息，不允许修改密码
	 * @param params
     * @return
     */
	@PostMapping(value = "/user/update")
	@ResponseBody
 	public Result userUpdate(@RequestBody User params) {
		/**
		 * 手机号码、邮件账号、密码不允许修改
		 */

		if(LoginUtils.isGuest()){
			return Result.error("user-not-login","未登录用户");
		}
		User user=LoginUtils.getCurrentUserInfo();
		params.put("locked",null);
		params.put("orgId",null);
		params.put("lockType",null);
		params.put("memType",null);
		params.put("displayUserid",null);
		params.setUserid(LoginUtils.getCurrentUserInfo().getUserid());
		params.setPhoneno(null);
		params.setEmail(null);
		params.setPassword(null);
		userOperService.userUpdate(params);
		return Result.ok();
	}


	/**
	 * 修改用户的手机号码
	 * @param params
	 * @return
	 */
	@PostMapping(value = "/user/update/phoneno")
	@ResponseBody
	public Result userUpdatePhoneno(@RequestBody User params) {
		/**
		 * 手机号码、邮件账号、密码不允许修改
		 */

		if(LoginUtils.isGuest()){
			return Result.error("user-not-login","未登录用户");
		}
		User user=LoginUtils.getCurrentUserInfo();
		params.setUserid(user.getUserid());
		User userdb=this.userQueryService.getUserByUserid(user.getUserid(),Collections.emptyMap());
		if(userdb==null){
			return Result.error("user-0","用户不存在");
		}
		if(StringUtils.hasText(userdb.getPhoneno()) && userdb.getPhoneno().equals(params.getPhoneno())){
			return Result.error("phoneno-same","手机号已经绑定当前账号，无须重复绑定");
		}
		Tips tips2=smsCodeService.validateSmsCodeUseCache(params.getPhoneno(), (String) params.get("smsCode"),"userPhoneno");
		if(tips2.isOk()==false){
			return Result.error(LangTips.fromTips(tips2));
		}
		if("1".equals(userdb.getAtype())){

		}
		List<User> users=this.userQueryService.queryMyUsers(userdb);
		userdb.setPhoneno(params.getPhoneno());
		userdb.setUserType(user.getUserType());
		String validLvls=userdb.getValidLvls();
		if(!StringUtils.hasText(validLvls)){
			validLvls="0,1,0,0,0";
		}else{
			String[] validLvlss=validLvls.split(",");
			validLvlss[1]="1";
			validLvls= Arrays.stream(validLvlss).collect(Collectors.joining(","));
		}
		userdb.setValidLvls(validLvls);
		userOperService.userUpdatePhoneno(userdb);
		sysClient.pushUserCreditScore(userdb.getUserid(),"4",userdb.getPhoneno(),"手机号验证");
		return Result.ok();
	}

    /**
     * 密码方式重置密码
	 * @param params
     * @return
     */
	@PostMapping(value = "/user/password/reset",params = {"type=password"})
	@ResponseBody
 	public Result updatePassword(@RequestBody Map<String,Object> params) {
		User user= LoginUtils.getCurrentUserInfo();
		String userid=user.getUserid();
		String oldPassword= (String) params.get("oldPassword");
		String newPassword= (String) params.get("newPassword");

		if(!StringUtils.hasText(oldPassword)){
			return Result.error("old-password-not-allow-empty","旧密码不能为空");
		}
		if(!StringUtils.hasText(userid)){
			return Result.error("user-not-login","用户未登录");
		}
		if(!StringUtils.hasText(newPassword)){
			return Result.error("new-password-not-allow-empty","新密码不能为空");
		}
		params.put("userType",user.getUserType());
		params.put("memType",user.getMemType());
		params.put("userid",userid);
		if(oldPassword.equals(newPassword)){
			return Result.error("new-and-old-not-allow-same","新旧密码不能一样");
		}
			User userDb=this.userQueryService.getUserByUserid(userid,Collections.emptyMap());
			if(userDb==null){
				return Result.error("userid-not-exists","账户不存在");
			}else {
				if(!passwordEncoder.matches(oldPassword,userDb.getPassword())){
					return Result.error("old-password-not-match","旧密码不正确");
				};
			}
			userOperService.updatePassword(userid,oldPassword,passwordEncoder.encode(newPassword),params);

		return Result.ok();
	}

    /**
     * 短信验证码方式重置密码
	 * @param params
     * @return
     */
	@PostMapping(value = "/user/password/reset",params = {"type=sms"})
	@ResponseBody
 	public Result resetPasswordByPhoneno(@RequestBody Map<String,Object> params) {

		String userid= (String) params.get("userid");
		String phoneno= (String) params.get("phoneno");
		String smsCode= (String) params.get("smsCode");
		String newPassword= (String) params.get("newPassword");

		if(!StringUtils.hasText(phoneno)){
			return Result.error("phoneno-not-allow-empty","手机号码不能为空");
		}
		Result.assertIsFalse(ValidUtils.validPhoneno(phoneno));
		if(!StringUtils.hasText(smsCode)){
			return Result.error("valid-code-not-allow-empty","短信验证码不能为空");
		}
		if(!StringUtils.hasText(newPassword)){
			return Result.error("new-password-not-allow-empty","新密码不能为空");
		}
		Result.assertIsFalse(smsCodeService.validateSmsCodeUseCache(phoneno,smsCode,"changePassword"));
		String dbUserid=userid;
			List<User> users=this.userQueryService.queryByUserloginid(phoneno,"phoneno",Collections.emptyMap());
			if(users==null||users.size()==0){
				return Result.error("phoneno-err","该手机注册的账户不存在");
			}
			for (User user : users) {
				userOperService.resetPasswordByPhoneno(user.getUserid(),phoneno,smsCode,passwordEncoder.encode(newPassword),params);
			}
			smsCodeService.clearSmsCode(phoneno,smsCode,"changePassword");
		return Result.ok();
	}

    /**
     * 邮件方式重置密码,提交密码保存
	 * @param params
     * @return
     */
	@PostMapping(value = "/user/password/reset",params = {"type=email"})
	@ResponseBody
 	public Result resetPasswordByEmail(@RequestBody Map<String,Object> params) {
		String userid= (String) params.get("userid");
		String valiCode= (String) params.get("valiCode");
		String newPassword= (String) params.get("newPassword");
		String userType= (String) params.get("userType");
		if(!StringUtils.hasText(userType)){
			userType="staff";
			params.put("userType",userType);
		}

		if(!StringUtils.hasText(valiCode)){
			return Result.error("email-valid-code-not-allow-empty","邮件验证码不能为空");

		}
		if(!StringUtils.hasText(newPassword)){
			return Result.error("new-password-not-allow-empty","新密码不能为空");
		}
		List<User> users = this.userQueryService.getUserByEmailCode(userid,"",valiCode,userType);
 		if(users!=null && users.size()>0){
			for (User user : users) {
				userOperService.resetPasswordByEmail(user.getUserid(),"",valiCode,passwordEncoder.encode(newPassword),params);
			}
		}else{
			Result.error("valid-code-not-match","验证码不正确");
		}

		return Result.ok();
	}

    /**
     * 发送重置密码的邮件到用户的邮箱
	 * 验证场景0-重置密码，1-注册验证，2-更换常用邮箱第一步验证旧邮箱,，3-更换常用邮箱第二步验证新邮箱，5-更换备用邮箱验证新邮箱，
	 * post
	 * @param params  {email:'邮箱号码',callbackUri:'回调地址',userType:'用户类型 cust|staff',}
     * @return
     */
	@PostMapping(value = "/user/sendEmail")
	@ResponseBody
	public Result sendEmail(@RequestBody Map<String,Object> params) {
		String codeEmail= (String) params.get("codeEmail");

		String codeScene= (String) params.get("codeScene");
		String userType= (String) params.get("userType");
		String callbackUri= (String) params.get("callbackUri");
		if(!StringUtils.hasText(userType)){
			return Result.error("user-type-not-allow-empty","请上送客户类型参数userType");
		}
		if(!StringUtils.hasText(codeScene)){
			return Result.error("valid-code-not-allow-empty","请上送验证码场景");
		}
		if(!StringUtils.hasText(callbackUri)){
			return Result.error("callback-uri-not-allow-empty","请上送回调请求地址callbackUri");
		}
		if(StringUtils.hasText(codeEmail)) {
			Tips tips2 = ValidUtils.validEmail(codeEmail);
			Result.assertIsFalse(tips2);
		}

		if(sequenceService==null){
			sequenceService=new SequenceService();
		}
		String valiCode=createValiCode();


		String desc="";
		String goDesc="";
		User user=null;
		User cuser=LoginUtils.getCurrentUserInfo();
		if(codeScene.equals("0")){
			desc="重置密码";
			goDesc="去重置密码";
			List<User> users=this.userQueryService.queryByUserloginid(codeEmail,"email",params);
			if(users==null || users.size()==0){
				return Result.error("email-not-exists","邮箱账号不存在");
			}
		}else if(codeScene.equals("1")){
			desc="进行注册邮箱验证";
			goDesc="去验证注册邮箱";
			List<User> users=this.userQueryService.queryByUserloginid(codeEmail,"email",params);
			if(users!=null && users.size()>0){
				return Result.error("email-had-exists","邮箱账号已存在");
			}
		}else if(codeScene.equals("2")){
			desc="更换常用邮箱验证";
			goDesc="去验证原常用邮箱";
			user=this.userQueryService.getUserByUserid(cuser.getUserid(),params);
			if(user==null || !StringUtils.hasText(user.getEmail())  ){
				return Result.error("email-not-exists","邮箱账号不存在");

			}else if (!codeEmail.equals(user.getEmail())){
				return Result.error("email-not-right","邮箱账号不正确");

			}
			codeEmail=user.getEmail();
		}else if(codeScene.equals("3")){
			desc="验证新的常用邮箱";
			goDesc="去验证新的常用邮箱";
			user=this.userQueryService.getUserByUserid(cuser.getUserid(),params);
			if(user!=null && codeEmail.equals(user.getEmail())){
				return Result.error("email-had-exists","邮箱账号已存在");
			}
		}else if(codeScene.equals("5")){
			desc="验证新的备用邮箱";
			goDesc="去验证新的备用邮箱";
			user=this.userQueryService.getUserByUserid(cuser.getUserid(),params);
			if(user!=null && codeEmail.equals(user.getEmail())){
				return Result.error("email-had-exists","邮箱账号已存在");
			}
		}
		String text="尊敬的会员您好，唛盟平台欢迎您，请点击以下链接%s <a href='%s'>%s</a>";
		HttpServletRequest request= RequestUtils.getRequest();
		if(callbackUri.indexOf("?")>=0){
			callbackUri=callbackUri+"&valiCode="+valiCode+"&codeEmail="+codeEmail;
		}else{
			callbackUri=callbackUri+"?valiCode="+valiCode+"&codeEmail="+codeEmail;
		}
		text=String.format(text,desc,callbackUri,goDesc);
		emailService.sendSimpleMail(desc,fromEmail,codeEmail,text);
		ValidCodeVo validCodeVo=new ValidCodeVo();
		if(user!=null){
			validCodeVo.setUserid(user.getUserid());
		}else{
			if(cuser!=null){
				validCodeVo.setUserid(cuser.getUserid());
			}else{
				cuser=LoginUtils.getCurrentUserInfo();
				validCodeVo.setUserid(cuser.getUserid());
			}
		}
		validCodeVo.setValiCode(valiCode);
		validCodeVo.setCodeEmail(codeEmail);
		validCodeVo.setCodeSendTime(new Date());
		validCodeVo.setCodeScene(codeScene);
		validCodeVo.setUserType(userType);
		validCodeVo.setId(validCodeService.createKey("id"));
		validCodeService.insert(validCodeVo);
		Map<String,Object> data=new HashMap<>();
		data.put("codeEmail",codeEmail);
		return Result.ok().setData(data);
	}

	/**
	 * 验证验证码是否正确
	 *
	 * @param params {valiCode:'验证码',userType:'staff|cust'}
	 * @return
	 */
	@GetMapping(value = "/user/validEmailCode")
	@ResponseBody
	public Result validEmailCode(@RequestParam Map<String,Object> params) {
 		Tips tips = new Tips("成功");
		String valiCode= (String) params.get("valiCode");
		if(!StringUtils.hasText(valiCode)){
			return Result.error("valid-code-not-allow-empty","验证码不能为空");

		}
		String userType= (String) params.get("userType");
		if(!StringUtils.hasText(valiCode)){
			return Result.error("user-type-not-allow-empty","用户类型不能为空");

		}
		ValidCodeVo vcVo=new ValidCodeVo();
		QueryWrapper<ValidCode> qw=new QueryWrapper<>();
		qw.eq("vali_code",valiCode);
		List<ValidCode> vos=  this.validCodeService.list(qw);
		if(vos==null||vos.size()==0){
			return Result.error("valid-code-not-exists","验证码不存在");

		}
		ValidCodeVo vc=BaseUtils.fromMap(BaseUtils.toMap(vos.get(0)),ValidCodeVo.class);
		vc.setUserType(userType);
		if(vos.get(0).getCodeValidTime()!=null){
			return Result.error("valid-code-had-used","验证码已使用");
		}

		if("0".equals(vc.getCodeScene())){//重置密码
			ValidCodeVo validCodeVoUpdate=new ValidCodeVo();
			validCodeVoUpdate.setUserType(userType);
			validCodeVoUpdate.setId(vc.getId());
			validCodeVoUpdate.setCodeValidTime(new Date());
			this.validCodeService.updateSomeFieldByPk(validCodeVoUpdate);
		} else if("1".equals(vc.getCodeScene())){//如果时注册验证
			User user = new User();
			user.setUserid(vc.getUserid());
			user.setUserType(userType);
			user.put("locked","0");
			user.setEmail(vc.getCodeEmail());
			User userdb=this.userQueryService.getUserByUserid(vc.getUserid(), BaseUtils.map("userType",userType));
			String validLvls=userdb.getValidLvls();
			if(!StringUtils.hasText(validLvls)){
				validLvls="0,0,1,0,0";
			}else{
				String[] validLvlss=validLvls.split(",");
				validLvlss[2]="1";
				validLvls= Arrays.stream(validLvlss).collect(Collectors.joining(","));
				user.setValidLvls(validLvls);
			}
			this.userOperService.userUpdate(user);
			this.sysClient.pushUserCreditScore(userdb.getUserid(),"5",userdb.getEmail(),"邮箱验证");
			//Map<String,Object> org = new HashMap<>();
			//org.put("orgUserid",vc.getUserid());
			//org.put("email",vc.getCodeEmail());
			//this.orgService.updateOrgEmail(org);
			//自动登录
			Map<String,Object> webParams=new HashMap<>();
			webParams.put("userType",userType);
			RequestTokenParams requestTokenParams=new RequestTokenParams();
			requestTokenParams.setUserloginid(user.getEmail());
			requestTokenParams.setUserid(user.getUserid());
			requestTokenParams.setAuthType(AuthType.email.name());
			requestTokenParams.setPassword(valiCode);
			Result result=tokenPasswordController.clientPasswordGrant(webParams,requestTokenParams);
			return result;
		}else if("2".equals(vc.getCodeScene())){//更换常用邮箱第一步，
			ValidCodeVo validCodeVoUpdate=new ValidCodeVo();
			validCodeVoUpdate.setUserType(userType);
			validCodeVoUpdate.setId(vc.getId());
			validCodeVoUpdate.setCodeValidTime(new Date());
			this.validCodeService.updateSomeFieldByPk(validCodeVoUpdate);
		}else if("3".equals(vc.getCodeScene())){//更换常用邮箱第二步，需要把常用邮箱更新为新邮箱
			ValidCodeVo validCodeVoUpdate=new ValidCodeVo();
			validCodeVoUpdate.setUserType(userType);
			validCodeVoUpdate.setId(vc.getId());
			validCodeVoUpdate.setCodeValidTime(new Date());
			this.validCodeService.updateSomeFieldByPk(validCodeVoUpdate);
			User user = new User();
			user.setUserType(userType);
			user.setUserid(vc.getUserid());
			user.setEmail(vc.getCodeEmail());
			User userdb=this.userQueryService.getUserByUserid(vc.getUserid(), BaseUtils.map("userType",userType));
			String validLvls=userdb.getValidLvls();
			if(!StringUtils.hasText(validLvls)){
				validLvls="0,0,1,0,0";
			}else{
				String[] validLvlss=validLvls.split(",");
				validLvlss[2]="1";
				validLvls= Arrays.stream(validLvlss).collect(Collectors.joining(","));
				user.setValidLvls(validLvls);
			}


			this.userOperService.userUpdate(user);

			this.sysClient.pushUserCreditScore(userdb.getUserid(),"5",userdb.getEmail(),"邮箱验证");

			//Map<String,Object> org = new HashMap<>();
			//org.put("orgUserid",vc.getUserid());
			//org.put("email",vc.getCodeEmail());
			//this.orgService.updateOrgEmail(org);
		}else if("5".equals(vc.getCodeScene())){//更换备用邮箱第2步，需要把备用邮箱更新成新邮箱
			ValidCodeVo validCodeVoUpdate=new ValidCodeVo();
			validCodeVoUpdate.setUserType(userType);
			validCodeVoUpdate.setId(vc.getId());
			validCodeVoUpdate.setCodeValidTime(new Date());
			this.validCodeService.updateSomeFieldByPk(validCodeVoUpdate);
			User user = new User();
			user.setUserid(vc.getUserid());
			user.setUserType(userType);
			user.put("emailBak",vc.getCodeEmail());
 			this.userOperService.userUpdate(user);
			//Map<String,Object> org = new HashMap<>();
			//org.put("orgUserid",vc.getUserid());
			//org.put("emailBak",vc.getCodeEmail());
			//this.orgService.updateOrgEmail(org);
		}
		return Result.ok();
	}

	public String createValiCode(){
		if(sequenceService==null){
			sequenceService=new SequenceService();
		}
		String valiCode=sequenceService.getCommonNo("{date62:yyyyMMddHHmm}{rands:25}");//生成一个随机码

		return valiCode.toUpperCase(Locale.ROOT);
	}
}
