package com.app.challenge.application.service;

import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;

/**
 * Interfaz del servicio de aplicación para resolver problemas matemáticos.
 *
 * Define el contrato para la resolución del problema de Codeforces 1374A.
 * Esta interfaz permite la implementación del patrón Strategy y facilita
 * las pruebas unitarias mediante mocking.
 */
public interface MathProblemService {

    /**
     * Resuelve el problema matemático de Codeforces 1374A.
     *
     * @param request DTO con los parámetros x, y, n del problema
     * @return MathProblemResponse con el resultado calculado
     */
    MathProblemResponse solveMathProblem(MathProblemRequest request);
} 
