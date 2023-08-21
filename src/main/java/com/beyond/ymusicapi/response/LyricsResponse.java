package com.beyond.ymusicapi.response;

public class LyricsResponse extends AbstractResponse {
    private String songLyrics;

    public LyricsResponse() {
    }

    public LyricsResponse(String songLyrics) {
        this.songLyrics = songLyrics;
    }

    public String getSongLyrics() {
        return songLyrics;
    }

    public void setSongLyrics(String songLyrics) {
        this.songLyrics = songLyrics;
    }
}
