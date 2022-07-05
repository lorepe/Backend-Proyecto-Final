package com.dh.odontologia.exceptions;

import org.apache.log4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger =  Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> todosErrres(Exception ex , WebRequest req){
        logger.error(ex.getMessage());
        return new ResponseEntity("Error"+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> procesarErrorBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()+ " capturado desde la clase... "+this.getClass().getName()+" de manera global");
    }
    @ExceptionHandler({ResourceNotFoundExceptions.class})
    public ResponseEntity<String> procesarErrorBadRequest(ResourceNotFoundExceptions ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()+ " capturado desde la clase... "+this.getClass().getName()+" de manera global");
    }

}
