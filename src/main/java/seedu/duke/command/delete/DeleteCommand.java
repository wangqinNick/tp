package seedu.duke.command.delete;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;
import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.duke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.duke.util.Message.MESSAGE_DELETE_MODULE_SUCCESS;

public class DeleteCommand extends Command {
    private Parser.typeOfEntries typeOfEntry;
    private int taskId;
    private String moduleCode;

    public DeleteCommand(Parser.typeOfEntries typeOfEntry, int taskId, String moduleCode) {
        this.typeOfEntry = typeOfEntry;
        this.taskId = taskId;
        this.moduleCode = moduleCode;
    }

    /**
     * Deletes the task from the task list.
     *
     * @param taskId
     * @throws TaskManager.TaskNotFoundException If the task is not found in the task list.
     */
    private void deleteTask(int taskId) throws TaskManager.TaskNotFoundException {
        TaskManager.delete(taskId);
    }

    /**
     * Deletes the module from the module list.
     *
     * @param moduleCode
     * @throws ModuleManager.ModuleNotFoundException If the module is not found in the module list.
     */
    private void deleteModule(String moduleCode) throws ModuleManager.ModuleNotFoundException {
        ModuleManager.delete(moduleCode);
    }

    /**
     * Deletes the task from their respective lists
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
            }
            return new CommandResult(message);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
    }
}
