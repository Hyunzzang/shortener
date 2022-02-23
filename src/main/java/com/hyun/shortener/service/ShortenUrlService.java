package com.hyun.shortener.service;

import com.hyun.shortener.domain.ShortLink;
import java.util.Optional;

public interface ShortenUrlService {
    String bringShortenUrl(String originalUrl);

    Optional<ShortLink> bringOriginalUrl(String shortUrl);
}
