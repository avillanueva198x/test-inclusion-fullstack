package com.app.challenge.domain.usecase;

import com.app.challenge.application.service.MathProblemService;
import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MathProblemUseCase implements MathProblemService {

    @Override
    public MathProblemResponse solveMathProblem(MathProblemRequest request) {
        log.info("Resolviendo problema matemático con x={}, y={}, n={}", request.x(), request.y(), request.n());

        // Validar que y < x (constraint del problema)
        if (request.y() >= request.x()) {
            throw new IllegalArgumentException("y debe ser menor que x");
        }

        Long result = calculateMaxK(request.x(), request.y(), request.n());

        log.info("Resultado calculado: k={}", result);

        return new MathProblemResponse(result, request.x(), request.y(), request.n());
    }

    /**
     * Calcula el máximo k tal que 0 ≤ k ≤ n y k mod x = y
     * <p>
     * Algoritmo:
     * 1. Si y > n, no existe tal k
     * 2. Si y ≤ n, entonces k = y + ((n-y)/x) * x
     *
     * @param x valor x
     * @param y valor y
     * @param n valor n
     * @return máximo k encontrado
     */
    private Long calculateMaxK(Long x, Long y, Long n) {
        // Si y > n, no existe solución válida
        if (y > n) {
            return -1L;
        }

        // Calcular el máximo k
        // k = y + floor((n-y)/x) * x
        Long quotient = (n - y) / x;
        return y + (quotient * x);
    }
} 
