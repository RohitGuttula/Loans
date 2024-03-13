package com.microservices.Loans.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue) {
         super(String.format("%s is not found for given values %s:%s",resourceName,fieldName,fieldValue));
    }

}
