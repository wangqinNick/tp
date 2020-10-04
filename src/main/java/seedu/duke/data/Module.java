package seedu.duke.data;

import seedu.duke.directory.Directory;
import seedu.duke.directory.DirectoryLevel;

public class Module extends Directory {
    private String moduleCode;
    private String title;

    public Module(String code) {
        this.moduleCode = code;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String code) {
        this.moduleCode = code;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Edits the title of the module.
     *
     * @param title
     *  The title of the module
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Checks if one module has the same module code as another.
     *
     * @param moduleCode
     *  The module code to check
     * @return
     *  <code>TRUE</code> if they are the same, and <code>FALSE</code> otherwise
     */
    public boolean isSameModule(String moduleCode) {
        return this.moduleCode.equalsIgnoreCase(moduleCode);
    }

    @Override
    public Directory getParent() {
        return null;
    }

    @Override
    public DirectoryLevel getLevel() {
        return null;
    }
}

