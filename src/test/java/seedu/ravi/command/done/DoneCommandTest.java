package seedu.ravi.command.done;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ravi.command.CommandResult;
import seedu.ravi.data.Task;
import seedu.ravi.data.TaskManager;
import seedu.ravi.util.ExceptionMessage;
import seedu.ravi.util.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author amalinasani
public class DoneCommandTest {

    @BeforeEach
    void setupTaskList() {
        TaskManager.clear();
        Task newTask = new Task("read a book");
        TaskManager.add(newTask);
    }

    @Test
    void doneTask_validIndex_success() {
        DoneCommand doneValidTask = new DoneCommand(0);
        CommandResult commandResult = doneValidTask.execute();
        Task editedTask = new Task("read a book", true);
        assertEquals(String.format(Message.MESSAGE_DONE_TASK_SUCCESS, editedTask.toString()),
                commandResult.feedbackToUser);
    }

    @Test
    void doneTask_invalidIndex_exceptionThrown() {
        DoneCommand doneInvalidTask = new DoneCommand(2);
        CommandResult commandResult = doneInvalidTask.execute();
        assertEquals(ExceptionMessage.MESSAGE_TASK_NOT_FOUND, commandResult.feedbackToUser);
    }
}
