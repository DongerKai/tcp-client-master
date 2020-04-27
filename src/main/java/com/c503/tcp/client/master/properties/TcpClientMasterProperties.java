package com.c503.tcp.client.master.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * tcp client master 配置
 *
 * @author DongerKai
 * @since 2020/4/27 09:09 ，1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "tcp-client-master")
public class TcpClientMasterProperties {
    private String[] addresses;
    private String url;
    private Server server;


    @Data
    public static class Server{
        private String ip;
        private Integer port;
        private Integer connections;
        private String msg;
        private Integer cycleTimes;
        private Integer threads;
    }
}
