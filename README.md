# Tipo de Cambio API - Backend

Este proyecto es una API desarrollada en Java con Spring Boot que permite consultar y almacenar el tipo de cambio proporcionado por el Banco de Guatemala a través de un servicio SOAP. La API expone dos endpoints principales para obtener el tipo de cambio actual y el último registro almacenado en la base de datos.

## Requisitos

- Java 17 o superior
- Maven 2.0.7+
- MySQL
- Internet para conectarse al servicio SOAP del Banco de Guatemala

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/usuario/tipocambio-api.git
   cd tipocambio-api
   
## Configuración para la Base de Datos (MySQL)
La configuración principal se encuentra en el archivo src/main/resources/application.properties. Asegúrate de actualizar los siguientes parámetros:

Copiar código
# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/tipocambio
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# Configuración del servicio SOAP
banguat.endpoint=https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx

## Uso
Una vez que la aplicación esté en funcionamiento, podrás acceder a los endpoints de la API.

Estructura del Proyecto
controller: Contiene los controladores REST que gestionan las solicitudes HTTP.
service: Contiene la lógica de negocio, como la conexión con el servicio SOAP y la manipulación de datos.
repository: Incluye los repositorios de datos que se comunican con la base de datos.
model: Define las entidades y modelos de datos utilizados en la aplicación.
config: Configuración del servicio SOAP y de otros componentes.

## Tecnologías Utilizadas
Java 17
Spring Boot - Framework principal de la aplicación.
Spring Web Services - Para consumir el servicio SOAP.
MySQL - Base de datos para almacenar los registros.
Maven - Gestión de dependencias y construcción del proyecto.

## Licencia

Este `README.md` proporciona una guía completa para cualquier usuario o desarrollador que quiera ejecutar o contribuir al proyecto.
