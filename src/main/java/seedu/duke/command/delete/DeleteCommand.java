package seedu.duke.command.delete;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;

import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_PARAMETERS;
import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.duke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.duke.util.Message.MESSAGE_DELETE_MODULE_SUCCESS;

public class DeleteCommand extends Command {
    private Parser.TypeOfEntries typeOfEntry;
    private int taskId;
    private String moduleCode;
    public static final String COMMAND_WORD = "del";
    public static final String FORMAT = COMMAND_WORD + " <opt> <args>";

    /**
     * Constructor to delete task from task list.
     *
     * @param typeOfEntry Type of entry that the user wants to delete.
     * @param taskId ID of the task to be deleted.
     */
    public DeleteCommand(Parser.TypeOfEntries typeOfEntry, int taskId) {
        this.typeOfEntry = typeOfEntry;
        this.taskId = taskId;
    }

    /**
     * Constructor to delete module from module list.
     *
     * @param typeOfEntry Type of entry that the user wants to delete.
     * @param moduleCode Module code to be deleted.
     */
    public DeleteCommand(Parser.TypeOfEntries typeOfEntry, String moduleCode) {
        this.typeOfEntry = typeOfEntry;
        this.moduleCode = moduleCode;
    }

    /**
     * Deletes the task from the task list.
     *
     * @param taskId ID of the task to be deleted.
     * @throws TaskManager.TaskNotFoundException If the task is not found in the task list.
     */
    private void deleteTask(int taskId) throws TaskManager.TaskNotFoundException {
        TaskManager.delete(taskId);
    }

    /**
     * Deletes the module from the module list.
     *
     * @param moduleCode Module code to be deleted.
     * @throws ModuleManager.ModuleNotFoundException If the module is not found in the module list.
     */
    private void deleteModule(String moduleCode) throws ModuleManager.ModuleNotFoundException {
        ModuleManager.delete(moduleCode);
    }

    /**
     * Deletes the task from their respective lists.
     *
     * @return CommandResult containing acknowledgement of the delete.
     */
    @Override
    public CommandResult execute() {
        String message = "";
        try {
            switch (typeOfEntry) {
            case TASK:
                deleteTask(taskId);
                message = MESSAGE_DELETE_TASK_SUCCESS;
                break;
            case MODULE:
                deleteModule(moduleCode);
                message = MESSAGE_DELETE_MODULE_SUCCESS;
                break;
            default:
                message = MESSAGE_INVALID_PARAMETERS;
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
