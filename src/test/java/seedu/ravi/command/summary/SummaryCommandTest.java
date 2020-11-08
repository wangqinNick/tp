package seedu.ravi.command.summary;

import org.junit.jupiter.api.Test;
import seedu.ravi.command.CommandResult;
import seedu.ravi.data.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ravi.util.Message.MESSAGE_SUMMARY_PRINTED;

//@@author amalinasani
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
