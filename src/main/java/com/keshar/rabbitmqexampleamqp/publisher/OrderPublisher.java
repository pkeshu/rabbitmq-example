package com.keshar.rabbitmqexampleamqp.publisher;

import com.keshar.rabbitmqexampleamqp.dto.Order;
import com.keshar.rabbitmqexampleamqp.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/publisher")
public class OrderPublisher {
    @Value("${com.keshar.exchange}")
    private String EXCHANGE;
    @Value("${com.keshar.routing}")
    private String ROUTING_KEY;

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restroName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restroName) {

        order.setOrderId(UUID.randomUUID().toString());
        //restroService
        //paymentService
        OrderStatus orderStatus = new OrderStatus(order, "PROGRESS", "order placed successfully in " + restroName);
        template.convertAndSend(EXCHANGE, ROUTING_KEY, orderStatus);
        return "Order successfully created";

    }
}
