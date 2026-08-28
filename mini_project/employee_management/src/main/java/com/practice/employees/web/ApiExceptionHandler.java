package com.practice.employees.web;

import com.practice.employees.exception.ConflictException;
import com.practice.employees.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.URI;

@RestControllerAdvice(basePackages = "com.practice.employees.web")
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ProblemDetail notFound(ResourceNotFoundException ex) { return problem(HttpStatus.NOT_FOUND, ex.getMessage()); }
    @ExceptionHandler(ConflictException.class)
    ProblemDetail conflict(ConflictException ex) { return problem(HttpStatus.CONFLICT, ex.getMessage()); }
    @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class})
    ProblemDetail badRequest(Exception ex) { return problem(HttpStatus.BAD_REQUEST, ex.getMessage()); }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail validation(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage()).distinct().sorted()
                .reduce((left, right) -> left + "; " + right).orElse("Validation failed");
        return problem(HttpStatus.BAD_REQUEST, detail);
    }
    private ProblemDetail problem(HttpStatus status, String detail) {
        var problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setType(URI.create("https://example.com/problems/" + status.value()));
        problem.setTitle(status.getReasonPhrase());
        return problem;
    }
}
