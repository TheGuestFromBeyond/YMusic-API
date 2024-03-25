package com.beyond.ymusicapi.response.parser.impl;

import com.beyond.ymusicapi.response.AbstractResponse;
import com.beyond.ymusicapi.response.AllSongsResponse;
import com.beyond.ymusicapi.response.model.SongItem;
import com.beyond.ymusicapi.response.parser.AbstractParser;
import com.beyond.ymusicapi.response.parser.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllSongsResponseParser extends AbstractParser implements Parser {
    @Override
    public AbstractResponse parseResponse(String jsonResponse) {
        JsonNode rootNode = getRootNodeFromStringResponse(jsonResponse);
        JsonNode playlistShelfRenderer = rootNode.findValues("musicPlaylistShelfRenderer").get(0);

        AllSongsResponse response = new AllSongsResponse();
        response.setSongItems(parseSongs(playlistShelfRenderer));

        JsonNode continuations = playlistShelfRenderer.findValue("continuations");
        if (continuations != null) {
            AllSongsResponse.ContinuationData continuationData = new AllSongsResponse.ContinuationData();
            continuationData.setContinuationToken(continuations.get(0).findValue("continuation").asText());
            continuationData.setClickTrackingParams(continuations.get(0).findValue("clickTrackingParams").asText());
            response.setContinuationData(continuationData);
        }

        return response;
    }

    protected List<SongItem> parseSongs(JsonNode playlistShelfRenderer) {
        ArrayNode songItemsArray = (ArrayNode) playlistShelfRenderer.findValue("contents");
        if (songItemsArray == null) {
            return new ArrayList<>();
        }
        List<SongItem> songItems = new ArrayList<>();
        for (JsonNode songItemNode : songItemsArray) {
            SongItem songItem = new SongItem();
            songItem.setSongName(songItemNode.findValue("runs").findValue("text").asText());
            songItem.setSongId(songItemNode.findValue("videoId").asText());
            songItem.setPlaylistId(songItemNode.findValue("playlistId").asText());
            songItems.add(songItem);
        }
        return songItems;
    }

    @Override
    public Class getParserResponseType() {
        return AllSongsResponse.class;
    }
}
