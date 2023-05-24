package com.beyond.ymusicapi.response;

import com.beyond.ymusicapi.response.model.SongItem;

import java.util.List;

public class AllSongsResponse extends AbstractResponse {
    private ContinuationData continuationData;
    private List<SongItem> songItems;

    public ContinuationData getContinuationData() {
        return continuationData;
    }

    public void setContinuationData(ContinuationData continuationData) {
        this.continuationData = continuationData;
    }

    public List<SongItem> getSongItems() {
        return songItems;
    }

    public void setSongItems(List<SongItem> songItems) {
        this.songItems = songItems;
    }

    public static class ContinuationData {
        String continuationToken;
        String clickTrackingParams;

        public String getContinuationToken() {
            return continuationToken;
        }

        public void setContinuationToken(String continuationToken) {
            this.continuationToken = continuationToken;
        }

        public String getClickTrackingParams() {
            return clickTrackingParams;
        }

        public void setClickTrackingParams(String clickTrackingParams) {
            this.clickTrackingParams = clickTrackingParams;
        }
    }
}
