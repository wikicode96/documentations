
![wsl img](./images/wsl.png)
# Windows Subsystem for Linux (WSL)
Es una característica de Windows que permite ejecutar distribuciones de Linux directamente en Windows sin necesidad de una máquina virtual o un arranque dual (dual-boot).
WSL permite ejecutar binarios de Linux en Windows, facilitando la integración de herramientas de desarrollo que antes requerían un sistema Linux separado.
Ofrece acceso a los archivos del sistema Windows desde Linux y viceversa, permitiendo a los usuarios trabajar con archivos y herramientas de ambos sistemas de manera fluida.
Aunque no es tan rápido como un sistema Linux nativo, WSL ofrece un desempeño razonable para muchas tareas de desarrollo y scripting.
A diferencia de soluciones como VirtualBox o VMware, WSL no requiere la configuración de una máquina virtual completa, lo que reduce el consumo de recursos del sistema.

## Versiones de WSL
1. WSL 1: La primera versión utiliza una capa de compatibilidad para traducir las llamadas del sistema Linux a Windows. Es más rápido en términos de acceso a los archivos del sistema Windows.

2. WSL 2: Introduce un kernel Linux completo basado en tecnología de máquina virtual ligera, lo que mejora la compatibilidad del sistema y el rendimiento para tareas más complejas y orientadas a Linux. También permite el uso de Docker y otros servicios que requieren un kernel completo de Linux.

## Instalación de WSL y Distros
### Instalación de WSL2
Asegúrate de que la virtualización esté habilitada en tu BIOS. WSL 2 requiere la virtualización habilitada.
Ejecutar el comando como administrador:no 
```shell
choco install wsl2 -y
```

### Instalación de Distros
Puedes ver las distribuciones disponibles en la Microsoft Store o usar la línea de comandos de PowerShell. Con la Store solo tienes que buscar la dstro y obtener. Con PowerShell para listar las distribuciones que puedes instalar ejecuta el siguiente comando:
```shell
wsl --list --online
```

A continuación para instalar la distro deseada:
```shell
wsl --install --distribution <Distro>
```
Se puede usar la flag --distribution o -d

## Configuración
Para establecer WSL 2 como versión predeterminada abre PowerShell y ejecuta el siguiente comando para establecer WSL 2 como tu versión predeterminada:
```shell
wsl --set-default-version 2
```

## Comandos básicos
Comprobar la versión de wsl, kernel y otras caracteristicas:
```shell
wsl --version
```

Detener todas las distribuciones WSL y cierra la sesión:
```shell
wsl --shutdown
```

Listar todas las distribuciones de Linux instaladas en WSL:
```shell
wsl --list
```

Listar todas las distribuciones con más detalles:
```shell
wsl --list --verbose
```

Establecer la versión predeterminada para nuevas distribuciones (WSL1 o WSL2):
```shell
wsl --set-version <Distro> <Version>
```

Convertir una distribución específica de WSL a la versión especificada (WSL1 o WSL2):
```shell
wsl --set-default-version <Version>
```
## Integración con Herramientas y Desarrollo
### Desarrollo con Visual Studio Code
Es una herramienta de Visual Studio Code (VS Code) que permite a los desarrolladores usar una distribución de Linux instalada en WSL como entorno de desarrollo para editar código y ejecutar aplicaciones. Se puede acceder y modificar archivos ubicados en la distribución de WSL directamente desde VS Code.

Extension ID: 
```
ms-vscode-remote.remote-wsl
```
### Docker y Contenedores
Docker y WSL2 (Windows Subsystem for Linux 2) forman una combinación poderosa para el desarrollo en entornos Windows, facilitando la ejecución de contenedores Linux en un sistema operativo Windows. Docker usa el kernel de Linux proporcionado por WSL2, lo que mejora la velocidad de arranque de los contenedores y el rendimiento en general.

Docker Desktop para Windows ha incorporado soporte para WSL2, permitiendo que los contenedores Docker se ejecuten en el entorno WSL2. Esto elimina la necesidad de una VM de Linux completa (como lo requería Docker Toolbox o Docker con Hyper-V).
