package com.beyond.ymusicapi.response;

import com.beyond.ymusicapi.response.model.ArtistItem;
import com.beyond.ymusicapi.response.model.SongItem;

import java.util.List;

public class ChartResponse extends AbstractResponse {
    private List<ArtistItem> chartArtists;
    private List<SongItem> chartSongs;

    public List<ArtistItem> getChartArtists() {
        return chartArtists;
    }

    public void setChartArtists(List<ArtistItem> chartArtists) {
        this.chartArtists = chartArtists;
    }

    public List<SongItem> getChartSongs() {
        return chartSongs;
    }

    public void setChartSongs(List<SongItem> chartSongs) {
        this.chartSongs = chartSongs;
    }
}
