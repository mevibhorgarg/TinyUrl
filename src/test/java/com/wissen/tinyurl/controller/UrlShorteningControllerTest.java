package com.wissen.tinyurl.controller;

import com.wissen.tinyurl.model.UrlRequest;
import com.wissen.tinyurl.model.UrlResponse;
import com.wissen.tinyurl.service.UrlService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {UrlShorteningController.class})
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UrlShorteningControllerTest {

    @Mock
    private UrlService urlService;
    @Mock
    HttpServletResponse response;
    @InjectMocks
    UrlShorteningController urlShorteningController;
    UrlRequest urlRequest= new UrlRequest();
    UrlResponse urlResponse = new UrlResponse();

    @Test
    void createTinyUrl() {
        urlRequest.setOriginalUrl("test");
        urlResponse.setShortUrl("Test");
        Mockito.when(urlService.createTinyUrl(urlRequest)).thenReturn(urlResponse);
        UrlResponse result= urlShorteningController.createTinyUrl(urlRequest);
        Assert.assertEquals("Test", result.getShortUrl());
    }

    @Test
    void getLongUrl() throws IOException {

        Mockito.when(urlService.getLongUrl("Test")).thenReturn("Test url");
        ResponseEntity<?> result=  urlShorteningController.getLongUrl("Test", response);
        Assert.assertNotNull("Successfully exicuted");
    }
}