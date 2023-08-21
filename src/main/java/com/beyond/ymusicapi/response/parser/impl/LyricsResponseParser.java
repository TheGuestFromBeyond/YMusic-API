package com.beyond.ymusicapi.response.parser.impl;

import com.beyond.ymusicapi.response.AbstractResponse;
import com.beyond.ymusicapi.response.LyricsResponse;
import com.beyond.ymusicapi.response.parser.AbstractParser;
import com.beyond.ymusicapi.response.parser.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public class LyricsResponseParser extends AbstractParser implements Parser {
    @Override
    public AbstractResponse parseResponse(String jsonResponse) {
        JsonNode rootNode = getRootNodeFromStringResponse(jsonResponse);
        String lyrics = "";
        JsonNode renderer = rootNode.findValue("musicDescriptionShelfRenderer");
        if (renderer != null) {
            lyrics = renderer.findValue("text").asText();
        } else {
            lyrics = rootNode.findValue("runs").findValue("text").asText();
        }

        return new LyricsResponse(lyrics);
    }

    @Override
    public Class getParserResponseType() {
        return LyricsResponseParser.class;
    }
}
