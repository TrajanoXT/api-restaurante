package dev.trajano.restaurante.exceptions.handler;

import dev.trajano.restaurante.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> cpfAlreadyExistsException(CpfAlreadyExistsException ex){
        return build(HttpStatus.CONFLICT,ex.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException ex){
        return build(HttpStatus.NOT_FOUND,ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex){
        return build(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        return build(HttpStatus.BAD_REQUEST,ex.getMessage());
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(BusinessException ex){
        return build(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<ErrorResponse> dataConflictException(DataConflictException ex){
        return build(HttpStatus.CONFLICT,ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String message){
        return ResponseEntity.status(status).body(
                new ErrorResponse(status.value(),message,Instant.now())
        );
    }
}
