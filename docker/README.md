![alt text](../images/docker.png)
# Docker Documentation

## Cargar una imagen en .tar con
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