package com.wissen.tinyurl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UrlShortenerException extends RuntimeException{

    public UrlShortenerException(String message) {
        super(message);
    }
}