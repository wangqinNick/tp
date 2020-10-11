package seedu.duke.command.help;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.ui.TextUi;


public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Prints help message
     *
     * @return CommandResult containing list of available commands
     */
    @Override
    public CommandResult execute() {
        String message = TextUi.getHelpMessage();
        return new CommandResult(message);
    }
}