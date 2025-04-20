package com.mdp.core.log;

import com.mdp.core.api.ContextEnvService;
import com.mdp.core.utils.LogUtils;
import com.mdp.core.utils.SequenceFormat;
import com.mdp.core.utils.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
 

public class MdpLogFilter implements Filter {
 
	Logger log= LoggerFactory.getLogger(MdpLogFilter.class);

	public void setContextEnvService(ContextEnvService contextEnvService) {
		this.contextEnvService = contextEnvService;
	}

	ContextEnvService contextEnvService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String gloNo=request.getParameter(LogUtils.GLO_NO_NAME);
		String reqNo= SequenceFormat.parse(LogUtils.SEQ_PATTERN);
		
		if(StringUtils.isEmpty(gloNo)) {
			gloNo=LogUtils.genGloNo();
			log.info(">>> 客户端没有上送 全局跟踪号，将自动生成全局跟踪号： "+LogUtils.GLO_NO_NAME+"【"+gloNo+"】，自动生成请求跟踪号："+LogUtils.REQ_NO_NAME+"【"+reqNo+"】"); 
		}else {
			log.info(">>> 客户端上送 全局跟踪号： "+LogUtils.GLO_NO_NAME+"【"+gloNo+"】，自动生成请求跟踪号："+LogUtils.REQ_NO_NAME+"【"+reqNo+"】"); 
		}
		
		 MDC.put(LogUtils.GLO_NO_NAME,gloNo );
		 MDC.put(LogUtils.REQ_NO_NAME, reqNo);
		 log.info(">>>>>> req start 接受客户端请求开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 if(request instanceof HttpServletRequest) {
			 HttpServletRequest req=(HttpServletRequest)request;    
			 log.info(">>req-url 请求路径："+ UrlUtils.buildFullRequestUrl(req));
			 log.info(">>req content-type ："+req.getContentType());
		 }
		
		 chain.doFilter(request, response); 
		 log.info(">>>>>> req end 处理客户端请求结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		 MDC.remove(LogUtils.GLO_NO_NAME);
		 MDC.remove(LogUtils.REQ_NO_NAME);
		 MDC.remove(LogUtils.USERID_NAME);
		 if(contextEnvService!=null){
			 contextEnvService.clearThreadLock();

		 }

	}

	@Override
	public void destroy() {
	}

}
