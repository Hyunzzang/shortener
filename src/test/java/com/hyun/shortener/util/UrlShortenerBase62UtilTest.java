package com.hyun.shortener.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UrlShortenerBase62UtilTest {


    @Test
    public void encodeTest() {
        String shortUrl = UrlShortenerBase62Util.encode(Integer.MAX_VALUE);
        assertThat(shortUrl).isNotNull().isEqualTo("cvuMLb");
    }

    @Test
    public void decodeTest() {
        long num = UrlShortenerBase62Util.decode("cvuMLb");
        assertThat(num).isEqualTo(Integer.MAX_VALUE);
    }
}
