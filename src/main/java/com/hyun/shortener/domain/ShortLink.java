package com.hyun.shortener.domain;

import com.hyun.shortener.util.UrlShortenerBase62Util;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
    name = "short_link",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"original_url"}
        )
    }
)
public class ShortLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public ShortLink(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    /**
     * [주의] 트랙잭션내에서 실행 되어야 db에 shortUrl 가 저장됨
     */
    public void setShortUrl() {
        if (this.id == null && this.id > 0) {
            // todo: 에러 처리
        }

        this.shortUrl = "http://localhost/".concat(UrlShortenerBase62Util.encode(getId()));
    }


    public String getShortUrl() {
        if (StringUtils.isEmpty(this.shortUrl)) {
            setShortUrl();
        }

        return this.shortUrl;
    }
}
