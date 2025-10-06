package com.wikicode96.spring_kafka_streams_demo.service;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.test.TestRecord;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class EventProcessorServiceTest {

    private final String cadena = "Valor de salida: ";

    private TopologyTestDriver testDriver;
    private TestInputTopic<String, String> inputTopic;
    private TestOutputTopic<String, String> outputTopic1;
    private TestOutputTopic<String, String> outputTopic2;

    @BeforeEach
    void setup() {
        // Creamos la topología manualmente, igual que en producción
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> input = builder.stream("input-topic");

        EventProcessorService service = new EventProcessorService();
        service.process(input);

        Topology topology = builder.build();

        // Configuración de test driver
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        testDriver = new TopologyTestDriver(topology, props);

        inputTopic = testDriver.createInputTopic(
                "input-topic",
                Serdes.String().serializer(),
                Serdes.String().serializer()
        );

        outputTopic1 = testDriver.createOutputTopic(
                "output-topic-1",
                Serdes.String().deserializer(),
                Serdes.String().deserializer()
        );

        outputTopic2 = testDriver.createOutputTopic(
                "output-topic-2",
                Serdes.String().deserializer(),
                Serdes.String().deserializer()
        );
    }

    @AfterEach
    void tearDown() {
        testDriver.close();
    }

    @Test
    void whenValueContainsOne_thenGoesToTopic1() {
        var id = UUID.randomUUID().toString();

        inputTopic.pipeInput(id, "1-value");
        inputTopic.pipeInput(id, "1-dato");
        inputTopic.pipeInput(id, "1-prueba");
        inputTopic.pipeInput(id, "1-numero");

        List<TestRecord<String, String>> output = outputTopic1.readRecordsToList();
        assertThat(output)
                .extracting(TestRecord::key, TestRecord::value)
                .containsExactlyInAnyOrder(
                        Tuple.tuple(id, cadena.concat("1-value")),
                        Tuple.tuple(id, cadena.concat("1-dato")),
                        Tuple.tuple(id, cadena.concat("1-prueba")),
                        Tuple.tuple(id, cadena.concat("1-numero"))
                );

        assertThat(outputTopic2.isEmpty()).isTrue();
    }

    @Test
    void whenValueDoesNotContainOne_thenGoesToTopic2() {
        var id = UUID.randomUUID().toString();

        inputTopic.pipeInput(id, "x-value");
        inputTopic.pipeInput(id, "x-dato");
        inputTopic.pipeInput(id, "x-prueba");
        inputTopic.pipeInput(id, "x-numero");

        List<TestRecord<String, String>> output = outputTopic2.readRecordsToList();
        assertThat(output)
                .extracting(TestRecord::key, TestRecord::value)
                .containsExactlyInAnyOrder(
                        Tuple.tuple(id, cadena.concat("x-value")),
                        Tuple.tuple(id, cadena.concat("x-dato")),
                        Tuple.tuple(id, cadena.concat("x-prueba")),
                        Tuple.tuple(id, cadena.concat("x-numero"))
                );

        assertThat(outputTopic1.isEmpty()).isTrue();
    }

    @Test
    void whenMultipleEvents_thenRoutedToCorrectTopics() {
        inputTopic.pipeInput("a", "valor-1");
        inputTopic.pipeInput("b", "dato-2");
        inputTopic.pipeInput("c", "dato-1");
        inputTopic.pipeInput("d", "prueba");
        inputTopic.pipeInput("e", "numero-1");

        List<TestRecord<String, String>> output1 = outputTopic1.readRecordsToList();
        List<TestRecord<String, String>> output2 = outputTopic2.readRecordsToList();

        assertThat(output1)
                .extracting(TestRecord::key, TestRecord::value)
                .containsExactlyInAnyOrder(
                        Tuple.tuple("a", cadena.concat("valor-1")),
                        Tuple.tuple("c", cadena.concat("dato-1")),
                        Tuple.tuple("e", cadena.concat("numero-1"))
                );

        assertThat(output2)
                .extracting(TestRecord::key, TestRecord::value)
                .containsExactlyInAnyOrder(
                        Tuple.tuple("b", cadena.concat("dato-2")),
                        Tuple.tuple("d", cadena.concat("prueba"))
                );
    }
}
