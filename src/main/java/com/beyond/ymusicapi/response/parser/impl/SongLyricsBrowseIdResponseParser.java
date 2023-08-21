package com.beyond.ymusicapi.response.parser.impl;

import com.beyond.ymusicapi.response.AbstractResponse;
import com.beyond.ymusicapi.response.SongLyricsBrowseIdResponse;
import com.beyond.ymusicapi.response.parser.AbstractParser;
import com.beyond.ymusicapi.response.parser.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public class SongLyricsBrowseIdResponseParser extends AbstractParser implements Parser {
    @Override
    public AbstractResponse parseResponse(String jsonResponse) {
        JsonNode rootNode = getRootNodeFromStringResponse(jsonResponse);
        String browseId = "";
        for (JsonNode tabRenderer : rootNode.findValues("tabRenderer")) {
            if (tabRenderer.findValue("pageType").asText().equals("MUSIC_PAGE_TYPE_TRACK_LYRICS")) {
                browseId = tabRenderer.findValue("browseId").asText();
            }
        }
        return new SongLyricsBrowseIdResponse(browseId);
    }

    @Override
    public Class getParserResponseType() {
        return SongLyricsBrowseIdResponseParser.class;
    }
}
