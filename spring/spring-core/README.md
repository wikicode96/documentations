# Spring Core
Spring Core es el núcleo del Spring Framework, proporcionando una base robusta para la configuración y gestión de componentes en aplicaciones Java. Este documento explora los conceptos más importantes de Spring Core, incluyendo Inversión de Control (IoC) y la Inyección de Dependencias (DI).

## Inversión de Control (IoC)
### Contenedor IoC
El contenedor IoC de Spring es responsable de gestionar la configuración y el ciclo de vida de los objetos (beans). El contenedor utiliza la configuración proporcionada para instanciar, ensamblar y gestionar los beans de la aplicación.

Las implementaciones principales del contenedor IoC son:

* BeanFactory: Es el contenedor IoC más básico, que ofrece capacidades básicas de IoC.
* ApplicationContext: Extiende BeanFactory para incluir características empresariales, como soporte para la gestión de eventos y la resolución de mensajes.
```Java
// Ejemplo de uso de ApplicationContext
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
MyBean myBean = context.getBean(MyBean.class);
```

### Tipos de IoC
1. Inyección de Constructor: Se pasa la dependencia a través del constructor.
2. Inyección de Setter: Se pasa la dependencia a través de métodos setter.
3. Inyección de Campo: Utiliza anotaciones para inyectar dependencias directamente en los campos.

## Inyección de Dependencias (DI)
La Inyección de Dependencias (DI) permite que las dependencias de los objetos sean proporcionadas en lugar de ser creadas o buscadas por el propio objeto. Esto facilita el desacoplamiento de los componentes y la inyección de implementaciones alternativas para pruebas o configuraciones diferentes.

### Tipos de DI
* Inyección de Constructor:
```Java
@Component
public class MyService {
    private final MyRepository repository;

    @Autowired
    public MyService(MyRepository repository) {
        this.repository = repository;
    }
}
```

* Inyección de Setter:
```Java
@Component
public class MyService {
    private MyRepository repository;

    @Autowired
    public void setRepository(MyRepository repository) {
        this.repository = repository;
    }
}
```

* Inyección de Campo:
```Java
@Component
public class MyService {
    @Autowired
    private MyRepository repository;
}
```

## Beans en Spring
### Definición y Configuración de Beans
Un Bean en Spring es un objeto que el contenedor IoC instancia, configura y gestiona. Los beans pueden ser definidos en archivos XML, anotaciones o mediante clases Java.

* XML:
```XML
<bean id="myService" class="com.package.MyService"/>
```

* Anotaciones:
```Java
@Component
public class MyService {
}
```

* Bean:
```Java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

### Alcance de los Beans (@Scope)
La anotación @Scope se utiliza para definir el alcance (scope) de un bean, determinando el ciclo de vida y la visibilidad del bean en la aplicación. Los scopes más comunes son:

* Singleton: Este es el scope predeterminado. Un solo objeto bean se crea por contenedor de Spring y se comparte en toda la aplicación.

```Java
@Component
@Scope("singleton")
public class MyService {
}
```

* Prototype: Cada solicitud de bean crea una nueva instancia.
```Java
@Component
@Scope("prototype")
public class MyService {
}
```

* Request: Una nueva instancia de bean se crea para cada solicitud HTTP. Este scope es aplicable en aplicaciones web.
```Java
@Component
@Scope("request")
public class MyService {
}
```

* Session: Una nueva instancia de bean se crea para cada sesión HTTP. Este scope es aplicable en aplicaciones web.
```Java
@Component
@Scope("session")
public class MyService {
}
```

* Application: Una única instancia de bean se comparte a lo largo del contexto de la aplicación.
```Java
@Component
@Scope("application")
public class MyService {
}
```

### Ciclo de Vida de los Beans
El ciclo de vida de un bean incluye varias fases:

1. Instanciación: El contenedor crea una instancia del bean.
2. Inyección de Dependencias: Se inyectan las dependencias en el bean.
3. Inicialización: Métodos específicos como @PostConstruct se llaman.
4. Uso: El bean está listo para ser utilizado por la aplicación.
5. Destrucción: Métodos específicos como @PreDestroy se llaman antes de que el contenedor destruya el bean.

```Java
@Component
public class MyService {
    @PostConstruct
    public void init() {
        // Código de inicialización
    }

