package com.beyond.ymusicapi.controller;

import com.beyond.ymusicapi.response.AllSongsResponse;
import com.beyond.ymusicapi.response.ChartResponse;
import com.beyond.ymusicapi.response.NewReleasesResponse;
import com.beyond.ymusicapi.service.YMusicAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1")
public class YMusicController {

    private YMusicAPIService service;

    @GetMapping("/newReleases")
    public NewReleasesResponse getNewReleases(@RequestHeader Map<String, String> headers) {
        return service.getNewReleases(headers);
    }

    @GetMapping("/artist/{artistId}/songs")
    public AllSongsResponse getAllSongsByArtist(@PathVariable String artistId) {
        return service.getAllSongsByArtistId(artistId);
    }

    @GetMapping("/song/{songId}/lyrics")
    public String getSongLyrics(@PathVariable String songId) {
         return service.getLyricsBySongId(songId);
    }

    @GetMapping("/charts/{countryCode}")
    public ChartResponse getChartDataByCountry(@PathVariable String countryCode) {
        return service.getChartDataByCountry(countryCode);
    }

    @GetMapping("/dislike/{songId}")
    public void dislikeSong(@PathVariable String songId, @RequestHeader Map<String, String> headers) {
        service.setDislikeToSong(songId, headers);
    }

    @Autowired
    public void setService(YMusicAPIService service) {
        this.service = service;
    }
}
