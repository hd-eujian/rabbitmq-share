package com.eujian.rabbitmqshare.controller;

import com.eujian.rabbitmqshare.config.QueueCon;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping
@RestController
public class UserController {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/demo")
    public String demo(Long ttl){
        MessageProperties messageProperties = new MessageProperties();
        Long delayTime = ttl;
        String messageBody = "发送的延迟时间:"+ttl;
        Message message = rabbitTemplate.getMessageConverter().toMessage(messageBody, messageProperties);

        rabbitTemplate.convertAndSend(QueueCon.topoc,QueueCon.rockey,message);
        return "success";
    }
}
