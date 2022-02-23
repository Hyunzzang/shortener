package com.hyun.shortener.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/redirect/v1")
@RequiredArgsConstructor
public class UrlRedirectController {

    @Qualifier("shortenUrlBase62Service")
    private final com.hyun.shortener.service.ShortenUrlService ShortenUrlService;

    @GetMapping("/{shortUrl}")
    public ModelAndView redirectToOriginalUrl(@PathVariable("shortUrl") String shortUrl) {
        return ShortenUrlService.bringOriginalUrl(parsingShortUrl(shortUrl))
            .map(shortLink -> new ModelAndView("redirect:" + shortLink.getOriginalUrl()))
            .orElseGet(() -> new ModelAndView("not found"));
    }

    private String parsingShortUrl(String shortUrl) {
        String[] arrUrl = StringUtils.split(shortUrl, "/");
        return arrUrl[arrUrl.length - 1];
    }
}