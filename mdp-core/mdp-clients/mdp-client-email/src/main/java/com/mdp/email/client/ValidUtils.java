package com.mdp.email.client;


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
        String reg="\\w+@(\\w+\\.){1,3}\\w+";
        Pattern pattern=Pattern.compile(reg);
        Matcher matcher=pattern.matcher(email);
        if(matcher.matches()==false){
            tips.setErrMsg("邮件格式不正确");
        }
        return tips;
    }

    public static void main(String[] args) {
        String emil="cyc@163.com";
        validEmail(emil);
        String phoneno="13610336198";
        Instant instant=Instant.now();
        Long time=instant.toEpochMilli();

        System.out.println(time);
        Date date=new Date();
        System.out.println(date.getTime());

    }
}
