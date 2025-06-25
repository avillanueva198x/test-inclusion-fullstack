package com.app.challenge.domain.usecase;

import com.app.challenge.application.service.MathProblemService;
import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para resolver el problema matemático de Codeforces 1374A.
 * 
 * Este caso de uso implementa el algoritmo para encontrar el máximo entero k
 * tal que cumple las condiciones: 0 ≤ k ≤ n y k mod x = y.
 * 
 * El problema se resuelve usando la siguiente lógica:
 * - Si y > n, no existe solución válida (retorna -1)
 * - Si y ≤ n, entonces k = y + floor((n-y)/x) * x
 * 
 * Esta implementación sigue los principios de Domain-Driven Design,
 * manteniendo la lógica de negocio pura y separada de la infraestructura.
 * 
 * @author Adolfo Villanueva
 * @version 1.0
 * @since 2024-06-26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MathProblemUseCase implements MathProblemService {

    /**
     * Resuelve el problema matemático de Codeforces 1374A.
     * 
     * Este método implementa el algoritmo principal para encontrar el máximo k
     * que satisface las condiciones del problema.
     * 
     * Pasos del algoritmo:
     * 1. Aplicar el algoritmo de cálculo (que maneja el caso y > n internamente)
     * 2. Validar que y < x (constraint del problema) solo si hay una solución válida
     * 3. Retornar el resultado encapsulado en el DTO de respuesta
     * 
     * @param request Objeto que contiene los parámetros x, y, n ya validados
     * @return MathProblemResponse con el resultado y parámetros originales
     * @throws IllegalArgumentException si y >= x (violación de constraint del problema)
     */
    @Override
    public MathProblemResponse solveMathProblem(MathProblemRequest request) {
        log.info("Resolviendo problema matemático con x={}, y={}, n={}", request.x(), request.y(), request.n());

        // Aplicar el algoritmo de cálculo (que maneja internamente el caso y > n)
        Long result = calculateMaxK(request.x(), request.y(), request.n());

        // Solo validar y >= x si no hay una solución válida debido a y > n
        // Si y > n, el algoritmo retorna -1 y no necesitamos validar y >= x
        if (result != -1L && request.y() >= request.x()) {
            log.error("Violación de constraint: y={} debe ser menor que x={}", request.y(), request.x());
            throw new IllegalArgumentException("y debe ser menor que x");
        }

        log.info("Resultado calculado: k={} para parámetros x={}, y={}, n={}", 
                 result, request.x(), request.y(), request.n());

        return new MathProblemResponse(result, request.x(), request.y(), request.n());
    }

    /**
     * Calcula el máximo k tal que 0 ≤ k ≤ n y k mod x = y.
     * 
     * Implementación del algoritmo matemático:
     * 
     * 1. Caso base: Si y > n, no existe solución válida
     *    - Esto significa que el residuo deseado (y) es mayor que el límite superior (n)
     *    - En este caso retornamos -1 como indica el problema
     * 
     * 2. Caso general: Si y ≤ n, calculamos k usando la fórmula:
     *    - k = y + floor((n-y)/x) * x
     *    - Esta fórmula encuentra el múltiplo más grande de x que cuando se suma a y
     *      no excede n
     * 
     * Ejemplo: x=7, y=5, n=12345
     * - (n-y)/x = (12345-5)/7 = 12340/7 = 1762.857... → floor = 1762
     * - k = 5 + 1762 * 7 = 5 + 12334 = 12339
     * - Verificación: 12339 mod 7 = 5 ✓ y 12339 ≤ 12345 ✓
     * 
     * @param x valor x (base del módulo)
     * @param y valor y (residuo deseado)  
     * @param n valor n (límite superior)
     * @return máximo k encontrado, o -1 si no existe solución
     */
    private Long calculateMaxK(Long x, Long y, Long n) {
        // Caso base: Si y > n, no existe solución válida
        if (y > n) {
            log.debug("No existe solución: y={} > n={}", y, n);
            return -1L;
        }

        // Caso general: Calcular el máximo k usando la fórmula matemática
        // k = y + floor((n-y)/x) * x
        Long quotient = (n - y) / x;  // División entera (floor automático)
        Long result = y + (quotient * x);
        
        log.debug("Cálculo intermedio: quotient={}, resultado={}", quotient, result);
        
        return result;
    }
} 
