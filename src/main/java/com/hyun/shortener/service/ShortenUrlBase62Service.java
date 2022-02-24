package com.hyun.shortener.service;

import com.hyun.shortener.domain.ShortLink;
import com.hyun.shortener.repository.ShortLinkRepository;
import com.hyun.shortener.util.UrlShortenerBase62Util;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShortenUrlBase62Service implements ShortenUrlService {

    private final ShortLinkRepository shortLinkRepository;


    @Override
    @Transactional
    public String bringShortenUrl(String originalUrl) {
        // todo: 파라미터 url 검증 필요

        return Optional.ofNullable(shortLinkRepository.findByOriginalUrl(originalUrl))
            .orElseGet(() -> saveShortLink(originalUrl))
            .getShortUrl();
    }

    private ShortLink saveShortLink(String originalUrl) {

        // todo: shortUrl 이 중복 된다면?
        ShortLink shortLink = ShortLink.builder()
            .originalUrl(originalUrl)
            .build();

        return shortLinkRepository.save(shortLink);
    }

    @Override
    public Optional<ShortLink> bringOriginalUrl(String shortUrl) {
        return shortLinkRepository.findById(UrlShortenerBase62Util.decode(shortUrl));
    }
}
