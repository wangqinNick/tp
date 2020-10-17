package seedu.duke.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class State {
    private String encodedSavedList;
    private String encodedSavedMap;

    public State(String encodedSavedList, String encodedSavedMap) {
        this.encodedSavedList = encodedSavedList;
        this.encodedSavedMap = encodedSavedMap;
    }

    public String getEncodedSavedList() {
        return encodedSavedList;
    }

    public String getEncodedSavedMap() {
        return encodedSavedMap;
    }
}
