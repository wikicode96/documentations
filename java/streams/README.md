# Java Streams
## ¿Qué es un Stream en Java?
Un **Stream** en Java es una secuencia de elementos que permite realizar operaciones funcionales (como filtrar, transformar, reducir) de manera declarativa y eficiente sobre colecciones de datos.

Los Streams no almacenan datos, sino que procesan elementos de una fuente (como listas, arrays, conjuntos, etc.) y permiten manejar datos de forma fluida y legible.

### Características clave:
- **Declarativo:** describes qué quieres hacer, no cómo hacerlo.
- **No modifica la fuente:** las colecciones originales no cambian.
- **Lazy (perezoso):** las operaciones se ejecutan solo cuando es necesario, generalmente al final.
- **Puede ser paralelo:** procesamiento eficiente usando múltiples núcleos.

## Cómo crear un Stream
Normalmente, un Stream se crea a partir de una colección, array o generadores:

```java
List<String> lista = List.of("Ana", "Juan", "Pedro", "Laura");

Stream<String> stream = lista.stream();
```

## Operaciones principales en Streams
Las operaciones en streams se dividen en:
- **Intermedias:** transforman el stream y permiten encadenar más operaciones. Son lazy, no ejecutan nada hasta que hay una operación terminal.
- **Terminales:** generan el resultado final o efecto, y ejecutan el pipeline de operaciones.

### Operaciones intermedias comunes
| Método        | Qué hace                                                                              | Ejemplo básico                      |
| ------------- | ------------------------------------------------------------------------------------- | ----------------------------------- |
| `.filter()`   | Filtra elementos que cumplen una condición                                            | `.filter(x -> x.startsWith("A"))`   |
| `.map()`      | Transforma cada elemento a otro (función de transformación)                           | `.map(x -> x.toUpperCase())`        |
| `.distinct()` | Elimina elementos duplicados                                                          | `.distinct()`                       |
| `.sorted()`   | Ordena los elementos (natural o con comparador)                                       | `.sorted()` o `.sorted(Comparator)` |
| `.limit()`    | Limita el stream a un número máximo de elementos                                      | `.limit(5)`                         |
| `.skip()`     | Salta los primeros n elementos                                                        | `.skip(2)`                          |
| `.peek()`     | Permite ejecutar una acción intermedia sin modificar el stream, útil para debugging   | `.peek(System.out::println)`        |


### Operaciones terminales comunes
| Método         | Qué hace                                                          | Ejemplo básico                      |
| -------------- | ----------------------------------------------------------------- | ----------------------------------- |
| `.collect()`   | Recopila los elementos en una colección o resultado               | `.collect(Collectors.toList())`     |
| `.forEach()`   | Ejecuta una acción para cada elemento                             | `.forEach(System.out::println)`     |
| `.count()`     | Cuenta el número de elementos                                     | `.count()`                          |
| `.anyMatch()`  | Devuelve `true` si algún elemento cumple una condición            | `.anyMatch(x -> x.startsWith("A"))` |
| `.allMatch()`  | Devuelve `true` si todos cumplen una condición                    | `.allMatch(x -> x.length() > 2)`    |
| `.noneMatch()` | Devuelve `true` si ninguno cumple una condición                   | `.noneMatch(x -> x.isEmpty())`      |
| `.findFirst()` | Devuelve el primer elemento envuelto en Optional                  | `.findFirst()`                      |
| `.reduce()`    | Combina elementos en uno solo (reducción)                         | `.reduce(0, (a,b) -> a + b)`        |
| `.ifPresent()` | Ejecuta una acción si el valor está presente (usado con Optional) | `.ifPresent(System.out::println)`   |

## Ejemplos prácticos
### Filtrar y recoger
```java
List<String> lista = List.of("Ana", "Juan", "Pedro", "Laura");

List<String> filtrados = lista.stream()
    .filter(nombre -> nombre.startsWith("A"))
    .collect(Collectors.toList());

System.out.println(filtrados); // [Ana]
```

### Transformar con map
```java
List<String> mayusculas = lista.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());

System.out.println(mayusculas); // [ANA, JUAN, PEDRO, LAURA]
```

### Contar elementos que cumplen una condición
```java
long count = lista.stream()
    .filter(nombre -> nombre.length() > 3)
    .count();

System.out.println(count); // 3
```

### Buscar si existe algún elemento
```java
boolean existe = lista.stream()
    .anyMatch(nombre -> nombre.equalsIgnoreCase("juan"));

System.out.println(existe); // true
```

### Ordenar y limitar resultados
```java
List<String> ordenados = lista.stream()
    .sorted()
    .limit(2)
    .collect(Collectors.toList());

System.out.println(ordenados); // [Ana, Juan]
```

## Buenas prácticas con Streams
- Usa **operaciones intermedias** para construir un pipeline claro y legible.
- Las operaciones son **perezosas**: el procesamiento ocurre solo con una operación terminal.
- Prefiere funciones puras en `map`, `filter`, etc. (sin efectos secundarios).
- No modifiques las colecciones originales.
- Considera usar `.parallelStream()` para procesamiento paralelo en colecciones grandes.
