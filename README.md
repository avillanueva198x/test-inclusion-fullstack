# POC - Problema Matemático Codeforces 1374A (Reto Técnico)

API RESTful desarrollada en Java 21 con Spring Boot 3, orientada a resolver el problema matemático de Codeforces 1374A. 
Implementa arquitectura hexagonal (Domain-Driven Design), principios SOLID, Clean Code, y buenas prácticas de seguridad, 
escalabilidad y mantenibilidad.

## Descripción del Problema

El problema consiste en encontrar el máximo entero **k** tal que **0 ≤ k ≤ n** y **k mod x = y**.

**Entrada:** Tres enteros x, y, n donde:
- 2 ≤ x ≤ 10^9
- 0 ≤ y < x  
- 1 ≤ n ≤ 10^9

**Salida:** El máximo valor k que cumple las condiciones, o -1 si no existe tal k.

**Algoritmo:** k = y + ((n-y)/x) * x, pero si y > n entonces k = -1.

## Tabla de contenidos

1. [Tecnologías utilizadas](#tecnologías-utilizadas)
3. [Instalación y ejecución](#instalación-y-ejecución)
4. [Accesos útiles](#accesos-útiles)
5. [Endpoint principal](#endpoint-principal)
6. [Validaciones del API](#Validaciones-del-API)
7. [Verificación de calidad y pruebas](#Verificación-de-calidad-y-pruebas)
8. [Principios y patrones aplicados](#principios-y-patrones-aplicados)

## Tecnologías utilizadas

- **Java 21** (Actualizado desde Java 17)
- **Spring Boot 3.2**
- **Gradle**
- **Spring Data JPA (usando Hibernate como implementación) + H2 Database (en memoria)**
- **JWT (JSON Web Token)** para autenticación (mantenido de implementación anterior)
- **Spring Security con JWT** (mínima implementación para generación de tokens)
- **Lombok**
- **Swagger / OpenAPI 3** para documentación de la API
- **JUnit 5 + Mockito** para pruebas unitarias
- **Spring Boot Actuator** para **observabilidad** y monitoreo
- **Logback** para configuración de logging
- **MDC (Mapped Diagnostic Context)** para identificar correlación de logs entre peticiones
- **AOP (Aspect-Oriented Programming)** para auditoría con la anotación `@Aspect`
- **Checkstyle** y **PMD** para análisis estático de código y aseguramiento de calidad
- **SonarQube** para integración continua y análisis de calidad de código
- **Logstash y ELK** (ElasticSearch, Logstash, Kibana) para almacenamiento y visualización de logs
- **Repositorio en GitHub**
- **Records de Java** para DTOs inmutables
- **Validaciones Jakarta** para validación de entrada

## Instalación y ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/avillanueva198x/ms-inclusion-app.git
cd ms-inclusion-app
```

2. Asegúrate de tener Java 21 instalado:

```bash
java -version
# Debería mostrar Java 21
```

3. Levanta la aplicación con Gradle utilizando el perfil deseado (por defecto se sugiere dev):

```bash
./gradlew bootRun -Dspring.profiles.active=dev --offline
```

## Accesos útiles

- **Endpoint de salud**: [http://localhost:9191/actuator/health](http://localhost:9191/actuator/health)
- **Swagger UI**: [http://localhost:9191/swagger-ui.html](http://localhost:9191/swagger-ui.html)
- **Console H2**: [http://localhost:9191/h2-console](http://localhost:9191/h2-console)
    - JDBC URL: `jdbc:h2:mem:testdb`
    - Usuario: `sa`
    - Contraseña: (vacío)

## Endpoint principal

### Problema Matemático (Nuevo)

- `Request (POST /api/v1/math/solve)`:

```json
{
  "x": 7,
  "y": 5,
  "n": 12345
}
```

- `Respuesta exitosa 200 OK`:

```json
{
  "result": 12339,
  "x": 7,
  "y": 5,
  "n": 12345
}
```

### Registro de Usuarios (Funcionalidad previa mantenida)

- `Request (POST /api/v1/users)`:

```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "Hunter2",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
```

- `Respuesta exitosa 201 Created`:

```json
{
  "id": "uuid",
  "created": "fecha",
  "modified": "fecha",
  "lastLogin": "fecha",
  "token": "jwt",
  "isActive": true,
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "phones": [...]
}
```

## Validaciones del API

### Problema Matemático

- **x** debe ser mayor o igual a 2 y menor o igual a 10^9
- **y** debe ser mayor o igual a 0 y menor que x
- **n** debe ser mayor o igual a 1 y menor o igual a 10^9
- Validación de que y < x, si no se cumple retorna error 400:

```json
{
  "mensaje": "y debe ser menor que x"
}
```

- Si y > n, el resultado será -1 (caso válido, no error)

### Registro de Usuarios (Funcionalidad previa)

- Email debe tener formato válido (@Email)
- Password validado con expresión regular configurable vía application.yml
- Mensaje de error si el email ya existe:

```json
{
  "mensaje": "El correo ya registrado"
}
```

### Formato de Errores

- Todos los errores siguen este formato:

```json
{
  "mensaje": "mensaje de error"
}
```

- Los tokens JWT son firmados en memoria (no expiran realmente ya que no hay autenticación implementada aún).

## Verificación de calidad y pruebas

`Cobertura de pruebas`

**Problema Matemático (Nuevo):**
- Controlador REST (`MathProblemControllerTest`)
- Handler (`MathProblemHandlerTest`)
- Casos de uso (`MathProblemUseCaseTest`)

**Registro de Usuarios (Funcionalidad previa):**
- Controlador REST (`UserControllerTest`)
- Handler (`CreateUserHandlerTest`)
- Casos de uso (`CreateUserUseCaseTest`)
- Adaptador de persistencia (`UserPersistenceAdapterTest`)


- Para verificar que todo el proyecto cumple con estándares de calidad y tiene pruebas completas, **puedes ejecutar**:

```bash
./gradlew check
```

- Si cuentas con SONAR, **puedes ejecutar** este comando (Recuerda actualizar tus datos del sonar en el build.gradle):

```bash
./gradlew check -Penv=local
```

o Solo

```bash
./gradlew sonar -Penv=local
```

- Cómo **ver los reportes**:

```bash
PMD: build/reports/pmd/main.html
Checkstyle: build/reports/checkstyle/main.html
Cobertura JaCoCo:  build/reports/jacoco/test/html/index.html
Pruebas unitarias: build/reports/tests/test/index.html
```

- **NOTA:** Puedes usar Postman o Swagger para probar. Se incluyen dos colecciones de Postman:
  - `Math Problem API.postman_collection.json` - Para probar el problema matemático
  - `User API.postman_collection.json` - Para probar el registro de usuarios

## Principios y patrones aplicados

Esta solución fue diseñada aplicando los **principios de arquitectura SETO** (Seguridad, Escalabilidad, Trazabilidad y
Observabilidad), integrando además buenas prácticas modernas de desarrollo en **Java 21** con Spring Boot 3.2, arquitectura
limpia y enfoque DDD. Incluye tanto la funcionalidad original de registro de usuarios como la nueva implementación del 
problema matemático de Codeforces 1374A.

- **Arquitectura Hexagonal / Clean Architecture**: separación clara entre dominio, casos de uso, infraestructura y
  adaptadores.
- **Principios SOLID** aplicados a clases, controladores, servicios y adaptadores.
- **Clean Code**: clases pequeñas, responsabilidades únicas, nomenclatura clara y consistente.
- **DTOs** para desacoplar la capa REST del modelo de dominio.
- **Bean Validation (Jakarta)** con anotaciones como `@Valid`, `@NotBlank`, `@Email` y validaciones personalizadas.
- **JWT como token de autenticación** (generado al registrar usuario; no hay endpoints protegidos todavía).
- **Manejo centralizado de errores** con `@RestControllerAdvice` y mensajes estructurados en
  JSON (`{"mensaje": "error"}`).
- **Observabilidad avanzada**:
    - `Spring Boot Actuator` habilitado (`/actuator/health`, `/metrics`, etc.).
    - Uso de **MDC (Mapped Diagnostic Context)** para incluir `X-Correlation-Id` en cada request y log.
    - Configuración de **Logback estructurado** para logs JSON (`logback-spring.xml` compatible con ELK/Logstash).
- **Auditoría con AOP (Aspect-Oriented Programming)**: aplicación transversal de logging o trazabilidad mediante
  aspectos (`@Aspect`).
- **Seguridad desacoplada y extensible** con configuración de `SecurityFilterChain` para pruebas y
  perfiles (`@TestConfiguration`).
- **Inyección de dependencias limpia** usando `@Component`, `@Service`, `@Configuration`.
- **Mappers manuales** (con posibilidad de reemplazo por MapStruct).
- **Pruebas unitarias y TDD** cubriendo:
    - Controlador REST (`UserControllerTest`)
    - Casos de uso (`CreateUserUseCaseTest`)
    - Adaptador de persistencia (`UserPersistenceAdapterTest`)
- **Patrones para escalabilidad y mantenibilidad**:
    - `Ports & Adapters`: permite cambiar implementación (ej. de H2 a PostgreSQL) sin afectar dominio.
    - `Domain-Driven Design`: desacopla lógica de negocio real del transporte y persistencia.
    - `Handlers` como punto de entrada para orquestar validaciones y lógica.
- **Análisis de calidad ejecutado exitosamente**:
    - `Checkstyle`: aprobado
    - `PMD`: sin errores ni warnings
    - `JaCoCo`: cobertura generada correctamente
    - Todos los tests pasan (`./gradlew check`) sin fallos ni advertencias
- **Análisis con Sonarqube**:
    - Cobertura del código **al 100%**.


