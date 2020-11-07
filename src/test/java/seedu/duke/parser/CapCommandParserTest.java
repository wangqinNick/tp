//@@author tobiasceg
package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.cap.CapCommand;
import seedu.duke.exception.InvalidCapException;
import seedu.duke.exception.InvalidMatchException;
import seedu.duke.exception.InvalidModuleCreditException;

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
