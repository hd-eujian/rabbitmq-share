package com.eujian.rabbitmqshare.config;

import java.util.UUID;

public interface QueueCon {

    String queue = "queue"+UUID.randomUUID().toString();
    String rockey = "rockey"+UUID.randomUUID().toString();
    String topoc = "topic";
}
