package com.wissen.tinyurl.model;

import com.wissen.tinyurl.model.entity.User;

public class UrlRequest {

    private String originalUrl;
    private User userId;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
