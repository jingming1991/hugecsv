package com.kfzteile24.spark;

import com.kfzteile24.core.InjectorFactory;
import com.kfzteile24.core.MyConfigModule;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;

@Ignore
public class SendMailByHugeFileSparkTest {

    @Test
    public void run() {
        String path = getClass().getClassLoader().getResource("mail.csv").getPath();
        InjectorFactory injectorFactory = new InjectorFactory(Collections.singletonList(new MyConfigModule()));
        SendMailByHugeFileSpark sendMailByHugeFileSpark = new SendMailByHugeFileSpark();
        sendMailByHugeFileSpark.init(injectorFactory);
        sendMailByHugeFileSpark.run(path);
    }
}