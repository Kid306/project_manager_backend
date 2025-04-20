package com.mdp.login.service;

import com.mdp.core.entity.Tips;
import com.mdp.sms.client.api.SmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsLoginService {

    @Autowired
    SmsCodeService smsCodeService;
    /**
     *
     * 验证短信验证码是否正确
     * @param phoneno 手机号码
     * @param smsCode 短信验证码
     * @return
     */
    public Tips checkPhoneCode(String phoneno, String smsCode ){
        return smsCodeService.validateSmsCodeUseCache(phoneno,smsCode,"login");
    }
}
