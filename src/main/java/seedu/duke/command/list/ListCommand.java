package seedu.duke.command.list;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;
import static seedu.duke.util.ExceptionMessage.MESSAGE_LIST_EMPTY;
import static seedu.duke.util.Message.MESSAGE_LIST_PRINTED;

public class ListCommand extends Command {
    private Parser.typeOfEntries typeOfEntry;
    public static final String COMMAND_WORD = "list";
    public static final String FORMAT = COMMAND_WORD + " <opt> <args>";

    public ListCommand(Parser.typeOfEntries typeOfEntry) {
        this.typeOfEntry = typeOfEntry;
    }

    /**
     * Lists all the tasks from the task list.
     *
     * @throws TaskManager.TaskNotFoundException If the task is not found in the task list.
     */
    private void listTask() throws TaskManager.TaskListEmptyException {
        TaskManager.list();
    }

    /**
     * Lists all the modules from the module list.
     *
     * @throws ModuleManager.ModuleNotFoundException If the module is not found in the module list.
     */
    private void listModule() throws ModuleManager.ModuleListEmptyException {
        ModuleManager.list();
    }

    /**
     * Lists the tasks/modules from their respective lists
     *
     * @return CommandResult containing acknowledgement of the delete.
     */
    @Override
    public CommandResult execute() {
        try {
            switch (typeOfEntry) {
                case TASK:
                    listTask();
                    break;
                case MODULE:
                    listModule();
                    break;
            }
            return new CommandResult(MESSAGE_LIST_PRINTED);
        } catch (ModuleManager.ModuleListEmptyException | TaskManager.TaskListEmptyException e) {
            return new CommandResult(MESSAGE_LIST_EMPTY);
        }
    }
}
