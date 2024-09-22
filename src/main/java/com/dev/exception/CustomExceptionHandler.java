package com.dev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(er->
                errors.put(er.getField(),er.getDefaultMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StudentAlreadyExistsException.class)
    public Map<String,String> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex){
        Map<String,String> error = new HashMap<>();
        error.put("error",ex.getMessage());
        return error;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentNotFoundException.class)
    public Map<String,String> handleStudentNotFoundException(StudentNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("error",ex.getMessage());
        return error;
    }


}
