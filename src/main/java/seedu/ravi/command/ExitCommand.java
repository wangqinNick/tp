//@@author aseanseen

package seedu.ravi.command;

import static seedu.ravi.util.Message.MESSAGE_GOODBYE;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_GOODBYE, false, true);
    }
}
