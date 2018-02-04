package com.example.demo.controller;

import com.configurationclient.client.ConfigurationReader;
import com.configurationclient.model.ConfigResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class DemoController {

    private final ConfigurationReader configurationReader;

    @Autowired
    public DemoController(ConfigurationReader configurationReader) {
        this.configurationReader = configurationReader;
    }

    @GetMapping(value = "getconfig/{key}")
    public String getConfig(@PathVariable("key") String key) {
        return ((ConfigResponse)configurationReader.getValueFromFeign(key)).getValue();
    }

    @GetMapping(value = "getconfigFromCache/{key}")
    public String getConfigValueFromCache(@PathVariable("key") String key) {
        return configurationReader.getValueFromCache(key);
    }

    @GetMapping(value = "getAllConfigs")
    public String getAllConfigs() {
        return configurationReader.getAllValueFromFeign().toString();
    }
}
