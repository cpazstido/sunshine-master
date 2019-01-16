package com.sunshine.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("api/index")
    public String apiIndex(){
        return "pmc api index";
    }

    @RequestMapping("index")
    public String index(){
        return "pmc index";
    }
}
