package seedu.duke.command;

import static seedu.duke.util.Message.MESSAGE_GOODBYE;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_GOODBYE, false, true);
    }
}
