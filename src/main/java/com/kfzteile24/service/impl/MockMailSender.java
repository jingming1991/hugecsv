package com.kfzteile24.service.impl;

import com.google.inject.Singleton;
import com.kfzteile24.entity.FilePayLoad;
import com.kfzteile24.service.IMailSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Singleton
public class MockMailSender implements IMailSendService, Serializable {

    private static Logger LOG = LoggerFactory.getLogger(MockMailSender.class);

    @Override
    public void sendMail(FilePayLoad filePayLoad) {
        String email = filePayLoad.getEmail();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("send mail to : " + filePayLoad.getFirstName() + filePayLoad.getLastName() + ", " + email);
    }
}
