package com.beyond.ymusicapi.request.common;

import org.springframework.beans.factory.annotation.Value;

public class Client {
    @Value("${client.name}")
    private String clientName;

    @Value("${client.version}")
    private String clientVersion;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }
}
