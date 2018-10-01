package com.website.monitoring.tool.service;

import com.website.monitoring.tool.repository.URLParametersRepository;
import com.website.monitoring.tool.entity.URLParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("URLParametersService")
public class URLParametersServiceImpl implements URLParametersService {

    @Autowired
    @Qualifier("URLParametersRepository")
    private URLParametersRepository repository;

    private List<String> urls;
    private List<URLParameters> urlParams;

    @Override
    public URLParameters add(URLParameters param) {
        return repository.save(param);
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);

        return repository.existsById(id);
    }

    @Override
    public URLParameters change(URLParameters params) {
        URLParameters paramsToUpdate = repository.getOne(params.getId());

        paramsToUpdate.setUrl(params.getUrl());
        paramsToUpdate.setMonitoringPeriod(params.getMonitoringPeriod());
        paramsToUpdate.setResponseTime(params.getResponseTime());
        paramsToUpdate.setResponseCode(params.getResponseCode());
        paramsToUpdate.setMinResponseSize(params.getMinResponseSize());
        paramsToUpdate.setMaxResponseSize(params.getMaxResponseSize());

        return repository.save(paramsToUpdate);
    }

    @Override
    public URLParameters get(long id){
        return repository.getOne(id);
    }

    @Override
    public List<String> getAllURLs() {
        urls = new ArrayList<>();

        return repository.findAllURLs();
    }

    @Override
    public List<URLParameters> getAllURLsParam() {
        urlParams = new ArrayList<>();
        urlParams.addAll(repository.findAll());

        return urlParams;
    }
}
