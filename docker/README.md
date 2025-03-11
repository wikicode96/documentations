![alt text](./img/docker.png)
# Docker Documentation

## Indice
1. [Fundamentos de Docker](#fundamentos-de-docker)
    1. [Que es Docker](#que-es-docker)
    2. [Instalación de Docker Desktop](#instalación-de-docker-desktop)
    3. [Conceptos básicos](#conceptos-básicos)
    4. [Ciclo de vida de un contenedor](#ciclo-de-vida-de-un-contenedor)
2. [Diferencias entre contenedores y máquinas virtuales](#diferencias-entre-contenedores-y-máquinas-virtuales)
3. [Networking en Docker](#networking-en-docker)
    1. [Tipos de Redes en Docker](#tipos-de-redes-en-docker)
    2. [DNS en Docker](#dns-en-docker)
    3. [Ejemplo de redes en Docker Compose](#ejemplo-de-redes-en-docker-compose)
4. [Volúmenes y persistencia de datos](#volúmenes-y-persistencia-de-datos)
    1. [Volúmenes de Docker](#1-volúmenes-de-docker)
    2. [Bind Mounts (Montaje de Carpetas del Host)](#2️-bind-mounts-montaje-de-carpetas-del-host)
    3. [Tmpfs Mounts (Almacenamiento en Memoria)](#3️-tmpfs-mounts-almacenamiento-en-memoria)
    4. [Ejemplo Completo: Usar un Volumen con MySQL](#ejemplo-completo-usar-un-volumen-con-mysql)
    5. [Cuándo usar cada método](#cuándo-usar-cada-método)
5. [Dockerfiles y Mejores Prácticas](#dockerfiles-y-mejores-prácticas)
    1. [Creación de un Dockerfile](#creación-de-un-dockerfile)
    2. [Instrucciones comunes en Dockerfiles](#instrucciones-comunes-en-dockerfiles)
    3. [Optimización de Imágenes](#optimización-de-imágenes)
    4. [Uso de Multi-Stage Builds](#uso-de-multi-stage-builds)
6. [Docker Compose](#docker-compose)
    1. [Creación de un archivo docker-compose.yml](#creación-de-un-archivo-docker-composeyml)
    2. [Dependencias entre Contenedores](#dependencias-entre-contenedores)
    3. [Gestión de Entornos en Compose](#gestión-de-entornos-en-compose)
    4. [Escalabilidad en Docker Compose](#escalabilidad-en-docker-compose)
7. [Networking Avanzado](#networking-avanzado)
    1. [Conexión entre contenedores en diferentes redes](#conexión-entre-contenedores-en-diferentes-redes)
    2. [Comunicación entre contenedores y servicios externos](#comunicación-entre-contenedores-y-servicios-externos)
    3. [Redes Overlay en Docker Swarm](#redes-overlay-en-docker-swarm)
    4. [Herramientas para depuración de redes](#herramientas-para-depuración-de-redes)
8. [Docker Swarm y Orquestación](#docker-swarm-y-orquestación)
    1. [Introducción a Docker Swarm](#introducción-a-docker-swarm)
    2. [Diferencias entre Docker Swarm y Kubernetes](#diferencias-entre-docker-swarm-y-kubernetes)
    3. [Creación y gestión de un clúster de Docker Swarm](#creación-y-gestión-de-un-clúster-de-docker-swarm)
    4. [Servicios y tareas en Docker Swarm](#servicios-y-tareas-en-docker-swarm)
    5. [Escalado automático y distribución de contenedores](#escalado-automático-y-distribución-de-contenedores)
    6. [Configuración de balanceo de carga en Docker Swarm](#configuración-de-balanceo-de-carga-en-docker-swarm)
    7. [Actualizaciones y despliegue continuo en Docker Swarm](#actualizaciones-y-despliegue-continuo-en-docker-swarm)
8. [Desarrollo y Debugging en Docker](#desarrollo-y-debugging-en-docker)
    1. [Docker Compose para desarrollo local](#docker-compose-para-desarrollo-local)
    2. [Uso de *docker exec*, *docker logs* y *docker attach* para depurar contenedores](#uso-de-docker-exec-docker-logs-y-docker-attach-para-depurar-contenedores)
    3. [Uso de herramientas de análisis de contenedores: *cAdvisor*, *Prometheus* y *Grafana*](#uso-de-herramientas-de-análisis-de-contenedores-cadvisor-prometheus-y-grafana)
    4. [Logs y monitoring: cómo gestionar logs en Docker](#logs-y-monitoring-cómo-gestionar-logs-en-docker)
    5. [Docker CLI avanzado: docker ps, docker inspect, docker stats, etc](#docker-cli-avanzado-docker-ps-docker-inspect-docker-stats-etc)
    6. [Docker Events y cómo usarlos para depuración](#docker-events-y-cómo-usarlos-para-depuración)
9. [Portainer](#portainer)
10. [Compresión de imagenes](#compresión-de-imagenes)

## Fundamentos de Docker
### Que es Docker
Docker es una plataforma de software que permite empaquetar aplicaciones y sus dependencias en contenedores. Un contenedor es una unidad liviana, autónoma y ejecutable que contiene todo lo necesario para ejecutar una aplicación, como el código, las bibliotecas, las herramientas y otras dependencias del sistema. Esto garantiza que la aplicación funcione de manera consistente sin importar el entorno en el que se ejecute, ya sea en una computadora local, en un servidor o en la nube.

![alt text](./img/what-is-docker.png)

### Instalación de Docker Desktop
Seguir la guía oficial de instalación:
https://docs.docker.com/desktop/install/windows-install/

No olvide habilitar la virtualización de hardware en BIOS. Documentación detallada en el siguiente enlace:
https://docs.docker.com/desktop/troubleshoot/topics/#virtualization

### Conceptos básicos
* **Imágenes:** Plantillas de solo lectura utilizadas para crear contenedores. Contienen todo lo necesario para ejecutar una aplicación, incluyendo sistema operativo, bibliotecas y código.

* **Contenedores:** Entornos ligeros y portátiles que ejecutan una imagen. Incluyen todo lo necesario para ejecutar una aplicación, como código, bibliotecas y dependencias.

* **Docker Engine:** Plataforma que permite crear, gestionar y ejecutar contenedores Docker.

* **Docker Hub:** Repositorio en la nube donde se almacenan y comparten imágenes Docker.

* **Dockerfile:** Archivo de texto que contiene instrucciones para crear una imagen Docker, definiendo el entorno, dependencias y comandos a ejecutar en el contenedor.

* **Docker Compose:** Herramienta para definir y ejecutar aplicaciones multi-contenedor Docker mediante un archivo YML que configura servicios, redes y volúmenes.

* **Docker Swarm:** Herramienta de orquestación que gestiona y escala contenedores Docker como una única aplicación.

* **Kubernetes:** Plataforma de código abierto para automatizar la implementación, escalado y gestión de aplicaciones en contenedores, administrando clústeres de forma eficiente.

### Ciclo de vida de un contenedor
El ciclo de vida de un contenedor de Docker sigue una serie de pasos que permiten crear, ejecutar, gestionar y eliminar contenedores.

* **Crear:** Utilizas el comando docker create para crear un contenedor.
```bash
docker create -p <puertos> --name <nombre_contenedor> <imagen>
```

* **Ejecutar:** Después de crear el contenedor, se inicia el proceso de ejecución con el comando docker start. También es posible usar **docker run** para crear y ejecutar en un solo paso.
```bash
docker start <contenedor_id>
```
```bash
docker run -p <puertos> --name <nombre_contenedor> <imagen>
```

* **Detener:** El contenedor puede ser detenido en cualquier momento con el comando docker stop. Si el contenedor no se detiene de inmediato, se puede usar docker kill, que fuerza la terminación del contenedor.
```bash
docker stop <contenedor_id>
```
```bash
docker kill <contenedor_id>
```

* **Eliminar:** Después de detenerlo, el contenedor puede ser eliminado para liberar recursos, utilizando el comando docker rm.
```bash
docker rm <contenedor_id>
```

## Diferencias entre contenedores y máquinas virtuales
* Arquitectura:
    * VMs: Cada máquina virtual incluye un sistema operativo completo y se ejecuta sobre un hipervisor.
    * Contenedores: Comparten el mismo sistema operativo y kernel del host, solo incluyen las dependencias necesarias para la aplicación.

* Rendimiento:
    * VMs: Más pesadas, requieren más recursos y tiempo de arranque.
    * Contenedores: Ligeros, rápidos, consumen menos recursos y se inician rápidamente.

* Aislamiento:
    * VMs: Aislamiento completo entre sistemas operativos independientes.
    * Contenedores: Aislamiento a nivel de procesos, comparten el kernel del sistema operativo.

* Portabilidad:
    * VMs: Menos portátiles, dependen del hipervisor.
    * Contenedores: Muy portátiles, se pueden ejecutar en cualquier entorno con Docker.

* Gestión y Escalabilidad:
    * VMs: Más complejas de gestionar y escalar debido a su sobrecarga de recursos.
    * Contenedores: Faciles de gestionar y escalar, ideales para aplicaciones distribuidas y microservicios.

## Networking en Docker
Docker proporciona un sistema de redes altamente configurable para permitir que los contenedores se comuniquen entre sí y con el mundo exterior. Docker usa su propio motor de redes basado en namespaces de red de Linux y iptables para gestionar la conectividad.

### Tipos de Redes en Docker
Docker ofrece varios controladores de red (drivers) para diferentes escenarios:

* **Bridge (Por defecto):** Es el tipo de red predeterminado cuando se inicia un contenedor sin especificar una red. Permite la comunicación entre contenedores en el mismo host, pero no con otros hosts. Utiliza un NAT (Network Address Translation) para permitir el acceso a internet. Se pueden conectar varios contenedores al mismo bridge manualmente.

```bash
docker network create --driver bridge <mi_red_bridge>
docker network connect <mi_red_bridge> <mi_contenedor>
```

![alt text](./img/docker-engine-port-publishing-2000-opt.webp)

* **Host:** Usa la red del host directamente sin aislamiento. No crea una interfaz de red separada para el contenedor. Útil cuando se necesita el mejor rendimiento de red.

```bash
docker run --network host nginx
```

* **None:** Deshabilita por completo la red del contenedor. Útil para tareas que no requieren conexión a la red.

```bash
docker run --network none busybox
```

* **Overlay:** Permite la comunicación entre contenedores en múltiples hosts dentro de un clúster de Docker Swarm. Requiere Docker Swarm para funcionar. Usa VXLAN para crear redes virtuales distribuidas.

```bash
docker network create --driver overlay <mi_red_overlay>
```

* **Macvlan:** Permite asignar direcciones IP únicas a los contenedores dentro de la red física. Se comporta como si cada contenedor fuera un dispositivo físico en la red. Ideal para aplicaciones que requieren una dirección IP específica (como servidores DHCP o aplicaciones legadas).

```bash
docker network create -d macvlan \
  --subnet=192.168.1.0/24 \
  --gateway=192.168.1.1 \
  -o parent=eth0 mi_red_macvlan
```

### DNS en Docker
Docker usa un DNS interno para la resolución de nombres dentro de redes bridge y overlay, permitiendo que los contenedores se comuniquen sin conocer las IPs.

Para definir un DNS personalizado:
```bash
docker run --dns=8.8.8.8 ubuntu
```

### Ejemplo de redes en Docker Compose
Docker Compose permite definir redes de manera declarativa. Los servicios dentro de mi_red podrán comunicarse entre sí por nombre.
```yml
version: "3.8"
services:
  web:
    image: nginx
    networks:
      - mi_red
  app:
    image: myapp
    networks:
      - mi_red

networks:
  mi_red:
    driver: bridge
```

## Volúmenes y persistencia de datos
En Docker, los contenedores son efímeros, lo que significa que cuando se eliminan, también se eliminan los datos almacenados en su sistema de archivos interno. Para evitar la pérdida de datos y permitir la persistencia, Docker ofrece volúmenes y otros métodos de almacenamiento. A continuación los métodos de Persistencia en Docker:

### 1. Volúmenes de Docker
Son la forma recomendada de manejar la persistencia de datos en Docker. Los volúmenes se almacenan fuera del contenedor, en el host, dentro del directorio **/var/lib/docker/volumes/**.

**Ventajas:**
* ✅ Son gestionados por Docker.
* ✅ Pueden compartirse entre múltiples contenedores.
* ✅ Son más eficientes y seguros que los bind mounts.

**Crear un volumen:**
```bash
docker volume create mi_volumen
```

**Montar un volumen en un contenedor:**
```bash
docker run -d -v mi_volumen:/datos --name mi_contenedor ubuntu
```

Esto significa que todo lo que se escriba en /datos dentro del contenedor, permanecerá guardado en mi_volumen aunque el contenedor se elimine.

**Listar volúmenes:**
```bash
docker volume ls
```

**Eliminar un volumen:**
```bash
docker volume rm mi_volumen
```

### 2️. Bind Mounts (Montaje de Carpetas del Host)
En lugar de usar volúmenes administrados por Docker, los bind mounts permiten mapear directamente una carpeta del sistema anfitrión dentro del contenedor.
Ejemplo:
```bash
docker run -d -v /ruta/local:/ruta/contenedor --name mi_contenedor ubuntu
```
Aquí, la carpeta **/ruta/local** en el host se monta en **/ruta/contenedor** dentro del contenedor. Diferencias con los Volúmenes:
* ❌ Docker no gestiona su almacenamiento.
* ❌ Puede afectar la seguridad y rendimiento.
* ✅ Útil cuando se necesita acceso directo a archivos del host.

### 3️. Tmpfs Mounts (Almacenamiento en Memoria)
Se usan para almacenar datos en RAM en lugar de disco, lo que los hace muy rápidos, pero no persistentes.
```bash
docker run -d --tmpfs /tmp:rw,size=64m --name mi_contenedor ubuntu
```
Esto monta /tmp en memoria, con un límite de 64 MB.

### Ejemplo Completo: Usar un Volumen con MySQL
Si queremos correr un contenedor de MySQL sin perder los datos al reiniciarlo:
```bash
docker volume create mysql_data
docker run -d --name mysql_container -e MYSQL_ROOT_PASSWORD=secret -v mysql_data:/var/lib/mysql mysql:latest
```
Aquí, la base de datos se almacenará en el volumen mysql_data, y persistirá aunque el contenedor sea eliminado.

### Cuándo usar cada método
|Método      |Persistencia|Rendimiento        |Uso Recomendado                        |
|:-----------|:-----------|:------------------|:--------------------------------------|
|Volúmenes   |✅ Sí      |⚡ Alto            |Bases de datos, almacenamiento general |
|Bind Mounts |✅ Sí      |📉 Depende del host|Desarrollo, acceso directo a archivos  |
|Tmpfs       |❌ No      |🚀 Muy alto        |Datos temporales, cache en memoria     |

## Dockerfiles y Mejores Prácticas
### Creación de un Dockerfile
Un Dockerfile es un archivo de texto que contiene un conjunto de instrucciones para crear una imagen de Docker. Se define una serie de pasos que Docker sigue para construir la imagen. Ejemplo básico de un **Dockerfile:**
```dockerfile
# Imagen base
FROM ubuntu:latest

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar archivos necesarios
COPY . .

# Instalar dependencias
RUN apt-get update && apt-get install -y curl

# Definir variables de entorno
ENV PORT=8080

# Exponer un puerto
EXPOSE 8080

# Definir el comando por defecto
CMD ["echo", "Hello, Docker!"]
```

### Instrucciones comunes en Dockerfiles
* Define la imagen base desde la cual se construirá la nueva imagen.
```dockerfile
FROM node:18-alpine
```

* Ejecuta comandos durante la construcción de la imagen.
```dockerfile
RUN apt-get update && apt-get install -y python3
```

* Define el comando por defecto que se ejecutará cuando el contenedor inicie.
```dockerfile
CMD ["node", "app.js"]
```

* Similar a CMD, pero se usa para definir un comando principal fijo, permitiendo argumentos adicionales.
```dockerfile
ENTRYPOINT ["python3"]
```

* Indica el puerto en el que la aplicación dentro del contenedor escucha.
```dockerfile
EXPOSE 5000
```

* Define variables de entorno dentro del contenedor.
```dockerfile
ENV NODE_ENV=production
```

* Define un volumen para la persistencia de datos.
```dockerfile
VOLUME /data
```

* Establece el directorio de trabajo dentro del contenedor.
```dockerfile
WORKDIR /usr/src/app
```

### Optimización de Imágenes
Para reducir el tamaño y mejorar la eficiencia de las imágenes de Docker, sigue estas prácticas:
* **Eliminar capas innecesarias:** Usa comandos combinados en **RUN** para minimizar capas.
* **Usar imágenes base livianas:** Prefiere imágenes como **alpine** o **debian-slim**.
* **Limpiar caché y archivos temporales:**
```dockerfile
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
```

### Uso de Multi-Stage Builds
Los **multi-stage builds** permiten reducir el tamaño de las imágenes finales.
```dockerfile
# Primera etapa: Compilación
FROM golang:1.19 AS builder
WORKDIR /app
COPY . .
RUN go build -o myapp

# Segunda etapa: Imagen liviana
FROM alpine:latest
WORKDIR /root/
COPY --from=builder /app/myapp .
CMD ["./myapp"]
```
Esto reduce el tamaño de la imagen final al eliminar dependencias innecesarias.

## Docker Compose
### Creación de un archivo docker-compose.yml
El archivo docker-compose.yml define los servicios, redes y volúmenes necesarios para la aplicación.
Ejemplo básico:
```yml
services:
  db:
    image: mysql:latest
    volumes:
      - db_data:/var/lib/mysql
    networks:
      - my_network
volumes:
  db_data:
networks:
  my_network:
```

### Dependencias entre Contenedores
La directiva **depends_on** define el orden de inicio de los contenedores, asegurando que un servicio se ejecute solo después de que otro esté listo.
```yml
services:
  app:
    image: myapp
    depends_on:
      - db
  db:
    image: postgres:latest
```

### Gestión de Entornos en Compose
Se pueden definir variables de entorno en el archivo docker-compose.yml o en un archivo **.env**.
Ejemplo usando un archivo **.env**:
```yml
DB_USER=admin
DB_PASSWORD=secret
```

Ejemplo en **docker-compose.yml**:
```yml
environment:
  DB_USER: ${DB_USER}
  DB_PASSWORD: ${DB_PASSWORD}
```
### Escalabilidad en Docker Compose
Docker Compose permite escalar servicios horizontalmente con **docker-compose up --scale**.
```bash
docker-compose up --scale app=3
```
Esto inicia 3 instancias del servicio app, útil para manejar cargas de trabajo distribuidas.

## Networking Avanzado
### Conexión entre contenedores en diferentes redes
Se pueden conectar manualmente contenedores a diferentes redes:
```yml
docker network create my_network
docker network connect my_network my_container
```

### Comunicación entre contenedores y servicios externos
Los contenedores pueden acceder a servicios externos mediante nombres de host o configuraciones de red adecuadas en **docker-compose.yml**.
```yml
extra_hosts:
  - "api.example.com:192.168.1.100"
```

### Redes Overlay en Docker Swarm
Las redes overlay permiten conectar servicios en un clúster de Docker Swarm.
```bash
docker network create --driver overlay my_overlay_network
```

### Herramientas para depuración de redes
* Inspección de redes:
```bash
docker network inspect my_network
```

* Ver logs de un contenedor:
```bash
docker logs my_container
```

* Ejecutar comandos dentro de un contenedor:
```bash
docker exec -it my_container sh
```

## Docker Swarm y Orquestación
### Introducción a Docker Swarm
Docker Swarm es una herramienta de orquestación que permite gestionar múltiples contenedores distribuidos en varios servidores (nodos), tratándolos como un solo sistema. Se encarga de la alta disponibilidad, balanceo de carga y escalabilidad de las aplicaciones sin necesidad de herramientas externas.

¿Por qué usar Docker Swarm?
* **Escalabilidad:** Permite añadir o quitar contenedores de manera automática.
* **Alta disponibilidad:** Si un nodo falla, Swarm redistribuye los servicios a otros nodos activos.
* **Facilidad de uso:** Integración nativa con Docker, sin necesidad de instalar herramientas adicionales.

### Diferencias entre Docker Swarm y Kubernetes
|**Característica**   |**Docker Swarm**                             |**Kubernetes**                                   |
|:--------------------|:--------------------------------------------|:------------------------------------------------|
|**Facilidad de uso** |Más sencillo, comandos similares a Docker    |Más complejo, pero más personalizable            |
|**Escalabilidad**    |Adecuado para pequeños y medianos despliegues|Ideal para grandes infraestructuras              |
|**Balanceo de carga**|Interno, sencillo                            |Más flexible y configurable                      |
|**Almacenamiento**   |Volúmenes y redes de Docker                  |Más opciones avanzadas (CSI, persistencia)       |
|**Ecosistema**       |Integrado en Docker                          |Necesita herramientas externas (kubectl, kubeadm)|

Si tu aplicación es pequeña o mediana, Docker Swarm es una buena opción por su simplicidad. Para entornos más grandes y personalizados, Kubernetes ofrece mayor control.

### Creación y gestión de un clúster de Docker Swarm
* **Paso 1:** Inicializar un clúster Swarm. Para crear un clúster, ejecutamos en el nodo principal (máquina que actuará como **manager**). Esto generará un **token** para agregar más nodos al clúster:
```bash
docker swarm init --advertise-addr <IP_DEL_MANAGER>
```

* **Paso 2:** Agregar nodos al clúster. En las máquinas que actuarán como workers, ejecutamos:
```bash
docker swarm join --token <TOKEN_GENERADO> <IP_DEL_MANAGER>:2377
```
```bash
# Para ver los nodos conectados:
docker node ls
```

* **Paso 3: Gestionar nodos**
```bash
# Para remover un nodo:
docker node rm <ID_DEL_NODO>
```
```bash
# Para promover un nodo worker a manager:
docker node promote <ID_DEL_NODO>
```
```bash
# Para degradar un nodo manager a worker:
docker node demote <ID_DEL_NODO>
```

### Servicios y tareas en Docker Swarm
En Swarm, no ejecutamos contenedores directamente con **docker run**, sino que usamos **servicios**, que representan aplicaciones ejecutándose en múltiples réplicas.

* Crear un servicio
```bash
# Ejemplo: Iniciar un servicio Nginx con 3 réplicas:
docker service create --name miweb --replicas 3 -p 80:80 nginx
```
```bash
# Para listar los servicios activos:
docker service ls
```
```bash
# Para ver detalles de un servicio:
docker service ps miweb
```
```bash
# Para eliminar un servicio:
docker service rm miweb
```

### Escalado automático y distribución de contenedores
Swarm permite escalar servicios con **docker service scale**, distribuyendo contenedores en los nodos disponibles para garantizar alta disponibilidad.

* Escalar un servicio manualmente. Si queremos aumentar el número de réplicas a 5:
```bash
docker service scale miweb=5
```

* Ver la distribución de contenedores. Para ver dónde están corriendo los contenedores:
```bash
docker service ps miweb
```

### Configuración de balanceo de carga en Docker Swarm
Swarm incluye un balanceador de carga interno que distribuye el tráfico entre todas las réplicas de un servicio.
* Exponer un servicio con balanceo de carga
```bash
# Aquí, todas las peticiones a 8080 serán distribuidas entre los 3 contenedores internos en el puerto 5000
docker service create --name api --replicas 3 -p 8080:5000 myapp
```

### Actualizaciones y despliegue continuo en Docker Swarm
Swarm permite actualizaciones progresivas con **docker service update**, asegurando una transición sin interrupciones. Se pueden definir estrategias de actualización y rollback en caso de fallos.
* Actualizar un servicio. Por ejemplo, si queremos actualizar la versión de Nginx a 1.23 Swarm actualizará cada contenedor de forma progresiva. Ejemplo:
```bash
docker service update --image nginx:1.23 miweb
```

* Definir una estrategia de actualización. Podemos establecer una estrategia con una actualización de una réplica a la vez:
```bash
docker service update --update-parallelism 1 --image nginx:1.23 miweb
```

* Rollback (revertir una actualización). Si algo sale mal, volvemos a la versión anterior con:
```bash
docker service rollback miweb
```

## Desarrollo y Debugging en Docker
### Docker Compose para desarrollo local
Un **docker-compose.yml** típico para desarrollo con **nodemon** podría ser:
```yml
version: "3.8"
services:
  app:
    image: node:18
    volumes:
      - .:/app
    working_dir: /app
    command: ["npx", "nodemon", "server.js"]
    ports:
      - "3000:3000"
```

Luego se ejecuta con:
```bash
docker-compose up
```

### Uso de *docker exec*, *docker logs* y *docker attach* para depurar contenedores
Cuando un contenedor está en ejecución, podemos inspeccionarlo y depurarlo de varias maneras:

#### 💻 docker exec
Para ejecutar comandos dentro de un contenedor en ejecución. Ejemplo, entrar a un contenedor interactivo con bash:
```bash
docker exec -it mi-contenedor bash
```
Para ejecutar un solo comando sin entrar al contenedor:
```bash
docker exec mi-contenedor ls /app
```

#### 💻 docker logs
Para ver los logs de un contenedor en ejecución:
```bash
docker logs mi-contenedor
```
Para seguir los logs en tiempo real (tail -f):
```bash
docker logs -f mi-contenedor
```

#### 💻 docker attach
Para conectarse a la consola de un contenedor en ejecución. Si el contenedor tiene una salida activa (por ejemplo, un servidor interactivo), podemos unirnos a su sesión:
```bash
docker attach mi-contenedor
```
Para salir sin detener el contenedor, presiona **Ctrl + P, Ctrl + Q**.

### Uso de herramientas de análisis de contenedores: *cAdvisor*, *Prometheus* y *Grafana*
#### cAdvisor (Container Advisor)
Es una herramienta de Google que permite monitorizar métricas de rendimiento de los contenedores. Para ejecutarlo:
```bash
docker run -d --name=cadvisor -p 8080:8080 --volume=/var/run/docker.sock:/var/run/docker.sock google/cadvisor
```
Luego, accede a http://localhost:8080 para ver las métricas.

#### Prometheus y Grafana
Prometheus recopila métricas y Grafana las visualiza. Se configuran con Docker Compose:
```yml
version: "3.8"
services:
  prometheus:
    image: prom/prometheus
    ports:
      - "9090:9090"
  grafana:
    image: grafana/grafana
    ports:
      - "3000:3000"
```
Ejecutar con:
```bash
docker-compose up -d
```
Luego, accede a http://localhost:3000 (usuario: **admin**, contraseña: **admin**).

### Logs y monitoring: cómo gestionar logs en Docker
Por defecto, Docker almacena logs en JSON dentro de **/var/lib/docker/containers/<ID>/logs**.

#### Ver logs en tiempo real
```bash
docker logs -f mi-contenedor
```

#### Guardar logs en un archivo
```bash
docker logs mi-contenedor > logs.txt
```

#### Rotación de logs
Para evitar que los logs ocupen demasiado espacio, se puede configurar **log-driver**:
```bash
docker run --log-driver=json-file --log-opt max-size=10m --log-opt max-file=3 ubuntu
```
Esto limita los logs a 10MB por archivo y 3 archivos máximos.

#### Enviar logs a un servicio externo (ejemplo con Fluentd)
```bash
docker run --log-driver=fluentd --log-opt fluentd-address=localhost:24224 ubuntu
```

### Docker CLI avanzado: docker ps, docker inspect, docker stats, etc
#### 💻 docker ps
Listar contenedores en ejecución
```bash
docker ps
```

Para ver todos los contenedores, incluso los detenidos:
```bash
docker ps -a
```

#### 💻 docker inspect
Ver detalles de un contenedor
```bash
docker inspect mi-contenedor
```

Para obtener solo la IP de un contenedor:
```bash
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mi-contenedor
```

#### 💻 docker stats
Monitorizar uso de CPU y RAM en tiempo real
```bash
docker stats
```

#### 💻 docker top
Ver los procesos dentro de un contenedor
```bash
docker top mi-contenedor
```

### Docker Events y cómo usarlos para depuración
#### ¿Qué son Docker Events?
**docker events** muestra en tiempo real los eventos que ocurren en el sistema Docker, útil para depurar problemas en contenedores y redes.

Ejemplo de monitoreo en tiempo real:
```bash
docker events
```

Esto muestra eventos como:
* Creación y eliminación de contenedores
* Inicio y detención de servicios
* Errores en ejecución

Para filtrar por eventos específicos:
```bash
docker events --filter event=start
```

Para monitorear solo eventos de un contenedor específico:
```bash
docker events --filter container=mi-contenedor
```

## Portainer
Portainer es una herramienta muy útil para gestionar contenedores Docker a través de una interfaz gráfica (GUI). Con Portainer, puedes administrar fácilmente tus contenedores, imágenes, redes y volúmenes Docker sin necesidad de utilizar comandos en la línea de comandos.

### Instalar Portainer
#### 1. Crea un volumen para Portainer:
```bash
docker volume create portainer_data
```

#### 2. Inicia un contenedor de Portainer utilizando el siguiente comando:
```bash
docker run -d -p 9000:9000 --name=portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce
```
Aquí se está haciendo lo siguiente:
* **-d:** Ejecuta el contenedor en segundo plano.
* **-p 9000:9000:** Expone el puerto 9000, que es el puerto en el que Portainer se ejecutará en tu máquina local.
* **--name=portainer:** Asigna el nombre "portainer" al contenedor.
* **--restart=always:** Asegura que Portainer se reinicie automáticamente si el sistema se reinicia.
* **-v /var/run/docker.sock:/var/run/docker.sock:** Monta el socket de Docker para que Portainer pueda controlar Docker.
* **-v portainer_data:/data:** Almacena los datos de Portainer en un volumen persistente.

#### 3. Configurar Portainer
Accede a Portainer desde la dirección http://localhost:9000
1. Cuando accedas por primera vez a Portainer, se te pedirá que configures una cuenta de administrador.
2. Crea una contraseña de administrador.
3. Selecciona la opción "Local" para administrar el entorno Docker local donde Portainer está instalado.
4. ¡Listo! Ahora puedes empezar a gestionar tus contenedores, imágenes, redes y volúmenes desde la interfaz gráfica.


## Compresión de imagenes
### Guardar la imagen como un archivo .tar
Usa el comando docker save para exportar la imagen a un archivo .tar:
```bash
docker save -o nombre_archivo.tar nombre_imagen:tag
```

### Cargar una imagen en .tar con
Imaginemos que tenemos un archivo **myimage.tar** y quieres que la imagen se etiquete como **myapp:latest**. Aquí está el flujo completo:

1. Cargar la imagen:
```bash
docker load -i myimage.tar
```

2. Listar las imágenes para encontrar el IMAGE ID:
```bash
docker images
```
Supongamos que el **IMAGE ID** es **abc123**.

3. Renombrar y etiquetar la imagen:
```bash
docker tag abc123 myapp:latest
```