package com.hyun.shortener.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/redirect")
@RequiredArgsConstructor
public class UrlRedirectController {

    @Qualifier("shortenUrlBase62Service")
    private final com.hyun.shortener.service.ShortenUrlService ShortenUrlService;

    @GetMapping
    public ModelAndView redirectToOriginalUrl(@RequestParam("shortUrl") String shortUrl) {
        // todo: 페이지가 없을 경우
        return ShortenUrlService.bringOriginalUrl(parsingShortUrl(shortUrl))
            .map(shortLink -> new ModelAndView("redirect:" + shortLink.getOriginalUrl()))
            .orElseGet(() -> new ModelAndView("notFound"));
    }

    private String parsingShortUrl(String shortUrl) {
        String[] arrUrl = StringUtils.split(shortUrl, "/");
        return arrUrl[arrUrl.length - 1];
    }
}
