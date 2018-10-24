package com.kfzteile24.core;


import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.kfzteile24.service.IFileConvertService;
import com.kfzteile24.service.IMailSendService;
import com.kfzteile24.service.impl.FileConvertServiceCsvImpl;
import com.kfzteile24.service.impl.MockMailSender;

import java.io.Serializable;

@Singleton
public class MyConfigModule extends AbstractModule implements Serializable {

    @Override
    protected void configure() {
        bind(IFileConvertService.class).to(FileConvertServiceCsvImpl.class);
        bind(IMailSendService.class).to(MockMailSender.class);
    }
}
