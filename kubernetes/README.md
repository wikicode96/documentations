![logo](./img/Kubernetes_logo.svg)

## Indice
1. [Fundamentos de Kubernetes](#fundamentos-de-kubernetes)
    1. [Conceptos clave](#conceptos-clave)
    2. [Arquitectura de Kubernetes](#arquitectura-de-kubernetes)
2. [Instalación y Configuración](#instalación-y-configuración)
    1. [Minikube vs. Kind vs. K3s para entornos de desarrollo](#minikube-vs-kind-vs-k3s-para-entornos-de-desarrollo)

## Fundamentos de Kubernetes
### Conceptos clave
* **Pods:** Es la unidad mínima de ejecución en Kubernetes. Agrupan uno o varios contenedores que comparten almacenamiento, red y especificaciones sobre cómo ejecutar los contenedores. Son efímeros, lo que significa que su ciclo de vida es corto y se gestionan a través de objetos de nivel superior como Deployments.
* **Deployments:** Es un objeto de alto nivel que administra el despliegue y la actualización de aplicaciones. Permite definir el estado deseado para los Pods y se encarga de crear o actualizar los recursos necesarios (como ReplicaSets). Facilita actualizaciones continuas y rollbacks en caso de fallos, asegurando la disponibilidad de la aplicación.
* **Services:** Es una abstracción que define un conjunto lógico de Pods y una política de acceso para ellos. Proporciona un único punto de acceso (a menudo mediante un balanceador de carga) para comunicarse con los Pods, sin tener que preocuparse por las direcciones IP cambiantes de los mismos. Permite la exposición interna o externa de las aplicaciones.
* **Namespaces:** Son espacios virtuales dentro de un clúster que permiten la segmentación y aislamiento de recursos. Facilitan la organización de recursos, especialmente en entornos con múltiples equipos o proyectos. Ayudan a gestionar permisos y cuotas de recursos de forma aislada.
* **ConfigMaps:** Son objetos que permiten almacenar datos de configuración no confidenciales en forma de pares clave-valor. Se pueden inyectar en los contenedores como variables de entorno, argumentos de comandos o archivos de configuración. Permiten desacoplar la configuración del código de la aplicación, facilitando la administración y actualización de parámetros sin necesidad de reconstruir la imagen del contenedor.
* **Secrets:** Son objetos similares a los ConfigMaps, pero se utilizan para almacenar información sensible y confidencial (como contraseñas, tokens o claves). Proporcionan un mecanismo seguro para inyectar datos sensibles en los contenedores. Los datos almacenados en un Secret están codificados (por ejemplo, en base64) y se gestionan de manera que se minimicen los riesgos de exposición.

### Arquitectura de Kubernetes
1. **Master Node (Nodo Maestro):** Es el nodo responsable de la administración y control del clúster. Sus principales componentes son:
    1. **API Server:** Es el punto de entrada a Kubernetes. Gestiona todas las solicitudes REST y actúa como interfaz para el clúster. Valida y procesa comandos de **kubectl** o herramientas externas.
    2. **Controller Manager:** Supervisa el estado del clúster y actúa para garantizar que coincida con el estado deseado. Controla operaciones como la creación de Pods y la reparación de fallos.
    3. **Scheduler:** Asigna los Pods a los Worker Nodes según la disponibilidad de recursos. Considera factores como uso de CPU, memoria y afinidad de nodos.
    4. **etcd (Almacén de datos):** Base de datos distribuida que almacena el estado del clúster. Registra información de configuración, redes y estado de los Pods.

2. **Worker Nodes (Nodos de Trabajo):** Son los nodos donde se ejecutan las aplicaciones dentro de los Pods. Cada nodo de trabajo contiene los siguientes componentes:
    1. **Kubelet:** Agente que se ejecuta en cada nodo y se comunica con el API Server. Garantiza que los contenedores dentro de los Pods estén en ejecución y saludables.
    2. **Kube-Proxy:** Gestiona las reglas de red para permitir la comunicación entre los Pods y Services. Controla el tráfico dentro y fuera del clúster.
    3. **Container Runtime:** Se encarga de ejecutar los contenedores (Docker, containerd, CRI-O, etc.). Interactúa con Kubelet para iniciar, detener y gestionar contenedores.

3. **Comunicación entre Master y Worker Nodes**
    1. El **Master Node** asigna cargas de trabajo a los **Worker Nodes**.
    2. Los **Worker Nodes** informan constantemente su estado al **Master Node**.
    3. La API Server maneja las interacciones entre los componentes del clúster.

![Arquitectura de Kubernetes](./img/kubernetes-node-kubernetes-master.jpg)

## Instalación y Configuración
### Minikube vs. Kind vs. K3s para entornos de desarrollo
En entornos de desarrollo y pruebas locales, existen diversas opciones para ejecutar Kubernetes de manera ligera y eficiente. Las tres opciones más comunes son **Minikube**, **Kind** y **K3s**, cada una con sus ventajas y particularidades.

#### Minikube
Es una herramienta que permite ejecutar un clúster Kubernetes local en una sola máquina.
* **Características:** Fácil instalación y uso en sistemas operativos Windows, macOS y Linux. Soporta múltiples drivers (VirtualBox, Docker, KVM, etc.). Ideal para aprender y desarrollar en un entorno local sencillo.
* **Ventajas:** Configuración simple y documentación amplia. Permite probar características de Kubernetes sin la complejidad de un clúster completo.

#### Kind
Ejecuta clústeres de Kubernetes usando contenedores Docker como nodos.
* **Características:** Ligero y rápido para crear y destruir clústeres. Es muy útil para pruebas automatizadas e integración continua (CI). Facilita la simulación de clústeres multi-nodo sin requerir máquinas virtuales.
* **Ventajas:** Integración nativa con Docker, lo que lo hace ideal en entornos de desarrollo modernos. Permite la configuración avanzada de nodos para pruebas específicas.

#### K3s
Es una distribución ligera de Kubernetes, optimizada para entornos edge, IoT y desarrollo.
* **Características:** Consumo reducido de recursos y un binario único que simplifica la instalación. Compatible con la mayoría de herramientas y APIs de Kubernetes. Ideal para entornos con recursos limitados y despliegues rápidos.
* **Ventajas:** Fácil de instalar y mantener. Buen rendimiento en hardware modesto o escenarios donde se requiere un clúster ligero.

### Configuración básica y avanzada del *kubeconfig*
#### Configuración Básica del kubeconfig:
Es el archivo de configuración que almacena la información necesaria para que **kubectl** se conecte y autentique en un clúster Kubernetes. Contiene detalles como clusters, usuarios y contextos.

#### Configuración Avanzada del kubeconfig:

### Uso de kubectl y Lens para administración

## Gestión de Aplicaciones en Kubernetes

## Orquestación y Escalabilidad

## Seguridad en Kubernetes

## Observabilidad y Monitoreo

## CI/CD en Kubernetes

## Helm y Operators

## Kubernetes en la Nube

## Kubernetes Avanzado