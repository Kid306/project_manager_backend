package com.mdp.safe.client.qx;

import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.qx.HasModule;
import com.mdp.safe.client.cache.ModuleBranchRedisCacheService;
import com.mdp.safe.client.cache.ModuleRedisCacheService;
import com.mdp.safe.client.entity.Module;
import com.mdp.safe.client.entity.ModuleBranch;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 权限拦截器
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class ModuleAspect {
	
	@Value("${mdp.auth.module-check:true}")
	boolean moduleCheck=true;

	@Autowired
	ModuleRedisCacheService redisCacheService;

	@Autowired
	ModuleBranchRedisCacheService moduleBranchRedisCacheService;


    private static final Logger logger = LoggerFactory.getLogger(ModuleAspect.class);

    @Around("@annotation(hasModule)")
    public Object around(ProceedingJoinPoint pjp,HasModule hasModule) throws Throwable{  
    	String[] needRoles=hasModule.value();
    	if(needRoles==null || needRoles.length <=0 || !moduleCheck) {
    		return pjp.proceed(); 
    	}

    	User user=LoginUtils.getCurrentUserInfo();
    	if(LoginUtils.isSuperAdmin()){
			return pjp.proceed();
		}
    	String branchId=user.getBranchId();
    	if("0".equals(user.getMemType())){
			if (LoginUtils.hasAnyRoles(needRoles)) {
				return pjp.proceed();
			}else{
				throw new BizException("no-purchase-module",String.format("未开通模块【%s】，不能使用",printRole(hasModule)));
			}
		}
    	List<Tips> can=new ArrayList<>();
    	List<Tips> no=new ArrayList<>();
		for (String moduleId : needRoles) {
			Tips tips = new Tips("成功");
			ModuleBranch module=moduleBranchRedisCacheService.getModuleBranch(branchId,moduleId);

				if(module==null){
					tips.setErrMsg("no-purchase-module","未开通模块【%s】，不能使用",printRole(hasModule));
				}else{
					if("0".equals(module.getStatus())){
						tips.setErrMsg("module-status-0","模块【%s】已停用，不能使用",printRole(hasModule));
					}else{
						Date now= Calendar.getInstance().getTime();
						if(module.getEndTime()==null || module.getEndTime().compareTo(now)<0){
							tips.setErrMsg("module-end-time-expire","模块【%s】已超过规定使用期限，请续期",printRole(hasModule));
						}
					}
				}
				if(tips.isOk()==false){
					no.add(tips);
					continue;
				}else{
					can.add(tips);
					break;
				}
		}
		if(can.size()>0){
			return  pjp.proceed();
		} else {
			throw new BizException(no.get(0));
		}
    }
	public  Map<String, Module> roleNames=new HashMap<>();


    public String printRole( HasModule hasModule ){

    	String[] roles=hasModule.value();
		for (int i = 0; i < roles.length; i++) {
			if(roleNames.containsKey(roles[i])){
				roles[i]=roleNames.get(roles[i]).getName();
			}else {
				Module module=redisCacheService.getModule(roles[i]);
				if(module!=null){
					roleNames.put(module.getId(),module);
					roles[i]=module.getName();
				}
			}
		}
    	return Arrays.toString(roles);
	}
     
}