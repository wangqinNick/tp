package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.edit.EditModuleCommand;
import seedu.duke.command.edit.EditTaskCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ParserTest {
    static final String TASK_TO_EDIT = "3 read a book";
    static final String BAD_TASK_TO_EDIT = "read a book";
    static final String TASK_TO_ADD_WITH_DEADLINE = "-t project work -by 2-2-2020 1800";
    static final String TASK_TO_ADD_WITHOUT_DEADLINE = "-t do quiz";
    static final String BAD_TASK_TO_ADD = "-t do quiz -by ";

    @Test
    void isNothingToEdit_emptyString_returnsTrue() {
        assertTrue(new Parser().isEmptyParse(""));
        assertTrue(new Parser().isEmptyParse("", ""));
        assertTrue(new Parser().isEmptyParse("", "", ""));
    }

    @Test
    void isNothingToEdit_nonemptyString_returnsFalse() {
        assertFalse(new Parser().isEmptyParse(" "));
        assertFalse(new Parser().isEmptyParse(" ", ""));
        assertFalse(new Parser().isEmptyParse("", "b", ""));
    }

    @Test
    void prepareEditModuleCommandTest() {

        assertTrue(new EditCommandParser().prepareEditModuleCommand("-m cs2113 cs3224")
                instanceof EditModuleCommand);
        assertTrue(new EditCommandParser().prepareEditModuleCommand(" cs2113 cs3224")
                instanceof IncorrectCommand);
    }

    @Test
    void prepareEditTaskCommandTest_returnsEditTaskCommand() {
        assertTrue(new EditCommandParser().prepareEditTaskCommand(TASK_TO_EDIT)
                instanceof EditTaskCommand);
    }

    @Test
    void prepareEditTaskCommandTest_badTaskToEdit_isThrown() {
        assertThrows(NumberFormatException.class,
            () -> new EditCommandParser().prepareEditTaskCommand(BAD_TASK_TO_EDIT));
    }

    @Test
    void prepareAddCommandTest_returnsAddCommand() {
        assertTrue(new AddCommandParser().prepareAddCommand(TASK_TO_ADD_WITH_DEADLINE)
                instanceof AddCommand);
        assertTrue(new AddCommandParser().prepareAddCommand(TASK_TO_ADD_WITHOUT_DEADLINE)
                instanceof AddCommand);
    }

    @Test
    void prepareAddCommandTest_returnsIncorrectCommand() {
        assertTrue(new AddCommandParser().prepareAddCommand(BAD_TASK_TO_ADD)
                instanceof IncorrectCommand);
    }
}