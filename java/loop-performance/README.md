# Resultados
Con 1 millón de clientes:
```text
Clientes encontrados (for-each): 201238
Tiempo for-each: 19.3072 ms
Clientes encontrados (stream): 201238
Tiempo stream: 23.1431 ms
Clientes encontrados (parallelStream): 201238
Tiempo parallelStream: 44.1885 ms
```

Con 10 millones de clientes
```text
Clientes encontrados (for-each): 1998987
Tiempo for-each: 117.1668 ms
Clientes encontrados (stream): 1998987
Tiempo stream: 151.6844 ms
Clientes encontrados (parallelStream): 1998987
Tiempo parallelStream: 64.7236 ms
```

Con 50 millones de clientes
```text
Clientes encontrados (for-each): 9996343
Tiempo for-each: 474.9562 ms
Clientes encontrados (stream): 9996343
Tiempo stream: 457.2232 ms
Clientes encontrados (parallelStream): 9996343
Tiempo parallelStream: 199.0326 ms
```

Cuando se trata de filtrar grandes volúmenes de datos en Java, la elección entre **for-each**, **stream()** y **parallelStream()** depende del tamaño de la lista y los requisitos de rendimiento. Para listas pequeñas o medianas (~1 millón de elementos), el **for-each tradicional** es la opción más rápida, ya que evita la sobrecarga de Streams. Sin embargo, el uso de **Streams** mejora la legibilidad y mantenibilidad del código sin una penalización significativa en el rendimiento.

Para listas muy grandes (~10 millones de elementos o más), **parallelStream() se vuelve la opción más eficiente**, ya que aprovecha múltiples núcleos para paralelizar el filtrado y reduce el tiempo de ejecución significativamente. No obstante, la paralelización introduce una sobrecarga adicional, por lo que su uso debe evaluarse caso por caso. 