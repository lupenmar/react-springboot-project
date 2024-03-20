package com.lupenmar.springtodo.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String str) {
        super(str);
    }

}