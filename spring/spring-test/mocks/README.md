# 🧪 Spring Boot – Mock vs MockBean en tests de integración

Este proyecto demuestra una confusión común al escribir tests de integración en Spring Boot: **la diferencia entre `@Mock` / `@InjectMocks` (de Mockito)** y **`@MockBean` (de Spring Boot)**.

---

## 🧭 Escenario

Tenemos 3 clases:

MyController -> MyService -> MyDependency

- `MyController` expone un endpoint `/test`.
- `MyService` realiza una lógica llamando a `MyDependency`.
- Queremos testear que al hacer una llamada HTTP al endpoint, **se invoca el método de `MyDependency`**.

---

## ❌ Ejemplo fallido – `BadInjectionTest`

```java
@InjectMocks
private MyService service;

@Mock
private MyDependency dependencyMock;
```

En este test se usan anotaciones de **Mockito puro** (`@Mock`, `@InjectMocks`). Sin embargo:

* El `@SpringBootTest` levanta el **contexto real de Spring**.
* Spring crea su **propio bean de** `MyService` y `MyDependency`.
* El mock creado por `@Mock` **no se inyecta en el contexto de Spring**.
* Por tanto, al llamar al endpoint con `MockMvc`, se ejecuta el bean real, **no el mock**, y el `verify(...)` **falla**.

![bad test schema](./img/badtest.svg)

## ✅ Ejemplo correcto – GoodInjectionTest

```java
@MockBean
private MyDependency dependencyMock;
```

* `@MockBean` es una anotación de **Spring Boot**.
* Al usarla, Spring reemplaza el bean real de `MyDependency` por el mock **dentro del contexto de Spring**.
* Entonces cuando se llama al endpoint, `MyService` recibe el mock.
* El `verify(...)` funciona correctamente.

![good test schema](./img/goodtest.svg)

## 🧠 Diferencia clave
| Anotación      | Contexto    | ¿Inyecta en beans de Spring? | Uso recomendado                 |
| -------------- | ----------- | ---------------------------- | ------------------------------- |
| `@Mock`        | Mockito     | ❌ No                         | Tests unitarios sin Spring      |
| `@InjectMocks` | Mockito     | ❌ No                         | Inyección manual con Mockito    |
| `@MockBean`    | Spring Boot | ✅ Sí                         | Tests de integración con Spring |
