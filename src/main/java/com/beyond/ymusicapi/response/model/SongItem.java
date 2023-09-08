package com.beyond.ymusicapi.response.model;

public class SongItem {
    private String songName;
    private String songId;
    private ArtistItem artist;
    private String playlistId;

    public SongItem() {
    }

    public SongItem(String songId, ArtistItem artist) {
        this.songId = songId;
        this.artist = artist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public ArtistItem getArtist() {
        return artist;
    }

    public void setArtist(ArtistItem artist) {
        this.artist = artist;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
