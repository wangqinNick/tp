package seedu.duke.util;

/**
 * This class is solely for loading bunch of modules from a json file
 * and to be converted into objects of DummyModules, for users to enrol into.
 */
public class DummyModule {
    private String moduleCode;
    private String title;

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }
}
