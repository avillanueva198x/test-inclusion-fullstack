# Test Inclusion Fullstack - Math Problem Solver

Aplicación fullstack para resolver el problema matemático de **Codeforces 1374A**.

## Aplicación Desplegada

**Frontend (Angular 19)** – desplegado en AWS S3:  
http://front-inclusion-app.s3-website-us-east-1.amazonaws.com/browser/

**Backend API (Spring Boot Java 21)** – desplegado en AWS EC2:  
http://ec2-54-163-44-148.compute-1.amazonaws.com:9191/swagger-ui/index.html

## Instrucciones

- En el frontend, puedes ingresar los valores de `x`, `y`, y `n`, y ver el resultado en tiempo real.
- El backend expone un endpoint `/resolver` que recibe los tres parámetros y retorna la solución.
- La documentación Swagger está habilitada para probar los endpoints directamente desde el navegador.

## Tecnologías utilizadas

- **Backend**: Java 21, Spring Boot 3, Maven, Swagger
- **Frontend**: Angular 19, TypeScript
- **Infraestructura**: AWS EC2 (backend), AWS S3 (frontend)

## Tests y validaciones

- La solución incluye tests unitarios para la lógica del problema.
- Se validan los parámetros de entrada (tipos y valores).
- Se implementó manejo de errores y mensajes claros de respuesta.
- El código está debidamente comentado y estructurado.

---

**Fecha**: Junio 2025  
**Versión**: 1.0.0  
**Autor**: Adolfo Villanueva  
**Prueba Técnica**: Inclusion Cloud