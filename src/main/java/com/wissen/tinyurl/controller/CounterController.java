package com.wissen.tinyurl.controller;

import com.wissen.tinyurl.model.CounterRequest;
import com.wissen.tinyurl.model.UrlRequest;
import com.wissen.tinyurl.model.UrlResponse;
import com.wissen.tinyurl.service.UrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CounterController {

    private UrlService urlService;

    public CounterController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/counter")
    public void updateCounter(@RequestBody CounterRequest counterRequest){
        urlService.updateCounter(counterRequest);
    }
}
