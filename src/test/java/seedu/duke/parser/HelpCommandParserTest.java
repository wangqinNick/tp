//@@author tobiasceg

package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.help.HelpCommand;
import seedu.duke.exception.InvalidMatchException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpCommandParserTest {
    static final String VALID_HELP_INPUT_GENERIC = "";
    static final String VALID_HELP_INPUT_SPECIFIC = "edit";

    @Test
    void prepareHelpCommand_returnHelpCommand() throws InvalidMatchException {
        assertTrue(HelpCommandParser.prepareHelpCommand(VALID_HELP_INPUT_GENERIC)
                instanceof HelpCommand);
        assertTrue(HelpCommandParser.prepareHelpCommand(VALID_HELP_INPUT_SPECIFIC)
                instanceof HelpCommand);
    }
}
