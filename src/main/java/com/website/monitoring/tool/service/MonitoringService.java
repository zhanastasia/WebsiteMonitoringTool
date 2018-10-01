package com.website.monitoring.tool.service;

import com.website.monitoring.tool.entity.URLParameters;
import com.website.monitoring.tool.model.WebsiteMonitoringModel;

import java.net.HttpURLConnection;

public interface MonitoringService {

    HttpURLConnection getConnection(String urlStr);
    WebsiteMonitoringModel startPingServer(URLParameters params);

}
