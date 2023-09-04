package com.beyond.ymusicapi.service;

import com.beyond.ymusicapi.request.RequestHelper;
import com.beyond.ymusicapi.request.RequestProvider;
import com.beyond.ymusicapi.request.body.*;
import com.beyond.ymusicapi.request.common.Context;
import com.beyond.ymusicapi.request.common.FormData;
import com.beyond.ymusicapi.response.*;
import com.beyond.ymusicapi.response.parser.ResponseParserFactory;
import com.beyond.ymusicapi.response.parser.impl.LyricsResponseParser;
import com.beyond.ymusicapi.response.parser.impl.SongLyricsBrowseIdResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;

@Service
public class YMusicAPIService {
    private RequestProvider requestProvider;
    private RequestHelper requestHelper;
    private ResponseParserFactory parserFactory;
    private Context context;

    public NewReleasesResponse getNewReleases() {
        String apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.COMMON_OPERATION);
        NewReleasesBody body = new NewReleasesBody();
        body.setContext(context);
        body.setBrowseId("FEmusic_new_releases_albums");

        String jsonResponse = requestProvider.doRequest(apiUrl, body);

        return (NewReleasesResponse) parserFactory.getResponseParser(NewReleasesResponse.class).parseResponse(jsonResponse);
    }

    public AllSongsResponse getAllSongsByArtistId(String artistId) {
        MainPlaylistResponse mainPlaylist = getMainPlaylistByArtistId(artistId);
        if (mainPlaylist.getPlaylistId() == null) {
            return null;
        }
        String apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.COMMON_OPERATION);
        CommonBody body = new CommonBody();
        body.setContext(context);
        body.setBrowseId(mainPlaylist.getPlaylistId());

        String jsonResponse = requestProvider.doRequest(apiUrl, body);
        AllSongsResponse response = (AllSongsResponse) parserFactory.getResponseParser(AllSongsResponse.class).parseResponse(jsonResponse);
        if (response.getContinuationData() != null) {
            response.getSongItems().addAll(getContinuationContent(response).getSongItems());
        }
        return response;
    }

    public void setDislikeToSong(String songId) {
        String apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.DISLIKE);
        RatingBody body = new RatingBody(songId);
        body.setContext(context);

        requestProvider.doRequest(apiUrl, body);
    }

    public void setLikeToSong(String songId) {
        String apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.LIKE);
        RatingBody body = new RatingBody(songId);
        body.setContext(context);

        requestProvider.doRequest(apiUrl, body);
    }

    public String getLyricsBySongId(String songId) {
        String apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.LYRICS);
        LyricsBody body = new LyricsBody();
        body.setContext(context);
        body.setVideoId(songId);

        String jsonResponse = requestProvider.doRequest(apiUrl, body);
        String browseId = ((SongLyricsBrowseIdResponse) parserFactory.getResponseParser(SongLyricsBrowseIdResponseParser.class).parseResponse(jsonResponse)).getBrowseId();

        apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.COMMON_OPERATION);
        CommonBody commonBody = new CommonBody();
        commonBody.setContext(context);
        commonBody.setBrowseId(browseId);

        jsonResponse = requestProvider.doRequest(apiUrl, commonBody);
        return ((LyricsResponse) parserFactory.getResponseParser(LyricsResponseParser.class).parseResponse(jsonResponse)).getSongLyrics();
    }

    private MainPlaylistResponse getMainPlaylistByArtistId(String artistId) {
        String apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.COMMON_OPERATION);
        CommonBody body = new CommonBody();
        body.setContext(context);
        body.setBrowseId(artistId);

        String jsonResponse = requestProvider.doRequest(apiUrl, body);
        MainPlaylistResponse mainPlaylistResponse = (MainPlaylistResponse) parserFactory.getResponseParser(MainPlaylistResponse.class).parseResponse(jsonResponse);
        mainPlaylistResponse.setArtistId(artistId);
        return mainPlaylistResponse;
    }

    private ContinuationPlaylistResponse getContinuationContent(AllSongsResponse previousResponse) {
        LinkedList<String> urlParams = new LinkedList<>();
        urlParams.add(previousResponse.getContinuationData().getContinuationToken());
        urlParams.add(previousResponse.getContinuationData().getClickTrackingParams());
        String apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.CONTINUATION, urlParams);
        CommonBody body = new CommonBody();
        body.setContext(context);

        String jsonResponse = requestProvider.doRequest(apiUrl, body);
        return (ContinuationPlaylistResponse) parserFactory.getResponseParser(ContinuationPlaylistResponse.class).parseResponse(jsonResponse);
    }

    public ChartResponse getChartDataByCountry(String countryCode) {
        String apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.COMMON_OPERATION);
        ChartBody body = new ChartBody();
        body.setContext(context);
        body.setBrowseId("FEmusic_charts");
        body.setParams("sgYMRkVtdXNpY19ob21l");
        body.setFormData(new FormData(Collections.singletonList(countryCode)));

        String jsonResponse = requestProvider.doRequest(apiUrl, body);
        return (ChartResponse) parserFactory.getResponseParser(ChartResponse.class).parseResponse(jsonResponse);
    }

    @Autowired
    public void setRequestProvider(RequestProvider requestProvider) {
        this.requestProvider = requestProvider;
    }

    @Autowired
    public void setRequestHelper(RequestHelper requestHelper) {
        this.requestHelper = requestHelper;
    }

    @Autowired
    public void setParserFactory(ResponseParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Autowired
    public void setContext(Context context) {
        this.context = context;
    }
}
