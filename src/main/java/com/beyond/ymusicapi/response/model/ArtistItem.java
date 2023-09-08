package com.beyond.ymusicapi.response.model;

public class ArtistItem {
    private String youtubeArtistId;
    private String artistName;

    public ArtistItem(String youtubeArtistId, String artistName) {
        this.youtubeArtistId = youtubeArtistId;
        this.artistName = artistName;
    }

    public String getYoutubeArtistId() {
        return youtubeArtistId;
    }

    public void setYoutubeArtistId(String youtubeArtistId) {
        this.youtubeArtistId = youtubeArtistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
