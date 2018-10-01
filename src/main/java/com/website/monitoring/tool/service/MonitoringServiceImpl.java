package com.website.monitoring.tool.service;

import com.website.monitoring.tool.repository.URLParametersRepository;
import com.website.monitoring.tool.entity.URLParameters;
import com.website.monitoring.tool.entity.URLStatus;
import com.website.monitoring.tool.model.WebsiteMonitoringModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

@Service("monitoringService")
public class MonitoringServiceImpl implements MonitoringService {

    @Autowired
    @Qualifier("URLParametersRepository")
    private URLParametersRepository dao;

    @Autowired
    @Qualifier("URLParametersService")
    private URLParametersService service;

    private HttpURLConnection connection;
    private URL url;

    public HttpURLConnection getConnection(String urlStr) {
        try {
            url = new URL(urlStr);

            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            return connection;
        } catch (IOException e) {
            e.printStackTrace();
            connection.disconnect();
        }
        return null;
    }

    public WebsiteMonitoringModel startPingServer(URLParameters params) {
        String urlStr = params.getUrl();
        long monitoringPeriod = params.getMonitoringPeriod();
        connection = getConnection(urlStr);

        Results results = new Results(connection);
        WebsiteMonitoringModel websiteMonitoringModel = results.getResults();
        websiteMonitoringModel.setUrl(urlStr);
        websiteMonitoringModel.setMonitoringPeriod(monitoringPeriod);

        return validationResults(websiteMonitoringModel, params);
    }

    private WebsiteMonitoringModel validationResults(WebsiteMonitoringModel model,
                                                     URLParameters params) {

        if (model.getResponseCode() != 200) {
            model.setStatus(URLStatus.CRITICAL);
            model.setAbout("Response code isn`t 200: " + model.getResponseCode());
        }else if (model.getResponseSize() < params.getMinResponseSize() ||
                model.getResponseSize() > params.getMaxResponseSize()) {
            model.setStatus(URLStatus.CRITICAL);
            model.setAbout("Response size out of limit : " + model.getResponseSize() +
                    " (limit is [" + params.getMinResponseSize() + ", " + params.getMaxResponseSize() + "])");
        }else if (model.getResponseTime() > params.getResponseTime()){
            model.setStatus(URLStatus.CRITICAL);
            model.setAbout("Response time out of limit: " + model.getResponseTime() +
                    " (limit is "+ params.getResponseTime() + ")");
        }else if (model.getResponseTime() == params.getResponseTime()) {
            model.setStatus(URLStatus.WARNING);
            model.setAbout("Response time is equal to our limit: " + model.getResponseTime() +
                    " (limit is "+ params.getResponseTime() + ")");
        }else if (model.getResponseTime() < params.getResponseTime()) {
            model.setStatus(URLStatus.OK);
            model.setAbout("It`s Ok");
        }

        if (params.getSubstring() == null || params.getSubstring().equals("")) {
            model.setSubstring("Substring is empty");
            model.setStatus(URLStatus.CRITICAL);
            model.setAbout("Substring is empty");
        } else {
            if (model.getContent().contains(params.getSubstring())) {
                model.setSubstring("Found \"" + params.getSubstring() + "\"");
            }
        }

        return model;
    }


    private class Results {

        private HttpURLConnection connection;

        public Results(HttpURLConnection connection) {
            this.connection = connection;
        }

        WebsiteMonitoringModel getResults() {
            try {
                long timeStart = System.currentTimeMillis();
                connection.connect();
                long timeEnd = System.currentTimeMillis();
                long responseTime = timeEnd - timeStart;

                connection.setConnectTimeout(10000);
                BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
                int responseSize = inputStream.available();
                int responseCode = connection.getResponseCode();
                String responseMessage = connection.getResponseMessage();

                BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        Charset.forName("UTF-8")));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
                String responseContent = new String(sb);

                return new WebsiteMonitoringModel(responseTime, responseSize, responseCode,
                        responseMessage, URLStatus.NOT_VALIDATED, responseContent);

            } catch (IOException e) {
                connection.disconnect();
                throw new IllegalArgumentException("Cannot get results ", e);
            }
        }
    }
}
