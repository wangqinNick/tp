package seedu.duke.directory;

public abstract class Directory {
    protected Directory parent;

    /**
     * Constructs the Directory without a parent.
     */
    public Directory() {
        this.parent = null;
    }

    /**
     * Constructs the Directory that has a parent.
     *
     * @param parent
     *  The parent of the class in the Directory
     */
    public Directory(Directory parent) {
        this.parent = parent;
    }

    /**
     * Returns the parent of the class in the Directory.
     *
     * @return
     *  The parent of the class in the Directory
     */
    public abstract Directory getParent();

    /**
     * Returns the corresponding level of the Directory.
     *
     * @return
     *  The level of the Directory
     */
    public abstract DirectoryLevel getLevel();
}
