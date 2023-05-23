package com.beyond.ymusicapi.request.body;

public class NewReleasesBody extends AbstractRequestBody {
    private String browseId;

    public String getBrowseId() {
        return browseId;
    }

    public void setBrowseId(String browseId) {
        this.browseId = browseId;
    }
}
