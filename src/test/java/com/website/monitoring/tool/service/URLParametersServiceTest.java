package com.website.monitoring.tool.service;

import com.website.monitoring.tool.entity.URLParameters;
import com.website.monitoring.tool.repository.URLParametersRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {URLParametersServiceTest.URLParametersServiceTestConfiguration.class})
public class URLParametersServiceTest extends Assert {

    @Configuration
    public class URLParametersServiceTestConfiguration {

        @Bean
        public URLParametersService fooService() {
            return new URLParametersServiceImpl();
        }

        @Bean
        public URLParametersRepository fooRepository() {
            return Mockito.mock(URLParametersRepository.class);
        }
    }

    @MockBean
    private URLParametersRepository repository;

    @Autowired
    private URLParametersService service;

    private static URLParameters urlParams1;
    private static URLParameters urlParams2;


    @BeforeClass
    public static void init() {
        urlParams1 = new URLParameters();
        urlParams2 = new URLParameters();

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
    }

    @Before
    public void setUp() {
       Mockito.when(repository.findParamsByURL(urlParams1.getUrl()))
                .thenReturn(urlParams1);
        Mockito.when(repository.findParamsByURL(urlParams2.getUrl()))
                .thenReturn(urlParams2);
    }

    @Ignore //TODO : fix problem with mocked repository and not autowired service
    @Test
    public void addParamTest_true() {
        urlParams2 = new URLParameters();

        urlParams2.setUrl("https://www.google.com/");
        urlParams2.setMonitoringPeriod(5);
        urlParams2.setResponseTime(150);
        urlParams2.setResponseCode(200);
        urlParams2.setMinResponseSize(100);
        urlParams2.setMaxResponseSize(10000);
        System.out.println("urlParams2 : " + urlParams2);

        URLParameters urlsParamFromRepo = repository.findParamsByURL(urlParams2.getUrl());
        System.out.println("urlsParamFromRepo : " + urlsParamFromRepo);
        urlsParamFromRepo.setMinResponseSize(500);

        URLParameters urlsParamFromService = service.change(urlsParamFromRepo);

        System.out.println(urlsParamFromService);
        System.out.println(urlsParamFromRepo);

        assertEquals(urlsParamFromService, urlsParamFromRepo);
    }

    @Ignore //TODO : fix problem with mocked repository and not autowired service
    @Test
    public void addParamTest_false() {
        URLParameters paramFromService = service.add(urlParams1);
        URLParameters paramFromRepo = repository.findParamsByURL(urlParams2.getUrl());

        System.out.println(paramFromService);
        System.out.println(paramFromRepo);

        assertNotEquals(paramFromRepo.getUrl(), paramFromService.getUrl());
    }
}

