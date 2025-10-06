package com.wikicode96.spring_kafka_streams_demo.service;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.stereotype.Service;

@Service
public class EventProcessorService {

    public void process(KStream<String, String> input) {

        // Convertimos KStream a KTable por alguna lógica
        KTable<String, String> counts = input.toTable();

        // Volvemos a KStream para filtrar y enviar a diferentes tópicos
        KStream<String, String>[] branches = counts.toStream()
                .mapValues((key, value) -> "Valor de salida: " + value) // Se pasa solo el value. La key ya va incluida
                .branch(
                        (key, value) -> value.contains("1"),  // filtro para output-topic-1
                        (key, value) -> true                  // resto va a output-topic-2
                );

        branches[0].to("output-topic-1");
        branches[1].to("output-topic-2");
    }
}
