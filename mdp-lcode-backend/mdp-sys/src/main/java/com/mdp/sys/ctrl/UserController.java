package com.mdp.sys.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.Result;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.service.SequenceService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.email.client.service.EmailAsyncService;
import com.mdp.qx.DeptFilter;
import com.mdp.qx.HasQx;
import com.mdp.qx.HasRole;
import com.mdp.safe.client.pwd.SafePasswordEncoder;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.swagger.ApiEntityParams;
import com.mdp.sys.entity.*;
import com.mdp.sys.queue.SysUserPushService;
import com.mdp.sys.service.BranchInterestsService;
import com.mdp.sys.service.DeptService;
import com.mdp.sys.service.UserDeptService;
import com.mdp.sys.service.UserService;
import com.mdp.sys.vo.BatchResetPassword;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
import java.util.stream.Collectors;

import static com.mdp.core.utils.BaseUtils.fromMap;
import static com.mdp.core.utils.BaseUtils.toMap;


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
@RestController("mdp.sys.userController")
@RequestMapping(value="/*/sys/user")
@Api(tags={"sys_user操作接口"})
public class UserController {

	static Log logger=LogFactory.getLog(UserController.class);

	Map<String,Object> fieldsMap = toMap(new User());


	@Autowired
	SysUserPushService sysUserPushService;

	@Autowired
	private UserService userService;

	@Autowired
	DeptService deptService;

	PasswordEncoder passwordEncoder=new SafePasswordEncoder();

	@Autowired
	BranchInterestsService branchInterestsService;

	@Autowired
	EmailAsyncService emailService;

	@Value("${spring.mail.username:}")
	String fromEmail="";

	@Autowired
	UserDeptService userDeptService;

	@Autowired
	SequenceService sequenceService;

