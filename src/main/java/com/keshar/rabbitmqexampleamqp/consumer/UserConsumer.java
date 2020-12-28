package com.keshar.rabbitmqexampleamqp.consumer;

import com.keshar.rabbitmqexampleamqp.dto.OrderStatus;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

import static com.keshar.rabbitmqexampleamqp.util.Constants.*;

@Component
public class UserConsumer {

    @RabbitListener(queues = QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message Received from queue:: " + orderStatus);
    }

}
