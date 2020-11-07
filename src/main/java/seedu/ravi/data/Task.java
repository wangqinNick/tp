package seedu.ravi.data;

import seedu.ravi.util.Message;

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

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
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

    public boolean getStatus() {
        return isDone;
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    // For use within codebase
    public LocalDateTime retrieveDeadline() {
        return deadline;
    }

    // For FastJSON use
    public LocalDateTime getDeadline() {
        if (deadline == null) {
            return LocalDateTime.of(1, 1, 1, 0, 0);
        }
        return deadline;
    }

    // For FastJSON use
    public void setDeadline(LocalDateTime deadline) {
        if (deadline.isEqual(LocalDateTime.of(1, 1, 1, 0, 0))) {
            this.deadline = null;
        } else {
            this.deadline = deadline;
        }
    }

    public String generateStatusIcon() {
        if (this.isDone) {
            return Message.ICON_DONE;
        } else {
            return Message.ICON_NOT_DONE;
        }
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma, EEEE, dd MMM yyyy");
        if (retrieveDeadline() == null) {
            return getName() + " [" + generateStatusIcon() + "]";
        } else {
            return getName() + " [" + generateStatusIcon() + "]," + " by " + retrieveDeadline().format(formatter);
        }
    }
}



//import seedu.ravi.directory.Directory;
//import seedu.ravi.directory.DirectoryLevel;
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
