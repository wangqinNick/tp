package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.delete.DeleteModuleCommand;
import seedu.duke.command.delete.DeleteTaskCommand;
import seedu.duke.exception.InvalidMatchException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteCommandParserTest {
    static final String VALID_DELETE_TASK_INPUT = "-t 3";
    static final String VALID_DELETE_MODULE_INPUT = "-m CG2271";
    static final String INVALID_DELETE_MODULE_INPUT = "-t 3 or 5 also can";
    static final String INVALID_DELETE_TASK_INPUT = "-m CG2271 or CS2101";

    @Test
    void getDeleteCommand_returnsDeleteModuleCommand() throws InvalidMatchException {
        assertTrue(DeleteCommandParser.getDeleteCommand(VALID_DELETE_MODULE_INPUT)
                instanceof DeleteModuleCommand);
    }

    @Test
    void getDeleteCommand_returnsDeleteTaskCommand() throws InvalidMatchException {
        assertTrue(DeleteCommandParser.getDeleteCommand(VALID_DELETE_TASK_INPUT)
                instanceof DeleteTaskCommand);
    }

    @Test
    void getDeleteCommand_returnsInvalidCommand() throws InvalidMatchException {
        assertTrue(DeleteCommandParser.getDeleteCommand(INVALID_DELETE_TASK_INPUT)
                instanceof IncorrectCommand);
        assertTrue(DeleteCommandParser.getDeleteCommand(INVALID_DELETE_MODULE_INPUT)
                instanceof IncorrectCommand);
    }
}
