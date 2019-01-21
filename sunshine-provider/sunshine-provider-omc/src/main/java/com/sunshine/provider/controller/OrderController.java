package com.sunshine.provider.controller;

import com.sunshine.provider.api.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private ProductClient productClient;

    @RequestMapping("/product")
    public String getProductInfo(){
        return "get from pmc:"+productClient.index();
    }
}
