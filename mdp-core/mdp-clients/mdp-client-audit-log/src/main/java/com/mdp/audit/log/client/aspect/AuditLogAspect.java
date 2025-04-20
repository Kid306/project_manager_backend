package com.mdp.audit.log.client.aspect;

import com.mdp.audit.log.client.JSONUtils;
import com.mdp.audit.log.client.annotation.AuditLog;
import com.mdp.audit.log.client.entity.OperLog;
import com.mdp.audit.log.client.service.OperLogClientService;
import com.mdp.core.utils.RequestUtils;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AuditLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(AuditLogAspect.class);

    @Autowired
    OperLogClientService logService;

    @Value("${mdp.audit.log:true}")
    boolean auditLog=true;

    @Value("${mdp.audit.log-db:true}")
    boolean auditLogDb=true;

    @Around("@annotation(auditLog)")
    public Object around(ProceedingJoinPoint point,AuditLog auditLog) throws Throwable {
        // 执行方法
		Object result=null;
		// 执行时长(毫秒)
		long time=0;
		try {
			long beginTime = System.currentTimeMillis();
			result = point.proceed();
			time = System.currentTimeMillis() - beginTime;
			 //异步保存日志
	        saveLog(point, auditLog,time,result,true,"成功");
		} catch (Exception e) {
			 try {
			     saveLog(point,auditLog,time,result,false,e.getMessage());
             }catch (Exception e1){
			     logger.error("发送审计日记出错,但不影响交易继续执行",e1);
             }
			throw e;
		}
       
        return result;
    }
    void saveLog(ProceedingJoinPoint joinPoint,AuditLog auditLog, Long time, Object result,boolean isOk,String execMessage)  {
        if(this.auditLog==false){
            return;
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperLog operLog = new OperLog();
        Map<Object, Object> map = new HashMap<>(4);
        if (auditLog != null) {
            // 注解上的描述
            String [] argNames = signature.getParameterNames();
            Object [] args = joinPoint.getArgs();
           
            for(int i = 0;i < argNames.length;i++){
                map.put(argNames[i],args[i]);
            }
            String firstMenu = auditLog.firstMenu();
            String secondMenu = auditLog.secondMenu();
            String func= auditLog.func();
            String funcDesc=auditLog.funcDesc();
            if(funcDesc.contains("{{")) {
            	  for (Map.Entry<Object, Object> entry : map.entrySet()) {
                      Object k = entry.getKey();
                      Object v = entry.getValue();
                      if(funcDesc.contains("{{" + k + "}}")) {
                    	  try {
							funcDesc = funcDesc.replaceAll("{{" + k + "}}",String.valueOf(v));
						} catch (Exception e) {
							 
							e.printStackTrace();
						}
                      }
                  }
            }
          

            operLog.setFirstMenu(firstMenu);
            operLog.setSecondMenu(secondMenu);
            operLog.setFuncDesc(funcDesc);
            operLog.setFunc(func);
            operLog.setOpType(auditLog.operType().getValue());
        }
        operLog.setCtime(new Date()); 
        operLog.setOpTime(time.intValue());
        // 请求的方法名
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = signature.getName();
//        sysLog.setMethod(className + "." + methodName + "()");
        //         请求的参数
        Object[] args = joinPoint.getArgs();
        try {
        	if(args != null && args.length >0) {
                String params = JSONUtils.beanToJson(map);
                operLog.setParams(params);
        	}
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取request
        HttpServletRequest request = RequestUtils.getRequest();
        // 设置IP地址
        operLog.setIp(RequestUtils.getIpAddr(request));
        // 用户名
        User currUser = LoginUtils.getCurrentUserInfo();
        if (null == currUser) {
           
        } else {
            operLog.setUserid(currUser.getUserid());
            operLog.setUsername(currUser.getUsername());  
            operLog.setBranchId(currUser.getBranchId());
            operLog.setBranchName(currUser.getBranchName());
            operLog.setDeptid(currUser.getDeptid());
            operLog.setDeptName(currUser.getDeptName());
        }

        // 保存系统日志
        operLog.setId(logService.createKey("id"));
        if(isOk){
            logger.info("审计日志",operLog);
        }else{
            logger.error("审计日志",operLog);
        }

        if(this.auditLogDb){
            logService.saveAsync(operLog);
        }
    }
}
