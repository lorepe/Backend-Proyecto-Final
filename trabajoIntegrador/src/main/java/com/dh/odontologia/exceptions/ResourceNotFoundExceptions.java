package com.dh.odontologia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ResourceNotFoundExceptions extends Exception{
    public ResourceNotFoundExceptions(String message) {
        super(message);
    }

}
