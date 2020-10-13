package seedu.duke.data;

import seedu.duke.directory.Directory;
import seedu.duke.directory.DirectoryLevel;

public class Module extends Directory {
    private String code;
    private String title;

    public Module() {
    }

    public Module(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSameModule(Module checkModule) {
        String moduleCode = checkModule.getCode();
        String moduleTitle = checkModule.getTitle();
        return this.code.equalsIgnoreCase(moduleCode) && this.title.equalsIgnoreCase(moduleTitle);
    }

    public String toString() {
        return getCode() + ": " + getTitle();
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

//import seedu.duke.directory.Directory;
//import seedu.duke.directory.DirectoryLevel;
//
//public class Module extends Directory {
//
//    @Override
//    public Directory getParent() {
//        return null;
//    }
//
//    @Override
//    public DirectoryLevel getLevel() {
//        return null;
//    }
//}
