package com.kfzteile24.service;

import com.kfzteile24.service.impl.GoogleMailSender;
import org.junit.Test;

public class GoogleMailSenderTest {
    @Test
    public void haha() throws Exception {
        GoogleMailSender googleMailSender = new GoogleMailSender();
        googleMailSender.senmai();
        System.out.println(1);
    }
}