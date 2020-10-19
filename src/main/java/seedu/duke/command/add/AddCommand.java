package seedu.duke.command.add;

import seedu.duke.command.Command;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String FORMAT = COMMAND_WORD + " <opt> <args>";
}