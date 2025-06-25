package com.app.challenge.domain.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de respuesta del problema matemático con resultado y parámetros originales.
 *
 * @param result máximo k calculado, o -1 si no existe solución
 * @param x parámetro original x
 * @param y parámetro original y
 * @param n parámetro original n
 */
@Schema(description = "Respuesta del problema matemático")
public record MathProblemResponse(

    @Schema(description = "Resultado calculado", example = "12339")
    Long result,

    @Schema(description = "Parámetro x", example = "7")
    Long x,

    @Schema(description = "Parámetro y", example = "5")
    Long y,

    @Schema(description = "Parámetro n", example = "12345")
    Long n
) {
} 
