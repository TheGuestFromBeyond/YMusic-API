package com.beyond.ymusicapi.response.parser.impl;

import com.beyond.ymusicapi.response.AbstractResponse;
import com.beyond.ymusicapi.response.MainPlaylistResponse;
import com.beyond.ymusicapi.response.parser.AbstractParser;
import com.beyond.ymusicapi.response.parser.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class MainPlaylistResponseParser extends AbstractParser implements Parser {

    @Override
    public AbstractResponse parseResponse(String jsonResponse) {
        JsonNode rootNode = getRootNodeFromStringResponse(jsonResponse);

        JsonNode container = rootNode.findValues("musicShelfRenderer").get(0).findValue("runs");
        String mainPlaylistId = "";
        if ("Songs".equals(container.findValue("text").asText())) {
            mainPlaylistId = rootNode.findValues("musicShelfRenderer").get(0).findValue("runs").findValue("browseId").asText();
        }

        return new MainPlaylistResponse(mainPlaylistId);
    }

    @Override
    public Class getParserResponseType() {
        return MainPlaylistResponse.class;
    }
}
