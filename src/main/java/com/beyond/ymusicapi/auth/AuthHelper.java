package com.beyond.ymusicapi.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthHelper {

    @Value("${headers.path}")
    private String headersPath;
    private static Map<String, String> httpHeaders;

    public Map<String, String> getHttpHeaders() {
        if (httpHeaders != null) {
            return httpHeaders;
        }

        try {
            httpHeaders = new ObjectMapper().readValue(new ClassPathResource(headersPath).getFile(), HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return httpHeaders;
    }
}
