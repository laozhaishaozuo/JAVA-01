# 学习笔记

## 周三作业：
**1.（必做）**搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。

### 搭建一个 3 节点 Kafka 集群

参考 https://kafka.apachecn.org/quickstart.html 快速开始的代码，启动3个节点的Kafka集群

### 实现 spring kafka

实现简单的Kafka操作，主题是:test

代码地址：

关键配置

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092, localhost:9093, localhost:9094
    producer:
      retries: 0
      acks: all
    consumer:
      properties:
        group:
          id: defaultConsumerGroup
      enable-auto-commit: true
      auto-commit-interval: 1000
      auto-offset-reset: latest
    listener:
      missing-topics-fatal: false
```

