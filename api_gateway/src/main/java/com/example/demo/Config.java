package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandFactory;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.apache.HttpClientRibbonCommandFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Set;

/**
 * Created by alcava00 on 2018. 3. 6..
 */
@Configuration
public class Config {
    @Autowired(required = false)
    private Set<ZuulFallbackProvider> zuulFallbackProviders = Collections.emptySet();
    @Bean
    public RibbonCommandFactory<?> ribbonCommandFactory(
            SpringClientFactory clientFactory, ZuulProperties zuulProperties) {
        return new CustomHttpClientRibbonCommandFactory(clientFactory, zuulProperties, zuulFallbackProviders);
    }
}
