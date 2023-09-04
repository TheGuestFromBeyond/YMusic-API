package com.beyond.ymusicapi.request.common;

import java.util.List;

public class FormData {
    private List<String> selectedValues;

    public FormData(List<String> selectedValues) {
        this.selectedValues = selectedValues;
    }

    public List<String> getSelectedValues() {
        return selectedValues;
    }
}
