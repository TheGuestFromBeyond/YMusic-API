package com.beyond.ymusicapi.response;

public class SongLyricsBrowseIdResponse extends AbstractResponse {
    private String browseId;

    public SongLyricsBrowseIdResponse() {
    }

    public SongLyricsBrowseIdResponse(String browseId) {
        this.browseId = browseId;
    }

    public String getBrowseId() {
        return browseId;
    }

    public void setBrowseId(String browseId) {
        this.browseId = browseId;
    }
}
