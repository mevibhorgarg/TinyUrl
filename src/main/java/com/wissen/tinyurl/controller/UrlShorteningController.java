package com.wissen.tinyurl.controller;

import com.wissen.tinyurl.model.UrlRequest;
import com.wissen.tinyurl.model.UrlResponse;
import com.wissen.tinyurl.model.UrlResponse;
import com.wissen.tinyurl.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class UrlShorteningController {

    private UrlService urlService;

    public UrlShorteningController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/createurl")
    public UrlResponse createTinyUrl(@RequestBody @Valid UrlRequest urlRequest){

        return urlService.createTinyUrl(urlRequest);
    }

    @GetMapping("/geturl/{shorturl}")
    public ResponseEntity<?> getLongUrl(@PathVariable("shorturl") String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.getLongUrl(shortUrl);
        response.sendRedirect(originalUrl);
        return null;
    }

}
