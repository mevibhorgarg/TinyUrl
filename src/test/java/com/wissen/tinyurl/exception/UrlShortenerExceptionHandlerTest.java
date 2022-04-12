package com.wissen.tinyurl.exception;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UrlShortenerExceptionHandlerTest {

    @Test
    void handleInvalidRequest() {
    }

    @Test
    void handleBusinessException() {
        UrlShortenerExceptionHandler urlShortenerExceptionHandler = new UrlShortenerExceptionHandler();

        Map<String, String>  result= urlShortenerExceptionHandler.handleBusinessException(new UrlExpiredException("URL has expired"));
        assertTrue(result.containsValue("URL has expired"));
    }
}