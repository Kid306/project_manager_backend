package com.mdp.login.util;

import com.mdp.core.entity.Tips;

import java.time.Instant;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUtils {

    /**
     * 验证邮件格式是否正确
     * @param email
     * @return
     */
    public static Tips validEmail(String email){
        Tips tips = new Tips("邮件格式验证成功");
        String reg="^[\\.a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher=pattern.matcher(email);
        if(matcher.matches()==false){
            tips.setErrMsg("邮件格式不正确");
        }
        return tips;
    }

    /**
     * 手机号格式验证
     * @param phoneno
     * @return
     */
    public static Tips validPhoneno(String phoneno){
        Tips tips = new Tips("手机号格式验证成功");
        String reg="^(\\d{11})$";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher=pattern.matcher(phoneno);
        if(matcher.matches()==false){
            tips.setErrMsg("手机号格式不正确");
        }
        return tips;
    }

    public static void main(String[] args) {
        String emil="c-_yc@163_-xxx.com";
        validEmail(emil);
        String phoneno="13610336198";
        validPhoneno(phoneno);
        Instant instant=Instant.now();
        Long time=instant.toEpochMilli();

        System.out.println(time);
        Date date=new Date();
        System.out.println(date.getTime());

    }
}
