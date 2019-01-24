package com.sunshine.provider.api;

import com.sunshine.provider.api.hystrix.ProductClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "sunshine-provider-pmc"
        ,fallback = ProductClientHystrix.class
)
public interface ProductClientApi {

    @RequestMapping("api/index")
    String index();
}
