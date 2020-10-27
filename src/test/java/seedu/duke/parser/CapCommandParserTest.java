package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.cap.CapCommand;
import seedu.duke.exception.InvalidMatchException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CapCommandParserTest {
    static final String PROPER_CAP_INPUT = "45 4.24";
    static final String BAD_CAP_INPUT_CAP_IS_STRING = "20 FIVE";

    @Test
    void prepareCapCommand_returnsCapCommand() throws InvalidMatchException {
        assertTrue(new CapCommandParser().prepareCapCommand(PROPER_CAP_INPUT)
                instanceof CapCommand);
    }

    @Test
    void prepareCapCommand_NumberFormatException_isThrown() {
        assertThrows(NumberFormatException.class,
            () -> new CapCommandParser().prepareCapCommand(BAD_CAP_INPUT_CAP_IS_STRING));
    }
}
