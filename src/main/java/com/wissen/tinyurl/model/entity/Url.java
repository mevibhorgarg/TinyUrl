package com.wissen.tinyurl.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Url {

    @Id
    @GeneratedValue
    private Integer id;
    private String originalUrl;
    private String sortUrl;
    private Timestamp createDate;
    private Timestamp expireDate;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getSortUrl() {
        return sortUrl;
    }

    public void setSortUrl(String sortUrl) {
        this.sortUrl = sortUrl;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Timestamp expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", sortUrl='" + sortUrl + '\'' +
                ", createDate=" + createDate +
                ", expireDate=" + expireDate +
                ", user=" + user +
                '}';
    }
}
