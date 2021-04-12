# Redis配置

## 通用步骤

### 安装redis

搜索redis镜像

```shell
docker search redis
```

拉取镜像

```shell
docker pull  redis
```

下载完成后，我们就可以在本地镜像列表里查到REPOSITORY为redis

```shell
docker images redis
REPOSITORY   TAG       IMAGE ID       CREATED       SIZE
redis        latest    cc69ae189a1a   6 weeks ago   105MB
```

## Redis主从

### 运行redis镜像

   ```shell
   docker run -p 6379:6379 --name redis-master -v /opt/data/docker/redis/6379.conf:/etc/redis/redis.conf -v /opt/data/docker/redis/6379/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes
   
   
   docker run -p 6380:6379 --name redis-slave1 -v /opt/data/docker/redis/6380.conf:/etc/redis/redis.conf -v /opt/data/docker/redis/6380/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes
   
   
   docker run -p 6381:6379 --name redis-slave2 -v /opt/data/docker/redis/6381.conf:/etc/redis/redis.conf -v /opt/data/docker/redis/6381/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes
   ```

以上名命令分别启动了3个redis，端口分别是6379、6380、6381
命令说明：

1. -p 6379:6379  将容器的6379端口映射到主机的6379端口
2. --name redis-master 容器名称为redis-master，后续可以通过容器名称进行操作
3. -v /opt/data/docker/redis/6379.conf:/etc/redis/redis.conf  将主机中/opt/data/docker/redis/6379.conf 挂载到容器的 /etc/redis/redis.conf
4. -v /opt/data/docker/redis/6379/data:/data  将主机中/opt/data/docker/redis/6379/data挂载到容器的/data
5. -d 后台模式运行
6. redis-server --appendonly yes  在容器执行redis-server启动命令，并打开redis持久化配置

### 查看redis容器的内网地址
查看容器内网的ip地址等信息
```shell
#docker inspect redis-master #可以使用容器id或容器名称
docker inspect redis-master | grep  "IPAddress"
            "SecondaryIPAddresses": null,
            "IPAddress": "172.17.0.4",
                    "IPAddress": "172.17.0.4",

```
> ps:因为第一次启动redis-master报错，所以ip地址排序在后


经查询三个redis的地址分别是：
```shell
redis-master 172.17.0.4
redis-slave1 172.17.0.2
redis-slave2 172.17.0.3
```
### 修改从服务器配置

进入docker容器内部

```shell
docker exec -it redis-master redis-cli #进入docker容器内部
```

查看当前redis角色（主master还是从slave）（命令：info replication）

```
info replication
```

可以看到当前3台redis都是master角色，使用redis-cli命令修改redis-slave1、redis-slave2的主机为172.17.0.4:6379

```shell
SLAVEOF 172.17.0.4 6379
```

查看redis-master主从信息

```
127.0.0.1:6379> info replication
# Replication
role:master
connected_slaves:2
slave0:ip=172.17.0.2,port=6379,state=online,offset=422,lag=0
slave1:ip=172.17.0.3,port=6379,state=online,offset=422,lag=0
master_failover_state:no-failover
master_replid:b7424ad261f29667e158329786f74545a936d6dc
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:422
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:422

```



### 测试主从

在主服务器中执行命令 `set test "master"`

![](./resources/replication-test-master.png)

分别在从服务器中查看`get test`

![](./resources/replication-test-slave1.png)

![](./resources/replication-test-slave2.png)

## Redis 哨兵模式

在主从的基础上，增加哨兵模式

从官网下载sentinel.conf文件

```
wget http://download.redis.io/redis-stable/sentinel.conf
```

### 修改sentinel.conf并复制多份

```
port 26379 (26380|26381)

daemonize yes

sentinel monitor mymaster 172.17.0.2 6379 2

```

### 启动哨兵容器

```
# redis-sentinel实例1
docker run -it --name redis-sentinel1 -v /opt/data/docker/redis/sentinel_1.conf:/etc/redis/sentinel.conf -d redis /bin/bash
    
# redis-sentinel实例2
docker run -it --name redis-sentinel2 -v /opt/data/docker/redis/sentinel_2.conf:/etc/redis/sentinel.conf -d redis /bin/bash
    
# redis-sentinel实例3
docker run -it --name redis-sentinel3 -v /opt/data/docker/redis/sentinel_3.conf:/etc/redis/sentinel.conf -d redis /bin/bash
```

启动哨兵

```
docker  exec  -it redis-sentinel1(redis-sentinel2|redis-sentinel2) bash
redis-server /etc/redis/sentinel.conf --sentinel
```

