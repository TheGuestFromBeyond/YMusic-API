package com.beyond.ymusicapi.request.body;

import com.beyond.ymusicapi.request.common.FormData;

public class ChartBody extends CommonBody {
    private String params;
    private FormData formData;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public FormData getFormData() {
        return formData;
    }

    public void setFormData(FormData formData) {
        this.formData = formData;
    }
}
