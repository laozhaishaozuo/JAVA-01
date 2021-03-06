# 第15周作业

## 思考与总结
(必做)分别用 100 个字以上的一段话，加上一幅图 (架构图或脑图)，总结自己对下列技术的关键点思考和经验认识:

### JVM

JVM（Java Virtual Machine）是Java执行的基础，是Java能够实现跨平台的基石，作为一个虚拟机它模拟了计算机的功能，比如它有自己的指令集，有自己的内存管理（堆栈），有自己的程序计数器，提供一些监控手段等。

关于JVM中比较重要的概念有：

1. 类的加载机制 解决如何获取类
2. JVM内存结构 描述如何对Java管理的内存区域进行划分、
3. GC收集 Java如何管理内存的回收与分配
4. JVM的指令集 规范Java支持的指令系统
5. JMM（Java内存模型） 规范Java的内存访问，屏蔽各种底层对内存访问的差异，是多线程并发编程的基础
6. 一些监控手段 提供对虚拟机一些指标进行监控的工具

[JVM学习](./JVM学习.xmind)

### NIO

提到IO就不能说不提到那几种网络IO模型：

1. 同步阻塞 
2. 同步非阻塞
3. IO多路复用
4. 信号驱动
5. 异步非阻塞

同步与异步的区别：

1. 同步 发送IO请求后需要需要等待或者轮询内核 I/O操作完成后才能进行
2. 异步 发起 I/O 请求后仍继续执行，当内核 I/O 操作完成后会通知用户线程，或者调用用户线程注册的回调函数。

阻塞与非阻塞：

1. 阻塞 发送IO请求后阻塞，等待实际IO操作
2. 非阻塞 发送IO请求后立即返回一个状态

Java中的NIO就是基于IO多路复用实现的，不过不太好用。Netty是事实上Java网络通信的标准，各大框架都是使用Netty来实现网络通信。

[JavaIO操作](./JavaIO操作.xmind)

[Netty学习](./Netty学习.xmind)

### 并发编程

并发编程是提高效率的一种趋势，在每个语言中都会提供对并发编程的支持，在Java中也有对并发编程的支持。

Java对并发编程的支持：

1. 线程 并发执行任务
2. JMM  规范Java的内存访问，屏蔽各种底层对内存访问的差异，是多线程并发编程的基础
3. 锁 多线程中共享数据时，需要对共享数据进行保护
   1. synchronize Java元生对锁的支持
   2. locks 1.5之后对锁的支持
4. 线程池 对线程资源的管理

[并发体系整理](./并发体系整理.xmind)

### Spring 和 ORM 等框架

在不使用其他框架，直接使用Java也可以进行程序的开发，但是相对来将效率会很低。

Spring 一种相对轻量级的容器（与EJB对比），解决了企业开发中的一些痛点，它对开发中的各方面有统一的抽象，提供功能强大IOC和AOP功能，并且很方便与其他框架整合。Spring它没有去抢占其他框架的领域（比如ORM）,只是对其进行了统一的抽象，方便了使用。

SpringBoot 在Spring的基础上，更加的易用，大大简化了开发的效率。通过各种脚手架和自动化配置，可以实现0配置启动一个Web服务。

Java 提供了与数据库之间进行交互的规范（JDBC），各大数据库厂商对其进行了实现，但是直接使用 JDBC 进行操作在开发效率上会有很大的问题，所以有了各大ORM框架。

Hibernate  框架是一个全表映射的框架。通常开发者只要定义好持久化对象到数据库表的映射关系，就可以通过 Hibernate 框架提供的方法完成持久层操作。

MyBatis 框架是一个半自动映射的框架。这里所谓的“半自动”是相对于 Hibernate 框架全表映射而言的，MyBatis 框架需要手动匹配提供 POJO、SQL 和映射关系，而 Hibernate 框架只需提供 POJO 和映射关系即可

[Spring](Spring.xml)

### MySQL 数据库和 SQL

每个软件系统中最重要的是数据，那么如何持久化的存储这些数据就是在设计系统时必须要考虑到的。常用的数据库类型有：

