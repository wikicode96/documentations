# Usar una imagen base con JDK
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/orders-service-0.0.1-SNAPSHOT.jar orders-service.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "orders-service.jar"]
