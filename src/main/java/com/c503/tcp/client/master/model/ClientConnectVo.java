package com.c503.tcp.client.master.model;

import com.c503.tcp.client.master.properties.TcpClientMasterProperties.Server;
import lombok.Data;

/**
 * 请求参数
 *
 * @author DongerKai
 * @since 2020/4/23 12:33 ，1.0
 **/
@Data
public class ClientConnectVo {
    private String ip;
    private Integer port;
    private Integer connections;//总连接
    private String msg;
    private Integer threads;//使用的线程数
    private Integer cycleTimes;//循环次数

    public static ClientConnectVo format(Server server){
        ClientConnectVo clientConnect = new ClientConnectVo();
        clientConnect.setIp(server.getIp());
        clientConnect.setConnections(server.getConnections());
        clientConnect.setPort(server.getPort());
        clientConnect.setCycleTimes(server.getCycleTimes());
        clientConnect.setMsg(server.getMsg());
        clientConnect.setThreads(server.getThreads());
        return clientConnect;
    }
}
