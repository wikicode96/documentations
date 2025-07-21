package com.github.wikicode96.reactive.service;

import com.github.wikicode96.reactive.entity.BasicEntity;
import com.github.wikicode96.reactive.repository.DbSimulada;
import com.github.wikicode96.reactive.utils.EjecutarTareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class BasicService {

    @Autowired
    private DbSimulada dbSimulada;

    @Autowired
    private EjecutarTareas ejecutarTareas;

    public Mono<Void> simularLecturaReactiva() {
        return Mono.fromRunnable(() -> ejecutarTareas.simularLectura())
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    public Flux<BasicEntity> getElements() {
        return dbSimulada.getElements();
    }

    /**
     * 1. .subscribeOn()            Ejecuta lectura en hilo separado
     * 2. .then()                   Una vez termine la lectura, sigue con lo siguiente
     * 3. dbSimulada.getElements()  Obtenemos el Flux
     * 4. .collectList()            Lo convertimos en List<BasicEntity>
     * 5. .map()                    Ejecuta escritura
     * 6. .subscribeOn()            Escritura en hilo separado también
     */
    public Mono<String> procesarLecturaYEscritura() {
        return Mono.fromRunnable(() -> ejecutarTareas.simularLectura())
                .subscribeOn(Schedulers.boundedElastic())
                .then(dbSimulada.getElements()
                        .collectList()
                        .map(lista -> ejecutarTareas.simularEscritura(lista))
                        .subscribeOn(Schedulers.boundedElastic()));
    }
}
