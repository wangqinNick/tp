package seedu.duke.command.delete;

import seedu.duke.command.Command;
import seedu.duke.ui.TextUi;

public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "del";
    public static final String FORMAT = COMMAND_WORD + " <opt> <args>";
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
}
