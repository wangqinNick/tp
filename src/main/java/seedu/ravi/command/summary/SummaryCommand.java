package seedu.ravi.command.summary;

import seedu.ravi.command.Command;
import seedu.ravi.command.CommandResult;
import seedu.ravi.data.TaskManager;

import static seedu.ravi.util.Message.MESSAGE_SUMMARY_PRINTED;

//@@author amalinasani
public class SummaryCommand extends Command {
    public static final String COMMAND_WORD = "summary";
    public static final String HELP =   "View task summary."
                                        + "\n\tFormat: " + COMMAND_WORD;

    /**
     * Lists the tasks from respective lists in summaryLists.
     *
     * @return CommandResult containing task summary message
     */
    @Override
    public CommandResult execute() {
        String output = TaskManager.getSummary();
        return new CommandResult(MESSAGE_SUMMARY_PRINTED + output);

    }
}
