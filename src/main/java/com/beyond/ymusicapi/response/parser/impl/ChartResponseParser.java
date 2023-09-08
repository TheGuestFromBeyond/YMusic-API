package com.beyond.ymusicapi.response.parser.impl;

import com.beyond.ymusicapi.response.AbstractResponse;
import com.beyond.ymusicapi.response.ChartResponse;
import com.beyond.ymusicapi.response.model.ArtistItem;
import com.beyond.ymusicapi.response.model.SongItem;
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
                response.setChartArtists(getTopArtists(shelfRenderer));
            } else if ("Trending".equals(shelfRenderer.findValue("label").asText())) {
                response.setChartSongs(getTopSongs(shelfRenderer));
            }
        }

        return response;
    }

    private List<ArtistItem> getTopArtists(JsonNode shelfRenderer) {
        List<ArtistItem> topArtists = new ArrayList<>();
        ArrayNode artistItemsArray = (ArrayNode) shelfRenderer.findValue("contents");
        for (JsonNode artistNode : artistItemsArray) {
            String artistId = artistNode.findValue("browseId").asText();
            String artistName = artistNode.findValue("musicResponsiveListItemFlexColumnRenderer").findValue("runs").findValue("text").asText();
            topArtists.add(new ArtistItem(artistId, artistName));
        }
        return topArtists;
    }

    private List<SongItem> getTopSongs(JsonNode shelfRenderer) {
        List<SongItem> topSongs = new ArrayList<>();
        ArrayNode songItemsArray = (ArrayNode) shelfRenderer.findValue("contents");
        for (JsonNode songNode : songItemsArray) {
            String songId = songNode.findValue("videoId").asText();
            List<JsonNode> values = songNode.findValue("flexColumns").findValues("musicResponsiveListItemFlexColumnRenderer");
            String artistId = null;
            String artistName = null;
            for (JsonNode value : values) {
                if (value.findValue("pageType") != null && "MUSIC_PAGE_TYPE_ARTIST".equals(value.findValue("pageType").asText())) {
                    artistId = value.findValue("browseId").asText();
                    artistName = value.findValue("runs").findValue("text").asText();
                }
            }
            topSongs.add(new SongItem(songId, new ArtistItem(artistId, artistName)));
        }
        return topSongs;
    }

    @Override
    public Class getParserResponseType() {
        return ChartResponse.class;
    }
}
