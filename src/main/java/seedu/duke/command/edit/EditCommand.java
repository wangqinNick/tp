package seedu.duke.command.edit;

import seedu.duke.command.Command;
import seedu.duke.ui.TextUi;

public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
}
