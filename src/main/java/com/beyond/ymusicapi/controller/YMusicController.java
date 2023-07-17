package com.beyond.ymusicapi.controller;

import com.beyond.ymusicapi.response.AllSongsResponse;
import com.beyond.ymusicapi.response.NewReleasesResponse;
import com.beyond.ymusicapi.service.YMusicAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YMusicController {

    private YMusicAPIService service;

    @GetMapping("/newReleases")
    public NewReleasesResponse getNewReleases() {
        return service.getNewReleases();
    }

    @GetMapping("/artist/{artistId}/songs")
    public AllSongsResponse getAllSongsByArtist(@PathVariable String artistId) {
        return service.getAllSongsByArtistId(artistId);
    }

    @Autowired
    public void setService(YMusicAPIService service) {
        this.service = service;
    }
}
