package seedu.ravi.command.list;

import seedu.ravi.command.Command;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.PromptType;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.data.TaskManager;
import seedu.ravi.parser.Parser;
import seedu.ravi.ui.TextUi;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_LIST_EMPTY;
import static seedu.ravi.util.Message.MESSAGE_LIST_PRINTED;

//@@author amalinasani
public class ListCommand extends Command {
    private final Parser.TypeOfEntries typeOfEntry;
    public static final String COMMAND_WORD = "list";
    public static final String FORMAT_TASK = COMMAND_WORD + " -t";
    public static final String FORMAT_MODULE = COMMAND_WORD + " -m";
    public static final String FORMAT = FORMAT_TASK + "\n" + FORMAT_MODULE;
    public static final String HELP =   "List all tasks in the task list."
                                        + "\n\t@|bold,blue,BG_BLACK Format:|@ " + FORMAT_TASK
                                        + "\n\nList all modules in the module list."
                                        + "\n\t@|bold,blue,BG_BLACK Format:|@ " + FORMAT_MODULE;
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);

    public ListCommand(Parser.TypeOfEntries typeOfEntry) {
        this.typeOfEntry = typeOfEntry;
        setPromptType(PromptType.INFORMATIVE);
    }

    /**
     * Lists the tasks/modules from their respective lists.
     *
     * @return CommandResult containing list of tasks/modules
     */
    @Override
    public CommandResult execute() {
        String output = "";
        switch (typeOfEntry) {
        case TASK:
            output = TaskManager.list();
            break;
        case MODULE:
            output = ModuleManager.list();
            break;
        default:
            break;
        }
        if (output != null) {
            return new CommandResult(MESSAGE_LIST_PRINTED + output);
        } else {
            return new CommandResult(MESSAGE_LIST_EMPTY);
        }
    }
}
