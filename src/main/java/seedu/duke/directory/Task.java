package seedu.duke.directory;

import seedu.duke.util.DateTime;

import java.time.LocalDate;

import static seedu.duke.util.Constant.NO_ICON;
import static seedu.duke.util.Constant.YES_ICON;

public class Task extends Directory {
    private String description;
    private boolean isDone;
    private DateTime deadline;

    public Task(Directory module, String description, DateTime deadline) {
        super(module);
        this.description = description;
        this.deadline = deadline;
        this.isDone = false;
    }

    public Task(Directory module, String description) {
        super(module);
        this.description = description;
        this.deadline = new DateTime(LocalDate.MAX);
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

    public DateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(DateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public Module getParent() {
        return (Module) this.parent;
    }

    @Override
    public DirectoryLevel getLevel() {
        return DirectoryLevel.TASK;
    }

    /**
     * Returns a string containing the standard Task attributes.
     *
     * @return
     *  A string containing the standard Task attributes
     */
    @Override
    public String toString() {
        return String.format("Task Description: %s\nModule Code: %s\n\n",
                description, getParent().getModuleCode()) +
                String.format("Deadline: %s\n", deadline.isPresent() ? deadline.toShow() : "-") +
                String.format("Done Status: %s\n", isDone ? "Completed" : "Incomplete") +
                "Tags: ";
    }
}
