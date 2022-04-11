package com.wissen.tinyurl.service;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UrlExpireCheckService {

    public boolean expireCheck(Timestamp expireDate) {
        Long expireTime= expireDate.getTime();
        Long currentTime= System.currentTimeMillis();
        if(expireTime >= currentTime){
            return false;
        }
        return true;
    }
}
