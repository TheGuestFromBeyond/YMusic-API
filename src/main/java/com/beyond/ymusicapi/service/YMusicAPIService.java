package com.beyond.ymusicapi.service;

import com.beyond.ymusicapi.request.RequestHelper;
import com.beyond.ymusicapi.request.RequestProvider;
import com.beyond.ymusicapi.request.body.NewReleasesBody;
import com.beyond.ymusicapi.request.common.Context;
import com.beyond.ymusicapi.response.NewReleasesResponse;
import com.beyond.ymusicapi.response.parser.impl.NewReleasesResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YMusicAPIService {
    private RequestProvider requestProvider;
    private RequestHelper requestHelper;
    private NewReleasesResponseParser parser;
    private Context context;

    public NewReleasesResponse getNewReleases() {
        String apiUrl = requestHelper.generateApiUrl(RequestHelper.RequestOperation.NEW_RELEASES);
        NewReleasesBody body = new NewReleasesBody();
        body.setContext(context);
        body.setBrowseId("FEmusic_new_releases_albums");

        String jsonResponse = requestProvider.doRequest(apiUrl, body);

        return (NewReleasesResponse) parser.parseResponse(jsonResponse);
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
    public void setParser(NewReleasesResponseParser parser) {
        this.parser = parser;
    }

    @Autowired
    public void setContext(Context context) {
        this.context = context;
    }
}
