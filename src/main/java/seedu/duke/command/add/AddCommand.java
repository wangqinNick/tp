//@@author aseanseen
package seedu.duke.command.add;

import seedu.duke.command.Command;
import seedu.duke.ui.TextUi;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String FORMAT_ADD_MODULE = COMMAND_WORD + " -m <module_code>";
    public static final String FORMAT_ADD_TASK = COMMAND_WORD + " -t <task_name> [-by <deadline>]";
    public static final String FORMAT = FORMAT_ADD_TASK + "\n" + FORMAT_ADD_MODULE;
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
    public static final String HELP = AddTaskCommand.HELP + AddModuleCommand.HELP;
}