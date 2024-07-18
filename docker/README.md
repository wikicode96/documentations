![alt text](./img/docker.png)
# Docker Documentation
## Conceptos básicos
![alt text](./img/what-is-docker.png)
* **Imágenes:** Son plantillas de solo lectura utilizadas para crear contenedores. Contienen todo lo necesario para ejecutar una aplicación, incluyendo el sistema operativo, las bibliotecas y el código de la aplicación.

* **Contenedores:** Son entornos ligeros y portátiles que contienen todo lo necesario para empaquetar una aplicación, incluyendo código, bibliotecas y dependencias. Es nuestra imagen ya en ejecución.

* **Docker Engine:** Es una plataforma de software que permite la creación, gestión y ejecución de contenedores Docker.

* **Docker Hub:** Es un repositorio en la nube donde se pueden almacenar y compartir imágenes de contenedores Docker.

* **Docker Compose:** Es una herramienta que permite definir y ejecutar aplicaciones multi-contenedor Docker de manera sencilla, utilizando un archivo YAML para configurar los servicios de la aplicación.

* **Docker Swarm:** Es una herramienta de orquestación de contenedores Docker que permite gestionar y escalar múltiples contenedores como una sola aplicación.

* **Kubernetes:** Es una plataforma de código abierto para automatizar la implementación, el escalado y la administración de aplicaciones en contenedores. Permite la gestión de clústeres de contenedores de manera eficiente y flexible.

## Instalación de Docker Desktop
Seguir la guía oficial de instalación:
https://docs.docker.com/desktop/install/windows-install/

No olvide habilitar la virtualización de hardware en BIOS. Documentación detallada en el siguiente enlace:
https://docs.docker.com/desktop/troubleshoot/topics/#virtualization

## Comandos básicos
### Trabajar con imagenes
* Listar las imágenes
```shell
docker image ls
```

* Crea y ejecuta un nuevo contenedor a partir de una imagen
```shell
docker run -p 9092:9092 --name <NOMBRE CONTENEDOR> <IMAGE ID>
```

### Trabajar con contenedores
* Mostar los contenedores en cualquier estado
```shell
docker ps -a
```

* Mostar los contenedores en ejecución
```shell
docker ps
```

* Inicializar un contenedor detenido
```shell
docker start <NOMBRE CONTENEDOR o ID CONTENEDOR>
```

* Detener un contenedor
```shell
docker stop <NOMBRE CONTENEDOR o ID CONTENEDOR>
```

* Detener todos los contenedores
```shell
docker stop $(docker ps -q)
```

* Mostrar el log de la aplicación
```shell
docker logs <NOMBRE CONTENEDOR o ID CONTENEDOR>
```

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