# 学习笔记


### Week09 作业题目（周三）：
2.（选做）实现简单的 WebService-Axis2/CXF 远程调用 demo。

 	使用CXF实现WebService，使用 SpringBoot 作为脚手架

 	[代码地址](https://github.com/laozhaishaozuo/JAVA-01/tree/main/Week_09/simple-webservice)

3.（必做）改造自定义 RPC 的程序，提交到 GitHub：

1. 尝试将服务端写死查找接口实现类变成泛型和反射；
   1. [泛型和反射](https://github.com/laozhaishaozuo/JAVA-01/blob/main/Week_09/rpc-dev/rpcfx-demo-provider/src/main/java/io/kimmking/rpcfx/demo/provider/ReflectResolver.java)
2. 尝试将客户端动态代理改成 AOP，添加异常处理；
   1. AOP未完成，[异常处理代码](https://github.com/laozhaishaozuo/JAVA-01/blob/main/Week_09/rpc-dev/rpcfx-core/src/main/java/io/kimmking/rpcfx/exception/RpcfxException.java)
3. 尝试使用 Netty+HTTP 作为 client 端传输方式。
   1. [Netty+HTTP代码地址是](https://github.com/laozhaishaozuo/JAVA-01/blob/main/Week_09/rpc-dev/rpcfx-core/src/main/java/io/kimmking/rpcfx/client/NettyHttpRemoteClient.java)

代码地址：https://github.com/laozhaishaozuo/JAVA-01/tree/main/Week_09/rpc-dev

Week09 作业题目（周日）：

3.（必做）结合 dubbo+hmily，实现一个 TCC 外汇交易处理，代码提交到 GitHub:

用户 A 的美元账户和人民币账户都在 A 库，使用 1 美元兑换 7 人民币 ;
用户 B 的美元账户和人民币账户都在 B 库，使用 7 人民币兑换 1 美元 ;
设计账户表，冻结资产表，实现上述两个本地事务的分布式事务。

代码地址：https://github.com/laozhaishaozuo/JAVA-01/tree/main/Week_09/account-demo

遇到的坑有：
```plain
dubbo 升级 apache duboo 部分配置文件不兼容
dubbo 暴露的服务没有加上版本号
spring-boot-devtools 造成的  java.lang.ClassCastException
```
