package com.sunshine.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class IndexController {
    @Value("${test.dev}")
    private String msg;

    @RequestMapping("index")
    public String index(){
        return "config client index "+msg;
    }
}
