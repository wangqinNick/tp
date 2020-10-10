package seedu.duke.data;

import java.time.LocalDateTime;

public class Task {
    private String name;
    private LocalDateTime deadline;
    private boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public Task(String name, LocalDateTime dateTimeOfDeadline) {
        this.name = name;
        this.deadline = dateTimeOfDeadline;
        this.status = false;
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

    public boolean getStatus(){
        return status;
    }

    public void setStatus(){
        this.status = true;
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
