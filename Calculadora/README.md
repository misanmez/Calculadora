# Microservicio Calculadora

Este proyecto es un microservicio que expone un API RESTful para realizar operaciones aritméticas. Actualmente, soporta sumas y restas de dos números. El microservicio utiliza Spring Boot y está desarrollado en Java 17.

## Requisitos Técnicos

- Java 17
- Maven como manejador de dependencias.
- Git para el control de versiones.
- OpenAPI para la definición del API RESTful.
- JUnit5 para las pruebas unitarias.

## Estructura del Proyecto

El proyecto tiene la siguiente estructura de directorios:

``` bash
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───calculadora
│   │   │           ├───controller
│   │   │           ├───dto
│   │   │           ├───exception
│   │   │           ├───lib
│   │   │           ├───model
│   │   │           └───service
│   │   └───resources
│   │       ├───static
│   │       └───templates
│   └───test
│       ├───java
│       │   └───com
│       │       └───calculadora
│       │           ├───controller
│       │           ├───dto
│       │           ├───exceptions
│       │           └───service
│       └───resources

```



## Dependencias Utilizadas

El proyecto utiliza las siguientes dependencias principales:

- Spring Boot
- Spring Web (para el desarrollo de la API RESTful)
- OpenAPI (para la documentación del API)
- JUnit5 (para las pruebas unitarias)
- Mockito
- Lombok
- Librería Tracer

## Funcionalidades Implementadas y Alcance del Servicio

El microservicio permite realizar sumas y restas de dos números. Para realizar una operación, se debe hacer una solicitud POST a la URL `/calculadora/{operador}`, donde `{operador}` puede ser "sumar" para suma o "restar" para resta. Los operandos deben ser proporcionados en el cuerpo de la solicitud en formato JSON.



## Procedimientos de Compilación y Ejecución

Para compilar y ejecutar el microservicio, asegúrate de tener instalado Java 17 y Maven en tu sistema. Luego, sigue estos pasos:

1. Clona el repositorio: `git clone <url-repositorio>`
2. Ve al directorio del proyecto: `cd calculadora`
3. Compila el proyecto: `mvn clean package`
4. Ejecuta el microservicio: `java -jar target/calculadora-0.0.1-SNAPSHOT.jar`

El microservicio estará disponible en `http://localhost:8080`.

## Instalación del Tracer

1. Descarga el archivo jar del Tracer desde la ubicación apropiada.
2. Abrir una linea de comandos desde la ubicación del jar descargado.
2. Instalar la libreria en el repositorio local usando la siguiente instrucción: `mvn install:install-file -Dfile=tracer-1.0.0.jar -Djavadoc=tracer-1.0.0-javadoc.jar -Dsources=tracer-1.0.0-sources.jar`
3. Agregar el jar como dependencia en el proyecto incluyendo en el pom.xml las siguientes líneas: 

``` bash
<dependency>
    <groupId>com.example</groupId>
    <artifactId>tracer</artifactId>
    <version>1.0</version>
</dependency>

```

## Ejemplos de Llamadas al API

### Suma
- URL: `http://localhost:8080/calculadora/sumar`
- Método: POST
- Cuerpo de la Solicitud (JSON):
  ```json
  {
    "operando1": 10,
    "operando2": 5
  }

### Resta
- URL: `http://localhost:8080/calculadora/restar`
- Método: POST
- Cuerpo de la solicitud (JSON):
  ```json
  {
    "operando1": 10,
    "operando2" : 5
  }