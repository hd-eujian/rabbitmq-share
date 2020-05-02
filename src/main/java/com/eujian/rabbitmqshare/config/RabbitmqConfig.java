package com.eujian.rabbitmqshare.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    //新建topic交换器
    @Bean
    TopicExchange initExchange() {
        return new TopicExchange(Constant.TOPIC);
    }

    //新建队列，要设置独占队列，exclusive
    @Bean
    public Queue initQueue() {
        Queue queue = QueueBuilder.durable(Constant.QUEUE).exclusive().autoDelete().build();
        return queue;
    }

    //交换器和主题绑定
    @Bean
    Binding bindingiTopicExchange() {
        return BindingBuilder.bind(initQueue()).to(initExchange()).with(Constant.ROUTING_KEY);
    }

    //新建消费者
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, ChannelAwareMessageListener listener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // 指定消费者
        container.setMessageListener(listener);
        // 指定监听的队列
        container.setQueueNames(Constant.QUEUE);

        // 设置消费者的 ack 模式为手动确认模式
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        container.setPrefetchCount(300);
        //connection
        return container;
    }
}
