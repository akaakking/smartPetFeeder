package org.wlc.feeder.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/20 下午6:35
 */
@Configuration
public class HttpClientConfiguration {
    @Bean
    public HttpClient httpClient() {
        return HttpClients.createDefault();
    }
}
