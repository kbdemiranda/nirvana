package io.gihub.nivarna.Infrastructure.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(NirvanaException.class)
    public ResponseEntity<?> nirvanaException(NirvanaException nirvanaException){
        return ResponseEntity.status(nirvanaException.getStatus()).body(nirvanaException.getMessage());
    }
}

