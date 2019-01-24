package com.sunshine.provider.controller;

import com.netflix.hystrix.HystrixThreadPool;
import com.sunshine.provider.api.ProductClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@RestController
public class OrderController {
    @Autowired
    private ProductClientApi productClient;

    @RequestMapping("/product")
    public String getProductInfo(int threadNum){
//        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
//        for(int i=0;i<threadNum;i++){
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        countDownLatch.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    String re = productClient.index();
//                    System.out.println(re);
//                }
//            }).start();
//            countDownLatch.countDown();
//        }
//
//        return "";
        return "get from pmc:"+productClient.index();

    }

}
