package seedu.ravi.data;

import java.util.ArrayList;

public class State {
    private final String encodedSavedList;
    private final String encodedSavedMap;
    private final ArrayList<String> commandArrayList;
    private final String encodedTimeTable;

    public State(String encodedSavedList, String encodedSavedMap, ArrayList<String> commandArrayList, String encodedTimeTable) {
        this.encodedSavedList = encodedSavedList;
        this.encodedSavedMap = encodedSavedMap;
        this.commandArrayList = commandArrayList;
        this.encodedTimeTable = encodedTimeTable;
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

    public String getEncodedTimeTable() {
        return encodedTimeTable;
    }
}
