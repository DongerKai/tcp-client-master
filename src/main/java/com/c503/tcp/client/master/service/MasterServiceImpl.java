package com.c503.tcp.client.master.service;

import com.c503.tcp.client.master.model.ClientConnectVo;
import com.c503.tcp.client.master.properties.TcpClientMasterProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * tcp client service
 *
 * @author DongerKai
 * @since 2020/4/27 10:03 ，1.0
 **/
@Service
@AllArgsConstructor
@Slf4j
public class MasterServiceImpl {
    private RestTemplate restClientTemplate;
    private TcpClientMasterProperties tcpClientMasterProperties;

    public void start(){
        String[] addresses = tcpClientMasterProperties.getAddresses();
        List<String> urls = Arrays.stream(addresses).map(row->String.format(tcpClientMasterProperties.getUrl(), row)).collect(Collectors.toList());
        ClientConnectVo clientConnect = ClientConnectVo.format(tcpClientMasterProperties.getServer());
        clientConnect.setIp(tcpClientMasterProperties.getServer().getIp());
        urls.forEach(row-> {
            Object res = restClientTemplate.exchange(row, HttpMethod.POST, new HttpEntity<>(clientConnect, null), Object.class).getBody();
            log.info("res:{}", res);
        });
    }
}
