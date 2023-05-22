package com.beyond.ymusicapi.request.body;

public class SearchRequestBody extends AbstractRequestBody {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
