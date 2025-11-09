package com.example.people_management.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.people_management.dto.request.ApiRespone;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiRespone> handlingRuntimeException(Exception exception) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setMessage(exception.getMessage());
        apiRespone.setCode(1001);
        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespone> handlingValidException(MethodArgumentNotValidException exception) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setMessage(exception.getFieldError().getDefaultMessage());
        apiRespone.setCode(1002);
        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiRespone> handlingAppException(RuntimeException exception) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setMessage(exception.getMessage());
        apiRespone.setCode(1003);
        return ResponseEntity.badRequest().body(apiRespone);
    }

}
