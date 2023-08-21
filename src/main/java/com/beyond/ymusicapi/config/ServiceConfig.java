package com.beyond.ymusicapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {
    @Value("${youtube.api.url}")
    private String youtubeUrl;
    @Value("${youtube.api.prettyPrint}")
    private String prettyPrint;
    @Value("${youtube.api.key}")
    private String key;

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public String getPrettyPrint() {
        return prettyPrint;
    }

    public String getKey() {
        return key;
    }
}
