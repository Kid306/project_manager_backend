package com.mdp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class LogUtils {
	
	public static Logger log= LoggerFactory.getLogger(LogUtils.class);
	/**
	 * 请求流水号，每次请求自动生成一个流水号，在内部流转，并在相应报文头中返回下发给请求方，
	 */
	public static final String REQ_NO_NAME="reqNo";
	
	/**
	 * 全局流水号 ，实现上下游系统交易的串联，一般由外部系统传输过来，我方系统继续传输到下游系统
	 */
	public static final String GLO_NO_NAME="gloNo";
	
	
	public static final String SEQ_PATTERN ="{date:yyyyMMddHHmmssSSS}{rands:6}";
	
	/**
	 * 用户编号
	 */
	public static final String USERID_NAME="userid";
	
	/**
	 * 用户登陆编号
	 */
	public static final String DISPLAY_USER_ID_NAME="displayUserid";
	
	public static String genGloNo() {
		return SequenceFormat.parse(LogUtils.SEQ_PATTERN);
	}
	/**
	 * 从MDC获取全局跟踪号，如果没有，则根据条件创建
	 * @param forceCreate 是否强制生成全局跟踪号
	 * @return
	 */
	public static String getGloNo(boolean forceCreate) {
		String gloNo=(String) MDC.get(LogUtils.GLO_NO_NAME);
		if(StringUtils.isEmpty(gloNo)) {
			gloNo=LogUtils.genGloNo();
			MDC.put(LogUtils.GLO_NO_NAME, gloNo);
			log.info("自动创建全局跟踪号"+LogUtils.GLO_NO_NAME+"【"+gloNo+"】");
		}
		return gloNo;
	}
	
}
