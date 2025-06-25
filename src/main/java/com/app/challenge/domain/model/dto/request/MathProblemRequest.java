package com.app.challenge.domain.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para los parámetros del problema matemático de Codeforces 1374A.
 *
 * @param x base del módulo (2 ≤ x ≤ 10^9)
 * @param y residuo deseado (0 ≤ y < x)
 * @param n límite superior (1 ≤ n ≤ 10^9)
 */
@Schema(description = "Parámetros de entrada para el problema matemático")
public record MathProblemRequest(

    @Schema(description = "Base del módulo", example = "7", minimum = "2", maximum = "1000000000")
    @NotNull(message = "El parámetro x es obligatorio")
    @Min(value = 2, message = "x debe ser mayor o igual a 2")
    @Max(value = 1000000000L, message = "x debe ser menor o igual a 1000000000")
    Long x,

    @Schema(description = "Residuo deseado", example = "5", minimum = "0")
    @NotNull(message = "El parámetro y es obligatorio")
    @Min(value = 0, message = "y debe ser mayor o igual a 0")
    Long y,

    @Schema(description = "Límite superior", example = "12345", minimum = "1", maximum = "1000000000")
    @NotNull(message = "El parámetro n es obligatorio")
    @Min(value = 1, message = "n debe ser mayor o igual a 1")
    @Max(value = 1000000000L, message = "n debe ser menor o igual a 1000000000")
    Long n
) {
} 
