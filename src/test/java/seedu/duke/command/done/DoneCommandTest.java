package seedu.duke.command.done;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.command.add.AddCommand;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;
import seedu.duke.util.ExceptionMessage;
import seedu.duke.util.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {

    @BeforeEach
    void setupTaskList() {
        TaskManager.clear();
        Parser.TypeOfEntries typeOfEntry = Parser.TypeOfEntries.TASK;
        AddCommand addModule = new AddCommand(typeOfEntry, "Read book", null);
        addModule.execute();
    }

    @Test
    void doneTask_validIndex_success() {
        DoneCommand doneValidTask = new DoneCommand(1);
        CommandResult commandResult = doneValidTask.execute();
        assertEquals(Message.MESSAGE_DONE_TASK_SUCCESS, commandResult.feedbackToUser);
    }

    @Test
    void doneTask_invalidIndex_exceptionThrown() {
        DoneCommand doneInvalidTask = new DoneCommand(2);
        CommandResult commandResult = doneInvalidTask.execute();
        assertEquals(ExceptionMessage.MESSAGE_TASK_NOT_FOUND, commandResult.feedbackToUser);
    }
}
