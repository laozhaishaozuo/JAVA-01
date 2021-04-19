## 说明

第一个版本，完全基于内存queue的消息队列，实现了最基础的三个功能：

- 创建topic
- 订阅topic和poll消息
- 发送消息到topic

具体参见demo.KmqDemo


实现第二版本的MQ

自定义Queue的实现：

1. 提供对发布的消息的offset的记录
2. 提供对每个客户端消费的记录