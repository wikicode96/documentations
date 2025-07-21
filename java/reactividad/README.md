# 🧠 REACTIVIDAD EN JAVA – De Básico a Senior Pro 
## Índice
### 🔰 Nivel 0 – Fundamentos Previos (Requisitos)
1. Programación Java Avanzada
    * Streams API
    * Lambdas y funciones anónimas
    * CompletableFuture y programación asincrónica
    * Concurrencia y threading en Java (ExecutorService, ForkJoinPool)
2. Fundamentos de Programación Funcional
    * Inmutabilidad
    * Funciones puras
    * Composición y curryficación

### 🟢 Nivel 1 – Introducción a la Programación Reactiva
1. ¿Qué es la programación reactiva?
    * Principios: asincronía, flujo de datos, no bloqueante
    * Push vs Pull
    * El paradigma del Reactive Streams
2. Reactive Streams Specification (JCP 266)
    * Publisher
    * Subscriber
    * Subscription
    * Processor
3. ¿Qué es el Backpressure?
4. Ventajas y desventajas del modelo reactivo

### 🟡 Nivel 2 – Project Reactor (Base de Spring WebFlux)
1. Introducción a Project Reactor
    * ¿Por qué Reactor y no RxJava?
    * Mono vs Flux (tipos principales)
    * Operadores básicos (map, flatMap, filter, zip, concat, etc.)
2. Flujo de datos:
    * Creación de Publishers (Mono.just, Flux.fromIterable, Flux.range)
    * Suscripción y consumo
    * Transformación de datos (pipeline)
3. Gestión de errores reactiva
4. Control de backpressure
5. Schedulers y multithreading

### 🟠 Nivel 3 – Spring WebFlux
1. Introducción a Spring WebFlux
    * Diferencias con Spring MVC
    * Arquitectura interna (Reactor Netty, Servlet 3.1+)
2. Creación de controladores reactivos
    * @RestController + Mono / Flux
    * RouterFunction y programación funcional
3. Acceso reactivo a datos:
    * Spring Data R2DBC (bases SQL reactivas)
    * Spring Data MongoDB Reactive
4. Manejo de errores en WebFlux
    * @ControllerAdvice, ErrorWebExceptionHandler
5. Testing en entorno reactivo
    * StepVerifier
    * WebTestClient

### 🔵 Nivel 4 – Avanzado: Integración, Rendimiento y Arquitectura
1. WebSockets y SSE (Server-Sent Events)
    * Comunicación bidireccional
    * Integración con WebFlux
2. Seguridad Reactiva
    * spring-security-webflux
    * JWT + OAuth2 en modo no bloqueante
3. Integración con servicios externos
    * WebClient reactivo (reemplazo de RestTemplate)
    * Retry, circuit breakers (resilience4j, retryWhen)
4. Arquitectura de microservicios reactivos
    * Spring Cloud Gateway + WebFlux
    * Patrones como Backpressure, Reactive Composition, Service Mesh
5. Observabilidad en sistemas reactivos
    * Reactor debug hooks
    * Micrometer + Prometheus + Grafana
    * Logs con contexto (reactor.util.context.Context)

### 🔴 Nivel 5 – Experto Senior PRO
1. Tuning de rendimiento y profiling
    * Thread model de Reactor
    * Benchmarks (JMH) y profiling reactivo
2. Custom Operators y Processors
    * Crear operadores propios (transform, handle)
    * Crear Publisher y Subscriber personalizados
3. Dominar flujos fríos vs calientes
    * Diferencias clave
    * Uso avanzado de ConnectableFlux, ReplayProcessor, Sinks
4. Integración con Kafka/AMQP de forma reactiva
    * Reactive Kafka client
    * Backpressure en eventos de cola
5. Patrón CQRS y Event Sourcing Reactivo
6. Casos reales de migración de Spring MVC a WebFlux

### 📚 Bonus – Recursos y Buenas Prácticas
* Patrones de diseño para programación reactiva
* Antipatrones comunes (nested flatMap, memory leaks, etc.)
* Guías oficiales y documentación:
    * https://projectreactor.io/
    * https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html
    * https://github.com/reactor/reactor-core

## 🔰 Nivel 0 – Fundamentos Previos (Requisitos)
### 1. Programación Java Avanzada
#### ✅ Streams API
Permite procesar colecciones de datos de forma declarativa y paralela si se desea.

