package com.kfzteile24.service;

import com.kfzteile24.entity.FilePayLoad;

import javax.mail.MessagingException;
import java.util.List;

public interface IMailSendService {

    /**
     * send mail to FilePayLoad.email
     *
     * @param filePayLoad
     */
    void sendMail(FilePayLoad filePayLoad);

}
