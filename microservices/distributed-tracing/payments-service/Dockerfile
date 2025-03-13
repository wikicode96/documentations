# Usar una imagen base con JDK
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/payments-service-0.0.1-SNAPSHOT.jar payments-service.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "payments-service.jar"]
