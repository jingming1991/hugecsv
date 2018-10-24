package com.kfzteile24.spark;

import com.kfzteile24.core.InjecotrFactory;
import com.kfzteile24.core.MyConfigModule;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Ignore
public class SendMailByHugeFileSparkTest {

    @Test
    public void run() {

        InjecotrFactory injecotrFactory = new InjecotrFactory(Collections.singletonList(new MyConfigModule()));
        SendMailByHugeFileSpark sendMailByHugeFileSpark = new SendMailByHugeFileSpark();
        sendMailByHugeFileSpark.init(injecotrFactory);
        sendMailByHugeFileSpark.run("/home/dev/IdeaProjects/hugecsv/src/test/resources/mail.csv");
    }
}