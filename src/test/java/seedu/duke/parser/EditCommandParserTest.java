package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.edit.EditModuleCommand;
import seedu.duke.command.edit.EditTaskCommand;
import seedu.duke.exception.InvalidMatchException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditCommandParserTest {
    static final String TASK_TO_EDIT = "3 read a book";
    static final String BAD_TASK_TO_EDIT = "read a book";
    static final String MODULE_TO_EDIT = "cs2113 cs3224";
    static final String BAD_MODULE_TO_EDIT = "";

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
