package com.beyond.ymusicapi.response.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public abstract class AbstractParser {
    private ObjectMapper objectMapper;

    protected JsonNode getRootNodeFromStringResponse(String stringResponse) {
        try {
            return objectMapper.readTree(objectMapper.getJsonFactory()
                    .createJsonParser(stringResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
