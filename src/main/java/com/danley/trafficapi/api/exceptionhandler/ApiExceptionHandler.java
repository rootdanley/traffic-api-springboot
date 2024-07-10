package com.danley.trafficapi.api.exceptionhandler;


import com.danley.trafficapi.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
   
   
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                 HttpHeaders headers,
                                                                 HttpStatusCode status,
                                                                 WebRequest request) {
      
      ProblemDetail problemDetail = ProblemDetail.forStatus(status);
      problemDetail.setTitle("Um ou mais campos estão invalidos.");
      problemDetail.setType(URI.create("https://danley.com.br/api/erros/campos-invalidos"));
      problemDetail.setDetail(ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
      
      
      return handleExceptionInternal(ex, problemDetail, headers, status, request);
   }
   
   @ExceptionHandler(NegocioException.class)
   public ResponseEntity<String> capturar(NegocioException e){
      return ResponseEntity.badRequest().body(e.getMessage());
   }
}