```java
List<String> names = List.of("Ana", "Luis", "Carlos");
List<String> upper = names.stream()
                          .filter(name -> name.length() > 3)
                          .map(String::toUpperCase)
                          .collect(Collectors.toList());
```

📌 Conceptos clave:

* map, filter, reduce, collect
* Streams secuenciales vs paralelos
* Lazy evaluation (evaluación perezosa)

#### ✅ Lambdas y funciones anónimas
Permiten escribir funciones más concisas, fundamentales en el estilo funcional.

```java
List<Integer> nums = List.of(1, 2, 3);
nums.forEach(n -> System.out.println(n * 2));
```

📌 Importante:
* (param) -> expresión
* Uso con interfaces funcionales (Predicate, Function, Consumer, etc.)

#### ✅ CompletableFuture y programación asincrónica
Modelo de concurrencia moderno, reemplazo de Future.

```java
CompletableFuture.supplyAsync(() -> "Hola")
                 .thenApply(String::toUpperCase)
                 .thenAccept(System.out::println);
```

📌 Métodos clave:
* supplyAsync(), thenApply(), thenCompose(), exceptionally()
* Paralelismo sin bloqueo

#### ✅ Concurrencia y threading en Java
Base para entender cómo funcionan los sistemas no bloqueantes.

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(() -> System.out.println("Hola desde otro hilo"));
executor.shutdown();
```

📌 Claves:
* ExecutorService, Thread, Runnable, Callable
* ForkJoinPool y paralelismo estructurado
* Problemas comunes: race conditions, deadlocks, sincronización

### 2. Fundamentos de Programación Funcional
#### ✅ Inmutabilidad
Evitar modificar el estado una vez creado.

```java
final List<String> lista = List.of("a", "b", "c");
// lista.add("d"); ← Esto fallará
```

📌 Beneficios:
* Seguridad en concurrencia
* Menor complejidad

#### ✅ Funciones puras
Funciones sin efectos secundarios ni dependencia de estado externo.

```java
int cuadrado(int x) {
    return x * x; // Siempre da el mismo resultado con la misma entrada
}
```

📌 Ventajas:
* Testeables
* Componibles
* Predecibles

#### ✅ Composición y curryficación
**Composición:** construir funciones complejas a partir de simples.

```java
Function<Integer, Integer> doble = x -> x * 2;
Function<Integer, Integer> cuadrado = x -> x * x;

Function<Integer, Integer> dobleLuegoCuadrado = doble.andThen(cuadrado);
System.out.println(dobleLuegoCuadrado.apply(3)); // (3*2)^2 = 36
```

**Curryficación:** descomponer funciones de múltiples argumentos en una serie de funciones unarias.

```java
Function<Integer, Function<Integer, Integer>> suma =
    x -> y -> x + y;

