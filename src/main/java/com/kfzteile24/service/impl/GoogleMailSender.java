package com.kfzteile24.service.impl;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class GoogleMailSender {

    public void senmai() throws Exception {
        Session session = getGMailSession();
        MimeMessage message = new MimeMessage(session);
        message.setSubject("test");
        message.setSentDate(new Date());
        message.setFrom(new InternetAddress("jingming1991@gmail.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("jingming1991@gmail.com"));
        message.setContent("test11111", "text/html;charset=utf-8");
        Transport.send(message);


    }

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
}
