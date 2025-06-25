package com.app.challenge.domain.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO inmutable para la respuesta del problema matemático de Codeforces 1374A.
 *
 * Este Record encapsula el resultado del cálculo junto con los parámetros
 * originales de entrada para facilitar la trazabilidad y verificación.
 *
 * La estructura incluye:
 * - result: el máximo k calculado (puede ser -1 si no hay solución)
 * - x, y, n: los parámetros originales de entrada para referencia
 *
 * Utiliza Record de Java para garantizar inmutabilidad y eficiencia,
 * proporcionando implementaciones automáticas de equals(), hashCode() y toString().
 *
 * @param result el máximo k tal que 0 ≤ k ≤ n y k mod x = y, o -1 si no existe
 * @param x parámetro original x (base del módulo)
 * @param y parámetro original y (residuo deseado)
 * @param n parámetro original n (límite superior)
 *
 * @author Adolfo Villanueva
 * @version 1.0
 * @since 2024-06-26
 */
@Schema(description = "Respuesta del problema matemático con resultado y parámetros originales")
public record MathProblemResponse(

    @Schema(description = "Resultado: máximo k tal que 0 ≤ k ≤ n y k mod x = y, o -1 si no existe",
        example = "12339")
    Long result,

    @Schema(description = "Parámetro original x", example = "7")
    Long x,

    @Schema(description = "Parámetro original y", example = "5")
    Long y,

    @Schema(description = "Parámetro original n", example = "12345")
    Long n
) {
} 
