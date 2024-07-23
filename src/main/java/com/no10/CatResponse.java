package com.no10;


import org.springframework.http.HttpStatus;

import java.util.Map;

public class CatResponse {
    private String message;

    public CatResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
