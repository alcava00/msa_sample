package com.example.demo.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

/**
 * Created by alcava00 on 2018. 2. 21..
 */
@Configuration
@EnableCircuitBreaker
@EnableRedisHttpSession
public class config {
    @Bean
    public RestTemplate restTemplate(){

        RestTemplate restTemplate=new RestTemplate();
        return restTemplate;
    }
}
