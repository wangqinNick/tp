package seedu.duke.directory;

public class Task extends Directory {

    public enum typeOfTasks{
        TASK, MODULE
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
