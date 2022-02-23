package com.hyun.shortener.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyun.shortener.dto.OriginalUrlRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlRegisteredApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shortUrl_등록_성공() throws Exception {
        OriginalUrlRequest originalUrlRequest = new OriginalUrlRequest("https://en.wikipedia.org/wiki/URL_shortening");

        MvcResult mvcResult = mvc.perform(post("/api/v1/url/shorten")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(originalUrlRequest)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("shortUrl").value("http://localhost/b"))
            .andReturn();
    }
}
