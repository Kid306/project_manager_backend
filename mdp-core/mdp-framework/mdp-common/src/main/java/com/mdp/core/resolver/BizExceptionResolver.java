package com.mdp.core.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 所有业务异常的统一处理类<br>
 * 所有业务处理的错误信息，需要通过转义，转义成友好的，清晰的，有操作指引含义的描述。再传递给客户。<br>
 * 支持动态数据转换.如下面重试次数是通过程序传入的.<br>
 * 消息转义统一在conf/props/message.properties进行配置.<br>
 * 
 * 消息转换器,格式为:  <br>
 * 技术错误码=业务错误码+消息<br>
 * errcode01=[0760881]您输入的密码不正确,请重新输入,您还可以再试{0}次.<br>
 * 
 * @author cyc
 * @since 20150120
 *
 */
public class BizExceptionResolver implements HandlerExceptionResolver, Ordered {
	
	private int order = Ordered.HIGHEST_PRECEDENCE;
	
	protected static Logger log= LoggerFactory.getLogger(BizExceptionResolver.class);
	
	@Autowired
	private MessageSource messageSource;
	
	ObjectMapper o=new ObjectMapper();
	
	@Override
	public int getOrder() {
		return order;
	}
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		log.error("统一异常处理：",ex);
		if(ex instanceof ServletException){
			return null;
		}

		if(ex instanceof BizException && RequestUtils.isAjaxRequest(request) ){
			Tips tips=exceptionToTips(ex,messageSource);
			Map<String,Object> result =new HashMap<String,Object>();
			response.setCharacterEncoding("UTF-8");  
			response.setContentType("application/json; charset=utf-8");  
			result.put("tips", tips);  
			try {
				o.writeValue(response.getOutputStream(), result);
			}  catch (Exception e) {
				log.error("",e);
			} 
			return null;
		}
		return null;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	public static Tips exceptionToTips(Exception ex,MessageSource messageSource){
		Object[] args=null;
		Tips tips=null;
		if(ex instanceof BizException){
			BizException be=(BizException)ex;
			tips=be.getTips();
			args=be.getArgs();
		}else if(ex instanceof SQLException ){
			SQLException se=(SQLException)ex;
			tips=new Tips(false,""+se.getErrorCode(),"数据库操作错误");
			tips.put("moreMsg", ex.getMessage());
		}else if(ex instanceof DataAccessException){
			DataAccessException dae=(DataAccessException)ex;
			Throwable t=dae.getRootCause();
			if(t instanceof SQLException){
				SQLException se=(SQLException)dae.getRootCause();
				tips=new Tips(false,""+se.getErrorCode(),"数据库操作错误");
				tips.put("moreMsg", ex.getMessage());
			}else{
				tips=new Tips(false,"数据库操作错误");
				tips.put("moreMsg", ex.getMessage());
			}
		}else{
			tips=new Tips(false,"业务处理不成功");
			tips.put("moreMsg",ex.getMessage());
			tips.put("stackTrace", ex.getStackTrace());
		}
		/**转义消息*/
		String msg=messageSource.getMessage(tips.getTipscode(), args, tips.getMsg(),new Locale("zh"));
		tips.setErrMsg(msg);
		log.error("http协议状态码["+200+"],技术错误码["+tips.getTipscode()+"],转义后的消息["+msg+"]");
		return tips;
	}

}
