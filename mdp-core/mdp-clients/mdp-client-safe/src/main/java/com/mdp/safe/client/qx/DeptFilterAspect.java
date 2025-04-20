package com.mdp.safe.client.qx;

import com.mdp.core.entity.Tips;
import com.mdp.core.err.QxException;
import com.mdp.qx.DeptFilter;
import com.mdp.qx.ResourceType;
import com.mdp.safe.client.entity.CommonUserDetails;
import com.mdp.safe.client.entity.User;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 权限拦截器
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DeptFilterAspect {
	
	@Value("${mdp.auth.dept-filter:false}")
	boolean needDeptFilter=false;

    private static final Logger logger = LoggerFactory.getLogger(DeptFilterAspect.class);
    
    @Autowired
    DeptFilterCheckService deptFilterCheckService;
    
    @Autowired
    UserResourceQueryService userService;

    @Around("@annotation(deptFilter)")
    public Object around(ProceedingJoinPoint pjp,DeptFilter deptFilter) throws Throwable{  
    	 
    	if(!needDeptFilter) {
    		return pjp.proceed(); 
    	}
    	if(SecurityContextHolder.getContext()==null || SecurityContextHolder.getContext().getAuthentication()==null || SecurityContextHolder.getContext().getAuthentication().getPrincipal()==null){
    		throw new QxException(deptFilter.ignore().name() ,ResourceType.deptScope.name(),"未登录用户，不允许操作");
		}
		CommonUserDetails userDetails=LoginUtils.converterTokenToUserDetails(SecurityContextHolder.getContext().getAuthentication());
		if(userDetails==null || userDetails.getUser()==null || !StringUtils.hasText(userDetails.getUser().getUserid())){
			throw new QxException(deptFilter.ignore().name() ,ResourceType.deptScope.name(),"未登录用户，不允许操作");
		}else {
			User user=userDetails.getUser();
			Integer myMaxDataLvl= user.getMaxDataLvl();
			Tips tips=deptFilterCheckService.around(pjp, myMaxDataLvl, user, deptFilter, false);
			if(tips.isOk()) {
				return pjp.proceed();
			}else {
				throw new QxException(tips.getMsg());
			}
		}
    }
            
     
}