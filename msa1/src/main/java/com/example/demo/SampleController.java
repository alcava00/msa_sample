package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by alcava00 on 2018. 2. 21..
 */
@RestController
public class SampleController {

    @Autowired
    private SampleService  sampleService;

    @RequestMapping("/hello/{id}")
    public String hello(@PathVariable  String id){
        System.out.println("in >>>>>>>>>>>>>>>>>>>>>>>>>>" +id);
        return sampleService.call(id);
    }

}
