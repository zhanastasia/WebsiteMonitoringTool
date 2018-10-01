package com.website.monitoring.tool.service;

import com.website.monitoring.tool.entity.URLParameters;

import java.util.List;

public interface URLParametersService {

    URLParameters add(URLParameters param);
    boolean delete(Long id);
    URLParameters change(URLParameters params);
    URLParameters get(long id);
    List<String> getAllURLs();
    List<URLParameters> getAllURLsParam();
}
