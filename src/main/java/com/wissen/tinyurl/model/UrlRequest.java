package com.wissen.tinyurl.model;

import com.wissen.tinyurl.model.entity.User;

import javax.validation.constraints.NotNull;

public class UrlRequest {

    @NotNull(message = "originalUrl shouldn't be null")
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
