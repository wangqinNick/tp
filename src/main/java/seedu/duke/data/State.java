package seedu.duke.data;

public class State {
    private final String encodedSavedList;
    private final String encodedSavedMap;

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
