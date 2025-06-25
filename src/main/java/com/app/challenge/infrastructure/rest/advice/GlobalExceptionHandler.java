package com.app.challenge.infrastructure.rest.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Manejador global de excepciones para la aplicación.
 * 
 * Esta clase implementa el patrón de manejo centralizado de errores usando
 * @RestControllerAdvice, lo que permite capturar y manejar todas las excepciones
 * que ocurran en los controllers de forma uniforme.
 * 
 * Beneficios de este enfoque:
 * - Consistencia en formato de respuestas de error
 * - Separación de responsabilidades (controllers no manejan errores)
 * - Logging centralizado de errores
 * - Fácil mantenimiento y evolución del manejo de errores
 * 
 * Todos los errores siguen el formato estándar: {"mensaje": "descripción"}
 * 
 * @author Adolfo Villanueva
 * @version 1.0
 * @since 2024-06-26
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja errores de tipo de contenido no soportado.
     * 
     * Se activa cuando el cliente envía una petición con Content-Type no soportado.
     * Por ejemplo, cuando se envía text/plain en lugar de application/json.
     * 
     * @param ex excepción de tipo de contenido no soportado
     * @return ResponseEntity con código 415 (Unsupported Media Type) y mensaje descriptivo
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        log.error("Error de tipo de contenido no soportado: {}", ex.getMessage());
        return ResponseEntity
            .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .body(new ErrorResponse("Tipo de contenido no soportado. Se esperaba 'application/json'"));
    }

    /**
     * Maneja errores de validación de Bean Validation (Jakarta).
     * 
     * Se activa cuando las validaciones de @Valid fallan en los DTOs.
     * Extrae todos los mensajes de error de validación y los combina en un mensaje único.
     * 
     * Ejemplos de validaciones que activan este handler:
     * - @NotNull, @Min, @Max en MathProblemRequest
     * - Valores fuera de rango (ej. x < 2, n > 10^9)
     * 
     * @param ex excepción de validación de argumentos
     * @return ResponseEntity con código 400 (Bad Request) y mensajes de validación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        // Extraer todos los mensajes de error de validación
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .toList();

        String mensaje = String.join(" - ", errors);
        log.warn("Errores de validación: {}", mensaje);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(mensaje));
    }

    /**
     * Maneja errores de lógica de negocio (IllegalArgumentException).
     * 
     * Se activa cuando las validaciones de negocio fallan, como por ejemplo:
     * - Cuando y >= x en el problema matemático
     * - Otras reglas de negocio específicas del dominio
     * 
     * @param ex excepción de argumento ilegal
     * @return ResponseEntity con código 400 (Bad Request) y mensaje de la excepción
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Error de validación de negocio: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(ex.getMessage()));
    }

    /**
     * Maneja cualquier otra excepción no controlada específicamente.
     * 
     * Este es el manejador de último recurso que captura cualquier excepción
     * no prevista en los otros handlers. Evita que se expongan detalles
     * internos de la aplicación al cliente.
     * 
     * @param ex excepción genérica no controlada
     * @return ResponseEntity con código 500 (Internal Server Error) y mensaje genérico
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Error interno no controlado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("Error interno del servidor"));
    }

    /**
     * DTO inmutable para respuestas de error.
     * 
     * Utiliza un Record de Java para crear un DTO inmutable y eficiente.
     * Mantiene consistencia en el formato de todas las respuestas de error.
     * 
     * @param mensaje descripción del error en formato amigable para el usuario
     */
    public record ErrorResponse(String mensaje) {
    }
}
