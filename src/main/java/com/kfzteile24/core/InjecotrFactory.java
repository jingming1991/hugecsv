package com.kfzteile24.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Singleton;

import java.io.Serializable;

@Singleton
public class InjecotrFactory implements Serializable {

    private Injector injector;
    private Iterable<? extends Module> modules;

    public InjecotrFactory(Iterable<? extends Module> modules) {
        this.modules = modules;
    }

    public Injector getInjecotr() {
        return this.injector == null ? Guice.createInjector(modules) : this.injector;
    }
}
