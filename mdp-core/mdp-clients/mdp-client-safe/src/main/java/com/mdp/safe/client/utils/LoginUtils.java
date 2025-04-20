package com.mdp.safe.client.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.mdp.core.entity.Tips;
import com.mdp.core.utils.Const;
import com.mdp.core.utils.ObjectTools;
import com.mdp.qx.DataLvl;
import com.mdp.safe.client.cache.DeptRedisCacheService;
import com.mdp.safe.client.cache.InMemoryUserCahceService;
import com.mdp.safe.client.cache.ModuleBranchRedisCacheService;
import com.mdp.safe.client.cache.ModuleRedisCacheService;
import com.mdp.safe.client.entity.*;
import com.mdp.safe.client.service.UserResourceQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class LoginUtils {

	private static ThreadLocal<CommonUserDetails> local=new ThreadLocal<>();

	public static List<UserDetailsConverter>  userDetailsConverters;

	public static InMemoryUserCahceService inMemoryUserCahceService;

	public static ModuleRedisCacheService moduleRedisCacheService;

	public static ModuleBranchRedisCacheService moduleBranchRedisCacheService;



	private static final Logger logger = LoggerFactory.getLogger(LoginUtils.class);

	public static CommonUserDetails converterTokenToUserDetails(Authentication token){
		if(userDetailsConverters==null){
			Map<String,UserDetailsConverter> mConverters= SpringUtil.getBeansOfType(UserDetailsConverter.class);
			if(mConverters==null || mConverters.isEmpty()){
				userDetailsConverters=new ArrayList<>();
				userDetailsConverters.add(new DefaultUserDetailsConverter());
			}else {
				userDetailsConverters=mConverters.values().stream().collect(Collectors.toList());
			}
		}
		for (UserDetailsConverter userDetailsConverter : userDetailsConverters) {
 			if(userDetailsConverter.supportToken(token)){
				CommonUserDetails userDetails=userDetailsConverter.convert(token);
				if(userDetails==null){
					continue;
				}else {
					local.set(userDetails);
					return userDetails;
				}
			}
		}
		return null;
	}
	public static void clearThreadLock(){
		local.remove();
	}
	public static boolean isGuest(){ 
		Authentication auth=SecurityContextHolder.getContext().getAuthentication(); 
		if(auth==null){
			return true;
		}
		if(!StringUtils.hasText(auth.getName()) || "guest".equals(auth.getName())){
			return true;
		}
		if(auth.getClass().isAssignableFrom(AnonymousAuthenticationToken.class)){//访客登录
			return true;
		}
		CommonUserDetails uds=local.get();
		if( uds==null || !uds.getUsername().equals(auth.getName())){
			uds=converterTokenToUserDetails(auth);
		}
		if(uds==null){
			return true;
		}
		User user=uds.getUser();
		if(user==null || !StringUtils.hasText(user.getUserid()) || "guest".equals(user.getUserid())){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取当前登录者的信息，如果只有第三方登录信息，则虚拟一个临时用户返回前台。不保存数据库 
	 * @return
	 */
	public static User getCurrentUserInfo(){  
		if(SecurityContextHolder.getContext()==null || SecurityContextHolder.getContext().getAuthentication()==null){
			return createXuNiUser();
		}
		CommonUserDetails uds=local.get();
		if( uds==null || !uds.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
			uds=converterTokenToUserDetails(SecurityContextHolder.getContext().getAuthentication());
		}
		if(uds==null){
			return createXuNiUser();
		}else {
			return uds.getUser();
		}
	}
	
	

	/**判断当前用户是否是超级管理员*/
	public static boolean isSuperAdmin(){
		if(SecurityContextHolder.getContext()==null || SecurityContextHolder.getContext().getAuthentication()==null){
			return false;
		}
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		return Const.superAdminUserid.equals(auth.getName() );
	}
	/**获取我拥有的角色权限*/
	public static Collection<GrantedAuthority> getAuthorities(){

		if(SecurityContextHolder.getContext()==null || SecurityContextHolder.getContext().getAuthentication()==null ){
			return null;
		}
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		return (Collection<GrantedAuthority>) auth.getAuthorities();
	}

	/**判断当前用户是否有任何一种角色*/
	public static String[] getMyRoleids(){

		if(SecurityContextHolder.getContext()==null || SecurityContextHolder.getContext().getAuthentication()==null ){
			return null;
		}
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		return getRoleidsFromGrantedAuthoritys(auth.getAuthorities());
	}

	/**判断当前用户是否有任何一种角色*/
	public static boolean hasAnyRoles(String ...roleids){
		if(roleids==null) {
			return false;
		}
		if(SecurityContextHolder.getContext()==null || SecurityContextHolder.getContext().getAuthentication()==null ){
			return false;
		} 
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication(); 
		Collection<? extends GrantedAuthority> gas=auth.getAuthorities();
		if(gas==null || gas.isEmpty()){
			return false;
		}
		Iterator<GrantedAuthority> iterator = (Iterator<GrantedAuthority>) gas.iterator();   
        while(iterator.hasNext()){  
        	GrantedAuthority ga = iterator.next();
        	if(Const.superAdminRole.equals(ga.getAuthority())){
        		return true;
			}
        	for (String roleid : roleids) {
        		if(ga.getAuthority().equals(roleid)){
            		return true;
            	} 
			}
        	
        } 
        return false;
	}
	public static boolean hasAnyModules(String ...moduleIds){
		if(moduleIds==null) {
			return false;
		}
		if(moduleIds==null || moduleIds.length <=0  ) {
			return true;
		}

		User user=LoginUtils.getCurrentUserInfo();
		if(LoginUtils.isSuperAdmin()){
			return true;
		}
		String branchId=user.getBranchId();
		if("0".equals(user.getMemType())){
			String[] roles=new String[moduleIds.length];
			for (int i = 0; i < moduleIds.length; i++) {
				roles[i]="m_"+moduleIds[i];
			}
			if (LoginUtils.hasAnyRoles(roles)) {
				return true;
			}else{
				return false;
			}
		}
		List<Tips> can=new ArrayList<>();
		List<Tips> no=new ArrayList<>();
		for (String moduleId : moduleIds) {
			Tips tips = new Tips("成功");
			if(moduleBranchRedisCacheService==null){
				moduleBranchRedisCacheService=SpringUtil.getBean(ModuleBranchRedisCacheService.class);
			}
			ModuleBranch module=moduleBranchRedisCacheService.getModuleBranch(branchId,moduleId);

			if(module==null){
				tips.setErrMsg("no-purchase-module","未开通模块【%s】，不能使用",moduleIds);
			}else{
				if("0".equals(module.getStatus())){
					tips.setErrMsg("module-status-0","模块【%s】已停用，不能使用",moduleIds);
				}else{
					Date now= Calendar.getInstance().getTime();
					if(module.getEndTime()==null || module.getEndTime().compareTo(now)<0){
						tips.setErrMsg("module-end-time-expire","模块【%s】已超过规定使用期限，请续期",moduleIds);
					}
				}
			}
			if(tips.isOk()==false){
				no.add(tips);
				logger.error("模块开通情况检查不通过：", tips.getMsg());
				continue;
			}else{
				can.add(tips);
				break;
			}
		}
		if(can.size()>0){
			return  true;
		} else {
			return false;
		}
	}
	/**
	 * 判断当前登录用户是否拥有某个操作权限
	 * @param qxIds 空返回false
	 * @return
	 */
	public static boolean hasAnyQx(String ...qxIds) {

 		if(qxIds==null || qxIds.length==0){
			return false;
		}
		if(SecurityContextHolder.getContext()==null || SecurityContextHolder.getContext().getAuthentication()==null ){
			return false;
		}
		if(LoginUtils.isSuperAdmin()){
			return true;
		}
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> gas=auth.getAuthorities();
		if(gas==null || gas.isEmpty()){
			return false;
		}
		CommonUserDetails uds=converterTokenToUserDetails(auth);

		if(uds==null){
			return false;
		}
		User user=uds.getUser();
		if(user==null){
			return false;
		}

		String[] roleids= getRoleidsFromGrantedAuthoritys(gas);
		Map<String, Qx> qxs= getUserRoleQxsFromCache(user.getUserid(),roleids);

		if( qxs==null || qxs.size()==0 ){
			return false;
		}
		if (qxs != null) {
			for (String qxId : qxIds) {
				if(qxs.containsKey(qxId)){
					return true;
				};
			}
		}
		return false;
		
	}
	/**
	 * 判断当前登录用户是否拥有某个操作权限或者数据访问等级
	 * @param qxIds 需要判断的权限列表
	 * @param minDataLvl 最小的数据访问等级
	 * @return
	 */
	public static boolean hasAnyDataLvlOrQx(@Nullable DataLvl minDataLvl,@Nullable String ...qxIds) {
		if(hasMinDataLvl(minDataLvl)){
			return true;
		}
		return hasAnyQx(qxIds);

	}

	/**
	 * 获取当前登录用户最大的数据访问等级
	 * @return
	 */
	public static DataLvl getMaxDataLvl() {
 		User user=getCurrentUserInfo();

		 return DataLvl.getDataLvl(user.getMaxDataLvl());
	}
	/**
	 * 判断当前登录用户是否拥有高于或者等于指定的访问等级
	 * @param minDataLvl 最小的数据访问等级
	 * @return
	 */
	public static boolean hasMinDataLvl(@Nullable DataLvl minDataLvl) {
		DataLvl dl=getMaxDataLvl();
		if(dl.getLvl()>=minDataLvl.getLvl()){
			return true;
		}else{
			return false;
		}

	}


	/**
	 * 查出所有我拥有的权限
	 * @return
	 */
	public static Map<String,Qx> getMyQxs() {
		Map<String,Qx> myQxs=new HashMap<>();
		if(SecurityContextHolder.getContext()==null || SecurityContextHolder.getContext().getAuthentication()==null){
			return myQxs;
		}
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> gas=auth.getAuthorities();
		if(gas==null || gas.isEmpty()){
			return myQxs;
		}
		CommonUserDetails uds=local.get();
		if( uds==null || ! uds.getUsername().equals(auth.getName())){
			uds=converterTokenToUserDetails(auth);
		}
		if(uds==null){
			return myQxs;
		}
		User user=uds.getUser();
		if(user==null){
			return myQxs;
		}
		String[] roleids= getRoleidsFromGrantedAuthoritys(gas);
		Map<String,Qx> qxs= getUserRoleQxsFromCache(user.getUserid(),roleids);

		if( qxs==null || qxs.size()==0 ){
			return myQxs;
		}else {
			return qxs;
		}

	}
	public static User createXuNiUser(){
		User userNew=new User();
		userNew.setUserid("guest");
		userNew.setUsername("游客");
		userNew.setHeadimgurl("");
		userNew.setPhoneno("");
		userNew.setBranchId("guest");
		userNew.setBranchName("游客");
		userNew.setMemType("0");
		userNew.setMaxDataLvl(DataLvl.myDept.getLvl());
		return userNew;
		
	}

	/**
	 * 检查某个部门是不是我的有权限管辖部门
	 * @param deptid
	 * @return
	 */
	public static boolean checkIsMyDeptScope(String deptid){

		if(!StringUtils.hasText(deptid)){
			return false;
		}

		User user = LoginUtils.getCurrentUserInfo();
		DataLvl myMaxDataLvl=DataLvl.getDataLvl(user.getMaxDataLvl());
		if(user.hasDeptid(deptid)){
			if(myMaxDataLvl.getLvl()>=DataLvl.myDept.getLvl()){
				return true;
			}
		}
		if(myMaxDataLvl.getLvl()>=DataLvl.allowAll.getLvl()){
			return true;
		}
		if(myMaxDataLvl.getLvl()==DataLvl.forbiddenAll.getLvl()){
			return false;
		}
		DeptRedisCacheService deptCacheService = SpringUtil.getBean(DeptRedisCacheService.class);
		Dept deptVo=deptCacheService.getDept(deptid);
		if(deptVo==null){
			return false;
		}
		List<String> pdeptids= ObjectTools.isEmpty(deptVo.getIdPath())?new ArrayList<>():Arrays.asList(deptVo.getIdPath().split(","));
		if(pdeptids ==null || pdeptids.size()==0){
			return false;
		}
		Set<String> myDeptidSet=user.getDeptids();
		if(myDeptidSet==null || myDeptidSet.isEmpty()){
			return false;
		}
		for (String myDeptid : myDeptidSet) {
			String myTopParentDeptid="";
			Dept userDeptVo=deptCacheService.getDept(myDeptid);

			if(userDeptVo==null || !deptVo.getBranchId().equals(userDeptVo.getBranchId())){
				continue;
			}else{
				List<String> idPathList= ObjectTools.isEmpty(deptVo.getIdPath())?new ArrayList<>():Arrays.asList(deptVo.getIdPath().split(","));

				int pdeptidLength=idPathList.size();
				if(myMaxDataLvl.getLvl()>=DataLvl.allowAll.getLvl()){
					myTopParentDeptid= Const.topDeptid;
				}else if(myMaxDataLvl.getLvl()>=DataLvl.branch.getLvl()){
					myTopParentDeptid= idPathList.get(pdeptidLength-1);
				}else if(myMaxDataLvl.getLvl()>=DataLvl.p2.getLvl()){
					myTopParentDeptid=  idPathList.get(1);
				}else if(myMaxDataLvl.getLvl()>=DataLvl.p1.getLvl()){
					myTopParentDeptid=  idPathList.get(0);
				}else if(myMaxDataLvl.getLvl()==DataLvl.subDept.getLvl()){
					myTopParentDeptid=  userDeptVo.getDeptid();
				}else if(myMaxDataLvl.getLvl()>=DataLvl.myDept.getLvl()){
					myTopParentDeptid=  userDeptVo.getDeptid();
				}else{
					myTopParentDeptid=  myDeptid;
				}
			}
			if(deptid.equals(myTopParentDeptid)){
				return true;
			}
			if(pdeptids.contains(myTopParentDeptid)){
				return true;
			}
		}
		return false;
	}
	
	public static String getGuestRoleid() {
		return "guest";
	}

	
	/**
	 * 超级管理员 > 平台管理员 > 机构管理员
	 * @param msg
	 * @param roles
	 * @return
	 */
	public static Tips checkPublicRoles(String msg, String ...roles) {

		boolean hasSuperAdmin=LoginUtils.isSuperAdmin();
		boolean isSuperAdmin=false;
		boolean isBranchAdmin=false;
		
		Tips tips=new Tips("检查成功");
		if(roles==null) {
			return tips;
		}
		for (String roleid : roles) {
			if(roleid.equals(Const.superAdminRole)) {
				isSuperAdmin=true;
			}
			if(roleid.equals("branchAdmin")) {
				isBranchAdmin=true;
			}
		}
		if(!hasSuperAdmin) {
			if(isSuperAdmin) {
				tips.setErrMsg("无权操作超级管理员"+msg);
				return tips;
			}
		}
		if(!hasSuperAdmin) {
			if(isBranchAdmin) {
				tips.setErrMsg("您不是超级管理员，无权操作机构管理员"+msg);
				return tips;
			} 
		}
		return tips;
	}
	public static Tips checkPublicRoles(String msg,List<Role> roles) {
		int size=roles.size();
		String[] roleStrs=new String[size];
		for (int i=0;i<size;i++) {
			roleStrs[i]=roles.get(i).getRoleid();
		}
		
		return checkPublicRoles(msg,roleStrs);
	
	}
	public static String[] getRoleidsFromGrantedAuthoritys(Collection<? extends GrantedAuthority> gas){

		List<Object> res=gas.stream().map(x->x.getAuthority()).collect(Collectors.toList());
		String[] strArr=new String[res.size()];
		res.toArray(strArr);
		return strArr;
	}
	public static Map<String, Qx> getUserRoleQxsFromCache(String userid, String...roleids){
		if(inMemoryUserCahceService==null){
			inMemoryUserCahceService=SpringUtil.getBean(InMemoryUserCahceService.class);
		}

		Map<String, Qx> roleQxs=inMemoryUserCahceService.getUserQxs(userid);
		if( roleQxs==null ){
			UserResourceQueryService userService=SpringUtil.getBean(UserResourceQueryService.class);
			roleQxs=userService.loadRoleQxsByRoleids(roleids);
			inMemoryUserCahceService.putUserQxs(userid,roleQxs);
			return roleQxs;
		}else {
			return roleQxs;
		}
	}

	public static boolean isBranchAdmin(String branchId){
		User user=LoginUtils.getCurrentUserInfo();
		if(!StringUtils.hasText(branchId)){
			return false;
		}
		if(LoginUtils.isGuest()){
			return false;
		}
		if(("1".equals(user.getMemType()) && user.getBranchId().equals(branchId)) || user.getUserid().equals(branchId)){
			return true;
		}{
			return false;
		}
	}
	public static boolean isMainBranchAdmin(String branchId){
		User user=LoginUtils.getCurrentUserInfo();
		if(!StringUtils.hasText(branchId)){
			return false;
		}
		if(LoginUtils.isGuest()){
			return false;
		}
		if( user.getUserid().equals(branchId)){
			return true;
		}{
			return false;
		}
	}
	public static boolean isMainBranchAdmin(String userid,String branchId){
		if(!StringUtils.hasText(userid)){
			return false;
		}
		if(!StringUtils.hasText(branchId)){
			return false;
		}
		if(userid.equals(branchId)){
			return true;
		}{
			return false;
		}
	}


	public static boolean isMainBranchAdmin(){
		User user=LoginUtils.getCurrentUserInfo();
		if(LoginUtils.isGuest()){
			return false;
		}
		if(!StringUtils.hasText(user.getBranchId())){
			return false;
		}
		if(user.getUserid().equals(user.getBranchId())){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isBranchAdmin(){
		User user=LoginUtils.getCurrentUserInfo();
		if(LoginUtils.isGuest()){
			return false;
		}
		if(!StringUtils.hasText(user.getBranchId())){
			return false;
		}
		if("1".equals(user.getMemType()) || isMainBranchAdmin()){
			return true;
		}else{
			return false;
		}
	}
}
