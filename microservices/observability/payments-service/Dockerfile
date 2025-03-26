# Usar una imagen base con JDK
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/payments-service-0.0.1-SNAPSHOT.jar payments-service.jar

# Copiar el agente de OpenTelemetry al contenedor
COPY ./opentelemetry-javaagent.jar /otel/opentelemetry-javaagent.jar

# Configurar el agente con las opciones necesarias
ENV JAVA_TOOL_OPTIONS="-javaagent:/otel/opentelemetry-javaagent.jar \
-Dotel.service.name=payments-service \
-Dotel.exporter.otlp.endpoint=http://otel-collector:4318"

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "payments-service.jar"]
