package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.Executor;
import seedu.duke.command.CommandResult;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.edit.EditModuleCommand;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_EDIT_MODULE_SUCCESS;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

class ParserTest {

    @Test
    void parseCommand_editModuleCommand () {

    }

    @Test
    void isNothingToEdit_emptyString_returnsTrue(){
        assertTrue(new Parser().isNothingToEdit(""));
        assertTrue(new Parser().isNothingToEdit("", ""));
        assertTrue(new Parser().isNothingToEdit("", "", ""));
    }

    @Test
    void isNothingToEdit_nonemptyString_returnsFalse(){
        assertFalse(new Parser().isNothingToEdit(" "));
        assertFalse(new Parser().isNothingToEdit(" ", ""));
        assertFalse(new Parser().isNothingToEdit("", "b", ""));
    }

    @Test
    void prepareEditModuleCommandTest(){
        assertTrue(new Parser().prepareEditModuleCommand(" cs2113 -m cs3224")
                instanceof EditModuleCommand);
        assertTrue(new Parser().prepareEditModuleCommand(" cs2113 cs3224")
                instanceof IncorrectCommand);
    }
}