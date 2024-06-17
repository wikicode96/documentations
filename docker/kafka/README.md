# Kafka en Docker

## Instalar Kafka
### Descargar la imagen más reciente
```shell
docker pull apache/kafka:latest
```
 
### Visualizar contenedores
```shell
docker ps
```
 
### Acceder al contenedor
```shell
docker exec -it {contenedor} bash
```
 
## Trabajando con Kafka 
### Crear un topic
```bash
./opt/kafka/bin/kafka-topics.sh --create --topic {nombre-del-topic} --bootstrap-server localhost:9092
```
 
### Listar todos los topics
```bash
./opt/kafka/bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```
 
### Visualizar consola conmo consumidor
```bash
./opt/kafka/bin/kafka-console-consumer.sh --topic {nombre-del-topic} --bootstrap-server localhost:9092
```
 
### Visualizar consola como productor
```bash
./opt/kafka/bin/kafka-console-producer.sh --topic {nombre-del-topic} --bootstrap-server localhost:9092
```
