package com.app.challenge.domain.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request para resolver el problema matemático")
public record MathProblemRequest(
    @NotNull(message = "El valor x es obligatorio")
    @Min(value = 2, message = "x debe ser mayor o igual a 2")
    @Max(value = 1000000000L, message = "x debe ser menor o igual a 10^9")
    @Schema(example = "7", description = "Valor x (2 ≤ x ≤ 10^9)")
    Long x,

    @NotNull(message = "El valor y es obligatorio")
    @Min(value = 0, message = "y debe ser mayor o igual a 0")
    @Schema(example = "5", description = "Valor y (0 ≤ y < x)")
    Long y,

    @NotNull(message = "El valor n es obligatorio")
    @Min(value = 1, message = "n debe ser mayor o igual a 1")
    @Max(value = 1000000000L, message = "n debe ser menor o igual a 10^9")
    @Schema(example = "12345", description = "Valor n (1 ≤ n ≤ 10^9)")
    Long n
) {
} 
