package seedu.duke.command.summary;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.TaskManager;

import static seedu.duke.util.Message.MESSAGE_SUMMARY_PRINTED;

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
