package com.kfzteile24.service;

import com.kfzteile24.entity.FilePayLoad;

public interface IFileConvertService {

    /**
     * convert csv file line by line to Object(FilePayLoad)
     *
     * @param s
     * @return
     */
    FilePayLoad convertOne(String s);
}
