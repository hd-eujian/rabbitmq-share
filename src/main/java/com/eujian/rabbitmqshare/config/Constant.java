package com.eujian.rabbitmqshare.config;

import java.util.Date;
import java.util.UUID;

public interface Constant {

    //队列名称，这个用uuid
    String QUEUE = "queue:"+UUID.randomUUID().toString();

    //路由键，作为机器id
    String ROUTING_KEY = "routingKey:"+new Date().getTime();

    //固定值，代表消息主题
    String TOPIC = "topic";

    //redsikey的前缀
    String REDIS_KEY = "redisKey:";
}
