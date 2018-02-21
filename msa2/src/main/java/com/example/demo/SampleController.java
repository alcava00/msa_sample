package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alcava00 on 2018. 2. 21..
 */
@RestController
public class SampleController {

    @RequestMapping("/hello1/{id}")
    public String hello(@PathVariable String id) throws InterruptedException {
        System.out.println("IN !!!!!!!!>>>>>>>>>>>>>>>>>>>>>>>>>>>>>!!!!!" +id);
        if ("error".equals(id)) {
            throw new RuntimeException("error >>>>>>");
        }
        if("time".equals(id)){
            Thread.sleep(5000);
        }
        return "hi:" + id;
    }
}
