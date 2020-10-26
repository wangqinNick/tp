package seedu.duke.command.delete;

import seedu.duke.command.Command;
import seedu.duke.ui.TextUi;

public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "del";
    public static final String FORMAT_DELETE_TASK = DeleteCommand.COMMAND_WORD + " -t" + " <task_index>";
    public static final String FORMAT_DELETE_MODULE = DeleteCommand.COMMAND_WORD + " -m" + " <module_code>";
    public static final String FORMAT = FORMAT_DELETE_TASK + "\n" + FORMAT_DELETE_MODULE;
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
    public static final String HELP = DeleteTaskCommand.HELP + DeleteModuleCommand.HELP;
}
