package com.pablo.cantina.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        var details = ErrorDetails.Builder //
                .newBuilder() //
                .code(HttpStatus.NOT_FOUND.value()) //
                .title("Recurso não encontrado") //
                .message(rfnException.getMessage()) //
                .build(); //
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException iaeException) {
        var details = ErrorDetails.Builder //
                .newBuilder() //
                .code(HttpStatus.BAD_REQUEST.value()) //
                .title("Requisição inválida") //
                .message(iaeException.getMessage()) //
                .build(); //
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
