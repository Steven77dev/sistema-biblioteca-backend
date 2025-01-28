package com.growby.biblioteca.config;

import com.growby.biblioteca.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)

    public ResponseEntity<Response> handleGlobalException(Exception e) {
        Response response = Response.error("Ha ocurrido un error inesperado: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(AccesoDaoException.class)

    public ResponseEntity<Response> handleAccesoDaoException(AccesoDaoException e) {
        Response response = Response.error("Error de acceso a base de datos: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> handleServiceException(ServiceException e) {
        Response response = Response.error("Error en la lógica de negocio: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
