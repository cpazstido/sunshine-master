package com.sunshine.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class IndexController {

    @RequestMapping("api/index")
    public String apiIndex(){
        try {
            Random random = new Random();
            int sTime = random.nextInt(500);
//            System.out.println(sTime);
            Thread.sleep(sTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("api/index");
        return "pmc api index";
    }

    @RequestMapping("api/index1")
    public String apiIndex1(){
        System.out.println("api/index1");
        return "pmc api index1";
    }

    @RequestMapping("index")
    public String index(){
        return "pmc index";
    }
}
