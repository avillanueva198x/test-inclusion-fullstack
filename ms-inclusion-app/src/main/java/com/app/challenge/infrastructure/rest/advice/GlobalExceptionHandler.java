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
 * @author Adolfo Villanueva
 * @since 2024-06-26
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja errores de tipo de contenido no soportado.
     *
     * @param ex excepción de tipo de contenido no soportado
     * @return ResponseEntity con código 415 y mensaje de error
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        log.error("Error de tipo de contenido no soportado: {}", ex.getMessage());
        return ResponseEntity
            .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .body(new ErrorResponse("Tipo de contenido no soportado. Se esperaba 'application/json'"));
    }

    /**
     * Maneja errores de validación de Bean Validation.
     *
     * @param ex excepción de validación de argumentos
     * @return ResponseEntity con código 400 y mensajes de validación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
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
     * Maneja errores de lógica de negocio.
     *
     * @param ex excepción de argumento ilegal
     * @return ResponseEntity con código 400 y mensaje de la excepción
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Error de validación de negocio: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(ex.getMessage()));
    }

    /**
     * Maneja cualquier otra excepción no controlada.
     *
     * @param ex excepción genérica
     * @return ResponseEntity con código 500 y mensaje genérico
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Error interno no controlado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("Error interno del servidor"));
    }

    /**
     * DTO para respuestas de error.
     *
     * @param mensaje descripción del error
     */
    public record ErrorResponse(String mensaje) {
    }
}
