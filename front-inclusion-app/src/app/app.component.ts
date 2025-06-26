import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MathProblemService } from './services/math-problem.service';
import { MathProblemRequest, MathProblemResponse } from './models/math-problem.interface';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Math Problem Solver - Codeforces 1374A';
  
  // Formulario
  x: number = 0;
  y: number = 0;
  n: number = 0;
  
  // Estado de la aplicación
  result: MathProblemResponse | null = null;
  isLoading: boolean = false;
  error: string | null = null;

  constructor(private mathProblemService: MathProblemService) {}

  /**
   * Envía la solicitud al backend para resolver el problema matemático
   */
  solveProblem(): void {
    this.error = null;
    this.isLoading = true;
    
    const request: MathProblemRequest = {
      x: this.x,
      y: this.y,
      n: this.n
    };

    this.mathProblemService.solveMathProblem(request).subscribe({
      next: (response) => {
        this.result = response;
        this.isLoading = false;
      },
      error: (errorResponse) => {
        console.error('Error del backend:', errorResponse);
        this.isLoading = false;
        
        // Manejar diferentes tipos de errores del backend
        if (errorResponse.status === 400) {
          // Error de validación - mostrar mensaje del backend
          if (errorResponse.error && errorResponse.error.mensaje) {
            this.error = errorResponse.error.mensaje;
          } else if (errorResponse.error && typeof errorResponse.error === 'string') {
            this.error = errorResponse.error;
          } else {
            this.error = 'Parámetros inválidos. Verifica los valores ingresados.';
          }
        } else if (errorResponse.status === 500) {
          this.error = 'Error interno del servidor. Inténtalo nuevamente.';
        } else if (errorResponse.status === 0) {
          this.error = 'No se pudo conectar con el servidor. Verifica que el backend esté ejecutándose en http://localhost:9191';
        } else {
          this.error = `Error ${errorResponse.status}: ${errorResponse.message || 'Error desconocido'}`;
        }
      }
    });
  }

  /**
   * Limpia el formulario y los resultados
   */
  clearForm(): void {
    this.x = 0;
    this.y = 0;
    this.n = 0;
    this.result = null;
    this.error = null;
  }
}
