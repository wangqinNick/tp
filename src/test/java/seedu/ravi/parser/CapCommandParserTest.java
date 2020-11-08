//@@author tobiasceg

package seedu.ravi.parser;

import org.junit.jupiter.api.Test;
import seedu.ravi.command.cap.CapCommand;
import seedu.ravi.exception.InvalidCapException;
import seedu.ravi.exception.InvalidMatchException;
import seedu.ravi.exception.InvalidModuleCreditException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CapCommandParserTest {
    static final String PROPER_CAP_INPUT = "45 4.24";
    static final String BAD_CAP_INPUT_CAP = "20 FIVE";

    @Test
    void prepareCapCommand_returnsCapCommand()
            throws InvalidMatchException, InvalidCapException, InvalidModuleCreditException {
        assertTrue(new CapCommandParser().prepareCapCommand(PROPER_CAP_INPUT)
                instanceof CapCommand);
    }

    @Test
    void prepareCapCommand_InvalidMatchException_isThrown() {
        assertThrows(InvalidMatchException.class,
                () -> new CapCommandParser().prepareCapCommand(BAD_CAP_INPUT_CAP));
    }
}
