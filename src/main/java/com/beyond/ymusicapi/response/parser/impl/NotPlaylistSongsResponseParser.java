package com.beyond.ymusicapi.response.parser.impl;

import com.beyond.ymusicapi.response.AbstractResponse;
import com.beyond.ymusicapi.response.NotPlaylistAllSongResponse;
import com.beyond.ymusicapi.response.model.SongItem;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotPlaylistSongsResponseParser extends AllSongsResponseParser {
    @Override
    public AbstractResponse parseResponse(String jsonResponse) {
        JsonNode rootNode = getRootNodeFromStringResponse(jsonResponse);

        NotPlaylistAllSongResponse response = new NotPlaylistAllSongResponse();
        response.setSongItems(parseSongs(rootNode));

        return response;
    }

    @Override
    protected List<SongItem> parseSongs(JsonNode rootNode) {
        ArrayNode songItemsArray = (ArrayNode) rootNode.findValue("content").findValue("contents").get(0).findValue("contents");
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
        return NotPlaylistAllSongResponse.class;
    }
}
