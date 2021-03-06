package com.hyun.shortener.controller;

import com.hyun.shortener.dto.OriginalUrlRequest;
import com.hyun.shortener.dto.ShortenUrlResponse;
import com.hyun.shortener.service.ShortenUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class UrlRegisteredApiController {

    @Qualifier("shortenUrlBase62Service")
    private final ShortenUrlService ShortenUrlService;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> registerShortUrl(@RequestBody OriginalUrlRequest originalUrlRequest) {
        log.debug(":: registerShortUrl - originalUrl: {}", originalUrlRequest.getOriginalUrl());

        String shortUrl = ShortenUrlService.bringShortenUrl(originalUrlRequest.getOriginalUrl());

        return ResponseEntity.ok(new ShortenUrlResponse(shortUrl));
    }
}
