package seedu.duke.directory;

public enum DirectoryLevel {
    NONE,
    ROOT,
    MODULE,
    TASK;

    public DirectoryLevel next() {
        return values()[ordinal() + 1];
    }
}
