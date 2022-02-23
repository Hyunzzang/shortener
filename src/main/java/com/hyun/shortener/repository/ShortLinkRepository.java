package com.hyun.shortener.repository;

import com.hyun.shortener.domain.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {

    ShortLink findByOriginalUrl(String originalUrl);
}
