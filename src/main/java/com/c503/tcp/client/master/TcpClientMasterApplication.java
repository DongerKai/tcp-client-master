package com.c503.tcp.client.master;

import com.c503.tcp.client.master.context.SpringContextHolder;
import com.c503.tcp.client.master.service.MasterServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * tcp client master 启动项
 *
 * @author DongerKai
 * @since 2020/4/27 09:05 ，1.0
 **/

@SpringBootApplication(scanBasePackages = {"com.c503.**"})
public class TcpClientMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcpClientMasterApplication.class, args);
        SpringContextHolder.getBean(MasterServiceImpl.class).start();
    }
}
