package seedu.duke.command.help;

import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.util.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    static final String HELP_MESSAGE = Message.MESSAGE_GENERAL_HELP;

    @Test
    void testHelpCommand() {
        HelpCommand helpCommand = new HelpCommand("");
        CommandResult commandResult = helpCommand.execute();
        assertEquals(HELP_MESSAGE, commandResult.feedbackToUser);
    }
}
