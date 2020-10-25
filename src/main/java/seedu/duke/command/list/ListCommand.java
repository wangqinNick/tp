package seedu.duke.command.list;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LIST_EMPTY;
import static seedu.duke.util.Message.MESSAGE_LIST_PRINTED;

public class ListCommand extends Command {
    private Parser.TypeOfEntries typeOfEntry;
    public static final String COMMAND_WORD = "list";
    public static final String FORMAT_TASK = COMMAND_WORD + " -t";
    public static final String FORMAT_MODULE = COMMAND_WORD + " -m";
    public static final String HELP =   "List all tasks in the task list." +
                                        "\n\tFormat:" + FORMAT_TASK +
                                        "\n\nList all modules in the module list." +
                                        "\n\tFormat:" + FORMAT_MODULE;
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
        if (output == null) {
            return new CommandResult(MESSAGE_LIST_EMPTY);
        } else {
            return new CommandResult(MESSAGE_LIST_PRINTED + output);
        }
    }
}
