package com.hyun.shortener.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyun.shortener.service.ShortenUrlBase62Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UrlRedirectControllerTest {

    @Autowired
    private UrlRedirectController urlRedirectController;

    @Autowired
    private ShortenUrlBase62Service shortenUrlBase62Service;


    @Test
    public void redirectToOriginalUrl_성공() {
       shortenUrlBase62Service.bringShortenUrl("https://en.wikipedia.org/wiki/URL_shortening");

        String shortUrl = "http://localhost/b";
        String viewName = urlRedirectController.redirectToOriginalUrl(shortUrl).getViewName();

        assertThat(viewName).isEqualTo("redirect:" + "https://en.wikipedia.org/wiki/URL_shortening");
    }
}
