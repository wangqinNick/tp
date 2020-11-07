package seedu.ravi.data;

import java.util.ArrayList;

public class State {
    private final String encodedSavedList;
    private final String encodedSavedMap;
    private final ArrayList<String> commandArrayList;

    public State(String encodedSavedList, String encodedSavedMap, ArrayList<String> commandArrayList) {
        this.encodedSavedList = encodedSavedList;
        this.encodedSavedMap = encodedSavedMap;
        this.commandArrayList = commandArrayList;
    }

    public String getEncodedSavedList() {
        return encodedSavedList;
    }

    public String getEncodedSavedMap() {
        return encodedSavedMap;
    }

    public ArrayList<String> getEditTypeCommandArrayList() {
        return commandArrayList;
    }
}
