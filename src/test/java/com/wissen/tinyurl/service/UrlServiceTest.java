package com.wissen.tinyurl.service;

import com.wissen.tinyurl.mapper.UrlServiceMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class UrlServiceTest {

    @Mock
    UrlServiceMapper urlServiceMapper;
    @Mock
    UrlExpireCheckService urlExpireCheckService;
    @InjectMocks
    UrlService urlService;

    @Test
    void createTinyUrl() {
    }

    @Test
    void getLongUrl() {
    }
}