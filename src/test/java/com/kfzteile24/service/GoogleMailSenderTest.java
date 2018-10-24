package com.kfzteile24.service;

import com.kfzteile24.entity.FilePayLoad;
import com.kfzteile24.service.impl.GoogleMailSender;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class GoogleMailSenderTest {
    @Test
    public void sendMail() {
        GoogleMailSender googleMailSender = new GoogleMailSender();
        googleMailSender.sendMail(new FilePayLoad("ming", "jing", "jingming1991@gmail.com"));
    }
}