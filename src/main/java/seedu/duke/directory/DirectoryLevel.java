package seedu.duke.directory;

public enum DirectoryLevel {
    NONE,
    ROOT,
    MODULE,
    TASK,

    TAG {
        @Override
        public DirectoryLevel next() {
            return NONE;
        }
    };

    public DirectoryLevel next() {
        return values()[ordinal() + 1];
    }
}
