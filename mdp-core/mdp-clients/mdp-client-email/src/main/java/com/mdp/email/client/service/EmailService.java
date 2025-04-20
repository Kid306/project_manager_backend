package com.mdp.email.client.service;

import com.mdp.core.err.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;
@Service
public class EmailService {

    Logger logger= LoggerFactory.getLogger(EmailService.class);

    @Value("${spring.mail.username:}")
    String fromEmail="";

    @Autowired(required = false)
    JavaMailSender javaMailSender;

    public void sendSimpleMail(String subject,String toEmail,String text){
        sendSimpleMail(subject,fromEmail,toEmail,text  );
    }

    /**
     * 普通邮件发送
     */
    public void sendSimpleMail(String subject,String fromEmail,String toEmail,String text) {

        // 构建一个邮件对象
        MimeMessage message = javaMailSender.createMimeMessage();


        // 设置邮件主题
        try {
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(fromEmail);
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
            logger.error("send email error",e);
            throw new BizException("send-email-error",e.getMessage());
        }

    }
}
