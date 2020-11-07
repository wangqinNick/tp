package seedu.ravi.parser;

import org.junit.jupiter.api.Test;
import seedu.ravi.command.list.ListCommand;
import seedu.ravi.exception.InvalidMatchException;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListCommandParserTest {
    static final String VALID_LIST_TASK_INPUT = "-t";
    static final String VALID_LIST_MODULE_INPUT = "-m";
    static final String INVALID_LIST_PREFIX = "-by";
    static final String INVALID_LIST_TASK_INPUT_EXCESS = "-t now";

    @Test
    void getListCommand_returnListCommand() throws InvalidMatchException {
        assertTrue(ListCommandParser.getListCommand(VALID_LIST_MODULE_INPUT)
                instanceof ListCommand);
        assertTrue(ListCommandParser.getListCommand(VALID_LIST_TASK_INPUT)
                instanceof ListCommand);
    }

    @Test
    void getListCommand_InvalidMatcherException_isThrown() throws InvalidMatchException {
        assertThrows(InvalidMatchException.class,
                () -> ListCommandParser.getListCommand(INVALID_LIST_TASK_INPUT_EXCESS));
    }

    @Test
    void getListCommand_InvalidParameterException_isThrown() throws InvalidMatchException {
        assertThrows(InvalidParameterException.class,
                () -> ListCommandParser.getListCommand(INVALID_LIST_PREFIX));
    }
}
