package com.beyond.ymusicapi.request;

import com.beyond.ymusicapi.auth.AuthHelper;
import com.beyond.ymusicapi.request.body.AbstractRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestProvider {

    private AuthHelper authHelper;
    private OkHttpClient httpClient;
    private ObjectMapper objectMapper;

    public String doRequest(String url, AbstractRequestBody body) {
        String jsonBody = getRequestBodyJsonString(body);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(authHelper.getHttpHeaders()))
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
    public void setAuthHelper(AuthHelper authHelper) {
        this.authHelper = authHelper;
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
