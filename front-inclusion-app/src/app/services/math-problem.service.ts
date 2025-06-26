import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MathProblemRequest, MathProblemResponse } from '../models/math-problem.interface';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MathProblemService {
  private readonly baseUrl = environment.apiUrl;
  private readonly apiUrl = `${this.baseUrl}/api/v1/math`;

  constructor(private http: HttpClient) { }

  /**
   * Resuelve el problema matemático enviando los parámetros al backend
   * @param request Parámetros x, y, n
   * @returns Observable con la respuesta del problema
   */
  solveMathProblem(request: MathProblemRequest): Observable<MathProblemResponse> {
    return this.http.post<MathProblemResponse>(`${this.apiUrl}/solve`, request);
  }
} 