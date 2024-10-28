# Proyecto de Spring Boot

Este proyecto es una aplicación básica de Spring Boot que se ejecuta en el puerto 8888 utilizando Gradle como sistema de construcción y Java 23.

## Prerrequisitos

Antes de comenzar, asegúrate de tener lo siguiente instalado en tu máquina:

- [Java 23](https://www.oracle.com/java/technologies/javase-jdk23-downloads.html)
- [Gradle](https://gradle.org/install/)
- Un editor de código (recomendado: [IntelliJ IDEA](https://www.jetbrains.com/idea/))

## Instalación

1. **Clona el repositorio**:

    ```sh
    git https://github.com/juandiegou/maquinandoBackend.git
    cd tu-repositorio
    ```

2. **Construir el proyecto**:

    Ejecuta el siguiente comando para construir el proyecto con Gradle:

    ```sh
    ./gradlew build
    ```

## Ejecución

Para ejecutar la aplicación en el puerto 8888, usa el siguiente comando:

```sh
./gradlew bootRun --args='--server.port=8888'
