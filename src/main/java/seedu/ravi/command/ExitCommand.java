//@@author aseanseen

package seedu.ravi.command;

import static seedu.ravi.util.Message.MESSAGE_GOODBYE;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String HELP =   "Exit ra.VI and saves all changes."
            + "\n\t@|bold,blue,BG_BLACK Format:|@ " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_GOODBYE, false, true);
    }
}
