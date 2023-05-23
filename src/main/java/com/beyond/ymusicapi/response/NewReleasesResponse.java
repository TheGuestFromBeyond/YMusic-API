package com.beyond.ymusicapi.response;

import com.beyond.ymusicapi.response.model.NewRelease;

import java.util.List;

public class NewReleasesResponse extends AbstractResponse {
    private List<NewRelease> newReleaseList;

    public NewReleasesResponse(List<NewRelease> newReleaseList) {
        this.newReleaseList = newReleaseList;
    }

    public List<NewRelease> getNewReleaseList() {
        return newReleaseList;
    }

    public void setNewReleaseList(List<NewRelease> newReleaseList) {
        this.newReleaseList = newReleaseList;
    }
}
