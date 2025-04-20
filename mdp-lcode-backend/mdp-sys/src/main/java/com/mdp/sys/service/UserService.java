package com.mdp.sys.service;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.Sequence;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.query.QueryTools;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.safe.client.entity.Role;
import com.mdp.safe.client.pwd.SafePasswordEncoder;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.User;
import com.mdp.sys.entity.UserDept;
import com.mdp.sys.entity.UserTpa;
import com.mdp.sys.entity.UserVo;
import com.mdp.sys.mapper.UserMapper;
import com.mdp.sys.queue.SysUserPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br>
 * 
 * 组织 com.qqkj<br>
 * 顶级模块 mdp<br>
 * 大模块 sys<br>
 * 小模块 <br>
 * 表 ADMIN.sys_user sys_user<br>
 * 实体 User<br>
 * 表是指数据库结构中的表,实体是指java类型中的实体类<br>
 * 当前实体所有属性名:<br>
 *	unionid,displayUserid,userid,locked,startdate,nickname,username,deptid,phoneno,password,salt,fingerpassword1,fingerpassword2,fingerpassword3,fingerpassword4,pwdtype,headimgurl,country,city,province,address,sex,enddate;<br>
 * 当前表的所有字段名:<br>
 *	unionid,display_userid,userid,locked,startdate,nickname,username,deptid,phoneno,password,salt,fingerpassword1,fingerpassword2,fingerpassword3,fingerpassword4,pwdtype,headimgurl,country,city,province,address,sex,enddate;<br>
 * 当前主键(包括多主键):<br>
 *	userid;<br>
 ***/
@Service("mdp.sys.userService")
public class UserService extends BaseService<UserMapper,User>{
	
	@Autowired
	UserDeptService userDeptService;
	@Autowired
	SysUserPushService sysUserUpdatePushService;

	@Autowired
	BranchInterestsService branchInterestsService;
	
	PasswordEncoder passwordEncoder=new SafePasswordEncoder();

