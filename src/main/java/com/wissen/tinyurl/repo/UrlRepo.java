package com.wissen.tinyurl.repo;

import com.wissen.tinyurl.model.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UrlRepo extends JpaRepository<Url, Integer> {

    @Query("SELECT a.originalUrl FROM Url a WHERE a.sortUrl=?1")
    String getLongUrl(String shortUrl);
}
