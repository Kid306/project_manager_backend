package com.mdp.login.service.client;

import com.mdp.core.entity.Tips;
import com.mdp.sms.client.api.SmsCodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsClientService {


	@Value(value = "${mdp.sms.validate-sms-code-uri:/sms/sms/sms/validateSmsCode}")
	String smsValidCodeUri="";

	@Autowired
	SmsCodeService smsCodeService;
	
	Log log=LogFactory.getLog(SmsClientService.class);
	 
	/**
	 * 与短信系统交互验证短信验证码的正确性
	 * @param phoneno
	 * @param smsCode
	 * @return
	 */
	public Tips validateSmsCode(String phoneno, String smsCode) {
		return smsCodeService.validateSmsCode(phoneno,smsCode,"login");
	}
	/**
	 * 从缓存中验证验证码是否正确，此方式与sms系统直接交互关系
	 * @param phoneno
	 * @param smsCode
	 * @return
	 */
	public Tips validateSmsCodeFromCache(String phoneno, String smsCode) {
		Tips tips = new Tips("检验成功");
    	tips=smsCodeService.validateSmsCodeUseCache(phoneno,smsCode,"login");

		if (tips.isOk()) {
			//验证成功要清空短信验证码，防止第二次验证，不需要这样，过期了就无效了，有效期内可以多次验证，防止多次发短信的情况发生。
			//smsCodeRedisCacheService.putSmsCode(phoneno, smsCode, "login");
			//smsCodeService.clearSmsCode(phoneno,smsCode,"login");
			return tips;
		}else {
			tips.setErrMsg("短信验证码已过期或者未存在");
			return tips;
		}
	}
	
}
