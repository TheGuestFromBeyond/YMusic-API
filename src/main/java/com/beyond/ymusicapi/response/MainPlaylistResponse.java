package com.beyond.ymusicapi.response;

public class MainPlaylistResponse extends AbstractResponse {
    private String artistId;
    private String playlistId;

    public MainPlaylistResponse(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
