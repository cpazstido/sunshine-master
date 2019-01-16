package com.sunshine.provider.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("sunshine-provider-pmc")
public interface ProductClient {

    @RequestMapping("api/index")
    String index();
}
