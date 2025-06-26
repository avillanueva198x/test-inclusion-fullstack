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
2. [Instalación y ejecución](#instalación-y-ejecución)
3. [Accesos útiles](#accesos-útiles)
4. [Endpoint principal](#endpoint-principal)
5. [Validaciones del API](#validaciones-del-api)
6. [Verificación de calidad y pruebas](#verificación-de-calidad-y-pruebas)
7. [Principios y patrones aplicados](#principios-y-patrones-aplicados)

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3.2**
- **Gradle**
- **Spring Data JPA (usando Hibernate como implementación) + H2 Database (en memoria)**
- **Spring Security** (configuración mínima para desarrollo)
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

### Prerrequisitos

- Java 21 o superior
- Gradle (opcional, se puede usar el wrapper incluido)

### Pasos de instalación

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

3. Ejecutar la aplicación:

```bash
# En Windows
.\gradlew.bat bootRun -Dspring.profiles.active=dev

# En Linux/Mac
./gradlew bootRun -Dspring.profiles.active=dev
```

La aplicación se ejecutará en el puerto `9191`.

## Accesos útiles

- **Aplicación**: [http://localhost:9191](http://localhost:9191)
- **Endpoint de salud**: [http://localhost:9191/actuator/health](http://localhost:9191/actuator/health)
- **Swagger UI**: [http://localhost:9191/swagger-ui.html](http://localhost:9191/swagger-ui.html)
- **Métricas**: [http://localhost:9191/actuator/metrics](http://localhost:9191/actuator/metrics)

## Endpoint principal

### Problema Matemático

**POST** `/api/v1/math/solve`

- **Request:**

```json
{
  "x": 7,
  "y": 5,
  "n": 12345
}
```

- **Respuesta exitosa (200 OK):**

```json
{
  "result": 12339,
  "x": 7,
  "y": 5,
  "n": 12345
}
```

- **Respuesta de error (400 Bad Request):**

```json
{
  "mensaje": "y debe ser menor que x"
}
```

### Ejemplos del problema de Codeforces

1. **Input:** x=7, y=5, n=12345 → **Output:** 12339
2. **Input:** x=5, y=0, n=4 → **Output:** 0  
3. **Input:** x=10, y=5, n=15 → **Output:** 15
4. **Input:** x=17, y=8, n=54321 → **Output:** 54306
5. **Input:** x=499999993, y=9, n=1000000000 → **Output:** 999999995
6. **Input:** x=10, y=5, n=187 → **Output:** 185
7. **Input:** x=2, y=0, n=999999999 → **Output:** 999999998

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

### Formato de Errores

Todos los errores siguen este formato estandarizado:

```json
{
  "mensaje": "descripción del error"
}
```

## Verificación de calidad y pruebas

### Ejecutar pruebas

```bash
# Ejecutar todas las pruebas
./gradlew test

# Ejecutar pruebas con reporte de cobertura
./gradlew test jacocoTestReport
```

### Cobertura de pruebas

**Problema Matemático:**
- Controlador REST (`MathProblemControllerTest`)
- Handler (`MathProblemHandlerTest`)  
- Casos de uso (`MathProblemUseCaseTest`)

### Análisis de calidad

Para verificar que todo el proyecto cumple con estándares de calidad:

```bash
./gradlew check
```

### Integración con SonarQube

```bash
# Configurar datos de SonarQube en build.gradle y ejecutar:
./gradlew sonar -Penv=local
```

### Ver reportes generados

- **PMD:** `build/reports/pmd/main.html`
- **Checkstyle:** `build/reports/checkstyle/main.html`
- **Cobertura JaCoCo:** `build/reports/jacoco/test/html/index.html`
- **Pruebas unitarias:** `build/reports/tests/test/index.html`

### Herramientas de prueba

- **Postman**: Se incluye la colección `Math Problem API.postman_collection.json`
- **Swagger UI**: Disponible en [http://localhost:9191/swagger-ui.html](http://localhost:9191/swagger-ui.html)

## Principios y patrones aplicados

Esta solución fue diseñada aplicando los **principios de arquitectura SETO** (Seguridad, Escalabilidad, Trazabilidad y
Observabilidad), integrando además buenas prácticas modernas de desarrollo en **Java 21** con Spring Boot 3.2, arquitectura
limpia y enfoque DDD para resolver el problema matemático de Codeforces 1374A.

### Arquitectura y Diseño

- **Arquitectura Hexagonal / Clean Architecture**: separación clara entre dominio, casos de uso, infraestructura y adaptadores
- **Principios SOLID** aplicados a clases, controladores, servicios y adaptadores
- **Clean Code**: clases pequeñas, responsabilidades únicas, nomenclatura clara y consistente
- **DTOs** para desacoplar la capa REST del modelo de dominio
- **Domain-Driven Design**: desacopla lógica de negocio del transporte y persistencia

### Validaciones y Manejo de Errores

- **Bean Validation (Jakarta)** con anotaciones como `@Valid`, `@NotNull`, `@Min`, `@Max`
- **Manejo centralizado de errores** con `@RestControllerAdvice` y mensajes estructurados
- **Validaciones de negocio** específicas del problema matemático

### Observabilidad y Monitoreo

- **Spring Boot Actuator** habilitado (`/actuator/health`, `/metrics`, etc.)
- **MDC (Mapped Diagnostic Context)** para incluir `X-Correlation-Id` en cada request y log
- **Configuración de Logback estructurado** para logs JSON (compatible con ELK/Logstash)

### Seguridad y Configuración

- **Seguridad desacoplada y extensible** con configuración de `SecurityFilterChain`
- **Configuración por perfiles** (dev, test, prod, qa)
- **CORS configurado** para desarrollo

### Pruebas y Calidad

- **Pruebas unitarias y TDD** con JUnit 5 + Mockito
- **Análisis de calidad** con Checkstyle, PMD, y JaCoCo
- **Integración con SonarQube** para análisis continuo
- **Cobertura de código del 100%**

### Escalabilidad y Mantenibilidad

- **Ports & Adapters**: permite cambiar implementación sin afectar dominio
- **Handlers** como punto de entrada para orquestar validaciones y lógica
- **Inyección de dependencias limpia** usando anotaciones de Spring
- **Records de Java** para DTOs inmutables y eficientes


