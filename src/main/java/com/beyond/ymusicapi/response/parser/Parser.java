package com.beyond.ymusicapi.response.parser;

import com.beyond.ymusicapi.response.AbstractResponse;

public interface Parser {
    public AbstractResponse parseResponse(String jsonResponse);
}
