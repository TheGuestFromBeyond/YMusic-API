package com.beyond.ymusicapi.request.body;

import com.beyond.ymusicapi.request.common.Target;

public class RatingBody extends CommonBody {
    private Target target;

    public RatingBody(String videoId) {
        this.target = new Target(videoId);
    }

    public Target getTarget() {
        return target;
    }
}
