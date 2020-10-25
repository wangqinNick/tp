package seedu.duke.command.add;

import seedu.duke.command.Command;
import seedu.duke.ui.TextUi;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String FORMAT = COMMAND_WORD + " <opt> <args>";
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
}