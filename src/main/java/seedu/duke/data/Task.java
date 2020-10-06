package seedu.duke.data;

import java.time.LocalDateTime;

public class Task {
    private String name;
    private LocalDateTime deadline;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, LocalDateTime dateTimeOfDeadline) {
        this.name = name;
        this.deadline = dateTimeOfDeadline;
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
