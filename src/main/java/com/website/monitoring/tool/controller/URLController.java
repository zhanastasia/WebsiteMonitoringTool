package com.website.monitoring.tool.controller;

import com.website.monitoring.tool.entity.URLParameters;
import com.website.monitoring.tool.model.WebsiteMonitoringModel;
import com.website.monitoring.tool.service.MonitoringService;
import com.website.monitoring.tool.service.URLParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class URLController {

    @Autowired
    @Qualifier("URLParametersService")
    private URLParametersService urlParamsService;

    @Autowired
    @Qualifier("monitoringService")
    private MonitoringService monitoringService;

    @Value("${api.base.url}")
    private String baseUrl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView websiteMonitoring(ModelMap model) {

        List<URLParameters> urlParams = urlParamsService.getAllURLsParam();

        List<WebsiteMonitoringModel> result = urlParams.parallelStream()
                .map(s -> monitoringService.startPingServer(s))
                .collect(Collectors.toList());

        return new ModelAndView("websiteMonitoring", "Model", result);
    }

    //TODO : fix problem with 415Unsupported Media Type (Content type 'application/json;charset=UTF-8' not supported)
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String post(@RequestBody URLParameters urlParams)
    {
        //URLParameters newURLConfigs = new Gson().fromJson(urlParameters, URLParameters.class);
        //URLParameters URLParam = urlParamsService.add(urlParameters);

        return "redirect:/";
    }


}
