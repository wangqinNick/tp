package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.done.DoneCommand;
import seedu.duke.exception.InvalidMatchException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoneCommandParserTest {
    static final String VALID_DONE_INPUT = "5";
    static final String INVALID_DONE_INPUT_STRING = "Five";
    static final String INVALID_DONE_INPUT_EXCESS = "5 or 10";

    @Test
    void prepareDoneCommand_returnsDoneCommand() throws InvalidMatchException {
        assertTrue(DoneCommandParser.prepareDoneCommand(VALID_DONE_INPUT)
                instanceof DoneCommand);
    }

    @Test
    void prepareDoneCommand_NumberFormatException_isThrown() {
        assertThrows(NumberFormatException.class,
                () -> DoneCommandParser.prepareDoneCommand(INVALID_DONE_INPUT_STRING));
    }

    @Test
    void prepareDoneCommand_InvalidMatchException_isThrown() {
        assertThrows(InvalidMatchException.class,
                () -> DoneCommandParser.prepareDoneCommand(INVALID_DONE_INPUT_EXCESS));
    }

}
