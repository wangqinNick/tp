package seedu.ravi.parser;

import org.junit.jupiter.api.Test;
import seedu.ravi.command.edit.EditModuleCommand;
import seedu.ravi.command.edit.EditTaskCommand;
import seedu.ravi.exception.InvalidMatchException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditCommandParserTest {
    static final String TASK_TO_EDIT = "3 read a book";
    static final String BAD_TASK_TO_EDIT = "read a book";
    static final String MODULE_TO_EDIT = "cs2113 cs3224";

    @Test
    void prepareEditModuleCommandTest_returnsEditModuleCommand() throws InvalidMatchException {
        assertTrue(new EditCommandParser().prepareEditModuleCommand(MODULE_TO_EDIT)
                instanceof EditModuleCommand);
    }

    @Test
    void prepareEditTaskCommandTest_returnsEditTaskCommand() throws InvalidMatchException {
        assertTrue(new EditCommandParser().prepareEditTaskCommand(TASK_TO_EDIT)
                instanceof EditTaskCommand);
    }

    @Test
    void prepareEditTaskCommandTest_NumberFormatException_isThrown() {
        assertThrows(NumberFormatException.class,
                () -> new EditCommandParser().prepareEditTaskCommand(BAD_TASK_TO_EDIT));
    }
}
