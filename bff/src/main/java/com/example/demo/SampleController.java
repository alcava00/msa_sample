package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by alcava00 on 2018. 3. 5..
 */
@RestController
public class SampleController {
    private Logger logger = LoggerFactory.getLogger(SampleController.class);


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable String id, HttpSession session) throws URISyntaxException, InterruptedException {
        session.setAttribute("tid", id);
        String uuid=UUID.randomUUID().toString();

        session.setAttribute("uuid",uuid);

        Thread.sleep(100);
        logger.info("In id:{},session id: {}", id, session.getId());
        return "hi: " + restTemplate.getForObject("http://api-gateway/msa1/hello/{id}/{uuid}", String.class, id,uuid);
//        return "hi: " + restTemplate.getForObject("http://msa1/hello/{id}/{uuid}", String.class, id,uuid);
//        return "hi: " + restTemplate.getForObject("http://api-gateway/hello/{id}/{uuid}", String.class, id,uuid);
//        return "hi: " + rssResponse.getBody();
    }

    @GetMapping("/hello2/{id}")
    public String hello2(@PathVariable String id, HttpSession session) throws URISyntaxException, InterruptedException {
        session.setAttribute("tid", id);
        String uuid=UUID.randomUUID().toString();

        session.setAttribute("uuid",uuid);

        Thread.sleep(100);
        Map body=new HashMap();
        body.put("tid",uuid);
        logger.info("In id:{},session id: {}", id, session.getId());
        return "hi: " + restTemplate.postForObject("http://api-gateway/msa1/hello/{id}/{uuid}",body, String.class, id,uuid);
    }
    public static void main(String[] args){
        String uuid=UUID.randomUUID().toString();
        System.out.println(uuid);
    }
}
