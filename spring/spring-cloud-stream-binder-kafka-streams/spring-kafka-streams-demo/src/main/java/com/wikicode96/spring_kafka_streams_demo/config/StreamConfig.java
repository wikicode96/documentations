package com.wikicode96.spring_kafka_streams_demo.config;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StreamConfig {

    @Bean
    public KStream<String, String> kStreamProcessor(StreamsBuilder builder) {
        KStream<String, String> inputStream = builder.stream("input-topic");
        return inputStream;
    }
}
