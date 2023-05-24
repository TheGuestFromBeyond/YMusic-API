package com.beyond.ymusicapi.service;

import com.beyond.ymusicapi.request.RequestHelper;
import com.beyond.ymusicapi.request.RequestProvider;
import com.beyond.ymusicapi.request.body.CommonBody;
import com.beyond.ymusicapi.request.body.NewReleasesBody;
import com.beyond.ymusicapi.request.common.Context;
import com.beyond.ymusicapi.response.AllSongsResponse;
import com.beyond.ymusicapi.response.MainPlaylistResponse;
import com.beyond.ymusicapi.response.NewReleasesResponse;
import com.beyond.ymusicapi.response.parser.ResponseParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private AllSongsResponse getContinuationContent(AllSongsResponse previousResponse) {
        LinkedList<String> urlParams = new LinkedList<>();
        urlParams.add(previousResponse.getContinuationData().getContinuationToken());
        urlParams.add(previousResponse.getContinuationData().getClickTrackingParams());
        String apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.CONTINUATION, urlParams);
        CommonBody body = new CommonBody();
        body.setContext(context);

        String jsonResponse = requestProvider.doRequest(apiUrl, body);
        return (AllSongsResponse) parserFactory.getResponseParser(AllSongsResponse.class).parseResponse(jsonResponse);
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
