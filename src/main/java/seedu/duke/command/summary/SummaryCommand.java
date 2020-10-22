package seedu.duke.command.summary;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.TaskManager;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LIST_EMPTY;
import static seedu.duke.util.Message.MESSAGE_SUMMARY_PRINTED;

public class SummaryCommand extends Command {
    public static final String COMMAND_WORD = "summary";

    /**
     * Lists the tasks from respective lists in summaryLists.
     *
     * @return CommandResult containing task summary message
     */
    @Override
    public CommandResult execute() {
        String output = "";
        output = TaskManager.getSummary();
        if (output.equals("")) {
            return new CommandResult(MESSAGE_LIST_EMPTY);
        } else {
            return new CommandResult(MESSAGE_SUMMARY_PRINTED + output);
        }
    }
}
