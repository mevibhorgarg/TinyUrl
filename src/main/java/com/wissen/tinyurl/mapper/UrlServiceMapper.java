package com.wissen.tinyurl.mapper;

import com.wissen.tinyurl.model.UrlRequest;
import com.wissen.tinyurl.model.entity.Url;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class UrlServiceMapper {

    public Url mapUrlFromUrlRequest(UrlRequest urlRequest, String shortUrl) {
        Url url = new Url();
        url.setOriginalUrl(urlRequest.getOriginalUrl());
        url.setUser(urlRequest.getUserId());
        url.setSortUrl(shortUrl);
        url.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        url.setExpireDate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
        return url;
    }
}
