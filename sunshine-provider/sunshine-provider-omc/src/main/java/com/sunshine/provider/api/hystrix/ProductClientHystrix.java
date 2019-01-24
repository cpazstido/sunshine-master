package com.sunshine.provider.api.hystrix;

import com.sunshine.provider.api.ProductClientApi;
import org.springframework.stereotype.Component;

@Component
public class ProductClientHystrix implements ProductClientApi {
    @Override
    public String index() {
        return "fall back";
    }
}
