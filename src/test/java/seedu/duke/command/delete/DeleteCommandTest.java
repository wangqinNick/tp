package seedu.duke.command.delete;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.add.AddModuleCommand;
import seedu.duke.command.add.AddTaskCommand;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;
import seedu.duke.util.ExceptionMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    static final String MOD_CODE = "CS2113T";
    static final String MOD_CODE_MISSING = "CS2040";
    static final String TASK = "test task";
    static final int TASK_ID_MISSING = 10;

    @BeforeEach
    void setupModObjects() {
        ModuleManager.clearModules();
        AddCommand addModule = new AddModuleCommand(MOD_CODE);
        addModule.execute();
    }

    @BeforeEach
    void setupTaskObjects() {
        TaskManager.clear();
        AddCommand addModule = new AddTaskCommand(TASK);
        addModule.execute();
    }

    @Test
    void deleteMissingModuleMessage_isShown() {
        Parser.TypeOfEntries typeOfEntry = Parser.TypeOfEntries.MODULE;
        DeleteCommand deleteMissingModuleTest = new DeleteCommand(typeOfEntry, MOD_CODE_MISSING);
        CommandResult commandResult = deleteMissingModuleTest.execute();
        assertEquals(ExceptionMessage.MESSAGE_MODULE_NOT_FOUND, commandResult.feedbackToUser);
    }

    @Test
    void deleteMissingTaskMessage_isShown() {
        Parser.TypeOfEntries typeOfEntry = Parser.TypeOfEntries.TASK;
        DeleteCommand deleteMissingTaskTest = new DeleteCommand(typeOfEntry, TASK_ID_MISSING);
        CommandResult commandResult = deleteMissingTaskTest.execute();
        assertEquals(ExceptionMessage.MESSAGE_TASK_NOT_FOUND, commandResult.feedbackToUser);
    }
}
