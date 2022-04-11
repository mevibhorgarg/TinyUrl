package com.wissen.tinyurl.repo;

import com.wissen.tinyurl.model.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UrlRepo extends JpaRepository<Url, Integer> {

    @Query("SELECT a FROM Url a WHERE a.sortUrl=?1")
    Url getLongUrl(String shortUrl);
}
