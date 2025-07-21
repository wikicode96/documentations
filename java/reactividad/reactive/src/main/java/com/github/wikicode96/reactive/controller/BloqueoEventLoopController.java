package com.github.wikicode96.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/bloqueo-event-loop")
public class BloqueoEventLoopController {

    @GetMapping("/bloqueo-event-loop")
    public Mono<String> bloqueoEventLoop() {
        return Mono.fromCallable(() -> {
            try {
                System.out.println("Inicio bloqueo event loop");
                Thread.sleep(5000); // bloquea el event loop 5 segundos
                System.out.println("Fin bloqueo event loop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Tarea completada";
        });
    }

    @GetMapping("/no-bloqueo-event-loop")
    public Mono<String> noBloqueoEventLoop() {
        return Mono.fromCallable(() -> {
            try {
                System.out.println("Inicio tarea bloqueante en hilo separado");
                Thread.sleep(5000);
                System.out.println("Fin tarea bloqueante");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Tarea completada";
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
