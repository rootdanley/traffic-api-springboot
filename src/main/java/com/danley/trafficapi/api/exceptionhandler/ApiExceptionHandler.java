package com.danley.trafficapi.api.exceptionhandler;


import com.danley.trafficapi.domain.exception.EntidadeNaoEncontradaException;
import com.danley.trafficapi.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
   
   
   private final MessageSource messageSource;
   
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                 HttpHeaders headers,
                                                                 HttpStatusCode status,
                                                                 WebRequest request) {
      
      ProblemDetail problemDetail = ProblemDetail.forStatus(status);
      problemDetail.setTitle("Um ou mais campos estão invalidos.");
      problemDetail.setType(URI.create("https://danley.com.br/api/erros/campos-invalidos"));
      
      
      
      Map<String, String> fields = ex.getBindingResult().getAllErrors()
          .stream()
          .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
              objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
      
      problemDetail.setProperty("fields", fields);
      
      return handleExceptionInternal(ex, problemDetail, headers, status, request);
   }
   
   @ExceptionHandler(NegocioException.class)
   public ProblemDetail handleNegocioException(NegocioException e){
      ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
      
      problemDetail.setTitle(e.getMessage());
      problemDetail.setType(URI.create("https://danley.com.br/api/erros/regra-de-negocio"));
      
      return problemDetail;

   }
   
   @ExceptionHandler(DataIntegrityViolationException.class)
   public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException e){
      ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
      problemDetail.setTitle(e.getMessage());
      problemDetail.setType(URI.create("https://danley.com.br/api/erros/recurso"));
      
      return problemDetail;
   }
   
   @ExceptionHandler(EntidadeNaoEncontradaException.class)
   public ProblemDetail handleEntidadeNaoEncontradaException(NegocioException e){
      ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
      
      problemDetail.setTitle(e.getMessage());
      problemDetail.setType(URI.create("https://danley.com.br/api/erros/recurso-nao-encontrado"));
      
      return problemDetail;
      
   }
   
}
