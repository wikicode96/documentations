# Spring Security
**Spring Security** es un marco de seguridad ampliamente utilizado en aplicaciones Java, desarrollado como parte del ecosistema Spring. Proporciona una serie de herramientas y configuraciones para gestionar la autenticación y la autorización, permitiendo proteger aplicaciones web y servicios RESTful contra accesos no autorizados. Ofrece integración con diversos métodos de autenticación, como formularios, HTTP Basic, y OAuth2, además de soporte para políticas de autorización a través de roles y permisos, ayudando a desarrollar aplicaciones seguras de manera eficiente y flexible.

## Dependencias del starter Spring Security
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```