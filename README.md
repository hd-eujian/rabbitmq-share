# 实验环节，需要部署两个jar包

1、分别在2台服务器发起请求初始化连接。
在A服务器输入 curl localhost:8081//buildRoutingKey?recUserId=1
在B服务器输入 curl localhost:8082//buildRoutingKey?recUserId=2

2、然后发送消息。
在任意一台服务器输入 curl localhost:8082/sendMsg?sendUserId=2\&recUserId=1\&msg=xiaozhangHello
在任意一台服务器输入 curl localhost:8082/sendMsg?sendUserId=1\&recUserId=2\&msg=xiaoMingHello