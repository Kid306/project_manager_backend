package com.mdp.safe.client.qx;

import com.mdp.core.err.QxException;
import com.mdp.qx.DataLvl;
import com.mdp.qx.HasDataLvl;
import com.mdp.qx.ResourceType;
import com.mdp.safe.client.service.UserResourceQueryService;
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

/**
 * 权限拦截器
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DataLvlAspect {

    private static final Logger logger = LoggerFactory.getLogger(DataLvlAspect.class);
     
    @Autowired
    UserResourceQueryService userService;

	@Value("${mdp.auth.data-lvl-check:false}")
	boolean lvlCheck=false;

    @Around("@annotation(hasDataLvl)")
    public Object around(ProceedingJoinPoint pjp, HasDataLvl hasDataLvl) throws Throwable{
    	if(!lvlCheck) {
    		return pjp.proceed();
    	}
    	if(hasDataLvl.value().name().equals(DataLvl.forbiddenAll.name())) {
    		throw new QxException(hasDataLvl.value().name(),ResourceType.deptScope.name(),"该资源已经禁止访问");
    	}
    	else if(hasDataLvl.value().name().equals(DataLvl.allowAll.name())) {
    		return pjp.proceed();
    	}
		boolean hasMinLvl= LoginUtils.hasMinDataLvl(hasDataLvl.value());
    	if(hasMinLvl==false) {
			throw new QxException(hasDataLvl.value().name(),ResourceType.deptScope.name(),"权限级别检查不通过，无法访问");
		}else {
			return pjp.proceed();
		}
    	
         
    }
     
     
}