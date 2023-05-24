package com.beyond.ymusicapi.response.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResponseParserFactory {

    private Map<Class, Parser> typeToParserMap = new HashMap<>();

    @Autowired
    public ResponseParserFactory(List<Parser> parsers) {
        for (Parser parser : parsers) {
            typeToParserMap.put(parser.getParserResponseType(), parser);
        }
    }

    public Parser getResponseParser(Class response) {
        return typeToParserMap.get(response);
    }
}
