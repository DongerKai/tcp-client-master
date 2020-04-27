package com.c503.tcp.client.master.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * rest client 配置文件
 *
 * @author DongerKai
 * @since 2020/4/27 09:50 ，1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "rest-client")
public class RestClientProperties {
    private int connectTimeout;
    private int readTimeout;
    private int waitTimeout;
    private int maxTotal;
    private int maxPerRoute;
    private long maxIdleTime;

}
