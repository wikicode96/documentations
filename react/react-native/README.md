![react-native](../img/react-native.png)
# React Native
React Native es un marco de desarrollo de código abierto creado por Facebook que se utiliza para construir aplicaciones móviles multiplataforma (iOS y Android) utilizando JavaScript y React. React Native utiliza componentes de la interfaz de usuario nativos, lo que resulta en un rendimiento rápido y una apariencia nativa en las aplicaciones móviles.

## Requerimientos
Es necesario tener **Node.js** y **npm** instalados. Para chequearlo usaremos los siguientes comandos:
```shell
node --version
```
```shell
npm --version
```

## Uso de Expo
Expo es una plataforma de código abierto que simplifica y acelera el desarrollo de aplicaciones móviles con React Native. Proporciona una serie de herramientas y servicios que permiten a los desarrolladores crear aplicaciones móviles de manera más rápida y sencilla, sin necesidad de configurar y mantener complejas infraestructuras.

## Crear un nuevo proyecto con Expo
* Crear un proyecto base
```shell
npx create-expo-app@latest
```
* Crear un proyecto en blanco
```shell
npx create-expo-app@latest --template blank
```

## Ejecutar proyecto
Ejecutar el siguiente comando:
```shell
npm start
```
En la consola se muestra las instrucciones para abrir la app en diferentes dispositivos.

### Ejecutar el proyecto con el QR
Si deseas abrir la aplicación en tu dispositivo movil leyendo el código QR necesitas tener instalada la app **Expo Go** en Android.

Para lograr que vuelva a aprecer el menú de Expo Go basta con agitar el dispositivo movil para que vuelva a mostrarse.

## Implementar TypeScript
1. Cambiar el formato a los archivos
Cambie el nombre de los archivos para utilizar la extensión .tsx o .ts
```shell
mv App.js App.tsx
```

2. Instalar las dependencias de desarrollo necesarias
Para instalar dependencias de desarrollo como typescript y @types/react en package.json:
```shell
npx expo install -- --save-dev typescript @types/react
```

3. Agregar la configuración base con tsconfig.json
Puede generar automáticamente un archivo tsconfig.json ejecutando el comando:
```shell
npx expo customize tsconfig.json
```

Recomiendo tener el archivo tsconfig.json con la siguiente configuración:
```json
{
  "extends": "expo/tsconfig.base",
  "compilerOptions": {
    "strict": true,
    "baseUrl": ".",
    "paths": {
      "@/*": ["src/*"]
    }
  }
}
```
El archivo tsconfig.json de un proyecto debe extender de **expo/tsconfig.base** de forma predeterminada.

## Componentes en React Native
En esta sección haré un breve resumen de los componentes más usados de React Native. Para más información ir a la documentación original en el siguiente enlace: https://reactnative.dev/docs/components-and-apis#basic-components

### Componentes básicos
* **View:** Similar a un \<div> en HTML.
```HTML
<View style={{backgroundColor: 'blue', flex: 0.3}} />
```

* **Text:** Para imprimir Texto.
```HTML
<Text>Hello World!</Text>
```

* **Image:** Usado para mostrar imagenes.
```HTML
<Image 
    style={styles.tinyLogo} 
    source={require('@expo/snack-static/logo.png')}
/>
```
```HTML
<Image
    style={styles.tinyLogo}
    source={{
        uri: 'https://reactnative.dev/img/tiny_logo.png',
    }}
/>
```

* **TextInput:** Para formularios y entradas de Texto.
```HTML
<TextInput
    style={styles.input}
    onChangeText={onChangeText}
    value={text}
/>
```
```HTML
<TextInput
    style={styles.input}
    onChangeText={onChangeNumber}
    value={number}
    placeholder="useless placeholder"
    keyboardType="numeric"
/>
```

* **ScrollView:** Te permitirá dar scroll en tu app y puede tener múltiples componentes dentro.
```HTML
<ScrollView style={styles.scrollView}>
```

* **StyleSheet:** Para darle apariencia a tu App, similar a CSS.
```TypeScript
import React from 'react';
import {StyleSheet, Text, View} from 'react-native';

const App = () => (
  <View style={styles.container}>
    <Text style={styles.title}>React Native</Text>
  </View>
);

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 24,
    backgroundColor: '#eaeaea',
  },
  title: {
    marginTop: 16,
    paddingVertical: 8,
    borderWidth: 4,
    borderColor: '#20232a',
    borderRadius: 6,
    backgroundColor: '#61dafb',
    color: '#20232a',
    textAlign: 'center',
    fontSize: 30,
    fontWeight: 'bold',
  },
});

export default App;
```

### Componentes de Interfaz de Usuario
* **Button:** Un componente de botón básico. Admite un nivel mínimo de personalización.
```HTML
<Button
  onPress={onPressLearnMore}
  title="Learn More"
  color="#841584"
  accessibilityLabel="Learn more about this purple button"
/>
```

* **Switch:**
```HTML
<Switch
    trackColor={{false: '#767577', true: '#81b0ff'}}
    thumbColor={isEnabled ? '#f5dd4b' : '#f4f3f4'}
    ios_backgroundColor="#3e3e3e"
    onValueChange={toggleSwitch}
    value={isEnabled}
/>
```
### Componentes de Listas

## React Navigation
Documentación de [React Navigation](./react-navigation/README.md).