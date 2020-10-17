package seedu.duke.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class State {
    private String encodedSavedList;
    private String encodedSavedMapList;

    public State(String encodedSavedList, String encodedSavedMapList) {
        this.encodedSavedList = encodedSavedList;
        this.encodedSavedMapList = encodedSavedMapList;
    }

    public String getEncodedSavedList() {
        return encodedSavedList;
    }

    public String getEncodedSavedMapList() {
        return encodedSavedMapList;
    }
}
