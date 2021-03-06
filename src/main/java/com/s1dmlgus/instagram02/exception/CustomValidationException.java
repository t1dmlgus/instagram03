package com.s1dmlgus.instagram02.exception;

import java.util.Map;

public class CustomValidationException extends RuntimeException{


    private Map<String, String> errorMap;


    public CustomValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public CustomValidationException(String message) {
        super(message);
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
