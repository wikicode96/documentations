![JavaScript](./img/js.png)
# JavaScript
## Tipos de datos
### Number
Representa tanto números enteros como de punto flotante.
```js
let entero = 42;
let flotante = 3.14;
```
El valor **NaN** o **Not-a-Number** pertece a los numbers. NaN indica que un valor que se suponía debía ser un número no puede ser representado como tal.

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
const nuevoArreglo = arreglo.filter(n => n !== 30 && n);
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
const nuevoArreglo = arreglo.map(n => n===30 ? 60 : n);
```

## Ternarios
Los operadores ternarios te permiten escribir condicionales de manera concisa en una sola línea. La estructura básica es:
```js
let edad = 18;
let mensaje = (edad >= 18) ? 'Eres mayor de edad' : 'Eres menor de edad';
console.log(mensaje); // Imprime: "Eres mayor de edad"

```

En este caso, si **edad** es mayor o igual a 18, **mensaje** será "Eres mayor de edad"; de lo contrario, será "Eres menor de edad".

## Bucles
### For
```js
const arreglo = ['Elemento1', 'Elemento2', 'Elemento3'];

for (let i = 0; i < arreglo.length; i++) {
    console.log(arreglo[i]);
}
```

### For ... of
```js
const arreglo = ['Elemento1', 'Elemento2', 'Elemento3'];

for (let i of arreglo) {
    console.log(arreglo[i]);
}
```

### Foreach
```js
const arreglo = ['Elemento1', 'Elemento2', 'Elemento3'];

arreglo.forEach(function(tech) {
    console.log(tech);
})
```

### Map
La diferencia entre **.map()** y **.forEach()** es que .map() genera un nuevo arreglo.
```js
const arreglo = ['Elemento1', 'Elemento2', 'Elemento3'];

const nuevoArreglo = arreglo.map(function(tech) {
    console.log(tech);
})
```

## Funciones
En el ejemplo puede ver la sintaxis de una función en JS.
```js
function sumar(n1, n2) {
    console.log(n1 + n2);
}

sumar(2, 3); // Resultado 2 + 3 = 5;
```

En los parametros de entrada podemos asignar valores por defecto en el caso de que no se inserten valores.
```js
function sumar(n1 = 0, n2 = 0) {
    console.log(n1 + n2);
}

sumar(); // Resultado 0 + 0 = 0;
```

### Arrow Functions
Sintaxis:
```js
const sumar = (n1 = 0, n2 = 0) => {
    let n3 = n1 + n2;
    return n3;
}
```

En una linea puedes eliminar los **{}**.
```js
const sumar = (n1 = 0, n2 = 0) => console.log(n1 + n2);
```

Si vas a retornar el valor en una línea puedes omitir el **return**.
```js
const sumar = (n1 = 0, n2 = 0) => n1 + n2;
const resultado = sumar(10, 20); // resultado = 30
```

## Optional Chaining ? y Nullish coalescing operator ??
### Optional Chaining ?
En este código de ejemplo tendriamos un error en el primer **console.log()** que impediría seguir ejecutando el programa, ya que **examenes** no existe en el objeto alumno. Gracias al signo **?** podemos continuar con la ejecución del código saltandonos esa línea en caso de que esa key no exista. Funciona similar a un **If else**
```js
const alumno = {
    nombre: 'Juan',
    clase: 'Programación',
    aprobado: true
}

console.log(alumno.examenes?.examen1);
console.log("Linea siguiente!");
```

### Nullish coalescing
En el siguiente ejemplo si **x** es distinto de **null** o **undefined** la variable **n** será igual a **x**, en caso contrario se le asignará el valor de **y**. En este ejmplo **n** es igual a 4:
```js
let x = null;
let y = 4;

let n = x ?? y
```

## Truthy y Falsy
En JavaScript, un valor es considerado **"truthy"** si se evalúa como verdadero en un contexto booleano. Esto significa que aunque el valor no sea estrictamente **true** (verdadero), JavaScript lo tratará como verdadero cuando se evalúe en una condición.
### Valores que son truthy
Cualquier valor no vacío que no sea **false**, como **true, "texto", 1, [], {},** etc.

### Valores que son falsy
false, 0, "", null, undefined, NaN

## Evaluación de cortocircuito
Con la siguiente sintaxis si **auth** es true se ejecutará la función a la derecha de los caracteres **&&**, por el contrario si es false denegará su ejecución.
```js
const auth = true;
auth && console.log('Usuario Autenticado');
```

## Importaciones
Si queremos importar la siguiente función sumar() necesitamos escribir **export**:
```js
export function sumar(n1, n2) {
    return n1 + n2;
}
```

También podemos usar la siguiente sintaxis para exportar varias funciones:
```js
function sumar(n1, n2) {
    return n1 + n2;
}

