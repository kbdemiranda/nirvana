package io.gihub.nivarna.Infrastructure.exception;

import lombok.Getter;

@Getter
public class NirvanaException extends RuntimeException{

    private final Integer status;

    public NirvanaException(Integer status, String message){
        super(message);
        this.status = status;
    }
}
