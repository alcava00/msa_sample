package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by alcava00 on 2018. 2. 21..
 */
@RestController
public class SampleController {
    private Logger logger = LoggerFactory.getLogger(SampleController.class);
    @Autowired
    private SampleService  sampleService;

    @RequestMapping("/hello/{id}/{uuid}")
    public String hello(@PathVariable  String id,@PathVariable String uuid, HttpSession session){
        System.out.println("in >>>>>>>>>>>>>>>>>>>>>>>>>>" +session.getAttribute("id")+":"+session.getId());
        logger.info("Path value: id:{},uuid:{}",id,uuid);
        logger.info("Session value: id:{},uuid:{}",session.getAttribute("tid"),session.getAttribute("uuid"));
        logger.info("Session value: id:{},uuid:{}",session.getAttribute("tid"),session.getAttribute("uuid"));

//        return sampleService.call(id);
        return id;
    }

}
