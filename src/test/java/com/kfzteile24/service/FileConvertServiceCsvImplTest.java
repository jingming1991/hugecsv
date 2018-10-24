package com.kfzteile24.service;

import com.kfzteile24.entity.FilePayLoad;
import com.kfzteile24.service.impl.FileConvertServiceCsvImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

public class FileConvertServiceCsvImplTest {

    private FileConvertServiceCsvImpl fileConvertServiceCsv;

    @Before
    public void setUp() {
        fileConvertServiceCsv = new FileConvertServiceCsvImpl();
    }

    @Test
    public void convertOne() {
        String s1 = "\"jing\";\"ming\";\"jingming1991@gmail.com\"";
        FilePayLoad filePayLoadOk = fileConvertServiceCsv.convertOne(s1);
        Assert.assertNotNull(filePayLoadOk);
        Assert.assertEquals("jing", filePayLoadOk.getFirstName());
        Assert.assertEquals("ming", filePayLoadOk.getLastName());
        Assert.assertEquals("jingming1991@gmail.com", filePayLoadOk.getEmail());

        String s2 = "jing;ming;jingming1991@gmail.com";
        FilePayLoad noQuotes = fileConvertServiceCsv.convertOne(s2);
        Assert.assertNull(noQuotes);


        String s3 = "\"jing\";\"ming\";\"hello world\"";
        FilePayLoad badMail = fileConvertServiceCsv.convertOne(s3);
        Assert.assertNull(badMail);

        String s4 = "\"ji;ng\";\"m;in;g\";\"jing;ming1991@gmail.com\"";
        FilePayLoad moreColon = fileConvertServiceCsv.convertOne(s4);
        Assert.assertNull(moreColon);

    }

    @Test
    public void isValidPayLoadError() {
        FilePayLoad noFirstName = new FilePayLoad("", "ming", "jingming1991@gmail.com");
        FilePayLoad noLastName = new FilePayLoad("jing", null, "jingming1991@gmail.com");
        FilePayLoad badMail = new FilePayLoad("jing", "ming", "hello world");
        Stream.of(null, noFirstName, noLastName, badMail)
                .map(fileConvertServiceCsv::isValidPayLoad)
                .forEach(Assert::assertFalse);
    }

    @Test
    public void isValidEmail() {
        Assert.assertFalse(fileConvertServiceCsv.isValidEmail("hello world"));
        Assert.assertFalse(fileConvertServiceCsv.isValidEmail("jingming@gmail . com"));
        Assert.assertFalse(fileConvertServiceCsv.isValidEmail("#$%@gmail.com"));
        Assert.assertFalse(fileConvertServiceCsv.isValidEmail("111@gmail"));
        Assert.assertTrue(fileConvertServiceCsv.isValidEmail("jingming@gmail.com"));
        Assert.assertTrue(fileConvertServiceCsv.isValidEmail("jing123@gmail.com"));
        Assert.assertTrue(fileConvertServiceCsv.isValidEmail("ming.jing@gmail.com"));
        Assert.assertTrue(fileConvertServiceCsv.isValidEmail("ming.jing@gmail.com.cn"));
    }

    @Test
    public void parseFilePayLoadString() {
        String s1 = "jing;ming;jingming1991@gmail.com";
        List<String> stringNoQuotes = fileConvertServiceCsv.parseFilePayLoadString(s1);
        Assert.assertEquals(0, stringNoQuotes.size());

        String s2 = "\"jing\"\"ming\"\"jingming1991@gmail.com\"";
        List<String> stringNoColon = fileConvertServiceCsv.parseFilePayLoadString(s2);
        Assert.assertEquals(1, stringNoColon.size());
        Assert.assertEquals("jing\"\"ming\"\"jingming1991@gmail.com", stringNoColon.get(0));

        String s3 = "\"jing\";\"ming\";\"jingming1991@gmail.com\"";
        List<String> stringOk = fileConvertServiceCsv.parseFilePayLoadString(s3);
        Assert.assertEquals(3, stringOk.size());
        Assert.assertEquals("jing", stringOk.get(0));
        Assert.assertEquals("ming", stringOk.get(1));
        Assert.assertEquals("jingming1991@gmail.com", stringOk.get(2));

        String s4 = "\"ji\"ng\";\"mi\"ng\";\"jing\"ming1991@gmail.com\"";
        List<String> stringMoreQuotes = fileConvertServiceCsv.parseFilePayLoadString(s4);
        Assert.assertEquals(3, stringMoreQuotes.size());
        Assert.assertEquals("ji\"ng", stringMoreQuotes.get(0));
        Assert.assertEquals("mi\"ng", stringMoreQuotes.get(1));
        Assert.assertEquals("jing\"ming1991@gmail.com", stringMoreQuotes.get(2));

        String s5 = "\"ji;ng\";\"m;in;g\";\"jing;ming1991@gmail.com\"";
        List<String> stringMoreColon = fileConvertServiceCsv.parseFilePayLoadString(s5);
        Assert.assertNotEquals(3, stringMoreColon.size());
    }
}