package com.beyond.ymusicapi.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RequestHelper {
    @Value("${youtube.api.url}")
    private String youtubeUrl;
    @Value("${youtube.api.prettyPrint}")
    private String prettyPrint;
    @Value("${youtube.api.key}")
    private String key;

    public String generateApiUrl(RequestOperation operation) {
        StringBuilder url = new StringBuilder(youtubeUrl);
        switch (operation) {
            case NEW_RELEASES -> url.append("browse?");
        }
        url
                .append("prettyPrint")
                .append(prettyPrint)
                .append("&key")
                .append(key);

        return url.toString();
    }

    public enum RequestOperation {
        NEW_RELEASES
    }
}
