# Spring Boot
**Spring Boot** es una extensión del **Spring Framework** diseñada para simplificar la creación de aplicaciones Java. Mientras que **Spring Framework** ofrece una amplia gama de herramientas para configurar manualmente aplicaciones y gestionar sus dependencias, Spring Boot automatiza gran parte de esta configuración mediante “starters” preconfigurados, minimizando la cantidad de código y configuración necesarios para comenzar a trabajar. En resumen, Spring Boot hace que el desarrollo con Spring sea más rápido y menos complicado, especialmente para aplicaciones independientes y basadas en microservicios.

## Tabla de Contenidos
- [Concepto de starters y su utilidad](#concepto-de-starters-y-su-utilidad)
- [Contenedores Integrados](#contenedores-integrados)
- [Uso de Spring Initializr para iniciar proyectos](#uso-de-spring-initializr-para-iniciar-proyectos)
- [Maven wrapper](#maven-wrapper)
- [Anotación @SpringBootApplication](#anotación-springbootapplication)
- [Configuración y Convenciones](#configuración-y-convenciones)
    - [Convenciones de estructura de proyectos](#convenciones-de-estructura-de-proyectos)
    - [Aauto configuration en Spring Boot](#auto-configuration-en-spring-boot)
    - [Propiedades de aplicación y perfil en Spring Boot](#propiedades-de-aplicación-y-perfil-en-spring-boot)
    - [application.properties / application.yml](#applicationproperties--applicationyml)
- [Referencias](#referencias)

## Concepto de starters y su utilidad
Los starters de Spring Boot son dependencias preconfiguradas que incluyen un conjunto de dependencias comunes y configuraciones. Por ejemplo, spring-boot-starter-web incluye Tomcat y configuración para aplicaciones web. Esto simplifica la configuración inicial del proyecto y ayuda a centrarse en el desarrollo de la lógica de negocio.

## Contenedores Integrados
Spring Boot incluye contenedores embebidos como Tomcat, Jetty o Undertow. Esto permite ejecutar la aplicación como un JAR auto-contenido que incluye el servidor web necesario para ejecutar la aplicación. Esto facilita el despliegue y la ejecución de aplicaciones sin necesidad de configuraciones adicionales de servidor.

## Uso de Spring Initializr para iniciar proyectos
[Spring Initializr](https://start.spring.io/) es una herramienta en línea que permite generar rápidamente proyectos de Spring Boot con las dependencias necesarias. Permite seleccionar las dependencias requeridas (como web, data, security) y generar un proyecto base listo para importar y comenzar a desarrollar.

## Maven wrapper
Spring Boot adopta y promueve el uso de Maven Wrapper de manera predeterminada en los proyectos generados con Spring Initializr para enfocarse en la simplicidad y la facilidad de configuración, así se elimina la necesidad de que los desarrolladores instalen Maven globalmente.

## Anotación @SpringBootApplication
La anotación **@SpringBootApplication** combina tres anotaciones principales: **@Configuration**, **@EnableAutoConfiguration** y **@ComponentScan**. Esto configura automáticamente la aplicación Spring Boot y habilita la configuración basada en anotaciones.

## Configuración y Convenciones
### Convenciones de estructura de proyectos
Spring Boot recomienda una estructura de directorios estándar que facilita la organización de los componentes de la aplicación. Por ejemplo:
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── ejemplo/
│   │           └── myapplication/
│   │               ├── controller/
│   │               ├── model/
│   │               ├── repository/
│   │               └── service/
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── ejemplo/
                └── myapplication/
                    ├── controller/
                    ├── repository/
                    └── service/

```
### Auto configuration en Spring Boot
Spring Boot realiza auto-configuración analizando el classpath de la aplicación y automáticamente configurando las dependencias y los beans de Spring necesarios. Esto simplifica la configuración inicial y reduce la cantidad de configuración explícita que un desarrollador necesita escribir.

### Propiedades de aplicación y perfil en Spring Boot
Las propiedades de la aplicación en Spring Boot se pueden definir en archivos de propiedades (application.properties) o en archivos YAML (application.yml). Estas propiedades pueden personalizarse según los diferentes perfiles de entorno (dev, test, prod) utilizando la notación application-{profile}.properties.

### application.properties / application.yml
Los archivos de configuración (application.properties o application.yml) son fundamentales en Spring Boot para configurar aspectos como las propiedades del servidor, conexiones a bases de datos, configuración de logging, entre otros. Permiten ajustar el comportamiento de la aplicación sin necesidad de modificar el código fuente.

## Referencias
Para más información, consulta las siguientes referencias:

* [Documentación Oficial de Spring Boot](https://docs.spring.io/spring-boot/)
* [Guía de Referencia de Spring Boot](https://spring.io/guides)
* [Código Fuente de Spring Boot](https://github.com/spring-projects/spring-boot)