package org.man.consumer.config.rest;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private int defaultMaxTotalConnections = 100;
    private int defaultMaxConnectionsPerRoute = 50;
    private int requestTimeout = 5000;
    private int connectTimeout =  5000;
    private int readTimeout = 30000;

    /**
     * Defines a HttpClient with connection pooling
     *
     * @return
     */
    @Bean
    public HttpClient httpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(defaultMaxTotalConnections);
        connectionManager.setDefaultMaxPerRoute(defaultMaxConnectionsPerRoute);
        return HttpClientBuilder.create().setConnectionManager(connectionManager).build();
    }

    /**
     * This function set customHttpRequestFactory for RestTemplate.
     *
     * @return HttpComponentsClientHttpRequestFactory
     */
    @Bean(name = "customHttpRequestFactory")
    @Primary
    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient());
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        factory.setConnectionRequestTimeout(requestTimeout);
        return factory;
    }

    @Primary
    @Bean(name = "normalRestTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(customHttpRequestFactory());
        return restTemplate;
    }

    @Bean(name = "loadBalancedRestTemplate")
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(customHttpRequestFactory());
        return restTemplate;
    }

}
