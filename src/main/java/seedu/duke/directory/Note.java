package seedu.duke.directory;

import seedu.duke.util.DateTime;

import static seedu.duke.util.Constant.NO_ICON;
import static seedu.duke.util.Constant.YES_ICON;

public class Note extends Directory {
    private String description;
    private boolean isDone;
    private DateTime deadline;
    private int priority;

    public Note(Module module, String description, DateTime deadline, int priority) {
        super(module);
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
        this.priority = priority;
    }

    public DateTime getDeadline() {
        return deadline;
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

    public int getPriority() {
        return priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDeadline(DateTime deadline) {
        this.deadline = deadline;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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
