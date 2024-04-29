package com.no10;

public class CatNotFoundException extends RuntimeException {

    public CatNotFoundException(String message) {
        super(message);
    }
}
