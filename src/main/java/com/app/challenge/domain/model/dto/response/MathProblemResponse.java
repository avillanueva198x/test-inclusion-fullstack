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
 * @author Adolfo Villanueva
 * @version 1.0
 * @since 2024-06-26
 */
@Schema(description = "Respuesta del problema matemático")
public record MathProblemResponse(
    /**
     * Resultado del cálculo - máximo valor k encontrado.
     * 
     * Representa el máximo entero k tal que:
     * - 0 ≤ k ≤ n
     * - k mod x = y
     * 
     * Retorna -1 si no existe solución válida (cuando y > n).
     */
    @Schema(example = "12339", description = "Máximo valor k tal que 0 ≤ k ≤ n y k mod x = y")
    Long result,

    /**
     * Parámetro x original - base del módulo.
     * 
     * Se incluye en la respuesta para facilitar la verificación
     * y trazabilidad del resultado.
     */
    @Schema(example = "7", description = "Valor x usado en el cálculo")
    Long x,

    /**
     * Parámetro y original - residuo deseado.
     * 
     * Se incluye en la respuesta para facilitar la verificación
     * y trazabilidad del resultado.
     */
    @Schema(example = "5", description = "Valor y usado en el cálculo")
    Long y,

    /**
     * Parámetro n original - límite superior.
     * 
     * Se incluye en la respuesta para facilitar la verificación
     * y trazabilidad del resultado.
     */
    @Schema(example = "12345", description = "Valor n usado en el cálculo")
    Long n
) {
} 
