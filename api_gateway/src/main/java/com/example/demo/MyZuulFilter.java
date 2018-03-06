package com.example.demo;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.support.AbstractRibbonCommand;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by alcava00 on 2018. 3. 6..
 */
@Component
public class MyZuulFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(MyZuulFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        org.springframework.cloud.netflix.zuul.filters.route.support.AbstractRibbonCommand d;
        RequestContext ctx = RequestContext.getCurrentContext();

        InputStreamReader inputStreamReader = null;
        try {
            System.out.println("ctx.getRequest().getInputStream()>>>>" + ctx.getRequest().getInputStream().toString());
            inputStreamReader = new InputStreamReader(ctx.getRequest().getInputStream());
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferReader.readLine()) != null) {
                logger.debug(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
