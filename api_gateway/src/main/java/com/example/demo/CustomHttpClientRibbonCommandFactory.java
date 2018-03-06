package com.example.demo;

import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandContext;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.apache.HttpClientRibbonCommand;
import org.springframework.cloud.netflix.zuul.filters.route.apache.HttpClientRibbonCommandFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by alcava00 on 2018. 3. 6..
 */
public class CustomHttpClientRibbonCommandFactory  extends HttpClientRibbonCommandFactory {
    private final SpringClientFactory clientFactory;

    private final ZuulProperties zuulProperties;

    public CustomHttpClientRibbonCommandFactory(SpringClientFactory clientFactory, ZuulProperties zuulProperties) {
        super(clientFactory, zuulProperties);
        this.clientFactory=clientFactory;
        this.zuulProperties=zuulProperties;
    }
    public CustomHttpClientRibbonCommandFactory(SpringClientFactory clientFactory, ZuulProperties zuulProperties,
                                          Set<ZuulFallbackProvider> fallbackProviders) {
        super(clientFactory,zuulProperties,fallbackProviders);
        this.clientFactory=clientFactory;
        this.zuulProperties=zuulProperties;
    }

    @Override
    public HttpClientRibbonCommand create(final RibbonCommandContext context) {
        ZuulFallbackProvider zuulFallbackProvider = getFallbackProvider(context.getServiceId());

        System.out.println(" context.getUri()>>>>>>>>>" + context.getUri());
        final String serviceId = context.getServiceId();
        final RibbonLoadBalancingHttpClient client = this.clientFactory.getClient(
                serviceId, RibbonLoadBalancingHttpClient.class);
        client.setLoadBalancer(this.clientFactory.getLoadBalancer(serviceId));

        return new HttpClientRibbonCommand(serviceId, client, context, zuulProperties, zuulFallbackProvider,
                clientFactory.getClientConfig(serviceId));
    }
}
