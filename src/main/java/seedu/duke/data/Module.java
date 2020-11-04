package seedu.duke.data;

import seedu.duke.directory.Directory;
import seedu.duke.directory.DirectoryLevel;

public class Module extends Directory {
    private String moduleCode;
    private String grade;
    private double moduleCredit;
    private String title;

    public Module() {
    }

    public Module(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleGrade() {
        if (grade == null) {
            return "No grade yet";
        }
        return grade;
    }

    public void setModuleGrade(String grade) {
        this.grade = grade;
    }

    public double getModuleCredit() {
        return moduleCredit;
    }

    public void setModuleCredit(double moduleCredit) {
        this.moduleCredit = moduleCredit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSameModule(Module checkModule) {
        String moduleCode = checkModule.getModuleCode();
        String moduleTitle = checkModule.getTitle();
        return this.moduleCode.equalsIgnoreCase(moduleCode) && this.title.equalsIgnoreCase(moduleTitle);
    }

    public String toString() {
        String format = "%s: %s (%sMC) (Grade: %s)";
        return String.format(format, getModuleCode(), getTitle(), getModuleCredit(), getModuleGrade());
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