	@Autowired
	Sequence sequenceService;

	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}

	@Transactional
	public int insert(UserVo userVo) {
		User user=userVo.getUser();
		if(ObjectTools.isEmpty(user.getUserid()) && ObjectTools.isNotEmpty(user.getDisplayUserid())){
			user.setUserid(user.getDisplayUserid());
		}

		user.setPassword(this.getNewMd5Password(user.getPassword()));
		UserDept userDept=userVo.getUserDept();
		if(userDept!=null && ObjectTools.isNotEmpty(userDept.getDeptid())){
			userDept.setUserid(user.getUserid());
			userDept.setEnabled("1");
			userDeptService.insert(userDept);
		}

		int i=super.insert(user);
		branchInterestsService.updateCurrUsersAfterChangeUser(user.getBranchId());
		return i;
	}



	public Tips resetPassword(String userid, String password){
		
		User userToUpdate=new User();
		userToUpdate.setUserid(userid);
		userToUpdate.setPassword(passwordEncoder.encode(password));
		userToUpdate.setPwdStrong("3");
		int i=this.updateSomeFieldByPk(userToUpdate);
		return LangTips.okMsg();
	}
	/** 请在此类添加自定义函数 */
	 
	public   int updateByPk(User user) {  
		
		List<Role> roles=this.loadUserRolesByUserid(user.getUserid());
		Tips tips=LoginUtils.checkPublicRoles("信息", roles);
		
		if(!tips.isOk()) {
			throw new BizException(tips);
		}
		User p=new User(); 
		p.setDisplayUserid(user.getDisplayUserid());
		List<User> users=selectListByWhere(p);
		boolean ok=true;
		for (User user2 : users) {
			if(user2.getDisplayUserid().equals(user.getDisplayUserid())&&user2.getUserid().equals(user.getUserid())){
				ok=true;
			}else{
				ok=false;
				break;
			}
		}
		if(ok){
			int i= super.updateByPk(user);
			sysUserUpdatePushService.pushUserInfoAfterChange(BaseUtils.toMap(user));
			return i;
		}else{
			throw new BizException("该用户编号已经有人使用");
		}
		
	}

	public List<Role> loadUserRolesByUserid(String userid) {
		Map<String,Object> params=new HashMap<>();
		params.put("userid",userid);
		params.put("platformBranchId","platform-branch-001");
		List<Role> roles=baseMapper.loadUserRolesByUserid(params);
		return roles;
	}

	@Override
	public String createKey(String keyName) { 
		return sequenceService.getUserid();
	}

	/**
	 * 生成md5加密密码
	 * @param password
	 * @return
	 */
	public String getNewMd5Password(String password){
		return passwordEncoder.encode(password);
	}

	public String createMd5Password(String rawPassword){
		return MD5.create().digestHex(rawPassword,"utf-8");
	}


	public User selectOneObjectWithPassword(User user) {
		return baseMapper.selectOneObjectWithPassword(user);
	}

    public List<Map<String, Object>> selectListSimpleMapByWhere(IPage page,Map<String, Object> user) {
		return baseMapper.selectListSimpleMapByWhere(page,user);
    }

    public List<User> selectListByPhonenosAndBranchId(List<String> phonenos, String branchId) {
		return baseMapper.selectListByPhonenosAndBranchId(map("branchId",branchId,"phonenos",phonenos));
    }


	public List<User> selectListByEmailsAndBranchId(List<String> emails, String branchId) {
		return baseMapper.selectListByEmailsAndBranchId(map("branchId",branchId,"emails",emails));
	}

	@Transactional
	public void batchAdd(List<User> usersAdd) {
		super.batchInsert(usersAdd);
		this.branchInterestsService.updateCurrUsersAfterChangeUser(usersAdd.get(0).getBranchId());
	}

	public void setUsersToBranchAdm(String branchId, List<String> userids) {
		List<User> users=super.list(QueryTools.initQueryWrapper(User.class).eq("mem_type","1").eq("branch_id",branchId));
		List<User> excludeUsers=users.stream().filter(k->!userids.stream().filter(u->u.equals(k.getUserid())).findAny().isPresent()).collect(Collectors.toList());
		List<String> toUnSetAdmUserids=excludeUsers.stream().filter(k->!k.equals(branchId)).map(k->k.getUserid()).collect(Collectors.toList());
		if(userids.size()>0){
			super.baseMapper.setUsersToBranchAdm(map("userids",userids,"branchId",branchId));
		}
		if(toUnSetAdmUserids.size()>0){
			super.baseMapper.setUsersUnBranchAdm(map("userids",toUnSetAdmUserids,"branchId",branchId));

		}
		for (String userid : userids) {
			this.sysUserUpdatePushService.pushUserInfoAfterChange(map("userid",userid,"memType","1"));
		}

	}
	public void setUsersUnBranchAdm(String branchId, List<String> userids) {
		baseMapper.setUsersUnBranchAdm(map("userids",userids,"branchId",branchId));
		for (String userid : userids) {
			this.sysUserUpdatePushService.pushUserInfoAfterChange(map("userid",userid,"memType","2"));
		}
	}


	public Map<String, Object> detailWithInterests(String userid) {
		return baseMapper.detailWithInterests(userid);
	}

	/**
	 * 手机号脱敏
	 */
	public static String phoneEncode(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return "";
		}
		if(phone.length()<11){
			return phone;
		}
		return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

	/**
	 * 身份证脱敏
	 */
	public static String idCardEncode(String idCard) {
		if (StringUtils.isEmpty(idCard)) {
			return "";
		}
		if(idCard.length()<8){
			return idCard;
		}
		return idCard.replaceAll("(\\d{4})\\d+(\\w{4})", "$1*****$2");
	}

	/**
	 * 邮箱脱敏
	 */
	public static String emailEncode(String email) {
		if (StringUtils.isEmpty(email)) {
			return "";
		}
		email = email.replaceAll("(^\\w)[^@]*(@.*$)", "$1****$2");
		return  email;
	}

	public static void main(String[] args) {
		User user=new User();
		user.setIdCardNo("450881198303202016");
		user.setEmail("cyc58469@163.com");
		user.setEmailBak("c@163.com");
		user.setPhoneno("13610336198");
		if(user!=null){
			user.setPhoneno(UserService.phoneEncode(user.getPhoneno()));
			user.setIdCardNo(UserService.idCardEncode(user.getIdCardNo()));
			user.setEmail(UserService.emailEncode(user.getEmail()));
			user.setEmailBak(UserService.emailEncode(user.getEmailBak()));
		}
	}

	public boolean checkIdCardNoExistsAndValid(String idCardNo,String excludeUserid) {
		Long count=baseMapper.checkIdCardNoExistsAndValid(map("idCardNo",idCardNo,"excludeUserid",excludeUserid));
		return count>0?true:false;
	}



	public long interestsOverUpdate(User userUpdate) {
		return baseMapper.interestsOverUpdate(userUpdate);
	}

	public List<Map<String, Object>> listUserNames(Map<String, Object> user) {
		return baseMapper.listUserNames(user);
	}

    public List<User> selectListByTpaOpenidOrTpaUnionidOrPhoneno(UserTpa userTpa) {
		return baseMapper.selectListByTpaOpenidOrTpaUnionidOrPhoneno(userTpa);
    }

	@Transactional
	public Tips batchResetPassword(List<String> userids, String password) {
		String pwd=passwordEncoder.encode(password);
		for (String userid : userids) {
			User userToUpdate=new User();
			userToUpdate.setUserid(userid);
			userToUpdate.setPassword(pwd);
			userToUpdate.setPwdStrong("3");
			int i=this.updateSomeFieldByPk(userToUpdate);
		}

		return LangTips.okMsg();
	}
}

