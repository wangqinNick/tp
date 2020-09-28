package seedu.duke.directory;

public class Root extends Directory {
    public Root() {
        super();
    }

    @Override
    public Directory getParent() {
        return null;
    }

    @Override
    public DirectoryLevel getLevel() {
        return DirectoryLevel.ROOT;
    }
}
