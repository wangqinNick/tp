package seedu.duke.data;

import seedu.duke.util.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private LocalDateTime deadline = null;
    private boolean isDone;

    public Task() {
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, LocalDateTime dateTimeOfDeadline) {
        this.name = name;
        this.deadline = dateTimeOfDeadline;
        this.isDone = false;
    }

    public Task(String name, LocalDateTime dateTimeOfDeadline, boolean isDone) {
        this.name = name;
        this.deadline = dateTimeOfDeadline;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setStatus() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        if (this.isDone) {
            return Message.ICON_DONE;
        } else {
            return Message.ICON_NOT_DONE;
        }
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma, EEEE, dd MMM yy ");
        if (getDeadline() == null) {
            return getName() + " [" + getStatusIcon() + "]";
        } else {
            return getName() + " [" + getStatusIcon() + "]," + " by " + getDeadline().format(formatter);
        }
    }
}



//import seedu.duke.directory.Directory;
//import seedu.duke.directory.DirectoryLevel;
//
//public class Task extends Directory {
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
