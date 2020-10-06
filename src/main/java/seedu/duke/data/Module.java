package seedu.duke.data;

import seedu.duke.directory.Directory;
import seedu.duke.directory.DirectoryLevel;

public class Module extends Directory {
    private String code;
    private String title;

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

    public boolean isSameModule(String moduleCode) {
        return this.code.equalsIgnoreCase(moduleCode);
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
