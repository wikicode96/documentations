package com.github.wikicode96.reactive.repository;

import com.github.wikicode96.reactive.entity.BasicEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Repository
public class DbSimulada {

    // Metodo que simula una búsqueda en base de datos reactiva
    public Flux<BasicEntity> getElements() {
        // Simula registros encontrados para ese ID
        List<BasicEntity> datos = List.of(
                new BasicEntity(UUID.randomUUID().toString(), "Elemento A"),
                new BasicEntity(UUID.randomUUID().toString(), "Elemento B"),
                new BasicEntity(UUID.randomUUID().toString(), "Elemento C")
        );

        // Simulamos un retardo como si viniera de base de datos (100ms por elemento)
        return Flux.fromIterable(datos)
                .delayElements(Duration.ofMillis(1000)); // Simula latencia
    }
}
