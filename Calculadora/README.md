# Microservicio Calculadora

Este proyecto es un microservicio que expone un API RESTful para realizar operaciones aritméticas. Actualmente, soporta sumas y restas de dos números. El microservicio utiliza Spring Boot y está desarrollado en Java 11.

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
│   │   │           ├───exception
│   │   │           ├───model
│   │   │           ├───service
│   │   │           └───trace
│   │   └───resources
│   │       ├───static
│   │       └───templates
│   └───test
│       ├───java
│       │   └───com
│       │       └───calculadora
│       └───resources

```



## Dependencias Utilizadas

El proyecto utiliza las siguientes dependencias principales:

- Spring Boot
- Spring Web (para el desarrollo de la API RESTful)
- OpenAPI (para la documentación del API)
- JUnit5 (para las pruebas unitarias)

## Funcionalidades Implementadas y Alcance del Servicio

El microservicio permite realizar sumas. Para realizar una suma, se debe hacer una solicitud POST a la URL `/calculadora/sumar`. Los operandos deben ser proporcionados en el cuerpo de la solicitud en formato JSON.

El microservicio permite realizar restas. Para realizar una suma, se debe hacer una solicitud POST a la URL `/calculadora/restar`. Los operandos deben ser proporcionados en el cuerpo de la solicitud en formato JSON.

El microservicio permite realizar sumas y restas de dos números. Para realizar una operación, se debe hacer una solicitud POST a la URL `/calculadora/operacion/{operador}`, donde `{operador}` puede ser "suma" para suma o "restar" para resta. Los operandos deben ser proporcionados en el cuerpo de la solicitud en formato JSON.



## Procedimientos de Compilación y Ejecución

Para compilar y ejecutar el microservicio, asegúrate de tener instalado Java 11 y Maven en tu sistema. Luego, sigue estos pasos:

1. Clona el repositorio: `git clone <url-repositorio>`
2. Ve al directorio del proyecto: `cd calculadora`
3. Compila el proyecto: `mvn clean package`
4. Ejecuta el microservicio: `java -jar target/calculadora-0.0.1-SNAPSHOT.jar`

El microservicio estará disponible en `http://localhost:8080`.

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