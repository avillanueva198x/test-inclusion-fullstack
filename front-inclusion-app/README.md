# Frontend - Math Problem Solver

Frontend desarrollado en **Angular 19** para consumir la API del problema matemático de Codeforces 1374A.

## 🚀 Características

- **Framework**: Angular 19
- **Diseño**: Interfaz Simple
- **Validaciones**: La validación todo lo hace el backend
- **Estados**: Manejo de loading, errores y resultados
- **Responsive**: Adaptado para móviles y desktop

## 📋 Prerrequisitos

- Node.js (versión 18.19.1, 20.11.1 o superior)
- npm (versión 8.0.0 o superior)
- Angular CLI 19

## 🛠️ Instalación

1. **Instalar dependencias:**
   ```bash
   npm install
   ```

2. **Instalar Angular CLI globalmente (si no está instalado):**
   ```bash
   npm install -g @angular/cli@19
   ```

## 🏃‍♂️ Ejecución

1. **Iniciar el servidor de desarrollo:**
   ```bash
   ng serve
   ```

2. **Abrir el navegador:**
   ```
   http://localhost:4200
   ```

## 🔧 Configuración

### Configuración del Backend

Por defecto, la aplicación se conecta al backend en `http://localhost:9191`. Si tu backend está en una URL diferente, modifica el archivo:

```typescript
// src/app/services/math-problem.service.ts
private readonly baseUrl = 'http://localhost:9191'; // Cambia esta URL
```

### Variables de Entorno

Para diferentes entornos, puedes crear archivos de configuración:

```typescript
// src/environments/environment.ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:9191'
};
```

## 📱 Uso de la Aplicación

### Problema Matemático - Codeforces 1374A

La aplicación resuelve el siguiente problema:
- Dado x, y, n, encuentra el máximo k tal que: **0 ≤ k ≤ n** y **k mod x = y**

### Ejemplo de uso:

```
Entrada:
x = 7
y = 5
n = 12345

Resultado:
k = 12339

Verificación: 12339 mod 7 = 5 ✓
```

## 🧪 Pruebas de Ejemplo

Puedes probar con estos casos:

1. **Caso básico:**
   - x: 7, y: 5, n: 12345
   - Resultado esperado: 12339

2. **Caso límite:**
   - x: 2, y: 1, n: 1000000000
   - Resultado esperado: 999999999

3. **Caso sin solución:**
   - x: 10, y: 5, n: 4
   - Resultado esperado: -1

## 🔍 Funcionalidades Técnicas

### Validaciones

- **Frontend**: Validación en tiempo real con Angular Forms
- **Backend**: Validación de rangos y tipos de datos

### Estados de la Aplicación

- **Loading**: Spinner mientras se procesa la solicitud
- **Success**: Resultado exitoso con verificación
- **Error**: Manejo de errores de red y validación

## 🚀 Comandos Útiles

```bash
# Desarrollo
ng serve                    # Servidor de desarrollo
ng build                    # Build para producción
ng test                     # Ejecutar tests
ng lint                     # Linter

# Instalación
npm install                 # Instalar dependencias
npm update                  # Actualizar dependencias
```

## 🌐 Despliegue

### Build para Producción

```bash
ng build --configuration production
```

Los archivos se generarán en `dist/front-inclusion-app/`.

### Servir archivos estáticos

```bash
# Con serve (npm install -g serve)
serve -s dist/front-inclusion-app

# Con http-server
http-server dist/front-inclusion-app
```

## 🔗 Enlaces Útiles

- [Problema Original - Codeforces 1374A](https://codeforces.com/problemset/problem/1374/A)
- [Angular Documentation](https://angular.dev)
- [Angular CLI](https://angular.dev/tools/cli)

## 📞 Soporte

Si encuentras algún problema:

1. Verifica que el backend esté ejecutándose en el puerto correcto
2. Revisa la consola del navegador para errores
3. Asegúrate de que las dependencias estén instaladas correctamente

---

**Desarrollado por:** Adolfo Villanueva  
**Fecha:** Junio 2025  
**Versión:** 1.0.0
