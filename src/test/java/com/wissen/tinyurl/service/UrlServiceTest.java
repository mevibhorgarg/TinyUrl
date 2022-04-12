package com.wissen.tinyurl.service;

import com.wissen.tinyurl.exception.UrlExpiredException;
import com.wissen.tinyurl.exception.UrlShortenerException;
import com.wissen.tinyurl.mapper.UrlServiceMapper;
import com.wissen.tinyurl.model.UrlRequest;
import com.wissen.tinyurl.model.UrlResponse;
import com.wissen.tinyurl.model.entity.Url;
import com.wissen.tinyurl.model.entity.User;
import com.wissen.tinyurl.repo.UrlRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@ContextConfiguration(classes = {UrlService.class})
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UrlServiceTest {

    @Spy
    private UrlServiceMapper urlServiceMapper;
    @Spy
    private UrlExpireCheckService urlExpireCheckService;
    @Mock
    private UrlRepo urlRepo;
    @InjectMocks
    private UrlService urlService;
    UrlRequest urlRequest= new UrlRequest();
    User user= new User();
    Url originalUrl= new Url();

    @Test
    void createTinyUrl() {
        urlRequest.setOriginalUrl("google.com");
        user.setUserId(1);
        user.setEmail("123@gmail.com");
        user.setName("Vibhor");
        user.setPhoneNumber(1234L);
        UrlResponse response = urlService.createTinyUrl(urlRequest);
        Assert.assertEquals("000q0V", response.getShortUrl());
    }

    @Test
    void getLongUrl() {
        Timestamp time= Timestamp.valueOf(LocalDateTime.now().plusHours(1));
        originalUrl.setOriginalUrl("google.com");
        originalUrl.setExpireDate(time);
        Mockito.when(urlRepo.getLongUrl("test")).thenReturn(originalUrl);
        String result= urlService.getLongUrl("test");
        Assert.assertEquals("google.com", originalUrl.getOriginalUrl());
    }

    @Test
    void getLongUrlForException() {
        Timestamp time= Timestamp.valueOf(LocalDateTime.now());
        originalUrl.setOriginalUrl("google.com");
        originalUrl.setExpireDate(time);
        Mockito.when(urlRepo.getLongUrl("test")).thenReturn(originalUrl);
        try{
            urlService.getLongUrl("test");
        }
        catch (UrlExpiredException e){
            Assert.assertEquals("URL has expired", e.getMessage());
        }

    }

    @Test
    void getLongUrlForExceptionEmptyObject() {
        Timestamp time= Timestamp.valueOf(LocalDateTime.now());
        originalUrl.setOriginalUrl("google.com");
        originalUrl.setExpireDate(time);
        Mockito.when(urlRepo.getLongUrl("test")).thenReturn(new Url());
        try{
            urlService.getLongUrl("test");
        }
        catch (UrlShortenerException e){
            Assert.assertEquals("There is no original URL for shortUrl: test", e.getMessage());
        }

    }
}