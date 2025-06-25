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
 * - Inmutabilidad por defecto (campos final)
 * - Implementación automática de equals(), hashCode() y toString()
 * - Constructor público automático
 * - Getters automáticos con el nombre del campo
 *
 * Las validaciones implementadas cubren:
 * - Restricciones de rango según el problema de Codeforces
 * - Valores nulos no permitidos
 * - Límites de seguridad para evitar overflow
 *
 * @param x base del módulo, debe estar en rango [2, 1000000000]
 * @param y residuo deseado, debe estar en rango [0, x-1]
 * @param n límite superior, debe estar en rango [1, 1000000000]
 *
 * @author Adolfo Villanueva
 * @version 1.0
 * @since 2024-06-26
 */
@Schema(description = "Parámetros de entrada para el problema matemático de Codeforces 1374A")
public record MathProblemRequest(

    @Schema(description = "Base del módulo. Restricción: 2 ≤ x ≤ 10^9",
        example = "7", minimum = "2", maximum = "1000000000")
    @NotNull(message = "El parámetro x es obligatorio")
    @Min(value = 2, message = "x debe ser mayor o igual a 2")
    @Max(value = 1000000000L, message = "x debe ser menor o igual a 1000000000")
    Long x,

    @Schema(description = "Residuo deseado del módulo. Restricción: 0 ≤ y < x",
        example = "5", minimum = "0")
    @NotNull(message = "El parámetro y es obligatorio")
    @Min(value = 0, message = "y debe ser mayor o igual a 0")
    Long y,

    @Schema(description = "Límite superior para k. Restricción: 1 ≤ n ≤ 10^9",
        example = "12345", minimum = "1", maximum = "1000000000")
    @NotNull(message = "El parámetro n es obligatorio")
    @Min(value = 1, message = "n debe ser mayor o igual a 1")
    @Max(value = 1000000000L, message = "n debe ser menor o igual a 1000000000")
    Long n
) {
} 
