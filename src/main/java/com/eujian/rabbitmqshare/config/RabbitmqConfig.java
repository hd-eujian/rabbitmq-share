package com.eujian.rabbitmqshare.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class RabbitmqConfig {


    @Bean
    TopicExchange dealLineExchange() {
        return new TopicExchange(QueueCon.topoc);
    }

    @Bean
    public Queue initRealQueue() {
        Queue queue = QueueBuilder.durable(QueueCon.queue).exclusive().autoDelete().build();
        return queue;
    }
    @Bean
    Binding bindingiVewUgcTopicExchange() {
        return BindingBuilder.bind(initRealQueue()).to(dealLineExchange()).with(QueueCon.rockey);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, ChannelAwareMessageListener listener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // 指定消费者
        container.setMessageListener(listener);
        // 指定监听的队列
        container.setQueueNames(QueueCon.queue);

        // 设置消费者的 ack 模式为手动确认模式
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        container.setPrefetchCount(300);

        return container;
    }

}
