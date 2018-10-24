package com.kfzteile24.service;

import com.kfzteile24.entity.FilePayLoad;

public interface IMailSendService {

    void sendMail(FilePayLoad filePayLoad);
}