    @PreDestroy
    public void cleanup() {
        // Código de limpieza
    }
}
```

### Diferencias en el ciclo de vida según el tipo de DI
Ejecutaremos el siguiente código de ejemplo y observaremos la salida que nos devuelve el programa cuando se inyecta por campo y cuando se inyecta por constructor.

Primero ejecutaremos el código inyectando por campo:
```Java
@RestController
public class DemoController {

    @Autowired
    private DemoService service;

    public DemoController() {
        System.out.println("Soy el Controller! Estoy inyectando el Servicio por campo.");
    }

    @PreDestroy
    public void init() {
        System.out.println("Soy el Controller y este es el final de mi ciclo de vida!");
    }
}
```
```Java
@Service
public class DemoService {

    public DemoService() {
        System.out.println("Soy el Servicio y he sido creago!");
    }

    @PreDestroy
    public void init() {
        System.out.println("Soy el Servicio y este es el final de mi ciclo de vida!");
    }
}
```

Al inyectar por campo la salida del programa será la siguiente:
```
Soy el Controller! Estoy inyectando el Servicio por campo.
Soy el Servicio y he sido creago!

Soy el Controller y este es el final de mi ciclo de vida!
Soy el Servicio y este es el final de mi ciclo de vida!
```

Ahora ejecutaremos el código inyectando por constructor:
```Java
@RestController
public class DemoController {

    private DemoService service;

    public DemoController(DemoService service) {
        System.out.println("Soy el Controller! Estoy inyectando el Servicio por campo.");
        this.service = service;
    }

    @PreDestroy
    public void init() {
        System.out.println("Soy el Controller y este es el final de mi ciclo de vida!");
    }
}
```
```Java
@Service
public class DemoService {

    public DemoService() {
        System.out.println("Soy el Servicio y he sido creago!");
    }

    @PreDestroy
    public void init() {
        System.out.println("Soy el Servicio y este es el final de mi ciclo de vida!");
    }
}
```

Al inyectar por constructor la salida del programa será la siguiente:
```
Soy el Servicio y he sido creago!
Soy el Controller! Estoy inyectando el Servicio por campo.

Soy el Controller y este es el final de mi ciclo de vida!
Soy el Servicio y este es el final de mi ciclo de vida!
```

Si observamos al inyectar por campo primero se inicializará el Bean padre que inyecta sus dependencias, por el contrario al inyectar por constructor se inicializará primeramente los Beans hijos, incluso si prestamos atención la línea de código this.service = service; se ejecuta antes que el mensaje del constructor a pesar de estar por detrás de ella.

## Configuración de Spring
Spring permite la configuración de múltiples maneras:

### Configuración XML
La configuración tradicional utiliza archivos XML para definir los beans y sus dependencias.
```XML
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="myService" class="com.example.MyService"/>
</beans>
```

### Configuración basada en Anotaciones
Utiliza anotaciones en las clases Java para definir la configuración de los beans.
```Java
@ComponentScan("com.package")
public class AppConfig {
}
```

### Configuración basada en Java
Define la configuración en clases Java usando la anotación @Configuration.
```Java
@Configuration
@ComponentScan("com.package")
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```
## Referencias
Para más información, consulta las siguientes referencias:

* [Documentación Oficial de Spring](https://docs.spring.io/spring-framework/reference/)
* [Guía de Referencia de Spring Core](https://spring.io/guides)
* [Código Fuente de Spring](https://github.com/spring-projects/spring-framework)