package seedu.duke.command.delete;

import seedu.duke.command.Command;

public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "del";
    public static final String FORMAT = COMMAND_WORD + " <opt> <args>";
}
