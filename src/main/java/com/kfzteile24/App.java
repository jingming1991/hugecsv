package com.kfzteile24;

import com.kfzteile24.core.InjecotrFactory;
import com.kfzteile24.core.MyConfigModule;
import com.kfzteile24.spark.SendMailByHugeFileSpark;

import java.util.Collections;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        InjecotrFactory injecotrFactory = new InjecotrFactory(Collections.singletonList(new MyConfigModule()));
        SendMailByHugeFileSpark sendMailByHugeFileSpark = new SendMailByHugeFileSpark();
        sendMailByHugeFileSpark.init(injecotrFactory);
        sendMailByHugeFileSpark.run();
    }
}
