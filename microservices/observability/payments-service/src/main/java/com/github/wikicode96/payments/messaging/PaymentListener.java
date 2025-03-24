package com.github.wikicode96.payments.messaging;

import com.github.wikicode96.model.Order;
import com.github.wikicode96.payments.entity.OrderEntity;
import com.github.wikicode96.payments.repository.OrderRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    private static final Logger logger = LoggerFactory.getLogger(PaymentListener.class);

    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final OrderRepository orderRepository;

    public PaymentListener(final KafkaTemplate<String, Order> kafkaTemplate,
                           final OrderRepository orderRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "orders-topic", groupId = "payments-group", containerFactory = "kafkaListenerContainerFactory")
    public void processPayment(ConsumerRecord<String, Order> record) {
        Order order = record.value();
        logger.info("💰 Procesando pago para la orden {}", order.toString());

        OrderEntity entity = new OrderEntity(
                order.getId(),
                order.getProduct(),
                order.getQuantity()
        );

        orderRepository.save(entity);

        // Simulación de procesamiento de pago
        logger.info("✅ Pago aprobado para orden {}", order.toString());
    }
}

