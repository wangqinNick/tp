package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.edit.EditModuleCommand;
import seedu.duke.command.edit.EditTaskCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

class ParserTest {
    static final String TASK_TO_EDIT = "3 read a book";
    static final String BAD_TASK_TO_EDIT = "read a book";
    static final String TASK_TO_ADD_WITH_DEADLINE = "project work -by 2-2-2020 1800";
    static final String TASK_TO_ADD_WITHOUT_DEADLINE = "do quiz";
    static final String BAD_TASK_TO_ADD = "do quiz -by ";

    @Test
    void isNothingToEdit_emptyString_returnsTrue() {
        assertTrue(new Parser().isNothingToEdit(""));
        assertTrue(new Parser().isNothingToEdit("", ""));
        assertTrue(new Parser().isNothingToEdit("", "", ""));
    }

    @Test
    void isNothingToEdit_nonemptyString_returnsFalse() {
        assertFalse(new Parser().isNothingToEdit(" "));
        assertFalse(new Parser().isNothingToEdit(" ", ""));
        assertFalse(new Parser().isNothingToEdit("", "b", ""));
    }

    @Test
    void prepareEditModuleCommandTest() {
        assertTrue(new Parser().prepareEditModuleCommand("-m cs2113 cs3224")
                instanceof EditModuleCommand);
        assertTrue(new Parser().prepareEditModuleCommand(" cs2113 cs3224")
                instanceof IncorrectCommand);
    }

    @Test
    void prepareEditTaskCommandTest_returnsEditTaskCommand() {
        assertTrue(new Parser().prepareEditTaskCommand(TASK_TO_EDIT)
                instanceof EditTaskCommand);
    }

    @Test
    void prepareEditTaskCommandTest_badTaskToEdit_isThrown() {
        assertThrows(NumberFormatException.class, () -> new Parser().prepareEditTaskCommand(BAD_TASK_TO_EDIT));
    }

    @Test
    void prepareAddCommandTest_returnsAddCommand() {
        assertTrue(new Parser().prepareAddCommand(TASK_TO_ADD_WITH_DEADLINE, Parser.TypeOfEntries.TASK)
                instanceof AddCommand);
        assertTrue(new Parser().prepareAddCommand(TASK_TO_ADD_WITHOUT_DEADLINE, Parser.TypeOfEntries.TASK)
                instanceof AddCommand);
    }

    @Test
    void prepareAddCommandTest_returnsIncorrectCommand() {
        assertTrue(new Parser().prepareAddCommand(BAD_TASK_TO_ADD, Parser.TypeOfEntries.TASK)
                instanceof IncorrectCommand);
    }
}