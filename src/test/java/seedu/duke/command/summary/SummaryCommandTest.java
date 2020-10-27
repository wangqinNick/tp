package seedu.duke.command.summary;

import org.junit.jupiter.api.BeforeEach;
import seedu.duke.data.Task;
import seedu.duke.data.TaskManager;

public class SummaryCommandTest {

    @BeforeEach
    void setupTaskList() {
        TaskManager.clear();
        TaskManager.add(new Task("Undated Task 1"));
        TaskManager.add(new Task("Undated Task 2"));
        TaskManager.add(new Task("Dated Task 1 -by 20-12-2020 1800"));
        TaskManager.add(new Task("Dated Task 2 -by 30-12-2020 1800"));
    }
}
