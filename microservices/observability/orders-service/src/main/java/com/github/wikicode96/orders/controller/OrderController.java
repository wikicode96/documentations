package com.github.wikicode96.orders.controller;

import com.github.wikicode96.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderController(final KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        order.setId(UUID.randomUUID());
        logger.info("📦 Enviando orden {} a Kafka", order.toString());
        kafkaTemplate.send("orders-topic", order);
        return "Orden enviada: " + order.toString();
    }
}
