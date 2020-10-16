package seedu.duck.data;

public class State {
    private String encodedSavedList;

    public State(String encodedSavedList) {
        this.encodedSavedList = encodedSavedList;
    }

    public String getEncodedSavedList() {
        return encodedSavedList;
    }
}
