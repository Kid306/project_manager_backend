package com.mdp.sms.client.service;


import com.mdp.core.entity.Tips;
import com.mdp.micro.client.CallBizService;
import com.mdp.sms.client.api.SmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DefaultSmsCodeService implements SmsCodeService {

    @Autowired
    CallBizService callBizService;

    @Autowired
    SmsCodeServerRedisCacheService smsCodeServerRedisCacheService;

    @Override
    public Tips sendSmsCode(String phoneno, int codeLength, String scene) {
        Map<String,Object> params=new HashMap<>();
        params.put("phoneno",phoneno);
        params.put("codeLength",codeLength);
        params.put("scene",scene);
        Tips tips = callBizService.postForTips("/sms/sendSmsCode",params);
        return tips;
    }

    @Override
    public Tips validateSmsCode(String phoneno, String smsCode, String scene) {

        Map<String,Object> params=new HashMap<>();
        params.put("phoneno",phoneno);
        params.put("smsCode",smsCode);
        params.put("scene",scene);
        Tips tips = callBizService.postForTips("/sms/validateSmsCode",params);
        return tips;
    }

    @Override
    public Tips validateSmsCodeUseCache(String phoneno, String smsCode, String scene) {
        Tips tips = new Tips("验证码检查成功");
        String key=scene+":"+phoneno+":"+smsCode;
        String phonenoCache= smsCodeServerRedisCacheService.get(key);
        if(phonenoCache==null){
            tips.setErrMsg("验证码不存在");
        }
        return tips;
    }

    @Override
    public void clearSmsCode(String phoneno, String smsCode, String scene) {

        String key=scene+":"+phoneno+":"+smsCode;
        smsCodeServerRedisCacheService.remove(key);
    }

}
