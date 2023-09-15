package com.beyond.ymusicapi.response.parser.impl;

import com.beyond.ymusicapi.response.AbstractResponse;
import com.beyond.ymusicapi.response.NewReleasesResponse;
import com.beyond.ymusicapi.response.model.ArtistItem;
import com.beyond.ymusicapi.response.model.NewRelease;
import com.beyond.ymusicapi.response.parser.AbstractParser;
import com.beyond.ymusicapi.response.parser.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewReleasesResponseParser extends AbstractParser implements Parser {

    @Override
    public AbstractResponse parseResponse(String jsonResponse) {
        JsonNode rootNode = getRootNodeFromStringResponse(jsonResponse);
        List<NewRelease> newReleaseList = new ArrayList<>();

        ArrayNode contentsNode = (ArrayNode) rootNode.findValue("sectionListRenderer").findValue("contents");
        for (JsonNode contentNode : contentsNode) {
            if ("New releases".equals(contentNode.findValue("accessibilityData").findValue("label").asText())) {
                for (JsonNode jsonNode : contentNode) {
                    try {
                        String releaseName = jsonNode.findValue("title").findValue("text").asText();

                        List<TextNode> subtitleList = (ArrayList) jsonNode.findValue("subtitle").findValues("text");
                        JsonNode artistIdNode = jsonNode.findValue("subtitle").findValue("browseId");
                        String artistId = "";
                        if (artistIdNode != null) {
                            artistId = artistIdNode.asText();
                        }

                        String artistName = ((TextNode) ((ArrayList) jsonNode.findValue("subtitle").findValues("text")).get(subtitleList.size() - 1)).asText();

                        NewRelease newRelease = new NewRelease();
                        newRelease.setArtist(new ArtistItem(artistId, artistName));
                        newRelease.setReleaseName(releaseName);

                        newReleaseList.add(newRelease);
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }
                break;
            }
        }

        return new NewReleasesResponse(newReleaseList);
    }

    @Override
    public Class getParserResponseType() {
        return NewReleasesResponse.class;
    }
}
