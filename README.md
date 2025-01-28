# Proyecto Backend con Spring Boot

## Resumen

API REST para la gestión de préstamos de libros, desarrollada con Spring Boot. Incluye funcionalidades de CRUD y está configurada para trabajar con una base de datos relacional.

## Requisitos Previos

- **Java 17**.
- **Maven 3.x** o superior.
- **Base de Datos**: Oracle, H2 para local.
- **IDE**: IntelliJ IDEA, Eclipse, u otro editor de tu preferencia.

## Instalación y Configuración

### Clonar el Repositorio

```bash
git clone https://github.com/Steven77dev/sistema-biblioteca-backend
cd sistema-biblioteca-backend
```

### Configurar el Archivo application.properties
   Edita el archivo src/main/resources/application.properties para agregar la configuración de la base de datos:
```bash
# Configuración del puerto
spring.application.name=biblioteca-backend
server.port=9098

# Configuración de la base de datos
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuración adicional
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
    

### Construir y Ejecutar el Proyecto
   Usa Maven para construir el proyecto y ejecutar el servidor:

```bash
# Instalar y compilar el proyecto
mvn clean install
# Ejecutar la aplicación
mvn spring-boot:run
```
El backend estará disponible en: http://localhost:9098.

### Pruebas con Postman
En el repositorio también se encuentra la colección de los endpoints que podrán ser exportados por Postman para las pruebas, el archivo es **Growby Prueba.postman_collection.json**
