package com.bgddt.qlvb.common.exceptions;

import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class BusinessException extends Exception {
    private String description;
    public BusinessException(String errorMessage){
        super(errorMessage);
    }
    public BusinessException(String errorMessage, String description){
        super(errorMessage);
        this.description = description;
    }
}