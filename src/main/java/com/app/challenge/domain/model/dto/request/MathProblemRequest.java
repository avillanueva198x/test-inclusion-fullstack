package com.app.challenge.domain.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO inmutable para la petición del problema matemático de Codeforces 1374A.
 * 
 * Este Record encapsula los tres parámetros necesarios para resolver el problema:
 * - x: base del módulo (2 ≤ x ≤ 10^9)
 * - y: residuo deseado (0 ≤ y < x) 
 * - n: límite superior (1 ≤ n ≤ 10^9)
 * 
 * Utiliza Bean Validation (Jakarta) para validaciones de entrada automáticas
 * y documentación Swagger para la API REST.
 * 
 * La inmutabilidad se garantiza usando Record de Java 14+, lo que proporciona:
 * - Inmutabilidad por defecto
 * - Generación automática de equals(), hashCode(), toString()
 * - Mejor rendimiento que las clases tradicionales
 * 
 * @author Adolfo Villanueva
 * @version 1.0
 * @since 2024-06-26
 */
@Schema(description = "Request para resolver el problema matemático")
public record MathProblemRequest(
    /**
     * Valor x - base del módulo.
     * 
     * Debe estar en el rango [2, 10^9] según las especificaciones del problema.
     * Se valida automáticamente con Bean Validation antes de llegar al controller.
     */
    @NotNull(message = "El valor x es obligatorio")
    @Min(value = 2, message = "x debe ser mayor o igual a 2")
    @Max(value = 1000000000L, message = "x debe ser menor o igual a 10^9")
    @Schema(example = "7", description = "Valor x (2 ≤ x ≤ 10^9)")
    Long x,

    /**
     * Valor y - residuo deseado del módulo.
     * 
     * Debe ser no negativo y menor que x. La validación y < x se realiza
     * en el caso de uso como regla de negocio, no aquí.
     */
    @NotNull(message = "El valor y es obligatorio")
    @Min(value = 0, message = "y debe ser mayor o igual a 0")
    @Schema(example = "5", description = "Valor y (0 ≤ y < x)")
    Long y,

    /**
     * Valor n - límite superior para k.
     * 
     * Debe estar en el rango [1, 10^9] según las especificaciones del problema.
     * El resultado k nunca será mayor que n.
     */
    @NotNull(message = "El valor n es obligatorio")
    @Min(value = 1, message = "n debe ser mayor o igual a 1")
    @Max(value = 1000000000L, message = "n debe ser menor o igual a 10^9")
    @Schema(example = "12345", description = "Valor n (1 ≤ n ≤ 10^9)")
    Long n
) {
} 
