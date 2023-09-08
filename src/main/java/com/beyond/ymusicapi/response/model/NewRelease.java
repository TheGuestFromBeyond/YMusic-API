package com.beyond.ymusicapi.response.model;

public class NewRelease {
    private String releaseName;
    private ArtistItem artist;

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public ArtistItem getArtist() {
        return artist;
    }

    public void setArtist(ArtistItem artist) {
        this.artist = artist;
    }
}
