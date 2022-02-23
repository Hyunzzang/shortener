package com.hyun.shortener.repository;



import static org.assertj.core.api.Assertions.assertThat;

import com.hyun.shortener.domain.ShortLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ShortLinkRepositoryTest {

    @Autowired
    ShortLinkRepository shortLinkRepository;


    @Test
    public void 저장_성공() {
        ShortLink shortLink = ShortLink.builder()
            .originalUrl("http://test/test/12345")
            .build();

        shortLinkRepository.save(shortLink);
        ShortLink dbShortLink = shortLinkRepository.findByOriginalUrl("http://test/test/12345");

        assertThat(dbShortLink).isNotNull();
        assertThat(dbShortLink.getOriginalUrl()).isEqualTo("http://test/test/12345");
    }

}
