package com.beyond.ymusicapi.response.parser.impl;

import com.beyond.ymusicapi.response.AbstractResponse;
import com.beyond.ymusicapi.response.MainPlaylistResponse;
import com.beyond.ymusicapi.response.parser.AbstractParser;
import com.beyond.ymusicapi.response.parser.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class MainPlaylistResponseParser extends AbstractParser implements Parser {

    @Override
    public AbstractResponse parseResponse(String jsonResponse) {
        JsonNode rootNode = getRootNodeFromStringResponse(jsonResponse);

        List<JsonNode> musicShelfRenderers = rootNode.findValues("musicShelfRenderer");
        if (CollectionUtils.isEmpty(musicShelfRenderers)) {
            return new MainPlaylistResponse();
        }
        JsonNode container = musicShelfRenderers.get(0).findValue("runs");
        String mainPlaylistId = "";
        if ("Songs".equals(container.findValue("text").asText())) {
            JsonNode playlistNode = musicShelfRenderers.get(0).findValue("runs").findValue("browseId");

            mainPlaylistId = playlistNode == null ? null : playlistNode.asText();
        }

        return new MainPlaylistResponse(mainPlaylistId);
    }

    @Override
    public Class getParserResponseType() {
        return MainPlaylistResponse.class;
    }
}
