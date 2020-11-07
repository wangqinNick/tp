package seedu.ravi.command.add;

import seedu.ravi.command.Command;
import seedu.ravi.ui.TextUi;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String FORMAT_ADD_MODULE = COMMAND_WORD + " -m <module_code>";
    public static final String FORMAT_ADD_TASK = COMMAND_WORD + " -t <task_name> [-by <deadline>]";
    public static final String FORMAT = FORMAT_ADD_TASK + "\n" + FORMAT_ADD_MODULE;
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
    public static final String HELP = AddTaskCommand.HELP + AddModuleCommand.HELP;
}