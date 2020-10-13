package seedu.duke.command.delete;

import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.parser.Parser;
import seedu.duke.util.ExceptionMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    static final String MOD_CODE_1 = "CS2113T";
    static final int TASK_ID = 1;

    @Test
    void deleteMissingModule_MESSAGE_MODULE_NOT_FOUND_isShown() {
        Parser.TypeOfEntries typeOfEntry = Parser.TypeOfEntries.MODULE;
        DeleteCommand deleteMissingModuleTest = new DeleteCommand(typeOfEntry, MOD_CODE_1);
        CommandResult commandResult = deleteMissingModuleTest.execute();
        assertEquals(ExceptionMessage.MESSAGE_MODULE_NOT_FOUND, commandResult.feedbackToUser);
    }

    @Test
    void deleteMissingTask_MESSAGE_TASK_NOT_FOUND_isShown() {
        Parser.TypeOfEntries typeOfEntry = Parser.TypeOfEntries.TASK;
        DeleteCommand deleteMissingTaskTest = new DeleteCommand(typeOfEntry, TASK_ID);
        CommandResult commandResult = deleteMissingTaskTest.execute();
        assertEquals(ExceptionMessage.MESSAGE_TASK_NOT_FOUND, commandResult.feedbackToUser);
    }
}
