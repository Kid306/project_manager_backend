package com.mdp.safe.client.qx;

import com.mdp.core.err.QxException;
import com.mdp.core.utils.ObjectTools;
import com.mdp.core.utils.RequestUtils;
import com.mdp.qx.HasQx;
import com.mdp.qx.ResourceType;
import com.mdp.safe.client.entity.Menu;
import com.mdp.safe.client.service.GuestService;
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
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Order(-2)// 保证该AOP在@Transactional之前执行
@Component
public class QxAspect {

	
    @Autowired
    UserResourceQueryService userService;
	
	@Autowired
    GuestService guestService;

	public static AntPathMatcher antPathMatcher=new AntPathMatcher();

	@Value("${mdp.auth.qx-check:false}")
	boolean qxCheck=false;

    private static final Logger logger = LoggerFactory.getLogger(QxAspect.class);

	@Around("@annotation(hasQx)")
    public Object around(ProceedingJoinPoint pjp, HasQx hasQx) throws Throwable{
		if(qxCheck==false){
			return pjp.proceed();
		}
		if(!LoginUtils.hasAnyQx(hasQx.value())){
			HttpServletRequest req=RequestUtils.getRequest();
			QxException qxException=new QxException("notHasQx" ,ResourceType.url.name(),"权限不足，该业务需要以下权限【"+hasQx.name()+"】");
			if(req==null){
				throw qxException;
			}else{
				String uri= req.getRequestURI();
				if(ObjectTools.isEmpty(uri)){
					throw qxException;
				}else{
					Map<String, Menu> menusMap=userService.loadRoleMenusByRoleids(LoginUtils.getMyRoleids());
					if(menusMap==null || menusMap.isEmpty()){
						throw qxException;
					}else{
						for (Menu m : menusMap.values()) {
							if(!ObjectTools.isEmpty(m.getApiRules())){
								if(antPathMatcher.match(m.getApiRules(),uri)){
									return pjp.proceed();
								}
							}
						}
						throw qxException;
					}
				}
			}

		}else{
			return pjp.proceed();
		}
	}
}
