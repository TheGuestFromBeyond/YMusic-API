package com.beyond.ymusicapi.response.parser;

import com.beyond.ymusicapi.response.AbstractResponse;

public interface Parser {
    AbstractResponse parseResponse(String jsonResponse);

    Class getParserResponseType();
}
