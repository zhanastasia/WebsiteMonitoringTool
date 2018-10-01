package com.website.monitoring.tool.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "url_parameters")
public class URLParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "url")
    private String url;

    @Column(name = "monitoring_period")
    private int monitoringPeriod;

    @Column(name = "response_time")
    private long responseTime;

    @Column(name = "response_code")
    private int responseCode;

    @Column(name = "min_response_size")
    private int minResponseSize;

    @Column(name = "max_response_size")
    private int maxResponseSize;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private URLStatus status;

    @Column(name = "substring")
    private String substring;

    @Override
    public String toString() {
        return  "id = "                 + id                + "\n" +
                "url = "                + url               + "\n" +
                "monitoringPeriod = "   + monitoringPeriod  + "\n" +
                "responseTime = "       + responseTime      + "\n" +
                "responseCode = "       + responseCode      + "\n" +
                "minResponseSize = "    + minResponseSize   + "\n" +
                "maxResponseSize = "    + maxResponseSize   + "\n" +
                "status = "             + status;
    }
}
