# tcp-client-master
to control clients when use them to send messages to server

这是多个tcp client的入口控制。

# 配置

[tcp-client-master/src/main/resources/application.yml|https://github.com/DongerKai/tcp-client-master/blob/master/src/main/resources/application.yml]

## 配置详解

```json
tcp-client-master:
  name: tcp-client-master
  addresses: 192.168.1.75:6601,192.168.2.91:6601,192.168.1.241:6601//client address
  url: 'http://%s/${tcp-client-master.url-path}'
  url-path: ClientStartService
  server:
    ip: 192.168.1.67//server ip
    port: 6668//server port
    connections: 300//单个client服务启动的连接数
    msg: 7e0200003f000004021895000b00000000000400030158ccaa06cb79f500950000000016010516541501040000697402020000030200002504000000002b040000000030010031010b3201467c7e//报文
    cycle-times: 10000//循环次数
    threads: 100//开启线程数
```

并发总数=单个client服务启动的连接数*循环次数