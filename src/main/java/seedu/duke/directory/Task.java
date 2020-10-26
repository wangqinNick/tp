package seedu.duke.directory;

import seedu.duke.util.DateTime;

import static seedu.duke.util.Constant.NO_ICON;
import static seedu.duke.util.Constant.YES_ICON;

public class Task extends Directory {
    private String description;
    private boolean isDone;

    public Task(Directory module, String description) {
        super(module);
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? YES_ICON : NO_ICON);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isSameTask(String taskDescription) {
        return this.description.equals(taskDescription);
    }

    @Override
    public Module getParent() {
        return (Module) this.parent;
    }

    @Override
    public DirectoryLevel getLevel() {
        return DirectoryLevel.TASK;
    }
}
