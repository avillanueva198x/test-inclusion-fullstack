# Test Inclusion Fullstack - Math Problem Solver

Aplicación fullstack para resolver el problema matemático de **Codeforces 1374A**, desarrollada con **Spring Boot** (backend) y **Angular 19** (frontend).

## 🧮 Problema Matemático

**Codeforces 1374A**: Dado x, y, n, encuentra el máximo k tal que:
- `0 ≤ k ≤ n`
- `k mod x = y`

[Ver problema original](https://codeforces.com/problemset/problem/1374/A)

## 🏗️ Arquitectura

```
test-inclusion-fullstack/
├── ms-inclusion-app/          # Backend Spring Boot
│   ├── src/main/java/        # Código fuente Java
│   ├── src/test/java/        # Tests unitarios
│   └── additional/           # Documentación y Postman
├── front-inclusion-app/       # Frontend Angular 19
│   ├── src/app/              # Código fuente Angular
│   └── public/               # Archivos públicos
└── README.md                 # Este archivo
```

## 🚀 Tecnologías Utilizadas

### Backend
- **Java 21**
- **Spring Boot** (última versión)
- **Maven/Gradle** para gestión de dependencias
- **Swagger/OpenAPI** para documentación de API
- **JUnit** para testing

### Frontend
- **Angular 19**
- **TypeScript**
- **HTML5 & CSS3**
- **Bootstrap/CSS Grid** para diseño responsivo

## 🛠️ Instalación y Ejecución

### Prerrequisitos

- **Java 21** o superior
- **Node.js** (versión 18.19.1, 20.11.1 o superior)
- **npm** (versión 8.0.0 o superior)
- **Angular CLI 19**

### 1. Backend (Spring Boot)

```bash
# Navegar al directorio del backend
cd ms-inclusion-app

# Ejecutar la aplicación
./gradlew bootRun
# o si usas Windows:
gradlew.bat bootRun

# El backend estará disponible en: http://localhost:9191
```

#### API Endpoints

- **POST** `/api/v1/math/solve` - Resuelve el problema matemático
- **GET** `/swagger-ui.html` - Documentación Swagger
- **GET** `/api-docs` - Documentación OpenAPI JSON
- **GET** `/actuator/health` - Health check

### 2. Frontend (Angular 19)

```bash
# Navegar al directorio del frontend
cd front-inclusion-app

# Instalar dependencias
npm install

# Ejecutar la aplicación
ng serve

# El frontend estará disponible en: http://localhost:4200
```

## 📝 Uso de la Aplicación

### Interfaz Web

1. Abre tu navegador en `http://localhost:4200`
2. Ingresa los parámetros:
   - **x**: Base del módulo (2 ≤ x ≤ 10^9)
   - **y**: Residuo deseado (0 ≤ y < x)
   - **n**: Límite superior (1 ≤ n ≤ 10^9)
3. Presiona "Resolver Problema"
4. Ve el resultado con verificación matemática

### API REST (Postman/cURL)

```bash
curl -X POST http://localhost:9191/api/v1/math/solve \
  -H "Content-Type: application/json" \
  -d '{
    "x": 7,
    "y": 5,
    "n": 12345
  }'
```

**Respuesta:**
```json
{
  "result": 12339,
  "x": 7,
  "y": 5,
  "n": 12345
}
```

## 🧪 Casos de Prueba

### Caso 1: Básico
- **Entrada**: x=7, y=5, n=12345
- **Salida esperada**: 12339
- **Verificación**: 12339 mod 7 = 5 ✓

### Caso 2: Límite superior
- **Entrada**: x=2, y=1, n=1000000000
- **Salida esperada**: 999999999
- **Verificación**: 999999999 mod 2 = 1 ✓

### Caso 3: Sin solución
- **Entrada**: x=10, y=5, n=4
- **Salida esperada**: -1
- **Verificación**: No existe k ≤ 4 tal que k mod 10 = 5

## 🔍 Testing

### Backend Tests

```bash
cd ms-inclusion-app

# Ejecutar todos los tests
./gradlew test

# Ver reporte de cobertura
./gradlew jacocoTestReport
```

### Frontend Tests

```bash
cd front-inclusion-app

# Ejecutar tests unitarios
ng test

# Ejecutar tests e2e
ng e2e
```

## 📚 Documentación

### API Documentation

Una vez que el backend esté ejecutándose, puedes acceder a:

- **Swagger UI**: http://localhost:9191/swagger-ui.html
- **OpenAPI JSON**: http://localhost:9191/api-docs

### Postman Collection

En `ms-inclusion-app/additional/postman/` encontrarás la colección de Postman con ejemplos de uso.

## 🌐 Despliegue

### Preparación para Producción

#### Backend
```bash
cd ms-inclusion-app
./gradlew bootJar
# El JAR estará en build/libs/
```

#### Frontend
```bash
cd front-inclusion-app
ng build --configuration production
# Los archivos estarán en dist/front-inclusion-app/
```

### Docker (Opcional)

```bash
# Backend
cd ms-inclusion-app
docker build -t math-problem-backend .

# Frontend
cd front-inclusion-app
docker build -t math-problem-frontend .
```

## 🔧 Configuración

### CORS Configuration

El backend está configurado para permitir requests desde `http://localhost:4200`. Para cambiar esto, modifica:

```java
// ms-inclusion-app/src/main/java/.../config/CorsConfig.java
```

### URL del Backend

Si despliegas el backend en una URL diferente, actualiza:

```typescript
// front-inclusion-app/src/app/services/math-problem.service.ts
private readonly baseUrl = 'http://localhost:9191'; // Cambiar aquí
```

## 📋 Checklist de Evaluación

- ✅ **Problema resuelto correctamente** (Codeforces 1374A)
- ✅ **API REST implementada** con Spring Boot
- ✅ **Tests unitarios** incluidos
- ✅ **Frontend moderno** con Angular 19
- ✅ **Documentación completa** con Swagger
- ✅ **Validación de parámetros** en backend y frontend
- ✅ **Manejo de errores** adecuado
- ✅ **Código comentado** y bien estructurado
- ✅ **README detallado** con instrucciones
- ✅ **Diseño responsivo** para móviles

## 🚨 Troubleshooting

### Problemas Comunes

1. **Error de CORS**: Verifica que el backend permita requests desde el frontend
2. **Puerto ocupado**: Cambia los puertos en las configuraciones si es necesario
3. **Dependencias**: Asegúrate de tener las versiones correctas de Java y Node.js

### Logs

- **Backend**: Los logs aparecen en la consola donde ejecutas `bootRun`
- **Frontend**: Los errores aparecen en la consola del navegador (F12)

## 📞 Contacto

**Desarrollador**: Adolfo Villanueva  
**Email**: [tu-email@ejemplo.com]  
**LinkedIn**: [tu-perfil-linkedin]  

## 📄 Licencia

Este proyecto fue desarrollado como parte de una prueba técnica para Inclusion Cloud.

---

**Fecha**: Diciembre 2024  
**Versión**: 1.0.0  
**Prueba Técnica**: Inclusion Cloud