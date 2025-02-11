# mark
M.A.R.K. - Management of Accounts, Resources, and Key-data  

## 📄 Documentación
Para más detalles, consulta la [Documentación](./doc/README.md).

## 🚀 Tecnologías Utilizadas

### ⚙️ **Frameworks y Librerías de Backend**
- **[Spring Boot 3.2.2](https://spring.io/projects/spring-boot):** Framework para construir aplicaciones Spring independientes y listas para producción.
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa):** Simplifica la implementación de capas de acceso a datos utilizando JPA (Java Persistence API).
- **[Spring Data REST](https://spring.io/projects/spring-data-rest):** Expone repositorios JPA como APIs RESTful de forma sencilla.
- **[Spring Web](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web):** Permite construir aplicaciones web RESTful y microservicios.

### 📊 **Base de Datos y Persistencia**
- **[H2 Database 2.3.232](https://www.h2database.com/):** Base de datos ligera en memoria ideal para entornos de desarrollo y pruebas.
- **[Jakarta Validation API 3.0.2](https://jakarta.ee/specifications/bean-validation/3.0/):** Proporciona una forma estandarizada de validar beans en Java.

### 📦 **Documentación de API**
- **[SpringDoc OpenAPI (Swagger UI) 2.3.0](https://springdoc.org/):** Automatiza la generación de documentación OpenAPI para servicios RESTful.

### 🛠️ **Herramientas de Desarrollo**
- **[Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools):** Mejora la experiencia de desarrollo con funcionalidades como recarga en caliente y reinicios automáticos.
- **[Lombok](https://projectlombok.org/):** Reduce el código repetitivo en Java mediante el uso de anotaciones.

### 🔄 **Mapeo de Objetos**
- **[ModelMapper 3.1.1](http://modelmapper.org/):** Librería que facilita el mapeo de objetos entre DTOs y entidades.

### 🧪 **Frameworks de Pruebas**
- **[Spring Boot Starter Test](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing):** Proporciona soporte integral para pruebas en aplicaciones Spring Boot.
- **[Spring REST Docs (MockMVC)](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/):** Genera documentación para servicios RESTful a partir de pruebas.

### ⚙️ **Gestión de Construcción y Dependencias**
- **[Maven](https://maven.apache.org/):** Herramienta de automatización de compilación y gestión de dependencias para proyectos Java.
- **[Maven Compiler Plugin](https://maven.apache.org/plugins/maven-compiler-plugin/):** Configura el compilador de Java para utilizar la versión 17.

### 🗂️ **Gestión de Configuración**
- **[YAML Configuration](https://yaml.org/):** Estándar de serialización de datos legible para humanos, utilizado para la configuración de la aplicación en `application.yaml`.

---

## 🚀 Cómo Iniciar el Proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio.git
   cd tu-repositorio
   ```

2. Construye el proyecto usando Maven:

  ```bash
    mvn clean install
  ```

3. Ejecuta la aplicación:
    
   ```bash
    mvn spring-boot:run
    ```

4. Accede a la API en:

   ```bash
    http://localhost:8080
    ```

5. Documentación de la API disponible en:

   ```bash
    http://localhost:8080/swagger-ui/index.html

    ```


