package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by alcava00 on 2018. 2. 22..
 */
@Service
public class SampleService {
    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "defaultStores")
    public String call(String id){
        return restTemplate.getForObject("http://127.0.0.1:9000/hello/{id}",String.class,id);
    }

    public String defaultStores(String id,Throwable e) {
        return "fail::::" + e;
    }
}