1. 关系型数据库 比如 MySQL、Oracle、PostgreSQL等
2. 图数据库
3. NoSql数据库 比如 Redis、Mongodb等

MySQL是比较常用的关系型数据库，它的一些知识点包括：

1. 存储引擎
2. 索引
3. 事务和锁
4. 执行计划
5. 对高可用的支持

[数据库](./数据库.xmind)

###  分库分表

当数据量到达一定的级别之后，单台数据库实例可能无法承受空间或时间上的损失，那么在这种情况下可以考虑对数据库进行拆分，拆分的方式有分库和分表。

所谓分库分表就是按照一定的规则，对原有的数据库和表进行拆分，把原本存储于一个库的数据分块存储到多个库上，把原本存储于一个表的数据分块存储到多个表上。

在如何拆分的选择上可以选择以下的方式：

1. 垂直拆分 根据业务进行划分。将一个数据库，拆分成多个提供不同业务数据处理能力的数据库。如果单表数据量过大，还可能需要对单表进行拆分。
2. 水平划分 不改变数据的结构，只是按照一定规则，例如时间或id序列值等进行数据的拆分。比如根据年份来拆分不同的数据库。每个数据库结构一致，但是数据得以拆分，从而提升性能。又比如根据用户id的值，根据规则分成若干个表。每个表结构一致。

[数据库-超越单台的极限](./数据库-超越单台的极限.xmind)

### RPC 和微服务

在分布式的场景中，肯定会遇到多个系统需要进行相互调用。RPC就是一种进行不同系统之间调用的技术。

在一次RPC请求中，其涉及到的过程包括：

1. 查找远程服务 通过某种方式注册，比如使用注册中心
2. 生成本地调用代码 通过代理或字节码增强的方式
3. 序列化反序列化 实现数据的共享，可能的序列化方式有：Json、Serializable、thrift等方式
4. 网络通信 tcp/udp等协议
5. 反射调用
6. 等等

RPC框架有：语言自带的，dubbo， gRPC等

SOA 面向服务的架构 每个系统将自己的业务能力封装并对外提供服务

微服务 一种软件组织或架构方式，一个微服务解决一类相关的业务问题。将单体应用拆分之后有一些需要解决的问题

1. 服务发现 
2. 降级限流
3. 统一认证
4. 分布式事务等

[分布式系统](./分布式系统.xmind)

### 分布式缓存

缓存的使用很常见，比如在CPU中一级二级缓存，浏览器缓存等，缓存是系统优化的最简单有效的方式。

一般使用 Redis 作为缓存，当然它不仅仅可以作为缓存，还提供其他的功能，比如可以实现分布式锁。

在使用缓存的过程中可能会遇到的问题，比如

1. 缓存雪崩
2. 缓存穿透
3. 缓存击穿

[分布式系统](./分布式系统.xmind)

### 分布式消息队列

消息队列，是异步通信最常用的方式，当然它还有其他的功能，比如限流削峰（解决其他系统中速率不匹配的问题）。

常见的消息队列有：

1. ActiveMQ 一个完全支持JMS1.1和J2EE 1.4规范的 JMS Provider实现。它非常快速，支持多种语言的客户端和协议，而且可以非常容易的嵌入到企业的应用环境中，并有许多高级功能。
2. RabbitMQ 2007年发布，是一个在AMQP(高级消息队列协议)基础上完成的，可复用的企业消息系统，是当前最主流的消息中间件之一。使用的是ErLang语言。
3. RocketMQ 阿里公司的开源产品，用 Java 语言实现，在设计时参考了 Kafka，并做出了自己的一些改进
4. Kafka 一个分布式消息发布订阅系统。它最初由LinkedIn公司基于独特的设计实现为一个分布式的提交日志系统。目前使用最广

如果只是简单的业务场景，那么使用哪一类的消息队列都能满足要求，但是如果场景有侧重比如：Kafka在于分布式架构，RabbitMQ基于AMQP协议来实现，ActiveMQ支持的协议最多，可以根据不同的场景有侧重的选择。

当然如果是多语言的情况下，还需要考虑不同的语言客户端的支持。

[分布式系统](./分布式系统.xmind)