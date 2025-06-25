package com.app.challenge.application.service;

import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;

public interface MathProblemService {
    /**
     * Resuelve el problema matemático: encontrar el máximo k tal que 0 ≤ k ≤ n y k mod x = y
     *
     * @param request Parámetros de entrada x, y, n
     * @return Respuesta con el resultado calculado
     */
    MathProblemResponse solveMathProblem(MathProblemRequest request);
} 
