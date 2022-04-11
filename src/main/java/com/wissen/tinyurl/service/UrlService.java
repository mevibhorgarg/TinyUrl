package com.wissen.tinyurl.service;

import com.wissen.tinyurl.exception.UrlExpiredException;
import com.wissen.tinyurl.exception.UrlShortenerException;
import com.wissen.tinyurl.mapper.UrlServiceMapper;
import com.wissen.tinyurl.model.UrlRequest;
import com.wissen.tinyurl.model.UrlResponse;
import com.wissen.tinyurl.model.entity.Url;
import com.wissen.tinyurl.repo.UrlRepo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UrlService {

    Long counter = 100000l;
    String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final UrlRepo urlRepo;
    private final UrlServiceMapper urlServiceMapper;
    private final UrlExpireCheckService urlExpireCheckService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlService.class);

    public UrlService(UrlRepo urlRepo, UrlServiceMapper urlServiceMapper, UrlExpireCheckService urlExpireCheckService) {
        this.urlRepo = urlRepo;
        this.urlServiceMapper = urlServiceMapper;
        this.urlExpireCheckService = urlExpireCheckService;
    }

    public UrlResponse createTinyUrl(UrlRequest urlRequest) {
        UrlResponse urlResponse = new UrlResponse();
        String shortUrl = encode();
        Url url = urlServiceMapper.mapUrlFromUrlRequest(urlRequest, shortUrl);
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
        Url originalUrl = urlRepo.getLongUrl(shortUrl);
        if(ObjectUtils.isEmpty(originalUrl) || StringUtils.isEmpty(originalUrl.getOriginalUrl())){
            throw new UrlShortenerException("There is no original URL for shortUrl: " + shortUrl);
        }
        if(urlExpireCheckService.expireCheck(originalUrl.getExpireDate())){
            throw new UrlExpiredException("URL has expired");
        }
        return originalUrl.getOriginalUrl();
    }
}