Function<Integer, Integer> sumar5 = suma.apply(5);
System.out.println(sumar5.apply(3)); // 8
```

📌 Útil para:
* Composición avanzada
* Reutilización
* Claridad y modularidad

## 🟢 Nivel 1 – Introducción a la Programación Reactiva
### 1. ¿Qué es la programación reactiva?
La programación reactiva es un paradigma orientado al flujo de datos asíncronos, donde los componentes reaccionan a los eventos a medida que ocurren. Es especialmente útil para aplicaciones que manejan:

* Muchas operaciones I/O (API REST, bases de datos, colas)
* Flujos de datos continuos (eventos, streams, sensores)
* Sistemas altamente concurrentes y escalables

Se basa en el concepto de Streams de datos que se procesan de forma no bloqueante y reactiva (el sistema reacciona cuando llegan datos).

#### ✅ Principios fundamentales
1. Asincronía
    * No hay espera activa (bloqueo) por resultados.
    * Las operaciones se ejecutan en segundo plano, liberando recursos.

2. Flujo de datos
    * Todo se trata como una secuencia de eventos o valores en el tiempo.

3. No bloqueante
    * Las llamadas no retienen hilos esperando respuestas.
    * Ideal para sistemas con alta concurrencia y pocas CPU (ej. microservicios).

#### ✅ Push vs Pull
| Modelo    | Pull (Imperativo)                     | Push (Reactivo)                        |
| --------- | ------------------------------------- | -------------------------------------- |
| Dirección | El consumidor pide datos al productor | El productor envía datos al consumidor |
| Ejemplo   | `Iterator`                            | `Publisher` de Reactive Streams        |
| Control   | El consumidor decide cuándo           | El productor emite cuando tenga datos  |
| Problema  | Ineficiencia en asincronía            | Riesgo de inundar al consumidor        |

#### ✅ El paradigma de Reactive Streams
**Reactive Streams** es una especificación estandarizada (JSR 166/Reactive Streams Initiative) que define cómo debe funcionar el procesamiento de datos asincrónicos de forma backpressure-aware (control de presión).

🔗 Es base de:
* Project Reactor
* RxJava 2+
* Akka Streams
* Kotlin Flow

### 2. Reactive Streams Specification (JCP 266)
Define 4 interfaces clave:

#### Publisher<T>
Produce y emite datos hacia los Subscribers.
```java
public interface Publisher<T> {
    void subscribe(Subscriber<? super T> s);
}
```

#### Subscriber<T>
Consume los datos emitidos por el Publisher.
```java
public interface Subscriber<T> {
    void onSubscribe(Subscription s);
    void onNext(T t);
    void onError(Throwable t);
    void onComplete();
}
```

#### Subscription
Conecta Publisher y Subscriber. Controla la demanda (backpressure).
```java
public interface Subscription {
    void request(long n); // solicita N elementos
    void cancel();        // cancela la suscripción
}
```

#### Processor<T, R>
Es un Publisher y Subscriber al mismo tiempo. Transforma flujos.
```java
public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {}
```

### 3. ¿Qué es el Backpressure?
Es un mecanismo para evitar que un productor abrume a un consumidor con demasiados datos a la vez.

🔧 Ejemplo: si un Publisher emite 10k eventos por segundo y el Subscriber solo puede procesar 1k/s, hay que aplicar backpressure o se saturará el sistema.

➡️ Se maneja con el método request(n) de la Subscription.

Ejemplo básico:
```java
Publisher<Integer> publisher = subscriber -> {
    subscriber.onSubscribe(new Subscription() {
        public void request(long n) {
            for (int i = 0; i < n; i++) {
                subscriber.onNext(i);
            }
            subscriber.onComplete();
        }

        public void cancel() {}
    });
};

Subscriber<Integer> subscriber = new Subscriber<>() {
    public void onSubscribe(Subscription s) {
        s.request(5);
    }

    public void onNext(Integer item) {
        System.out.println("Recibido: " + item);
    }

    public void onError(Throwable t) {
        t.printStackTrace();
    }

    public void onComplete() {
        System.out.println("¡Completado!");
    }
};

publisher.subscribe(subscriber);
```

### 4. Ventajas y desventajas del modelo reactivo
#### ✅ Ventajas del modelo reactivo
✅ Escalabilidad: maneja miles de conexiones con pocos hilos.
✅ Uso eficiente de recursos: no bloquea hilos mientras espera.
✅ Responsividad: respuesta más rápida bajo carga.
✅ Flexibilidad: componer flujos de forma declarativa.
✅ Tolerancia a fallos: permite reintentos, timeouts, fallback, etc.

#### ❌ Desventajas y desafíos
⚠️ Curva de aprendizaje elevada
⚠️ Debugging más complejo (trazas fragmentadas)
⚠️ No siempre necesario: para tareas CPU-bound o simples puede ser sobreingeniería
⚠️ Necesita stack completo no bloqueante (bases de datos, librerías, etc.)

## 🟡 Nivel 2 – Project Reactor (Base de Spring WebFlux)
### 1. Introducción a Project Reactor
Project Reactor es una biblioteca de programación reactiva para Java 8+ desarrollada por Pivotal (Spring). Implementa la especificación Reactive Streams y es la base para la programación reactiva en Spring WebFlux.

Reactor ofrece dos tipos principales de flujos reactivos:
* Mono<T> → 0 o 1 valor
* Flux<T> → 0 a N valores (stream de datos)

#### ✅ ¿Por qué Reactor y no RxJava?
| Característica     | Project Reactor                                  | RxJava                            |
| ------------------ | ------------------------------------------------ | --------------------------------- |
| Integración Spring | ✔ Nativo en WebFlux                              | ❌ No oficial                      |
| API más moderna    | ✔ Basado en Reactive Streams                     | 🔄 Necesita adaptar (Flowable)    |
| Peso y rendimiento | ✔ Más liviano                                    | 🔁 Más features, más pesado       |
| Ecosistema         | ✔ Mejor soporte con R2DBC, Spring Security, etc. | ✔ Multiplataforma (Android, etc.) |

#### ✅ Mono vs Flux (tipos principales)
```java
Mono<String> mono = Mono.just("Hola");       // 1 valor
Flux<Integer> flux = Flux.range(1, 5);       // 5 valores: 1..5
```

| Tipo | Representa      | Ejemplo de uso            |
| ---- | --------------- | ------------------------- |
| Mono | 0 o 1 elemento  | Resultado HTTP, BD, login |
| Flux | 0 a N elementos | Lista, stream, mensajes   |

#### ✅ Operadores básicos
```java
Flux<Integer> flujo = Flux.range(1, 5)
    .map(i -> i * 2)                        // [2, 4, 6, 8, 10]
    .filter(i -> i > 4)                     // [6, 8, 10]
    .flatMap(i -> Mono.just(i + 1))        // [7, 9, 11]
    .concatWith(Flux.just(100));           // [7, 9, 11, 100]
