package com.website.monitoring.tool.model;

import com.website.monitoring.tool.entity.URLStatus;
import lombok.Data;

@Data
public class WebsiteMonitoringModel {

    private String url;
    private long responseTime;
    private int responseSize;
    private int responseCode;
    private String responseMessage;
    private URLStatus status;
    private long monitoringPeriod;
    private String about;
    private String substring;
    private String content;

    public WebsiteMonitoringModel(long responseTime, int responseSize,
                                  int responseCode, String responseMessage,
                                  URLStatus status, String content) {
        this.responseTime = responseTime;
        this.responseSize = responseSize;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.status = status;
        this.content = content;
    }
}
