package com.kfzteile24.spark.injector;


import com.google.inject.Injector;
import com.kfzteile24.core.InjectorFactory;
import org.apache.spark.api.java.function.VoidFunction;

abstract public class VoidFunctionInjector<T> implements VoidFunction<T> {
    private static Injector injector;

    public static Injector getInjector(InjectorFactory injectorFactory) {
        return injector == null ? injectorFactory.getInjecotr() : injector;
    }
}