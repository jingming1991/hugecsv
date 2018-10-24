package com.kfzteile24.spark.stage;


import com.google.inject.Injector;
import com.kfzteile24.core.InjecotrFactory;
import com.kfzteile24.entity.FilePayLoad;
import com.kfzteile24.service.IMailSendService;
import com.kfzteile24.spark.injector.VoidFunctionInjector;
import org.apache.spark.util.LongAccumulator;

import java.util.Iterator;

public class SendMailStage extends VoidFunctionInjector<Iterator<FilePayLoad>> {

    private InjecotrFactory injecotrFactory;
    private LongAccumulator sendCounter;

    public SendMailStage(InjecotrFactory injecotrFactory, LongAccumulator sendCounter) {
        this.injecotrFactory = injecotrFactory;
        this.sendCounter = sendCounter;
    }

    @Override
    public void call(Iterator<FilePayLoad> filePayLoadIterator) {
        Injector injector = getInjector(injecotrFactory);
        IMailSendService mailSendService = injector.getInstance(IMailSendService.class);
        filePayLoadIterator.forEachRemaining(f -> {
            sendCounter.add(1L);
            mailSendService.sendMail(f);
        });
    }
}
