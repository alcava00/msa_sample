package com.example.demo.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by alcava00 on 2018. 2. 21..
 */
@Configuration
@EnableCircuitBreaker
public class config {

    @Bean
    public RestTemplate restTemplate(){

        RestTemplate restTemplate=new RestTemplate();
        return restTemplate;
    }
}
