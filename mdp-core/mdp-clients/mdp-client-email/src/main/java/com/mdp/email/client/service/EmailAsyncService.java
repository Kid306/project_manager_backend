package com.mdp.email.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailAsyncService {

    @Autowired
    EmailService emailService;

    Logger logger= LoggerFactory.getLogger(EmailService.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    /**
     * 普通邮件发送
     */
    public void sendSimpleMail(String subject,String fromEmail,String toEmail,String text) {

             executorService.execute(new Runnable() {
                 @Override
                 public void run() {
                     try {
                        emailService.sendSimpleMail(subject,fromEmail,toEmail,text);
                     }catch (Exception e){
                         logger.error("send email error",e);
                     }
                 }
             });



    }
}
