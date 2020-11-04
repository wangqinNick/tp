package seedu.duke.command.summary;

import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.data.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.util.Message.MESSAGE_SUMMARY_PRINTED;

public class SummaryCommandTest {
    @Test
    void listCommand_Summary_isShown() {
        TaskManager.clear();
        SummaryCommand summaryCommand = new SummaryCommand();
        CommandResult commandResult = summaryCommand.execute();
        String expectedOutput = MESSAGE_SUMMARY_PRINTED + TaskManager.getSummary();
        assertEquals(expectedOutput, commandResult.feedbackToUser);
    }
}
