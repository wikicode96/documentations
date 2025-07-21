package com.github.wikicode96.reactive.controller;

import com.github.wikicode96.reactive.entity.BasicEntity;
import com.github.wikicode96.reactive.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/basics-events")
public class BasicsEventsController {

    @Autowired
    private BasicService basicService;

    @GetMapping("/simular-lectura")
    public Mono<Void> simularLecturaReactiva() {
        return basicService.simularLecturaReactiva();
    }

    @GetMapping("/get-alementos")
    public Flux<BasicEntity> getElements() {
        return basicService.getElements();
    }

    @GetMapping("/procesar-lectura-escritura")
    public Mono<String> procesarLecturaYEscritura() {
        return basicService.procesarLecturaYEscritura();
    }
}