```

| Operador  | Descripción                                       |
| --------- | ------------------------------------------------- |
| `map`     | Transforma cada valor                             |
| `flatMap` | Transforma y aplana (útil para operaciones async) |
| `filter`  | Filtra valores                                    |
| `zip`     | Combina 2 flujos                                  |
| `concat`  | Une flujos secuencialmente                        |
| `merge`   | Une flujos en paralelo                            |

### 2. Flujo de datos:
#### ▶️ Creación de Publishers
```java
Mono<String> m = Mono.just("uno");
Flux<Integer> f1 = Flux.fromIterable(List.of(1, 2, 3));
Flux<Integer> f2 = Flux.range(10, 3); // 10, 11, 12
```

#### ▶️ Suscripción y consumo
```java
Flux.range(1, 3)
    .subscribe(
        item -> System.out.println("Item: " + item),
        error -> System.err.println("Error: " + error),
        () -> System.out.println("Completado")
    );
```

#### ▶️ Transformación de datos (pipeline)
```java
Flux.just("a", "b", "c")
    .map(String::toUpperCase)
    .subscribe(System.out::println); // A, B, C
```

### 3. Gestión de errores reactiva
```java
Mono.just("x")
    .map(x -> {
        if (x.equals("x")) throw new RuntimeException("Oops");
        return x;
    })
    .onErrorResume(e -> Mono.just("Recuperado")) // fallback
    .doOnError(e -> System.out.println("Error: " + e.getMessage()))
    .subscribe(System.out::println);
```

| Operador          | Uso                                   |
| ----------------- | ------------------------------------- |
| `onErrorResume()` | Reintenta con otro flujo (`fallback`) |
| `onErrorReturn()` | Devuelve un valor fijo si hay error   |
| `doOnError()`     | Lógica adicional al ocurrir error     |
| `retry()`         | Reintenta automáticamente N veces     |

### 4. Control de backpressure
Aunque Flux y Mono implementan backpressure internamente, puedes controlarlo explícitamente en casos avanzados.

**Estrategias comunes:**
```java
Flux.create(emitter -> {
    for (int i = 0; i < 1_000_000; i++) {
        emitter.next(i); // flujo rápido
    }
    emitter.complete();
})
.onBackpressureBuffer(1000)   // buffer limitado
.onBackpressureDrop()         // descarta si no hay espacio
.onBackpressureLatest()       // solo el último valor
.subscribe(System.out::println);
```

| Estrategia             | Descripción                              |
| ---------------------- | ---------------------------------------- |
| `onBackpressureBuffer` | Almacena temporalmente los valores       |
| `onBackpressureDrop`   | Descarta elementos cuando hay congestión |
| `onBackpressureLatest` | Retiene solo el último valor             |

### 5. Schedulers y multithreading
Project Reactor es **mono-hilo por defecto**, pero puedes cambiar de contexto con:

* subscribeOn(Scheduler)
* publishOn(Scheduler)

**Ejemplo con** Schedulers.parallel()

```java
Flux.range(1, 3)
    .doOnNext(i -> System.out.println("Antes: " + Thread.currentThread().getName()))
    .publishOn(Schedulers.parallel())
    .doOnNext(i -> System.out.println("Después: " + Thread.currentThread().getName()))
    .subscribe();
```

| Método        | Afecta a...            | Uso típico                    |
| ------------- | ---------------------- | ----------------------------- |
| `subscribeOn` | Origen del Publisher   | BD, WebClient, inicialización |
| `publishOn`   | Operadores posteriores | map/filter después de switch  |

🔧 Schedulers útiles:
* Schedulers.boundedElastic() – tareas bloqueantes
* Schedulers.parallel() – CPU-bound
* Schedulers.single() – un solo hilo
* Schedulers.immediate() – hilo actual