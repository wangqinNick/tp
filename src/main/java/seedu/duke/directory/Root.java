package seedu.duke.directory;

public class Root extends Directory {
    @Override
    public Directory getParent() {
        return null;
    }

    @Override
    public DirectoryLevel getLevel() {
        return null;
    }
}
