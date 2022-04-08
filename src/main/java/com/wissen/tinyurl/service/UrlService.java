package com.wissen.tinyurl.service;

import com.wissen.tinyurl.model.UrlRequest;
import com.wissen.tinyurl.model.UrlResponse;
import com.wissen.tinyurl.model.entity.Url;
import com.wissen.tinyurl.repo.UrlRepo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UrlService {

    Long counter = 100000l;
    String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private UrlRepo urlRepo;

    public UrlService(UrlRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public UrlResponse createTinyUrl(UrlRequest urlRequest) {
        UrlResponse urlResponse = new UrlResponse();
        Url url = new Url();
        String shortUrl = encode();

        url.setOriginalUrl(urlRequest.getOriginalUrl());
        url.setUser(urlRequest.getUserId());
        url.setSortUrl(shortUrl);
        url.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        url.setExpireDate(Timestamp.valueOf(LocalDateTime.now().plusMinutes(1)));
        urlRepo.save(url);

        urlResponse.setShortUrl(shortUrl);
        return urlResponse;
    }

    private String encode() {
        counter++;
        return base62Encode(counter);
    }

    private String base62Encode(long value) {
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            sb.append(base62.charAt((int)(value % 62)));
            value /= 62;
        }
        while (sb.length() < 6) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }

    public String getLongUrl(String shortUrl) {
        return urlRepo.getLongUrl(shortUrl);
    }
}
