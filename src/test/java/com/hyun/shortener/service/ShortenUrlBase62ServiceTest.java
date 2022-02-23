package com.hyun.shortener.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShortenUrlBase62ServiceTest {

    @Autowired
    private ShortenUrlBase62Service shortenUrlBase62Service;

    @Test
    public void shortenUrl_언제나_같은_결과를_리턴_하는지() {
        String originalUrl = "https://en.wikipedia.org/wiki/URL_shortening";
        String res = shortenUrlBase62Service.bringShortenUrl(originalUrl);
        assertThat(res).isEqualTo("http://localhost/b");

        String res1 = shortenUrlBase62Service.bringShortenUrl(originalUrl);
        assertThat(res1).isEqualTo("http://localhost/b");

        String res2 = shortenUrlBase62Service.bringShortenUrl(originalUrl);
        assertThat(res2).isEqualTo("http://localhost/b");
    }

    @Test
    public void bringOriginalUrl_성공() {
        String originalUrl = "https://en.wikipedia.org/wiki/URL_shortening";
        shortenUrlBase62Service.bringShortenUrl(originalUrl);

        String[] arrUrl = StringUtils.split("http://localhost/b", "/");
        String resOriginalUrl = shortenUrlBase62Service.bringOriginalUrl(arrUrl[arrUrl.length-1]).get().getOriginalUrl();
        assertThat(resOriginalUrl).isEqualTo("https://en.wikipedia.org/wiki/URL_shortening");

    }
}
