package com.kfzteile24.spark.injector;

import com.google.inject.Injector;
import com.kfzteile24.core.InjecotrFactory;
import org.apache.spark.api.java.function.FlatMapFunction;

abstract public class FlatMapFunctionInjector<T, R> implements FlatMapFunction<T, R> {

    private static Injector injector;

    public static Injector getInjector(InjecotrFactory injectorFactory) {
        return injector == null ? injectorFactory.getInjecotr() : injector;
    }
}