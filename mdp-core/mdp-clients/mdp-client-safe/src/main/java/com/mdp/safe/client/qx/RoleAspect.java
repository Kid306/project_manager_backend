package com.mdp.safe.client.qx;

import com.mdp.core.err.QxException;
import com.mdp.qx.HasRole;
import com.mdp.qx.ResourceType;
import com.mdp.safe.client.utils.LoginUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限拦截器
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class RoleAspect {
	
	@Value("${mdp.auth.role-check:true}")
	boolean roleCheck=true;

    private static final Logger logger = LoggerFactory.getLogger(RoleAspect.class);

    @Around("@annotation(hasRole)")
    public Object around(ProceedingJoinPoint pjp,HasRole hasRole) throws Throwable{  
    	String[] needRoles=hasRole.roles();
    	if(needRoles==null || needRoles.length <=0 || !roleCheck) {
    		return pjp.proceed(); 
    	}
    	for (int i = 0; i < needRoles.length; i++) {
    		 if("guest".equals(needRoles[i])) {
    			 return  pjp.proceed();
    		 }
		}
		boolean hasAnyRoles=LoginUtils.hasAnyRoles(needRoles);
    	if(hasAnyRoles){
			return  pjp.proceed();
		}else {
			throw new QxException("hasRole",ResourceType.role.name(),"权限不足,需要拥有 "+printRole(hasRole)+" 角色");
		}
    }
	static final Map<String,String> roleNames=new HashMap<>();

	static {

		roleNames.put("superAdmin","超级管理员");
		roleNames.put("branchAdmin","机构管理员");
		roleNames.put("user","普通用户");
	}
    public String printRole( HasRole hasRole ){
    	String[] roles=hasRole.roles();
		for (int i = 0; i < roles.length; i++) {
			if(roleNames.containsKey(roles[i])){
				roles[i]=roleNames.get(roles[i]);
			}
		}
    	return Arrays.toString(roles);
	}
     
}