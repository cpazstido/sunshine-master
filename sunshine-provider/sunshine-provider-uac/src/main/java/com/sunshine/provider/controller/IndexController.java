package com.sunshine.provider.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("index")
    public String index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "uac index";
    }
}