### 查看哨兵状态

```
redis-cli -p 26379 (26380|26381)
```

查看节点信息

```
#sentinel master mymaster  查看主节点状态
127.0.0.1:26379> sentinel master mymaster 
 1) "name"
 2) "mymaster"
 3) "ip"
 4) "172.17.0.2"
 5) "port"
 6) "6379"
 7) "runid"
 8) "5e958794d8cdfce16c3b43e87c6179f170cb23ba"
 9) "flags"
10) "master"
   
# sentinel slaves mymaster   查看从节点状态
127.0.0.1:26379> sentinel slaves mymaster 
1)  1) "name"
    2) "172.17.0.3:6379"
    3) "ip"
    4) "172.17.0.3"
    5) "port"
    6) "6379"
    7) "runid"
    8) "3611b74282dfcc9b844a870c6321b16999f1b079"
    9) "flags"
   10) "slave"
...
2)  1) "name"
    2) "172.17.0.4:6379"
    3) "ip"
    4) "172.17.0.4"
    5) "port"
    6) "6379"
    7) "runid"
    8) "e83edce6b210f7b48140cb376e0c9f885580b0e4"
    9) "flags"
   10) "master"
```

## 集群

### 修改配置文件

```
# redis端口
port 7001
# 关闭保护模式
protected-mode no
# 开启集群
cluster-enabled yes
# 集群节点配置
cluster-config-file nodes.conf
# 超时
cluster-node-timeout 5000
# 集群节点IP host模式为宿主机IP
cluster-announce-ip 192.168.199.101
# 集群节点端口 7001 - 7006
cluster-announce-port 7001
cluster-announce-bus-port 17001
# 开启 appendonly 备份模式
appendonly yes
# 每秒钟备份
appendfsync everysec
# 对aof文件进行压缩时，是否执行同步操作
no-appendfsync-on-rewrite no
# 当目前aof文件大小超过上一次重写时的aof文件大小的100%时会再次进行重写
auto-aof-rewrite-percentage 100
# 重写前AOF文件的大小最小值 默认 64mb
auto-aof-rewrite-min-size 64mb
```

### Docker环境搭建

这里还是通过docker-compose进行测试环境的docker编排

```yaml
version: '3.7'

services:
  redis7001:
    image: 'redis'
    container_name: redis7001
    command:
      ["redis-server", "/etc/redis/redis.conf"]
    volumes:
      - ./redis-cluster/7001/conf/redis.conf:/etc/redis/redis.conf
      - ./redis-cluster/7001/data:/data
    ports:
      - "7001:7001"
      - "17001:17001"
    environment:
      # 设置时区为上海，否则时间会有问题
      - TZ=Asia/Shanghai


  redis7002:
    image: 'redis'
    container_name: redis7002
    command:
      ["redis-server", "/etc/redis/redis.conf"]
    volumes:
      - ./redis-cluster/7002/conf/redis.conf:/etc/redis/redis.conf
      - ./redis-cluster/7002/data:/data
    ports:
      - "7002:7002"
      - "17002:17002"
    environment:
      # 设置时区为上海，否则时间会有问题
      - TZ=Asia/Shanghai


  redis7003:
    image: 'redis'
    container_name: redis7003
    command:
      ["redis-server", "/etc/redis/redis.conf"]
    volumes:
      - ./redis-cluster/7003/conf/redis.conf:/etc/redis/redis.conf
      - ./redis-cluster/7003/data:/data
    ports:
      - "7003:7003"
      - "17003:17003"
    environment:
      # 设置时区为上海，否则时间会有问题
      - TZ=Asia/Shanghai


  redis7004:
    image: 'redis'
    container_name: redis7004
    command:
      ["redis-server", "/etc/redis/redis.conf"]
    volumes:
      - ./redis-cluster/7004/conf/redis.conf:/etc/redis/redis.conf
      - ./redis-cluster/7004/data:/data
    ports:
      - "7004:7004"
      - "17004:17004"
    environment:
      # 设置时区为上海，否则时间会有问题
      - TZ=Asia/Shanghai


  redis7005:
    image: 'redis'
    container_name: redis7005
    command:
      ["redis-server", "/etc/redis/redis.conf"]
    volumes:
      - ./redis-cluster/7005/conf/redis.conf:/etc/redis/redis.conf
      - ./redis-cluster/7005/data:/data
    ports:
      - "7005:7005"
      - "17005:17005"
    environment:
      # 设置时区为上海，否则时间会有问题
      - TZ=Asia/Shanghai


  redis7006:
    image: 'redis'
    container_name: redis7006
    command:
      ["redis-server", "/etc/redis/redis.conf"]
    volumes:
      - ./redis-cluster/7006/conf/redis.conf:/etc/redis/redis.conf
      - ./redis-cluster/7006/data:/data
    ports:
      - "7006:7006"
      - "17006:17006"
    environment:
      # 设置时区为上海，否则时间会有问题
      - TZ=Asia/Shanghai

```

