package com.beyond.ymusicapi.request;

import com.beyond.ymusicapi.request.body.AbstractRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RequestProvider {
    private OkHttpClient httpClient;
    private ObjectMapper objectMapper;

    public String doRequest(String url, AbstractRequestBody body) {
        return doRequest(url, body, new HashMap<>());
    }

    public String doRequest(String url, AbstractRequestBody body, Map<String, String> headers) {
        List<String> neededHeaders = new ArrayList<>();
        neededHeaders.add("accept");
        neededHeaders.add("content-type");
        neededHeaders.add("accept-language");
        neededHeaders.add("authorization");
        neededHeaders.add("cookie");
        neededHeaders.add("origin");

        headers = headers.entrySet().stream()
                .filter(entry -> neededHeaders.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        String jsonBody = getRequestBodyJsonString(body);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(RequestBody.create(JSON, jsonBody))
                .build();

        String stringResponse = "";
        try {
            Response response = httpClient.newCall(request).execute();
            stringResponse = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringResponse;
    }

    private String getRequestBodyJsonString(AbstractRequestBody body) {
        String jsonBodyString = "";
        try {
            jsonBodyString = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonBodyString;
    }

    @Autowired
    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
