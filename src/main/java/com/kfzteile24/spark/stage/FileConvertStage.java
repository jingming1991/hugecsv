package com.kfzteile24.spark.stage;


import com.google.inject.Injector;
import com.kfzteile24.core.InjectorFactory;
import com.kfzteile24.entity.FilePayLoad;
import com.kfzteile24.service.IFileConvertService;
import com.kfzteile24.spark.injector.FlatMapFunctionInjector;

import java.util.*;

public class FileConvertStage extends FlatMapFunctionInjector<Iterator<String>, FilePayLoad> {


    private InjectorFactory injectorFactory;

    public FileConvertStage(InjectorFactory injectorFactory) {
        this.injectorFactory = injectorFactory;
    }

    @Override
    public Iterator<FilePayLoad> call(Iterator<String> stringIterator) {
        Injector injector = getInjector(injectorFactory);
        IFileConvertService fileConvert = injector.getInstance(IFileConvertService.class);

        List<FilePayLoad> results = new ArrayList<>();
        stringIterator.forEachRemaining(s -> {
            FilePayLoad filePayLoad = fileConvert.convertOne(s);
            if (filePayLoad != null) {
                results.add(filePayLoad);
            }
        });

        return results.iterator();
    }
}
