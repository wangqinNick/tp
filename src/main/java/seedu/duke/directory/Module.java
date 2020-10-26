package seedu.duke.directory;

import seedu.duke.data.TaskManager;

public class Module extends Directory {
    /** class level parent: root*/
    private static final Root root = new Root();
    private String moduleCode;
    private String title;
    private String description;
    private TaskManager tasks;

    /**
     * Constructs the module but with limited information.
     *
     * @param moduleCode
     *  The module code of the module
     */
    public Module(String moduleCode) {
        this(moduleCode, "", "NIL");
    }

    public Module(String moduleCode, String title, String description) {
        super(root);
        this.moduleCode = moduleCode.toUpperCase();
        this.title = title;
        this.description = description;
        tasks = new TaskManager();
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public TaskManager getTasks() {
        return tasks;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public boolean isSameModule(String moduleCode) {
        return this.moduleCode.equalsIgnoreCase(moduleCode);
    }

    @Override
    public Root getParent() {
        return (Root) this.parent;
    }

    @Override
    public DirectoryLevel getLevel() {
        return DirectoryLevel.MODULE;
    }
}
