package seedu.duke.command.list;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;
import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.duke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;

public class ListCommand extends Command {
    private Parser.typeOfEntries typeOfEntry;
    public static final String COMMAND_WORD = "list";
    public static final String FORMAT = COMMAND_WORD + " <opt> <args>";

    public ListCommand(Parser.typeOfEntries typeOfEntry {
        this.typeOfEntry = typeOfEntry;
    }

    /**
     * Lists all the tasks from the task list.
     *
     * @throws TaskManager.TaskNotFoundException If the task is not found in the task list.
     */
    private void listTask() throws TaskManager.TaskNotFoundException {
        TaskManager.list();
    }

    /**
     * Lists all the modules from the module list.
     *
     * @throws ModuleManager.ModuleNotFoundException If the module is not found in the module list.
     */
    private void listModule() throws ModuleManager.ModuleNotFoundException {
        ModuleManager.list();
    }

    /**
     * Lists the tasks/modules from their respective lists
     *
     * @return CommandResult containing acknowledgement of the delete.
     */
    @Override
    public CommandResult execute() {
        String message = "";
        try {
            switch (typeOfEntry) {
                case TASK:
                    listTask();
                    message = MESSAGE_LIST_TASK_SUCCESS;
                    break;
                case MODULE:
                    listModule();
                    message = MESSAGE_LIST_MODULE_SUCCESS;
                    break;
            }
            return new CommandResult(message);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        }
    }
}
