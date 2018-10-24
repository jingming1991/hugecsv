package com.kfzteile24.spark.stage;


import com.google.inject.Injector;
import com.kfzteile24.core.InjectorFactory;
import com.kfzteile24.entity.FilePayLoad;
import com.kfzteile24.service.IMailSendService;
import com.kfzteile24.spark.injector.VoidFunctionInjector;
import org.apache.spark.util.LongAccumulator;

import java.util.Iterator;

public class SendMailStage extends VoidFunctionInjector<Iterator<FilePayLoad>> {

    private InjectorFactory injectorFactory;
    private LongAccumulator sendCounter;

    public SendMailStage(InjectorFactory injectorFactory, LongAccumulator sendCounter) {
        this.injectorFactory = injectorFactory;
        this.sendCounter = sendCounter;
    }

    @Override
    public void call(Iterator<FilePayLoad> filePayLoadIterator) {
        Injector injector = getInjector(injectorFactory);
        IMailSendService mailSendService = injector.getInstance(IMailSendService.class);
        filePayLoadIterator.forEachRemaining(f -> {
            sendCounter.add(1L);
            mailSendService.sendMail(f);
        });
    }
}
