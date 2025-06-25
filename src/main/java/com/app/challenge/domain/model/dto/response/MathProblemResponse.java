package com.app.challenge.domain.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta del problema matemático")
public record MathProblemResponse(
    @Schema(example = "12339", description = "Máximo valor k tal que 0 ≤ k ≤ n y k mod x = y")
    Long result,

    @Schema(example = "7", description = "Valor x usado en el cálculo")
    Long x,

    @Schema(example = "5", description = "Valor y usado en el cálculo")
    Long y,

    @Schema(example = "12345", description = "Valor n usado en el cálculo")
    Long n
) {
} 
