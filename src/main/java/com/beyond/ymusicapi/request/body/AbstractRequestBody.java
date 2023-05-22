package com.beyond.ymusicapi.request.body;

import com.beyond.ymusicapi.request.common.Context;

public abstract class AbstractRequestBody {
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
