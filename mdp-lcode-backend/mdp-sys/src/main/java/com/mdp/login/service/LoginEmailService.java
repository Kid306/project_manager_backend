package com.mdp.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class LoginEmailService {

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 普通邮件发送
     */
    @Async
    public void sendSimpleMail(String subject,String formEmail,String toEmail,String text) {
        // 构建一个邮件对象
        MimeMessage message = javaMailSender.createMimeMessage();


        // 设置邮件主题
        try {
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(formEmail);
            // 设置邮件发送者，这个跟application.yml中设置的要一致
            // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
            // message.setTo("10*****16@qq.com","12****32*qq.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(subject);
            // 设置邮件抄送人，可以有多个抄送人
            //message.setCc("12****32*qq.com");
            // 设置隐秘抄送人，可以有多个
            //message.setBcc("7******9@qq.com");
            // 设置邮件发送日期
            mimeMessageHelper.setSentDate(new Date());
            // 设置邮件的正文
            mimeMessageHelper.setText(text,true);
            // 发送邮件
            javaMailSender.send(message);
        }catch (Exception e){

        }

    }
}
