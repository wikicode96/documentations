package com.github.wikicode96.payments.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    private static final Logger logger = LoggerFactory.getLogger(PaymentListener.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "orders-topic", groupId = "payments-group", containerFactory = "kafkaListenerContainerFactory")
    public void processPayment(ConsumerRecord<String, String> record) {
        logger.info("💰 Procesando pago para la orden {}", record.value());

        // Simulación de procesamiento de pago
        //kafkaTemplate.send("payments-topic", "Pago aprobado para orden " + orderId);
        logger.info("✅ Pago aprobado para orden {}", record.value());
    }
}

