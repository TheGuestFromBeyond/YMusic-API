package com.beyond.ymusicapi.response.parser.impl;

import com.beyond.ymusicapi.response.AbstractResponse;
import com.beyond.ymusicapi.response.ChartResponse;
import com.beyond.ymusicapi.response.parser.AbstractParser;
import com.beyond.ymusicapi.response.parser.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChartResponseParser extends AbstractParser implements Parser {
    @Override
    public AbstractResponse parseResponse(String jsonResponse) {
        JsonNode rootNode = getRootNodeFromStringResponse(jsonResponse);
        ChartResponse response = new ChartResponse();

        List<JsonNode> shelfRenderers = rootNode.findValues("musicCarouselShelfRenderer");
        for (JsonNode shelfRenderer : shelfRenderers) {
            if ("Top artists".equals(shelfRenderer.findValue("label").asText())) {
                response.setChartArtistsIds(getTopArtistsIds(shelfRenderer));
            } else if ("Trending".equals(shelfRenderer.findValue("label").asText())) {
                response.setChartSongsIds(getTopSongsIds(shelfRenderer));
            }
        }

        return response;
    }

    private List<String> getTopArtistsIds(JsonNode shelfRenderer) {
        List<String> topArtistsIds = new ArrayList<>();
        ArrayNode artistItemsArray = (ArrayNode) shelfRenderer.findValue("contents");
        for (JsonNode artistNode : artistItemsArray) {
            topArtistsIds.add(artistNode.findValue("browseId").asText());
        }
        return topArtistsIds;
    }

    private List<String> getTopSongsIds(JsonNode shelfRenderer) {
        List<String> topSongsIds = new ArrayList<>();
        ArrayNode songItemsArray = (ArrayNode) shelfRenderer.findValue("contents");
        for (JsonNode songNode : songItemsArray) {
            topSongsIds.add(songNode.findValue("videoId").asText());
        }
        return topSongsIds;
    }

    @Override
    public Class getParserResponseType() {
        return ChartResponse.class;
    }
}
