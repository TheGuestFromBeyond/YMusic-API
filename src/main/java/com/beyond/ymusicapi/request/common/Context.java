package com.beyond.ymusicapi.request.common;

public class Context {
    private Client client;

    public Context(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
