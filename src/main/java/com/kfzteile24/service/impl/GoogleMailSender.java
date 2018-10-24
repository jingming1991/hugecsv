package com.kfzteile24.service.impl;


import com.kfzteile24.entity.FilePayLoad;
import com.kfzteile24.service.IMailSendService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class GoogleMailSender implements IMailSendService {

    public Session getGMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jingming1991@gmail.com", "jingming123");
            }
        });
        return session;
    }

    @Override
    public void sendMail(FilePayLoad filePayLoad) {
        Session session = getGMailSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject("test");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress("jingming1991@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(filePayLoad.getEmail()));
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
