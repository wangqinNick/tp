package seedu.duke.command;

import static seedu.duke.util.Message.MESSAGE_EXIT_ACKNOWLEDGEMENT;

public class ExitCommand extends Command {
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }
}
