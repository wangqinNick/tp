package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.edit.EditModuleCommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    void parseCommand_editModuleCommand() {

    }

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
        assertTrue(new Parser().prepareEditModuleCommand(" cs2113 -m cs3224")
                instanceof EditModuleCommand);
        assertTrue(new Parser().prepareEditModuleCommand(" cs2113 cs3224")
                instanceof IncorrectCommand);
    }
}