package com.kfzteile24.service.impl;

import com.google.inject.Singleton;
import com.kfzteile24.entity.FilePayLoad;
import com.kfzteile24.service.IFileConvertService;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Singleton
public class FileConvertServiceCsvImpl implements IFileConvertService, Serializable {


    @Override
    public FilePayLoad convertOne(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        List<String> stringList = parseFilePayLoadString(s);
        FilePayLoad filePayLoad = new FilePayLoad(stringList.get(0), stringList.get(1), stringList.get(2));
        return isValidPayLoad(filePayLoad) ? filePayLoad : null;
    }


    public List<String> parseFilePayLoadString(String filePayLoad) {
        System.out.println(filePayLoad);
        String[] split = filePayLoad.split(";", 3);
        return Arrays.stream(split).collect(Collectors.toList());
    }


    public boolean isValidPayLoad(FilePayLoad filePayLoad) {
        return filePayLoad != null &&
                StringUtils.isNotBlank(filePayLoad.getFirstName()) &&
                StringUtils.isNotBlank(filePayLoad.getLastName()) &&
                isValidEmail(filePayLoad.getEmail());
    }


    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
