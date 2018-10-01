package com.website.monitoring.tool.repository;

import com.website.monitoring.tool.entity.URLParameters;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class URLParametersRepositoryTest extends Assert {

    @Autowired
    @Qualifier("URLParametersRepository")
    private URLParametersRepository repository;

    private static URLParameters urlParams1;
    private static URLParameters urlParams2;
    private static URLParameters urlParams3;

    @BeforeClass
    public static void init() {
        urlParams1 = new URLParameters();
        urlParams2 = new URLParameters();
        urlParams3 = new URLParameters();

        urlParams1.setUrl("https://www.google.com/");
        urlParams1.setMonitoringPeriod(5);
        urlParams1.setResponseTime(150);
        urlParams1.setResponseCode(200);
        urlParams1.setMinResponseSize(100);
        urlParams1.setMaxResponseSize(10000);

        urlParams2.setUrl("https://ua.sinoptik.ua/");
        urlParams2.setMonitoringPeriod(5);
        urlParams2.setResponseTime(100);
        urlParams2.setResponseCode(200);
        urlParams2.setMinResponseSize(100);
        urlParams2.setMaxResponseSize(17000);

        urlParams3.setUrl("https://habr.com/");
        urlParams3.setMonitoringPeriod(5);
        urlParams3.setResponseTime(60);
        urlParams3.setResponseCode(200);
        urlParams3.setMinResponseSize(100);
        urlParams3.setMaxResponseSize(20000);
    }

    @Test
    public void findAllURLsTest_true() {
        List<String> urls = Arrays.asList(urlParams1.getUrl(), urlParams2.getUrl(), urlParams3.getUrl());
        List<String> urlsFromRepo = repository.findAllURLs();

        assertEquals(urlsFromRepo.get(0), urls.get(0));
        assertEquals(urlsFromRepo.get(1), urls.get(1));
        assertEquals(urlsFromRepo.get(2), urls.get(2));
    }

    @Test
    public void findAllURLsTest_false() {
        List<String> urls = Arrays.asList(urlParams3.getUrl(), urlParams1.getUrl());
        List<String> urlsFromRepo = repository.findAllURLs();

        assertNotEquals(urlsFromRepo.get(0), urls.get(0));
        assertNotEquals(urlsFromRepo.get(1), urls.get(1));
    }
}

