package com.beyond.ymusicapi.response;

import java.util.List;

public class ChartResponse extends AbstractResponse {
    private List<String> chartArtistsIds;
    private List<String> chartSongsIds;

    public List<String> getChartArtistsIds() {
        return chartArtistsIds;
    }

    public void setChartArtistsIds(List<String> chartArtistsIds) {
        this.chartArtistsIds = chartArtistsIds;
    }

    public List<String> getChartSongsIds() {
        return chartSongsIds;
    }

    public void setChartSongsIds(List<String> chartSongsIds) {
        this.chartSongsIds = chartSongsIds;
    }
}
