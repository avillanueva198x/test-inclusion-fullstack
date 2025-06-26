package com.app.challenge.application.service;

import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;

public interface MathProblemService {

    MathProblemResponse solveMathProblem(MathProblemRequest request);

}
