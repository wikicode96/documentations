![JavaScript](./img/js.png)
# JavaScript
## Tipos de datos
### Number
Representa tanto números enteros como de punto flotante.
```js
let entero = 42;
let flotante = 3.14;
```

### BigInt
Se utiliza para representar números enteros muy grandes que no pueden ser representados por el tipo **Number**. Se crea añadiendo una **n** al final del número.
```js
let bigIntNumber = 9007199254740991n;
```

### String
Cadena de caracteres. Se puede definir usando comillas simples (' '), dobles (" ") o acento grave (backticks, ` `) para plantillas de cadenas.
```js
let cadena1 = 'Hola';
let cadena2 = "Mundo";
let cadena3 = `Hola ${cadena1}`;
```

### Boolean
Solo puede tener dos valores: true o false.
```js
let esVerdad = true;
let esFalso = false;
```

### Undefined
Representa una variable que ha sido declarada pero no definida.
```js
let indefinido;
```

### Null
Representa la ausencia intencional de cualquier valor.
```js
let vacio = null;
```

### Symbol
Representa un valor único y estático. Se utiliza principalmente como identificadores de propiedades de objetos. Por ejemplo si tienes dos Symbols e intentas compararlos con === el resultado será **false**.
```js
let simbolo = Symbol('descripcion');
```

## Variables
### var
No es recomendable su uso
### let
Puedes reasignarle cualquier valor.
```js
let variable = "Javier";
variable = 30;
```

### const
No se le puede reasignar valores y tienen que tener un valor inicial.
```js
const constante = 9.8;
```

## Objetos
Un objeto es un conjunto de propiedades en **JavaScript**, que están conpuestas por una clave y un valor.
```js
const producto = {
    nombre: "Monitor",
    precio: 250,
    disponible: false
}
```

### Destructuring
Puedes sacar las variables de un objeto en una sola linea.
```js
const { nombre, precio, disponible } = producto;

console.log(nombre);
console.log(precio);
console.log(disponible);
```

Renombrar variables usando destructuring:
```js
const { disponible: disponible } = producto;

console.log(disponible);
```

### Añadir un valor
Con la siguiente sintaxis puedes añadir valores a un objeto ya existente.
```js
producto.image = '.img/photo.jpg';
```

### Eliminar un valor
```js
delete producto.precio;
```

### Object.freeze()
Impide que un objeto sea modificado. No se puede cambiar, añadir o eliminar un valor.
```js
Object.freeze(producto);
```

### Object.seal()
Impide que en un objeto se añada o elimine un valor.
```js
Object.seal(producto);
```

### Unir objetos
Este código introducirá un objeto dentro del otro.
```js
const producto = {
    nombre: "Monitor",
    precio: 250,
    disponible: false
}

const carrito = {
    cantidad: 1,
    producto
}
```

Salida:
```js
const producto = {
    nombre: "Monitor",
    precio: 250,
    disponible: false
}

const carrito = {
    cantidad: 1,
    producto: {
        nombre: "Monitor",
        precio: 250,
        disponible: false
    }
}
```

Si queremos volcar el contenido de un objeto en el otro seguiremos la siguiente sintaxis. También podemos conseguir el mismo resultado con **Object.assign()**.
```js
const producto = {
    nombre: "Monitor",
    precio: 250,
    disponible: false
}

const carrito = {
    cantidad: 1,
    ...producto
}
```

Salida:
```js
const producto = {
    nombre: "Monitor",
    precio: 250,
    disponible: false
}

const carrito = {
    cantidad: 1,
    nombre: "Monitor",
    precio: 250,
    disponible: false
}
```

## Template Strings y Concatenación
### Concatenar
```js
const nombre = "Javier";
const apellido = "Martinez";

console.log('El cliente es:' + nombre + ' '+ apellido);
```

### Template Strings
Se usa con comillas inclinadas **`**
```js
const nombre = "Javier";
const apellido = "Martinez";

console.log(`El cliente es: ${nombre}  ${apellido}`);
```

## Arrays
En JS los arreglos pueden tener valores de diferentes tipos de datos. Sintaxis para instanciar y acceder a un arreglo:
```js
const arreglo = [1514, 1789, true, false, "Hello", null];

console.log(arreglo[3])
```

### Añadir valores
Puedes poner cualquier posición del arreglo y lo sustituirá. Ejemplo:
```js
const arreglo = [1514, 1789, true, false, "Hello", null];
arreglo[10] = "Nuevo valor";

console.log(arreglo[10])
```

Aunque el arreglo tiene 6 valores se introducirá correctamente.

Otra forma de añadir un valor es la siguiente:
```js
const arreglo = [1514, 1789, true, false, "Hello", null];
arreglo.push("Nuevo valor");
```

Lo más recomendable es no mutar el arreglo, sino generar uno nuevo:
```js
const arreglo = [1514, 1789, true, false, "Hello", null];
const nuevoArreglo = [...arreglo, "Nuevo valor"];
```

### Eliminar valores
Para eliminar el primer elemento usamos **.shift()**.
```js
const arreglo = [1514, 1789, true, false, "Hello", null];
arreglo.shift();
```

Para volcar el arreglo usamos **.filter()**.
```js
const arreglo = [20, 30, 40];
const nuevoArreglo = arreglo.filter(n => {
    if (n !== 30){
        return n;
    }
});
```

### Modificar valores
Modificando el arreglo.
```js
const arreglo = [1514, 1789, true, false, "Hello", null];
arreglo[2] = 3000;
```

Para volcar el arreglo usamos **.map()**.
```js
const arreglo = [20, 30, 40];
const nuevoArreglo = arreglo.map(n => {
    if (n === 30){
        return 60;
    } else {
        return n;
    }
});
```
