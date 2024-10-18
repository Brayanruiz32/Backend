package com.backend.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlerEntityNotFoundException(Exception e){
        return new ResponseEntity<>("No se encuentra el registro en la BD", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handlerRequestMethosNotSupportedException(Exception e){
        return new ResponseEntity<>("Error en el metodo usado para la petición", HttpStatus.BAD_REQUEST);
    }



}
