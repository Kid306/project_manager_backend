package com.mdp.sms.client.api;

import com.mdp.core.entity.Tips;

public interface SmsCodeService {
	/**
	 * 产生短信验证码
	 * @param phoneno
	 * @param codeLength
	 * @param scene 场景 login/changePassword/register/common等
	 * @return
	 */
	Tips sendSmsCode(String phoneno, int codeLength, String scene);
	
	/**
	 * 验证短信验证码
	 * @param phoneno
	 * @param smsCode
	 * @param scene 场景 login/changePassword/register/common等
	 * @return
	 */
	Tips validateSmsCode(String phoneno,String smsCode,String scene);

	 /**
	  * 验证短信验证码，不走api方式，直接从缓存取验证码进行比对
	  * @param phoneno
	  * @param smsCode
	  * @param scene 场景 login/changePassword/register/common等
	  * @return
	  */
	Tips validateSmsCodeUseCache(String phoneno,String smsCode,String scene);

	 /**
	  * 清除短信验证码
	  * @param phoneno
	  * @param smsCode
	  * @param scene
	  */
	void clearSmsCode(String phoneno,String smsCode,String scene);

}
