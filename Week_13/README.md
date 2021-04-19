# 学习笔记

## 周三作业：
**1.（必做）** 搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。

### 搭建一个 3 节点 Kafka 集群

参考 https://kafka.apachecn.org/quickstart.html 快速开始的代码，启动3个节点的Kafka集群

### 实现 spring kafka

实现简单的Kafka操作，主题是:test

代码地址：https://github.com/laozhaishaozuo/JAVA-01/tree/main/Week_13/simple-kafka

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

## 周日作业：

**2.（必做）** 思考和设计自定义 MQ 第二个版本或第三个版本，写代码实现其中至少一个功能点，把设计思路和实现代码，提交到 GitHub。

实现第二版本的MQ

自定义Queue的实现：

1. 提供对发布的消息的offset的记录
2. 提供对每个客户端消费的记录

代码地址： https://github.com/laozhaishaozuo/JAVA-01/tree/main/Week_13/kmq-core