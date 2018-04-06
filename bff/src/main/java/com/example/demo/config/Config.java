package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alcava00 on 2018. 3. 5..
 */
@EnableRedisHttpSession
@Configuration
public class Config {
    //    @Bean
//    public LettuceConnectionFactory connectionFactory() {
//        LettuceConnectionFactory LettuceConnectionFactory = new LettuceConnectionFactory("alcava00.synology.me", 16379);
//        return LettuceConnectionFactory;
//    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> ls = new ArrayList<ClientHttpRequestInterceptor>();
        ls.add(new HeaderRequestInterceptor());
        restTemplate.setInterceptors(ls);
        HttpComponentsClientHttpRequestFactory factory

                = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(10*1000);
        factory.setReadTimeout(10*1000); ///!! 111
        return restTemplate;
    }

    class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {
        private Logger log = LoggerFactory.getLogger(HeaderRequestInterceptor.class);

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {
            logging(request, body);
            HttpServletRequest orgRequest = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();

            request.getHeaders().add("Cookie", "SESSION=" + orgRequest.getSession().getId());
            return execution.execute(request, body);
        }

        private void logging(HttpRequest request, byte[] body) {
            if (log.isDebugEnabled()) {
                log.debug(" ======== Rest Template Request Logging ======== ");
                log.debug(" Request URL : " + request.getURI().toString());
                log.debug(" Request Method : " + request.getMethod().toString());
                log.debug(" Request Header : " + request.getHeaders());
                if (body.length == 0) {
                    log.debug(" Request Body : empty [0]");
                } else {
                    log.debug(" Request Body : " + new String(body));
                }
                log.debug(" ================================================ ");
            }
        }

    }
}
