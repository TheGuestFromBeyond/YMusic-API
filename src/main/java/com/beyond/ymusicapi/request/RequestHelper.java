package com.beyond.ymusicapi.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class RequestHelper {
    @Value("${youtube.api.url}")
    private String youtubeUrl;
    @Value("${youtube.api.prettyPrint}")
    private String prettyPrint;
    @Value("${youtube.api.key}")
    private String key;

    public String generateApiUrl(RequestOperation operation) {
        return generateApiUrl(operation, null);
    }

    public String generateApiUrl(RequestOperation operation, LinkedList<String> params) {
        StringBuilder url = new StringBuilder(youtubeUrl);
        switch (operation) {
            case COMMON_OPERATION -> url.append("browse?");
            case CONTINUATION -> url
                    .append("browse?")
                    .append("ctoken=")
                    .append(params.get(0))
                    .append("&continuation=")
                    .append(params.get(0))
                    .append("&type=next&itct=")
                    .append(params.get(1));

        }
        url
                .append("prettyPrint")
                .append(prettyPrint)
                .append("&key")
                .append(key);

        return url.toString();
    }

    public enum RequestOperation {
        COMMON_OPERATION,
        CONTINUATION
    }
}
