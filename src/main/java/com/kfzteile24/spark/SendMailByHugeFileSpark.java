package com.kfzteile24.spark;


import com.kfzteile24.core.InjectorFactory;
import com.kfzteile24.entity.FilePayLoad;
import com.kfzteile24.service.impl.MockMailSender;
import com.kfzteile24.spark.stage.FileConvertStage;
import com.kfzteile24.spark.stage.SendMailStage;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.LongAccumulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMailByHugeFileSpark {
    private static Logger LOG = LoggerFactory.getLogger(MockMailSender.class);

    private JavaSparkContext javaSparkContext;
    private InjectorFactory injectorFactory;

    public void init(InjectorFactory injectorFactory) {
        SparkConf sparkConf = new SparkConf().setAppName("FileConvert").setMaster("local[1]");
        javaSparkContext = new JavaSparkContext(sparkConf);
        this.injectorFactory = injectorFactory;
    }

    public void run(String fileUrl) {
        LongAccumulator sendCounter = javaSparkContext.sc().longAccumulator("Totally send mail");
        JavaRDD<String> stringJavaRDD = javaSparkContext.textFile(fileUrl);
        JavaRDD<FilePayLoad> filePayLoads = stringJavaRDD.mapPartitions(new FileConvertStage(injectorFactory));
        filePayLoads = repartition(filePayLoads.persist(StorageLevel.MEMORY_AND_DISK_2()));
        filePayLoads.foreachPartition(new SendMailStage(injectorFactory, sendCounter));
        LOG.info("Totally send mail :" + sendCounter.value());
    }

    private JavaRDD<FilePayLoad> repartition(JavaRDD<FilePayLoad> filePayLoads) {
        long count = filePayLoads.count();
        int partitions = (int) Math.max(1, count / 5000);
        return filePayLoads.repartition(partitions);
    }

}
