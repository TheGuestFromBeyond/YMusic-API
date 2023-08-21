package com.beyond.ymusicapi.request;

import com.beyond.ymusicapi.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class RequestHelper {

    ServiceConfig serviceConfig;

    public String generateApiUrl(RequestOperation operation) {
        return generateApiUrl(operation, null);
    }

    public String generateApiUrl(RequestOperation operation, LinkedList<String> params) {
        StringBuilder url = new StringBuilder(serviceConfig.getYoutubeUrl());
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
            case DISLIKE -> url.append("like/dislike?");
            case LIKE -> url.append("like/like?");
            case LYRICS -> url.append("next?");

        }
        url
                .append("prettyPrint")
                .append(serviceConfig.getPrettyPrint())
                .append("&key")
                .append(serviceConfig.getKey());

        return url.toString();
    }

    public enum RequestOperation {
        COMMON_OPERATION,
        CONTINUATION,
        DISLIKE,
        LIKE,
        LYRICS
    }

    @Autowired
    public void setServiceConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }
}
