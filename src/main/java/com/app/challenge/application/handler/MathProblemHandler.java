package com.app.challenge.application.handler;

import com.app.challenge.application.service.MathProblemService;
import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MathProblemHandler {

    private final MathProblemService mathProblemService;

    public MathProblemResponse handle(MathProblemRequest request) {
        log.info("Procesando solicitud de problema matemático");
        return this.mathProblemService.solveMathProblem(request);
    }
} 