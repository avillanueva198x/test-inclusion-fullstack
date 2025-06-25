package com.app.challenge.application.service;

import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;

/**
 * Servicio para resolver problemas matemáticos.
 */
public interface MathProblemService {

    /**
     * Resuelve el problema matemático de Codeforces 1374A.
     *
     * @param request parámetros x, y, n del problema
     * @return respuesta con el resultado calculado
     */
    MathProblemResponse solveMathProblem(MathProblemRequest request);
} 
