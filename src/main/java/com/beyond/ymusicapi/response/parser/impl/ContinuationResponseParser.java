package com.beyond.ymusicapi.response.parser.impl;

import com.beyond.ymusicapi.response.AbstractResponse;
import com.beyond.ymusicapi.response.ContinuationPlaylistResponse;
import com.beyond.ymusicapi.response.parser.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class ContinuationResponseParser extends AllSongsResponseParser implements Parser {
    @Override
    public AbstractResponse parseResponse(String jsonResponse) {
        JsonNode rootNode = getRootNodeFromStringResponse(jsonResponse);
        JsonNode playlistShelfRenderer = rootNode.findValues("musicPlaylistShelfContinuation").get(0);

        ContinuationPlaylistResponse response = new ContinuationPlaylistResponse();
        response.setSongItems(parseSongs(playlistShelfRenderer));

        return response;
    }

    @Override
    public Class getParserResponseType() {
        return ContinuationPlaylistResponse.class;
    }
}