### 配置集群

通过如下命令启动集群

```
docker exec -it redis7001 redis-cli -p 7001 --cluster create 192.168.199.101:7001 192.168.199.101:7002 192.168.199.101:7003 192.168.199.101:7004 192.168.199.101:7005 192.168.199.101:7006 --cluster-replicas 1 
```

部分输出内容

```
>>> Performing Cluster Check (using node 192.168.199.101:7001)
M: 74e49870207acb3e9778f8f6585310a2749fb5a1 192.168.199.101:7001
   slots:[0-5460] (5461 slots) master
   1 additional replica(s)
M: 557ca8549216baed5b04dbb6112d7badb94ebac6 192.168.199.101:7002
   slots:[5461-10922] (5462 slots) master
   1 additional replica(s)
M: a450298c4d8d319b21d0a4aff1c5bdf27bd2a4f8 192.168.199.101:7003
   slots:[10923-16383] (5461 slots) master
   1 additional replica(s)
S: 101a04bd54e38d97497988f55ad63fc5943420c7 192.168.199.101:7004
   slots: (0 slots) slave
   replicates 557ca8549216baed5b04dbb6112d7badb94ebac6
S: 7a6682a2ca0fbd8958e7118648b66820b0b4b657 192.168.199.101:7005
   slots: (0 slots) slave
   replicates a450298c4d8d319b21d0a4aff1c5bdf27bd2a4f8
S: 91648f27b3c02b5f87e7405f703ce2e57cf5ba5d 192.168.199.101:7006
   slots: (0 slots) slave
   replicates 74e49870207acb3e9778f8f6585310a2749fb5a1
[OK] All nodes agree about slots configuration.

```



### 查看节点信息

链接redis集群

```
redis-cli -c -h 192.168.199.101 -p 7001
```

查看集群状态

```
192.168.199.101:7001> cluster info
cluster_state:ok
cluster_slots_assigned:16384
cluster_slots_ok:16384
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:6
cluster_size:3
cluster_current_epoch:6
cluster_my_epoch:1
cluster_stats_messages_ping_sent:491
cluster_stats_messages_pong_sent:482
cluster_stats_messages_sent:973
cluster_stats_messages_ping_received:477
cluster_stats_messages_pong_received:491
cluster_stats_messages_meet_received:5
cluster_stats_messages_received:973

```

查看节点状态

```
192.168.199.101:7001> cluster nodes
74e49870207acb3e9778f8f6585310a2749fb5a1 192.168.199.101:7001@17001 myself,master - 0 1618200563000 1 connected 0-5460
557ca8549216baed5b04dbb6112d7badb94ebac6 192.168.199.101:7002@17002 master - 0 1618200563932 2 connected 5461-10922
a450298c4d8d319b21d0a4aff1c5bdf27bd2a4f8 192.168.199.101:7003@17003 master - 0 1618200564000 3 connected 10923-16383
101a04bd54e38d97497988f55ad63fc5943420c7 192.168.199.101:7004@17004 slave 557ca8549216baed5b04dbb6112d7badb94ebac6 0 1618200564533 2 connected
7a6682a2ca0fbd8958e7118648b66820b0b4b657 192.168.199.101:7005@17005 slave a450298c4d8d319b21d0a4aff1c5bdf27bd2a4f8 0 1618200564000 3 connected
91648f27b3c02b5f87e7405f703ce2e57cf5ba5d 192.168.199.101:7006@17006 slave 74e49870207acb3e9778f8f6585310a2749fb5a1 0 1618200563000 1 connected

```

### 测试

**插入值** 

```
192.168.199.101:7001> set test 'hello world'
-> Redirected to slot [6918] located at 192.168.199.101:7002
OK
192.168.199.101:7002> set test1 'hello world'
-> Redirected to slot [4768] located at 192.168.199.101:7001
OK
```
***获取值*
```
192.168.199.101:7002> get test
"hello world"
192.168.199.101:7002> get test1
-> Redirected to slot [4768] located at 192.168.199.101:7001
"hello world"
```
> 这里根据切片自动切换到了该数据分片所在的节点上，所以下面可以看到连接的节点有改变