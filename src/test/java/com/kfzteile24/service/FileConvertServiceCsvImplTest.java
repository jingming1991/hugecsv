package com.kfzteile24.service;

import com.kfzteile24.entity.FilePayLoad;
import com.kfzteile24.service.impl.FileConvertServiceCsvImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class FileConvertServiceCsvImplTest {

    private FileConvertServiceCsvImpl fileConvertServiceCsv;

    @Before
    public void setUp() throws Exception {
        fileConvertServiceCsv = new FileConvertServiceCsvImpl();
    }

    @Test
    public void convertOne() {
    }

    @Test
    public void isValidPayLoad() {
        FilePayLoad noFitstName = new FilePayLoad("", "ming", "jingming1991@gmail.com");
        FilePayLoad noLastName = new FilePayLoad("jing", null, "jingming1991@gmail.com");
        FilePayLoad badMail = new FilePayLoad("jing", "ming", "hello world");
        Stream.of(noFitstName, noLastName, badMail)
                .map(fileConvertServiceCsv::isValidPayLoad)
                .forEach(Assert::assertFalse);
    }


}