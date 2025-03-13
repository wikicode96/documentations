package com.github.wikicode96.orders.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderController(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/{orderId}")
    public String createOrder(@PathVariable String orderId) {
        logger.info("📦 Enviando orden {} a Kafka", orderId);
        kafkaTemplate.send("orders-topic", orderId);
        return "Orden enviada: " + orderId;
    }
}
