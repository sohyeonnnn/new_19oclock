package com.refactoring.ilgusi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private String url;
    private boolean close;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, String url) {
        super(message);
        this.url = url;
    }

    public CustomException(String message, String url, boolean close) {
        super(message);
        this.url = url;
        this.close = close;
    }


    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