	@ApiOperation( value = "查询sys_user信息列表",notes="listUser,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
 	@ApiEntityParams(User.class)
	@ApiResponses({
			@ApiResponse(code = 200,response= User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@HasRole
	@DeptFilter(rejectOnDeptidIsNull = false)
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result listUser( @ApiIgnore @RequestParam Map<String,Object> params){
		
		
		IPage page=QueryTools.initPage(params);
 		if(LoginUtils.isGuest()){
			return Result.error("guest-no-qx","您无权访问用户数据");
		}
		QueryWrapper<User> qw= QueryTools.initQueryWrapper(User.class,params);
 		qw.eq("branch_id",LoginUtils.getCurrentUserInfo().getBranchId());
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
	@ApiOperation( value = "查询用户列表，仅返回最基本的信息、机构编号、用户姓名、头像、性别等",notes="listUser,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiEntityParams(User.class)
	@ApiResponses({
			@ApiResponse(code = 200,response= User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@HasRole
	@RequestMapping(value="/list/simple",method=RequestMethod.GET)
	public Result listSimpleUser(@ApiIgnore @RequestParam Map<String,Object> params){
		
		
		IPage page=QueryTools.initPage(params);
		QueryWrapper<User> qw= QueryTools.initQueryWrapper(User.class,params);
		
		if(LoginUtils.isGuest()){
			Result.error("guest-no-qx","您无权访问用户数据");
		}
		if(!LoginUtils.isSuperAdmin()) {
			params.put("branchId",LoginUtils.getCurrentUserInfo().getBranchId());
		}
		List<Map<String,Object>>	userList = userService.selectListSimpleMapByWhere(page,params);	//列出User列表
		
		return Result.ok().setData(userList); 
	}

	@ApiOperation( value = "查询sys_user信息",notes="detail")
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
		if(LoginUtils.isGuest()){
			Result.error("guest-no-qx","您无权访问用户数据");
		}

		User user=this.userService.selectOneById(userid);
		if(user!=null){
			user.setPhoneno(UserService.phoneEncode(user.getPhoneno()));
			user.setIdCardNo(UserService.idCardEncode(user.getIdCardNo()));
			user.setEmail(UserService.emailEncode(user.getEmail()));
			user.setEmailBak(UserService.emailEncode(user.getEmailBak()));
			user.setPassword("******");
		}

		return Result.ok().setData(user); 
	}

	@ApiOperation( value = "查询sys_user信息",notes="listUser,条件之间是 and关系,模糊查询写法如 {studentName:'%才哥%'}")
	@ApiImplicitParams({
			@ApiImplicitParam(name="userid",value="内部用户编号,主键",required=false),
	})
	@ApiResponses({
			@ApiResponse(code = 200,response= User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'错误码'},pageInfo:{total:总记录数},data:[数据对象1,数据对象2,...]}")
	})
	@HasRole
	@RequestMapping(value="/getMyUserBaseInfo",method=RequestMethod.GET)
	public Result getMyUserBaseInfo( ){
		
		
		User user=this.userService.selectOneObject(new User(LoginUtils.getCurrentUserInfo().getUserid()));
		user.setPassword("******");
		return Result.ok().setData(user); 
	}
	@RequestMapping(value="/listUserNames",method=RequestMethod.GET)
	public Result listUserNames( Map<String,Object> user){
		
		
		
		List<Map<String,Object>>	userList = userService.listUserNames(user);	//列出User列表
		return Result.ok().setData(userList); 
	}



	/***/
	@ApiOperation( value = "新增一条sys_user信息",notes="addUser,主键如果为空，后台自动生成")
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	//@HasQx(value="sys_user_add",name="新增后台用户",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addUser(@RequestBody UserVo userVo) {
		
		
		User user=userVo.getUser();
		UserDept userDept=userVo.getUserDept();
		try{
			com.mdp.safe.client.entity.User cuser=LoginUtils.getCurrentUserInfo();
			if(StringUtils.isEmpty(user.getUserid())) {
				user.setUserid(userService.createKey("userid"));
			} else {
				User userQuery=new User(user.getUserid());
				if(userService.countByWhere(userQuery)>0) {
					return Result.error("用户编码已存在");
				};
			}
			if(ObjectTools.isEmpty(user.getPassword())){
				user.setPassword("e3ceb5881a0a1fdaad01296d7554868d");// 888888
			}
			if(ObjectTools.isNotEmpty(user.getDisplayUserid())){
				User userQuery=new User();
				userQuery.setDisplayUserid(user.getDisplayUserid());
				if(userService.countByWhere(userQuery)>0) {
					return Result.error("用户编码已存在");
				};
			}
			BranchInterests interestsDb=branchInterestsService.selectOneById(cuser.getBranchId());
			if(interestsDb==null){
				interestsDb=branchInterestsService.getDefaultBranchInterests(cuser.getBranchId());
			}
			if(interestsDb.getCurrUsers()!=null && interestsDb.getCurrUsers()>interestsDb.getMaxUsers()){
				return Result.error("max-users-to-small",String.format("当前企业人数超出开通总人数%s人",(interestsDb.getMaxUsers()-interestsDb.getCurrUsers())));
			}
			User p=new User();
			p.setDisplayUserid(user.getDisplayUserid());
			if(userService.countByWhere(p)>0){
				return Result.error("displayUserid-exists","用户账号已经存在");
			};

			if(userDept!=null && StringUtils.hasText(userDept.getDeptid())){

				Dept dept=new Dept();
				dept.setDeptid(userVo.getUserDept().getDeptid());
				Dept deptDb=deptService.selectOneObject(dept);
				if(deptDb==null){
					return Result.error("dept-0","部门不存在");
				}else{
					if(!cuser.getBranchId().equals(deptDb.getBranchId())){
						return Result.error("branchId-err","机构编号不正确");
					}
				}
			}
			user.setBranchId(cuser.getBranchId());
			user.setMemType("2");
			user.setAtype("0");
			user.setOpen("0");
			userVo.setUser(user);
			userService.insert(userVo);
			sysUserPushService.pushUserInfoAfterAdd(BaseUtils.toMap(user));
			return Result.ok().setData(user);
			
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}


	@ApiOperation( value = "批量修改某些字段",notes="")
	@ApiEntityParams( value = User.class, props={ }, remark = "个人信息", paramType = "body" )
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@RequestMapping(value="/editSomeFields",method=RequestMethod.POST)
	public Result editSomeFields( @ApiIgnore @RequestBody Map<String,Object> userMap) {
		
		
		try{
			List<String> ids= (List<String>) userMap.get("$pks");
			if(ids==null || ids.size()==0){
				return Result.error("$pks-0","$pks不能为空");
			}

			Set<String> fields=new HashSet<>();
			fields.add("userid");
			fields.add("guardId");
			fields.add("gradeId");
			fields.add("valiLvl");
			fields.add("ilvlId");
			fields.add("phoneno");
			fields.add("password");
			fields.add("email");
			fields.add("memType");
			fields.add("branchId");
			for (String fieldName : userMap.keySet()) {
				if(fields.contains(fieldName)){
					return Result.error(fieldName+"-no-edit",fieldName+"不允许修改");
				}
			}
			Set<String> fieldKey=userMap.keySet().stream().filter(i-> fieldsMap.containsKey(i)).collect(Collectors.toSet());
			fieldKey=fieldKey.stream().filter(i->!StringUtils.isEmpty(userMap.get(i) )).collect(Collectors.toSet());

			if(fieldKey.size()<=0) {
				return Result.error("fieldKey-0","没有需要更新的字段");
			}
			User user = fromMap(userMap,User.class);
			List<User> usersDb=userService.selectListByIds(ids);
			if(usersDb==null ||usersDb.size()==0){
				return Result.error("data-0","记录已不存在");
			}
			List<User> can=new ArrayList<>();
			List<User> no=new ArrayList<>();
			com.mdp.safe.client.entity.User cuser = LoginUtils.getCurrentUserInfo();
			boolean isBranchAdm=LoginUtils.isBranchAdmin();
			for (User userDb : usersDb) {
				if(!cuser.getUserid().equals(userDb.getUserid()) && !isBranchAdm){
					no.add(userDb);
				}else{
					can.add(userDb);
				}
			}
			if(can.size()>0){
				userMap.put("$pks",can.stream().map(i->i.getUserid()).collect(Collectors.toList()));
				userService.editSomeFields(userMap);
			}
			List<String> msgs=new ArrayList<>();
			if(can.size()>0){
				msgs.add(String.format("成功更新以下%s条数据",can.size()));
			}
			if(no.size()>0){
				msgs.add(String.format("以下%s个数据无权限更新,本人或者机构管理员才有权限更新数据",no.size()));
			}
			if(can.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else {
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
			//return Result.ok().setData(xmMenu);
		}catch (BizException e) {
			logger.error("",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("",e);
			return Result.error(e);
		}
	}

	/***/
	@ApiOperation( value = "设置人员为机构管理员",notes="setUsersToBranchAdm")
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	//@HasQx(value="sys_user_setUsersToBranchAdm",name="设置人员为机构管理员",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/setUsersToBranchAdm",method=RequestMethod.POST)
	public Result setUsersToBranchAdm(@RequestBody List<String> userids) {
		
		
		try{

			com.mdp.safe.client.entity.User cuser=LoginUtils.getCurrentUserInfo();
			if(!LoginUtils.isMainBranchAdmin()){
				return Result.error("main-branch-adm-0","您不是主管理，无权限设置，请用主管理员登录后再设置。");
			}
			List<String> userids2=new ArrayList<>();
			for (String userid : userids) {
				if(LoginUtils.isMainBranchAdmin(userid,cuser.getBranchId())){
					return Result.error("main-branch-adm-0",String.format("【%s】为永久性主管理员，无须调整",userid));
				}else{
					userids2.add(userid);
				}
			}
 				this.userService.setUsersToBranchAdm(cuser.getBranchId(),userids2);
			return Result.ok();

		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}
	@ApiOperation( value = "取消机构管理员资格",notes="setUsersUnBranchAdm")
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	//@HasQx(value="sys_user_setUsersUnBranchAdm",name="取消机构管理员资格",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/setUsersUnBranchAdm",method=RequestMethod.POST)
	public Result setUsersUnBranchAdm(@RequestBody List<String> userids) {
		
		
		try{
			if(userids==null ||   userids.size()<=0){
				return Result.error("userids-0","用户编号不能为空");
			}
			com.mdp.safe.client.entity.User cuser=LoginUtils.getCurrentUserInfo();
			if(!LoginUtils.isMainBranchAdmin()){
				return Result.error("main-branch-adm-0","您不是主管理员，无权限设置，请用主管理员登录后再设置。");
			}
			List<String> userids2=new ArrayList<>();
			for (String userid : userids) {
				if(!LoginUtils.isMainBranchAdmin(userid,cuser.getBranchId())){
					userids2.add(userid);
					//return Result.error("main-branch-adm-0",String.format("【%s】为永久性主管理员，无须调整",userid));
				};
			}
			if(userids2.size()>0){
				this.userService.setUsersUnBranchAdm(cuser.getBranchId(),userids2);
			}
			return Result.ok();

		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}
	/***/
	@ApiOperation( value = "邀请成员加入公司-邮箱邀请",notes="inviteUsersByEmails")
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	@HasQx(value="sys_user_invite_emails",name="邀请成员加入公司-邮箱邀请",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/inviteUsersByEmails",method=RequestMethod.POST)
	public Result inviteUsersByEmails(@RequestBody InviteUsersByEmailsVo inviteEmails) {
		
		
		try{
			if(inviteEmails==null || inviteEmails.getEmails()==null || inviteEmails.getEmails().size()<=0){
				return Result.error("phonenos-0","手机号码不能为空");
			}
			com.mdp.safe.client.entity.User cuser=LoginUtils.getCurrentUserInfo();
			List<User> users=this.userService.selectListByEmailsAndBranchId(inviteEmails.getEmails(),cuser.getBranchId());
			Set<String> emails=inviteEmails.getEmails().stream().filter(i->!users.stream().filter(k->k.getEmail().equals(i)).findAny().isPresent()).collect(Collectors.toSet());
			BranchInterests interestsDb=branchInterestsService.selectOneById(cuser.getBranchId());
			if(interestsDb==null){
				interestsDb=branchInterestsService.getDefaultBranchInterests(cuser.getBranchId());
			}
			if(interestsDb.getCurrUsers()!=null && interestsDb.getCurrUsers()>interestsDb.getMaxUsers()){
				return Result.error("max-users-to-small",String.format("当前企业人数超出开通总人数%s人",(interestsDb.getMaxUsers()-interestsDb.getCurrUsers())));
			}
			if(emails.size()>0 && interestsDb.getCurrUsers()!=null && (interestsDb.getCurrUsers()+emails.size())>interestsDb.getMaxUsers()){
				return Result.error("max-users-to-small",String.format("本次新增人员超出剩余名额，新增失败。当前企业已注册%s人，剩余%s个名额",interestsDb.getCurrUsers(),interestsDb.getMaxUsers()));
			}
			List<User>	 usersAdd=new ArrayList<>();
			List<String> msgs=new ArrayList<>();
			if(emails.size()>0){
				String subject="唛盟平台-加入团队邀请";
				String text="尊敬的用户您好，唛盟平台欢迎您，您在唛盟平台登录账号为【%s】,密码为【%s】,企业为【%s】 <a href='%s'>%s</a>";
				String callbackUri= inviteEmails.getCallbackUri();
				for (String email : emails) {
					if(email.length()>40){
						return Result.error("email-err",String.format("邮箱【%】超长。",email));
					}
					if(!email.contains("@")||email.length()<=2){
						return Result.error("email-err",String.format("邮箱【%】格式不正确。",email));
					}
				}
				for (String email : emails) {

					User sysUser=new User();
					sysUser.setEmail(email);
					sysUser.setUserid(this.userService.createKey("userid"));
					sysUser.setMemType("2");
					sysUser.setAtype("0");
					sysUser.setBranchId(cuser.getBranchId());
					sysUser.setUsername(email);
					sysUser.setDisplayUserid(email);
					String rawPassword=this.sequenceService.getCommonNo("{date62:yyyyMMddHH:mm:ss}");
					String password=this.userService.getNewMd5Password(this.userService.createMd5Password(rawPassword));
					sysUser.setPassword(password);
					sysUser.setLocked("0");
					sysUser.setLockType("1");
					sysUser.setLockRemark("请修改初始密码");
					usersAdd.add(sysUser);
					text=String.format(text,sysUser.getUserid(),rawPassword,cuser.getBranchName(),callbackUri,"登录唛盟平台");
					emailService.sendSimpleMail(subject,fromEmail,email,text);
				}
				this.userService.batchAdd(usersAdd);
				for (User user : usersAdd) {
					sysUserPushService.pushUserInfoAfterAdd(BaseUtils.toMap(user));
				}
				msgs.add(String.format("成功注册并发送通知到%s个邮箱。",emails.size()));
			}
			if(users.size()>0){
				msgs.add(String.format("以下%s个邮箱已在本企业注册账户，将被忽略，被忽略的邮件列表【%s】。",users.size(),users.stream().map(i->i.getEmail()).collect(Collectors.joining(","))));
			}
			if(emails.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}

	/***/
	@ApiOperation( value = "邀请成员加入公司-手机邀请",notes="inviteUsersByPhonenos")
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class,message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	@HasQx(value="sys_user_invite_phonenos",name="邀请成员加入公司-手机邀请",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/inviteUsersByPhonenos",method=RequestMethod.POST)
	public Result inviteUsersByPhonenos(@RequestBody InviteUsersByPhonenosVo invitePhonenos) {
		
		
		try{
			if(invitePhonenos==null || invitePhonenos.getPhonenos()==null || invitePhonenos.getPhonenos().size()<=0){
				return Result.error("phonenos-0","手机号码不能为空");
			}
			com.mdp.safe.client.entity.User cuser=LoginUtils.getCurrentUserInfo();
			List<User> users=this.userService.selectListByPhonenosAndBranchId(invitePhonenos.getPhonenos(),cuser.getBranchId());
			Set<String> phonenos=invitePhonenos.getPhonenos().stream().filter(i->!users.stream().filter(k->k.getPhoneno().equals(i)).findAny().isPresent()).collect(Collectors.toSet());
			BranchInterests interestsDb=branchInterestsService.selectOneById(cuser.getBranchId());
			if(interestsDb==null){
				interestsDb=branchInterestsService.getDefaultBranchInterests(cuser.getBranchId());
			}
			if(interestsDb.getCurrUsers()!=null && interestsDb.getCurrUsers()>interestsDb.getMaxUsers()){
				return Result.error("max-users-to-small",String.format("当前企业人数超出开通总人数%s人",(interestsDb.getMaxUsers()-interestsDb.getCurrUsers())));
			}
			if(phonenos.size()>0 && interestsDb.getCurrUsers()!=null && (interestsDb.getCurrUsers()+phonenos.size())>interestsDb.getMaxUsers()){
				return Result.error("max-users-to-small",String.format("本次新增人员超出剩余名额，新增失败。当前企业已注册%s人，剩余%s个名额",interestsDb.getCurrUsers(),interestsDb.getMaxUsers()));
			}
			List<User>	 usersAdd=new ArrayList<>();
			List<String> msgs=new ArrayList<>();
			if(phonenos.size()>0){
				String password=this.userService.getNewMd5Password(this.userService.createMd5Password("888888"));
				for (String phoneno : phonenos) {
					if(phoneno.length()!=11 ||!isNumber(phoneno)) {
						return Result.error("phoneno-err", String.format("【%s】手机格式不正确", phoneno));
					}
					User sysUser=new User();
					sysUser.setPhoneno(phoneno);
					sysUser.setUserid(this.userService.createKey("userid"));
					sysUser.setMemType("2");
					sysUser.setAtype("0");
					sysUser.setBranchId(cuser.getBranchId());
					sysUser.setUsername(phoneno);
					sysUser.setDisplayUserid(phoneno);
					sysUser.setPassword(password);
					sysUser.setLocked("1");
					sysUser.setLockType("1");
					sysUser.setLockRemark("请修改初始密码");
					usersAdd.add(sysUser);
				}
				this.userService.batchAdd(usersAdd);
				for (User user : usersAdd) {
					sysUserPushService.pushUserInfoAfterAdd(BaseUtils.toMap(user));
				}
				msgs.add(String.format("成功注册%s个手机账户。",phonenos.size()));
			}
			if(users.size()>0){
				msgs.add(String.format("以下%s个手机已在本企业注册账户，将被忽略，被忽略的手机列表【%s】。",users.size(),users.stream().map(i->i.getPhoneno()).collect(Collectors.joining(","))));
			}
			if(phonenos.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}

	public static boolean isNumber(String str) {
		String reg = "^[0-9]+(.[0-9]+)?$";
		return str.matches(reg);
	}
	/***/
	@ApiOperation( value = "删除一条sys_user信息",notes="delUser,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}}")
	})

	@HasQx(value="sys_user_del",name="删除后台用户",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public Result delUser(@RequestBody User user){
		
		
		try{
			if("superAdmin".equals(user.getDisplayUserid()) ||"superAdmin".equals(user.getUserid())) {
				return Result.error("superAdmin-no-del","不能删除超级管理员");
			}else {
				User userDb=this.userService.selectOneById(user.getUserid());
				if(userDb.getUserid().equals(userDb.getBranchId())){
					return Result.error("branchAdmin-no-del","不能删除机构管理员");
				}
				userService.deleteByPk(user);
				branchInterestsService.updateCurrUsersAfterChangeUser(userDb.getBranchId());
				return Result.ok();
			}

		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}

	/***/
	@ApiOperation( value = "重置密码",notes="resetPassword")
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	@HasQx(value="sys_user_batchResetPassword",name="重置密码",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchResetPassword",method=RequestMethod.POST)
	public Result batchResetPassword(@RequestBody BatchResetPassword users) {

		com.mdp.safe.client.entity.User user=LoginUtils.getCurrentUserInfo();
		if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(user.getBranchId()) ){
			return Result.error("no-qx","无权操作,只有机构管理员可以重置密码");
		}
			BranchInterests interestsDb=branchInterestsService.selectOneById(user.getBranchId());
			if(interestsDb==null){
				interestsDb=branchInterestsService.getDefaultBranchInterests(user.getBranchId());
			}
			if(interestsDb.getCurrUsers()!=null && interestsDb.getCurrUsers()>interestsDb.getMaxUsers()){
				return Result.error("max-users-to-small",String.format("当前企业人数超出开通总人数%s人",(interestsDb.getMaxUsers()-interestsDb.getCurrUsers())));
			}

			Tips tips=this.userService.batchResetPassword(users.getUserids(),users.getPassword());
			Result.assertIsFalse(tips);
			return Result.ok();
	}
	/***/
	@ApiOperation( value = "重置密码",notes="resetPassword")
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	@HasQx(value="sys_user_resetPassword",name="重置密码",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public Result resetPassword(@RequestBody User user) {
		
		
		try{
			User userDb=this.userService.selectOneById(user.getUserid());
			if(userDb==null){
				return Result.error("data-0","数据不存在");
			}
			BranchInterests interestsDb=branchInterestsService.selectOneById(userDb.getBranchId());
			if(interestsDb==null){
				interestsDb=branchInterestsService.getDefaultBranchInterests(userDb.getBranchId());
			}
			if(interestsDb.getCurrUsers()!=null && interestsDb.getCurrUsers()>interestsDb.getMaxUsers()){
				return Result.error("max-users-to-small",String.format("当前企业人数超出开通总人数%s人",(interestsDb.getMaxUsers()-interestsDb.getCurrUsers())));
			}
			if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(userDb.getBranchId()) && !userDb.getUserid().equals(LoginUtils.getCurrentUserInfo().getUserid()) ){
				return Result.error("no-qx","无权操作");
			}
			Tips tips=this.userService.resetPassword(user.getUserid(),user.getPassword());
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
	/***/
	@ApiOperation( value = "修改头像",notes="changePassword")
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	@RequestMapping(value="/editHeadimgurl",method=RequestMethod.POST)
	public Result editHeadimgurl(@RequestBody User user) {
		
		
		try{
			if(!StringUtils.hasText(user.getHeadimgurl())){
				return Result.error("headimgurl-is-null","头像不能为空");
			}
			com.mdp.safe.client.entity.User cuser=LoginUtils.getCurrentUserInfo();
			User userUpdate=new User();
			userUpdate.setUserid(cuser.getUserid());
			userUpdate.setHeadimgurl(user.getHeadimgurl());
			this.userService.updateSomeFieldByPk(userUpdate);
			sysUserPushService.pushUserInfoAfterChange(BaseUtils.toMap(userUpdate));
			return Result.ok();
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}
	/***/
	@ApiOperation( value = "修改密码",notes="changePassword")
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	public Result changePassword(@RequestBody Map<String,Object> user) {
		
		
		try{
			com.mdp.safe.client.entity.User cuser=LoginUtils.getCurrentUserInfo();
			User userdb=this.userService.selectOneObjectWithPassword(new User(cuser.getUserid()));
			String oldPassword= (String) user.get("oldPassword");
			String newPassword= (String) user.get("newPassword");
			if(!StringUtils.hasText(oldPassword)){
				return Result.error("old-password-is-null","原密码不能为空");
			}
			if(!StringUtils.hasText(newPassword)){
				return Result.error("password-is-null","新密码不能为空");
			}
			BranchInterests interestsDb=branchInterestsService.selectOneById(userdb.getBranchId());
			if(interestsDb==null){
				interestsDb=branchInterestsService.getDefaultBranchInterests(userdb.getBranchId());
			}
			if(interestsDb.getCurrUsers()!=null && interestsDb.getCurrUsers()>interestsDb.getMaxUsers()){
				return Result.error("max-users-to-small",String.format("当前企业人数超出开通总人数%s人",(interestsDb.getMaxUsers()-interestsDb.getCurrUsers())));
			}
			if(passwordEncoder.matches(oldPassword,userdb.getPassword())){
				newPassword=passwordEncoder.encode(newPassword);
				User userUpdate=new User();
				userUpdate.setUserid(userdb.getUserid());
				userUpdate.setPassword(newPassword);
				userUpdate.setPwdStrong("3");
				this.userService.updateSomeFieldByPk(userUpdate);
				return Result.ok();
			}else{
				return Result.error("old-password-not-right","旧密码不正确");
			}
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}
	/***/
	@ApiOperation( value = "根据主键修改一条sys_user信息",notes="editUser")
	@ApiResponses({
			@ApiResponse(code = 200,response=User.class, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'},data:数据对象}")
	})
	@HasRole
	@HasQx(value="sys_user_edit",name="修改后台用户",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Result editUser(@RequestBody User user) {
		
		
		try{
			User userDb=this.userService.selectOneById(user.getUserid());
			com.mdp.safe.client.entity.User userS=LoginUtils.getCurrentUserInfo();


			if(userDb==null){
				return Result.error("data-0","数据不存在");
			}
			if(!userS.getUserid().equals(user.getUserid()) && !LoginUtils.hasAnyRoles("superAdmin","platformAdmin")){
				if(LoginUtils.isBranchAdmin(userDb.getBranchId())){
					return Result.error("qx-0","您无权限修改，只有机构管理员可以修改");
				}
			}
			BranchInterests interestsDb=branchInterestsService.selectOneById(userDb.getBranchId());
			if(interestsDb==null){
				interestsDb=branchInterestsService.getDefaultBranchInterests(userDb.getBranchId());
			}
			if(interestsDb.getCurrUsers()!=null && interestsDb.getCurrUsers()>interestsDb.getMaxUsers()){
				return Result.error("max-users-to-small",String.format("当前企业人数超出开通总人数%s人",(interestsDb.getMaxUsers()-interestsDb.getCurrUsers())));
			}
			if(!LoginUtils.isSuperAdmin() && !LoginUtils.isBranchAdmin(userDb.getBranchId()) && !LoginUtils.getCurrentUserInfo().getBranchId().equals(userDb.getBranchId())){
				return Result.error("no-qx","无权操作");
			}
			user.setPassword(null);//不允许修改密码
			user.setBranchId(null);
			user.setValidLvls(null);
			user.setPwdStrong(null);
			user.setLocked(null);
			user.setUstatus(null);
			user.setIstatus(null);
			userService.updateSomeFieldByPk(user);
			sysUserPushService.pushUserInfoAfterChange(BaseUtils.toMap(user));
			return Result.ok().setData(user);
		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}




	/***/
	@ApiOperation( value = "根据主键列表批量删除sys_user信息",notes="batchDelUser,仅需要上传主键字段")
	@ApiResponses({
			@ApiResponse(code = 200, message = "{tips:{isOk:true/false,msg:'成功/失败原因',tipscode:'失败时错误码'}")
	})
	@HasQx(value="sys_user_batchDel",name="批量删除后台用户",moduleId = "mdp-sys",moduleName = "组织管理")
	@RequestMapping(value="/batchDel",method=RequestMethod.POST)
	public Result batchDelUser(@RequestBody List<User> users) {
		
		
		try{
			for (User user : users) {
				if("superAdmin".equals(user.getUserid())) {
					return Result.error("不允许删除超级管理员");
				}
			}
			List<User> usersDb=this.userService.selectListByIds(users.stream().map(i->i.getUserid()).collect(Collectors.toList()));
			List<User> canDel=new ArrayList<>();
			List<User> admDel=new ArrayList<>();
			List<User> othOrg=new ArrayList<>();
			String myBranchId=LoginUtils.getCurrentUserInfo().getBranchId();
			boolean isSuperAdminOrPlatformAdmin=LoginUtils.hasAnyRoles("superAdmin","platformAdmin");
			for (User user : usersDb) {
				if(isSuperAdminOrPlatformAdmin){
					canDel.add(user);
				}else if(user.getBranchId().equals(user.getUserid())){
					admDel.add(user);
				}else if(!user.getBranchId().equals(myBranchId)){
					othOrg.add(user);
				}else{
					canDel.add(user);
				}
			}

			List<String> msgs=new ArrayList<>();
			if(canDel.size()>0){
				userService.batchDelete(canDel);
				branchInterestsService.updateCurrUsersAfterChangeUser(myBranchId);
				msgs.add(String.format("成功删除%s个用户。",canDel.size()));
			}
			if(admDel.size()>0){
				msgs.add(String.format("以下%s个用户属于机构管理员，不能删除，【%s】",admDel.size(),admDel.stream().map(i->i.getUsername()).collect(Collectors.joining(","))));
			}
			if(othOrg.size()>0){
				msgs.add(String.format("以下%s个用户属于其它机构管，无权删除，【%s】",othOrg.size(),othOrg.stream().map(i->i.getUsername()).collect(Collectors.joining(","))));
			}
			if(canDel.size()>0){
				return Result.ok(msgs.stream().collect(Collectors.joining()));
			}else{
				return Result.error(msgs.stream().collect(Collectors.joining()));
			}

		}catch (BizException e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}catch (Exception e) {
			logger.error("执行异常",e);
			return Result.error(e);
		}
	}

}