export {
    sumar
}
```

Y a continuación usar **import**:
```js
import { sumar } from './funciones.js'

const resultado = sumar(20, 10);
```

Si existiesen dos funciones importadas con el mismo nombre debemos usar **as** para que no exista conflictos:
```js
import { sumar as sumarEnteros} from './funciones1.js'
import { sumar as sumarFlotantes} from './funciones2.js'

const resultado = sumarEnteros(20, 10);
```

Si utilizas **export default** solo puede haber un **default** por archivo. Esta es su sintaxis:
```js
export default function sumar(n1, n2) {
    return n1 + n2;
}

export function restar(n1, n2) {
    return n1 - n2;
}
```

La función con **export default** se cargará en la variable de justo detrás del **import**. Recuerda que solo puede haber un **default** por archivo.
```js
import funcionDefault { restar } from './funciones.js'

const resultado = funcionDefault(20, 10);
```

## Fetch API
### Con Promise
```js
const url = 'https://jsonplaceholder.typicode.com/comments';

fetch(url)
    .then(respuesta => {
        /* 
            Devuelve datos de la respuesta, como el status,
            headers etc.
        */
        respuesta.ok ? return respuesta.json() : throw new Error('hubo un error...');
    })
    .then(data => console.log(data))
    .catch(error => console.log(error.message))
```

### Con Async Await
```js
const url = 'https://jsonplaceholder.typicode.com/comments';

const consultarAPI = async () => {
    try {
        const respuesta = await fetch(url);
        if (!respuesta.ok) throw new Error('hubo un error...');

        const data = await respuesta.json();
        console.log(data);
    } catch (error) {
        console.log(error.message);
    }
}
```

### Performance
Para medir los **ms** de la petición puedes usar **performance.now()**.
```js
const url = 'https://jsonplaceholder.typicode.com/comments';

const consultarAPI = async () => {
    try {
        const inicio = performance.now();

        const respuesta = await fetch(url);
        if (!respuesta.ok) throw new Error('hubo un error...');

        const data = await respuesta.json();
        console.log(data);

        const fin = performance.now();
        console.log(`${fin - inicio} ms`)
    } catch (error) {
        console.log(error.message);
    }
}
```

### Multiples fetch en paralelo
Con la siguiente sintaxis las peticiones se ejecutan y se procesan al mismo tiempo:
```js
const consultarMultiplesAPIs = async () => {
    const inicio = performance.now();

    const [respuesta1, respuesta2, respuesta3] = await Promise.all([fetch(url1), fetch(url2), fetch(url3)]);

    const [data1, data2, data3] = await Promise.all([respuesta1.json(), respuesta2.json(), respuesta3.json()]);

    console.log(data1);
    console.log(data2);
    console.log(data3);

    const fin = performance.now();
    console.log(`${fin - inicio} ms`)
}
```

## DOM Scripting
### Selectores
Con **querySelectorAll()** traemos todos los elementos del documento, mientras que **querySelector()** solo devolverá un elemento, el primero que cumpla la condición.

Supongamos que tenemos el siguiente código HTML:
```html
<h1 class="heading" id="heading">Hola!</h1>
```
Para capturar el elemento podemos usar el método **querySelector()** y usar una sintaxis como la de **CSS**, es decir, usar **.** para buscar elementos por clases y **#** para buscar elementos por id:

```js
const headingPorClase = document.querySelector('.heading');
const headingPorId = document.querySelector('#heading');
```

#### Obtener etiqueta HTML
```js
heading.tagName
```

#### Obtener texto
```js
heading.textContent
```

#### Obtener lista de clases
```js
heading.classList
```

#### Obtener id
```js
heading.id
```

### Manipular elementos
#### Modificar atributo
Puedes usar las propiedades de los objetos. Por ejemplo, para modificar el texto:
```js
heading.textContent = "El nuevo texto";
```

#### Eliminar un atributo
```js
heading.removeAttribute('id'); // Elimina el ID: id=''
heading.removeAttribute('class'); // Elimina las clases: class=''
```

#### Añadir un atributo
Si tenemos el siguiente **\<input>**:
```html
<input id="nombre" type="text">
```

Y queremos añadir un atributo **value** lo hariamos así:
```js
const inputNombre = document.querySelector('#nombre');
inputNombre.value = "Un nuevo valor...";
```

## Eventos
Puedes añadir eventos a los elementos. Listado de elementos y su documentación [aquí](https://developer.mozilla.org/es/docs/Web/Events).