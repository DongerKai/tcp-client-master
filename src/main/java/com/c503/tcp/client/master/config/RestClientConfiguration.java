package com.c503.tcp.client.master.config;

import com.c503.tcp.client.master.properties.RestClientProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Rest Template 客户端
 *
 * @author DongerKai
 * @since 2020/4/27 09:51 ，1.0
 **/
@Configuration
@RequiredArgsConstructor
public class RestClientConfiguration {

    @NonNull
    private RestClientProperties restClientProperties;

    @Bean("restClientTemplate")
    public RestTemplate autoRestClient(ClientHttpRequestFactory clientHttpRequestFactory){
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean("clientHttpRequestFactory")
    public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        //httpClient 已经配置 但是不知道后面是不是使用，再配置一下吧，如何有使用 可以删除
        factory.setConnectTimeout(restClientProperties.getConnectTimeout()); // 连接超时，毫秒
        factory.setReadTimeout(restClientProperties.getReadTimeout()); // 读写超时，毫秒
        factory.setConnectionRequestTimeout(restClientProperties.getWaitTimeout());//请求连接时使用的超时，毫秒
        return factory;
    }

    @Bean("httpClient")
    public HttpClient httpClientBuilder(HttpClientConnectionManager httpClientConnectionManager) {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(restClientProperties.getConnectTimeout())// 连接超时，毫秒
                .setConnectionRequestTimeout(restClientProperties.getWaitTimeout())//请求连接时使用的超时，毫秒
                .setSocketTimeout(restClientProperties.getReadTimeout()).build();// 读写超时，毫秒
        return HttpClientBuilder.create()
                .setConnectionManager(httpClientConnectionManager)//设置HTTP连接管理器
                .setDefaultRequestConfig(config)//设置默认请求配置
                .evictExpiredConnections()
                .evictIdleConnections(restClientProperties.getMaxIdleTime(), TimeUnit.SECONDS)//设置定期清理时间60秒 ，最大空闲时间60s
                /*.setKeepAliveStrategy((response,context)->{
                    HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                    while (it.hasNext()) {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if (value != null && StringUtils.equalsIgnoreCase(param,TIMEOUT))
                            return Long.parseLong(value) * 1000;
                    }
                    return 60 * 1000;//如果没有约定，则默认定义时长为60s
                })//设置长连接*/
                .build();
    }

    @Bean("httpClientConnectionManager")
    public HttpClientConnectionManager poolingConnectionManager() {
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
        pool.setMaxTotal(restClientProperties.getMaxTotal()); // 连接池最大连接数
        pool.setDefaultMaxPerRoute(restClientProperties.getMaxPerRoute()); // 每个主机的并发
        return pool;
    }
}
